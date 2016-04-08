package com.jabava.service.announcement.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jabava.controller.announcement.QeuryEhrInformation;
import com.jabava.core.EnumConstents.HasDetail;
import com.jabava.core.EnumConstents.HasRead;
import com.jabava.core.EnumConstents.InformationRange;
import com.jabava.core.EnumConstents.InformationType;
import com.jabava.core.EnumConstents.IsDeleted;
import com.jabava.dao.announcement.EhrInformationMapper;
import com.jabava.pojo.announcement.EhrInformation;
import com.jabava.service.announcement.AnnouncementService;
import com.jabava.utils.Page;

/**
 * 信息公告
 *
 * @version $Id: AnnouncementServiceImpl.java, 
 * v 0.1 2015年12月25日 下午2:35:54 
 * <pre>
 * @author steven.chen
 * @date 2015年12月25日 下午2:35:54 
 * </pre>
 */
@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {
	
	@Resource
	private EhrInformationMapper informationMapper;
	
	
	@Override
	public List<Map<String, Object>>  findInformationByParamsPage(QeuryEhrInformation ehrInformation,
			Page<Map<String, Object>> page,String search){
		
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("companyId", ehrInformation.getCompanyId());
		map.put("informationTitle", ehrInformation.getInformationTitle());
		map.put("startDate", ehrInformation.getStartDate());
		map.put("finishDate", ehrInformation.getFinishDate());
		map.put("isPriority", ehrInformation.getIsPriority());
		map.put("informationType", ehrInformation.getInformationType());
		map.put("informationRange", ehrInformation.getInformationRange());
		map.put("hasRead", ehrInformation.getHasRead());
		map.put("orderBy", ehrInformation.getOrderBy());
		map.put("search", search);
		map.put("page", page);
		List<Map<String, Object>>  ehrInformation2 =  informationMapper.findInformationByParamsPage(map);
		
		return ehrInformation2;
	}
	
	@Override
	public List<EhrInformation>  findInformationByParams(QeuryEhrInformation ehrInformation){
				
		List<EhrInformation>  ehrInformation2 =  informationMapper.findInformationByParams(ehrInformation);
		
		return ehrInformation2;
	}
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
	@Override
	public String addAnnouncement(EhrInformation record) {
		record.setCreateDate(new Date());
		record.setStartDate(new Date());
		//默认类型为公司信息
		record.setInformationType(InformationType.COMPANY.getValue());
		//默认范围为全部公司人员
		record.setInformationRange(InformationRange.ALL_COMPANY_USER.getValue());
		record.setIsDeleted(IsDeleted.UN_DELETED.getValue());
		if(record.getHasDetail().equals(HasDetail.HAS_DETAIL)){
			record.setInformationContentLength(record.getInformationContent().length());
		}
		if( informationMapper.insertSelective(record)==1){
			return "success";
		}else {
			return "error";
		}
	}
	/**
	 * 删除公告信息
	 * @author steven.chen
	 * @date 2015年12月30日 下午4:21:24 
	 * @param informationId
	 * @return
	 * @see com.jabava.service.announcement.AnnouncementService#deleteByInformationId(java.lang.Long)
	 */
	@Override
	public String  deleteByInformationId(Long informationId) {
		EhrInformation information = new EhrInformation();
		information.setInformationId(informationId);
		information.setIsDeleted(IsDeleted.DELETED.getValue());
		if(informationMapper.updateByPrimaryKeySelective(information)==1){
			return "success";
		}else {
			return "error";
		}
	}	
	/**
	 * 根据ID查询信息公告
	 * @author steven.chen
	 * @date 2015年12月30日 下午4:21:07 
	 * @param informationId
	 * @return
	 * @see com.jabava.service.announcement.AnnouncementService#selectByInformationId(java.lang.Long)
	 */
	@Override
	public EhrInformation selectByInformationId(Long informationId) {
//		EhrInformation information = new EhrInformation();
//		information.setInformationId(informationId);
//		information.setHasRead(HasRead.READ.getValue());
//		informationMapper.updateByPrimaryKeySelective(information);
		return informationMapper.selectByPrimaryKey(informationId);
	}
	/**
	 * 修改信息公告
	 * @author steven.chen
	 * @date 2015年12月30日 下午4:20:54 
	 * @param record
	 * @return
	 * @see com.jabava.service.announcement.AnnouncementService#updateAnnouncement(com.jabava.pojo.announcement.EhrInformation)
	 */
	@Override
	public String updateAnnouncement(EhrInformation record) {
		if( informationMapper.updateByPrimaryKeySelective(record)==1){
			return "success";
		}else {
			return "error";
		}
	}
	
		
}
