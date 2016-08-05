package com.jabava.pojo.employee;

import java.util.Date;

public class EhrJobpost {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.post_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Long postId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.person_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Long personId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.source_department
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String sourceDepartment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.source_post
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String sourcePost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.source_rank
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String sourceRank;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.source_report
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String sourceReport;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.source_location
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String sourceLocation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.source_long
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String sourceLong;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.source_cost
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String sourceCost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.new_department
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String newDepartment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.new_post
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String newPost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.new_rank
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String newRank;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.new_report
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String newReport;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.new_location
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String newLocation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.new_cost
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String newCost;
    
    
    private Date recordStartDate;
    
    private Date recordEndDate;
    
    private String changeType;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.cause
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String cause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.new_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Date newDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.is_change_salary
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Byte isChangeSalary;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.create_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.create_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String createUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.create_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.last_modify_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Long lastModifyUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.last_modify_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String lastModifyUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.last_modify_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Date lastModifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.memo
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_jobpost.is_change
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    private Byte isChange;
    
    private Integer isDeleted;

    public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.post_id
     *
     * @return the value of ehr_jobpost.post_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.post_id
     *
     * @param postId the value for ehr_jobpost.post_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.person_id
     *
     * @return the value of ehr_jobpost.person_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.person_id
     *
     * @param personId the value for ehr_jobpost.person_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.source_department
     *
     * @return the value of ehr_jobpost.source_department
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getSourceDepartment() {
        return sourceDepartment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.source_department
     *
     * @param sourceDepartment the value for ehr_jobpost.source_department
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setSourceDepartment(String sourceDepartment) {
        this.sourceDepartment = sourceDepartment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.source_post
     *
     * @return the value of ehr_jobpost.source_post
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getSourcePost() {
        return sourcePost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.source_post
     *
     * @param sourcePost the value for ehr_jobpost.source_post
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setSourcePost(String sourcePost) {
        this.sourcePost = sourcePost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.source_rank
     *
     * @return the value of ehr_jobpost.source_rank
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getSourceRank() {
        return sourceRank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.source_rank
     *
     * @param sourceRank the value for ehr_jobpost.source_rank
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setSourceRank(String sourceRank) {
        this.sourceRank = sourceRank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.source_report
     *
     * @return the value of ehr_jobpost.source_report
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getSourceReport() {
        return sourceReport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.source_report
     *
     * @param sourceReport the value for ehr_jobpost.source_report
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setSourceReport(String sourceReport) {
        this.sourceReport = sourceReport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.source_location
     *
     * @return the value of ehr_jobpost.source_location
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getSourceLocation() {
        return sourceLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.source_location
     *
     * @param sourceLocation the value for ehr_jobpost.source_location
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.source_long
     *
     * @return the value of ehr_jobpost.source_long
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getSourceLong() {
        return sourceLong;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.source_long
     *
     * @param sourceLong the value for ehr_jobpost.source_long
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setSourceLong(String sourceLong) {
        this.sourceLong = sourceLong;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.source_cost
     *
     * @return the value of ehr_jobpost.source_cost
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getSourceCost() {
        return sourceCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.source_cost
     *
     * @param sourceCost the value for ehr_jobpost.source_cost
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setSourceCost(String sourceCost) {
        this.sourceCost = sourceCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.new_department
     *
     * @return the value of ehr_jobpost.new_department
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getNewDepartment() {
        return newDepartment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.new_department
     *
     * @param newDepartment the value for ehr_jobpost.new_department
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setNewDepartment(String newDepartment) {
        this.newDepartment = newDepartment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.new_post
     *
     * @return the value of ehr_jobpost.new_post
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getNewPost() {
        return newPost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.new_post
     *
     * @param newPost the value for ehr_jobpost.new_post
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setNewPost(String newPost) {
        this.newPost = newPost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.new_rank
     *
     * @return the value of ehr_jobpost.new_rank
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getNewRank() {
        return newRank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.new_rank
     *
     * @param newRank the value for ehr_jobpost.new_rank
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setNewRank(String newRank) {
        this.newRank = newRank;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.new_report
     *
     * @return the value of ehr_jobpost.new_report
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getNewReport() {
        return newReport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.new_report
     *
     * @param newReport the value for ehr_jobpost.new_report
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setNewReport(String newReport) {
        this.newReport = newReport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.new_location
     *
     * @return the value of ehr_jobpost.new_location
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getNewLocation() {
        return newLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.new_location
     *
     * @param newLocation the value for ehr_jobpost.new_location
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setNewLocation(String newLocation) {
        this.newLocation = newLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.new_cost
     *
     * @return the value of ehr_jobpost.new_cost
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getNewCost() {
        return newCost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.new_cost
     *
     * @param newCost the value for ehr_jobpost.new_cost
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setNewCost(String newCost) {
        this.newCost = newCost;
    }

    
    public Date getRecordStartDate() {
		return recordStartDate;
	}

	public void setRecordStartDate(Date recordStartDate) {
		this.recordStartDate = recordStartDate;
	}

	public Date getRecordEndDate() {
		return recordEndDate;
	}

	public void setRecordEndDate(Date recordEndDate) {
		this.recordEndDate = recordEndDate;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.cause
     *
     * @return the value of ehr_jobpost.cause
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getCause() {
        return cause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.cause
     *
     * @param cause the value for ehr_jobpost.cause
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setCause(String cause) {
        this.cause = cause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.new_date
     *
     * @return the value of ehr_jobpost.new_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Date getNewDate() {
        return newDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.new_date
     *
     * @param newDate the value for ehr_jobpost.new_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.is_change_salary
     *
     * @return the value of ehr_jobpost.is_change_salary
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Byte getIsChangeSalary() {
        return isChangeSalary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.is_change_salary
     *
     * @param isChangeSalary the value for ehr_jobpost.is_change_salary
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setIsChangeSalary(Byte isChangeSalary) {
        this.isChangeSalary = isChangeSalary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.create_user_id
     *
     * @return the value of ehr_jobpost.create_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.create_user_id
     *
     * @param createUserId the value for ehr_jobpost.create_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.create_user_name
     *
     * @return the value of ehr_jobpost.create_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.create_user_name
     *
     * @param createUserName the value for ehr_jobpost.create_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.create_date
     *
     * @return the value of ehr_jobpost.create_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.create_date
     *
     * @param createDate the value for ehr_jobpost.create_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.last_modify_user_id
     *
     * @return the value of ehr_jobpost.last_modify_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Long getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.last_modify_user_id
     *
     * @param lastModifyUserId the value for ehr_jobpost.last_modify_user_id
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setLastModifyUserId(Long lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.last_modify_user_name
     *
     * @return the value of ehr_jobpost.last_modify_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.last_modify_user_name
     *
     * @param lastModifyUserName the value for ehr_jobpost.last_modify_user_name
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.last_modify_date
     *
     * @return the value of ehr_jobpost.last_modify_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.last_modify_date
     *
     * @param lastModifyDate the value for ehr_jobpost.last_modify_date
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.memo
     *
     * @return the value of ehr_jobpost.memo
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.memo
     *
     * @param memo the value for ehr_jobpost.memo
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_jobpost.is_change
     *
     * @return the value of ehr_jobpost.is_change
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public Byte getIsChange() {
        return isChange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_jobpost.is_change
     *
     * @param isChange the value for ehr_jobpost.is_change
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    public void setIsChange(Byte isChange) {
        this.isChange = isChange;
    }
}