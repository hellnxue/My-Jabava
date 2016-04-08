package com.jabava.dao.hro;

import java.util.List;

import com.jabava.pojo.hro.BdEmpRecInfo;

public interface BdEmpRecInfoMapper {
	
	/**
	 * 批量新增或者更新员工详细信息
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月29日 下午3:30:31 
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	int insertOrUpdateList(List<BdEmpRecInfo> list);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_rec_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long recInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_rec_info
     *
     * @mbggenerated
     */
    int insert(BdEmpRecInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_rec_info
     *
     * @mbggenerated
     */
    int insertSelective(BdEmpRecInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_rec_info
     *
     * @mbggenerated
     */
    BdEmpRecInfo selectByPrimaryKey(Long recInfoId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_rec_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BdEmpRecInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_emp_rec_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BdEmpRecInfo record);
}