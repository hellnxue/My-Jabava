package com.jabava.service.socialsecurity.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.jabava.dao.socialsecurity.EhrDesignatedHospitalMapper;
import com.jabava.pojo.socialsecurity.EhrDesignatedHospital;
import com.jabava.service.socialsecurity.EhrDesignatedHospitalService;

@Service
public class EhrDesignatedHospitalServiceImplementImpl implements EhrDesignatedHospitalService{
	public static Logger log = Logger.getLogger(EhrDesignatedHospitalServiceImplementImpl.class);
	
	@Resource
	private EhrDesignatedHospitalMapper ehrDesignatedHospitalMapper;

	public List<EhrDesignatedHospital> getDesignatedHospitalInfoByProfileId(Long personId) {
		return ehrDesignatedHospitalMapper.getDesignatedHospitalInfoByProfileId(personId);
	}

	public int savetDesignatedHospitalInfo(List<EhrDesignatedHospital> list) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		String createDate = sdf.format(new Date());
		
		int ret = 0;
		if(list.size() > 0){
			EhrDesignatedHospital obj = list.get(0);
			Long personId = obj.getPersonId();
			
			ehrDesignatedHospitalMapper.removeDesignatedHospitalInfoByProfileId(personId);
			if(obj.getProfileId() == -1){	//删除定点医院的标识
				return ret;
			}
			
			for(EhrDesignatedHospital edh : list){
				edh.setCreateTime(createDate);
				edh.setHospitaCode(UUID.randomUUID().toString());
				edh.setIsDeleted(0);
			}
			
			ret = ehrDesignatedHospitalMapper.addBatchDesignatedHospitalInfo(list);
		}
		
		return ret;
	}

}
