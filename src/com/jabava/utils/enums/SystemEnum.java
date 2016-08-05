package com.jabava.utils.enums;

public class SystemEnum {
	/**
	 * 系统操作状态
	 * 1、add  增加
	 * 2、delete  修改 
	 * 3、update  查询
	 * 4、select  查看
	 * 5、upload  上传
	 * 6、download 下载
	 * 7、login  登录
	 */
	public enum LogOperateType{
		/**1 增加	*/
		Add(1),
		/**2 删除	*/
		Delete(2),
		/**3 修改	*/
		Update(3),
		/**4 查询	*/
		Select(4),
		/**5 上传	*/
		Upload(5),
		/**6 下载	*/
		Download(6),
		/**7、登录 */
		Login(7);
		private Integer value;
		LogOperateType(Integer value ){
			this.value=value;
		}
		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
		
	}
	/**
	 * 功能模块
	 * personnel("组织人事管理",1)
	 * fund("社保公积金",2)
	 * salary("薪资管理",3)
	 * supplier("供应商管理",4)
	 */
	public enum Module{
		 Organization("组织人事管理",1),
		 Ssaf("社保公积金",2),
		 Salary("薪资管理",3),
		 Report("报表管理",4),
		 Supplier("供应商管理",5),
		 Login("登录",6),
		 InformationBulletin("信息公告",7),
		 UserManagement("用户管理",8),
		 SystemManagement("系统管理",9);
		private String moduleName;
		private Integer value;
        Module (String moduleName,Integer value){
			this.moduleName=moduleName;
			this.value=value;
		}
		public Integer getValue() {
			return value;
		}
		public void setValue(Integer value) {
			this.value = value;
		}
		
		public String getModuleName() {
			return moduleName;
		}
		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}
	}
	
	public enum Function{
		/**1 组织架构		 */
		SearchOrganization(1),
		/**2 入职管理		 */
		AddPerson(2),
		/**3 花名册		 */
		EmployeeList(3),
		/**4 社保账户		 */
		SocialSecurityAccountMain(4),
		/**5 公积金账户		 */
		AccumulationFundAccountMain(5),
		/**6 员工社保档案		 */
		SecurityProfile(6),
		/**7 社保汇缴清单		 */
		SsListPaymentBill(7),
		/**8 公积金汇缴清单		 */
		AfListPaymentBill(8),
		/**9 薪资方案管理		 */
		ListSalaryChangeDef(9),
		/**10 员工薪酬档案管理		 */
		ListSalary(10),
		/**11 外部数据导入		 */
		ListSalaryChangeData(11),
		/**12 薪酬生成与查询		 */
		ListMonthlySalary(12),
		/**13 生成报表		 */
		GenerateReport(13),
		/**14 服务开通		 */
		ServiceOpen(14),
		/**15 服务单管理		 */
		ListProtocol(15),
		/**16 上传增减变表		 */
		UploadChange(16),
		/**17 订单增减表反馈		 */
		ListQueryChange(17),
		/**18 订单		 */
		OrderMain(18),
		/**19 账单		 */
		BillList(19);
		private Integer value;
		Function(Integer value){
			this.value=value;
		}
		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}
}
