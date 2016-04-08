package com.jabava.pojo.manage;

public class EhrUserPersonPowerValue {
	private Long userPersonPowerValue;
	
	private Long userPersonPowerId;
	
	private Integer fieldId;
	
	private String fieldKey;
	
	private String fieldValue;

    public Long getUserPersonPowerId() {
        return userPersonPowerId;
    }

    public void setUserPersonPowerId(Long userPersonPowerId) {
        this.userPersonPowerId = userPersonPowerId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue == null ? null : fieldValue.trim();
    }

	public Long getUserPersonPowerValue() {
		return userPersonPowerValue;
	}

	public void setUserPersonPowerValue(Long userPersonPowerValue) {
		this.userPersonPowerValue = userPersonPowerValue;
	}

	public String getFieldKey() {
		return fieldKey;
	}

	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}
    
}