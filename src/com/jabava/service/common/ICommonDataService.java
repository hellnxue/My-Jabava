package com.jabava.service.common;

import java.util.List;

import com.jabava.pojo.common.EhrCommonData;

public interface ICommonDataService {
	List<EhrCommonData> listByCommonDataType(Integer commonDataType);
}
