/**
 * 
 */
package com.jabava.service.hro;

import java.util.List;

import com.jabava.pojo.hro.HroOrderSend;
import com.jabava.utils.Page;

/**
 * 
 *
 * @version $Id: ChangeService.java, 
 * v 0.1 2016年1月11日 下午4:38:58 
 * <pre>
 * @author steven.chen
 * @date 2016年1月11日 下午4:38:58 
 * </pre>
 */
public interface ChangeService {

	public HroOrderSend selectOrderSendById(Long id) ;
	
	public String addChange(HroOrderSend hroOrderSend,Long companyId)throws Exception ;

	List<HroOrderSend> findChangePage(Page<HroOrderSend> page,
			HroOrderSend hroOrderSend);


}
