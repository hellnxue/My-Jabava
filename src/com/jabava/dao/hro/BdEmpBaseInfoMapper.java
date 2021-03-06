package com.jabava.dao.hro;

import java.util.List;

import com.jabava.pojo.hro.BdEmpBaseInfo;

public interface BdEmpBaseInfoMapper {
	
	/**
	 * 批量新增或者更新员工基本信息 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月29日 下午3:29:14 
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	int insertOrUpdateList(List<BdEmpBaseInfo> list);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_base_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long baseInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_base_info
     *
     * @mbggenerated
     */
    int insert(BdEmpBaseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_base_info
     *
     * @mbggenerated
     */
    int insertSelective(BdEmpBaseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_base_info
     *
     * @mbggenerated
     */
    BdEmpBaseInfo selectByPrimaryKey(Long baseInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_base_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BdEmpBaseInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_base_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BdEmpBaseInfo record);
}