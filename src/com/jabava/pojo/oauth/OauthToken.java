package com.jabava.pojo.oauth;

import java.util.Date;

public class OauthToken {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.id
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.access_token
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private String accessToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.expire_in
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private Integer expireIn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.refresh_token
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private String refreshToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.token_type
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private String tokenType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.username
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.oauth_client_id
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private Long oauthClientId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.create_date
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.create_user
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.update_date
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.update_user
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private String updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_oauth_token.is_deleted
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    private Integer isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.id
     *
     * @return the value of ehr_oauth_token.id
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.id
     *
     * @param id the value for ehr_oauth_token.id
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.access_token
     *
     * @return the value of ehr_oauth_token.access_token
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.access_token
     *
     * @param accessToken the value for ehr_oauth_token.access_token
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.expire_in
     *
     * @return the value of ehr_oauth_token.expire_in
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public Integer getExpireIn() {
        return expireIn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.expire_in
     *
     * @param expireIn the value for ehr_oauth_token.expire_in
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setExpireIn(Integer expireIn) {
        this.expireIn = expireIn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.refresh_token
     *
     * @return the value of ehr_oauth_token.refresh_token
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.refresh_token
     *
     * @param refreshToken the value for ehr_oauth_token.refresh_token
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.token_type
     *
     * @return the value of ehr_oauth_token.token_type
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.token_type
     *
     * @param tokenType the value for ehr_oauth_token.token_type
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.username
     *
     * @return the value of ehr_oauth_token.username
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.username
     *
     * @param username the value for ehr_oauth_token.username
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.oauth_client_id
     *
     * @return the value of ehr_oauth_token.oauth_client_id
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public Long getOauthClientId() {
        return oauthClientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.oauth_client_id
     *
     * @param oauthClientId the value for ehr_oauth_token.oauth_client_id
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setOauthClientId(Long oauthClientId) {
        this.oauthClientId = oauthClientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.create_date
     *
     * @return the value of ehr_oauth_token.create_date
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.create_date
     *
     * @param createDate the value for ehr_oauth_token.create_date
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.create_user
     *
     * @return the value of ehr_oauth_token.create_user
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.create_user
     *
     * @param createUser the value for ehr_oauth_token.create_user
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.update_date
     *
     * @return the value of ehr_oauth_token.update_date
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.update_date
     *
     * @param updateDate the value for ehr_oauth_token.update_date
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.update_user
     *
     * @return the value of ehr_oauth_token.update_user
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.update_user
     *
     * @param updateUser the value for ehr_oauth_token.update_user
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_oauth_token.is_deleted
     *
     * @return the value of ehr_oauth_token.is_deleted
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_oauth_token.is_deleted
     *
     * @param isDeleted the value for ehr_oauth_token.is_deleted
     *
     * @mbggenerated Fri Mar 04 17:20:50 CST 2016
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}