package com.jabava.service.system;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.system.EhrTableData;

public interface EhrTableDataService {
	/**
	 * 自定义字段的数据处理
	 * @param list
	 * @return
	 */
	public void handleCustomData(List<EhrTableData> list);
	/**
	 * 查询自定义字段的值
	 * @param tableFieldDefId
	 * @return
	 */
	public List<EhrTableData> selectByTableFieldDefId(Long tableFieldDefId);

}
