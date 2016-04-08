package com.jabava.service.hro;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.hro.EfReceiptOwned;
import com.jabava.pojo.manage.EhrUser;

public interface IEfReceiptOwnedService {

    int insertOrUpdateList(List<EfReceiptOwned> recordList);
    
    List<Map<String,Object>> queryReceiptOwnedList(EhrUser user,String ym,String amount) throws Exception;
}