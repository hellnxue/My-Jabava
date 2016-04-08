package com.jabava.pojo.employee;

import java.util.Date;

public class EhrExperience {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.experience_id
     *
     * @mbggenerated
     */
    private Long experienceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.person_id
     *
     * @mbggenerated
     */
    private Long personId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.employer
     *
     * @mbggenerated
     */
    private String employer;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.work_Post
     *
     * @mbggenerated
     */
    private String workPost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.start_date
     *
     * @mbggenerated
     */
    private Date startDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.end_date
     *
     * @mbggenerated
     */
    private Date endDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.authenticator
     *
     * @mbggenerated
     */
    private String authenticator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.create_user_id
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.create_user_name
     *
     * @mbggenerated
     */
    private String createUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.last_modify_user_id
     *
     * @mbggenerated
     */
    private Long lastModifyUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.last_modify_user_name
     *
     * @mbggenerated
     */
    private String lastModifyUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.last_modify_date
     *
     * @mbggenerated
     */
    private Date lastModifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_experience.is_delete
     *
     * @mbggenerated
     */
    private Integer isDelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.experience_id
     *
     * @return the value of ehr_experience.experience_id
     *
     * @mbggenerated
     */
    public Long getExperienceId() {
        return experienceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.experience_id
     *
     * @param experienceId the value for ehr_experience.experience_id
     *
     * @mbggenerated
     */
    public void setExperienceId(Long experienceId) {
        this.experienceId = experienceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.person_id
     *
     * @return the value of ehr_experience.person_id
     *
     * @mbggenerated
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.person_id
     *
     * @param personId the value for ehr_experience.person_id
     *
     * @mbggenerated
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.employer
     *
     * @return the value of ehr_experience.employer
     *
     * @mbggenerated
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.employer
     *
     * @param employer the value for ehr_experience.employer
     *
     * @mbggenerated
     */
    public void setEmployer(String employer) {
        this.employer = employer == null ? null : employer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.work_Post
     *
     * @return the value of ehr_experience.work_Post
     *
     * @mbggenerated
     */
    public String getWorkPost() {
        return workPost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.work_Post
     *
     * @param workPost the value for ehr_experience.work_Post
     *
     * @mbggenerated
     */
    public void setWorkPost(String workPost) {
        this.workPost = workPost == null ? null : workPost.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.start_date
     *
     * @return the value of ehr_experience.start_date
     *
     * @mbggenerated
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.start_date
     *
     * @param startDate the value for ehr_experience.start_date
     *
     * @mbggenerated
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.end_date
     *
     * @return the value of ehr_experience.end_date
     *
     * @mbggenerated
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.end_date
     *
     * @param endDate the value for ehr_experience.end_date
     *
     * @mbggenerated
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.authenticator
     *
     * @return the value of ehr_experience.authenticator
     *
     * @mbggenerated
     */
    public String getAuthenticator() {
        return authenticator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.authenticator
     *
     * @param authenticator the value for ehr_experience.authenticator
     *
     * @mbggenerated
     */
    public void setAuthenticator(String authenticator) {
        this.authenticator = authenticator == null ? null : authenticator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.description
     *
     * @return the value of ehr_experience.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.description
     *
     * @param description the value for ehr_experience.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.create_user_id
     *
     * @return the value of ehr_experience.create_user_id
     *
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.create_user_id
     *
     * @param createUserId the value for ehr_experience.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.create_user_name
     *
     * @return the value of ehr_experience.create_user_name
     *
     * @mbggenerated
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.create_user_name
     *
     * @param createUserName the value for ehr_experience.create_user_name
     *
     * @mbggenerated
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.create_date
     *
     * @return the value of ehr_experience.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.create_date
     *
     * @param createDate the value for ehr_experience.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.last_modify_user_id
     *
     * @return the value of ehr_experience.last_modify_user_id
     *
     * @mbggenerated
     */
    public Long getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.last_modify_user_id
     *
     * @param lastModifyUserId the value for ehr_experience.last_modify_user_id
     *
     * @mbggenerated
     */
    public void setLastModifyUserId(Long lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.last_modify_user_name
     *
     * @return the value of ehr_experience.last_modify_user_name
     *
     * @mbggenerated
     */
    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.last_modify_user_name
     *
     * @param lastModifyUserName the value for ehr_experience.last_modify_user_name
     *
     * @mbggenerated
     */
    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.last_modify_date
     *
     * @return the value of ehr_experience.last_modify_date
     *
     * @mbggenerated
     */
    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.last_modify_date
     *
     * @param lastModifyDate the value for ehr_experience.last_modify_date
     *
     * @mbggenerated
     */
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_experience.is_delete
     *
     * @return the value of ehr_experience.is_delete
     *
     * @mbggenerated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_experience.is_delete
     *
     * @param isDelete the value for ehr_experience.is_delete
     *
     * @mbggenerated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}