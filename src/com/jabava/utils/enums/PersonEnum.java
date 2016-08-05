package com.jabava.utils.enums;

public class PersonEnum{
	/**
	 * 岗位变动类型
	 * 1：入职；2：转正；3：调配；4：离职
	 */
	public enum PostChangeType {
		/** 1：入职 */
		Entry(1),
		/** 2：转正 */
		BecomeMember(2),
		/** 3：调配 */
		TransferPost(3),
		/** 4：离职 */
		Dimission(4);
		
		private int value;
		PostChangeType(int value){
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
