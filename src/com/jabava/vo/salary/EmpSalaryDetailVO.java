package com.jabava.vo.salary;

import java.math.BigDecimal;

public class EmpSalaryDetailVO {
	private String empName;
	private Integer cardType;
	private String cardId;
	private BigDecimal amountSalary;
	private BigDecimal amountSalaried;
	private Integer taxType;
	private String bankName;
	private String payCard;
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public BigDecimal getAmountSalary() {
		return amountSalary;
	}
	public void setAmountSalary(BigDecimal amountSalary) {
		this.amountSalary = amountSalary;
	}
	public BigDecimal getAmountSalaried() {
		return amountSalaried;
	}
	public void setAmountSalaried(BigDecimal amountSalaried) {
		this.amountSalaried = amountSalaried;
	}
	public Integer getTaxType() {
		return taxType;
	}
	public void setTaxType(Integer taxType) {
		this.taxType = taxType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPayCard() {
		return payCard;
	}
	public void setPayCard(String payCard) {
		this.payCard = payCard;
	}
	
}
