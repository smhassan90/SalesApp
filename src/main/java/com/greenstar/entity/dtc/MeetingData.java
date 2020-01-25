package com.greenstar.entity.dtc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 19th November, 2019
 */

@Entity
@Table(name="DTC_MEETING_DATA")
public class MeetingData {
    @Id
    @Column(name="ID")
    private long id;

    @Column(name="path")
    private String path;

    @Column(name="type")
    private int type;

    @Column(name="meetingId")
    private long meetingId;

    @Column(name="staffCode")
    private String staffCode;

    @Column(name="status")
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(long meetingId) {
        this.meetingId = meetingId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
