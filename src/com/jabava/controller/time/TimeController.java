package com.jabava.controller.time;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jabava.pojo.manage.EhrBaseData;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.manage.ITimeService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.utils.constants.BaseDataConstants;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;

/**
 * 考勤记录Controller
 * 
 * @author 郑长山
 * 
 */
@SuppressWarnings("deprecation")
@Controller
@RequestMapping("time")
public class TimeController {
	@Autowired
	private ITimeService timeService;
	@Resource
	private IBaseDataService baseDataService;

	/**
	 * 获取考勤列表，分页
	 * 
	 * @param data
	 *            高级查询json参数
	 * @param request
	 * @param start
	 *            开始行数
	 * @param length
	 *            每页显示行数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("getTimeListPage")
	@ResponseBody
	public Page<Map<String, Object>> getTimeListPage(String data,
			HttpServletRequest request, Integer start, Integer length) {
		Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(
				JSONObject.fromObject(data), Map.class);
		// 设置分页参数
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(start,
				length);

		// 获取登录用户	
		EhrUser user = RequestUtil.getLoginUser(request);
		String company_id = Long.toString(user.getCompanyId());

		// 模糊搜索
		String search = request.getParameter("search[value]");

		/* 高级查询 */
		// 工号
		String job_number = map.get("job_number").toString().trim();
		// 姓名
		String employee_name = map.get("employee_name").toString().trim();
		// 月份
		String year_month_record = map.get("year_month_record").toString().trim();
		// 所属部门
		String organization_id = map.get("organization_id").toString().trim();
		// 工作地
		String work_location = map.get("work_location").toString().trim();

		/* 排序 */
		// 排序列的下标
		String order = request.getParameter("order[0][column]");
		// 排序列的名称
		order = (order.equals("0")) ? "year_month_record" : request
				.getParameter("columns[" + order + "][data]");
		// 升序或倒序
		String according = request.getParameter("order[0][dir]");

		// 执行查询
		List<Map<String, Object>> list = timeService.getTimeListPage(
				company_id, job_number, employee_name, year_month_record,
				organization_id, work_location, search,
				order + " " + according, page);
		page.setData(list);
		return page;
	}
	/**
	 * 获取当前公司的城市列表
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月7日 下午3:22:04 
	 * </pre>
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getWorkCityList")
	@ResponseBody
	public List<EhrBaseData> getWorkCityList(HttpServletRequest request) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
		return baseDataService.selectBaseData(user.getCompanyId(), BaseDataConstants.BASE_DATA_CITY, null);
	}
	/**
	 * 删除考勤记录
	 * 
	 * @param attend_id
	 *            考勤记录id
	 * @return
	 */
	@RequestMapping("delAttend")
	@ResponseBody
	public Map<String, Object> delAttend(String attend_id) {
		return timeService.delAttend(attend_id);
	}

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
	@RequestMapping("updAttend")
	@ResponseBody
	public Map<String, Object> updAttend(String name, String value, String pk) {
		return timeService.updAttend(name, value, pk);
	}
	/**
	 * 导出全部的条数
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月11日 下午3:48:28 
	 * </pre>
	 *
	 * @return
	 */
	@RequestMapping("exportAllSum")
	@ResponseBody
	public Long exportAllSum(HttpServletRequest request){
		EhrUser user = RequestUtil.getLoginUser(request);
		String company_id = Long.toString(user.getCompanyId());
		List<Map<String, Object>> list = timeService.downTimeExcel(company_id,
				null);
		return (long)list.size();
	}
	/**
	 * 导出全部考勤记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("downTimeExcelAll")
	@ResponseBody
	public Map<String, Object> downTimeExcelAll(HttpServletRequest request,
			HttpServletResponse response) {	
		EhrUser user = RequestUtil.getLoginUser(request);
		String company_id = Long.toString(user.getCompanyId());
		List<Map<String, Object>> list = timeService.downTimeExcel(company_id,
				null);
		return downTimeExcel(request, response, list);
	}
	/**
	 * 导出部分记录的条数
	 * <pre>
	 * @author steven.chen
	 * @date 2016年4月11日 下午3:52:05 
	 * </pre>
	 *
	 * @param request
	 * @param params
	 * @return
	 */
	@RequestMapping("exportPartSum")
	@ResponseBody
	public Long exportPartSum(HttpServletRequest request,String params){
		String[] attend_ids = params.split(",");
		List<Map<String, Object>> list = timeService.downTimeExcel(null,
				attend_ids);
		return (long)list.size();
	}
	/**
	 * 导出部分考勤记录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("downTimeExcelPart")
	@ResponseBody
	public Map<String, Object> downTimeExcelPart(String params,
			HttpServletRequest request, HttpServletResponse response) {
		String[] attend_ids = params.split(",");
		List<Map<String, Object>> list = timeService.downTimeExcel(null,
				attend_ids);
		return downTimeExcel(request, response, list);
	}

	/**
	 * 导出Excel
	 * 
	 * @param request
	 * @param response
	 * @param list
	 *            数据集合
	 * @return
	 */
	@SuppressWarnings("resource")
	public Map<String, Object> downTimeExcel(HttpServletRequest request,
			HttpServletResponse response, List<Map<String, Object>> list) {
		String res = "";
		/* 第一步：获取需要导出excel的数据 */
		if (list == null || list.size() == 0) {
			res = MessageUtil.IS_NULL;
		}
		/* 第二步：生成excel */
		try {
			// 创建新的Excel 工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel 工作簿中建一工作表
			HSSFSheet sheet = workbook.createSheet("考勤记录表");
			// 设置单元格格式(文本)
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
			// 在索引0的位置创建行（第一行）
			String[] excelTitle = { "工号", "姓名", "考勤月份", "迟到次数", "早退次数", "旷工次数",
					"事假天数", "病假天数", "出差天数", "年假天数", "平日加班", "周末加班", "节假日加班",
					"调休天数", "夜班天数", "应休年假天数", "剩余年假", "婚假", "产假", "陪产假", "丧假",
					"备注" };
			HSSFRow row = sheet.createRow((short) 0);
			// 标题的列数（第一列空着作为序号列）
			int index = excelTitle.length + 1;
			HSSFCell[] allcell = new HSSFCell[index];
			for (int i = 0; i < index; i++) {
				allcell[i] = row.createCell((short) (i));
				if (i == 0) {
					allcell[i].setCellValue("序号");
				} else {
					allcell[i].setCellValue(excelTitle[i - 1]);
				}
			}
			// 表格内容
			Iterator<Map<String, Object>> it = list.iterator();
			int j = 1;
			while (it.hasNext()) {
				Map<String, Object> sm = it.next();
				String[] info = new String[index];
				int l = 0;
				info[l++] = "" + j;
				// 工号
				info[l++] = formatMapValue(sm.get("job_number"));
				// 姓名
				info[l++] = formatMapValue(sm.get("employee_name"));
				// 考勤月份
				info[l++] = formatMapValue(sm.get("year_month_record"));
				// 迟到次数
				info[l++] = formatMapValue(sm.get("late_times"));
				// 早退次数
				info[l++] = formatMapValue(sm.get("leave_early_times"));
				// 旷工次数
				info[l++] = formatMapValue(sm.get("absent_time"));
				// 事假天数
				info[l++] = formatMapValue(sm.get("all_leave"));
				// 病假天数
				info[l++] = formatMapValue(sm.get("sick_leave"));
				// 出差天数
				info[l++] = formatMapValue(sm.get("business"));
				// 年假天数
				info[l++] = formatMapValue(sm.get("annual_leave"));
				// 平日加班
				info[l++] = formatMapValue(sm.get("work_overtime"));
				// 周末加班
				info[l++] = formatMapValue(sm.get("week_overtime"));
				// 节假日加班
				info[l++] = formatMapValue(sm.get("holidays_overtime"));
				// 调休天数
				info[l++] = formatMapValue(sm.get("adjust_day"));
				// 夜班天数
				info[l++] = formatMapValue(sm.get("night_shift"));
				// 应休年假天数
				info[l++] = formatMapValue(sm.get("total_annual"));
				// 剩余年假
				info[l++] = formatMapValue(sm.get("surplus_annual"));
				// 婚假
				info[l++] = formatMapValue(sm.get("marriage_leave"));
				// 产假
				info[l++] = formatMapValue(sm.get("maternity_leave"));
				// 陪产假
				info[l++] = formatMapValue(sm.get("paternity_leave"));
				// 丧假
				info[l++] = formatMapValue(sm.get("funeral_leave"));
				// 备注
				info[l++] = formatMapValue(sm.get("memo"));
				row = sheet.createRow((short) j);
				for (int i = 1; i <= index; i++) {
					HSSFCell cell = row.createCell((short) (i - 1));
					// 设置单元格格式
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(info[i - 1]);
				}
				j++;
			}
			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String("downTimeExcel.xls"));
			OutputStream output = response.getOutputStream();
			try {
				output.flush();
				workbook.write(output);
				output.close();
				res = MessageUtil.SUCCESS;
			} catch (IOException e) {
				e.printStackTrace();
				res = "Excel导出失败！";
			}
		} catch (Exception ioexception) {
			ioexception.printStackTrace();
			res = "创建文件失败！";
		}
		return MessageUtil.message(res);
	}

	/**
	 * 导入考勤
	 * 
	 * @param file
	 *            文件名称
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "importTimeExcel", produces = "text/html; charset=utf-8")
	@ResponseBody
	public String importTimeExcel(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws IOException {
		String temp = request.getSession().getServletContext()
				.getRealPath(File.separator)
				+ "temp"; // 临时目录	
		EhrUser ehrUser = RequestUtil.getLoginUser(request);
		Map<String, Object> user = new HashMap<String, Object>();
		user.put("create_user_id", ehrUser.getUserId());
		user.put("create_user_name", ehrUser.getUserName());
		String res = timeService.importTimeExcel(temp, file, user);		
		if (res.equals(MessageUtil.SUCCESS)) {
			res = "导入成功";
		}
		return res+"<script>alert('" + res + "');"
				+ "window.parent.location.reload();"				
				+ "</script>";
	}

	/**
	 * 判断取出的map值是否为空
	 * 
	 * @param mapValue
	 *            map值
	 * @return
	 */
	public String formatMapValue(Object mapValue) {
		if (mapValue == null || mapValue.toString().equals("")) {
			return "";
		}
		return mapValue.toString();
	}
}
