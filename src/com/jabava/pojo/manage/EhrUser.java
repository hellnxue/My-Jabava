package com.jabava.pojo.manage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EhrUser implements Serializable{

	private static final long serialVersionUID = 1169653884352625774L;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.user_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Long userId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.user_code
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private String userCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.password
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private String password;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.user_name
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private String userName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.sex
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Integer sex;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.telephone
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private String telephone;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.mobile
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private String mobile;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.mail_address
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private String mailAddress;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.memo
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private String memo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.is_valid
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Byte isValid;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.is_locked
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Byte isLocked;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.last_change_password_date
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Date lastChangePasswordDate;
	
	private Date lastLoginDate;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.failture_time
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Integer failtureTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.login_time
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Integer loginTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.user_type
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Byte userType;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.company_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Long companyId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.create_user_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Long createUserId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.create_user_name
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private String createUserName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.create_date
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Date createDate;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.last_modify_user_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Long lastModifyUserId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.last_modify_date
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private Date lastModifyDate;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.last_modify_user_name
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private String lastModifyUserName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column ehr_user.user_distinguish
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	private String userDistinguish;

	private EhrCompany company;

	private String companyCode;

	private String companyName;

	private String modifyDate;

	private String valid;

	private String locked;

	private String utype;

	private int isDeleted;

	private int flag;

	private Map<String, EhrButton> buttonMap = new HashMap<String, EhrButton>();
	
	private List<EhrMenu> menuList = new ArrayList<EhrMenu>();

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getUtype() {
		return utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.user_id
	 * 
	 * @return the value of ehr_user.user_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.user_id
	 * 
	 * @param userId
	 *            the value for ehr_user.user_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.user_code
	 * 
	 * @return the value of ehr_user.user_code
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.user_code
	 * 
	 * @param userCode
	 *            the value for ehr_user.user_code
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.password
	 * 
	 * @return the value of ehr_user.password
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.password
	 * 
	 * @param password
	 *            the value for ehr_user.password
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.user_name
	 * 
	 * @return the value of ehr_user.user_name
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.user_name
	 * 
	 * @param userName
	 *            the value for ehr_user.user_name
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.sex
	 * 
	 * @return the value of ehr_user.sex
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.sex
	 * 
	 * @param sex
	 *            the value for ehr_user.sex
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.telephone
	 * 
	 * @return the value of ehr_user.telephone
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.telephone
	 * 
	 * @param telephone
	 *            the value for ehr_user.telephone
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.mobile
	 * 
	 * @return the value of ehr_user.mobile
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.mobile
	 * 
	 * @param mobile
	 *            the value for ehr_user.mobile
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.mail_address
	 * 
	 * @return the value of ehr_user.mail_address
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.mail_address
	 * 
	 * @param mailAddress
	 *            the value for ehr_user.mail_address
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.memo
	 * 
	 * @return the value of ehr_user.memo
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.memo
	 * 
	 * @param memo
	 *            the value for ehr_user.memo
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.is_valid
	 * 
	 * @return the value of ehr_user.is_valid
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Byte getIsValid() {
		return isValid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.is_valid
	 * 
	 * @param isValid
	 *            the value for ehr_user.is_valid
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setIsValid(Byte isValid) {
		this.isValid = isValid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.is_locked
	 * 
	 * @return the value of ehr_user.is_locked
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Byte getIsLocked() {
		return isLocked;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.is_locked
	 * 
	 * @param isLocked
	 *            the value for ehr_user.is_locked
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setIsLocked(Byte isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.last_change_password_date
	 * 
	 * @return the value of ehr_user.last_change_password_date
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Date getLastChangePasswordDate() {
		return lastChangePasswordDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.last_change_password_date
	 * 
	 * @param lastChangePasswordDate
	 *            the value for ehr_user.last_change_password_date
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setLastChangePasswordDate(Date lastChangePasswordDate) {
		this.lastChangePasswordDate = lastChangePasswordDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.failture_time
	 * 
	 * @return the value of ehr_user.failture_time
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Integer getFailtureTime() {
		return failtureTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.failture_time
	 * 
	 * @param failtureTime
	 *            the value for ehr_user.failture_time
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setFailtureTime(Integer failtureTime) {
		this.failtureTime = failtureTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.login_time
	 * 
	 * @return the value of ehr_user.login_time
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Integer getLoginTime() {
		return loginTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.login_time
	 * 
	 * @param loginTime
	 *            the value for ehr_user.login_time
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setLoginTime(Integer loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.user_type
	 * 
	 * @return the value of ehr_user.user_type
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Byte getUserType() {
		return userType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.user_type
	 * 
	 * @param userType
	 *            the value for ehr_user.user_type
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.company_id
	 * 
	 * @return the value of ehr_user.company_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.company_id
	 * 
	 * @param companyId
	 *            the value for ehr_user.company_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.create_user_id
	 * 
	 * @return the value of ehr_user.create_user_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.create_user_id
	 * 
	 * @param createUserId
	 *            the value for ehr_user.create_user_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.create_user_name
	 * 
	 * @return the value of ehr_user.create_user_name
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.create_user_name
	 * 
	 * @param createUserName
	 *            the value for ehr_user.create_user_name
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.create_date
	 * 
	 * @return the value of ehr_user.create_date
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.create_date
	 * 
	 * @param createDate
	 *            the value for ehr_user.create_date
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.last_modify_user_id
	 * 
	 * @return the value of ehr_user.last_modify_user_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Long getLastModifyUserId() {
		return lastModifyUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.last_modify_user_id
	 * 
	 * @param lastModifyUserId
	 *            the value for ehr_user.last_modify_user_id
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setLastModifyUserId(Long lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.last_modify_date
	 * 
	 * @return the value of ehr_user.last_modify_date
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.last_modify_date
	 * 
	 * @param lastModifyDate
	 *            the value for ehr_user.last_modify_date
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.last_modify_user_name
	 * 
	 * @return the value of ehr_user.last_modify_user_name
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public String getLastModifyUserName() {
		return lastModifyUserName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.last_modify_user_name
	 * 
	 * @param lastModifyUserName
	 *            the value for ehr_user.last_modify_user_name
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setLastModifyUserName(String lastModifyUserName) {
		this.lastModifyUserName = lastModifyUserName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column ehr_user.user_distinguish
	 * 
	 * @return the value of ehr_user.user_distinguish
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public String getUserDistinguish() {
		return userDistinguish;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column ehr_user.user_distinguish
	 * 
	 * @param userDistinguish
	 *            the value for ehr_user.user_distinguish
	 * 
	 * @mbggenerated Wed Dec 16 12:00:52 CST 2015
	 */
	public void setUserDistinguish(String userDistinguish) {
		this.userDistinguish = userDistinguish;
	}

	public EhrCompany getCompany() {
		return company;
	}

	public void setCompany(EhrCompany company) {
		this.company = company;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Map<String, EhrButton> getButtonMap() {
		return buttonMap;
	}

	public void setButtonMap(Map<String, EhrButton> buttonMap) {
		this.buttonMap = buttonMap;
	}

	public List<EhrMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<EhrMenu> menuList) {
		this.menuList = menuList;
	}

	public static String getColumnName(String order) {
		if (order != null) {
			if (order.equals("userCode")) {
				return "user_code";
			} else if ("userName".equals(order)) {
				return "user_name";
			} else if ("mailAddress".equals(order)) {
				return "mail_address";
			} else if ("lastModifyUserName".equals(order)) {
				return "last_modify_user_name";
			} else if ("modifyDate".equals(order)) {
				return "last_modify_date";
			} else if ("valid".equals(order)) {
				return "is_valid";
			} else if ("locked".equals(order)) {
				return "is_locked";
			} else if ("utype".equals(order)) {
				return "user_type";
			}
		}
		return "user_code";
	}
}