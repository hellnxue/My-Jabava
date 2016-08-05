package com.jabava.task;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.jabava.dao.hro.BdEmpBaseInfoMapper;
import com.jabava.dao.hro.BdEmpRecInfoMapper;
import com.jabava.dao.hro.order.HsEmpOrdMapper;
import com.jabava.dao.hro.order.HsEmpOrdNsbDetailMapper;
import com.jabava.dao.hro.order.HsEmpOrdNsbDetailRecMapper;
import com.jabava.dao.hro.order.HsEmpOrdRecMapper;
import com.jabava.dao.hro.order.HsEmpOrdSbDetailMapper;
import com.jabava.dao.hro.order.HsEmpOrdSbDetailRecMapper;
import com.jabava.pojo.hro.BdEmpBaseInfo;
import com.jabava.pojo.hro.BdEmpRecInfo;
import com.jabava.pojo.hro.HroPactInfo;
import com.jabava.pojo.hro.order.HsEmpOrd;
import com.jabava.pojo.hro.order.HsEmpOrdNsbDetail;
import com.jabava.pojo.hro.order.HsEmpOrdNsbDetailRec;
import com.jabava.pojo.hro.order.HsEmpOrdRec;
import com.jabava.pojo.hro.order.HsEmpOrdSbDetail;
import com.jabava.pojo.hro.order.HsEmpOrdSbDetailRec;
import com.jabava.pojo.hro.order.HsEmpOrdVO;
import com.jabava.service.hro.OutsourcingService;
import com.jabava.utils.HROFetchService;
import com.jabava.utils.HROFetchToken;
import com.jabava.core.config.JabavaPropertyCofigurer;
import com.jabava.utils.JabavaUtil;
import com.jabava.utils.enums.JabavaEnum.GjjStatus;
import com.jabava.utils.enums.JabavaEnum.IsDeleted;

/**
 * 导入订单信息
 *
 * @version $Id: ImportOrderTask.java, 
 * v 0.1 2016年1月20日 上午11:47:05 
 * <pre>
 * @author steven.chen
 * @date 2016年1月20日 上午11:47:05 
 * </pre>
 */
@Service
public class ImportOrderTask {


	@Resource
	private HsEmpOrdMapper  hsEmpOrdMapper;
	@Resource
	private HsEmpOrdRecMapper  hsEmpOrdRecMapper;
	@Resource
	private HsEmpOrdNsbDetailRecMapper  hsEmpOrdNsbDetailRecMapper;
	@Resource
	private HsEmpOrdSbDetailRecMapper  hsEmpOrdSbDetailRecMapper;
	@Resource
	private HsEmpOrdNsbDetailMapper  hsEmpOrdNsbDetailMapper;
	@Resource
	private HsEmpOrdSbDetailMapper  hsEmpOrdSbDetailMapper;
	@Resource
	private BdEmpBaseInfoMapper  bdEmpBaseInfoMapper;
	@Resource
	private BdEmpRecInfoMapper  bdEmpRecInfoMapper;
	@Autowired
	private OutsourcingService outsourcingService;
	
	private HROFetchService requestService;
	public ImportOrderTask(){
		String server = JabavaPropertyCofigurer.getProperty("SERVER_URL");//"http://neice.ezhiyang.com";
		HROFetchToken fetchToken = new HROFetchToken(
				server + "/open/authorize", JabavaPropertyCofigurer.getProperty("CLIENT_ID"),
				JabavaPropertyCofigurer.getProperty("CLIENT_SECRET"));
		requestService = new HROFetchService(server	+ "/open/rest", fetchToken);
	}
	
	private static final Logger logger = Logger.getLogger(ImportOrderTask.class);
	// String server="http://king.ezhiyang.com";
	SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat format2=new SimpleDateFormat("yyyy-MM-dd");

	public void execute() throws Exception {
		logger.info(" ImportOrderTask  Start .................");
		List<HroPactInfo> hroPactInfoList= outsourcingService.queryPactInfoList();
		if(hroPactInfoList == null ||hroPactInfoList.size()==0){
			logger.info(" 没有可以执行的协议号 ...");
			return ;			
		}		
		//需要更新isLast 为空的 orderId
		List<Long> isLastList = new ArrayList<Long>();
		List<Long> sbList = new ArrayList<Long>();		
		List<Long> nsbList = new ArrayList<Long>();
		//社保非社保月度表 更新状态用的参数
		HsEmpOrdVO hsEmpOrdVO = new HsEmpOrdVO();	
		//当前时间
		Date now = new Date();
		//员工基本信息
		List<BdEmpBaseInfo> hdEmpBaseInfoList = new ArrayList<BdEmpBaseInfo>();	
		//员工信息详情
		List<BdEmpRecInfo> bdEmpRecInfoList = new ArrayList<BdEmpRecInfo>();	
		//订单
		List<HsEmpOrd> hsEmpOrdList = new ArrayList<HsEmpOrd>();	
		//订单详情 
		List<HsEmpOrdRec> hsEmpOrdRecList = new ArrayList<HsEmpOrdRec>();
		//订单非社保月度表 
		List<HsEmpOrdNsbDetail> hsEmpOrdNsbDetailList = new ArrayList<HsEmpOrdNsbDetail>();
		//订单社保月度表 
		List<HsEmpOrdSbDetail> hsEmpOrdSbDetailList = new ArrayList<HsEmpOrdSbDetail>();
		JSONArray jsonList = null;
		Map<String, Object> result =null ;
		Map<String, Object> sbResult =null ;
		Map<String, Object> nsbResult =null ;		
		JSONArray sbJsonList = null;	
		JSONArray nsbJsonList = null;		
		 Long orderId = null;
		 int orderSum =0;//订单数量统计
		 int sbSum=0;//社保月度表数量统计
		 int nsbSum=0;//非社保月度表数量统计
		for(HroPactInfo hroPactInfo:hroPactInfoList){
			if(result!=null ){
				result.clear();
			}
			if(jsonList!=null){
				jsonList.clear();
			}	
			
			Map<String, Object> parameter = new HashMap<String, Object>();
			/*Long orderTotal  = hsEmpOrdMapper.getOrderTotalByProcotolCode(hroPactInfo.getPactCode());
			if(orderTotal>0){//增加时间条件只获取最近两个月的订单
				SimpleDateFormat format3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//上个月第一天 00：00：00
				Date startDate = JabavaDateUtils.previousMonth();
				//本月最后一天 23：59：59
				Date endDate =JabavaDateUtils.lastDayOfMonth();
				parameter.put("START_DATE", format3.format(startDate));
				parameter.put("END_DATE",format3.format(endDate));
			}*/
			Map<String, Object> data = new HashMap<String, Object>();	
			Map<String, Object> page = new HashMap<String, Object>();
			page.put("pageSize", 99999);
			data.put("page",page);		
			parameter.put("PROTOCOL_CODE", hroPactInfo.getPactCode());
			parameter.put("code", "queryorderbyprocotolcode");
			data.put("parameter", parameter);	
			try{
				result = requestService.invoke("SqlQuery", data);	
			}catch (Exception e){
				e.printStackTrace();
				logger.info(" 获取订单数据出错 ************************* 协议号 " +hroPactInfo.getPactCode());
				System.out.println(" 获取订单数据出错 ************************* 协议号 " +hroPactInfo.getPactCode());
				
			}				
			jsonList = TaskUtil.getJSONArray(result);
			if(jsonList!=null && jsonList.size()>0){
				orderSum+= jsonList.size();//订单数量				
				for(int i=0;i<jsonList.size();i++){
					//如果订单数量超过一千条，insert 一次
					if(hsEmpOrdList!=null && hsEmpOrdList.size()>1000){	
						try{
						//导入订单数据
						importOrder(hsEmpOrdList, isLastList,hsEmpOrdRecList,hdEmpBaseInfoList, bdEmpRecInfoList);
						}catch (Exception e){
							e.printStackTrace();
							logger.info(" 导入订单数据出错 ************************* 协议号 " +hroPactInfo.getPactCode());
							System.out.println(" 导入订单数据出错  获取出错************************* 协议号 " +hroPactInfo.getPactCode());
							
						}
					}			
					com.alibaba.fastjson.JSONObject json =   (com.alibaba.fastjson.JSONObject) jsonList.get(i);	
					if(json!=null && json.size()>0){
						orderId = null;
						json.put("companyId", hroPactInfo.getCompanyId());
						json.put("procotolCode", hroPactInfo.getPactCode());
						//订单
						hsEmpOrdList.add(getHsEmpOrd(json));
						//订单详情
						hsEmpOrdRecList.add(getHsEmpOrdRec(json));	
						//员工基本信息
						hdEmpBaseInfoList.add(getHdEmpBaseInfo(json));
						//员工详细信息
						bdEmpRecInfoList.add(getHdEmpRecInfo(json));
						if(StringUtils.isNotBlank(json.getString("ORDER_ID"))){
							orderId = Long.parseLong(json.getString("ORDER_ID").toString());
							isLastList.add(orderId);							
						}						
						if(sbResult!=null){
							sbResult.clear();
						}
						if(sbJsonList!=null ){
							sbJsonList.clear();
						}
						
						// 社保月度表
						Map<String, Object> parameter5 = new HashMap<String, Object>();
						Map<String, Object> data5 = new HashMap<String, Object>();		
						parameter5.put("order_id", orderId);	
						parameter5.put("code", "querysbdetailbyorderid");							
						data5.put("page",page);
						data5.put("parameter", parameter5);	
						try{							
							if(hsEmpOrdSbDetailList!=null && hsEmpOrdSbDetailList.size()>0){//每个order_id 下面的数据insert 一次	
								try{
								//导入社保月度表数据
								importSbDetail(hsEmpOrdSbDetailList, hsEmpOrdVO,sbList,now);
								}catch (Exception e){
									e.printStackTrace();
									logger.info("导入社保月度表数据出错 ************************* 订单ID " +hsEmpOrdSbDetailList.get(0).getOrderId());
									System.out.println(" 导入社保月度表数据出错************************* 订单ID " +hsEmpOrdSbDetailList.get(0).getOrderId());
							
								}
							}
						sbResult = requestService.invoke("SqlQuery", data5);
						}catch (Exception e){
							e.printStackTrace();
							logger.info(" 社保月度表  获取出错************************* 订单ID "+orderId);
							System.out.println(" 社保月度表  获取出错************************* 订单ID "+orderId);							
						}
						sbJsonList = TaskUtil.getJSONArray(sbResult);
						if(sbJsonList!=null && sbJsonList.size()>0){							
							System.out.println("该订单 "+orderId+"社保月度表 数量  ************************* "+sbJsonList.size());			
							for(int a=0;a<sbJsonList.size();a++){
								com.alibaba.fastjson.JSONObject json5 =   (com.alibaba.fastjson.JSONObject) sbJsonList.get(a);	
								if(json5!=null && json5.size()>0){
									if(StringUtils.isBlank(json5.getString("ID"))){
										continue;
									}else{
										json5.put("companyId", hroPactInfo.getCompanyId());
										hsEmpOrdSbDetailList.add(getHsEmpOrdSbDetail(json5));
										sbList.add(Long.parseLong(json5.getString("ID").toString()));
										sbSum++;//社保数量	
									}
									
									
								}				

							}
						}
						if(nsbResult!=null){
							nsbResult.clear();
						}
						if(nsbJsonList!=null){
							nsbJsonList.clear();
						}
						// 非社保月度表						
						Map<String, Object> parameter4 = new HashMap<String, Object>();
						Map<String, Object> data4 = new HashMap<String, Object>();		
						parameter4.put("order_id", orderId);	
						parameter4.put("code", "querynsbdetailbyorderid");							
						data4.put("page",page);
						data4.put("parameter", parameter4);	
						try{
					
						//每个order_id 下面的数据insert 一次
						if(hsEmpOrdNsbDetailList!=null && hsEmpOrdNsbDetailList.size()>0){
							try{
							//导入非社保月度表数据
							importNsbDetail(hsEmpOrdNsbDetailList,hsEmpOrdVO,nsbList,now);
							}catch (Exception e){
								e.printStackTrace();
								logger.info("导入非社保月度表数据出错 ************************* 订单ID " +hsEmpOrdNsbDetailList.get(0).getOrderId());
								System.out.println(" 导入非社保月度表数据出错************************* 订单ID " +hsEmpOrdNsbDetailList.get(0).getOrderId());
								
							}
						}
						nsbResult = requestService.invoke("SqlQuery", data4);
						}catch (Exception e){
							e.printStackTrace();
							System.out.println(" 非社保月度表  获取出错 ************************* 订单ID"+ orderId);
							
						}
						nsbJsonList = TaskUtil.getJSONArray(nsbResult);
						if(nsbJsonList!=null && nsbJsonList.size()>0){							
							System.out.println("该订单 " +orderId+" 非社保数量  ************************* "+nsbJsonList.size());
							for(int b=0;b<nsbJsonList.size();b++){
								com.alibaba.fastjson.JSONObject json4 =   (com.alibaba.fastjson.JSONObject) nsbJsonList.get(b);	
								if(json4!=null && json4.size()>0){
									if(StringUtils.isBlank(json4.getString("ID"))){
										continue;
									}else{
										json4.put("companyId",  hroPactInfo.getCompanyId());
										hsEmpOrdNsbDetailList.add(getHsEmpOrdNsbDetail(json4));	
										nsbList.add(Long.parseLong(json4.getString("ID").toString()));
										nsbSum++;//非社保数量
									}
									
								}				

							}
						}
						
					}				

				}
				
			}	
		}
		if (hsEmpOrdList != null && hsEmpOrdList.size() > 0) {
			//导入订单数据
			importOrder(hsEmpOrdList, isLastList,hsEmpOrdRecList,hdEmpBaseInfoList, bdEmpRecInfoList);
			
		}else{
			logger.info("没有获取到订单");
			return ;
		}
		
		if(hsEmpOrdSbDetailList!=null && hsEmpOrdSbDetailList.size()>0){	
			//导入社保月度表数据
			importSbDetail(hsEmpOrdSbDetailList, hsEmpOrdVO,sbList,now);			
		}		
		if(hsEmpOrdNsbDetailList!=null && hsEmpOrdNsbDetailList.size()>0){	
			importNsbDetail(hsEmpOrdNsbDetailList,hsEmpOrdVO,nsbList,now);
		}
		logger.info(" 共导入订单................."+orderSum);
		logger.info(" 共导入社保月度数据................."+orderSum);
		logger.info(" 共导入非社保月度数据................."+orderSum);
		System.out.println(" 订单数量  ************************* "+orderSum);
		System.out.println(" 社保数量  ************************* "+sbSum);
		System.out.println(" 非社保数量  ************************* "+nsbSum);
		logger.info(" ImportOrderTask  End .................");
		
	}
	/**
	 * 导入订单数据
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月21日 下午3:10:31 
	 * </pre>
	 *
	 * @param hsEmpOrdList
	 * @param isLastList
	 * @param hsEmpOrdRecList
	 * @param hdEmpBaseInfoList
	 * @param bdEmpRecInfoList
	 */
	private void importOrder(List<HsEmpOrd> hsEmpOrdList,List<Long> isLastList,List<HsEmpOrdRec> hsEmpOrdRecList,
			List<BdEmpBaseInfo> hdEmpBaseInfoList,List<BdEmpRecInfo>  bdEmpRecInfoList){
		// 订单		
		int res = hsEmpOrdMapper.insertOrUpdateList(hsEmpOrdList);
		logger.info("新增或者更新订单数     " + res + " listSize "
				+ hsEmpOrdList.size());		
		//更新订单详情的isLast 字段为Null 
		hsEmpOrdRecMapper.updateIsLast(isLastList);			
		// 订单详情			
		int recRes = hsEmpOrdRecMapper.insertOrUpdateList(hsEmpOrdRecList);
		logger.info("新增或者更新订单详情    " + recRes + " listSize "
				+ hsEmpOrdRecList.size());		
		// 个人基本信息  
		int infoRes = bdEmpBaseInfoMapper
				.insertOrUpdateList(hdEmpBaseInfoList);
		logger.info("新增或者更新个人基本信息    " + infoRes + " listSize "
				+ hdEmpBaseInfoList.size());		
		// 个人详细信息
		int recInfoRes = bdEmpRecInfoMapper
				.insertOrUpdateList(bdEmpRecInfoList);
		logger.info("新增或者更新个人详细信息    " + recInfoRes + " listSize "
				+ bdEmpRecInfoList.size());
		hsEmpOrdList.clear();
		isLastList.clear();
		hsEmpOrdRecList.clear();
		hdEmpBaseInfoList.clear();
		bdEmpRecInfoList.clear();
	}

	/**
	 * 导入社保月度表数据
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月21日 下午1:49:33 
	 * </pre>
	 *
	 * @param hsEmpOrdSbDetailList
	 * @param hsEmpOrdVO
	 * @param sbList
	 * @param now
	 */
	private void importSbDetail(List<HsEmpOrdSbDetail> hsEmpOrdSbDetailList,HsEmpOrdVO hsEmpOrdVO,List<Long> sbList,Date now ){
		hsEmpOrdVO.setNowDate(now);
		hsEmpOrdVO.setOrderId(hsEmpOrdSbDetailList.get(0).getOrderId());
		hsEmpOrdVO.setIdList(sbList);
		int sbdeteil = hsEmpOrdSbDetailMapper.insertOrUpdateList(hsEmpOrdSbDetailList);
		logger.info("sbdeteil 社保月度表 "+ sbdeteil +"hsEmpOrdSbDetailList  size  "+hsEmpOrdSbDetailList.size());	
		//更新订单月度表的isDeleted 字段为 1
		int nsbIsdeleted = hsEmpOrdSbDetailMapper.updateIsDeleted(hsEmpOrdVO);		
		logger.info("nsbIsdeleted 更新社保 月度表状态数量"+ nsbIsdeleted );	
		hsEmpOrdSbDetailList.clear();//清空list
		sbList.clear();
		hsEmpOrdVO.setOrderId(null);//清空orderId
		hsEmpOrdVO.setIdList(null);
		hsEmpOrdVO.setNowDate(null);
	}
	/**
	 * 导入非社保月度表数据
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月21日 下午1:50:43 
	 * </pre>
	 *
	 * @param hsEmpOrdNsbDetailList
	 * @param hsEmpOrdVO
	 * @param nsbList
	 * @param now
	 */
	private void importNsbDetail(List<HsEmpOrdNsbDetail> hsEmpOrdNsbDetailList,HsEmpOrdVO hsEmpOrdVO,List<Long> nsbList,Date now ){
		hsEmpOrdVO.setNowDate(now);
		hsEmpOrdVO.setOrderId(hsEmpOrdNsbDetailList.get(0).getOrderId());
		hsEmpOrdVO.setIdList(nsbList);
		int nsbdeteil = hsEmpOrdNsbDetailMapper.insertOrUpdateList(hsEmpOrdNsbDetailList);
		logger.info("nsbdeteil 非社保 月度表"+ nsbdeteil +"hsEmpOrdNsbDetailList  size  "+hsEmpOrdNsbDetailList.size());	
		//更新订单非社保月度表的isDeleted 字段为 1
		int nsbIsdeleted = hsEmpOrdNsbDetailMapper.updateIsDeleted(hsEmpOrdVO);		
		logger.info("nsbIsdeleted 更新非 社保 月度表状态数量"+ nsbIsdeleted );	
		hsEmpOrdNsbDetailList.clear();//清空list
		nsbList.clear();
		hsEmpOrdVO.setOrderId(null);//清空orderId
		hsEmpOrdVO.setIdList(null);
		hsEmpOrdVO.setNowDate(null);
	}
	//组装社保月度表
	private HsEmpOrdSbDetail getHsEmpOrdSbDetail(com.alibaba.fastjson.JSONObject json){
		HsEmpOrdSbDetail hsEmpOrdSbDetail  = new HsEmpOrdSbDetail();		
		hsEmpOrdSbDetail.setCompanyId(Long.parseLong(json.getString("companyId").toString()));
		if(StringUtils.isNotBlank(json.getString("ID"))){
			hsEmpOrdSbDetail.setOrdSbDetailId(Long.parseLong(json.getString("ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_ID"))){
			hsEmpOrdSbDetail.setOrderId(Long.parseLong(json.getString("ORDER_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PROD_NAME"))){
			hsEmpOrdSbDetail.setProdName(json.getString("PROD_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("ITEM_ID"))){
			hsEmpOrdSbDetail.setItemId(Long.parseLong(json.getString("ITEM_ID").toString()));
		}		
		if(StringUtils.isNotBlank(json.getString("SB_GROUP_ID"))){
			hsEmpOrdSbDetail.setSbGroupId(Long.parseLong(json.getString("SB_GROUP_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_GROUP_ID"))){
			hsEmpOrdSbDetail.setPolicyGroupId(Long.parseLong(json.getString("POLICY_GROUP_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("POLICY_CITY_CODE"))){
			hsEmpOrdSbDetail.setPolicyCityCode(json.getString("POLICY_CITY_CODE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("ITEM_TYPE"))){
			hsEmpOrdSbDetail.setItemType(json.getString("ITEM_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("PAYMENT_MONTH"))){
			hsEmpOrdSbDetail.setPaymentMonth(json.getString("PAYMENT_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BILL_MONTH"))){
			hsEmpOrdSbDetail.setBillMonth(json.getString("BILL_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_BASE"))){
			hsEmpOrdSbDetail.setCompanyBase(new BigDecimal(Double.parseDouble(json.getString("COMPANY_BASE").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_BASE"))){
			hsEmpOrdSbDetail.setIndividualBase(new BigDecimal(Double.parseDouble(json.getString("INDIVIDUAL_BASE").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_RATIO"))){
			hsEmpOrdSbDetail.setCompanyRatio(new BigDecimal(Double.parseDouble(json.getString("COMPANY_RATIO").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_RATIO"))){
			hsEmpOrdSbDetail.setIndividualRatio(new BigDecimal(Double.parseDouble(json.getString("INDIVIDUAL_RATIO").toString())));
		}		
		if(StringUtils.isNotBlank(json.getString("COMPANY_SUM"))){
			hsEmpOrdSbDetail.setCompanySum(new BigDecimal(Double.parseDouble(json.getString("COMPANY_SUM").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_SUM"))){
			hsEmpOrdSbDetail.setIndividualSum(new BigDecimal(Double.parseDouble(json.getString("INDIVIDUAL_SUM").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("PAY_SUM"))){
			hsEmpOrdSbDetail.setPaySum(new BigDecimal(Double.parseDouble(json.getString("PAY_SUM").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_APPEND"))){
			hsEmpOrdSbDetail.setCompanyAppend(new BigDecimal(Double.parseDouble(json.getString("COMPANY_APPEND").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_APPEND"))){
			hsEmpOrdSbDetail.setIndividualAppend(new BigDecimal(Double.parseDouble(json.getString("INDIVIDUAL_APPEND").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_BILL_TEMPLATE_ID"))){
			hsEmpOrdSbDetail.setCompanyBillTemplateId(Long.parseLong(json.getString("COMPANY_BILL_TEMPLATE_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_BILL_TEMPLATE_ID"))){
			hsEmpOrdSbDetail.setIndividualBillTemplateId(Long.parseLong(json.getString("INDIVIDUAL_BILL_TEMPLATE_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_DT"))){
			try {
				hsEmpOrdSbDetail.setCreateDate(JabavaUtil.formatDate(json.getString("CREATE_DT").toString(),"yyyyMMddHHmmss"));
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_BY"))){
			hsEmpOrdSbDetail.setCreateUserId(Long.parseLong(json.getString("CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_BY"))){
			hsEmpOrdSbDetail.setUpdateUserId(Long.parseLong(json.getString("UPDATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_DT"))){
			try {
				hsEmpOrdSbDetail.setUpdateDate(JabavaUtil.formatDate(json.getString("UPDATE_DT").toString(),"yyyyMMddHHmmss"));
			} catch (Exception e) {
				logger.error("", e);
			}
		}		
		if(StringUtils.isNotBlank(json.getString("IS_DELETED"))){
			hsEmpOrdSbDetail.setIsDeleted(Integer.parseInt(json.getString("IS_DELETED").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_RATIO_ID"))){
			hsEmpOrdSbDetail.setCompanyRatioId(Long.parseLong(json.getString("COMPANY_RATIO_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_RATIO_ID"))){
			hsEmpOrdSbDetail.setIndividualRatioId(Long.parseLong(json.getString("INDIVIDUAL_RATIO_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("HAS_CHANGED"))){
			hsEmpOrdSbDetail.setHasChanged(Integer.parseInt(json.getString("HAS_CHANGED").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("AUTO_ADD"))){
			hsEmpOrdSbDetail.setAutoAdd(Integer.parseInt(json.getString("AUTO_ADD").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("SB_MONTH"))){
			hsEmpOrdSbDetail.setSbMonth(json.getString("SB_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("FREQUENCY"))){
			hsEmpOrdSbDetail.setFrequency(json.getString("FREQUENCY").toString());
		}
		if(StringUtils.isNotBlank(json.getString("IF_PREPAY"))){
			hsEmpOrdSbDetail.setIfPrepay(Integer.parseInt(json.getString("IF_PREPAY").toString()));
		}
		hsEmpOrdSbDetail.setImportDate(new Date());
		return hsEmpOrdSbDetail;
	}
	//组装非社保月度表	
	private HsEmpOrdNsbDetail getHsEmpOrdNsbDetail(com.alibaba.fastjson.JSONObject json){

		HsEmpOrdNsbDetail hsEmpOrdNsbDetail  = new HsEmpOrdNsbDetail();		
		hsEmpOrdNsbDetail.setCompanyId(Long.parseLong(json.getString("companyId").toString()));
		if(StringUtils.isNotBlank(json.getString("ID"))){
			hsEmpOrdNsbDetail.setOrdNsbDetailId(Long.parseLong(json.getString("ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_ID"))){
			hsEmpOrdNsbDetail.setOrderId(Long.parseLong(json.getString("ORDER_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("OFFER_ID"))){
			hsEmpOrdNsbDetail.setOfferId(Long.parseLong(json.getString("OFFER_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("SUMMARY"))){
			hsEmpOrdNsbDetail.setQuotationName(json.getString("SUMMARY").toString());
		}
		if(StringUtils.isNotBlank(json.getString("ITEM_ID"))){
			hsEmpOrdNsbDetail.setItemId(Long.parseLong(json.getString("ITEM_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ITEM_TYPE"))){
			hsEmpOrdNsbDetail.setItemType(json.getString("ITEM_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("PROD_NAME"))){
			hsEmpOrdNsbDetail.setProdName(json.getString("PROD_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("PAYMENT_MONTH"))){
			hsEmpOrdNsbDetail.setPaymentMonth(json.getString("PAYMENT_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BILL_MONTH"))){
			hsEmpOrdNsbDetail.setBillMonth(json.getString("BILL_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("SUM"))){
			hsEmpOrdNsbDetail.setSum(new BigDecimal(Double.parseDouble(json.getString("SUM").toString())));
		}
	
		if(StringUtils.isNotBlank(json.getString("BILL_TEMPLATE_ID"))){
			hsEmpOrdNsbDetail.setBillTemplateId(Long.parseLong(json.getString("BILL_TEMPLATE_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_DT"))){
			try {
				hsEmpOrdNsbDetail.setCreateDate(JabavaUtil.formatDate(json.getString("CREATE_DT").toString(),"yyyyMMddHHmmss"));
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_BY"))){
			hsEmpOrdNsbDetail.setCreateUserId(Long.parseLong(json.getString("CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_BY"))){
			hsEmpOrdNsbDetail.setUpdateUserId(Long.parseLong(json.getString("UPDATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_DT"))){
			try {
				hsEmpOrdNsbDetail.setUpdateDate(JabavaUtil.formatDate(json.getString("UPDATE_DT").toString(),"yyyyMMddHHmmss"));
			} catch (Exception e) {
				logger.error("", e);
			}
		}		
		if(StringUtils.isNotBlank(json.getString("IS_DELETED"))){
			hsEmpOrdNsbDetail.setIsDeleted(Integer.parseInt(json.getString("IS_DELETED").toString()));
		}
	
		if(StringUtils.isNotBlank(json.getString("HAS_CHANGED"))){
			hsEmpOrdNsbDetail.setHasChanged(Integer.parseInt(json.getString("HAS_CHANGED").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("AUTO_ADD"))){
			hsEmpOrdNsbDetail.setAutoAdd(Integer.parseInt(json.getString("AUTO_ADD").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("EMP_RELATIVE_ID"))){
			hsEmpOrdNsbDetail.setEmpRelativeId(Long.parseLong(json.getString("EMP_RELATIVE_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("FREQUENCY"))){
			hsEmpOrdNsbDetail.setFrequency(json.getString("FREQUENCY").toString());
		}
		if(StringUtils.isNotBlank(json.getString("IF_PREPAY"))){
			hsEmpOrdNsbDetail.setIfPrepay(Integer.parseInt(json.getString("IF_PREPAY").toString()));
		}
		hsEmpOrdNsbDetail.setImportDate(new Date());
		return hsEmpOrdNsbDetail;
	
	}
	//组装非社保段表
	@SuppressWarnings("unused")
	private HsEmpOrdNsbDetailRec getHsEmpOrdNsbDetailRec(com.alibaba.fastjson.JSONObject json){
		HsEmpOrdNsbDetailRec hsEmpOrdNsbDetailRec  = new HsEmpOrdNsbDetailRec();		
		hsEmpOrdNsbDetailRec.setCompanyId(Long.parseLong(json.getString("companyId").toString()));
		if(StringUtils.isNotBlank(json.getString("ID"))){
			hsEmpOrdNsbDetailRec.setOrdNsbRecId(Long.parseLong(json.getString("ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_RECORD_ID"))){
			hsEmpOrdNsbDetailRec.setOrderRecordId(Long.parseLong(json.getString("ORDER_RECORD_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("OFFER_ID"))){
			hsEmpOrdNsbDetailRec.setOfferId(Long.parseLong(json.getString("OFFER_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("PROD_NAME"))){
			hsEmpOrdNsbDetailRec.setProdName(json.getString("PROD_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("SUMMARY"))){
			hsEmpOrdNsbDetailRec.setQuotationName(json.getString("SUMMARY").toString());
		}
		if(StringUtils.isNotBlank(json.getString("ITEM_ID"))){
			hsEmpOrdNsbDetailRec.setItemId(Long.parseLong(json.getString("ITEM_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ITEM_TYPE"))){
			hsEmpOrdNsbDetailRec.setItemType(json.getString("ITEM_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("PAYMENT_START_MONTH"))){
			hsEmpOrdNsbDetailRec.setPaymentStartMonth(json.getString("PAYMENT_START_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("PAYMENT_SATRT_DAY"))){
			hsEmpOrdNsbDetailRec.setPaymentSatrtDay(json.getString("PAYMENT_SATRT_DAY").toString());
		}
		if(StringUtils.isNotBlank(json.getString("PAYMENT_END_MONTH"))){
			hsEmpOrdNsbDetailRec.setPaymentEndMonth(json.getString("PAYMENT_END_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("PAYMENT_END_DAY"))){
			hsEmpOrdNsbDetailRec.setPaymentEndDay(json.getString("PAYMENT_END_DAY").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BILL_START_MONTH"))){
			hsEmpOrdNsbDetailRec.setBillStartMonth(json.getString("BILL_START_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BILL_END_MONTH"))){
			hsEmpOrdNsbDetailRec.setBillEndMonth(json.getString("BILL_END_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("SUM"))){
			hsEmpOrdNsbDetailRec.setSum(new BigDecimal(Double.parseDouble(json.getString("SUM").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_INDEX"))){
			hsEmpOrdNsbDetailRec.setOrderIndex(Integer.parseInt(json.getString("ORDER_INDEX").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("BILL_TEMPLATE_ID"))){
			hsEmpOrdNsbDetailRec.setBillTemplateId(Long.parseLong(json.getString("BILL_TEMPLATE_ID").toString()));
		}		
		if(StringUtils.isNotBlank(json.getString("CREATE_BY"))){
			hsEmpOrdNsbDetailRec.setCreateUserId(Long.parseLong(json.getString("CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_DT"))){
			try {
				hsEmpOrdNsbDetailRec.setCreateDate(JabavaUtil.formatDate(json.getString("CREATE_DT").toString(),"yyyyMMddHHmmss"));
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_BY"))){
			hsEmpOrdNsbDetailRec.setUpdateUserId(Long.parseLong(json.getString("UPDATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_DT"))){
			try {
				hsEmpOrdNsbDetailRec.setUpdateDate(JabavaUtil.formatDate(json.getString("UPDATE_DT").toString(),"yyyyMMddHHmmss"));
			} catch (Exception e) {
				logger.error("", e);
			}
		}		
		if(StringUtils.isNotBlank(json.getString("IS_DELETED"))){
			hsEmpOrdNsbDetailRec.setIsDeleted(Integer.parseInt(json.getString("IS_DELETED").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("HAS_CHANGED"))){
			hsEmpOrdNsbDetailRec.setHasChanged(Integer.parseInt(json.getString("HAS_CHANGED").toString()));
		}
		return hsEmpOrdNsbDetailRec;
	}
	//组装社保段表
	@SuppressWarnings("unused")
	private HsEmpOrdSbDetailRec getHsEmpOrdSbDetailRec(com.alibaba.fastjson.JSONObject json){
		HsEmpOrdSbDetailRec hsEmpOrdSbDetailRec  = new HsEmpOrdSbDetailRec();		
		hsEmpOrdSbDetailRec.setCompanyId(Long.parseLong(json.getString("companyId").toString()));
		if(StringUtils.isNotBlank(json.getString("ID"))){
			hsEmpOrdSbDetailRec.setOrdSbRecId(Long.parseLong(json.getString("ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_RECORD_ID"))){
			hsEmpOrdSbDetailRec.setOrderRecordId(Long.parseLong(json.getString("ORDER_RECORD_ID").toString()));
		}	
		
		if(StringUtils.isNotBlank(json.getString("PROD_NAME"))){
			hsEmpOrdSbDetailRec.setProdName(json.getString("PROD_NAME").toString());
		}
		
		if(StringUtils.isNotBlank(json.getString("ITEM_ID"))){
			hsEmpOrdSbDetailRec.setItemId(Long.parseLong(json.getString("ITEM_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_BASE"))){
			hsEmpOrdSbDetailRec.setCompanyBase(new BigDecimal(Double.parseDouble(json.getString("COMPANY_BASE").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_BASE"))){
			hsEmpOrdSbDetailRec.setIndividualBase(new BigDecimal(Double.parseDouble(json.getString("INDIVIDUAL_BASE").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_RATIO"))){
			hsEmpOrdSbDetailRec.setCompanyRatio(new BigDecimal(Double.parseDouble(json.getString("COMPANY_RATIO").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_RATIO"))){
			hsEmpOrdSbDetailRec.setIndividualRatio(new BigDecimal(Double.parseDouble(json.getString("INDIVIDUAL_RATIO").toString())));
		}		
		if(StringUtils.isNotBlank(json.getString("COMPANY_SUM"))){
			hsEmpOrdSbDetailRec.setCompanySum(new BigDecimal(Double.parseDouble(json.getString("COMPANY_SUM").toString())));
		}		
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_SUM"))){
			hsEmpOrdSbDetailRec.setIndividualSum(new BigDecimal(Double.parseDouble(json.getString("INDIVIDUAL_SUM").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("PAY_SUM"))){
			hsEmpOrdSbDetailRec.setPaySum(new BigDecimal(Double.parseDouble(json.getString("PAY_SUM").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("COMPANY_APPEND"))){
			hsEmpOrdSbDetailRec.setCompanyAppend(new BigDecimal(Double.parseDouble(json.getString("COMPANY_APPEND").toString())));
		}
		if(StringUtils.isNotBlank(json.getString("INDIVIDUAL_APPEND"))){
			hsEmpOrdSbDetailRec.setIndividualAppend(new BigDecimal(Double.parseDouble(json.getString("INDIVIDUAL_APPEND").toString())));
		}
		
		if(StringUtils.isNotBlank(json.getString("PAYMENT_START_MONTH"))){
			hsEmpOrdSbDetailRec.setPaymentStartMonth(json.getString("PAYMENT_START_MONTH").toString());
		}		
		if(StringUtils.isNotBlank(json.getString("PAYMENT_END_MONTH"))){
			hsEmpOrdSbDetailRec.setPaymentEndMonth(json.getString("PAYMENT_END_MONTH").toString());
		}		
		if(StringUtils.isNotBlank(json.getString("BILL_START_MONTH"))){
			hsEmpOrdSbDetailRec.setBillStartMonth(json.getString("BILL_START_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BILL_END_MONTH"))){
			hsEmpOrdSbDetailRec.setBillEndMonth(json.getString("BILL_END_MONTH").toString());
		}	
		
		if(StringUtils.isNotBlank(json.getString("ORDER_INDEX"))){
			hsEmpOrdSbDetailRec.setOrderIndex(Integer.parseInt(json.getString("ORDER_INDEX").toString()));
		}				
		if(StringUtils.isNotBlank(json.getString("CREATE_BY"))){
			hsEmpOrdSbDetailRec.setCreateUserId(Long.parseLong(json.getString("CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_DT"))){
			try {
				hsEmpOrdSbDetailRec.setCreateDate(JabavaUtil.formatDate(json.getString("CREATE_DT").toString(),"yyyyMMddHHmmss"));
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_BY"))){
			hsEmpOrdSbDetailRec.setUpdateUserId(Long.parseLong(json.getString("UPDATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_DT"))){
			try {
				hsEmpOrdSbDetailRec.setUpdateDate(JabavaUtil.formatDate(json.getString("UPDATE_DT").toString(),"yyyyMMddHHmmss"));
			} catch (Exception e) {
				logger.error("", e);
			}
		}		
		if(StringUtils.isNotBlank(json.getString("IS_DELETED"))){
			hsEmpOrdSbDetailRec.setIsDeleted(Integer.parseInt(json.getString("IS_DELETED").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("HAS_CHANGED"))){
			hsEmpOrdSbDetailRec.setHasChanged(Integer.parseInt(json.getString("HAS_CHANGED").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("SB_START_MONTH"))){
			hsEmpOrdSbDetailRec.setSbStartMonth(json.getString("SB_START_MONTH").toString());
		}
		if(StringUtils.isNotBlank(json.getString("SB_END_MONTH"))){
			hsEmpOrdSbDetailRec.setSbEndMonth(json.getString("SB_END_MONTH").toString());
		}
		return hsEmpOrdSbDetailRec;
	}
	/**
	 * 组装订单
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月20日 下午4:03:48 
	 * </pre>
	 *
	 * @param json
	 * @return
	 */
	private HsEmpOrd getHsEmpOrd(com.alibaba.fastjson.JSONObject json){
		HsEmpOrd hsEmpOrd = new HsEmpOrd();
		hsEmpOrd.setCompanyId(Long.parseLong(json.getString("companyId").toString()));
		hsEmpOrd.setProtocolCode(json.getString("procotolCode").toString());//协议号		
		if(StringUtils.isNotBlank(json.getString("ORDER_ID"))){
			hsEmpOrd.setOrderId(Long.parseLong(json.getString("ORDER_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("CLIENT_ID"))){
			hsEmpOrd.setClientId(Long.parseLong(json.getString("CLIENT_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_STATUS"))){
			hsEmpOrd.setOrderStatus(Long.parseLong(json.getString("ORDER_STATUS").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_CREATE_REMARK"))){
			hsEmpOrd.setOrderCreateRemark(json.getString("ORDER_CREATE_REMARK").toString());
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_CHANGE_REMARK"))){
			hsEmpOrd.setOrderChangeRemark(json.getString("ORDER_CHANGE_REMARK").toString());
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_CANCEL_REMARK"))){
			hsEmpOrd.setOrderCancelRemark(json.getString("ORDER_CANCEL_REMARK").toString());
		}
		//订单创建人
		if(StringUtils.isNotBlank(json.getString("CREATE_USER"))){
			hsEmpOrd.setCreateUser(json.getString("CREATE_USER").toString());
		}
		//订单创建时间
		if(StringUtils.isNotBlank(json.getString("ORD_CREATE_DT"))){			
			try {
				hsEmpOrd.setCreateDate(format.parse(json.getString("ORD_CREATE_DT").toString()));
			} catch (ParseException e) {
				logger.error("", e);
			}
		}
		//订单修改人
		if(StringUtils.isNotBlank(json.getString("UPDATE_USER"))){
			hsEmpOrd.setUpdateUser(json.getString("UPDATE_USER").toString());
		}
		//订单修改时间
		if(StringUtils.isNotBlank(json.getString("ORD_UPDATE_DT"))){	
			
			try {
				hsEmpOrd.setUpdateDate(format.parse(json.getString("ORD_UPDATE_DT").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("CREATE_BY"))){
			hsEmpOrd.setCreateUserId(Long.parseLong(json.getString("CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("UPDATE_BY"))){
			hsEmpOrd.setUpdateUserId(Long.parseLong(json.getString("UPDATE_BY").toString()));
		}
		//报减员
		if(StringUtils.isNotBlank(json.getString("REDUCESTAFF_USER"))){
			hsEmpOrd.setReducestaff(json.getString("REDUCESTAFF_USER").toString());
		}
		//报减员ID
		if(StringUtils.isNotBlank(json.getString("ORD_REDUCESTAFF_UPDATE_BY"))){
			hsEmpOrd.setReducestaffUpdateBy(Long.parseLong(json.getString("ORD_REDUCESTAFF_UPDATE_BY").toString()));
		}
		//报减时间
		if(StringUtils.isNotBlank(json.getString("ORD_REDUCESTAFF_UPDATE_DT"))){
			try {
				hsEmpOrd.setReducestaffUpdateDt(format.parse(json.getString("ORD_REDUCESTAFF_UPDATE_DT").toString()));
			} catch (ParseException e) {
				logger.error("", e);
			}
		}
		//证件号码
		if(StringUtils.isNotBlank(json.getString("CARD_ID"))){	
			hsEmpOrd.setCardNo(json.getString("CARD_ID"));
		}
		//证件类型
		if(StringUtils.isNotBlank(json.getString("CARD_TYPE"))){
			if("1".equals(json.getString("CARD_TYPE"))){//身份证
				hsEmpOrd.setCardType(0);//身份证
			}else if("2".equals(json.getString("CARD_TYPE"))){//军人证
				hsEmpOrd.setCardType(1);
			}else if("5".equals(json.getString("CARD_TYPE"))){//护照
				hsEmpOrd.setCardType(2);
			}else{
				hsEmpOrd.setCardType(3);//其它

			}
			
		}
		//员工ID
		if(StringUtils.isNotBlank(json.getString("EMPLOYEE_ID"))){
			hsEmpOrd.setEmployeeId(Long.parseLong(json.getString("EMPLOYEE_ID").toString()));
		}
		//员工履历ID
		if(StringUtils.isNotBlank(json.getString("EMPLOYEE_REC_ID"))){
			hsEmpOrd.setEmployeeRecId(Long.parseLong(json.getString("EMPLOYEE_REC_ID").toString()));
		}
		//客服名称
		if(StringUtils.isNotBlank(json.getString("CUSTOMER_SERVICE"))){
			hsEmpOrd.setCustomerService(json.getString("CUSTOMER_SERVICE").toString());
		}
		//社保状态 不是空，并且是已申请
		if(StringUtils.isNotBlank(json.getString("SB_APPLY_STATUS")) && json.getString("SB_APPLY_STATUS").equals("1")){
			
			//申请状态为 已申请
			if(StringUtils.isNotBlank(json.getString("SB_PAY_STATUS"))){
				String sbPayStatus = json.getString("SB_PAY_STATUS");
				if(sbPayStatus.equals("1")){//在缴
					hsEmpOrd.setSbStatus(GjjStatus.PAY.getValue());//在缴
				}else if(sbPayStatus.equals("2")){
					hsEmpOrd.setSbStatus(GjjStatus.NOT_PAY.getValue());//停缴
				}
				
			}else{//申请状态已申请 并且 缴纳状态为空
				
				hsEmpOrd.setSbStatus(GjjStatus.APPLY.getValue());//已申请
			}			
			
		}else{
			
			if(StringUtils.isNotBlank(json.getString("SB_PAY_STATUS"))){
				String sbPayStatus = json.getString("SB_PAY_STATUS");
				if(sbPayStatus.equals("1")){//在缴
					hsEmpOrd.setSbStatus(GjjStatus.PAY.getValue());//在缴
				}else if(sbPayStatus.equals("2")){
					hsEmpOrd.setSbStatus(GjjStatus.NOT_PAY.getValue());//停缴
				}
				
			}else{//申请状态为空并且 缴纳状态为空
				hsEmpOrd.setSbStatus(GjjStatus.NOT_APPLY.getValue());//未申请
				
			}
			//不是空并且是驳回状态，显示驳回
			if(StringUtils.isNotBlank(json.getString("SB_PAY_STATUS")) && json.getString("SB_PAY_STATUS").equals("4")){
				hsEmpOrd.setSbStatus(GjjStatus.REJECT.getValue());//驳回
			}
			
		}	
		//在缴 停缴
		if(StringUtils.isNotBlank(json.getString("SB_PAY_STATUS"))){
			String sbPayStatus = json.getString("SB_PAY_STATUS");
			if(sbPayStatus.equals("1")){//在缴
				hsEmpOrd.setSbStatus(GjjStatus.PAY.getValue());//在缴
			}else if(sbPayStatus.equals("2")){
				hsEmpOrd.setSbStatus(GjjStatus.NOT_PAY.getValue());//停缴
			}
			
		}
		//公积金状态不是空，并且是已申请
		if(StringUtils.isNotBlank(json.getString("GJJ_APPLY_STATUS")) && json.getString("GJJ_APPLY_STATUS").equals("1")){
			
			//申请状态为 已申请
			if(StringUtils.isNotBlank(json.getString("GJJ_PAY_STATUS"))){//申请状态已申请 并且 状态为空
				String sbPayStatus = json.getString("GJJ_PAY_STATUS");
				if(sbPayStatus.equals("1")){//在缴
					hsEmpOrd.setGjjStatus(GjjStatus.PAY.getValue());//在缴
				}else if(sbPayStatus.equals("2")){
					hsEmpOrd.setGjjStatus(GjjStatus.NOT_PAY.getValue());//停缴
				}
				
			}else{//
				hsEmpOrd.setGjjStatus(GjjStatus.APPLY.getValue());//已申请
				
			}			
			
		}else{
			
			if(StringUtils.isNotBlank(json.getString("GJJ_PAY_STATUS"))){
				
				String sbPayStatus = json.getString("GJJ_PAY_STATUS");
				if(sbPayStatus.equals("1")){//在缴
					hsEmpOrd.setGjjStatus(GjjStatus.PAY.getValue());//在缴
				}else if(sbPayStatus.equals("2")){
					hsEmpOrd.setGjjStatus(GjjStatus.NOT_PAY.getValue());//停缴
				}
			}else{//申请状态为空并且 缴纳状态为空		
				hsEmpOrd.setGjjStatus(GjjStatus.NOT_APPLY.getValue());//未申请
				
			}
			//不是空并且是驳回状态，显示驳回
			if(StringUtils.isNotBlank(json.getString("GJJ_APPLY_STATUS")) && json.getString("GJJ_APPLY_STATUS").equals("4")){
				hsEmpOrd.setGjjStatus(GjjStatus.REJECT.getValue());//驳回
			}
			
		}
		if(StringUtils.isNotBlank(json.getString("GJJ_PAY_STATUS"))){			
			String sbPayStatus = json.getString("GJJ_PAY_STATUS");
			if(sbPayStatus.equals("1")){//在缴
				hsEmpOrd.setGjjStatus(GjjStatus.PAY.getValue());//在缴
			}else if(sbPayStatus.equals("2")){
				hsEmpOrd.setGjjStatus(GjjStatus.NOT_PAY.getValue());//停缴
			}
		}
		//导入数据的时间
		hsEmpOrd.setImportDate(new Date());
		// 修改为获取过来的订单是否被删除
		if(StringUtils.isNotBlank(json.getString("ORDER_IS_DELETED"))){
			hsEmpOrd.setIsDeleted(Integer.parseInt(json.getString("ORDER_IS_DELETED").toString()));
		}else{
			hsEmpOrd.setIsDeleted(IsDeleted.UN_DELETED.getValue());	
		}
		
		return hsEmpOrd;

	}
	//组装个人基本信息
	private BdEmpBaseInfo getHdEmpBaseInfo(com.alibaba.fastjson.JSONObject json){
		BdEmpBaseInfo bdEmpBaseInfo = new BdEmpBaseInfo();
		if(StringUtils.isNotBlank(json.getString("BD_EMP_BASE_INFO_ID"))){
			bdEmpBaseInfo.setBaseInfoId(Long.parseLong(json.getString("BD_EMP_BASE_INFO_ID").toString()));
		}		
		
		bdEmpBaseInfo.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		
		if(StringUtils.isNotBlank(json.getString("BASE_MARRIAGE_STATUS"))){
			bdEmpBaseInfo.setMarriageStatus(json.getString("BASE_MARRIAGE_STATUS").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_MOBILE"))){
			bdEmpBaseInfo.setMobile(json.getString("BASE_MOBILE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_NATIONALITY"))){
			bdEmpBaseInfo.setNationality(json.getString("BASE_NATIONALITY").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_ORIGINAL_EMPLOYEE_ID"))){
			bdEmpBaseInfo.setOriginalEmployeeId(Long.parseLong(json.getString("BASE_ORIGINAL_EMPLOYEE_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("BASE_PHONE"))){
			bdEmpBaseInfo.setPhone(json.getString("BASE_PHONE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_PROXY_BY"))){
			bdEmpBaseInfo.setProxyBy(Long.parseLong(json.getString("BASE_PROXY_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("BASE_REGISTERED_ADDRESS"))){
			bdEmpBaseInfo.setRegisteredAddress(json.getString("BASE_REGISTERED_ADDRESS").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_RESIDENCE_ADDRESS"))){
			bdEmpBaseInfo.setResidenceAddress(json.getString("BASE_RESIDENCE_ADDRESS").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_RESIDENCE_ZIP"))){
			bdEmpBaseInfo.setResidenceZip(json.getString("BASE_RESIDENCE_ZIP").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_RESIDENT_TYPE"))){
			bdEmpBaseInfo.setResidentType(json.getString("BASE_RESIDENT_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_UPDATE_BY"))){
			bdEmpBaseInfo.setUpdateBy(Long.parseLong(json.getString("BASE_UPDATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("BASE_UPDATE_DT"))){
			try {
				bdEmpBaseInfo.setUpdateDt(format.parse(json.getString("BASE_UPDATE_DT").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("", e);
			}
			
		}
		if(StringUtils.isNotBlank(json.getString("BASE_WORK_ADDRESS"))){
			bdEmpBaseInfo.setWorkAddress(Integer.parseInt(json.getString("BASE_WORK_ADDRESS").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("WORK_CITY"))){
			bdEmpBaseInfo.setWorkCity(json.getString("WORK_CITY").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_EMPLOYEE_NAME"))){
			bdEmpBaseInfo.setEmployeeName(json.getString("BASE_EMPLOYEE_NAME").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_ADDRESS_BEGIN_DATE"))){
			try {
				bdEmpBaseInfo.setAddressBeginDate(format.parse(json.getString("BASE_ADDRESS_BEGIN_DATE").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("BASE_BIRTHDAY"))){
			try {
				bdEmpBaseInfo.setBirthday(format2.parse(json.getString("BASE_BIRTHDAY").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("BASE_CARD_ID"))){
			bdEmpBaseInfo.setCardId(json.getString("BASE_CARD_ID").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_CARD_TYPE"))){
			bdEmpBaseInfo.setCardType(json.getString("BASE_CARD_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_COUNTRY_ID"))){
			bdEmpBaseInfo.setCountryId(Long.parseLong(json.getString("BASE_COUNTRY_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("BASE_CREATE_BY"))){
			bdEmpBaseInfo.setCreateBy(Long.parseLong(json.getString("BASE_CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("BASE_CREATE_DT"))){
			try {
				bdEmpBaseInfo.setCreateDt(format.parse(json.getString("BASE_CREATE_DT").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("BASE_EDUCATION_LEVEL"))){
			bdEmpBaseInfo.setEducationLevel(json.getString("BASE_EDUCATION_LEVEL").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_EMAIL"))){
			bdEmpBaseInfo.setEmail(json.getString("BASE_EMAIL").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_FAMILY_INSURANCE_ADDRESS"))){
			bdEmpBaseInfo.setFamilyInsuranceAddress(json.getString("BASE_FAMILY_INSURANCE_ADDRESS").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_GENDER"))){
			bdEmpBaseInfo.setGender(json.getString("BASE_GENDER").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_MARRIAGE_STATUS"))){
			bdEmpBaseInfo.setMarriageStatus(json.getString("BASE_MARRIAGE_STATUS").toString());
		}
		if(StringUtils.isNotBlank(json.getString("BASE_ORIGINAL_EMPLOYEE_ID"))){
			bdEmpBaseInfo.setOriginalEmployeeId(Long.parseLong(json.getString("BASE_ORIGINAL_EMPLOYEE_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("BASE_ORIGINAL_EMPLOYEE_ID"))){
			bdEmpBaseInfo.setOriginalEmployeeId(Long.parseLong(json.getString("BASE_ORIGINAL_EMPLOYEE_ID").toString()));
		}		
		return bdEmpBaseInfo;		
	}
	//组装员工基本信息
	private BdEmpRecInfo getHdEmpRecInfo(com.alibaba.fastjson.JSONObject json){
		BdEmpRecInfo bdEmpRecInfo = new BdEmpRecInfo();
		if(StringUtils.isNotBlank(json.getString("REC_INFO_ID"))){
			bdEmpRecInfo.setRecInfoId(Long.parseLong(json.getString("REC_INFO_ID").toString()));
		}	
		if(StringUtils.isNotBlank(json.getString("EMPLOYEE_SYSTEM_ID"))){
			bdEmpRecInfo.setEmployeeSystemId(Long.parseLong(json.getString("EMPLOYEE_SYSTEM_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("EMPLOYEE_CLIENT_ID"))){
			bdEmpRecInfo.setEmployeeClientId(json.getString("EMPLOYEE_CLIENT_ID").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_CLIENT_ID"))){
			bdEmpRecInfo.setClientId(Long.parseLong(json.getString("REC_CLIENT_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("REC_HIRE_TYPE"))){
			bdEmpRecInfo.setHireType(json.getString("REC_HIRE_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_HIRE_DATE"))){
			try {
				bdEmpRecInfo.setHireDate(format.parse(json.getString("REC_HIRE_DATE").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("", e);
			}		
		}
		if(StringUtils.isNotBlank(json.getString("REC_HIRE_REMARK"))){
			bdEmpRecInfo.setHireRemark(json.getString("REC_HIRE_REMARK").toString());
		}		
		if(StringUtils.isNotBlank(json.getString("REC_DIMISSION_TYPE"))){
			bdEmpRecInfo.setDimissionType(json.getString("REC_DIMISSION_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_DIMISSION_DATE"))){
			try {
				bdEmpRecInfo.setDimissionDate(format.parse(json.getString("REC_DIMISSION_DATE").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("REC_DIMISSION_REMARK"))){
			bdEmpRecInfo.setDimissionRemark(json.getString("REC_DIMISSION_REMARK").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_HIRE_STATUS"))){
			bdEmpRecInfo.setHireStatus(json.getString("REC_HIRE_STATUS").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_RESIDENCE_ADDRESS"))){
			bdEmpRecInfo.setResidenceAddress(json.getString("REC_RESIDENCE_ADDRESS").toString());
		}
		
		bdEmpRecInfo.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		
		if(StringUtils.isNotBlank(json.getString("REC_MARRIAGE_STATUS"))){
			bdEmpRecInfo.setMarriageStatus(json.getString("REC_MARRIAGE_STATUS").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_MOBILE"))){
			bdEmpRecInfo.setMobile(json.getString("REC_MOBILE").toString());
		}		
		if(StringUtils.isNotBlank(json.getString("REC_PHONE"))){
			bdEmpRecInfo.setPhone(json.getString("REC_PHONE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_PROXY_BY"))){
			bdEmpRecInfo.setProxyBy(Long.parseLong(json.getString("REC_PROXY_BY").toString()));
		}		
		if(StringUtils.isNotBlank(json.getString("REC_RESIDENCE_ADDRESS"))){
			bdEmpRecInfo.setResidenceAddress(json.getString("REC_RESIDENCE_ADDRESS").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_RESIDENCE_ZIP"))){
			bdEmpRecInfo.setResidenceZip(json.getString("REC_RESIDENCE_ZIP").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_RESIDENT_TYPE"))){
			bdEmpRecInfo.setResidentType(json.getString("REC_RESIDENT_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_UPDATE_BY"))){
			bdEmpRecInfo.setUpdateBy(Long.parseLong(json.getString("REC_UPDATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("REC_UPDATE_DT"))){
			try {
				bdEmpRecInfo.setUpdateDt(format.parse(json.getString("REC_UPDATE_DT").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("", e);
			}
			
		}
		if(StringUtils.isNotBlank(json.getString("REC_WORK_ADDRESS"))){
			bdEmpRecInfo.setWorkAddress(Integer.parseInt(json.getString("REC_WORK_ADDRESS").toString()));
		}	
		if(StringUtils.isNotBlank(json.getString("REC_CREATE_BY"))){
			bdEmpRecInfo.setCreateBy(Long.parseLong(json.getString("REC_CREATE_BY").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("REC_CREATE_DT"))){
			try {
				bdEmpRecInfo.setCreateDt(format.parse(json.getString("REC_CREATE_DT").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		if(StringUtils.isNotBlank(json.getString("REC_EDUCATION_LEVEL"))){
			bdEmpRecInfo.setEducationLevel(json.getString("REC_EDUCATION_LEVEL").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_EMAIL"))){
			bdEmpRecInfo.setEmail(json.getString("REC_EMAIL").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_RESIDENT_TYPE"))){
			bdEmpRecInfo.setResidentType(json.getString("REC_RESIDENT_TYPE").toString());
		}
		if(StringUtils.isNotBlank(json.getString("REC_CREATE_ORG_ID"))){
			bdEmpRecInfo.setCreateOrgId(Long.parseLong(json.getString("REC_CREATE_ORG_ID").toString()));
		}		
	
		return bdEmpRecInfo;
		
	}
	/**
	 *  组装订单详情表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月20日 下午4:04:16 
	 * </pre>
	 *
	 * @param json
	 * @return
	 */
	private HsEmpOrdRec getHsEmpOrdRec(com.alibaba.fastjson.JSONObject json){
		HsEmpOrdRec hsEmpOrdRec = new HsEmpOrdRec();
		hsEmpOrdRec.setCompanyId(Long.parseLong(json.getString("companyId").toString()));
		if(StringUtils.isNotBlank(json.getString("ORDER_ID"))){
			hsEmpOrdRec.setOrderId(Long.parseLong(json.getString("ORDER_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("ORDER_REC_ID"))){
			hsEmpOrdRec.setOrdRecId(Long.parseLong(json.getString("ORDER_REC_ID").toString()));
		}
		if(StringUtils.isNotBlank(json.getString("REC_CLIENT_ID"))){
			hsEmpOrdRec.setClientId(Long.parseLong(json.getString("REC_CLIENT_ID").toString()));
		}
		//是否是最新履历 1 是最新履历
		if(StringUtils.isNotBlank(json.getString("REC_IS_LAST"))){
			hsEmpOrdRec.setIsLast(Integer.parseInt(json.getString("REC_IS_LAST").toString()));
		}
		//合同类型（员工类型）
		if(StringUtils.isNotBlank(json.getString("REC_CONTRACT_TYPE"))){
			hsEmpOrdRec.setContractType(Integer.parseInt(json.getString("REC_IS_LAST").toString()));
		}
		//社保个人基数
		if(StringUtils.isNotBlank(json.getString("SB_INDIVIDUAL_BASE")) && !"0".equals(json.getString("SB_INDIVIDUAL_BASE"))){
			hsEmpOrdRec.setSocialInsuranceIndividualBase(new BigDecimal(json.getString("SB_INDIVIDUAL_BASE").toString()));
		}		
		//公积金个人基数
		if(StringUtils.isNotBlank(json.getString("GJJ_INDIVIDUAL_BASE"))  && !"0".equals(json.getString("SB_INDIVIDUAL_BASE"))){
			hsEmpOrdRec.setReserveIndividualBase(new BigDecimal(json.getString("GJJ_INDIVIDUAL_BASE").toString()));
		}		
		hsEmpOrdRec.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		
		return hsEmpOrdRec;

		
	}
	public static void main(String args[]) {
		
		
	}
}