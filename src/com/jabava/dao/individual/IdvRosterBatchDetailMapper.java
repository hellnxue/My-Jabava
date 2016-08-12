package com.jabava.dao.individual;

import java.util.*;

import com.jabava.pojo.individual.IdvRosterBatchDetail;

public interface IdvRosterBatchDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table idv_roster_batch_detail
     *
     * @mbggenerated Wed Aug 10 16:06:11 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table idv_roster_batch_detail
     *
     * @mbggenerated Wed Aug 10 16:06:11 CST 2016
     */
    int insert(IdvRosterBatchDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table idv_roster_batch_detail
     *
     * @mbggenerated Wed Aug 10 16:06:11 CST 2016
     */
    int insertSelective(IdvRosterBatchDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table idv_roster_batch_detail
     *
     * @mbggenerated Wed Aug 10 16:06:11 CST 2016
     */
    IdvRosterBatchDetail selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table idv_roster_batch_detail
     *
     * @mbggenerated Wed Aug 10 16:06:11 CST 2016
     */
    int updateByPrimaryKeySelective(IdvRosterBatchDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table idv_roster_batch_detail
     *
     * @mbggenerated Wed Aug 10 16:06:11 CST 2016
     */
    int updateByPrimaryKey(IdvRosterBatchDetail record);
    
   List<Map<String, Object>> selectRosterBatchDetail(Long osterBatchId);
}