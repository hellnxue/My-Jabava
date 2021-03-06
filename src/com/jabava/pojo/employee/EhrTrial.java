package com.jabava.pojo.employee;

import java.util.Date;

import com.jabava.utils.enums.JabavaEnum.PositiveType;

public class EhrTrial {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.trial_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Long trialId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.person_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Long personId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.trial_start_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Date trialStartDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.positive_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Date positiveDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.positive_type
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Integer positiveType;
    //显示 转正性质
    private String positiveTypeShow;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.evaluation_result
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String evaluationResult;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.memo
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.create_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.create_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String createUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.create_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.last_modify_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Long lastModifyUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.last_modify_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String lastModifyUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.last_modify_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Date lastModifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.trial_end_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Date trialEndDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.trial_month
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Byte trialMonth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.notice_number
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String noticeNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_trial.approval
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String approval;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.trial_id
     *
     * @return the value of ehr_trial.trial_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Long getTrialId() {
        return trialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.trial_id
     *
     * @param trialId the value for ehr_trial.trial_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setTrialId(Long trialId) {
        this.trialId = trialId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.person_id
     *
     * @return the value of ehr_trial.person_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.person_id
     *
     * @param personId the value for ehr_trial.person_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.trial_start_date
     *
     * @return the value of ehr_trial.trial_start_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Date getTrialStartDate() {
        return trialStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.trial_start_date
     *
     * @param trialStartDate the value for ehr_trial.trial_start_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setTrialStartDate(Date trialStartDate) {
        this.trialStartDate = trialStartDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.positive_date
     *
     * @return the value of ehr_trial.positive_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Date getPositiveDate() {
        return positiveDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.positive_date
     *
     * @param positiveDate the value for ehr_trial.positive_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setPositiveDate(Date positiveDate) {
        this.positiveDate = positiveDate;
    }

    public Integer getPositiveType() {
		return positiveType;
	}

	public void setPositiveType(Integer positiveType) {
		this.positiveType = positiveType;
	}

	public String getPositiveTypeShow() {
		if(this.getPositiveType()!=null){
			if(this.getPositiveType().equals(PositiveType.ADVANCE.getValue())){
				return "提前";
			}else if(this.getPositiveType().equals(PositiveType.ON_SCHEDULE.getValue())){
				return "按期";
			}else if(this.getPositiveType().equals(PositiveType.DELAY.getValue())){
				return "延期";
			}
			
		}
		return positiveTypeShow;
	}

	public void setPositiveTypeShow(String positiveTypeShow) {		
		this.positiveTypeShow = positiveTypeShow;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.evaluation_result
     *
     * @return the value of ehr_trial.evaluation_result
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getEvaluationResult() {
        return evaluationResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.evaluation_result
     *
     * @param evaluationResult the value for ehr_trial.evaluation_result
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setEvaluationResult(String evaluationResult) {
        this.evaluationResult = evaluationResult;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.memo
     *
     * @return the value of ehr_trial.memo
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.memo
     *
     * @param memo the value for ehr_trial.memo
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.create_user_id
     *
     * @return the value of ehr_trial.create_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.create_user_id
     *
     * @param createUserId the value for ehr_trial.create_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.create_user_name
     *
     * @return the value of ehr_trial.create_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.create_user_name
     *
     * @param createUserName the value for ehr_trial.create_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.create_date
     *
     * @return the value of ehr_trial.create_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.create_date
     *
     * @param createDate the value for ehr_trial.create_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.last_modify_user_id
     *
     * @return the value of ehr_trial.last_modify_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Long getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.last_modify_user_id
     *
     * @param lastModifyUserId the value for ehr_trial.last_modify_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setLastModifyUserId(Long lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.last_modify_user_name
     *
     * @return the value of ehr_trial.last_modify_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.last_modify_user_name
     *
     * @param lastModifyUserName the value for ehr_trial.last_modify_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.last_modify_date
     *
     * @return the value of ehr_trial.last_modify_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.last_modify_date
     *
     * @param lastModifyDate the value for ehr_trial.last_modify_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.trial_end_date
     *
     * @return the value of ehr_trial.trial_end_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Date getTrialEndDate() {
        return trialEndDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.trial_end_date
     *
     * @param trialEndDate the value for ehr_trial.trial_end_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setTrialEndDate(Date trialEndDate) {
        this.trialEndDate = trialEndDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.trial_month
     *
     * @return the value of ehr_trial.trial_month
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Byte getTrialMonth() {
        return trialMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.trial_month
     *
     * @param trialMonth the value for ehr_trial.trial_month
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setTrialMonth(Byte trialMonth) {
        this.trialMonth = trialMonth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.notice_number
     *
     * @return the value of ehr_trial.notice_number
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getNoticeNumber() {
        return noticeNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.notice_number
     *
     * @param noticeNumber the value for ehr_trial.notice_number
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setNoticeNumber(String noticeNumber) {
        this.noticeNumber = noticeNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_trial.approval
     *
     * @return the value of ehr_trial.approval
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getApproval() {
        return approval;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_trial.approval
     *
     * @param approval the value for ehr_trial.approval
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setApproval(String approval) {
        this.approval = approval;
    }
}