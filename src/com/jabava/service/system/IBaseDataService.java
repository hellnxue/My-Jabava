package com.jabava.service.system;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrBaseDataType;
import com.jabava.pojo.manage.EhrUser;
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
	
	/**
	 * 根据基础数据类型获取基础数据Map列表 Map(code,name)
	 * @param companyId
	 * @param baseDataType
	 * @param baseDataName
	 * @return
	 */
	Map<String,Object>  getBaseDataMap(Long companyId, int baseDataType, String baseDataName);
	
	List<EhrBaseData> selectBaseData(Long companyId, int baseDataType);
	/**
	 * 批量取值
	 * @param importFile
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object>  importBaseData(Map <String,Object>map,EhrUser ehrUser) throws Exception;
	/**
	 * 批量插值
	 * @param map
	 * @return
	 */
	public Map<String,Object> moreInsert(Map<String, Object>map)throws Exception;
	public Map<String,Object> resolveBaseData(CommonsMultipartFile multipartFile)throws Exception;
	/**
	 * 查询所有基础数据类型
	 * @return
	 */
	List<EhrBaseDataType> selectBaseDateType();
	/**
	 * 根据主键查询基础数据类型
	 * @param baseDataType
	 * @return
	 */
	EhrBaseDataType selectByPrimaryKey(Integer baseDataType);
}
