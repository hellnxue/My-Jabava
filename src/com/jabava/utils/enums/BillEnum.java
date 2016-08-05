package com.jabava.utils.enums;

public class BillEnum{
	/**
	 * 账单交互状态
	 * 1：待确认；2：已确认；3：已驳回；4：已作废
	 */
	public enum BillStatus {
		/** 1：待确认 */
		ToBeConfirmed(1),
		/** 2：已确认 */
		Confirmed(2),
		/** 3：已驳回 */
		Rejected(3),
		/** 4：已作废 */
		Canceled(4);
		
		private int value;
		BillStatus(int value){
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}
	}
}
