package com.jabava.service.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.employee.EhrContract;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.Page;

public interface EhrPersonService {

	public static List<String> errorMsg = new ArrayList<String>();

	public List<EhrPerson> searchPerson(Map<String, Object> map, Long userId,
			Integer start, Integer length, String search, String order,
			String according, int isNumeric, Page<EhrPerson> page)
			throws Exception;


	public int searchSalary(Long personId) throws Exception;

	public List<EhrBaseData> searchBaseData() throws Exception;

	Map<String, Object> addPerson(EhrPerson person, EhrUser user)
			throws Exception;

	public int searchPositive(int day, Long companyId, String distinguish,
			Long userId) throws Exception;

	public int searchContract(int day, int flag, Long companyId,
			String distinguish, Long userId) throws Exception;

	public int searchBirth(int day, Long userId, Long companyId,
			String distinguish) throws Exception;

	public List<EhrPerson> searchBirthList(int day, Long userId,
			Long companyId, String distinguish) throws Exception;

	public List<EhrPerson> searchPositiveList(int day, Long companyId,
			String distinguish, Long userId) throws Exception;

	public List<EhrContract> searchContractList(int day, int flag,
			Long companyId, String distinguish, Long userId) throws Exception;

	EhrPerson getByPersonId(Long personId) throws Exception;
	
	EhrPerson getByJobNumber(Long companyId, String jobNumber);

	public void importPerson(CommonsMultipartFile multipartFile, EhrUser user)
			throws Exception;

	public List<EhrPerson> selectAllPerson(Long companyId) throws Exception;

	public void exportPerson(List<EhrPerson> persons,
			EhrUser user, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	boolean deletePerson(Long personId) throws Exception;
	
	Map<String, Object> updatePerson(HttpServletRequest request, EhrPerson person, EhrUser user) throws Exception;
}
