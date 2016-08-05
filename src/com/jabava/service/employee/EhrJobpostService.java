package com.jabava.service.employee;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jabava.pojo.employee.EhrJobpost;
import com.jabava.pojo.employee.EhrJobpostVO;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;

public interface EhrJobpostService {
	List<EhrJobpostVO> getByPersonId(Long personId);
	
	boolean addJobpost(EhrJobpost jobpost,EhrUser user) throws Exception;
	
	boolean updateJobpost(EhrJobpost jobpost) throws Exception;
	
	boolean delJobpost(Long postId) throws Exception ;
	
	 /**
     *  根据personId查询出上一次岗位调动产生的任职记录
     * @param map
     * @return
     */
     EhrJobpost  getPreviousRecordByPersonId(Long personId);
     /**
      * 根据personId查询员工所有的任职记录
      * @param personId
      * @return
      */
     List<Map<String,Object>> getAllRecordsByPersonId(Long personId);
     
 	/**
 	 * 处理任职记录  修改上一条任职记录的结束时间并且新增一条任职记录
 	 * @param personId
 	 * @param u 
 	 * @param date 结束时间
 	 * @param changeType 变动类型
 	 * @param  importPerson 花名册导入的员工
 	 * @return
 	 */
 	public boolean HandleRecordByPersonId(Long personId,EhrUser u,Date date,String changeType,EhrPerson importPerson);
 	
 	 /**
     * 根据personId查询员工的变动类型为入职的任职记录
     * @param personId
     * @return
     */
 	 EhrJobpost getEntryRecordByPersonId(Map<String,Object> map);
 	 
 	/**
  	 * 处理变动类型为入职的任职记录     查找此人是否有入职的任职记录 有-修改startdate 没有-新增任职记录
  	 * @param personId
  	 * @param u 
  	 * @param date 结束时间
  	 * @return
  	 */
  	public boolean HandleEntryRecordByPersonId(Long personId,EhrUser u,Date startDate);
  	
  	/**
  	 * 处理变动类型为入职的任职记录
  	 * @return
  	 */
	public boolean HandleEntryRecord(EhrPerson person,EhrUser user,boolean isOld);
}
