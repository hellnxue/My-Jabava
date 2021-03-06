package com.jabava.dao.hro;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.hro.BdSb;
import com.jabava.pojo.hro.order.HsEmpOrd;


public interface BdSbMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_sb
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long sbId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_sb
     *
     * @mbggenerated
     */
    int insert(BdSb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_sb
     *
     * @mbggenerated
     */
    int insertSelective(BdSb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_sb
     *
     * @mbggenerated
     */
    BdSb selectByPrimaryKey(Long sbId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_sb
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BdSb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bd_sb
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BdSb record);
    
    List<Map<String,Object>> getSheBaoInfoPage(Map<String,Object> map);
    
    /**
	 * 批量新增或者更新社保公积金信息，如果 sbId 存在就更新，如果sbId 不存在就新增
	 * <pre>
	 * @author xueping.liu
	 * @date 2016年1月20日 下午6:22:21 
	 * </pre>
	 *
	 * @param List<BdSb>
	 * @return
	 */
	int insertSbUpdateList(List<BdSb> list);
}