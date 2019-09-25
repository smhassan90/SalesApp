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
}
