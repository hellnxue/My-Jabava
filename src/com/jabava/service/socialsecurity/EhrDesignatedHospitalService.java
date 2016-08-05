package com.jabava.service.socialsecurity;

import java.util.List;

import com.jabava.pojo.socialsecurity.EhrDesignatedHospital;

public interface EhrDesignatedHospitalService {
	
	/**
	 * 依据社保档案代码查询相关联的医院列表
	 * @param profileId
	 * @return
	 */
	public List <EhrDesignatedHospital> getDesignatedHospitalInfoByProfileId(Long profileId);
	
	/**
	 * 保存社保档案相关联的定点医院列表
	 * @param profileId
	 * @param list
	 * @return
	 */
	public int savetDesignatedHospitalInfo(List<EhrDesignatedHospital> list);
}
