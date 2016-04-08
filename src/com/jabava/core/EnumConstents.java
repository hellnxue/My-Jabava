package com.jabava.core;

/**
 * 
 *
 * @version $Id: EnumConstents.java, 
 * v 0.1 2015年12月28日 上午11:35:28 
 * <pre>
 * @author steven.chen
 * @date 2015年12月28日 上午11:35:28 
 * </pre>
 */
public class EnumConstents {
	
	
   /**
    * 是否排列优先
    *
    * @version $Id: EnumConstents.java, 
    * v 0.1 2015年12月28日 上午11:40:26 
    * <pre>
    * @author steven.chen
    * @date 2015年12月28日 上午11:40:26 
    * </pre>
    */
	public enum IsPriority {
		
		/** 优先 */
		FIRST (1),
		/** 普通  */
		NORMAL(0);			
		IsPriority(Integer value) {
			this.value = value;
		}	
		private Integer value;	
		
		public Integer getValue() {
			return value;
		}	
		public void setValue(Integer value) {
			this.value = value;
		}

	}
	/**
	 * 信息类型
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2015年12月28日 上午11:43:55 
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月28日 上午11:43:55 
	 * </pre>
	 */
	public enum InformationType {
		/** 平台信息 */
		PLAT(1),
		/** 公司信息 */
		COMPANY(2);
		
		InformationType(Integer value) {
			this.value = value;
		}	
		private Integer value;	
		
		public Integer getValue() {
			return value;
		}	
		public void setValue(Integer value) {
			this.value = value;
		}
	}
	/**
	 * 信息范围
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2015年12月28日 上午11:47:45 
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月28日 上午11:47:45 
	 * </pre>
	 */
	public enum InformationRange {
		/** 全部人员 */
		ALL(1),
		/** 全部系统管理员 */
		ALL_MANAGER(2),
		/** 全部公司人员 */
		ALL_COMPANY_USER(3);
		
		InformationRange(Integer value) {
			this.value = value;
		}	
		private Integer value;	
		
		public Integer getValue() {
			return value;
		}	
		public void setValue(Integer value) {
			this.value = value;
		}
	}
	/**
	 * 是否已读
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2015年12月28日 上午11:50:43 
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月28日 上午11:50:43 
	 * </pre>
	 */
	public enum HasRead {
		/** 未读 */
		UNREAD(0),
		/** 已读 */
		READ(1);
		
		HasRead(Integer value) {
			this.value = value;
		}	
		private Integer value;	
		
		public Integer getValue() {
			return value;
		}	
		public void setValue(Integer value) {
			this.value = value;
		}
	}
	/**
	 * 是否有详细信息
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2015年12月28日 下午2:17:35 
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月28日 下午2:17:35 
	 * </pre>
	 */
	public enum HasDetail {
		/** 有详细信息 */
		HAS_DETAIL(true),
		/** 没有详细信息 */
		NO_DETAIL(false);
		
		HasDetail(Boolean value) {
			this.value = value;
		}	
		private Boolean value;	
		
		public Boolean getValue() {
			return value;
		}	
		public void setValue(Boolean value) {
			this.value = value;
		}
	}
	/**
	 * 是否删除
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2015年12月28日 下午2:24:47 
	 * <pre>
	 * @author steven.chen
	 * @date 2015年12月28日 下午2:24:47 
	 * </pre>
	 */
	public enum IsDeleted {
		/** 正常  */
		UN_DELETED(0),
		/** 已删除 */
		DELETED(1);
		
		IsDeleted(Integer value) {
			this.value = value;
		}	
		private Integer value;	
		
		public Integer getValue() {
			return value;
		}	
		public void setValue(Integer value) {
			this.value = value;
		}
	}
	/**
	 * 协议状态
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年1月8日 上午10:23:54 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月8日 上午10:23:54 
	 * </pre>
	 */
	public enum ProtocolStatus	{
		/** 未开通 */
		NOT_OPEN(0),
		/** 启用 */
		 ENABLE(2),
		 /** 停用  */
		 DISABLE(3);
		ProtocolStatus(Integer value) {
			this.value = value;
		}	
		private Integer value;	
		
		public Integer getValue() {
			return value;
		}	
		public void setValue(Integer value) {
			this.value = value;
		} 
	}

	/**
	 * 树形节点移动时，与目标节点的相对位置
	 * prev-作为目标节点的上一个兄弟节点
	 * inner-作为目标节点的最后一个子节点
	 * next-作为目标节点的下一个兄弟节点
	 */
	public enum NodeMoveFlag {
		PREV_BROTHER("prev"),
		LAST_CHILDREN("inner"),
		NEXT_BROTHER("next");
		
		NodeMoveFlag(String value) {
			this.value = value;
		}	
		private String value;	
		
		public String getValue() {
			return value;
		}	
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	/**
	 * 证件类型
	 * 
	 * 0-身份证，1-军人证，2-护照，3-其它
	 */
	public enum CardType{
		ID_CARD(0),	
		MILITARY_CARD(1),
		PASSPORT(2),
		OTHER(3);
		
		CardType(int value){
			this.value = value;
		}
		
		private int value;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
		
	}
	/**
	 * 4:增员已确认,7:变更已确认,10:减员已确认,13撤销减员  
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年1月29日 下午7:34:32 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月29日 下午7:34:32 
	 * </pre>
	 */
	public enum OrderStatus{
		
		/** 4:增员已确认 */
		ADD_CONFIRM(4L),
		/** 7:变更已确认 */
		CHANGE_CONFIRM(7L),
		/** 10:减员已确认 */
		REDUCE_CONFIRM(10L),
		/** 13撤销减员 */
		CANCEL_REDUCE(13L);
		OrderStatus(Long value) {
			this.value = value;
		}

		private Long value;

		public Long getValue() {
			return value;
		}

		public void setValue(Long value) {
			this.value = value;
		}	
	}
	/**
	 * 雇佣状态
	 * 1 入职中   2 在职   3离职中 4 离职
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年1月29日 下午7:42:36 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月29日 下午7:42:36 
	 * </pre>
	 */
	public enum HireStatus{
		/**1 入职中 */
		ENTRY("1"),
		/** 2 在职 */
		JOB("2"),
		/** 3离职中 */
		LEAVING("3"),
		/** 4 离职 */
		LEAVE ("4");
		
		HireStatus(String value) {
			this.value = value;
		}

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}	
		
	}
	/**
	 * 性别 1 男 2 女 
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年1月29日 下午7:46:38 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月29日 下午7:46:38 
	 * </pre>
	 */
	public enum Gender{
		
		BOY("1"),
		GIRL("2");
		Gender(String value) {
			this.value = value;
		}

		private String value;

		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}	
		
	}
	/**
	 * 证件类型
	 * 1 身份证 2  军人证 3 港澳身份证 4 台胞证 5 护照 9 其它
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年1月29日 下午7:49:11 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月29日 下午7:49:11 
	 * </pre>
	 */
	public enum HroCardType {
		/** 1 身份证 */
		 IDENTITY_CARD("1"),
			/** 2  军人证  */
		 SOLDIER_CARD ("2"),
		 /** 3 港澳身份证  */
		 HONG_KONG_CARD("3"),
		 /** 4 台胞证*/
		 MTP("4"),
		 /** 5 护照 */
		 PASSPORT("5"),
		 /** 9 其它 */
		 OTHER("9");
		 HroCardType(String value) {
			this.value = value;
		}

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}		
	}
	/**
	 * 户口类型
    	0	不限
		1	本地城镇
		2	外地城镇
		3	本地农村
		4	外地农村
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年1月29日 下午8:03:05 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月29日 下午8:03:05 
	 * </pre>
	 */
	public enum AccountType{
		NO_LIMIT("0"), LOCAL_TOWNS("1"), OTHER_TOWNS("2"), LOCAL_RURAL_AREAS("3"), RURAL_AREAS(
				"4");
		AccountType(String value) {
			this.value = value;
		}

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
	/**
	 * 学历
	 *
	 *学历 
	1	博士
	2	硕士
	3	大学
	4	大专
	5	中专
	6	技校
	7	高中
	8	职高
	9	初中
	10	小学
	11	文盲或半文盲
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年1月29日 下午8:05:19 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月29日 下午8:05:19 
	 * </pre>
	 */
	public enum Education{
		DOCTOR(1),

		MASTER(2),

		UNIVERSITY(3),

		COLLEGE(4),

		TECHNICAL_SECONDARY_SCHOOL(5),

		TECHNICAL_SCHOOL(6),

		HIGH_SCHOOL(7),

		VOCATIONAL_HIGH_SCHOOL(8),

		JUNIOR_HIGH_SCHOOL(9),

		PRIMARY_SCHOOL(10),

		ILLITERATE(11);
		
		Education(Integer value) {
			this.value = value;
		}

		private Integer value;

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
		
	}
	/**
	 * 离职原因
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年1月29日 下午8:10:23 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年1月29日 下午8:10:23 
	 * </pre>
	 */
	public enum DimissionType{
		DIMISSION_TYPE_1("1"), DIMISSION_TYPE_2("2"), DIMISSION_TYPE_3("3"), DIMISSION_TYPE_4(
				"4"), DIMISSION_TYPE_5("5"), DIMISSION_TYPE_6("6"), DIMISSION_TYPE_7(
				"7"), DIMISSION_TYPE_8("8"), DIMISSION_TYPE_9("9"), DIMISSION_TYPE_10(
				"10"), DIMISSION_TYPE_11("11");
		
		DimissionType(String value) {
			this.value = value;
		}

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	/**
	 * 社保状态
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年3月3日 下午2:43:18 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月3日 下午2:43:18 
	 * </pre>
	 */
	public enum SbStatus {
		/** 未申请 */      /** 已申请 */ /** 驳回*/  /**已缴纳 *//**未缴纳 */
		NOT_APPLY(1),	APPLY(2), REJECT(3), PAY(4), NOT_PAY(5);		
		
		private Integer value;
		SbStatus(Integer value) {
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
	 * 公积金状态
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年3月3日 下午2:44:55 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月3日 下午2:44:55 
	 * </pre> 
	 */
	public enum GjjStatus {
		/** 未申请 */      /** 已申请 */   /** 驳回*/ /**已缴纳 */  /**未缴纳 */
		NOT_APPLY(1),	APPLY(2),   REJECT(3), PAY(4),   NOT_PAY(5);		
		
		private Integer value;
		GjjStatus(Integer value) {
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
	 * 合同类型
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年3月11日 上午12:50:44 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月11日 上午12:50:44 
	 * </pre>
	 */
	public enum ContractType {
		/** 人才派遣 */            /** 人事代理 */
		ContractType_1(1),	ContractType_2(2);		
		
		private Integer value;
		ContractType(Integer value) {
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
	 * 劳动合同类型
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年3月23日 下午4:09:24 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月23日 下午4:09:24 
	 * </pre>
	 */
	public enum WorkContractType	{
		/** 1 固定期限 */
		WORK_CONTRACT_TYPE_1(1),
		/** 2无固定期限 */
		WORK_CONTRACT_TYPE_2(2),
		/** 3以完成一定工作量的协议 */
		WORK_CONTRACT_TYPE_3(3),
		/** 4劳务 */
		WORK_CONTRACT_TYPE_4(4),
		/** 5派遣*/
		WORK_CONTRACT_TYPE_5(5),
		/** 6实习*/
		WORK_CONTRACT_TYPE_6(6);		
		private Integer value;
		WorkContractType(Integer value) {
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
	 * 劳动合同性质
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年3月23日 下午4:15:20 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月23日 下午4:15:20 
	 * </pre>
	 */
	public enum WorkContractProperty {
		/** 1 首签 */
		FIRST_SIGN(1),
		/** 2续签 */
		RENEWAL(2);
		
		private Integer value;
		WorkContractProperty(Integer value) {
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
	 * 转正性质
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年3月24日 下午1:50:39 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月24日 下午1:50:39 
	 * </pre>
	 */
	public enum PositiveType {
		/** 1 提前 */
		ADVANCE(1),
		/** 2按期 */
		ON_SCHEDULE(2),
		/** 3 延期*/
		DELAY(3);
		
		private Integer value;
		PositiveType(Integer value) {
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
	 * 惩奖类型
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年3月24日 下午3:22:26 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月24日 下午3:22:26 
	 * </pre>
	 */
	public enum RewardType {
		/** 1 奖励 */
		REWARD(1),
		/** 2惩罚 */
		PUNISHMENT(2);
		
		private Integer value;
		RewardType(Integer value) {
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
	 * 离职原因
	 *
	 * @version $Id: EnumConstents.java, 
	 * v 0.1 2016年3月25日 下午2:16:00 
	 * <pre>
	 * @author steven.chen
	 * @date 2016年3月25日 下午2:16:00 
	 * </pre>
	 */
	public enum DimissionCause {
		/** 1 辞职 */
		DimissionCause_1(1),
		/** 2 辞退 */
		DimissionCause_2(2),
		/** 3 协商解除 */
		DimissionCause_3(3),
		/** 4 合同到期终止 */
		DimissionCause_4(4),
		/** 5 其他 */
		DimissionCause_5(5);
		
		private Integer value;
		DimissionCause(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
	}
}
