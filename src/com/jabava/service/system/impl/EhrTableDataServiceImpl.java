package com.jabava.service.system.impl;

import java.util.*;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jabava.dao.manage.EhrBaseDataMapper;
import com.jabava.dao.system.EhrTableDataMapper;
import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.system.EhrTableData;
import com.jabava.service.system.EhrTableDataService;

@Service
public class EhrTableDataServiceImpl implements EhrTableDataService {
	
	@Resource
	private EhrTableDataMapper tableDataMapper;

	@Resource
	public EhrBaseDataMapper dataMapper;

	@Override
	public void handleCustomData(List<EhrTableData> tableDatalist) {
		
		List<EhrTableData> insertBachlist=new ArrayList<EhrTableData>();//批量新增
		List<EhrTableData> updateBachlist=new ArrayList<EhrTableData>();//批量修改
		
		for(EhrTableData tableData:tableDatalist){
			if(tableData.getTableDataId()!=null){
				updateBachlist.add(tableData);
			}else{
				insertBachlist.add(tableData);
			}
		}
		if(insertBachlist.size()>0){
			  tableDataMapper.insertBachForEhrTableData(insertBachlist);
		}
		if(updateBachlist.size()>0){
			 tableDataMapper.updateBachForEhrTableData(updateBachlist);
		}
		
	}

	@Override
	public List<EhrTableData> selectByTableFieldDefId(Long tableFieldDefId) {
		// TODO Auto-generated method stub
		List<EhrTableData> ehrTableDataList = tableDataMapper.selectByTableFieldDefId(tableFieldDefId);
		return ehrTableDataList;
	}
	
 

}
