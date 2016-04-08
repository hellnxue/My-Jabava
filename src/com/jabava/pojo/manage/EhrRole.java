package com.jabava.pojo.manage;

import java.util.Date;

public class EhrRole implements java.io.Serializable{
    /**  */
	private static final long serialVersionUID = 5368138880119107004L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_role.role_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    private Long roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_role.role_name
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_role.memo
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_role.create_user_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_role.create_user_name
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    private String createUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_role.create_date
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_role.last_modify_user_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    private Long lastModifyUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_role.last_modify_user_name
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    private String lastModifyUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_role.last_modify_date
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    private String lastModifyDate1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_role.company_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    private Long companyId;
    
    private Date lastModifyDate;
    
    private Integer start;
    
    private Integer length;

    public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_role.role_id
     *
     * @return the value of ehr_role.role_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    
    public Long getRoleId() {
        return roleId;
    }

    public String getLastModifyDate1() {
		return lastModifyDate1;
	}

	public void setLastModifyDate1(String lastModifyDate1) {
		this.lastModifyDate1 = lastModifyDate1;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_role.role_id
     *
     * @param roleId the value for ehr_role.role_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_role.role_name
     *
     * @return the value of ehr_role.role_name
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_role.role_name
     *
     * @param roleName the value for ehr_role.role_name
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_role.memo
     *
     * @return the value of ehr_role.memo
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_role.memo
     *
     * @param memo the value for ehr_role.memo
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_role.create_user_id
     *
     * @return the value of ehr_role.create_user_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_role.create_user_id
     *
     * @param createUserId the value for ehr_role.create_user_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_role.create_user_name
     *
     * @return the value of ehr_role.create_user_name
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_role.create_user_name
     *
     * @param createUserName the value for ehr_role.create_user_name
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_role.create_date
     *
     * @return the value of ehr_role.create_date
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_role.create_date
     *
     * @param createDate the value for ehr_role.create_date
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_role.last_modify_user_id
     *
     * @return the value of ehr_role.last_modify_user_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public Long getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_role.last_modify_user_id
     *
     * @param lastModifyUserId the value for ehr_role.last_modify_user_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public void setLastModifyUserId(Long lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_role.last_modify_user_name
     *
     * @return the value of ehr_role.last_modify_user_name
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_role.last_modify_user_name
     *
     * @param lastModifyUserName the value for ehr_role.last_modify_user_name
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_role.last_modify_date
     *
     * @return the value of ehr_role.last_modify_date
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_role.last_modify_date
     *
     * @param lastModifyDate the value for ehr_role.last_modify_date
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_role.company_id
     *
     * @return the value of ehr_role.company_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_role.company_id
     *
     * @param companyId the value for ehr_role.company_id
     *
     * @mbggenerated Wed Dec 23 14:21:27 CST 2015
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public static String getColumnName(String colmun) {
		if ("roleId".equals(colmun)) {
			return "role_id";
		} else if ("roleName".equals(colmun)) {
			return "role_name";
		} else if ("memo".equals(colmun)) {
			return "memo";
		}  else if ("lastModifyDate".equals(colmun) || "lastModifyDate1".equals(colmun)) {
			return "last_modify_date";
		}else if ("lastModifyUserName".equals(colmun)) {
			return "last_modify_user_name";
		}
		return "role_name";
	}

}