package com.jabava.service.individual.impl;

import java.io.InputStream;
import java.util.*;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.MapContext;
import com.jabava.common.exception.JabavaServiceException;
import com.jabava.dao.individual.IdvRosterBatchDetailMapper;
import com.jabava.dao.individual.IdvRosterBatchMapper;
import com.jabava.dao.individual.IdvSetTableMapper;
import com.jabava.pojo.individual.IdvRosterBatch;
import com.jabava.pojo.individual.IdvRosterBatchDetail;
import com.jabava.pojo.individual.IdvSetTable;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.individual.IdvRosterBatchService;
import com.jabava.utils.FileUtil;
import com.jabava.utils.employee.impl.PersonSecurityProfile;
import com.jabava.utils.individual.BPImport;
@Service
public class IdvRosterBatchServiceImpl implements IdvRosterBatchService {

	@Resource
	private IdvRosterBatchMapper idvRosterBatchMapper;
	
	@Resource
	private IdvRosterBatchDetailMapper ivRosterBatchDetailMapper;
	
	@Resource
	private IdvSetTableMapper idvSetTableMapper;
	
	
	@Override
	public int insertIdvRosterBatch(IdvRosterBatch idvRosterBatch) {
		// TODO Auto-generated method stub
		int result = idvRosterBatchMapper.insertSelective(idvRosterBatch);
		return result;
	}
	
	
	@Override
	public Map<String, Object> selectBatchInfoByCompanyIdAndType( Map<String, Object> map) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		List<IdvRosterBatch> list=idvRosterBatchMapper.selectBatchInfoByCompanyIdAndType(map);
		if(list!=null&&list.size()>0){
			data.put("rosterBatchList", list);
		}
		return data;
	}
	
	
	@Override
	public Map<String, Object> getRosterBatchDetail(Long rosterbatchId) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		List<Map<String, Object>> detailList= ivRosterBatchDetailMapper.selectRosterBatchDetail( rosterbatchId);
		
		if(detailList!=null&&detailList.size()>0){
			
			for(Map<String, Object> detail:detailList){
				
				data.put(detail.get("id_card").toString(), detail);
				
			}
		}
		
		
		return data;
	}


	@Override
	public Map<String, Object> importBDFiles(CommonsMultipartFile multipartFile,Long rosterbatchId,Integer type, EhrUser user) throws Exception {
		Workbook book = null;
		InputStream inputstream = multipartFile.getInputStream();
		if (".xlsx".equals(FileUtil.getExtension(multipartFile.getOriginalFilename()))) {
			book = new XSSFWorkbook(inputstream);
		} else if (".xls".equals(FileUtil.getExtension(multipartFile.getOriginalFilename()))) {
			book = new HSSFWorkbook(inputstream);
		} else {
			throw new JabavaServiceException("BP文件导入失败，错误：文件格式错误。");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		
		//根据批次id获取批次详情
		Map<String, Object> rosterBatchDetailMap= getRosterBatchDetail(rosterbatchId);
		
		//结果集
		List<Map<String, Object>> rosterDetailList=null;
		
		
		//读取EXCLE里的数据,返回BP名单里存在的人员具体信息的列表
		
		for (int num = 0; num < book.getNumberOfSheets(); num++) {
			Sheet sheet = book.getSheetAt(num);			 
			BPImport bpImport=new BPImport();
			rosterDetailList=bpImport.iterateBPFile(num, sheet, rosterBatchDetailMap, user);
		}
		
		List<Map<String, Object>> lastResult=handlerRosterDetailAndSetTable(rosterDetailList,type,user.getCompanyId());
		
		data.put("rosterDetailList", rosterDetailList);
		data.put("rosterDetailList", lastResult);
		 
		
		return data;
	}


	@Override
	public List<Map<String, Object>> handlerRosterDetailAndSetTable( List<Map<String, Object>> rosterBatchDetail,int type, Long companyId) {
		
		//结果集
		List<Map<String, Object>> resultList=new ArrayList<Map<String, Object>>();
		
		
		
		//查询公司下的套表列表
		 List<IdvSetTable> setTableList= idvSetTableMapper.selectSetTableByCompanyId(companyId);
		 
		 Map<String, Object> synthesisMap=null;//合成map
		 
		//遍历员工列表
		for(Map<String, Object> personMap:rosterBatchDetail){
			
			synthesisMap=new HashMap<String, Object>();
			
			synthesisMap.put("person", personMap);
			
			handlerSinglePersonSetTable(  synthesisMap, setTableList, personMap,  type);
			
			resultList.add(synthesisMap);
			
		}
		 
		 
		 
		return resultList;
	}
	
	/**
	 * 处理每个人的套表  一个人对应多条套表数据
	 * @param synthesisMap 结果集map
	 * @param setTableList 套表列表
	 * @param personMap 人员map
	 * @param type 批次类型-合同类型
	 * 
	 */
	private void handlerSinglePersonSetTable( Map<String, Object> synthesisMap,List<IdvSetTable> setTableList,Map<String, Object> personMap,int type){
		
		//添加表达式字段
		FelEngine fel = new FelEngineImpl();
		MapContext ctx = new MapContext();
		
		ctx.set("id", personMap.get("id"));
		ctx.set("roster_batch_id",  personMap.get("roster_batch_id"));
		ctx.set("id_card",  personMap.get("id_card"));
		ctx.set("employee_name",  personMap.get("employee_name"));
		ctx.set("sex",  personMap.get("sex"));
		ctx.set("contract_body",  personMap.get("contract_body"));
		ctx.set("sign_date",  personMap.get("sign_date"));
		ctx.set("first_party",  personMap.get("first_party"));
		ctx.set("second_party",  personMap.get("second_party"));
		ctx.set("representative",  personMap.get("representative"));
		
		ctx.set("residence",  personMap.get("residence"));
		ctx.set("contract_type",  personMap.get("contract_type"));
		ctx.set("contract_start_date",  personMap.get("contract_start_date"));
		ctx.set("contract_end_date",  personMap.get("contract_end_date"));
		ctx.set("probation_end_date",  personMap.get("probation_end_date"));
		
		ctx.set("work_post",  personMap.get("work_post"));
		ctx.set("work_place",  personMap.get("work_place"));
		ctx.set("work_type",  personMap.get("work_type"));
		ctx.set("first_party_stamp_time",  personMap.get("first_party_stamp_time"));
		ctx.set("second_party_stamp_time",  personMap.get("second_party_stamp_time"));
		ctx.set("need_secrecy_agreement",  personMap.get("need_secrecy_agreement"));//是否签订保密协议 1是  0否
		
		//遍历套表取文件名称，路径和份数
		for(IdvSetTable setTable:setTableList){
			
			
			int num=0;//份数
			
			if(type==1){	//入职 
				if(setTable.getExp1()!=null&&!setTable.getExp1().equals("")){
					Object o=fel.eval(setTable.getExp1(), ctx);
					if(o!=null&&!o.equals("")){
						num=(Integer)o;
					}
				}
			}else if(type==2){//续签 
				if(setTable.getExp2()!=null&&!setTable.getExp2().equals("")){
					Object o=fel.eval(setTable.getExp2(), ctx);
					if(o!=null&&!o.equals("")){
						num=(Integer)o;
					}
				}
				
			}else if(type==3){//换签 
				if(setTable.getExp3()!=null&&!setTable.getExp3().equals("")){
					Object o=fel.eval(setTable.getExp3(), ctx);
					if(o!=null&&!o.equals("")){
						num=(Integer)o;
					}
				}
				
			}
			
			synthesisMap.put(setTable.getFilePath(), num);
			
		}
		
		
		
	}
	
	public static void main(String[] args) {
		Long type=1L;
		
		Object o="";
//		System.out.println(o.equals(""));
//		
//		System.out.println((Integer)o);
//		
//		System.out.println(type==2);
		Object o1=new String("");
		Object o2=new String("");
		
		System.out.println(o1.equals(o2));
		
	}
	
	

}
