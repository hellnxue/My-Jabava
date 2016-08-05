package com.jabava.utils.enums;

/**
 * 
 *
 * @version $Id: SsAfEnum.java, 
 * v 0.1 2015年12月28日 上午11:35:28 
 * <pre>
 * @author steven.chen
 * @date 2015年12月28日 上午11:35:28 
 * </pre>
 */
public class SsAfEnum {

	/**
	 * 社保缴费清单状态
	 * 1-已生成
	 * 2-已锁定
	 * 3-已作废
	 */
	public enum PaymentBillStatus {
		/** 1-已生成 */
		Created(1),
		/** 2-已锁定 */
		Locked(2),
		/** 3-已作废 */
		Cancelled(3);		
		
		private Integer value;
		PaymentBillStatus(Integer value) {
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
