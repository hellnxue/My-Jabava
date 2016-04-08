package com.jabava.pojo.manage;

public class EhrPersonFieldFixvalue {
    private Long personFieldFixvalueId;

    private Integer fieldId;

    private String fieldKey;

    private String fieldValue;

    public Long getPersonFieldFixvalueId() {
        return personFieldFixvalueId;
    }

    public void setPersonFieldFixvalueId(Long personFieldFixvalueId) {
        this.personFieldFixvalueId = personFieldFixvalueId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public void setFieldKey(String fieldKey) {
        this.fieldKey = fieldKey == null ? null : fieldKey.trim();
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue == null ? null : fieldValue.trim();
    }
}