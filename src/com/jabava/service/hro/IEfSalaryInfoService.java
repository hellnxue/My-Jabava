package com.jabava.service.hro;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.hro.EfSalaryInfo;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.Page;

public interface IEfSalaryInfoService {

    int insertOrUpdateList(List<EfSalaryInfo> recordList);
    
    List<Map<String,Object>> querySalaryInfoPage(EhrUser user,Page<Map<String, Object>> page,
    		String ym,String search,String orderBy) throws Exception;
}