package com.jabava.pojo.manage;

import java.util.List;

public class EhrUserBusinessPower {
    private Long userPersonPowerId;

    private Long userId;

    private Integer fieldId;

    private Integer seqNo;

    private Byte fieldType;

    private Byte operateType;

    private String fieldValue;

    private Integer functionPointCode;
    
	private List<EhrUserPersonPowerValue> powerValueList;
	
	private EhrPersonField personField;
	
	private Byte ftype;

    public Byte getFtype() {
		return ftype;
	}

	public void setFtype(Byte ftype) {
		this.ftype = ftype;
	}

	public Long getUserPersonPowerId() {
        return userPersonPowerId;
    }

    public void setUserPersonPowerId(Long userPersonPowerId) {
        this.userPersonPowerId = userPersonPowerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    public Byte getFieldType() {
        return fieldType;
    }

    public void setFieldType(Byte fieldType) {
        this.fieldType = fieldType;
    }

    public Byte getOperateType() {
        return operateType;
    }

    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue == null ? null : fieldValue.trim();
    }

    public Integer getFunctionPointCode() {
        return functionPointCode;
    }

    public void setFunctionPointCode(Integer functionPointCode) {
        this.functionPointCode = functionPointCode;
    }

	public List<EhrUserPersonPowerValue> getPowerValueList() {
		return powerValueList;
	}

	public void setPowerValueList(List<EhrUserPersonPowerValue> powerValueList) {
		this.powerValueList = powerValueList;
	}

	public EhrPersonField getPersonField() {
		return personField;
	}

	public void setPersonField(EhrPersonField personField) {
		this.personField = personField;
	}
    
    
}