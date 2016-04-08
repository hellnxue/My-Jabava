package com.jabava.utils.enums;

/**
 * 
 *
 * @version $Id: SalaryEnum.java, 
 * v 0.1 2015年12月28日 上午11:35:28 
 * <pre>
 * @author steven.chen
 * @date 2015年12月28日 上午11:35:28 
 * </pre>
 */
public class SalaryEnum {

	/**
	 * 工资变动项数据类型
	 * 1-字符类型
	 * 2-数值类型
	 */
	public enum SalaryChangeDefItemType {
		/** 1-字符类型 */
		Character(1),
		/** 2-数值类型 */
		Number(2);		
		
		private Integer value;
		SalaryChangeDefItemType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}

	/**
	 * 计税方法
	 * 1-合并计税
	 * 2-独立计税
	 */
	public enum SalaryTaxRule {
		/** 1-合并计税 */
		Coalescent(1),
		/** 2-独立计税 */
		Separated(2);
		
		private Integer value;
		SalaryTaxRule(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}

	/**
	 * 合并计算规则
	 * 1-税前计入
	 * 2-税后计入
	 */
	public enum SalaryCalculateRule {
		/** 1-税前计入 */
		BeforeTax(1),
		/** 2-税后计入 */
		AfterTax(2);
		
		private Integer value;
		SalaryCalculateRule(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}

	/**
	 * 项目类别
	 * 1-加项
	 * 2-减项
	 */
	public enum SalaryItemType {
		/** 1-加项 */
		AddItem(1),
		/** 2-减项 */
		SubtractItem(2);
		
		private Integer value;
		SalaryItemType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}

	/**
	 * 项目标识
	 * 0-普通
	 * 1-年终奖
	 * 2-养老
	 * 3-医疗
	 * 4-失业
	 * 5-公积金
	 */
	public enum SalaryItemFlag {
		/** 0-普通 */
		Common(0),
		/** 1-年终奖 */
		AnnualBonus(1),
		/** 2-养老 */
		EndowmentInsurance(2),
		/** 3-医疗 */
		MedicalInsurance(3),
		/** 4-失业 */
		UnemployedInsurance(4),
		/** 5-公积金 */
		AccumulationFund(5);
		
		private Integer value;
		SalaryItemFlag(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}

	/**
	 * 系统变动表
	 * -1-ehr_attendance-考勤表
	 * -2-ehr_social_insurance-社保表
	 */
	public enum SystemChangeTable {
		/** -1-ehr_attendance-考勤表 */
		EhrAttendance("ehr_attendance",-1L,"考勤表"),
		/** -2-ehr_social_insurance-社保表 */
		EhrSocialInsurance("ehr_social_insurance",-2L,"社保表");
		
		private String tableName;
		private Long id;
		private String displayName;
		SystemChangeTable(String tableName, Long id, String displayName) {
			this.tableName = tableName;
			this.id = id;
			this.displayName = displayName;
		}

		public String getTableName() {
			return this.tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		
	}
}
