package com.jabava.service.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrBaseDataType;
import com.jabava.utils.Page;

/**
 * 
 * @author panfei
 * 
 */
public interface IBaseDataService {
	/**
	 * 新增
	 * 
	 * @param dataInfo
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertBaseData(EhrBaseData data) throws Exception;

	/**
	 * 修改前查询
	 * 
	 * @param baseDataId
	 * @return
	 * @throws Exception
	 */
	public EhrBaseData selectByBaseDataKey(Long baseDataId) throws Exception;

	/**
	 * 修改基础数据
	 * 
	 * @param info 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updateBaseData(EhrBaseData data) throws Exception;

	/**
	 * 查询基础数据
	 * 
	 * @param baseDataCode
	 * @param baseDataName
	 * @param baseDataType
	 * @param isValid
	 * @param companyId
	 * @return
	 * @throws Exception
	 */
	List<EhrBaseData> searchBaseData(Map<String,Object> map, String search,
			String order, String according, int isNumeric,Page<EhrBaseData> page)
			throws Exception;

	/**
	 * 查询基础数据类型
	 * 
	 * @return
	 */
	List<EhrBaseDataType> selectByBaseDataType() throws Exception;

	public boolean updateByIsvalid(Long baseDataId, Long isValid)
			throws Exception;
	
	List<EhrBaseData> getByCompanyId(Long companyId) throws Exception;
	
	List<EhrBaseData> selectBaseData(Long companyId, int baseDataType, String baseDataName) throws Exception;

}
