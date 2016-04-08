package com.jabava.pojo.manage;

import java.util.List;

public class EhrPersonField {
    private Integer fieldId;

    private String fieldName;

    private String fieldDesc;

    private Byte fieldType;

    private String relateSql;
    
    private List<EhrPersonFieldFixvalue> fieldValueList;

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc == null ? null : fieldDesc.trim();
    }

    public Byte getFieldType() {
        return fieldType;
    }

    public void setFieldType(Byte fieldType) {
        this.fieldType = fieldType;
    }

    public String getRelateSql() {
        return relateSql;
    }

    public void setRelateSql(String relateSql) {
        this.relateSql = relateSql == null ? null : relateSql.trim();
    }

	public List<EhrPersonFieldFixvalue> getFieldValueList() {
		return fieldValueList;
	}

	public void setFieldValueList(List<EhrPersonFieldFixvalue> fieldValueList) {
		this.fieldValueList = fieldValueList;
	}
    
    
}