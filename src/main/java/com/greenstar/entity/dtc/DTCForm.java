package com.greenstar.entity.dtc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 18/9/2019
 */

@Entity
@Table(name="DTC_FORM_ANDROID")
public class DTCForm {
    @Id
    @Column(name="ID")
    private long id;

    @Column(name="staffCode")
    private String staffCode;

    //Meeting basic data
    @Column(name="districtCode")
    private String districtCode;
    @Column(name="membershipStatus")
    private int membershipStatus;
    @Column(name="meetingDate")
    private String meetingDate;

    //Detail of Person attending the meeting
    @Column(name="personName")
    private String personName;
    @Column(name="personDesignation")
    private String personDesignation;
    @Column(name="personECode")
    private String personECode;
    @Column(name="personTeam")
    private String personTeam;

    //Detail of Person attending with him
    @Column(name="attendingWithName")
    private String attendingWithName;
    @Column(name="attendingWithDesignation")
    private String attendingWithDesignation;
    @Column(name="attendingWithECode")
    private String attendingWithECode;
    @Column(name="attendingWithTeam")
    private String attendingWithTeam;

    //Detail of Chair Person of the meeting
    @Column(name="chairName")
    private String chairName;
    @Column(name="chairDesignation")
    private String chairDesignation;
    @Column(name="chairCellNumber")
    private String chairCellNumber;
    @Column(name="chairEmailAddress")
    private String chairEmailAddress;
    @Column(name="chairMeetingAddress")
    private String chairMeetingAddress;

    @Column(name="status")
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public int getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(int membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonDesignation() {
        return personDesignation;
    }

    public void setPersonDesignation(String personDesignation) {
        this.personDesignation = personDesignation;
    }

    public String getPersonECode() {
        return personECode;
    }

    public void setPersonECode(String personECode) {
        this.personECode = personECode;
    }

    public String getPersonTeam() {
        return personTeam;
    }

    public void setPersonTeam(String personTeam) {
        this.personTeam = personTeam;
    }

    public String getAttendingWithName() {
        return attendingWithName;
    }

    public void setAttendingWithName(String attendingWithName) {
        this.attendingWithName = attendingWithName;
    }

    public String getAttendingWithDesignation() {
        return attendingWithDesignation;
    }

    public void setAttendingWithDesignation(String attendingWithDesignation) {
        this.attendingWithDesignation = attendingWithDesignation;
    }

    public String getAttendingWithECode() {
        return attendingWithECode;
    }

    public void setAttendingWithECode(String attendingWithECode) {
        this.attendingWithECode = attendingWithECode;
    }

    public String getAttendingWithTeam() {
        return attendingWithTeam;
    }

    public void setAttendingWithTeam(String attendingWithTeam) {
        this.attendingWithTeam = attendingWithTeam;
    }

    public String getChairName() {
        return chairName;
    }

    public void setChairName(String chairName) {
        this.chairName = chairName;
    }

    public String getChairDesignation() {
        return chairDesignation;
    }

    public void setChairDesignation(String chairDesignation) {
        this.chairDesignation = chairDesignation;
    }

    public String getChairCellNumber() {
        return chairCellNumber;
    }

    public void setChairCellNumber(String chairCellNumber) {
        this.chairCellNumber = chairCellNumber;
    }

    public String getChairEmailAddress() {
        return chairEmailAddress;
    }

    public void setChairEmailAddress(String chairEmailAddress) {
        this.chairEmailAddress = chairEmailAddress;
    }

    public String getChairMeetingAddress() {
        return chairMeetingAddress;
    }

    public void setChairMeetingAddress(String chairMeetingAddress) {
        this.chairMeetingAddress = chairMeetingAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
