package com.greenstar.dal;

import com.greenstar.entity.SDDepot;
import com.greenstar.entity.qat.Area;
import com.greenstar.entity.qat.QATTCForm;
import com.greenstar.entity.qat.Question;
import com.greenstar.entity.qtv.Providers;

import java.io.Serializable;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */
public class HSData implements Serializable {
    private String name;
    private String code;
    private String AMName;
    private String AMCode;
    private String region;
    private Dashboard dashboard;
    private int isQTVAllowed;
    private int isQATAllowed;
    private int isQATAMAllowed;

    List<ApprovalQATArea> approvalQATAreas;
    List<ApprovalQATForm> approvalQATForms;
    List<ApprovalQATFormQuestion> approvalQATFormQuestions;
    List<ApprovalQTVForm> qtvForms;
    List<Providers> providers;
    List<Question> questions;
    List<Area> areas;
    List<QATTCForm> qattcForms;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAMName() {
        return AMName;
    }

    public void setAMName(String AMName) {
        this.AMName = AMName;
    }

    public String getAMCode() {
        return AMCode;
    }

    public void setAMCode(String AMCode) {
        this.AMCode = AMCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Providers> getProviders() {
        return providers;
    }

    public void setProviders(List<Providers> providers) {
        this.providers = providers;
    }

    public List<ApprovalQTVForm> getQtvForms() {
        return qtvForms;
    }

    public void setQtvForms(List<ApprovalQTVForm> qtvForms) {
        this.qtvForms = qtvForms;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public int getIsQTVAllowed() {
        return isQTVAllowed;
    }

    public void setIsQTVAllowed(int isQTVAllowed) {
        this.isQTVAllowed = isQTVAllowed;
    }

    public int getIsQATAllowed() {
        return isQATAllowed;
    }

    public void setIsQATAllowed(int isQATAllowed) {
        this.isQATAllowed = isQATAllowed;
    }

    public List<ApprovalQATArea> getApprovalQATAreas() {
        return approvalQATAreas;
    }

    public void setApprovalQATAreas(List<ApprovalQATArea> approvalQATAreas) {
        this.approvalQATAreas = approvalQATAreas;
    }

    public List<ApprovalQATForm> getApprovalQATForms() {
        return approvalQATForms;
    }

    public void setApprovalQATForms(List<ApprovalQATForm> approvalQATForms) {
        this.approvalQATForms = approvalQATForms;
    }

    public List<ApprovalQATFormQuestion> getApprovalQATFormQuestions() {
        return approvalQATFormQuestions;
    }

    public void setApprovalQATFormQuestions(List<ApprovalQATFormQuestion> approvalQATFormQuestions) {
        this.approvalQATFormQuestions = approvalQATFormQuestions;
    }

    public List<QATTCForm> getQattcForms() {
        return qattcForms;
    }

    public void setQattcForms(List<QATTCForm> qattcForms) {
        this.qattcForms = qattcForms;
    }

    public int getIsQATAMAllowed() {
        return isQATAMAllowed;
    }

    public void setIsQATAMAllowed(int isQATAMAllowed) {
        this.isQATAMAllowed = isQATAMAllowed;
    }
}
