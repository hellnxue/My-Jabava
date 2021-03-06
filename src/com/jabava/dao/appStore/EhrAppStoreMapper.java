package com.jabava.dao.appStore;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.appStore.EhrAppStore;

public interface EhrAppStoreMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_app_store
     *
     * @mbggenerated Tue Aug 09 11:02:08 CST 2016
     */
    int deleteByPrimaryKey(Long appId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_app_store
     *
     * @mbggenerated Tue Aug 09 11:02:08 CST 2016
     */
    int insert(EhrAppStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_app_store
     *
     * @mbggenerated Tue Aug 09 11:02:08 CST 2016
     */
    int insertSelective(EhrAppStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_app_store
     *
     * @mbggenerated Tue Aug 09 11:02:08 CST 2016
     */
    EhrAppStore selectByPrimaryKey(Long appId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_app_store
     *
     * @mbggenerated Tue Aug 09 11:02:08 CST 2016
     */
    int updateByPrimaryKeySelective(EhrAppStore record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ehr_app_store
     *
     * @mbggenerated Tue Aug 09 11:02:08 CST 2016
     */
    int updateByPrimaryKey(EhrAppStore record);
    
    /**
     * @Description: 查询出所有有效的在店应用
     * @param @param paramMap 请求参数
     * 					parentId 父级id
     * 					isDeleted 逻辑删除状态
     * @return List<EhrAppStore>  返回值
     * @author meng.meng
     * @date 2016年8月9日
     */
    List<EhrAppStore> selectAppInStore(Map<String, Object> paramMap);
}