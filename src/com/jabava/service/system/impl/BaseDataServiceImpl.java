package com.jabava.service.system.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.manage.EhrBaseDataTypeMapper;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrBaseDataType;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.system.IBaseDataService;
import com.jabava.utils.FileUtil;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
/***
 * 
 * @author panfei
 *
 */
@Service("baseDataService")
public class BaseDataServiceImpl implements IBaseDataService {
	@Resource
	public EhrBaseDataMapper dataMapper;
	@Resource
	public EhrBaseDataTypeMapper ehrBaseDataTypeMapper;
	/**
	 * 修改前查询
	 */
	@Override
	public EhrBaseData selectByBaseDataKey(Long baseDataId) throws Exception {
		EhrBaseData data = null;
	try {
		if(baseDataId != null){
			data =  dataMapper.selectByPrimaryKey(baseDataId);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		return data;
	}
	/**
	 * 修改基础数据
	 */
	@Override
	public Map<String, Object> updateBaseData(EhrBaseData info){
		Map<String, Object> map = new HashMap<String, Object>();
		int count = dataMapper.checkCode(info);
		int count1 = dataMapper.checkName(info);
		if(count >0 && count1 > 0){
			map.put("success", false);
			map.put("msg", "基本数据编号和名称重复！");
			return map;
		}else if(count > 0){
			map.put("success", false);
			map.put("msg", "基本数据编号重复！");
			return map;
		}else if(count1 >0){
			map.put("success", false);
			map.put("msg", "基本数据名称重复！");
			return map;
		}
		try {
			dataMapper.updateByPrimaryKeySelective(info);
			map.put("success", true);
			map.put("msg", "修改基本数据成功！");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "修改基本数据失败！");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查询基础数据列表
	 */
	@Override
	public List<EhrBaseData> searchBaseData(Map<String,Object> map, String search,
			String order, String according, int isNumeric,Page<EhrBaseData> page) throws Exception {
		map.put("according", according);
		map.put("order", order);
		map.put("search", search);
		map.put("page", page);
		return  dataMapper.searchBaseDataPage(map);
	}

	/**
	 * 查询数据类型
	 */
	@Override
	public List<EhrBaseDataType> selectByBaseDataType(){
		
		return dataMapper.selectByBaseDataType();
		
	}
	/**
	 * 新增
	 */
	@Override
	public Map<String, Object> insertBaseData(EhrBaseData data)  {
		Map<String, Object> map = new HashMap<String, Object>();
		int count = dataMapper.checkCode(data);
		int count1 = dataMapper.checkName(data);
		if(count >0 && count1 > 0){
			map.put("success", false);
			map.put("msg", "基本数据编号和名称重复！");
			return map;
		}else if(count > 0){
			map.put("success", false);
			map.put("msg", "基本数据编号重复！");
			return map;
		}else if(count1 >0){
			map.put("success", false);
			map.put("msg", "基本数据名称重复！");
			return map;
		}
		try {
			dataMapper.insert(data);
			map.put("success", true);
			map.put("msg", "添加基础数据成功！");
		} catch (Exception e) {
			map.put("success", false);
			map.put("msg", "添加基础数据失败！");
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 删除
	 */
	@Override
	public boolean updateByIsvalid(Long baseDataId,Long isValid){
		boolean result = false;
		try {
			int code = dataMapper.updateByIsvalid(baseDataId,isValid);
			result = (1 == code);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	@Override
	public List<EhrBaseData> getByCompanyId(Long companyId) throws Exception {
		return dataMapper.getByCompanyId(companyId);
	}
	@Override
	public List<EhrBaseData> selectBaseData(Long companyId, int baseDataType,
			String baseDataName) throws Exception {
		return dataMapper.selectBaseData(companyId, baseDataType, baseDataName);
	}

	@Override
	public List<EhrBaseData> selectBaseData(Long companyId, int baseDataType) {
		return dataMapper.selectByType(companyId, baseDataType);
	}
	/**
	 * 解析文件
	 */
	@Override
	public Map<String, Object> resolveBaseData(
			CommonsMultipartFile multipartFile)throws Exception  {
		// TODO Auto-generated method stub
		Workbook book = null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> errorList = new ArrayList<String>();
        List<Sheet>sheet1 = new ArrayList<Sheet>();	
		InputStream inputStream = multipartFile.getInputStream();
		Sheet sheet=null;
		if(".xlsx".equals(FileUtil.getExtension(multipartFile.getOriginalFilename()))){
			book = new XSSFWorkbook(inputStream);
		}else if(".xls".equals(FileUtil.getExtension(multipartFile.getOriginalFilename()))){
			book = new HSSFWorkbook(inputStream);
		}else{
			throw new JabavaServiceException("基础数据导入失败，错误：文件格式错误");
		}
		for(int num = 0; num<book.getNumberOfSheets();num++){
			sheet = book.getSheetAt(num);
			Row frist = sheet.getRow(0);
			if(frist==null){
				errorList.add(sheet.getSheetName()+"sheet的格式错误");
				continue;
			}
			if(frist.getCell(0)!=null){
				if(!frist.getCell(0).toString().equals("基础数据编号")){
					errorList.add(sheet.getSheetName()+"的格式错误");
					continue;
				}
			}else{
				errorList.add(sheet.getSheetName()+"的格式错误");
				continue;
			}
			sheet1.add(sheet);	
		}
		map.put("errorList",errorList);
		map.put("sheet",sheet1);
		return map;
	}
		
	/**
	 * 批量读取文件，得到对象
	 */
	@Override
	public Map<String, Object> importBaseData(Map<String, Object> map, EhrUser ehrUser)
			throws Exception {
		// TODO Auto-generated method stub
		List<Sheet> sheet1 =  (List<Sheet>) map.get("sheet");
		List<String> errorList=null;
		if(map.containsKey("errorList")){
			errorList=(List<String>) map.get("errorList");
		}else{
			errorList=new ArrayList<String>();
		}
		List<EhrBaseData> baseDataList = new ArrayList<EhrBaseData>();
		EhrBaseData baseData=null;
		for(int x=0;x<sheet1.size();x++){
			if(sheet1.get(x)!=null){
				Sheet sheet=sheet1.get(x);
				for(int i=1; i<=sheet.getLastRowNum();i++){
					Row r = sheet.getRow(i);
					if(r==null){continue;}
					baseData = new EhrBaseData();
					int baseType = 0;
					EhrBaseDataType ehrBaseDataType = ehrBaseDataTypeMapper.selectBaseTypeByBaseName(sheet.getSheetName());
					if(ehrBaseDataType!=null){
						ehrBaseDataType.setBaseDataTypeName(sheet.getSheetName());
						baseType = ehrBaseDataType.getBaseDataType();
						baseData.setBaseDataType(baseType);
					}else{
						errorList.add("不存在"+sheet.getSheetName()+"这个基础类型");
						continue;
					}
					baseData.setBaseDataTypeName(sheet.getSheetName());
					baseData.setCompanyId(ehrUser.getCompanyId());
					baseData.setCreateDate(new Date());
					baseData.setCreateUserId(ehrUser.getUserId());
			 		baseData.setCreateUserName(ehrUser.getUserName());
			 		baseData.setIsValid(Byte.valueOf("1"));
			 		baseData.setLastModifyUserId(ehrUser.getUserId());
			 		baseData.setLastModifyUserName(ehrUser.getUserName());
			 		baseData.setLastModifyDate(new Date());
			 		boolean flag=true;
					for(int j=0; j<sheet.getRow(0).getLastCellNum();j++){
						if(j==0||j==1){
							if(r.getCell(j)!=null){
								if(r.getCell(j).toString().equals("")){
									errorList.add("基础数据取值失败，基础数据类型为"+sheet.getSheetName()+"的第"+(i+1)+"行，第"+(j+1)+"列数据为空");
									flag = false;
									continue;
								}
							}else{
								errorList.add("基础数据取值失败，基础数据类型为"+sheet.getSheetName()+"的第"+(i+1)+"行，第"+(j+1)+"列数据为空");
								flag = false;
								continue;
							}
						}	
						for(int z=0;z<baseDataList.size();z++){
						EhrBaseData	baseData1 =baseDataList.get(z);
							if(j==1){
								if(r.getCell(1).toString().equals(baseData1.getBaseDataName())&&baseType==baseData1.getBaseDataType()){
									errorList.add("基础数据取值失败，基础数据类型为"+sheet.getSheetName()+"的第"+(i+1)+"行，第"+(j+1)+"列数据为重复");
									flag = false;
									continue;
								}
							}
						}
						if(j==0){
							baseData.setBaseDataCode(r.getCell(j).toString());
						}else if(j==1){
							baseData.setBaseDataName(r.getCell(j).toString());
						}else if(j==2&&r.getCell(j)!=null){
							baseData.setMemo( r.getCell(j).toString());
						}
					}
					if(flag){
						baseDataList.add(baseData);
					}
				}
			}
		}
		map.put("baseDataList", baseDataList);
		map.put("errorList", errorList);
		return map;
	}
	/**
	 * 批量往数据库里插值
	 * @throws JabavaServiceException 
	 */
	public Map<String,Object> moreInsert(Map<String, Object>map) throws Exception {
		List<EhrBaseData>baseDataList=(List<EhrBaseData>) map.get("baseDataList");
		List<String> errorList=(List<String>) map.get("errorList");
		int sum=0;
		int sul=0;
		for(int i=0;i<baseDataList.size();i++){
			EhrBaseData baseData = baseDataList.get(i);
			int count = dataMapper.checkCode(baseData);
			int count1 = dataMapper.checkName(baseData);
			if(count1>0){
				errorList.add("基础数据类型为"+baseData.getBaseDataTypeName()+"名称为"+baseData.getBaseDataName()+"的数据与数据库数据名称重复导入失败");
				sul++;
			}else if(count>0){
				 dataMapper.updateByCode(baseData);
				 sum++;
			}else{
				dataMapper.insert(baseData);
				sum++;
			}
		}
		errorList.add("总共取到"+baseDataList.size()+"条数据，成功执行"+sum+"条，失败"+sul+"条");
		Map<String, Object> map2 = MessageUtil.successMessage("导入成功"); 
		map2.put("errorList", errorList);
		return map2;
	}
	@Override
	public List<EhrBaseDataType> selectBaseDateType() {
		// TODO Auto-generated method stub
		return ehrBaseDataTypeMapper.selectBaseDataType();
	}
	@Override
	public EhrBaseDataType selectByPrimaryKey(Integer baseDataType) {
		// TODO Auto-generated method stub
		EhrBaseDataType ehrBaseDataType = ehrBaseDataTypeMapper.selectByPrimaryKey(baseDataType);
		return ehrBaseDataType;
	}
	@Override
	public  Map<String, Object>  getBaseDataMap(Long companyId, int baseDataType, String baseDataName) {
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<EhrBaseData> baseDataList=dataMapper.selectBaseData(companyId, baseDataType, baseDataName);
		
		if(baseDataList!=null&&baseDataList.size()>0){
			for(EhrBaseData baseData:baseDataList){
				dataMap.put(baseData.getBaseDataCode(), baseData.getBaseDataName());
			}
			return dataMap;
		}
		
		return dataMap;
	}
}
