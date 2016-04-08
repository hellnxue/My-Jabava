package com.jabava.service.manage;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.jabava.utils.Page;

/**
 * 考勤记录Service
 * 
 * @author 郑长山
 * 
 */
public interface ITimeService {
	/**
	 * 获取考勤列表，分页
	 * 
	 * @param company_id
	 *            公司id
	 * @param job_number
	 *            工号
	 * @param employee_name
	 *            姓名
	 * @param year_month_record
	 *            月份
	 * @param organization_id
	 *            所属部门
	 * @param work_location
	 *            工作地
	 * @param search
	 *            模糊搜索
	 * @param orderBy
	 *            排序
	 * @param page
	 *            分页参数
	 * @return
	 */
	List<Map<String, Object>> getTimeListPage(String company_id,
			String job_number, String employee_name, String year_month_record,
			String organization_id, String work_location, String search,
			String orderBy, Page<Map<String, Object>> page);

	/**
	 * 删除考勤记录
	 * 
	 * @param attend_id
	 *            考勤记录id
	 * @return
	 */
	Map<String, Object> delAttend(String attend_id);

	/**
	 * 修改考勤记录
	 * 
	 * @param name
	 *            修改的字段
	 * @param value
	 *            修改字段的值
	 * @param pk
	 *            数据库主键id值
	 * @return
	 */
	Map<String, Object> updAttend(String name, String value, String pk);

	/**
	 * 批量导入Excel
	 * 
	 * @param temp
	 *            文件上传路径
	 * @param file
	 *            上传文件名称
	 * @param user
	 *            session
	 * @return
	 */
	String importTimeExcel(String temp, MultipartFile file,
			Map<String, Object> user);

	/**
	 * 导出Excel
	 * 
	 * @param company_id
	 *            公司id
	 * @param attend_ids
	 *            id集合
	 * @return
	 */
	List<Map<String, Object>> downTimeExcel(String company_id,
			String[] attend_ids);
}
