/**
 * 
 */
package com.jabava.service.hro;

import java.util.List;

import com.jabava.pojo.hro.HroPactInfo;

/**
 * @author WangYongqiang
 * 
 */
public interface IHroPactInfoService {

	public HroPactInfo selectPactById(Long id) throws Exception;

	public List<HroPactInfo> queryPactInfoList();
}
