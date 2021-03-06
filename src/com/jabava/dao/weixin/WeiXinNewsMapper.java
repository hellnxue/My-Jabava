package com.jabava.dao.weixin;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.weixin.WeiXinNews;
import com.jabava.pojo.weixin.WeiXinNewsVO;



public interface WeiXinNewsMapper {
	
	public List<WeiXinNewsVO> findNewsPage(Map<String, Object> map);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table weixin_news
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long newsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table weixin_news
     *
     * @mbggenerated
     */
    int insertSelective(WeiXinNews record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table weixin_news
     *
     * @mbggenerated
     */
    WeiXinNews selectByPrimaryKey(Long newsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table weixin_news
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(WeiXinNews record);

}