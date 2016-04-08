package com.jabava.controller.employee;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jabava.pojo.manage.EhrOrganization;
import com.jabava.pojo.manage.EhrPerson;
import com.jabava.pojo.manage.EhrUser;
import com.jabava.service.employee.IEmployeeService;
import com.jabava.service.system.IBaseDataService;
import com.jabava.service.system.IEhrOrganizationService;
import com.jabava.utils.Page;
import com.jabava.utils.RequestUtil;
import com.jabava.utils.excel.ExcelConfig;
import com.jabava.utils.excel.ExcelHeader;
import com.jabava.utils.excel.ExcelUtil;


@Controller
@RequestMapping("employee")
public class EmployeeController {

	@Resource
	private IEmployeeService personService;
	@Resource
	private IEhrOrganizationService organizationSerevice;
	@Resource
	private IBaseDataService basedataService;
	
	@RequestMapping("addressList")
	public String addressList(HttpServletRequest request,HttpServletResponse response){
		return "employees/list_address";		  
	  }
	
	@RequestMapping("addressListPage")
	@ResponseBody
	public Page<EhrPerson> addressList(HttpServletRequest request,HttpServletResponse response,Integer start,Integer length){
		EhrUser user = RequestUtil.getLoginUser(request);
		String billYm = request.getParameter("billYm");
		String search = request.getParameter("search[value]");//search框的值
		String order = request.getParameter("order[0][column]");//排序列的下标
		order = request.getParameter("columns["+order+"][data]"); //排序列的名称
		String according = request.getParameter("order[0][dir]");//升序或倒序
		order = this.getColumnName(order);

		Long companyId = user.getCompanyId();
		Page<EhrPerson> page = new Page<EhrPerson>(start,length);
		List<EhrPerson> list = personService.searchAddress(companyId,search,page,order + " " + according,billYm);
		page.setData(list);
		return page;  
		
//		request.setAttribute("list", list);
//		return "employees/list_address";
		  
	}
    
	private String getColumnName(String order){
		if(order != null){
			if(order.equals("personId")){
				return "person_id";
			}else if(order.equals("jobNumber")){
				return "job_number";
			}else if(order.equals("employeeName")){
				return "employee_name";
			}else if(order.equals("org")){
				return "organization_id";
			}else if(order.equals("post")){
				return "post_id";
			}else if(order.equals("phone")){
				return "mobile";
			}else if(order.equals("email")){
				return "email";
			}
		}
		return "person_id";
	}
	
	@RequestMapping("exportAddress")
	public void exportAddress(HttpServletRequest request, HttpServletResponse response) throws Exception{
		EhrUser user = RequestUtil.getLoginUser(request);
//		Long companyId = user.getCompanyId();
//		List<EhrPerson> list = personService.searchAllAddress(companyId,search);
//		List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
//		headers.add(new ExcelHeader("ID", "personId", 1, 1, 1, 1));
//		headers.add(new ExcelHeader("工号", "jobNumber", 1, 1, 1, 2));
//		headers.add(new ExcelHeader("姓名", "employeeName", 1, 1, 1, 3));
//		headers.add(new ExcelHeader("所属部门", "org", 1, 1, 1, 4));
//		headers.add(new ExcelHeader("职位", "post", 1, 1, 1, 5));
//		headers.add(new ExcelHeader("电话", "mobile", 1, 1, 1, 6));
//		headers.add(new ExcelHeader("邮箱", "email", 1, 1, 1, 7));
//
//		List<JSONObject> datas = new ArrayList<JSONObject>();
//		JSONObject jo = null;
//		for (EhrPerson person : list) {
//			jo = JSONObject.fromObject(person);
//			if(StringUtils.isEmpty(person.getMobile())){
//				jo.put("mobile", person.getPhone());
//			}
//			datas.add(jo);
//		}
//
//		ExcelConfig config = new ExcelConfig("通讯录", headers, datas);
		
		EhrPerson person = new EhrPerson();
		person.setStatus((byte)0);
		person.setIsPayrollFlag((byte)0);
		
//		List<ExcelHeader> headers = new ArrayList<ExcelHeader>();
//		headers.add(new ExcelHeader("序号", "seq", 1, 1, 1, 1));
//		headers.add(new ExcelHeader("单位名称", "org0", 1, 1, 1, 2));
//		headers.add(new ExcelHeader("一级部门", "org1", 1, 1, 1, 3));
//		headers.add(new ExcelHeader("二级部门", "org2", 1, 1, 1, 4));
//		headers.add(new ExcelHeader("三级部门", "org3", 1, 1, 1, 5));
//		headers.add(new ExcelHeader("团队", "team", 1, 1, 1, 6));
//		headers.add(new ExcelHeader("成本中心", "cost", 1, 1, 1, 7));
//		headers.add(new ExcelHeader("姓名", "employeeName", 1, 1, 1, 8));
//		headers.add(new ExcelHeader("岗位", "post", 1, 1, 1, 9));
//		headers.add(new ExcelHeader("工作地点", "workLocation", 1, 1, 1, 10));
//		headers.add(new ExcelHeader("汇报对象", "reportObject", 1, 1, 1, 11));
//		headers.add(new ExcelHeader("手机", "mobile", 1, 1, 1, 12));
//		headers.add(new ExcelHeader("邮箱", "email", 1, 1, 1, 13));
//		headers.add(new ExcelHeader("用工性质", "employmentType", 1, 1, 1, 14));
//		
//		List<ExcelConfig> configList = new ArrayList<ExcelConfig>();
		
		Map<String,Object> datas = new HashMap<String,Object>();
		
		person.setKeyPerson((byte)1);
		List<EhrPerson> retMdl = personService.searchPerson(person, user.getUserId(), user.getCompanyId(), user.getUserDistinguish());
		//configList.add(this.getExcelConfig("关键人",retMdl,headers,user));
		datas.put("keyPersons", this.getExcelData(retMdl, user));

		person.setKeyPerson(null);
		retMdl = personService.searchPerson(person, user.getUserId(), user.getCompanyId(), user.getUserDistinguish());
		//configList.add(this.getExcelConfig("所有人员",retMdl,headers,user));
		datas.put("allPersons", this.getExcelData(retMdl, user));
		
		response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
		response.setHeader("Content-Disposition","attachment; filename=" + new String("addresslist.xlsx"));
		
		OutputStream out = response.getOutputStream();
		//ExcelUtil.write(out, configList);
		ExcelUtil.write(datas, "tpl_adress.xlsx", out);
		
//		EhrUser user = RequestUtil.getLoginUser(request);
//		String distinguish = user.getUserDistinguish();
//		
//		EhrPerson person = new EhrPerson();
//		person.setStatus((byte)0);
//		person.setIsPayrollFlag((byte)0);
//		
//		long companyid=user.getCompanyId();
//		try {
//			// 创建新的Excel 工作簿
//			HSSFWorkbook workbook = new HSSFWorkbook();
//			// 在Excel 工作簿中建一工作表
//			HSSFSheet sheet = workbook.createSheet("关键人");
//			// 设置单元格格式(文本)
//			HSSFCellStyle cellStyle = workbook.createCellStyle();
//			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
//			// 在索引0的位置创建行（第一行）
//			String[] index={"序号","单位名称","一级部门","二级部门","三级部门","团队","成本中心","姓名",
//					"岗位","工作地点","汇报对象","手机","邮箱","用工性质"};
//			HSSFRow row = sheet.createRow((short) 0);
//			HSSFCell[] allcell=new HSSFCell[14];
//			for(int i=0;i<14;i++)
//			{
//				allcell[i]=row.createCell((short)(i));
//				allcell[i].setCellValue(index[i]);
//			}
//			
//			List<EhrPerson> retMdl=null;
//			Iterator<EhrPerson> it;
//			person.setKeyPerson((byte)1);
//			retMdl = personService.searchPerson(person, user.getUserId(), companyid, distinguish);
//			it = retMdl.iterator();
//			int j = 1;
//			while (it.hasNext()){
//				EhrPerson sm = it.next();
//				String[] info = new String[14];
//				info[0] = ""+j;
//				EhrOrganization eor = null;
//				String mycompanyid = sm.getCol(2);
//				if(sm.getCol(6) != null && !"".equals(sm.getCol(6)) && !"0".equals(sm.getCol(6)))
//					sm.setCostcode((basedataService.selectByBaseDataKey(Long.parseLong(sm.getCol(6)))).getBaseDataCode());
//				else
//					sm.setCostcode("");
//				String org[]=new String[4];
//				if(!mycompanyid.equals("0")){
//				org[0]=user.getCompany().getCompanyName();
//				eor = organizationSerevice.selectByorganizationId(Long.parseLong(mycompanyid));
//				if(eor!=null){
//					int level=eor.getOrganizationLevel();
//					long parentid=eor.getParentId();
//					
//					if(level >= 3){
//						level = 3;
//					}
//					
//					if(level!=0)
//						org[level]=eor.getOrganizationName();
//						while(level>1){
//							eor = organizationSerevice.selectByorganizationId(parentid);
//							if(eor!=null){
//							level=eor.getOrganizationLevel();
//							if(level >= 3){
//								level = 3;
//							}
//							parentid=eor.getParentId();
//							org[level]=eor.getOrganizationName();
//							}
//						}
//					}
//				}
//				
//				info[1]=org[0];
//				info[2]=org[1];
//				info[3]=org[2];
//				info[4]=org[3];
//				info[5]=(sm.getTeam()==null || "".equals(sm.getTeam()) || sm.getTeam()==0)?"":((EhrBaseData)basedataService.selectByBaseDataKey(sm.getTeam())).getBaseDataName();;
//				info[6]=(sm.getCostId()==null || "".equals(sm.getCostId()))?"":((EhrBaseData)basedataService.selectByBaseDataKey(Long.parseLong((sm.getCostId()==null)?"":sm.getCostId().toString()))).getBaseDataName();
//				info[7]=sm.getEmployeeName();
//				info[8]=(sm.getPostId()==null || "".equals(sm.getPostId()))?"":((EhrBaseData)basedataService.selectByBaseDataKey(Long.parseLong((sm.getPostId()==null)?"":sm.getPostId().toString()))).getBaseDataName();
//				String worklocation = null;
//				if(!"0".equals(sm.getWorkLocation())){
//					worklocation = sm.getWorkLocation();
//				}
//				info[9]=(worklocation==null || "".equals(worklocation))?"":((EhrBaseData)basedataService.selectByBaseDataKey(Long.parseLong((worklocation==null)?"":worklocation.toString()))).getBaseDataName();
////				info[9]=(sm.getWorkLocation()==null || "".equals(sm.getWorkLocation()))?"":((EhrBaseDataModel)baseDataService.selectByBaseDataKey(Long.parseLong((sm.getWorkLocation()==null)?"":sm.getWorkLocation().toString()))).getBaseDataName();
//				info[10]=sm.getReportObject();
//				info[11]=sm.getMobile();
//				info[12]=sm.getEmail();
//				info[13]=(sm.getEmploymentType()==null)?"":sm.getEmploymentType().toString();
//				/*
//				info[5]=(sm.getTeam()==null || "".equals(sm.getTeam()))?"":((EhrBaseDataModel)baseDataService.selectByBaseDataKey(sm.getTeam())).getBaseDataName();
//				info[6]=(sm.getCostId()==null || "".equals(sm.getCostId()))?"":((EhrBaseDataModel)baseDataService.selectByBaseDataKey(Long.parseLong((sm.getCostId()==null)?"":sm.getCostId().toString()))).getBaseDataName();
//				info[7]=sm.getEmployeeName();
//				info[8]=(sm.getPostId()==null || "".equals(sm.getPostId()))?"":((EhrBaseDataModel)baseDataService.selectByBaseDataKey(Long.parseLong((sm.getPostId()==null)?"":sm.getPostId().toString()))).getBaseDataName();
//				info[9]=(sm.getWorkLocation()==null || "".equals(sm.getWorkLocation()))?"":((EhrBaseDataModel)baseDataService.selectByBaseDataKey(Long.parseLong((sm.getWorkLocation()==null)?"":sm.getWorkLocation().toString()))).getBaseDataName();
//				info[10]=sm.getReportObject();
//				info[11]=sm.getMobile();
//				info[12]=sm.getEmail();
//				info[13]=(sm.getEmploymentType()==null)?"":sm.getEmploymentType().toString();
//				*/
//				
//				row = sheet.createRow((short) j);
//				for (int i = 1; i <= 14; i++) {
//					HSSFCell cell = row.createCell((short) (i-1));
//					// 设置单元格格式
//					
//					//if(i!=1 && i!=15 && i!=18)
//					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//					//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
//					cell.setCellStyle(cellStyle);
//				
//					cell.setCellValue(info[i-1]);
//				}
//				j++;
//			}
//			
//			//第2个sheet，全部人员
//			sheet = workbook.createSheet("所有人员");
//			// 设置单元格格式(文本)
//			cellStyle = workbook.createCellStyle();
//			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
//			// 在索引0的位置创建行（第一行）
//			
//			row = sheet.createRow((short) 0);
//			allcell=new HSSFCell[14];
//			for(int i=0;i<14;i++)
//			{
//				allcell[i] = row.createCell((short)(i));
//				allcell[i].setCellValue(index[i]);
//			}
//						
//			person.setKeyPerson(null);
//			retMdl = personService.searchPerson(person, user.getUserId(), companyid, distinguish);
//			it = retMdl.iterator();
//			j = 1;
//			while (it.hasNext()){
//				EhrPerson sm=(EhrPerson)it.next();
//				String[] info=new String[14];
//				info[0]=""+j;
//				//OrganizationService orgService=new OrganizationService(db);
//				EhrOrganization eor=null;
//				String mycompanyid=sm.getCol(2);
//				if(sm.getCol(6)!=null && !"".equals(sm.getCol(6)) && !"0".equals(sm.getCol(6)))
//					sm.setCostcode((basedataService.selectByBaseDataKey(Long.parseLong(sm.getCol(6)))).getBaseDataCode());
//				else
//					sm.setCostcode("");
//				String org[]=new String[4];
//				if(!mycompanyid.equals("0"))
//				{
//				org[0] = user.getCompany().getCompanyName();
//				eor = organizationSerevice.selectByorganizationId(Long.parseLong(mycompanyid));
//				if(eor!=null){
//					int level=eor.getOrganizationLevel();
//					long parentid=eor.getParentId();
//					
//					if(level >= 3){
//						level = 3;
//					}
//					
//					if(level!=0)
//						org[level]=eor.getOrganizationName();
//					
//						while(level>1){
//							eor = organizationSerevice.selectByorganizationId(parentid);
//							if(eor!=null){
//							level=eor.getOrganizationLevel();
//							if(level >= 3){
//								level = 3;
//							}
//							parentid=eor.getParentId();
//							org[level]=eor.getOrganizationName();
//							}
//						}
//					}
//				}
//				
//				info[1]=org[0];
//				info[2]=org[1];
//				info[3]=org[2];
//				info[4]=org[3];
//				info[5]=(sm.getTeam()==null || "".equals(sm.getTeam()) ||  sm.getTeam()==0)?"":(basedataService.selectByBaseDataKey(sm.getTeam())).getBaseDataName();
//				info[6]=(sm.getCostId()==null || "".equals(sm.getCostId()))?"":(basedataService.selectByBaseDataKey(Long.parseLong((sm.getCostId()==null)?"":sm.getCostId().toString()))).getBaseDataName();
//				info[7]=sm.getEmployeeName();
//				info[8]=(sm.getPostId()==null || "".equals(sm.getPostId()))?"":(basedataService.selectByBaseDataKey(Long.parseLong((sm.getPostId()==null)?"":sm.getPostId().toString()))).getBaseDataName();
//				info[9]=(sm.getWorkLocation()==null || "".equals(sm.getWorkLocation()))?"":(basedataService.selectByBaseDataKey(Long.parseLong((sm.getWorkLocation()==null)?"":sm.getWorkLocation().toString()))).getBaseDataName();
//				info[10]=sm.getReportObject();
//				info[11]=sm.getMobile();
//				info[12]=sm.getEmail();
//				info[13]=(sm.getEmploymentType()==null)?"":sm.getEmploymentType().toString();
//				
//				row = sheet.createRow((short) j);
//				for (int i = 1; i <= 14; i++) {
//					HSSFCell cell = row.createCell((short) (i-1));
//					// 设置单元格格式
//					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//					cell.setCellStyle(cellStyle);
//					cell.setCellValue(info[i-1]);
//				}
//				j++;
//			}
//			
//			request.setAttribute("message", "文件生成成功！");
//			response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");
//			response.setHeader("Content-Disposition","attachment; filename=" + new String("addresslist.xls"));
//			OutputStream output=response.getOutputStream();
//			try   {    
//		         output.flush();    
//		         workbook.write(output);    
//		         output.close();   
//			}catch(IOException e)   {    
//		         e.printStackTrace();    
//			}    
//		}catch (Exception ioexception) {
//			ioexception.printStackTrace();
//			request.setAttribute("message", "创建文件失败！");
//		}
	}
	
	private ExcelConfig getExcelConfig(String sheetName,List<EhrPerson> personList,List<ExcelHeader> headers,EhrUser user) throws Exception{
		List<JSONObject> datas = new ArrayList<JSONObject>();
		JSONObject jo = null;
		String org[] = null;
		int seq = 0;
		for (EhrPerson person : personList) {
			jo = JSONObject.fromObject(person);
//			if(person.getCol(6) != null && !"".equals(person.getCol(6)) && !"0".equals(person.getCol(6))){
//				jo.put("costCode",basedataService.selectByBaseDataKey(Long.parseLong(person.getCol(6))).getBaseDataCode());
//			}
			org = this.getOrg(person, user);
			jo.put("seq",  ++ seq);
			jo.put("org0", org[0]);
			jo.put("org1", org[1]);
			jo.put("org2", org[2]);
			jo.put("org3", org[3]);
			if(!(person.getTeam() == null || "".equals(person.getTeam()) || person.getTeam()==0)){
				jo.put("team", basedataService.selectByBaseDataKey(person.getTeam()).getBaseDataName());
			}
			if(!(person.getCostId()==null || "".equals(person.getCostId()))){
				jo.put("cost",basedataService.selectByBaseDataKey(Long.parseLong((person.getCostId()==null) ? "":person.getCostId().toString())).getBaseDataName());
			}
			if(!(person.getPostId()==null || "".equals(person.getPostId()))){
				jo.put("post",basedataService.selectByBaseDataKey(Long.parseLong((person.getPostId()==null) ? "":person.getPostId().toString())).getBaseDataName());
			}
			if(!(person.getWorkLocation()==null || "".equals(person.getWorkLocation()))){
				jo.put("workLocation", basedataService.selectByBaseDataKey(Long.parseLong((person.getWorkLocation()==null)?"":person.getWorkLocation().toString())).getBaseDataName());
			}
			datas.add(jo);
		}

		return new ExcelConfig(sheetName, headers, datas);
	}
	
	private List<Map<String,Object>> getExcelData(List<EhrPerson> personList,EhrUser user) throws Exception{
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		String org[] = null;
		int seq = 0;
		for (EhrPerson person : personList) {
			map = new HashMap<String,Object>();
//			if(person.getCol(6) != null && !"".equals(person.getCol(6)) && !"0".equals(person.getCol(6))){
//				map.put("costCode",basedataService.selectByBaseDataKey(Long.parseLong(person.getCol(6))).getBaseDataCode());
//			}
			org = this.getOrg(person, user);
			map.put("seq",  ++ seq);
			map.put("org0", org[0]);
			map.put("org1", org[1]);
			map.put("org2", org[2]);
			map.put("org3", org[3]);
			if(!(person.getTeam() == null || "".equals(person.getTeam()) || person.getTeam()==0)){
				map.put("team", basedataService.selectByBaseDataKey(person.getTeam()).getBaseDataName());
			}
			if(!(person.getCostId()==null || "".equals(person.getCostId()))){
				map.put("cost",basedataService.selectByBaseDataKey(Long.parseLong((person.getCostId()==null) ? "":person.getCostId().toString())).getBaseDataName());
			}
			map.put("employeeName", person.getEmployeeName());
			if(!(person.getPostId()==null || "".equals(person.getPostId()))){
				map.put("post",basedataService.selectByBaseDataKey(Long.parseLong((person.getPostId()==null) ? "":person.getPostId().toString())).getBaseDataName());
			}
			if(!(person.getWorkLocation()==null || "".equals(person.getWorkLocation()))){
				map.put("workLocation", basedataService.selectByBaseDataKey(Long.parseLong((person.getWorkLocation()==null)?"":person.getWorkLocation().toString())).getBaseDataName());
			}
			map.put("reportObject", person.getReportObject());
			map.put("mobile", person.getMobile());
			map.put("email", person.getEmail());
			map.put("employmentType", person.getEmploymentType());
			
			datas.add(map);
		}

		return datas;
	}
	
	private String[] getOrg(EhrPerson person,EhrUser user){
		String org[] = new String[4];
		String mycompanyid = person.getCol(2);
		if(!mycompanyid.equals("0")){
			org[0] = user.getCompany().getCompanyName();
			EhrOrganization eor = organizationSerevice.selectByorganizationId(Long.parseLong(mycompanyid));
			if(eor!=null){
				int level=eor.getOrganizationLevel();
				long parentid=eor.getParentId();
				
				if(level >= 3){
					level = 3;
				}
				
				if(level!=0)
					org[level]=eor.getOrganizationName();
				
				while(level>1){
					eor = organizationSerevice.selectByorganizationId(parentid);
					if(eor!=null){
					level=eor.getOrganizationLevel();
					if(level >= 3){
						level = 3;
					}
					parentid=eor.getParentId();
					org[level]=eor.getOrganizationName();
					}
				}
			}
		}
		
		return org;
	}
	
}
