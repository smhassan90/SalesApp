package com.greenstar.entity.tb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 22nd October, 2020
 */

@Entity
@Table(name="TB_TB01")
public class TB01 {

    @Column(name="tb_trt_history")
    private String tb_trt_history;

    @Column(name="tb_trt_tablets_initial")
    private String tb_trt_tablets_initial;

    @Column(name="pat_name")
    private String pat_name;

    @Column(name="ptd_hiv_screening")

    private String ptd_hiv_screening;
    @Column(name="type_pat_bacteriology_clinically")

    private String type_pat_bacteriology_clinically;
    @Column(name="ptd_xray_test")

    private String ptd_xray_test;
    @Column(name="tb_trt_tablets_cont")

    private int tb_trt_tablets_cont;
    @Column(name="xpt_mtsltr")

    private String xpt_mtsltr;
    @Column(name="date_of_insertion")

    private String date_of_insertion;
    @Column(name="rsk_other")

    private String rsk_other;
    @Column(name="disease_site")

    private String disease_site;
    @Column(name="pat_category")

    private String pat_category;

    @Column(name="ptd_otp")
    private String ptd_otp;
    @Column(name="hhm_number")

    private int hhm_number;
    @Column(name="register_in")

    private String register_in;
    @Column(name="eptb_evidence")

    private String eptb_evidence;
    @Column(name="cnic_of")

    private String cnic_of;
    @Column(name="pat_referred_by")

    private String pat_referred_by;
    @Column(name="trt_supporter_contact")

    private String trt_supporter_contact;
    @Column(name="ptd_ssm_result_6")

    private String ptd_ssm_result_6;
    @Column(name="ptd_ssm_result_5")

    private String ptd_ssm_result_5;
    @Column(name="rsk_ptb_case")

    private String rsk_ptb_case;
    @Column(name="ptd_ssm_result_2")

    private String ptd_ssm_result_2;
    @Column(name="pat_contact")

    private String pat_contact;
    @Column(name="trt_supporter_type")

    private String trt_supporter_type;
    @Column(name="rsk_hcw")

    private String rsk_hcw;
    @Column(name="pat_occupation")

    private String pat_occupation;
    @Column(name="rsk_diabetes")

    private String rsk_diabetes;
    @Column(name="district")

    private String district;
    @Column(name="trt_outcome")

    private String trt_outcome;
    @Column(name="ptd_xpert_test_reason")

    private String ptd_xpert_test_reason;
    @Id
    @Column(name="serial_no")
    private int serial_no;
    @Column(name="tb_trt_cont")

    private String tb_trt_cont;
    @Column(name="pvt_provider_name")

    private String pvt_provider_name;
    @Column(name="user_name")

    private String user_name;
    @Column(name="rsk_smoking")

    private String rsk_smoking;
    @Column(name="ptd_ssm_result")

    private String ptd_ssm_result;
    @Column(name="tb_reg_no")

    private String tb_reg_no;
    @Column(name="pat_gender")

    private String pat_gender;
    @Column(name="date_2")

    private String date_2;
    @Column(name="pat_address")

    private String pat_address;
    @Column(name="type_pat_bacteriology")

    private String type_pat_bacteriology;
    @Column(name="pvt_clinic_code")

    private String pvt_clinic_code;
    @Column(name="rsk_malnutrition")

    private String rsk_malnutrition;
    @Column(name="ptd_xpert_result")

    private String ptd_xpert_result;
    @Column(name="pvt_clinic_name")

    private String pvt_clinic_name;
    @Column(name="trt_supporter_name")

    private String trt_supporter_name;
    @Column(name="pat_dor")

    private String pat_dor;
    @Column(name="ptd_xpert_test")

    private String ptd_xpert_test;
    @Column(name="rsk_hiv_aids")

    private String rsk_hiv_aids;
    @Column(name="cnic")

    private String cnic;
    @Column(name="eptb_site")

    private String eptb_site;
    @Column(name="ptd_xray_result")

    private String ptd_xray_result;
    @Column(name="ptd_ssm_date")

    private String ptd_ssm_date;
    @Column(name="ptd_xpert_lab")

    private String ptd_xpert_lab;
    @Column(name="age_2")

    private String age_2;
    @Column(name="tb_trt_initial")

    private String tb_trt_initial;
    @Column(name="pat_guardian")

    private String pat_guardian;
    @Column(name="ptd_xpert_date")

    private String ptd_xpert_date;

    @Column(name="pat_age")
    private String pat_age;

    public String getTb_trt_history() {
        return tb_trt_history;
    }

    public void setTb_trt_history(String tb_trt_history) {
        this.tb_trt_history = tb_trt_history;
    }

    public String getTb_trt_tablets_initial() {
        return tb_trt_tablets_initial;
    }

    public void setTb_trt_tablets_initial(String tb_trt_tablets_initial) {
        this.tb_trt_tablets_initial = tb_trt_tablets_initial;
    }

    public String getPat_name() {
        return pat_name;
    }

    public void setPat_name(String pat_name) {
        this.pat_name = pat_name;
    }

    public String getPtd_hiv_screening() {
        return ptd_hiv_screening;
    }

    public void setPtd_hiv_screening(String ptd_hiv_screening) {
        this.ptd_hiv_screening = ptd_hiv_screening;
    }

    public String getType_pat_bacteriology_clinically() {
        return type_pat_bacteriology_clinically;
    }

    public void setType_pat_bacteriology_clinically(String type_pat_bacteriology_clinically) {
        this.type_pat_bacteriology_clinically = type_pat_bacteriology_clinically;
    }

    public String getPtd_xray_test() {
        return ptd_xray_test;
    }

    public void setPtd_xray_test(String ptd_xray_test) {
        this.ptd_xray_test = ptd_xray_test;
    }

    public int getTb_trt_tablets_cont() {
        return tb_trt_tablets_cont;
    }

    public void setTb_trt_tablets_cont(int tb_trt_tablets_cont) {
        this.tb_trt_tablets_cont = tb_trt_tablets_cont;
    }

    public String getXpt_mtsltr() {
        return xpt_mtsltr;
    }

    public void setXpt_mtsltr(String xpt_mtsltr) {
        this.xpt_mtsltr = xpt_mtsltr;
    }

    public String getDate_of_insertion() {
        return date_of_insertion;
    }

    public void setDate_of_insertion(String date_of_insertion) {
        this.date_of_insertion = date_of_insertion;
    }

    public String getRsk_other() {
        return rsk_other;
    }

    public void setRsk_other(String rsk_other) {
        this.rsk_other = rsk_other;
    }

    public String getDisease_site() {
        return disease_site;
    }

    public void setDisease_site(String disease_site) {
        this.disease_site = disease_site;
    }

    public String getPat_category() {
        return pat_category;
    }

    public void setPat_category(String pat_category) {
        this.pat_category = pat_category;
    }

    public String getPtd_otp() {
        return ptd_otp;
    }

    public void setPtd_otp(String ptd_otp) {
        this.ptd_otp = ptd_otp;
    }

    public int getHhm_number() {
        return hhm_number;
    }

    public void setHhm_number(int hhm_number) {
        this.hhm_number = hhm_number;
    }

    public String getRegister_in() {
        return register_in;
    }

    public void setRegister_in(String register_in) {
        this.register_in = register_in;
    }

    public String getEptb_evidence() {
        return eptb_evidence;
    }

    public void setEptb_evidence(String eptb_evidence) {
        this.eptb_evidence = eptb_evidence;
    }

    public String getCnic_of() {
        return cnic_of;
    }

    public void setCnic_of(String cnic_of) {
        this.cnic_of = cnic_of;
    }

    public String getPat_referred_by() {
        return pat_referred_by;
    }

    public void setPat_referred_by(String pat_referred_by) {
        this.pat_referred_by = pat_referred_by;
    }

    public String getTrt_supporter_contact() {
        return trt_supporter_contact;
    }

    public void setTrt_supporter_contact(String trt_supporter_contact) {
        this.trt_supporter_contact = trt_supporter_contact;
    }

    public String getPtd_ssm_result_6() {
        return ptd_ssm_result_6;
    }

    public void setPtd_ssm_result_6(String ptd_ssm_result_6) {
        this.ptd_ssm_result_6 = ptd_ssm_result_6;
    }

    public String getPtd_ssm_result_5() {
        return ptd_ssm_result_5;
    }

    public void setPtd_ssm_result_5(String ptd_ssm_result_5) {
        this.ptd_ssm_result_5 = ptd_ssm_result_5;
    }

    public String getRsk_ptb_case() {
        return rsk_ptb_case;
    }

    public void setRsk_ptb_case(String rsk_ptb_case) {
        this.rsk_ptb_case = rsk_ptb_case;
    }

    public String getPtd_ssm_result_2() {
        return ptd_ssm_result_2;
    }

    public void setPtd_ssm_result_2(String ptd_ssm_result_2) {
        this.ptd_ssm_result_2 = ptd_ssm_result_2;
    }

    public String getPat_contact() {
        return pat_contact;
    }

    public void setPat_contact(String pat_contact) {
        this.pat_contact = pat_contact;
    }

    public String getTrt_supporter_type() {
        return trt_supporter_type;
    }

    public void setTrt_supporter_type(String trt_supporter_type) {
        this.trt_supporter_type = trt_supporter_type;
    }

    public String getRsk_hcw() {
        return rsk_hcw;
    }

    public void setRsk_hcw(String rsk_hcw) {
        this.rsk_hcw = rsk_hcw;
    }

    public String getPat_occupation() {
        return pat_occupation;
    }

    public void setPat_occupation(String pat_occupation) {
        this.pat_occupation = pat_occupation;
    }

    public String getRsk_diabetes() {
        return rsk_diabetes;
    }

    public void setRsk_diabetes(String rsk_diabetes) {
        this.rsk_diabetes = rsk_diabetes;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTrt_outcome() {
        return trt_outcome;
    }

    public void setTrt_outcome(String trt_outcome) {
        this.trt_outcome = trt_outcome;
    }

    public String getPtd_xpert_test_reason() {
        return ptd_xpert_test_reason;
    }

    public void setPtd_xpert_test_reason(String ptd_xpert_test_reason) {
        this.ptd_xpert_test_reason = ptd_xpert_test_reason;
    }

    public int getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(int serial_no) {
        this.serial_no = serial_no;
    }

    public String getTb_trt_cont() {
        return tb_trt_cont;
    }

    public void setTb_trt_cont(String tb_trt_cont) {
        this.tb_trt_cont = tb_trt_cont;
    }

    public String getPvt_provider_name() {
        return pvt_provider_name;
    }

    public void setPvt_provider_name(String pvt_provider_name) {
        this.pvt_provider_name = pvt_provider_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRsk_smoking() {
        return rsk_smoking;
    }

    public void setRsk_smoking(String rsk_smoking) {
        this.rsk_smoking = rsk_smoking;
    }

    public String getPtd_ssm_result() {
        return ptd_ssm_result;
    }

    public void setPtd_ssm_result(String ptd_ssm_result) {
        this.ptd_ssm_result = ptd_ssm_result;
    }

    public String getTb_reg_no() {
        return tb_reg_no;
    }

    public void setTb_reg_no(String tb_reg_no) {
        this.tb_reg_no = tb_reg_no;
    }

    public String getPat_gender() {
        return pat_gender;
    }

    public void setPat_gender(String pat_gender) {
        this.pat_gender = pat_gender;
    }

    public String getDate_2() {
        return date_2;
    }

    public void setDate_2(String date_2) {
        this.date_2 = date_2;
    }

    public String getPat_address() {
        return pat_address;
    }

    public void setPat_address(String pat_address) {
        this.pat_address = pat_address;
    }

    public String getType_pat_bacteriology() {
        return type_pat_bacteriology;
    }

    public void setType_pat_bacteriology(String type_pat_bacteriology) {
        this.type_pat_bacteriology = type_pat_bacteriology;
    }

    public String getPvt_clinic_code() {
        return pvt_clinic_code;
    }

    public void setPvt_clinic_code(String pvt_clinic_code) {
        this.pvt_clinic_code = pvt_clinic_code;
    }

    public String getRsk_malnutrition() {
        return rsk_malnutrition;
    }

    public void setRsk_malnutrition(String rsk_malnutrition) {
        this.rsk_malnutrition = rsk_malnutrition;
    }

    public String getPtd_xpert_result() {
        return ptd_xpert_result;
    }

    public void setPtd_xpert_result(String ptd_xpert_result) {
        this.ptd_xpert_result = ptd_xpert_result;
    }

    public String getPvt_clinic_name() {
        return pvt_clinic_name;
    }

    public void setPvt_clinic_name(String pvt_clinic_name) {
        this.pvt_clinic_name = pvt_clinic_name;
    }

    public String getTrt_supporter_name() {
        return trt_supporter_name;
    }

    public void setTrt_supporter_name(String trt_supporter_name) {
        this.trt_supporter_name = trt_supporter_name;
    }

    public String getPat_dor() {
        return pat_dor;
    }

    public void setPat_dor(String pat_dor) {
        this.pat_dor = pat_dor;
    }

    public String getPtd_xpert_test() {
        return ptd_xpert_test;
    }

    public void setPtd_xpert_test(String ptd_xpert_test) {
        this.ptd_xpert_test = ptd_xpert_test;
    }

    public String getRsk_hiv_aids() {
        return rsk_hiv_aids;
    }

    public void setRsk_hiv_aids(String rsk_hiv_aids) {
        this.rsk_hiv_aids = rsk_hiv_aids;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getEptb_site() {
        return eptb_site;
    }

    public void setEptb_site(String eptb_site) {
        this.eptb_site = eptb_site;
    }

    public String getPtd_xray_result() {
        return ptd_xray_result;
    }

    public void setPtd_xray_result(String ptd_xray_result) {
        this.ptd_xray_result = ptd_xray_result;
    }

    public String getPtd_ssm_date() {
        return ptd_ssm_date;
    }

    public void setPtd_ssm_date(String ptd_ssm_date) {
        this.ptd_ssm_date = ptd_ssm_date;
    }

    public String getPtd_xpert_lab() {
        return ptd_xpert_lab;
    }

    public void setPtd_xpert_lab(String ptd_xpert_lab) {
        this.ptd_xpert_lab = ptd_xpert_lab;
    }

    public String getAge_2() {
        return age_2;
    }

    public void setAge_2(String age_2) {
        this.age_2 = age_2;
    }

    public String getTb_trt_initial() {
        return tb_trt_initial;
    }

    public void setTb_trt_initial(String tb_trt_initial) {
        this.tb_trt_initial = tb_trt_initial;
    }

    public String getPat_guardian() {
        return pat_guardian;
    }

    public void setPat_guardian(String pat_guardian) {
        this.pat_guardian = pat_guardian;
    }

    public String getPtd_xpert_date() {
        return ptd_xpert_date;
    }

    public void setPtd_xpert_date(String ptd_xpert_date) {
        this.ptd_xpert_date = ptd_xpert_date;
    }

    public String getPat_age() {
        return pat_age;
    }

    public void setPat_age(String pat_age) {
        this.pat_age = pat_age;
    }
}
