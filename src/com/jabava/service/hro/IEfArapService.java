package com.jabava.service.hro;

import java.util.List;
import java.util.Map;

import com.jabava.pojo.hro.EfArap;
import com.jabava.pojo.hro.EfArapDetailEmpNsb;
import com.jabava.pojo.hro.EfArapDetailEmpSb;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.utils.Page;

/**
 * 账单Service接口
 * 
 * @author 郑长山
 * 
 */
public interface IEfArapService {
	
	List<EfArap> searchBill(Long CompanyId,String year,String search,String orderBy);
	
	List<EfArap> searchBill(Long CompanyId,String billYm,Page<EfArap> page,String search,String orderBy);
	
	List<EfArap> searchBill(Long CompanyId,String billYm,String orderBy);
	
	EfArap findByPrimaryKey(Long billId);
	
	List<Map<String,Object>> searchBillDetailHeader(EfArap bill);
	
	List<Map<String,Object>> searchBillDetailData(EfArap bill,String order,String by);

	/**
	 * 根据账单编号查询是否存在此账单
	 * 
	 * @param billCode
	 *            账单编号
	 * @return 0-不存在，1-存在
	 */
	//int isExitBill(Long bill_id);
	int isExitBill(String billCode);

	/**
	 * 根据账单实体对象插入一条数据
	 * 
	 * @param efArap
	 *            账单实体对象
	 * @return
	 */
	int insertSelective(EfArap efArap);

	/**
	 * 根据账单实体对象修改一条数据
	 * 
	 * @param efArap
	 *            账单实体对象
	 * @return
	 */
	int updateByPrimaryKey(EfArap efArap);

	// **********************************
	/**
	 * 根据账单id查询是否存在此账单明细
	 * 
	 * @param billDetailId
	 *            账单id
	 * @return 0-不存在，1-存在
	 */
	public int isExitDetailEmp(Long billDetailId);

	/**
	 * 根据账单明细实体对象插入一条数据
	 * 
	 * @param efArap
	 *            账单明细实体对象
	 * @return
	 */
	public int insertSelective(EfArapDetailEmpNsb efArapDetailEmp);

	/**
	 * 根据账单明细实体对象修改一条数据
	 * 
	 * @param efArap
	 *            账单明细实体对象
	 * @return
	 */
	public int updateByPrimaryKey(EfArapDetailEmpNsb efArapDetailEmp);

	// **********************************
	/**
	 * 根据账单id查询是否存在此账明细非社保
	 * 
	 * @param billDetailId
	 *            账单id
	 * @return 0-不存在，1-存在
	 */
	public int isExitDetailEmpSb(Long billDetailId);

	/**
	 * 根据账明细非社保实体对象插入一条数据
	 * 
	 * @param efArap
	 *            账明细非社保实体对象
	 * @return
	 */
	public int insertSelective(EfArapDetailEmpSb efArapDetailEmpSb);

	/**
	 * 根据账明细非社保实体对象修改一条数据
	 * 
	 * @param efArap
	 *            账明细非社保实体对象
	 * @return
	 */
	public int updateByPrimaryKey(EfArapDetailEmpSb efArapDetailEmpSb);
	
	public boolean isBillExist(String protocolCode);
	
	/**
	 * 获取对账信息
	 * @param use
	 * @param year
	 * @return
	 */
	public List<Map<String,Object>> queryBalanceList(EhrUser user, String year);
}
