package com.jabava.service.manage.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jabava.dao.manage.EhrAttendanceMapper;
import com.jabava.service.manage.ITimeService;
import com.jabava.utils.MessageUtil;
import com.jabava.utils.Page;

/**
 * 考勤记录ServiceImpl
 * 
 * @author 郑长山
 * 
 */
@SuppressWarnings("deprecation")
@Service
public class TimeServiceImpl implements ITimeService {

	@Autowired
	private EhrAttendanceMapper ehrAttendanceMapper;

	@Override
	public List<Map<String, Object>> getTimeListPage(String company_id,
			String job_number, String employee_name, String year_month_record,
			String organization_id, String work_location, String search,
			String orderBy, Page<Map<String, Object>> page) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company_id", company_id);
		params.put("job_number", job_number);
		params.put("employee_name", employee_name);
		params.put(
				"year_month_record",
				StringUtils.isEmpty(year_month_record) ? "" : year_month_record
						.replace("-", ""));
		params.put("organization_id", organization_id);
		params.put("work_location", work_location);
		params.put("search", search);
		params.put("orderBy", orderBy);
		params.put("page", page);
		return ehrAttendanceMapper.getTimeListPage(params);
	}

	@Override
	public Map<String, Object> delAttend(String attend_id) {
		String msg = "";
		if (StringUtils.isEmpty(attend_id)) {
			msg = MessageUtil.IS_NULL;
		} else {
			long id = -1l;
			try {
				id = Long.parseLong(attend_id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				msg = MessageUtil.EXC_NUMBER;
			}
			int res = ehrAttendanceMapper.deleteByPrimaryKey(id);
			if (res > 0) {
				msg = MessageUtil.DEL_SUCCESS;
			} else {
				msg = MessageUtil.DEL_ERROR;
			}
		}
		return MessageUtil.message(msg);
	}

	@Override
	public Map<String, Object> updAttend(String name, String value, String pk) {
		String msg = "";
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(value)
				|| StringUtils.isEmpty(pk)) {
			msg = MessageUtil.IS_NULL;
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", name);
			params.put("value", value);
			params.put("pk", pk);
			int res = ehrAttendanceMapper.updateByParam(params);
			if (res > 0) {
				msg = MessageUtil.UPD_SUCCESS;
			} else {
				msg = MessageUtil.UPD_ERROR;
			}
		}
		return MessageUtil.message(msg);
	}

	@Override
	public String importTimeExcel(String temp, MultipartFile file,
			Map<String, Object> user) {
		String res = "";
		File tempFile = new File(temp);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		DiskFileUpload fu = new DiskFileUpload();
		fu.setSizeMax(10 * 1024 * 1024); // 设置允许用户上传文件大小,单位:位
		fu.setSizeThreshold(4096); // 设置最多只允许在内存中存储的数据,单位:位
		fu.setRepositoryPath(temp); // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
		if (file == null)
			res = MessageUtil.IS_NULL;
		String name = file.getOriginalFilename();// 获取上传文件名,包括路径
		long size = file.getSize();
		if (StringUtils.isEmpty(name) && size == 0)
			res = MessageUtil.IS_NULL;
		InputStream in;
		List<Map<String, Object>> importTimeExcel = null;
		try {
			in = file.getInputStream();
			importTimeExcel = readImportTimeExcel(in);
		} catch (IOException e) {
			e.printStackTrace();
			res = MessageUtil.ERROR;
		}
		try {
			for (Map<String, Object> map : importTimeExcel) {
				map.put("create_user_id", user.get("create_user_id").toString());
				map.put("create_user_name", user.get("create_user_name")
						.toString());
				map.put("create_date", new Date());
				ehrAttendanceMapper.importTimeExcel(map);
			}
			res = MessageUtil.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			res = MessageUtil.ERROR;
		}
		return res;
	}

	/**
	 * 考勤Excel导入数据处理中心
	 * 
	 * @param is
	 *            导入流
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	private List<Map<String, Object>> readImportTimeExcel(InputStream is)
			throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				map = new HashMap<String, Object>();
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				for (int i = 0; i < hssfRow.getLastCellNum(); i++) {
					HSSFCell brandIdHSSFCell = hssfRow.getCell(i);
					String value = brandIdHSSFCell != null ? brandIdHSSFCell
							.toString() : "";
					// 序号
					switch (i) {
					case 0:
						break;
					// 姓名
					case 1:
						map.put("xm", value);
						break;
					// 工号
					case 2:
						map.put("gh", value);
						break;
					// 证件号码
					case 3:
						map.put("sfz", value);
						break;
					// 部门
					case 4:
						map.put("bm", value);
						break;
					// 岗位
					case 5:
						map.put("gw", value);
						break;
					// 电子邮箱
					case 6:
						map.put("dzyx", value);
						break;
					// 月份
					case 7:
						map.put("yf", value);
						break;
					// 次数
					case 8:
						map.put("cs", value);
						break;
					// 迟到次数
					case 9:
						map.put("cdcs", value);
						break;
					// 早退次数
					case 10:
						map.put("ztcs", value);
						break;
					// 旷工次数
					case 11:
						map.put("kgcs", value);
						break;
					// 事假天数
					case 12:
						map.put("sjts", value);
						break;
					// 病假天数
					case 13:
						map.put("bjts", value);
						break;
					// 出差天数
					case 14:
						map.put("ccts", value);
						break;
					// 年假天数
					case 15:
						map.put("njts", value);
						break;
					// 平日加班（小时）
					case 16:
						map.put("prjb", value);
						break;
					// 周末加班（小时）O
					case 17:
						map.put("zmjb", value);
						break;
					// 节假日加班（h）
					case 18:
						map.put("jjrjb", value);
						break;
					// 调休天数
					case 19:
						map.put("txts", value);
						break;
					// 白班天数
					case 20:
						map.put("bbts", value);
						break;
					// 夜班天数
					case 21:
						map.put("ybts", value);
						break;
					// 应休年假天数
					case 22:
						map.put("yxnjts", value);
						break;
					// 剩余年假
					case 23:
						map.put("synj", value);
						break;
					// 婚假
					case 24:
						map.put("hj", value);
						break;
					// 产假
					case 25:
						map.put("cj", value);
						break;
					// 陪产假
					case 26:
						map.put("pcj", value);
						break;
					// 丧假
					case 27:
						map.put("sj", value);
						break;
					// 备注
					case 28:
						map.put("bz", value);
						break;
					default:
						break;
					}
				}
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> downTimeExcel(String company_id,
			String[] attend_ids) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("company_id", company_id);
		params.put("attend_ids", attend_ids);
		return ehrAttendanceMapper.downTimeExcel(params);
	}
}
