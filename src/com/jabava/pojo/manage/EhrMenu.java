package com.jabava.pojo.manage;

import java.io.Serializable;
import java.util.Date;

public class EhrMenu implements Serializable {

	private static final long serialVersionUID = 856914820654774257L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.menu_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private Long menuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.menu_name
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private String menuName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.menu_type
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private Byte menuType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.menu_url
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private String menuUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.parent_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private Long parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.create_user_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.create_user_name
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private String createUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.create_date
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.last_modify_user_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private Long lastModifyUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.last_modify_user_name
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private String lastModifyUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.last_modify_date
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private Date lastModifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.IS_DELETED
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private Integer isDeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_menu.menu_seq
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    private Byte menuSeq;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.menu_id
     *
     * @return the value of ehr_menu.menu_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.menu_id
     *
     * @param menuId the value for ehr_menu.menu_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.menu_name
     *
     * @return the value of ehr_menu.menu_name
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.menu_name
     *
     * @param menuName the value for ehr_menu.menu_name
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.menu_type
     *
     * @return the value of ehr_menu.menu_type
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public Byte getMenuType() {
        return menuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.menu_type
     *
     * @param menuType the value for ehr_menu.menu_type
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setMenuType(Byte menuType) {
        this.menuType = menuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.menu_url
     *
     * @return the value of ehr_menu.menu_url
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.menu_url
     *
     * @param menuUrl the value for ehr_menu.menu_url
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.parent_id
     *
     * @return the value of ehr_menu.parent_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.parent_id
     *
     * @param parentId the value for ehr_menu.parent_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.create_user_id
     *
     * @return the value of ehr_menu.create_user_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.create_user_id
     *
     * @param createUserId the value for ehr_menu.create_user_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.create_user_name
     *
     * @return the value of ehr_menu.create_user_name
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.create_user_name
     *
     * @param createUserName the value for ehr_menu.create_user_name
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.create_date
     *
     * @return the value of ehr_menu.create_date
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.create_date
     *
     * @param createDate the value for ehr_menu.create_date
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.last_modify_user_id
     *
     * @return the value of ehr_menu.last_modify_user_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public Long getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.last_modify_user_id
     *
     * @param lastModifyUserId the value for ehr_menu.last_modify_user_id
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setLastModifyUserId(Long lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.last_modify_user_name
     *
     * @return the value of ehr_menu.last_modify_user_name
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.last_modify_user_name
     *
     * @param lastModifyUserName the value for ehr_menu.last_modify_user_name
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.last_modify_date
     *
     * @return the value of ehr_menu.last_modify_date
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.last_modify_date
     *
     * @param lastModifyDate the value for ehr_menu.last_modify_date
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.IS_DELETED
     *
     * @return the value of ehr_menu.IS_DELETED
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.IS_DELETED
     *
     * @param isDeleted the value for ehr_menu.IS_DELETED
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_menu.menu_seq
     *
     * @return the value of ehr_menu.menu_seq
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public Byte getMenuSeq() {
        return menuSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_menu.menu_seq
     *
     * @param menuSeq the value for ehr_menu.menu_seq
     *
     * @mbggenerated Wed Dec 16 12:00:52 CST 2015
     */
    public void setMenuSeq(Byte menuSeq) {
        this.menuSeq = menuSeq;
    }
}