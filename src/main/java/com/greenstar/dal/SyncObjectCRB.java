package com.greenstar.dal;

import com.greenstar.entity.crb.CRBForm;

import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 29th November, 2019
 */
public class SyncObjectCRB {

    List<CRBForm> crbForms;

    public List<CRBForm> getCrbForms() {
        return crbForms;
    }

    public void setCrbForms(List<CRBForm> crbForms) {
        this.crbForms = crbForms;
    }
}
