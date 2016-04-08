package com.jabava.service.hro.order.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jabava.dao.hro.order.HsEmpOrdMapper;
import com.jabava.dao.hro.order.HsEmpOrdNsbDetailMapper;
import com.jabava.dao.hro.order.HsEmpOrdNsbDetailRecMapper;
import com.jabava.dao.hro.order.HsEmpOrdSbDetailMapper;
import com.jabava.dao.hro.order.HsEmpOrdSbDetailRecMapper;
import com.jabava.pojo.hro.order.HsEmpOrd;
import com.jabava.pojo.hro.order.HsEmpOrdDetailVO;
import com.jabava.pojo.hro.order.HsEmpOrdNsbDetail;
import com.jabava.pojo.hro.order.HsEmpOrdNsbDetailRec;
import com.jabava.pojo.hro.order.HsEmpOrdRec;
import com.jabava.pojo.hro.order.HsEmpOrdSbDetail;
import com.jabava.pojo.hro.order.HsEmpOrdSbDetailRec;
import com.jabava.pojo.hro.order.HsEmpOrdVO;
import com.jabava.service.hro.order.OrderService;
import com.jabava.utils.JabavaDateUtils;

/**
 * 订单
 *
 * @version $Id: OrderServiceImpl.java, 
 * v 0.1 2016年1月22日 上午11:38:32 
 * <pre>
 * @author steven.chen
 * @date 2016年1月22日 上午11:38:32 
 * </pre>
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private HsEmpOrdMapper hsEmpOrdMapper;
	@Autowired
	private HsEmpOrdNsbDetailRecMapper hsEmpOrdNsbDetailRecMapper;
	@Autowired
	private HsEmpOrdSbDetailRecMapper hsEmpOrdSbDetailRecMapper;
	@Autowired
	private HsEmpOrdNsbDetailMapper hsEmpOrdNsbDetailMapper;
	@Autowired
	private HsEmpOrdSbDetailMapper hsEmpOrdSbDetailMapper;
	
	@Override
	public List<HsEmpOrdVO> findServiceCountPage(Map<String, Object> map){
		// return hsEmpOrdMapper.findServiceCountPage(map);
		Date date =JabavaDateUtils.firstDayOfMonth();
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		int now  = Integer.valueOf(df.format(date));
		List<HsEmpOrdVO> ordList= hsEmpOrdMapper.findServiceCountPage(map);
		List<HsEmpOrdVO> ordList2= new ArrayList<HsEmpOrdVO>();
		if(ordList!=null && ordList.size()>0){
			for(HsEmpOrdVO hsEmpOrdVO:ordList){
				if(Integer.parseInt(hsEmpOrdVO.getYearmonth())<=now){
					ordList2.add(hsEmpOrdVO);
				}
				
			}
			
		}
		return ordList2;
	
	}
	@Override
	public List<HsEmpOrdVO> findNewOrderListPage(Map<String, Object> map){
		return hsEmpOrdMapper.findNewOrderListPage(map);
	}
	@Override
	public List<HsEmpOrdVO> newFindOrderListPage(Map<String, Object> map){
		return hsEmpOrdMapper.findOrderListPage(map);
	}
	@Override
	public List<HsEmpOrdVO> findOrderListPage(Map<String, Object> map) {
		return  hsEmpOrdMapper.findOrderListPage(map);		
	}
	@Override
	public List<HsEmpOrdVO> findOrderList(Map<String, Object> map) {
		return hsEmpOrdMapper.findOrderList(map);
	}
	@Override
	public HsEmpOrdVO findOrderDetail(HsEmpOrd hsEmpOrd) {
		return hsEmpOrdMapper.findOrderDetail(hsEmpOrd);
	}

	@Override
	public List<HsEmpOrdSbDetailRec> findOrderSB(HsEmpOrdRec hsEmpOrdRec) {
		return hsEmpOrdSbDetailRecMapper.findOrderSB(hsEmpOrdRec);
	}

	@Override
	public List<HsEmpOrdNsbDetailRec> findOrderNSB(HsEmpOrdRec hsEmpOrdRec) {
		return hsEmpOrdNsbDetailRecMapper.findOrderNSB(hsEmpOrdRec);
	}
	//订单费用信息查询
	@Override
	public List<HsEmpOrdDetailVO> findOrderCostPage(Map<String, Object> map){
	    List<HsEmpOrdNsbDetail>	hsEmpOrdNsbDetailList = hsEmpOrdNsbDetailMapper.findOrderCostPage(map);
	    List<HsEmpOrdSbDetail>	hsEmpOrdSbDetailList = hsEmpOrdSbDetailMapper.findOrderCostPage(map);
	    if("bill".equals(map.get("type").toString())){
	    	return getBillList(hsEmpOrdNsbDetailList,hsEmpOrdSbDetailList);
	    }else{
	    	return getPaymentList(hsEmpOrdNsbDetailList,hsEmpOrdSbDetailList);
	    }
	 
		
	}
	//获取账单月列表 
	private List<HsEmpOrdDetailVO> getBillList( List<HsEmpOrdNsbDetail>	hsEmpOrdNsbDetailList,List<HsEmpOrdSbDetail>	hsEmpOrdSbDetailList){
		List<HsEmpOrdDetailVO> hsEmpOrdDetailVOList = new ArrayList<HsEmpOrdDetailVO>();

		if(hsEmpOrdSbDetailList!=null && hsEmpOrdSbDetailList.size()>0){
	    	
			BigDecimal billSum = new BigDecimal(0);//按照账单月计算的金额
			String billMonth  = hsEmpOrdSbDetailList.get(0).getPaymentMonth();
			int row = 0;//这个月的第一行
			int count=0;
	    	for(HsEmpOrdSbDetail hsEmpOrdSbDetail:hsEmpOrdSbDetailList){	  
	    		
	    		HsEmpOrdDetailVO  hsEmpOrdDetailVO = new HsEmpOrdDetailVO();
	    		hsEmpOrdDetailVO.setPaymentMonth(hsEmpOrdSbDetail.getPaymentMonth());
	    		hsEmpOrdDetailVO.setProdName(hsEmpOrdSbDetail.getProdName());
	    		hsEmpOrdDetailVO.setPaySum(hsEmpOrdSbDetail.getPaySum());
	    		hsEmpOrdDetailVO.setPaymentMonth(hsEmpOrdSbDetail.getPaymentMonth());
	    		hsEmpOrdDetailVO.setBillMonth(hsEmpOrdSbDetail.getBillMonth());
	    		hsEmpOrdDetailVO.setCompanyBase(hsEmpOrdSbDetail.getCompanyBase());
	    		hsEmpOrdDetailVO.setIndividualBase(hsEmpOrdSbDetail.getIndividualBase());
	    		hsEmpOrdDetailVO.setCompanyRatio(hsEmpOrdSbDetail.getCompanyRatio());
	    		hsEmpOrdDetailVO.setIndividualRatio(hsEmpOrdSbDetail.getIndividualRatio());
	    		hsEmpOrdDetailVO.setFrequency(hsEmpOrdSbDetail.getFrequency());
	    		hsEmpOrdDetailVO.setIfPrepay(hsEmpOrdSbDetail.getIfPrepay());
	    		hsEmpOrdDetailVO.setCompanySum(hsEmpOrdSbDetail.getCompanySum());
	    		hsEmpOrdDetailVO.setIndividualSum(hsEmpOrdSbDetail.getIndividualSum());
	    		count++;
	    		if(billMonth.equals(hsEmpOrdSbDetail.getBillMonth())){//在同一个账单月里的金额
	    			if(row==0  && count==1){
	    				HsEmpOrdDetailVO  hsEmpOrdDetailVO1 = new HsEmpOrdDetailVO();
	    		    	hsEmpOrdDetailVO1.setHeadPayment(hsEmpOrdSbDetail.getPaymentMonth());//第一行的服务月
	    		    	hsEmpOrdDetailVO1.setHeadBill(hsEmpOrdSbDetail.getBillMonth());//第一行的账单月
	    				hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO1);
	    			}
	    			row++;
	    			billSum = billSum.add(hsEmpOrdSbDetail.getPaySum());
	    			hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO);
	    			if(count==hsEmpOrdSbDetailList.size()){//最后一行
	    				HsEmpOrdDetailVO  hsEmpOrdDetailVO2 = new HsEmpOrdDetailVO();		    			
		    			hsEmpOrdDetailVO2.setPaySum(billSum);//这个月账单的总金额
		    			hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO2);
	    			}
	    		}else{
	    			//统计这个月非社保的金额
	    			   if(hsEmpOrdNsbDetailList!=null && hsEmpOrdNsbDetailList.size()>0){
	   	    	    	for(HsEmpOrdNsbDetail hsEmpOrdNsbDetail:hsEmpOrdNsbDetailList){
	   	    	    		HsEmpOrdDetailVO  hsEmpOrdDetailVO3 = new HsEmpOrdDetailVO();
	   	    	    		hsEmpOrdDetailVO3.setPaymentMonth(hsEmpOrdNsbDetail.getPaymentMonth());
	   	    	    		hsEmpOrdDetailVO3.setProdName(hsEmpOrdNsbDetail.getProdName());
	   	    	    		hsEmpOrdDetailVO3.setPaySum(hsEmpOrdNsbDetail.getSum());
	   	    	    		hsEmpOrdDetailVO3.setPaymentMonth(hsEmpOrdNsbDetail.getPaymentMonth());
	   	    	    		hsEmpOrdDetailVO3.setBillMonth(hsEmpOrdNsbDetail.getBillMonth());
	   	    	    		hsEmpOrdDetailVO3.setCompanyBase(new BigDecimal(0));
	   	    	    		hsEmpOrdDetailVO3.setIndividualBase(new BigDecimal(0));
	   	    	    		hsEmpOrdDetailVO3.setCompanyRatio(new BigDecimal(0));
	   	    	    		hsEmpOrdDetailVO3.setIndividualRatio(new BigDecimal(0));
	   	    	    		hsEmpOrdDetailVO3.setFrequency(hsEmpOrdNsbDetail.getFrequency());
	   	    	    		hsEmpOrdDetailVO3.setIfPrepay(hsEmpOrdNsbDetail.getIfPrepay());
	   	    	    		if(billMonth.equals(hsEmpOrdNsbDetail.getBillMonth())){//在同一个账单月里的金额
	   	    	    			if(row==0){//第一行
	   	 	    				HsEmpOrdDetailVO  hsEmpOrdDetailVO1 = new HsEmpOrdDetailVO();
	   	 	    		    	hsEmpOrdDetailVO1.setHeadPayment(hsEmpOrdNsbDetail.getPaymentMonth());//第一行的服务月
	   	 	    		    	hsEmpOrdDetailVO1.setHeadBill(hsEmpOrdNsbDetail.getBillMonth());//第一行的账单月
	   	 	    				hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO1);
	   	    	    			}
	   	    	    			billSum = billSum.add(hsEmpOrdNsbDetail.getSum());
	   	    	    			row++;
		   	    	    		hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO3);
	   	 	    			}
	   	    	    	}
	   	    	    	
	   	    	    }
	    			if(row!=0){
	    				HsEmpOrdDetailVO  hsEmpOrdDetailVO2 = new HsEmpOrdDetailVO();
		    			
		    			hsEmpOrdDetailVO2.setPaySum(billSum);//这个月账单的总金额
		    			hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO2);
		    			row=0;
		    			HsEmpOrdDetailVO  hsEmpOrdDetailVO1 = new HsEmpOrdDetailVO();
	    		    	hsEmpOrdDetailVO1.setHeadPayment(hsEmpOrdSbDetail.getPaymentMonth());//第一行的服务月
	    		    	hsEmpOrdDetailVO1.setHeadBill(hsEmpOrdSbDetail.getBillMonth());//第一行的账单月
	    				hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO1);
	    				hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO);//第二行
		    			billSum=new BigDecimal(0);
		    			billMonth = hsEmpOrdSbDetail.getBillMonth();
	    			}   
	    			
	    		}
	    	 
	    	}
	    	
	    }
		return hsEmpOrdDetailVOList;
	}
	//获取服务月月列表 
	private List<HsEmpOrdDetailVO> getPaymentList( List<HsEmpOrdNsbDetail>	hsEmpOrdNsbDetailList,List<HsEmpOrdSbDetail>	hsEmpOrdSbDetailList){
			List<HsEmpOrdDetailVO> hsEmpOrdDetailVOList = new ArrayList<HsEmpOrdDetailVO>();

			if(hsEmpOrdSbDetailList!=null && hsEmpOrdSbDetailList.size()>0){
		    	
				BigDecimal paymentSum = new BigDecimal(0);//按照服务月计算的金额
				String paymentMonth  = hsEmpOrdSbDetailList.get(0).getPaymentMonth();
				int row = 0;//这个月的第一行
				int count=0;
		    	for(HsEmpOrdSbDetail hsEmpOrdSbDetail:hsEmpOrdSbDetailList){		    		
		    	 		
		    		HsEmpOrdDetailVO  hsEmpOrdDetailVO = new HsEmpOrdDetailVO();
		    		hsEmpOrdDetailVO.setPaymentMonth(hsEmpOrdSbDetail.getPaymentMonth());
		    		hsEmpOrdDetailVO.setProdName(hsEmpOrdSbDetail.getProdName());
		    		hsEmpOrdDetailVO.setPaySum(hsEmpOrdSbDetail.getPaySum());
		    		hsEmpOrdDetailVO.setPaymentMonth(hsEmpOrdSbDetail.getPaymentMonth());
		    		hsEmpOrdDetailVO.setBillMonth(hsEmpOrdSbDetail.getBillMonth());
		    		hsEmpOrdDetailVO.setCompanyBase(hsEmpOrdSbDetail.getCompanyBase());
		    		hsEmpOrdDetailVO.setIndividualBase(hsEmpOrdSbDetail.getIndividualBase());
		    		hsEmpOrdDetailVO.setCompanyRatio(hsEmpOrdSbDetail.getCompanyRatio());
		    		hsEmpOrdDetailVO.setIndividualRatio(hsEmpOrdSbDetail.getIndividualRatio());
		    		hsEmpOrdDetailVO.setFrequency(hsEmpOrdSbDetail.getFrequency());
		    		hsEmpOrdDetailVO.setIfPrepay(hsEmpOrdSbDetail.getIfPrepay());
		    		hsEmpOrdDetailVO.setCompanySum(hsEmpOrdSbDetail.getCompanySum());
		    		hsEmpOrdDetailVO.setIndividualSum(hsEmpOrdSbDetail.getIndividualSum());
		    		count++;
		    		if(paymentMonth.equals(hsEmpOrdSbDetail.getPaymentMonth())){//在同一个月里的金额
		    			if(row==0 && count==1){// 第一行
		    				HsEmpOrdDetailVO  hsEmpOrdDetailVO1 = new HsEmpOrdDetailVO();
		    		    	hsEmpOrdDetailVO1.setHeadPayment(hsEmpOrdSbDetail.getPaymentMonth());//第一行的服务月
		    		    	hsEmpOrdDetailVO1.setHeadBill(hsEmpOrdSbDetail.getBillMonth());//第一行的账单月
		    				hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO1);
		    			}
		    			row++;
		    			paymentSum = paymentSum.add(hsEmpOrdSbDetail.getPaySum());
		    			hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO);
		    			if(count==hsEmpOrdSbDetailList.size()){//最后一行
		    				//统计这个月非社保的金额
			    			   if(hsEmpOrdNsbDetailList!=null && hsEmpOrdNsbDetailList.size()>0){
			   	    	    	for(HsEmpOrdNsbDetail hsEmpOrdNsbDetail:hsEmpOrdNsbDetailList){
			   	    	    		HsEmpOrdDetailVO  hsEmpOrdDetailVO3 = new HsEmpOrdDetailVO();
			   	    	    		hsEmpOrdDetailVO3.setPaymentMonth(hsEmpOrdNsbDetail.getPaymentMonth());
			   	    	    		hsEmpOrdDetailVO3.setProdName(hsEmpOrdNsbDetail.getProdName());
			   	    	    		hsEmpOrdDetailVO3.setPaySum(hsEmpOrdNsbDetail.getSum());
			   	    	    		hsEmpOrdDetailVO3.setPaymentMonth(hsEmpOrdNsbDetail.getPaymentMonth());
			   	    	    		hsEmpOrdDetailVO3.setBillMonth(hsEmpOrdNsbDetail.getBillMonth());
			   	    	    		hsEmpOrdDetailVO3.setCompanyBase(new BigDecimal(0));
			   	    	    		hsEmpOrdDetailVO3.setIndividualBase(new BigDecimal(0));
			   	    	    		hsEmpOrdDetailVO3.setCompanyRatio(new BigDecimal(0));
			   	    	    		hsEmpOrdDetailVO3.setIndividualRatio(new BigDecimal(0));
			   	    	    		hsEmpOrdDetailVO3.setFrequency(hsEmpOrdNsbDetail.getFrequency());
			   	    	    		hsEmpOrdDetailVO3.setIfPrepay(hsEmpOrdNsbDetail.getIfPrepay());		   	    	    		
			   	    	    		if(paymentMonth.equals(hsEmpOrdNsbDetail.getPaymentMonth())){//在同一个账单月里的金额
			   	    	    			if(row==0){//第一行
			   	 	    				HsEmpOrdDetailVO  hsEmpOrdDetailVO1 = new HsEmpOrdDetailVO();
			   	 	    		    	hsEmpOrdDetailVO1.setHeadPayment(hsEmpOrdNsbDetail.getPaymentMonth());//第一行的服务月
			   	 	    		    	hsEmpOrdDetailVO1.setHeadBill(hsEmpOrdNsbDetail.getBillMonth());//第一行的账单月
			   	 	    				hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO1);
			   	    	    			}
			   	    	    			paymentSum = paymentSum.add(hsEmpOrdNsbDetail.getSum());
			   	    	    			row++;
				   	    	    		hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO3);
			   	 	    			}
			   	    	    	}
			   	    	    	
			   	    	    }
		    				HsEmpOrdDetailVO  hsEmpOrdDetailVO2 = new HsEmpOrdDetailVO();		    			
			    			hsEmpOrdDetailVO2.setPaySum(paymentSum);//这个月账单的总金额
			    			hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO2);
		    			}
		    		}else{
		    			//统计这个月非社保的金额
		    			   if(hsEmpOrdNsbDetailList!=null && hsEmpOrdNsbDetailList.size()>0){
		   	    	    	for(HsEmpOrdNsbDetail hsEmpOrdNsbDetail:hsEmpOrdNsbDetailList){
		   	    	    		HsEmpOrdDetailVO  hsEmpOrdDetailVO3 = new HsEmpOrdDetailVO();
		   	    	    		hsEmpOrdDetailVO3.setPaymentMonth(hsEmpOrdNsbDetail.getPaymentMonth());
		   	    	    		hsEmpOrdDetailVO3.setProdName(hsEmpOrdNsbDetail.getProdName());
		   	    	    		hsEmpOrdDetailVO3.setPaySum(hsEmpOrdNsbDetail.getSum());
		   	    	    		hsEmpOrdDetailVO3.setPaymentMonth(hsEmpOrdNsbDetail.getPaymentMonth());
		   	    	    		hsEmpOrdDetailVO3.setBillMonth(hsEmpOrdNsbDetail.getBillMonth());
		   	    	    		hsEmpOrdDetailVO3.setCompanyBase(new BigDecimal(0));
		   	    	    		hsEmpOrdDetailVO3.setIndividualBase(new BigDecimal(0));
		   	    	    		hsEmpOrdDetailVO3.setCompanyRatio(new BigDecimal(0));
		   	    	    		hsEmpOrdDetailVO3.setIndividualRatio(new BigDecimal(0));
		   	    	    		hsEmpOrdDetailVO3.setFrequency(hsEmpOrdNsbDetail.getFrequency());
		   	    	    		hsEmpOrdDetailVO3.setIfPrepay(hsEmpOrdNsbDetail.getIfPrepay());		   	    	    		
		   	    	    		if(paymentMonth.equals(hsEmpOrdNsbDetail.getPaymentMonth())){//在同一个账单月里的金额
		   	    	    			if(row==0){//第一行
		   	 	    				HsEmpOrdDetailVO  hsEmpOrdDetailVO1 = new HsEmpOrdDetailVO();
		   	 	    		    	hsEmpOrdDetailVO1.setHeadPayment(hsEmpOrdNsbDetail.getPaymentMonth());//第一行的服务月
		   	 	    		    	hsEmpOrdDetailVO1.setHeadBill(hsEmpOrdNsbDetail.getBillMonth());//第一行的账单月
		   	 	    				hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO1);
		   	    	    			}
		   	    	    			paymentSum = paymentSum.add(hsEmpOrdNsbDetail.getSum());
		   	    	    			row++;
			   	    	    		hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO3);
		   	 	    			}
		   	    	    	}
		   	    	    	
		   	    	    }
	    			   if(row!=0){//每个月最后一行的总额
	    				    HsEmpOrdDetailVO  hsEmpOrdDetailVO2 = new HsEmpOrdDetailVO();		    			
			    			hsEmpOrdDetailVO2.setPaySum(paymentSum);//这个服务月的总金额
			    			hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO2);
			    			row=0;
			    			HsEmpOrdDetailVO  hsEmpOrdDetailVO1 = new HsEmpOrdDetailVO();
		    		    	hsEmpOrdDetailVO1.setHeadPayment(hsEmpOrdSbDetail.getPaymentMonth());//第一行的服务月
		    		    	hsEmpOrdDetailVO1.setHeadBill(hsEmpOrdSbDetail.getBillMonth());//第一行的账单月
		    				hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO1);
		    				hsEmpOrdDetailVOList.add(hsEmpOrdDetailVO);
			    			paymentSum=new BigDecimal(0);
			    			paymentMonth = hsEmpOrdSbDetail.getPaymentMonth();
	    			   }
		    			
		    		}
		    	 
		    	}
		    	
		    }
			return hsEmpOrdDetailVOList;
		}
	
	
}
