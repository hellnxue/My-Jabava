package com.jabava.service.individual;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jabava.pojo.individual.IdvRosterBatch;
import com.jabava.pojo.manage.EhrUser;

public interface IdvRosterBatchService {
	/**
	 * 名单批次表新增数据
	 * @param idvRosterBatch
	 * @return
	 */
	public int insertIdvRosterBatch(IdvRosterBatch idvRosterBatch);
	
	/**
     *  根据companyId和批次类型查询名单批次信息
     * @param map
     * @return
     */
	Map<String, Object> selectBatchInfoByCompanyIdAndType(Map<String, Object> map);
	
	/**
	 * 根据名单批次id rosterbatchId查询名单批次详细信息
	 * @param rosterbatchId
	 * @return
	 */
	Map<String, Object> getRosterBatchDetail(Long rosterbatchId);
	
	/**
	 * 上传BP文件
	 * @param multipartFile
	 * @param rosterbatchId 选择的批次id
	 * @param type 选择批次的类型
	 * @param user
	 * @return
	 */
	Map<String, Object> importBDFiles(CommonsMultipartFile multipartFile,Long rosterbatchId,Integer type, EhrUser user) throws Exception ;
	
	/**
	 * 人员和套表数据合成
	 * @param rosterBatchDetail  人员详情集合
	 * @param 批次类型-也是合同类型
	 * @param companyId
	 * @return
	 */
	List<Map<String, Object>> handlerRosterDetailAndSetTable(List<Map<String, Object>> rosterBatchDetail,int type,Long companyId);
}
