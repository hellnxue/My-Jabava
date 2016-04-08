package com.jabava.pojo.hro.order;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jabava.core.EnumConstents.AccountType;
import com.jabava.core.EnumConstents.ContractType;
import com.jabava.core.EnumConstents.DimissionType;
import com.jabava.core.EnumConstents.Gender;
import com.jabava.core.EnumConstents.GjjStatus;
import com.jabava.core.EnumConstents.HireStatus;
import com.jabava.core.EnumConstents.HroCardType;
import com.jabava.core.EnumConstents.OrderStatus;
import com.jabava.core.EnumConstents.SbStatus;
import com.jabava.pojo.hro.BdEmpBaseInfo;
import com.jabava.pojo.hro.BdEmpRecInfo;
import com.jabava.pojo.manage.EhrPerson;

public class HsEmpOrdVO { 
	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.order_id
     *
     * @mbggenerated
     */
    private Long orderId;
    // 订单履历表ID
    private Long ordRecId;
    // 是否最新 1 为最新 0 不是最新
   private Integer  isLast;
   private Long companyId;
   // 个人基本信息
   private  BdEmpBaseInfo bdEmpBaseInfo;
   // 个人详细信息
   private BdEmpRecInfo bdEmpRecInfo;
   //  一些用于页面状态显示的字段
    //证件类型
   private  String cardTypeShow;
   //性别
   private String genderShow;
   //雇佣状态
   private String hireStatusShow;
   //订单状态
   private String orderStatusShow;
   //离职原因
   private String dimissionTypeShow;
   //户籍性质
   private String residentTypeShow;
   //服务状态显示
   private String serviceStatusShow;
   //社保状态显示
   private String sbStatusShow;
   //公积金状态显示
   private String gjjStatusShow;
   //服务起始月
   @SuppressWarnings("unused")
   private Date  serviceStartDate;
   //服务结束月
   private Date serviceEndDate;
   //年月
  private String  yearmonth;
  //服务人数
  private Long serviceCount;
  //增员人数
  private Long addCount;
  //减员人数
  private Long delCount;
  //变更人数
  private Long changeCount;
  //合同类型(员工类型)
  private String contractTypeShow;
  // 用于存放Long类型的数据
  private List<Long> idList;
  //当前时间
  private Date nowDate;
 
	
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.client_id
     *
     * @mbggenerated
     */
    private Long clientId;
    //证件类型
    private Integer cardType;
    // 证件号码
    private String cardNo;
    
    private EhrPerson ehrPerson;
    //订单履历表
    private HsEmpOrdRec hsEmpOrdRec;
    
    //客服
    private String customerService;
    // 社保 始做状态
    private Integer sbStatus;  
    //公积金始做状态
    private Integer gjjStatus;
    //数据导入时间
    private Date importDate;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.employee_id
     *
     * @mbggenerated
     */
    private Long employeeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.order_status
     *
     * @mbggenerated
     */
    private Long orderStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.order_create_remark
     *
     * @mbggenerated
     */
    private String orderCreateRemark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.order_change_remark
     *
     * @mbggenerated
     */
    private String orderChangeRemark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.order_cancel_remark
     *
     * @mbggenerated
     */
    private String orderCancelRemark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.create_user_id
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.update_user
     *
     * @mbggenerated
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.update_user_id
     *
     * @mbggenerated
     */
    private Long updateUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.is_deleted
     *
     * @mbggenerated
     */
    private Integer isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.employee_rec_id
     *
     * @mbggenerated
     */
    private Long employeeRecId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.reducestaff
     *
     * @mbggenerated
     */
    private String reducestaff;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.reducestaff_update_by
     *
     * @mbggenerated
     */
    private Long reducestaffUpdateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hs_emp_ord.reducestaff_update_dt
     *
     * @mbggenerated
     */
    private Date reducestaffUpdateDt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.order_id
     *
     * @return the value of hs_emp_ord.order_id
     *
     * @mbggenerated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.order_id
     *
     * @param orderId the value for hs_emp_ord.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.client_id
     *
     * @return the value of hs_emp_ord.client_id
     *
     * @mbggenerated
     */
    public Long getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.client_id
     *
     * @param clientId the value for hs_emp_ord.client_id
     *
     * @mbggenerated
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.employee_id
     *
     * @return the value of hs_emp_ord.employee_id
     *
     * @mbggenerated
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.employee_id
     *
     * @param employeeId the value for hs_emp_ord.employee_id
     *
     * @mbggenerated
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.order_status
     *
     * @return the value of hs_emp_ord.order_status
     *
     * @mbggenerated
     */
    public Long getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.order_status
     *
     * @param orderStatus the value for hs_emp_ord.order_status
     *
     * @mbggenerated
     */
    public void setOrderStatus(Long orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.order_create_remark
     *
     * @return the value of hs_emp_ord.order_create_remark
     *
     * @mbggenerated
     */
    public String getOrderCreateRemark() {
        return orderCreateRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.order_create_remark
     *
     * @param orderCreateRemark the value for hs_emp_ord.order_create_remark
     *
     * @mbggenerated
     */
    public void setOrderCreateRemark(String orderCreateRemark) {
        this.orderCreateRemark = orderCreateRemark == null ? null : orderCreateRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.order_change_remark
     *
     * @return the value of hs_emp_ord.order_change_remark
     *
     * @mbggenerated
     */
    public String getOrderChangeRemark() {
        return orderChangeRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.order_change_remark
     *
     * @param orderChangeRemark the value for hs_emp_ord.order_change_remark
     *
     * @mbggenerated
     */
    public void setOrderChangeRemark(String orderChangeRemark) {
        this.orderChangeRemark = orderChangeRemark == null ? null : orderChangeRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.order_cancel_remark
     *
     * @return the value of hs_emp_ord.order_cancel_remark
     *
     * @mbggenerated
     */
    public String getOrderCancelRemark() {
        return orderCancelRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.order_cancel_remark
     *
     * @param orderCancelRemark the value for hs_emp_ord.order_cancel_remark
     *
     * @mbggenerated
     */
    public void setOrderCancelRemark(String orderCancelRemark) {
        this.orderCancelRemark = orderCancelRemark == null ? null : orderCancelRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.create_user
     *
     * @return the value of hs_emp_ord.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.create_user
     *
     * @param createUser the value for hs_emp_ord.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.create_user_id
     *
     * @return the value of hs_emp_ord.create_user_id
     *
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.create_user_id
     *
     * @param createUserId the value for hs_emp_ord.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.create_date
     *
     * @return the value of hs_emp_ord.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.create_date
     *
     * @param createDate the value for hs_emp_ord.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.update_user
     *
     * @return the value of hs_emp_ord.update_user
     *
     * @mbggenerated
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.update_user
     *
     * @param updateUser the value for hs_emp_ord.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.update_user_id
     *
     * @return the value of hs_emp_ord.update_user_id
     *
     * @mbggenerated
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.update_user_id
     *
     * @param updateUserId the value for hs_emp_ord.update_user_id
     *
     * @mbggenerated
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.update_date
     *
     * @return the value of hs_emp_ord.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.update_date
     *
     * @param updateDate the value for hs_emp_ord.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.is_deleted
     *
     * @return the value of hs_emp_ord.is_deleted
     *
     * @mbggenerated
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.is_deleted
     *
     * @param isDeleted the value for hs_emp_ord.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.employee_rec_id
     *
     * @return the value of hs_emp_ord.employee_rec_id
     *
     * @mbggenerated
     */
    public Long getEmployeeRecId() {
        return employeeRecId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.employee_rec_id
     *
     * @param employeeRecId the value for hs_emp_ord.employee_rec_id
     *
     * @mbggenerated
     */
    public void setEmployeeRecId(Long employeeRecId) {
        this.employeeRecId = employeeRecId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.reducestaff
     *
     * @return the value of hs_emp_ord.reducestaff
     *
     * @mbggenerated
     */
    public String getReducestaff() {
        return reducestaff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.reducestaff
     *
     * @param reducestaff the value for hs_emp_ord.reducestaff
     *
     * @mbggenerated
     */
    public void setReducestaff(String reducestaff) {
        this.reducestaff = reducestaff == null ? null : reducestaff.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.reducestaff_update_by
     *
     * @return the value of hs_emp_ord.reducestaff_update_by
     *
     * @mbggenerated
     */
    public Long getReducestaffUpdateBy() {
        return reducestaffUpdateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.reducestaff_update_by
     *
     * @param reducestaffUpdateBy the value for hs_emp_ord.reducestaff_update_by
     *
     * @mbggenerated
     */
    public void setReducestaffUpdateBy(Long reducestaffUpdateBy) {
        this.reducestaffUpdateBy = reducestaffUpdateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hs_emp_ord.reducestaff_update_dt
     *
     * @return the value of hs_emp_ord.reducestaff_update_dt
     *
     * @mbggenerated
     */
    public Date getReducestaffUpdateDt() {
        return reducestaffUpdateDt;
    }
    public Long getOrdRecId() {
		return ordRecId;
	}

	public void setOrdRecId(Long ordRecId) {
		this.ordRecId = ordRecId;
	}

	public Integer getIsLast() {
		return isLast;
	}

	public void setIsLast(Integer isLast) {
		this.isLast = isLast;
	}

	public HsEmpOrdRec getHsEmpOrdRec() {
		return hsEmpOrdRec;
	}

	public void setHsEmpOrdRec(HsEmpOrdRec hsEmpOrdRec) {
		this.hsEmpOrdRec = hsEmpOrdRec;
	}

	public EhrPerson getEhrPerson() {
		return ehrPerson;
	}

	public void setEhrPerson(EhrPerson ehrPerson) {
		this.ehrPerson = ehrPerson;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hs_emp_ord.reducestaff_update_dt
     *
     * @param reducestaffUpdateDt the value for hs_emp_ord.reducestaff_update_dt
     *
     * @mbggenerated
     */
    public void setReducestaffUpdateDt(Date reducestaffUpdateDt) {
        this.reducestaffUpdateDt = reducestaffUpdateDt;
    }
    public String getCardTypeShow() {
		if(this.getBdEmpBaseInfo()!=null && StringUtils.isNotBlank(this.getBdEmpBaseInfo().getCardType())){
			if(HroCardType.IDENTITY_CARD.getValue().equals(this.getBdEmpBaseInfo().getCardType())){
				return "身份证";
			}else if(HroCardType.SOLDIER_CARD.getValue().equals(this.getBdEmpBaseInfo().getCardType())){
				return "军人证";
			}else if(HroCardType.HONG_KONG_CARD.getValue().equals(this.getBdEmpBaseInfo().getCardType())){
				return "港澳身份证";
			}else if(HroCardType.MTP.getValue().equals(this.getBdEmpBaseInfo().getCardType())){
				return "台胞证";
			}else if(HroCardType.PASSPORT.getValue().equals(this.getBdEmpBaseInfo().getCardType())){
				return "护照";
			}else if(HroCardType.OTHER.getValue().equals(this.getBdEmpBaseInfo().getCardType())){
				return "其它";
			}
			
		}
		return cardTypeShow;
	}

	public void setCardTypeShow(String cardTypeShow) {
		this.cardTypeShow = cardTypeShow;
	}

	public String getGenderShow() {
		if(this.getBdEmpBaseInfo()!=null && StringUtils.isNotBlank(this.getBdEmpBaseInfo().getGender())){
			if(Gender.BOY.getValue().equals(this.getBdEmpBaseInfo().getGender())){
				return "男";
			}else if(Gender.GIRL.getValue().equals(this.getBdEmpBaseInfo().getGender())){
				return "女";
			}
			
		}
		return genderShow;
	}

	public void setGenderShow(String genderShow) {
		this.genderShow = genderShow;
	}

	public String getHireStatusShow() {
		
		if(this.getBdEmpRecInfo()!=null && StringUtils.isNotBlank(this.getBdEmpRecInfo().getHireStatus())){
			if(HireStatus.ENTRY.getValue().equals(this.getBdEmpRecInfo().getHireStatus())){
				return "入职中";
			}else if(HireStatus.JOB.getValue().equals(this.getBdEmpRecInfo().getHireStatus())){
				return "在职";
			}else if(HireStatus.LEAVING.getValue().equals(this.getBdEmpRecInfo().getHireStatus())){
				return "离职中";
			}else if(HireStatus.LEAVE.getValue().equals(this.getBdEmpRecInfo().getHireStatus())){
				return "离职";
			}
			
		}
		return hireStatusShow;
	}

	public void setHireStatusShow(String hireStatusShow) {
		this.hireStatusShow = hireStatusShow;
	}

	public String getOrderStatusShow() {
		
		if(this.getOrderStatus()!=null){
			if(OrderStatus.ADD_CONFIRM.getValue().equals(this.getOrderStatus())){
				return "增员已确认";
			}else if(OrderStatus.CHANGE_CONFIRM.getValue().equals(this.getOrderStatus())){
				return "变更已确认";
			}else if(OrderStatus.REDUCE_CONFIRM.getValue().equals(this.getOrderStatus())){
				return "减员已确认";
			}else if(OrderStatus.CANCEL_REDUCE.getValue().equals(this.getOrderStatus())){
				return "撤销减员";
			}
			
		}
		return orderStatusShow;
	}

	public void setOrderStatusShow(String orderStatusShow) {
		this.orderStatusShow = orderStatusShow;
	}

	public String getDimissionTypeShow() {
		
		if(this.getBdEmpRecInfo()!=null && StringUtils.isNotBlank(this.getBdEmpRecInfo().getDimissionType())){
			if(DimissionType.DIMISSION_TYPE_1.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "合同到期终止";
			}else if(DimissionType.DIMISSION_TYPE_2.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "试用期解除";
			}else if(DimissionType.DIMISSION_TYPE_3.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "个人主动辞职";
			}else if(DimissionType.DIMISSION_TYPE_4.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "死亡/失踪";
			}else if(DimissionType.DIMISSION_TYPE_5.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "不胜任工作";
			}else if(DimissionType.DIMISSION_TYPE_6.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "单位与个人协商一致";
			}else if(DimissionType.DIMISSION_TYPE_7.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "个人与单位协商一致";
			}else if(DimissionType.DIMISSION_TYPE_8.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "严重违反用人单位规章制度";
			}else if(DimissionType.DIMISSION_TYPE_9.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "其它";
			}else if(DimissionType.DIMISSION_TYPE_10.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "给用人单位造成重大损害";
			}else if(DimissionType.DIMISSION_TYPE_11.getValue().equals(this.getBdEmpRecInfo().getDimissionType())){
				return "退休";
			}
			
		}
		return dimissionTypeShow;
	}

	public void setDimissionTypeShow(String dimissionTypeShow) {
		this.dimissionTypeShow = dimissionTypeShow;
	}

	public String getResidentTypeShow() {
		if(this.getBdEmpRecInfo()!=null && StringUtils.isNotBlank(this.getBdEmpRecInfo().getResidentType())){
			if(AccountType.NO_LIMIT.getValue().equals(this.getBdEmpRecInfo().getResidentType())){
				return "不限";
			}else if(AccountType.LOCAL_TOWNS.getValue().equals(this.getBdEmpRecInfo().getResidentType())){
				return "本地城镇";
			}else if(AccountType.OTHER_TOWNS.getValue().equals(this.getBdEmpRecInfo().getResidentType())){
				return "外地城镇";
			}else if(AccountType.LOCAL_RURAL_AREAS.getValue().equals(this.getBdEmpRecInfo().getResidentType())){
				return "本地农村";
			}else if(AccountType.RURAL_AREAS.getValue().equals(this.getBdEmpRecInfo().getResidentType())){
				return "外地农村";
			}
		}
		return residentTypeShow;
	}

	public void setResidentTypeShow(String residentTypeShow) {
		this.residentTypeShow = residentTypeShow;
	}

	public BdEmpBaseInfo getBdEmpBaseInfo() {
		return bdEmpBaseInfo;
	}

	public void setBdEmpBaseInfo(BdEmpBaseInfo bdEmpBaseInfo) {
		this.bdEmpBaseInfo = bdEmpBaseInfo;
	}

	public BdEmpRecInfo getBdEmpRecInfo() {
		return bdEmpRecInfo;
	}

	public void setBdEmpRecInfo(BdEmpRecInfo bdEmpRecInfo) {
		this.bdEmpRecInfo = bdEmpRecInfo;
	}

    public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCustomerService() {
		return customerService;
	}

	public void setCustomerService(String customerService) {
		this.customerService = customerService;
	}

	public Integer getSbStatus() {
		return sbStatus;
	}

	public void setSbStatus(Integer sbStatus) {		
		
		this.sbStatus = sbStatus;
	}

	public Integer getGjjStatus() {
		return gjjStatus;
	}

	public void setGjjStatus(Integer gjjStatus) {
		this.gjjStatus = gjjStatus;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public String getServiceStatusShow() {
		if(this.getOrderStatus()!=null ){
			if(OrderStatus.ADD_CONFIRM.getValue().equals(this.getOrderStatus()) || OrderStatus.CHANGE_CONFIRM.getValue().equals(this.getOrderStatus())){
				return "服务中";
			}else if(OrderStatus.CANCEL_REDUCE.getValue().equals(this.getOrderStatus()) || OrderStatus.REDUCE_CONFIRM.getValue().equals(this.getOrderStatus())){
				return "已停止";
			}
			
		}	
		return serviceStatusShow;
	}

	public void setServiceStatusShow(String serviceStatusShow) {
		this.serviceStatusShow = serviceStatusShow;
	}

	public String getSbStatusShow() {
		if(this.getSbStatus()!=null ){
			if(SbStatus.NOT_APPLY.getValue().equals(this.getSbStatus()) ){
				return "未申请";
			}else if(SbStatus.APPLY.getValue().equals(this.getSbStatus())){
				return "已申请";
			}else if(SbStatus.PAY.getValue().equals(this.getSbStatus())){
				return "在缴";
			}else if(SbStatus.NOT_APPLY.getValue().equals(this.getSbStatus())){
				return "停缴";
			}else if(SbStatus.REJECT.getValue().equals(this.getSbStatus())){
				return "驳回";
			}
			
		}	
		return sbStatusShow;
	}

	public void setSbStatusShow(String sbStatusShow) {
		this.sbStatusShow = sbStatusShow;
	} 

	public String getGjjStatusShow() {
		if(this.getGjjStatus()!=null ){
			if(GjjStatus.NOT_APPLY.getValue().equals(this.getGjjStatus()) ){
				return "未申请";
			}else if(GjjStatus.APPLY.getValue().equals(this.getGjjStatus())){
				return "已申请";
			}else if(GjjStatus.PAY.getValue().equals(this.getGjjStatus())){
				return "在缴";
			}else if(GjjStatus.NOT_APPLY.getValue().equals(this.getGjjStatus())){
				return "停缴";
			}else if(GjjStatus.REJECT.getValue().equals(this.getGjjStatus())){
				return "驳回";
			}
			
		}	
		return gjjStatusShow;
	}

	public void setGjjStatusShow(String gjjStatusShow) {
		this.gjjStatusShow = gjjStatusShow;
	}

	public Date getServiceStartDate() {
		return this.getCreateDate();
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}

	public Date getServiceEndDate() {
		
		if(OrderStatus.CANCEL_REDUCE.getValue().equals(this.getOrderStatus()) || OrderStatus.REDUCE_CONFIRM.getValue().equals(this.getOrderStatus())){
			if( this.getUpdateDate()!=null ){
				return  this.getUpdateDate();	
			}
					
		}
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}

	public String getYearmonth() {
		return yearmonth;
	}

	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}

	public Long getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(Long serviceCount) {
		this.serviceCount = serviceCount;
	}

	public Long getAddCount() {
		return addCount;
	}

	public void setAddCount(Long addCount) {
		this.addCount = addCount;
	}

	public Long getDelCount() {
		return delCount;
	}

	public void setDelCount(Long delCount) {
		this.delCount = delCount;
	}

	public String getContractTypeShow() {
		if(this.getHsEmpOrdRec()!=null && this.getHsEmpOrdRec().getContractType()!=null && ContractType.ContractType_1.getValue().equals(this.getHsEmpOrdRec().getContractType())){
			return "派遣员工";					
		}else if(this.getHsEmpOrdRec()!=null && this.getHsEmpOrdRec().getContractType()!=null && ContractType.ContractType_2.getValue().equals(this.getHsEmpOrdRec().getContractType())){
			return "代理员工";
		}
		return contractTypeShow;
	}

	public void setContractTypeShow(String contractTypeShow) {
		this.contractTypeShow = contractTypeShow;
	}

	public Long getChangeCount() {
		return changeCount;
	}

	public void setChangeCount(Long changeCount) {
		this.changeCount = changeCount;
	}

	public List<Long> getIdList() {
		return idList;
	}

	public void setIdList(List<Long> idList) {
		this.idList = idList;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}
	
   }