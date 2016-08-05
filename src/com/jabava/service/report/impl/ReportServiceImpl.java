package com.jabava.service.report.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.UnifiedJEXL;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.common.EhrFileMapper;
import com.jabava.dao.manage.EhrCompanyMapper;
import com.jabava.dao.report.EhrReportConfigMapper;
import com.jabava.dao.report.EhrReportDao;
import com.jabava.dao.report.EhrReportMapper;
import com.jabava.dao.report.EhrReportParamMapper;
import com.jabava.pojo.common.EhrFile;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.pojo.report.EhrReport;
import com.jabava.pojo.report.EhrReportConfig;
import com.jabava.service.report.IReportService;
import com.jabava.utils.Constants;
import com.jabava.utils.FileUtil;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.enums.JabavaEnum;
import com.jabava.utils.privilege.AuthorisedPersonUtil;

@Service
public class ReportServiceImpl implements IReportService {
	@Autowired
	private EhrReportMapper reportMapper;
	@Autowired
	private EhrReportConfigMapper reportConfigMapper;
	@Autowired
	private EhrReportParamMapper reportParamMapper;
	@Autowired
	private EhrFileMapper fileMapper;
	@Autowired
	private EhrReportDao reportDao;
	@Autowired
	private EhrCompanyMapper companyMapper;

	@Override
	public List<EhrReport> listReportPage(Map<String, Object> params) {
		List<EhrReport> result = reportMapper.listReportPage(params);
		for(EhrReport r : result){
			if(JabavaEnum.ReportScopeEnum.COMPANY.getValue().equals(r.getScopeType())){
				r.setScopeName(companyMapper.selectByPrimaryKey(r.getScopeId()).getCompanyName());
			}
		}
		return result;
	}

	@Override
	public EhrReport selectByTypeAndName(Long companyId, String reportType,
			String reportName) {
		return reportMapper.selectByTypeAndName(companyId, reportType, reportName);
	}

	@Override
	public int saveOrUpdate(EhrUser user, EhrReport report) {
		Date now = new Date();
		report.setCompanyId(user.getCompanyId());
		report.setLastModifyDate(now);
		report.setLastModifyUserId(user.getUserId());
		report.setLastModifyUserName(user.getUserName());
		if(report.getReportId() == null){
			report.setCreateDate(now);
			report.setCreateUserId(user.getUserId());
			report.setCreateUserName(user.getUserName());
			return reportMapper.insertSelective(report);
		}else{
			return reportMapper.updateByPrimaryKey(report);
		}
	}

	@Override
	public int deleteById(Long companyId, Long reportId) {
		reportConfigMapper.deleteByReportId(reportId);
		reportParamMapper.deleteByReportId(reportId);
		reportMapper.deleteById(companyId, reportId);
		return 1;
	}

	@Override
	public EhrReport loadReport(Long companyId, Long reportId) {
		return reportMapper.selectByReportId(companyId, reportId);
	}

	@Override
	public EhrReport selectByReportId(Long reportId) {
		return reportMapper.selectByPrimaryKey(reportId);
	}

	@Override
	public List<EhrReport> listReportByType(Long companyId, String reportType) {
		return reportMapper.listReportByType(companyId, reportType);
	}

	@Override
	public Map<String, Object> generateReport(EhrUser user, EhrReport report, Map<String, Object> params) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//添加常量到上下文
		params.put("companyId", user.getCompanyId());
		params.put("userId", user.getUserId());
		params.put("userName", user.getUserName());
		String ap = AuthorisedPersonUtil.getUserAuthorisedPersonList();
		params.put("UserAuthorisedPerson", StringUtils.isEmpty(ap) ? "''" : ap);
		//params.put("OrgAuthorisedPerson", AuthorisedPersonUtil.getOrgAuthorisedPersonList());
		map.putAll(params);
		
		//数据集名称与父数据集关联元素映射
		Map<String,String> datasetRelationMap = new HashMap<String,String>();
		//数据集名称与数据映射
        Map<String,List<Map<String, Object>>> datasetDataMap = new HashMap<String,List<Map<String, Object>>>();
		//创建一个map，放动态数据集和动态关联元素
		Map<String, Object> mapDynamic = new HashMap<String, Object>();
		//判断是否为分页
		String multiPageDataSet = null;
		//创建一个MultiSheetList
		String multiBeanName = null;
		//每页显示行数
		Integer maxResultPerSheet = -1;
		
		//获取报表配置
		List<EhrReportConfig> configList = reportConfigMapper.listByReportId(report.getReportId());

		//父数据集/单个数据集先执行
		for (EhrReportConfig config : configList) {
			String parentReportDataset = config.getParentReportDataset();
			if (parentReportDataset == null || StringUtils.isEmpty(parentReportDataset.trim())) {
				String reportDataset = config.getReportDataset();
				String sql = config.getSqlSentence();
				if (!StringUtils.isEmpty(sql)) {
					String formatSql = formatSql(sql, params);
					List<Map<String, Object>> list = reportDao.getResultBySql(formatSql);
					map.put(reportDataset, list);
					//判断是否多sheet
					if (config.getIsMultiSheetColumn() == 1) {
						//multiBeanName = reportDataset;
                        multiBeanName = "sheetdata";
                        datasetRelationMap.put(reportDataset, config.getParentAssociateElement());
						//maxResultPerSheet = maxResultPerSheet > config.getMaxResultPerSheet() ? maxResultPerSheet : config
						//		.getMaxResultPerSheet();
                        datasetDataMap.put(reportDataset, list);
					}
					//判断是否多页
					if (config.getIsMultiPageColumn() == 1) {
						//multiPageDataSet = reportDataset;
                        multiPageDataSet = "multiList";
						maxResultPerSheet = maxResultPerSheet > config.getMaxResultPerSheet() ? maxResultPerSheet : config
								.getMaxResultPerSheet();
						//multiDataSetPerSheet.add(list);
						datasetDataMap.put(reportDataset, list);
					}
					//判断是否为动态列
					if (config.getIsDynamicColumn() == 1) {
						mapDynamic.put("list", list);
						mapDynamic.put("element", config.getDynamicColumnElement());
					}
				} else {
					map.put(reportDataset, params.get(reportDataset));
				}
			}
		}

		//子数据集后执行
		for (EhrReportConfig config : configList) {
			String parentReportDataset = config.getParentReportDataset();
			if (parentReportDataset != null && !StringUtils.isEmpty(parentReportDataset.trim())) {
				String reportDataset = config.getReportDataset();
				String parentAssociateElement = config.getParentAssociateElement();
				String sql = config.getSqlSentence();
				List<Map<String, Object>> list = (List<Map<String, Object>>) map.get(parentReportDataset);
                if (list == null){
                	continue;
                }
				
				if(config.getIsDynamicColumn() == 1){	//Map形式
					for (Map<String, Object> mapInternal : list) {
						String[] childParamArray = parentAssociateElement.split(",");
						for (String childParam : childParamArray) {
							String value = String.valueOf(mapInternal.get(childParam));
							params.put(childParam, value);
						}
						String formatSql = formatSql(sql, params);
						List<Map<String, Object>> listInternal = reportDao.getResultBySql(formatSql);
						Map<Object,Map<String, Object>> subdatamap = new HashMap<Object,Map<String, Object>> ();
						for(Map<String, Object> sdata : listInternal){
							String keyname = config.getDynamicColumnElement();
							if(keyname == null || "".equals(keyname)){
								throw new Exception("子数据集主键定义错误");
							}
							subdatamap.put(sdata.get(keyname), sdata);
						}
						
						//给父数据集的每个实体插入一个map
						mapInternal.put(reportDataset, subdatamap);
					}
				} else {	//List形式
					for (Map<String, Object> mapInternal : list) {
						String[] childParamArray = parentAssociateElement.split(",");
						for (String childParam : childParamArray) {
							String value = String.valueOf(mapInternal.get(childParam));
							params.put(childParam, value);
						}
						String formatSql = formatSql(sql, params);
						List<Map<String, Object>> listInternal = reportDao.getResultBySql(formatSql);
						//给父数据集的每个实体插入一个list
						mapInternal.put(reportDataset, listInternal);
					}
				}
				
				/*
                // 固定列
                if (mapDynamic.size() == 0) {
                    for (Map<String, Object> mapInternal : list) {
                        String[] childParamArray = parentAssociateElement.split(",");
                        for (String childParam : childParamArray) {
                            String value = String.valueOf(mapInternal.get(childParam));
                            params.put(childParam, value);
                        }
                        String formatSql = formatSql(sql, params);
                        List<Map<String, Object>> listInternal = getResultBySql(formatSql);
                        // 给父数据集的每个实体插入一个list
                        mapInternal.put(reportDataset, listInternal);
                    }
                    // 动态模板列
                } else {
                    for (Map<String, Object> mapInternal : list) {
                        final List<Map<String, Object>> listModule = (List<Map<String, Object>>) mapDynamic.get("list");
                        // 实际列
                        String[] childParamArray = parentAssociateElement.split(",");
                        for (String childParam : childParamArray) {
                            String value = String.valueOf(mapInternal.get(childParam));
                            params.put(childParam, value);
                        }

                        String formatSql = formatSql(sql, params);
                        List<Map<String, Object>> listInternal = getResultBySql(formatSql);
                        // 给实际列补足到动态模板列
                        if (listInternal != null && listInternal.size() > 0) {
                            Map<String, Object> mapTemp = listInternal.get(0);
                            Map<String, Object> mapColumn = new HashMap<String, Object>();
                            mapColumn.putAll(mapTemp);
                            Set<String> setColumn = mapColumn.keySet();
                            setColumn.remove(mapDynamic.get("element"));
                            List<Map<String, Object>> listProd = new ArrayList<Map<String, Object>>();
                            // 把模板列复制到实际列，并用null填充
                            if (listModule != null) {
                                for (final Map<String, Object> mapModule : listModule) {
                                    Map<String, Object> mapProd = new HashMap<String, Object>();
                                    mapProd.put(String.valueOf(mapDynamic.get("element")),
                                            mapModule.get(mapDynamic.get("element")));
                                    for (String column : setColumn) {
                                        mapProd.put(column, null);
                                    }
                                    listProd.add(mapProd);
                                }
                            }
                            // 把真实数据填充到实际列
                            for (Map<String, Object> mapProd : listProd) {
                                for (Map<String, Object> mapInfo : listInternal) {
                                    if (String.valueOf(mapProd.get(mapDynamic.get("element"))).equals(
                                            String.valueOf(mapInfo.get(mapDynamic.get("element"))))) {
                                        for (String column : setColumn) {
                                            mapProd.put(column, mapInfo.get(column));
                                        }
                                    }
                                }
                            }
                            // 给父数据集的每个实体插入一个list
                            mapInternal.put(reportDataset, listProd);
                        }
                    }
                }*/
			}
		}

		//移除父数据集中是分sheet或者分page的数据集
		for (EhrReportConfig config : configList) {
			String parentReportDataset = config.getParentReportDataset();
			if (parentReportDataset == null || StringUtils.isEmpty(parentReportDataset.trim())) {
				String reportDataset = config.getReportDataset();
				//判断是否多sheet
				if (config.getIsMultiSheetColumn() == 1) {
					map.remove(reportDataset);
				}
				//判断是否多Page
				if (config.getIsMultiPageColumn() == 1) {
					map.remove(reportDataset);
				}
			}
		}

        List<Map<String,List<Map<String, Object>>>> mutilSheetList = new ArrayList<Map<String,List<Map<String, Object>>>>();
        List<List<Map<String, Object>>> mutilPageSheetList = new ArrayList<List<Map<String, Object>>>();
		List<String> sheetNameList = new ArrayList<String>();
		if (!StringUtils.isEmpty(multiBeanName)) {
			//处理分sheet数据
            Map<String, Map<String,List<Map<String, Object>>>> sheetdatas = new HashMap<String, Map<String,List<Map<String, Object>>>>();
            for(String datasetname : datasetDataMap.keySet()){
                for (Map<String, Object> data : datasetDataMap.get(datasetname)) {
                	//父数据集关联字段的每一个值对应一个sheet
                    Object sheetname = data.get(datasetRelationMap.get(datasetname));
                    if(sheetname == null){
                        sheetname = Constants.DEFAULT_SHEET_NAME;
                    }
                    String key = sheetname.toString();
                    if(sheetdatas.get(key) == null){
                        sheetdatas.put(key.toString(), new HashMap<String,List<Map<String, Object>>>());
                        sheetNameList.add(key.toString());
                    }
                    if(sheetdatas.get(key).get(datasetname) == null){
                        sheetdatas.get(key).put(datasetname, new ArrayList<Map<String, Object>>());
                    }
                    //可能有多个子表关联该字段的该值(比如companyId为8的用户和订单在一个sheet中显示)
                    sheetdatas.get(key).get(datasetname).add(data);
                }
            }
            for(String sheetname : sheetNameList){
                for(String datasetname : datasetDataMap.keySet()){
                    if(sheetdatas.get(sheetname).get(datasetname) == null){
                    	//没有数据的需要初始化空列表
                        sheetdatas.get(sheetname).put(datasetname, new ArrayList<Map<String, Object>>());
                    }
                }
                mutilSheetList.add(sheetdatas.get(sheetname));
            }
        } else if(!StringUtils.isEmpty(multiPageDataSet)){
        	//处理分页数据
            boolean flag = true;
            int num = 0;
            int sn = 1;
            List<Map<String, Object>> curSheet = null;
            while (flag) {
                flag = false;
                Map<String, Object> multiData = new HashMap<String, Object>();
                for(String datasetname : datasetDataMap.keySet()){
                    List<Map<String, Object>> data = datasetDataMap.get(datasetname);
                    //在新的一行中，只要有一个dataSet有值，则该行有效
                    flag = flag || num < data.size();
                    if (num < data.size()) {
                        //取原先若干个dataSet同一行的值，构成一行新的值
                        multiData.putAll(data.get(num));
                    }
                }
                if (flag) {
                    //如果该页记录已到达每页最大记录数，则新建分页
                    if (num % maxResultPerSheet == 0) {
                        curSheet = new ArrayList<Map<String, Object>>();
                        mutilPageSheetList.add(curSheet);
                        sn = 1;
                    }
                    if (!StringUtils.isEmpty(multiPageDataSet)) {
                        multiData.put("sn", sn);
                    }
                    curSheet.add(multiData);
                }
                
                num ++;
                sn ++;
            }
            
            //如果该页记录数没达到最大数，则补空行
            while (curSheet != null && curSheet.size() < maxResultPerSheet) {
                curSheet.add(new HashMap<String, Object>());
            }
        }
		
		Date now = new Date();
		String fileClass = "300";	//数据文件

		//生成数据文件
		XLSTransformer transformer = new XLSTransformer();
		//EhrFile tplFile = fileMapper.getByFileId(user.getCompanyId(), report.getFileId());
		EhrFile tplFile = fileMapper.selectByPrimaryKey(report.getFileId());
		String srcFilePath = tplFile.getFilePath();
		String destFileName = FileUtil.generateFileName(user.getUserCode(), tplFile.getFileName(), true);
		String destFilePath = FileUtil.confirmFullPath(user.getCompanyId().toString(), fileClass) + File.separator + destFileName;
		if (!StringUtils.isEmpty(multiBeanName)) {	//multi sheet
			InputStream is = new FileInputStream(srcFilePath);
			OutputStream os = new FileOutputStream(destFilePath);
			Workbook workbook = transformer.transformMultipleSheetsList(is, mutilSheetList, sheetNameList,
					multiBeanName, map, 0);
			workbook.write(os);
		} else {					//single sheet
			if (!StringUtils.isEmpty(multiPageDataSet)) {
				map.put(multiPageDataSet, mutilPageSheetList);
                map.put("allPage", mutilPageSheetList.size()); // 设置总页数
			}
			
			transformer.transformXLS(srcFilePath, map, destFilePath);
		}
		
		//保存生成的数据文件
		EhrFile dataFile = new EhrFile();
		dataFile.setCompanyId(user.getCompanyId());
		dataFile.setFileClass(Integer.valueOf(fileClass));
		dataFile.setFileName(report.getReportName() + ".xlsx");
		dataFile.setFilePath(destFilePath);
		dataFile.setLastModifyDate(now);
		dataFile.setLastModifyUserId(user.getUserId());
		dataFile.setLastModifyUserName(user.getUserName());
		dataFile.setCreateDate(now);
		dataFile.setCreateUserId(user.getUserId());
		dataFile.setCreateUserName(user.getUserName());
		fileMapper.insertSelective(dataFile);
		
		//返回结果并发送消息
        Map<String, Object> resultMap = MessageUtil.successMessage("生成成功");
        resultMap.put("reportName",report.getReportName());
        resultMap.put("DATE",JabavaUtil.parseDate(now, "yyyy年MM月dd日  HH:mm:ss"));
        resultMap.put("fileId", dataFile.getFileId());
        //发送消息
        //userNotificationService.send(user.getUserId(), "REPORT_FINISH", resultMap);
		return resultMap;
	}

	private String formatSql(String sql, Map<String, Object> params) throws Exception {
		JexlContext context = new MapContext(params);
		JexlEngine jexl = new JexlEngine();
		UnifiedJEXL ujexl = new UnifiedJEXL(jexl);
		UnifiedJEXL.Expression expr = ujexl.parse(sql);
		String result = expr.evaluate(context).toString();
		return result;
	}

}
