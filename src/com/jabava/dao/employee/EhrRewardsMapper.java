package com.jabava.dao.employee;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jabava.pojo.employee.EhrRewards;

public interface EhrRewardsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_rewards
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    int deleteByPrimaryKey(Long rewardId);
    /**
     * 根据ID更新is_deleted=1
     * <pre>
     * @author steven.chen
     * @date 2016年3月24日 下午3:37:58 
     * </pre>
     *
     * @param rewardId
     * @return
     */
    int updateByrewardId(Long rewardId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_rewards
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    int insert(EhrRewards record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_rewards
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    int insertSelective(EhrRewards record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_rewards
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    EhrRewards selectByPrimaryKey(Long rewardId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_rewards
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    int updateByPrimaryKeySelective(EhrRewards record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_rewards
     *
     * @mbggenerated Wed Jan 13 19:57:44 CST 2016
     */
    int updateByPrimaryKey(EhrRewards record);
    
    List<EhrRewards> getByPersonId(@Param("personId") Long personId);
}