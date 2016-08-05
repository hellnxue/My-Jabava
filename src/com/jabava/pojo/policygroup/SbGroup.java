package com.jabava.pojo.policygroup;

import java.util.Date;

public class SbGroup {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.sb_group_name
     *
     * @mbggenerated
     */
    private String sbGroupName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.org_id
     *
     * @mbggenerated
     */
    private Long orgId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.privately
     *
     * @mbggenerated
     */
    private Boolean privately;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.is_deleted
     *
     * @mbggenerated
     */
    private Integer isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.create_by
     *
     * @mbggenerated
     */
    private Long createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.create_dt
     *
     * @mbggenerated
     */
    private Date createDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.update_by
     *
     * @mbggenerated
     */
    private Long updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.update_dt
     *
     * @mbggenerated
     */
    private Date updateDt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.mimic_by
     *
     * @mbggenerated
     */
    private Long mimicBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sb_group.proxy_by
     *
     * @mbggenerated
     */
    private Long proxyBy;
    
    private String summary;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.id
     *
     * @return the value of sb_group.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.id
     *
     * @param id the value for sb_group.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.sb_group_name
     *
     * @return the value of sb_group.sb_group_name
     *
     * @mbggenerated
     */
    public String getSbGroupName() {
        return sbGroupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.sb_group_name
     *
     * @param sbGroupName the value for sb_group.sb_group_name
     *
     * @mbggenerated
     */
    public void setSbGroupName(String sbGroupName) {
        this.sbGroupName = sbGroupName == null ? null : sbGroupName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.org_id
     *
     * @return the value of sb_group.org_id
     *
     * @mbggenerated
     */
    public Long getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.org_id
     *
     * @param orgId the value for sb_group.org_id
     *
     * @mbggenerated
     */
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.privately
     *
     * @return the value of sb_group.privately
     *
     * @mbggenerated
     */
    public Boolean getPrivately() {
        return privately;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.privately
     *
     * @param privately the value for sb_group.privately
     *
     * @mbggenerated
     */
    public void setPrivately(Boolean privately) {
        this.privately = privately;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.is_deleted
     *
     * @return the value of sb_group.is_deleted
     *
     * @mbggenerated
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.is_deleted
     *
     * @param isDeleted the value for sb_group.is_deleted
     *
     * @mbggenerated
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.create_by
     *
     * @return the value of sb_group.create_by
     *
     * @mbggenerated
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.create_by
     *
     * @param createBy the value for sb_group.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.create_dt
     *
     * @return the value of sb_group.create_dt
     *
     * @mbggenerated
     */
    public Date getCreateDt() {
        return createDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.create_dt
     *
     * @param createDt the value for sb_group.create_dt
     *
     * @mbggenerated
     */
    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.update_by
     *
     * @return the value of sb_group.update_by
     *
     * @mbggenerated
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.update_by
     *
     * @param updateBy the value for sb_group.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.update_dt
     *
     * @return the value of sb_group.update_dt
     *
     * @mbggenerated
     */
    public Date getUpdateDt() {
        return updateDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.update_dt
     *
     * @param updateDt the value for sb_group.update_dt
     *
     * @mbggenerated
     */
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.mimic_by
     *
     * @return the value of sb_group.mimic_by
     *
     * @mbggenerated
     */
    public Long getMimicBy() {
        return mimicBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.mimic_by
     *
     * @param mimicBy the value for sb_group.mimic_by
     *
     * @mbggenerated
     */
    public void setMimicBy(Long mimicBy) {
        this.mimicBy = mimicBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sb_group.proxy_by
     *
     * @return the value of sb_group.proxy_by
     *
     * @mbggenerated
     */
    public Long getProxyBy() {
        return proxyBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sb_group.proxy_by
     *
     * @param proxyBy the value for sb_group.proxy_by
     *
     * @mbggenerated
     */
    public void setProxyBy(Long proxyBy) {
        this.proxyBy = proxyBy;
    }

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

    
}