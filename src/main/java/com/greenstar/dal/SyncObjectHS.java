package com.greenstar.dal;

import com.greenstar.entity.qat.QATAreaDetail;
import com.greenstar.entity.qat.QATFormHeader;
import com.greenstar.entity.qat.QATFormQuestion;
import com.greenstar.entity.qtv.QTVForm;

import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 11/07/2019
 */
public class SyncObjectHS {
    List<QTVForm> qtvForms;
    List<QATFormHeader> qatFormHeaders;
    List<QATFormQuestion> qatFormQuestions;
    List<QATAreaDetail> qatAreaDetails;

    public List<QTVForm> getQtvForms() {
        return qtvForms;
    }

    public void setQtvForms(List<QTVForm> qtvForms) {
        this.qtvForms = qtvForms;
    }

    public List<QATFormHeader> getQatFormHeaders() {
        return qatFormHeaders;
    }

    public void setQatFormHeaders(List<QATFormHeader> qatFormHeaders) {
        this.qatFormHeaders = qatFormHeaders;
    }

    public List<QATFormQuestion> getQatFormQuestions() {
        return qatFormQuestions;
    }

    public void setQatFormQuestions(List<QATFormQuestion> qatFormQuestions) {
        this.qatFormQuestions = qatFormQuestions;
    }

    public List<QATAreaDetail> getQatAreaDetails() {
        return qatAreaDetails;
    }

    public void setQatAreaDetails(List<QATAreaDetail> qatAreaDetails) {
        this.qatAreaDetails = qatAreaDetails;
    }
}
