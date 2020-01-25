package com.greenstar.dal;

import com.greenstar.entity.dtc.DTCForm;
import com.greenstar.entity.dtc.MeetingData;

import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 18/9/2019
 */
public class SyncObjectDTC {
    List<DTCForm> forms;
    List<MeetingData> meetingDataList;

    public List<DTCForm> getForms() {
        return forms;
    }

    public void setForms(List<DTCForm> forms) {
        this.forms = forms;
    }

    public List<MeetingData> getMeetingDataList() {
        return meetingDataList;
    }

    public void setMeetingDataList(List<MeetingData> meetingDataList) {
        this.meetingDataList = meetingDataList;
    }
}