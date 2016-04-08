package com.jabava.pojo.manage;

public class EhrFunctionPoint {
    private Integer functionPointCode;

    private String functionPointName;

    public Integer getFunctionPointCode() {
        return functionPointCode;
    }

    public void setFunctionPointCode(Integer functionPointCode) {
        this.functionPointCode = functionPointCode;
    }

    public String getFunctionPointName() {
        return functionPointName;
    }

    public void setFunctionPointName(String functionPointName) {
        this.functionPointName = functionPointName == null ? null : functionPointName.trim();
    }
}