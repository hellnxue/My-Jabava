package com.jabava.pojo.hro.order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单费用信息 
 *
 * @version $Id: HsEmpOrdDetailVO.java, 
 * v 0.1 2016年1月25日 下午3:16:17 
 * <pre>
 * @author steven.chen
 * @date 2016年1月25日 下午3:16:17 
 * </pre>
 */
public class HsEmpOrdDetailVO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.ord_sb_detail_id
     *
     * @mbggenerated
     */
    private Long ordSbDetailId;
    private Long companyId;
    
    //是否预收显示
    private String ifPrepayShow;
    
    
    public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.order_id
     *
     * @mbggenerated
     */
    private Long orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.prod_name
     *
     * @mbggenerated
     */
    private String prodName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.item_id
     *
     * @mbggenerated
     */
    private Long itemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.sb_group_id
     *
     * @mbggenerated
     */
    private Long sbGroupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.policy_group_id
     *
     * @mbggenerated
     */
    private Long policyGroupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.policy_city_code
     *
     * @mbggenerated
     */
    private String policyCityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.item_type
     *
     * @mbggenerated
     */
    private String itemType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.payment_month
     *
     * @mbggenerated
     */
    private String paymentMonth;
    private String headPayment;
    private String headBill;
    
    public String getHeadPayment() {
		return headPayment;
	}

	public void setHeadPayment(String headPayment) {
		this.headPayment = headPayment;
	}

	public String getHeadBill() {
		return headBill;
	}

	public void setHeadBill(String headBill) {
		this.headBill = headBill;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.bill_month
     *
     * @mbggenerated
     */
    private String billMonth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.company_base
     *
     * @mbggenerated
     */
    private BigDecimal companyBase;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.individual_base
     *
     * @mbggenerated
     */
    private BigDecimal individualBase;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.company_ratio
     *
     * @mbggenerated
     */
    private BigDecimal companyRatio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.individual_ratio
     *
     * @mbggenerated
     */
    private BigDecimal individualRatio;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.company_sum
     *
     * @mbggenerated
     */
    private BigDecimal companySum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.individual_sum
     *
     * @mbggenerated
     */
    private BigDecimal individualSum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.pay_sum
     *
     * @mbggenerated
     */
    private BigDecimal paySum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.company_append
     *
     * @mbggenerated
     */
    private BigDecimal companyAppend;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.individual_append
     *
     * @mbggenerated
     */
    private BigDecimal individualAppend;
    
    
    
    private String frequency;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.if_prepay
     *
     * @mbggenerated
     */
    private Integer ifPrepay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.company_bill_template_id
     *
     * @mbggenerated
     */
    private Long companyBillTemplateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.individual_bill_template_id
     *
     * @mbggenerated
     */
    private Long individualBillTemplateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.create_user_id
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.update_user_id
     *
     * @mbggenerated
     */
    private Long updateUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.is_deleted
     *
     * @mbggenerated
     */
    private Integer isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.company_ratio_id
     *
     * @mbggenerated
     */
    private Long companyRatioId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.individual_ratio_id
     *
     * @mbggenerated
     */
    private Long individualRatioId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.has_changed
     *
     * @mbggenerated
     */
    private Integer hasChanged;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.auto_add
     *
     * @mbggenerated
     */
    private Integer autoAdd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord_sb_detail.sb_month
     *
     * @mbggenerated
     */
    private String sbMonth;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.ord_sb_detail_id
     *
     * @return the value of hs_emp_ord_sb_detail.ord_sb_detail_id
     *
     * @mbggenerated
     */
    public Long getOrdSbDetailId() {
        return ordSbDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.ord_sb_detail_id
     *
     * @param ordSbDetailId the value for hs_emp_ord_sb_detail.ord_sb_detail_id
     *
     * @mbggenerated
     */
    public void setOrdSbDetailId(Long ordSbDetailId) {
        this.ordSbDetailId = ordSbDetailId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.order_id
     *
     * @return the value of hs_emp_ord_sb_detail.order_id
     *
     * @mbggenerated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.order_id
     *
     * @param orderId the value for hs_emp_ord_sb_detail.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.prod_name
     *
     * @return the value of hs_emp_ord_sb_detail.prod_name
     *
     * @mbggenerated
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.prod_name
     *
     * @param prodName the value for hs_emp_ord_sb_detail.prod_name
     *
     * @mbggenerated
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.item_id
     *
     * @return the value of hs_emp_ord_sb_detail.item_id
     *
     * @mbggenerated
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.item_id
     *
     * @param itemId the value for hs_emp_ord_sb_detail.item_id
     *
     * @mbggenerated
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.sb_group_id
     *
     * @return the value of hs_emp_ord_sb_detail.sb_group_id
     *
     * @mbggenerated
     */
    public Long getSbGroupId() {
        return sbGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.sb_group_id
     *
     * @param sbGroupId the value for hs_emp_ord_sb_detail.sb_group_id
     *
     * @mbggenerated
     */
    public void setSbGroupId(Long sbGroupId) {
        this.sbGroupId = sbGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.policy_group_id
     *
     * @return the value of hs_emp_ord_sb_detail.policy_group_id
     *
     * @mbggenerated
     */
    public Long getPolicyGroupId() {
        return policyGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.policy_group_id
     *
     * @param policyGroupId the value for hs_emp_ord_sb_detail.policy_group_id
     *
     * @mbggenerated
     */
    public void setPolicyGroupId(Long policyGroupId) {
        this.policyGroupId = policyGroupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.policy_city_code
     *
     * @return the value of hs_emp_ord_sb_detail.policy_city_code
     *
     * @mbggenerated
     */
    public String getPolicyCityCode() {
        return policyCityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.policy_city_code
     *
     * @param policyCityCode the value for hs_emp_ord_sb_detail.policy_city_code
     *
     * @mbggenerated
     */
    public void setPolicyCityCode(String policyCityCode) {
        this.policyCityCode = policyCityCode == null ? null : policyCityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.item_type
     *
     * @return the value of hs_emp_ord_sb_detail.item_type
     *
     * @mbggenerated
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.item_type
     *
     * @param itemType the value for hs_emp_ord_sb_detail.item_type
     *
     * @mbggenerated
     */
    public void setItemType(String itemType) {
        this.itemType = itemType == null ? null : itemType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.payment_month
     *
     * @return the value of hs_emp_ord_sb_detail.payment_month
     *
     * @mbggenerated
     */
    public String getPaymentMonth() {
        return paymentMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.payment_month
     *
     * @param paymentMonth the value for hs_emp_ord_sb_detail.payment_month
     *
     * @mbggenerated
     */
    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth == null ? null : paymentMonth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.bill_month
     *
     * @return the value of hs_emp_ord_sb_detail.bill_month
     *
     * @mbggenerated
     */
    public String getBillMonth() {
        return billMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.bill_month
     *
     * @param billMonth the value for hs_emp_ord_sb_detail.bill_month
     *
     * @mbggenerated
     */
    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth == null ? null : billMonth.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.company_base
     *
     * @return the value of hs_emp_ord_sb_detail.company_base
     *
     * @mbggenerated
     */
    public BigDecimal getCompanyBase() {
        return companyBase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.company_base
     *
     * @param companyBase the value for hs_emp_ord_sb_detail.company_base
     *
     * @mbggenerated
     */
    public void setCompanyBase(BigDecimal companyBase) {
        this.companyBase = companyBase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.individual_base
     *
     * @return the value of hs_emp_ord_sb_detail.individual_base
     *
     * @mbggenerated
     */
    public BigDecimal getIndividualBase() {
        return individualBase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.individual_base
     *
     * @param individualBase the value for hs_emp_ord_sb_detail.individual_base
     *
     * @mbggenerated
     */
    public void setIndividualBase(BigDecimal individualBase) {
        this.individualBase = individualBase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.company_ratio
     *
     * @return the value of hs_emp_ord_sb_detail.company_ratio
     *
     * @mbggenerated
     */
    public BigDecimal getCompanyRatio() {
        return companyRatio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.company_ratio
     *
     * @param companyRatio the value for hs_emp_ord_sb_detail.company_ratio
     *
     * @mbggenerated
     */
    public void setCompanyRatio(BigDecimal companyRatio) {
        this.companyRatio = companyRatio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.individual_ratio
     *
     * @return the value of hs_emp_ord_sb_detail.individual_ratio
     *
     * @mbggenerated
     */
    public BigDecimal getIndividualRatio() {
        return individualRatio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.individual_ratio
     *
     * @param individualRatio the value for hs_emp_ord_sb_detail.individual_ratio
     *
     * @mbggenerated
     */
    public void setIndividualRatio(BigDecimal individualRatio) {
        this.individualRatio = individualRatio;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.company_sum
     *
     * @return the value of hs_emp_ord_sb_detail.company_sum
     *
     * @mbggenerated
     */
    public BigDecimal getCompanySum() {
        return companySum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.company_sum
     *
     * @param companySum the value for hs_emp_ord_sb_detail.company_sum
     *
     * @mbggenerated
     */
    public void setCompanySum(BigDecimal companySum) {
        this.companySum = companySum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.individual_sum
     *
     * @return the value of hs_emp_ord_sb_detail.individual_sum
     *
     * @mbggenerated
     */
    public BigDecimal getIndividualSum() {
        return individualSum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.individual_sum
     *
     * @param individualSum the value for hs_emp_ord_sb_detail.individual_sum
     *
     * @mbggenerated
     */
    public void setIndividualSum(BigDecimal individualSum) {
        this.individualSum = individualSum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.pay_sum
     *
     * @return the value of hs_emp_ord_sb_detail.pay_sum
     *
     * @mbggenerated
     */
    public BigDecimal getPaySum() {
        return paySum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.pay_sum
     *
     * @param paySum the value for hs_emp_ord_sb_detail.pay_sum
     *
     * @mbggenerated
     */
    public void setPaySum(BigDecimal paySum) {
        this.paySum = paySum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.company_append
     *
     * @return the value of hs_emp_ord_sb_detail.company_append
     *
     * @mbggenerated
     */
    public BigDecimal getCompanyAppend() {
        return companyAppend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.company_append
     *
     * @param companyAppend the value for hs_emp_ord_sb_detail.company_append
     *
     * @mbggenerated
     */
    public void setCompanyAppend(BigDecimal companyAppend) {
        this.companyAppend = companyAppend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.individual_append
     *
     * @return the value of hs_emp_ord_sb_detail.individual_append
     *
     * @mbggenerated
     */
    public BigDecimal getIndividualAppend() {
        return individualAppend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.individual_append
     *
     * @param individualAppend the value for hs_emp_ord_sb_detail.individual_append
     *
     * @mbggenerated
     */
    public void setIndividualAppend(BigDecimal individualAppend) {
        this.individualAppend = individualAppend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.company_bill_template_id
     *
     * @return the value of hs_emp_ord_sb_detail.company_bill_template_id
     *
     * @mbggenerated
     */
    public Long getCompanyBillTemplateId() {
        return companyBillTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.company_bill_template_id
     *
     * @param companyBillTemplateId the value for hs_emp_ord_sb_detail.company_bill_template_id
     *
     * @mbggenerated
     */
    public void setCompanyBillTemplateId(Long companyBillTemplateId) {
        this.companyBillTemplateId = companyBillTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.individual_bill_template_id
     *
     * @return the value of hs_emp_ord_sb_detail.individual_bill_template_id
     *
     * @mbggenerated
     */
    public Long getIndividualBillTemplateId() {
        return individualBillTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.individual_bill_template_id
     *
     * @param individualBillTemplateId the value for hs_emp_ord_sb_detail.individual_bill_template_id
     *
     * @mbggenerated
     */
    public void setIndividualBillTemplateId(Long individualBillTemplateId) {
        this.individualBillTemplateId = individualBillTemplateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.create_user_id
     *
     * @return the value of hs_emp_ord_sb_detail.create_user_id
     *
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.create_user_id
     *
     * @param createUserId the value for hs_emp_ord_sb_detail.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.create_date
     *
     * @return the value of hs_emp_ord_sb_detail.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.create_date
     *
     * @param createDate the value for hs_emp_ord_sb_detail.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.update_user_id
     *
     * @return the value of hs_emp_ord_sb_detail.update_user_id
     *
     * @mbggenerated
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.update_user_id
     *
     * @param updateUserId the value for hs_emp_ord_sb_detail.update_user_id
     *
     * @mbggenerated
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.update_date
     *
     * @return the value of hs_emp_ord_sb_detail.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.update_date
     *
     * @param updateDate the value for hs_emp_ord_sb_detail.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.is_deleted
     *
     * @return the value of hs_emp_ord_sb_detail.is_deleted
     *
     * @mbggenerated
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.is_deleted
     *
     * @param isDeleted the value for hs_emp_ord_sb_detail.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.company_ratio_id
     *
     * @return the value of hs_emp_ord_sb_detail.company_ratio_id
     *
     * @mbggenerated
     */
    public Long getCompanyRatioId() {
        return companyRatioId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.company_ratio_id
     *
     * @param companyRatioId the value for hs_emp_ord_sb_detail.company_ratio_id
     *
     * @mbggenerated
     */
    public void setCompanyRatioId(Long companyRatioId) {
        this.companyRatioId = companyRatioId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.individual_ratio_id
     *
     * @return the value of hs_emp_ord_sb_detail.individual_ratio_id
     *
     * @mbggenerated
     */
    public Long getIndividualRatioId() {
        return individualRatioId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.individual_ratio_id
     *
     * @param individualRatioId the value for hs_emp_ord_sb_detail.individual_ratio_id
     *
     * @mbggenerated
     */
    public void setIndividualRatioId(Long individualRatioId) {
        this.individualRatioId = individualRatioId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.has_changed
     *
     * @return the value of hs_emp_ord_sb_detail.has_changed
     *
     * @mbggenerated
     */
    public Integer getHasChanged() {
        return hasChanged;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.has_changed
     *
     * @param hasChanged the value for hs_emp_ord_sb_detail.has_changed
     *
     * @mbggenerated
     */
    public void setHasChanged(Integer hasChanged) {
        this.hasChanged = hasChanged;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.auto_add
     *
     * @return the value of hs_emp_ord_sb_detail.auto_add
     *
     * @mbggenerated
     */
    public Integer getAutoAdd() {
        return autoAdd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.auto_add
     *
     * @param autoAdd the value for hs_emp_ord_sb_detail.auto_add
     *
     * @mbggenerated
     */
    public void setAutoAdd(Integer autoAdd) {
        this.autoAdd = autoAdd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord_sb_detail.sb_month
     *
     * @return the value of hs_emp_ord_sb_detail.sb_month
     *
     * @mbggenerated
     */
    public String getSbMonth() {
        return sbMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord_sb_detail.sb_month
     *
     * @param sbMonth the value for hs_emp_ord_sb_detail.sb_month
     *
     * @mbggenerated
     */
    public void setSbMonth(String sbMonth) {
        this.sbMonth = sbMonth == null ? null : sbMonth.trim();
    }

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Integer getIfPrepay() {
		return ifPrepay;
	}

	public void setIfPrepay(Integer ifPrepay) {
		this.ifPrepay = ifPrepay;
	}

	public String getIfPrepayShow() {
		if(this.getIfPrepay()!=null){
			if(this.getIfPrepay().equals(0)){
				return "不预收";
			}else if(this.getIfPrepay().equals(1)){
				return "预收";
			}
		}
		return ifPrepayShow;
	}

	public void setIfPrepayShow(String ifPrepayShow) {
		this.ifPrepayShow = ifPrepayShow;
	}
	
    
}