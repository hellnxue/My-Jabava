package com.jabava.service.employee;

import java.util.List;

import com.jabava.pojo.employee.EhrJobpost;
import com.jabava.pojo.employee.EhrJobpostVO;

public interface EhrJobpostService {
	List<EhrJobpostVO> getByPersonId(Long personId);
	
	boolean addJobpost(EhrJobpost jobpost) throws Exception;
	
	boolean updateJobpost(EhrJobpost jobpost) throws Exception;
	
	boolean delJobpost(Long postId) throws Exception ;
}
