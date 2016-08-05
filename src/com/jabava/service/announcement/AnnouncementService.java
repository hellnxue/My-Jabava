package com.jabava.service.announcement;

import java.util.List;
import java.util.Map;

import com.jabava.controller.announcement.QeuryEhrInformation;
import com.jabava.pojo.announcement.EhrInformation;
import com.jabava.utils.Page;

/**
 * 信息公告
 *
 * @version $Id: AnnouncementService.java, 
 * v 0.1 2015年12月30日 上午11:12:57 
 * <pre>
 * @author steven.chen
 * @date 2015年12月30日 上午11:12:57 
 * </pre>
 */
public interface AnnouncementService {

    List<Map<String, Object>>  findInformationByParamsPage(
			QeuryEhrInformation ehrInformation,Page<Map<String, Object>> page,String search);
	
	
	/**
	 * 添加公告信息
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月29日 下午2:33:27 
	 * </pre>
	 *
	 * @param record
	 * @return
	 */
	public  String addAnnouncement(EhrInformation record);

	/**
	 * 删除信息公告
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月30日 下午4:21:53 
	 * </pre>
	 *
	 * @param informationId
	 * @return
	 */
    String deleteByInformationId(Long informationId);   
    /**
     * 根据ID获取信息公告
     * <pre>
     * @author steven.chen
     * @date 2015年12月30日 下午4:22:17 
     * </pre>
     *
     * @param informationId
     * @return
     */
    EhrInformation selectByInformationId(Long informationId);
    /**
     * 根据参数查询信息列表
     * <pre>
     * @author steven.chen
     * @date 2015年12月28日 下午4:44:41 
     * </pre>
     *
     * @param record
     * @return
     */
    List<EhrInformation> findInformationByParams (QeuryEhrInformation ehrInformation);

    /**
     * 更新公告信息
     * <pre>
     * @author steven.chen
     * @date 2015年12月30日 下午4:22:39 
     * </pre>
     *
     * @param record
     * @return
     */
    String updateAnnouncement(EhrInformation record);



}
