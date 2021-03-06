package com.jabava.pojo.manage;

import java.math.BigDecimal;
import java.util.Date;

public class EhrAttendance {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.attend_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private Long attendId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.person_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private Long personId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.year_month_record
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private String yearMonthRecord;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.late_times
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private Byte lateTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.leave_early_times
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private Byte leaveEarlyTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.absent_time
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private Byte absentTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.all_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal allLeave;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.sick_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal sickLeave;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.business
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal business;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.annual_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal annualLeave;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.work_overtime
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal workOvertime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.week_overtime
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal weekOvertime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.holidays_overtime
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal holidaysOvertime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.adjust_day
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal adjustDay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.night_shift
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal nightShift;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.total_annual
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal totalAnnual;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.surplus_annual
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal surplusAnnual;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.marriage_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal marriageLeave;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.maternity_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal maternityLeave;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.paternity_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal paternityLeave;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.funeral_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal funeralLeave;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.memo
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.create_user_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.create_user_name
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private String createUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.create_date
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.last_modify_user_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private Long lastModifyUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.last_modify_user_name
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private String lastModifyUserName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.last_modify_date
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private Date lastModifyDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.day_shift
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private BigDecimal dayShift;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ehr_attendance.times
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    private Byte times;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.attend_id
     *
     * @return the value of ehr_attendance.attend_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public Long getAttendId() {
        return attendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.attend_id
     *
     * @param attendId the value for ehr_attendance.attend_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setAttendId(Long attendId) {
        this.attendId = attendId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.person_id
     *
     * @return the value of ehr_attendance.person_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.person_id
     *
     * @param personId the value for ehr_attendance.person_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.year_month_record
     *
     * @return the value of ehr_attendance.year_month_record
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public String getYearMonthRecord() {
        return yearMonthRecord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.year_month_record
     *
     * @param yearMonthRecord the value for ehr_attendance.year_month_record
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setYearMonthRecord(String yearMonthRecord) {
        this.yearMonthRecord = yearMonthRecord;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.late_times
     *
     * @return the value of ehr_attendance.late_times
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public Byte getLateTimes() {
        return lateTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.late_times
     *
     * @param lateTimes the value for ehr_attendance.late_times
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setLateTimes(Byte lateTimes) {
        this.lateTimes = lateTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.leave_early_times
     *
     * @return the value of ehr_attendance.leave_early_times
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public Byte getLeaveEarlyTimes() {
        return leaveEarlyTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.leave_early_times
     *
     * @param leaveEarlyTimes the value for ehr_attendance.leave_early_times
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setLeaveEarlyTimes(Byte leaveEarlyTimes) {
        this.leaveEarlyTimes = leaveEarlyTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.absent_time
     *
     * @return the value of ehr_attendance.absent_time
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public Byte getAbsentTime() {
        return absentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.absent_time
     *
     * @param absentTime the value for ehr_attendance.absent_time
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setAbsentTime(Byte absentTime) {
        this.absentTime = absentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.all_leave
     *
     * @return the value of ehr_attendance.all_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getAllLeave() {
        return allLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.all_leave
     *
     * @param allLeave the value for ehr_attendance.all_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setAllLeave(BigDecimal allLeave) {
        this.allLeave = allLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.sick_leave
     *
     * @return the value of ehr_attendance.sick_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getSickLeave() {
        return sickLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.sick_leave
     *
     * @param sickLeave the value for ehr_attendance.sick_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setSickLeave(BigDecimal sickLeave) {
        this.sickLeave = sickLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.business
     *
     * @return the value of ehr_attendance.business
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getBusiness() {
        return business;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.business
     *
     * @param business the value for ehr_attendance.business
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setBusiness(BigDecimal business) {
        this.business = business;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.annual_leave
     *
     * @return the value of ehr_attendance.annual_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getAnnualLeave() {
        return annualLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.annual_leave
     *
     * @param annualLeave the value for ehr_attendance.annual_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setAnnualLeave(BigDecimal annualLeave) {
        this.annualLeave = annualLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.work_overtime
     *
     * @return the value of ehr_attendance.work_overtime
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getWorkOvertime() {
        return workOvertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.work_overtime
     *
     * @param workOvertime the value for ehr_attendance.work_overtime
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setWorkOvertime(BigDecimal workOvertime) {
        this.workOvertime = workOvertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.week_overtime
     *
     * @return the value of ehr_attendance.week_overtime
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getWeekOvertime() {
        return weekOvertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.week_overtime
     *
     * @param weekOvertime the value for ehr_attendance.week_overtime
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setWeekOvertime(BigDecimal weekOvertime) {
        this.weekOvertime = weekOvertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.holidays_overtime
     *
     * @return the value of ehr_attendance.holidays_overtime
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getHolidaysOvertime() {
        return holidaysOvertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.holidays_overtime
     *
     * @param holidaysOvertime the value for ehr_attendance.holidays_overtime
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setHolidaysOvertime(BigDecimal holidaysOvertime) {
        this.holidaysOvertime = holidaysOvertime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.adjust_day
     *
     * @return the value of ehr_attendance.adjust_day
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getAdjustDay() {
        return adjustDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.adjust_day
     *
     * @param adjustDay the value for ehr_attendance.adjust_day
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setAdjustDay(BigDecimal adjustDay) {
        this.adjustDay = adjustDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.night_shift
     *
     * @return the value of ehr_attendance.night_shift
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getNightShift() {
        return nightShift;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.night_shift
     *
     * @param nightShift the value for ehr_attendance.night_shift
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setNightShift(BigDecimal nightShift) {
        this.nightShift = nightShift;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.total_annual
     *
     * @return the value of ehr_attendance.total_annual
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getTotalAnnual() {
        return totalAnnual;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.total_annual
     *
     * @param totalAnnual the value for ehr_attendance.total_annual
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setTotalAnnual(BigDecimal totalAnnual) {
        this.totalAnnual = totalAnnual;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.surplus_annual
     *
     * @return the value of ehr_attendance.surplus_annual
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getSurplusAnnual() {
        return surplusAnnual;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.surplus_annual
     *
     * @param surplusAnnual the value for ehr_attendance.surplus_annual
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setSurplusAnnual(BigDecimal surplusAnnual) {
        this.surplusAnnual = surplusAnnual;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.marriage_leave
     *
     * @return the value of ehr_attendance.marriage_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getMarriageLeave() {
        return marriageLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.marriage_leave
     *
     * @param marriageLeave the value for ehr_attendance.marriage_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setMarriageLeave(BigDecimal marriageLeave) {
        this.marriageLeave = marriageLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.maternity_leave
     *
     * @return the value of ehr_attendance.maternity_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getMaternityLeave() {
        return maternityLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.maternity_leave
     *
     * @param maternityLeave the value for ehr_attendance.maternity_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setMaternityLeave(BigDecimal maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.paternity_leave
     *
     * @return the value of ehr_attendance.paternity_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getPaternityLeave() {
        return paternityLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.paternity_leave
     *
     * @param paternityLeave the value for ehr_attendance.paternity_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setPaternityLeave(BigDecimal paternityLeave) {
        this.paternityLeave = paternityLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.funeral_leave
     *
     * @return the value of ehr_attendance.funeral_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getFuneralLeave() {
        return funeralLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.funeral_leave
     *
     * @param funeralLeave the value for ehr_attendance.funeral_leave
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setFuneralLeave(BigDecimal funeralLeave) {
        this.funeralLeave = funeralLeave;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.memo
     *
     * @return the value of ehr_attendance.memo
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.memo
     *
     * @param memo the value for ehr_attendance.memo
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.create_user_id
     *
     * @return the value of ehr_attendance.create_user_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.create_user_id
     *
     * @param createUserId the value for ehr_attendance.create_user_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.create_user_name
     *
     * @return the value of ehr_attendance.create_user_name
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.create_user_name
     *
     * @param createUserName the value for ehr_attendance.create_user_name
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.create_date
     *
     * @return the value of ehr_attendance.create_date
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.create_date
     *
     * @param createDate the value for ehr_attendance.create_date
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.last_modify_user_id
     *
     * @return the value of ehr_attendance.last_modify_user_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public Long getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.last_modify_user_id
     *
     * @param lastModifyUserId the value for ehr_attendance.last_modify_user_id
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setLastModifyUserId(Long lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.last_modify_user_name
     *
     * @return the value of ehr_attendance.last_modify_user_name
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.last_modify_user_name
     *
     * @param lastModifyUserName the value for ehr_attendance.last_modify_user_name
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.last_modify_date
     *
     * @return the value of ehr_attendance.last_modify_date
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.last_modify_date
     *
     * @param lastModifyDate the value for ehr_attendance.last_modify_date
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.day_shift
     *
     * @return the value of ehr_attendance.day_shift
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public BigDecimal getDayShift() {
        return dayShift;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.day_shift
     *
     * @param dayShift the value for ehr_attendance.day_shift
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setDayShift(BigDecimal dayShift) {
        this.dayShift = dayShift;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ehr_attendance.times
     *
     * @return the value of ehr_attendance.times
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public Byte getTimes() {
        return times;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ehr_attendance.times
     *
     * @param times the value for ehr_attendance.times
     *
     * @mbggenerated Mon Jan 04 14:11:04 CST 2016
     */
    public void setTimes(Byte times) {
        this.times = times;
    }
    
    public void setFieldValue(String fieldName, String value){
    	if("late_times".equals(fieldName)){					//迟到次数
    		this.setLateTimes(new BigDecimal(value).byteValue());
    	}else if("leave_early_times".equals(fieldName)){	//早退次数
    		this.setLeaveEarlyTimes(new BigDecimal(value).byteValue());
    	}else if("absent_time".equals(fieldName)){			//旷工次数
    		this.setAbsentTime(new BigDecimal(value).byteValue());
    	}else if("all_leave".equals(fieldName)){			//事假天数
    		this.setAllLeave(new BigDecimal(value));
    	}else if("sick_leave".equals(fieldName)){			//病假天数
    		this.setSickLeave(new BigDecimal(value));
    	}else if("business".equals(fieldName)){				//出差天数
    		this.setBusiness(new BigDecimal(value));
    	}else if("annual_leave".equals(fieldName)){			//年假天数
    		this.setAnnualLeave(new BigDecimal(value));
    	}else if("work_overtime".equals(fieldName)){		//平日加班
    		this.setWorkOvertime(new BigDecimal(value));
    	}else if("week_overtime".equals(fieldName)){		//周末加班
    		this.setWeekOvertime(new BigDecimal(value));
    	}else if("holidays_overtime".equals(fieldName)){	//节假日加班
    		this.setHolidaysOvertime(new BigDecimal(value));
    	}else if("adjust_day".equals(fieldName)){			//调休天数
    		this.setAdjustDay(new BigDecimal(value));
    	}else if("night_shift".equals(fieldName)){			//夜班天数
    		this.setNightShift(new BigDecimal(value));
    	}else if("total_annual".equals(fieldName)){			//应休年假天数
    		this.setTotalAnnual(new BigDecimal(value));
    	}else if("surplus_annual".equals(fieldName)){		//剩余年假
    		this.setSurplusAnnual(new BigDecimal(value));
    	}else if("marriage_leave".equals(fieldName)){		//婚假
    		this.setMarriageLeave(new BigDecimal(value));
    	}else if("maternity_leave".equals(fieldName)){		//产假
    		this.setMaternityLeave(new BigDecimal(value));
    	}else if("paternity_leave".equals(fieldName)){		//陪产假
    		this.setPaternityLeave(new BigDecimal(value));
    	}else if("funeral_leave".equals(fieldName)){		//丧假
    		this.setFuneralLeave(new BigDecimal(value));
    	}else if("memo".equals(fieldName)){					//备注
    		this.setMemo(value);
    	}
    }
}