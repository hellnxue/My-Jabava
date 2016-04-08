package com.jabava.pojo.employee;

import java.util.Date;

public class EhrEducation {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.education_id
     *
     * @mbggenerated
     */
    private Long educationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.person_id
     *
     * @mbggenerated
     */
    private Long personId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.education
     *
     * @mbggenerated
     */
    private String education;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.learn_type
     *
     * @mbggenerated
     */
    private String learnType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.entrance_date
     *
     * @mbggenerated
     */
    private Date entranceDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.graduate_date
     *
     * @mbggenerated
     */
    private Date graduateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.major
     *
     * @mbggenerated
     */
    private String major;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.graduate_school
     *
     * @mbggenerated
     */
    private String graduateSchool;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.school_time
     *
     * @mbggenerated
     */
    private Long schoolTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.education_certificate
     *
     * @mbggenerated
     */
    private String educationCertificate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.create_user_id
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.create_user_name
     *
     * @mbggenerated
     */
    private String createUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.last_modify_user_id
     *
     * @mbggenerated
     */
    private Long lastModifyUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.last_modify_user_name
     *
     * @mbggenerated
     */
    private String lastModifyUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.last_modify_date
     *
     * @mbggenerated
     */
    private Date lastModifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.education_type
     *
     * @mbggenerated
     */
    private String educationType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.degree
     *
     * @mbggenerated
     */
    private String degree;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.degree_date
     *
     * @mbggenerated
     */
    private Date degreeDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.degree_shcool
     *
     * @mbggenerated
     */
    private String degreeShcool;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.degree_country
     *
     * @mbggenerated
     */
    private String degreeCountry;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_education.is_delete
     *
     * @mbggenerated
     */
    private Integer isDelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.education_id
     *
     * @return the value of ehr_education.education_id
     *
     * @mbggenerated
     */
    public Long getEducationId() {
        return educationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.education_id
     *
     * @param educationId the value for ehr_education.education_id
     *
     * @mbggenerated
     */
    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.person_id
     *
     * @return the value of ehr_education.person_id
     *
     * @mbggenerated
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.person_id
     *
     * @param personId the value for ehr_education.person_id
     *
     * @mbggenerated
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.education
     *
     * @return the value of ehr_education.education
     *
     * @mbggenerated
     */
    public String getEducation() {
        return education;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.education
     *
     * @param education the value for ehr_education.education
     *
     * @mbggenerated
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.learn_type
     *
     * @return the value of ehr_education.learn_type
     *
     * @mbggenerated
     */
    public String getLearnType() {
        return learnType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.learn_type
     *
     * @param learnType the value for ehr_education.learn_type
     *
     * @mbggenerated
     */
    public void setLearnType(String learnType) {
        this.learnType = learnType == null ? null : learnType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.entrance_date
     *
     * @return the value of ehr_education.entrance_date
     *
     * @mbggenerated
     */
    public Date getEntranceDate() {
        return entranceDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.entrance_date
     *
     * @param entranceDate the value for ehr_education.entrance_date
     *
     * @mbggenerated
     */
    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.graduate_date
     *
     * @return the value of ehr_education.graduate_date
     *
     * @mbggenerated
     */
    public Date getGraduateDate() {
        return graduateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.graduate_date
     *
     * @param graduateDate the value for ehr_education.graduate_date
     *
     * @mbggenerated
     */
    public void setGraduateDate(Date graduateDate) {
        this.graduateDate = graduateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.major
     *
     * @return the value of ehr_education.major
     *
     * @mbggenerated
     */
    public String getMajor() {
        return major;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.major
     *
     * @param major the value for ehr_education.major
     *
     * @mbggenerated
     */
    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.graduate_school
     *
     * @return the value of ehr_education.graduate_school
     *
     * @mbggenerated
     */
    public String getGraduateSchool() {
        return graduateSchool;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.graduate_school
     *
     * @param graduateSchool the value for ehr_education.graduate_school
     *
     * @mbggenerated
     */
    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool == null ? null : graduateSchool.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.school_time
     *
     * @return the value of ehr_education.school_time
     *
     * @mbggenerated
     */
    public Long getSchoolTime() {
        return schoolTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.school_time
     *
     * @param schoolTime the value for ehr_education.school_time
     *
     * @mbggenerated
     */
    public void setSchoolTime(Long schoolTime) {
        this.schoolTime = schoolTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.education_certificate
     *
     * @return the value of ehr_education.education_certificate
     *
     * @mbggenerated
     */
    public String getEducationCertificate() {
        return educationCertificate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.education_certificate
     *
     * @param educationCertificate the value for ehr_education.education_certificate
     *
     * @mbggenerated
     */
    public void setEducationCertificate(String educationCertificate) {
        this.educationCertificate = educationCertificate == null ? null : educationCertificate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.create_user_id
     *
     * @return the value of ehr_education.create_user_id
     *
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.create_user_id
     *
     * @param createUserId the value for ehr_education.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.create_user_name
     *
     * @return the value of ehr_education.create_user_name
     *
     * @mbggenerated
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.create_user_name
     *
     * @param createUserName the value for ehr_education.create_user_name
     *
     * @mbggenerated
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.create_date
     *
     * @return the value of ehr_education.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.create_date
     *
     * @param createDate the value for ehr_education.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.last_modify_user_id
     *
     * @return the value of ehr_education.last_modify_user_id
     *
     * @mbggenerated
     */
    public Long getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.last_modify_user_id
     *
     * @param lastModifyUserId the value for ehr_education.last_modify_user_id
     *
     * @mbggenerated
     */
    public void setLastModifyUserId(Long lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.last_modify_user_name
     *
     * @return the value of ehr_education.last_modify_user_name
     *
     * @mbggenerated
     */
    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.last_modify_user_name
     *
     * @param lastModifyUserName the value for ehr_education.last_modify_user_name
     *
     * @mbggenerated
     */
    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.last_modify_date
     *
     * @return the value of ehr_education.last_modify_date
     *
     * @mbggenerated
     */
    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.last_modify_date
     *
     * @param lastModifyDate the value for ehr_education.last_modify_date
     *
     * @mbggenerated
     */
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.education_type
     *
     * @return the value of ehr_education.education_type
     *
     * @mbggenerated
     */
    public String getEducationType() {
        return educationType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.education_type
     *
     * @param educationType the value for ehr_education.education_type
     *
     * @mbggenerated
     */
    public void setEducationType(String educationType) {
        this.educationType = educationType == null ? null : educationType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.degree
     *
     * @return the value of ehr_education.degree
     *
     * @mbggenerated
     */
    public String getDegree() {
        return degree;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.degree
     *
     * @param degree the value for ehr_education.degree
     *
     * @mbggenerated
     */
    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.degree_date
     *
     * @return the value of ehr_education.degree_date
     *
     * @mbggenerated
     */
    public Date getDegreeDate() {
        return degreeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.degree_date
     *
     * @param degreeDate the value for ehr_education.degree_date
     *
     * @mbggenerated
     */
    public void setDegreeDate(Date degreeDate) {
        this.degreeDate = degreeDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.degree_shcool
     *
     * @return the value of ehr_education.degree_shcool
     *
     * @mbggenerated
     */
    public String getDegreeShcool() {
        return degreeShcool;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.degree_shcool
     *
     * @param degreeShcool the value for ehr_education.degree_shcool
     *
     * @mbggenerated
     */
    public void setDegreeShcool(String degreeShcool) {
        this.degreeShcool = degreeShcool == null ? null : degreeShcool.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.degree_country
     *
     * @return the value of ehr_education.degree_country
     *
     * @mbggenerated
     */
    public String getDegreeCountry() {
        return degreeCountry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.degree_country
     *
     * @param degreeCountry the value for ehr_education.degree_country
     *
     * @mbggenerated
     */
    public void setDegreeCountry(String degreeCountry) {
        this.degreeCountry = degreeCountry == null ? null : degreeCountry.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_education.is_delete
     *
     * @return the value of ehr_education.is_delete
     *
     * @mbggenerated
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_education.is_delete
     *
     * @param isDelete the value for ehr_education.is_delete
     *
     * @mbggenerated
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}