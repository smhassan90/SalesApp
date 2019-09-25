package com.greenstar.dal;

import com.greenstar.entity.dtc.DTCForm;

import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 18/9/2019
 */
public class SyncObjectDTC {
    List<DTCForm> forms;

    public List<DTCForm> getForms() {
        return forms;
    }

    public void setForms(List<DTCForm> forms) {
        this.forms = forms;
    }
}
