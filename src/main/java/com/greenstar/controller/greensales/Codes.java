package com.greenstar.controller.greensales;

public interface Codes {
    public static final String ALL_OK = "200";
    public static final String NOT_FOUND = "404";
    public static final String SOMETHING_WENT_WRONG = "502";
    public static final String ALREADY_LOGGED_IN = "504";
    public static final String FORCED_LOGOUT = "444";
    public static final String INVALID_TOKEN = "300";
    public static final String INVALID_VERSION = "303";
    public static final int FALCON_APP_CODE = 1;
    public static final int SALES_APP_CODE = 2;
    public static final int DTC_APP_CODE = 3;
    public static final int MECWHEEL_APP_CODE = 4;
    public static final int REJECTEDFORMSBYSYSTEM = 20;
    public static final int QAT_FOR_AM = 10;
    public static final int QAT_FOR_QAM = 30;
    public static final int QAT_FOR_BOTH = 40;
    public static final String SINGLE_QAT_FORM = "QAT_FORM";
    public static final String SINGLE_QAT_FORM_AM = "QAT_FORM_AM";
    public static final String SINGLE_QTV_FORM = "QTV_FORM";

    //Partial Sync
    public static final String ALL_OK_PS_BASICINFO = "2001";
    public static final String ERROR_PS_BASICINFO = "5021";

    public static final String PS_TYPE_ALL = "699";
    public static final String PS_TYPE_BASIC_INFO = "700";
    public static final String PS_TYPE_ApprovalQATForm = "702";
    public static final String PS_TYPE_ApprovalQTVForm = "704";
    public static final String PS_TYPE_Providers = "705";
    public static final String PS_TYPE_Question = "706";
    public static final String PS_TYPE_Area = "707";
    public static final String PS_TYPE_QATTCForm = "708";

    //QTV Form rejection Reason Messages
    public static final String REASON_ACCEPTED = "Accepted";
    public static final String REASON_AFTER_DUE = "Submission after due date";
    public static final String REASON_FWD_MONTH = "QTV performed on upcoming month";
    public static final String REASON_CLOSED_PROVIDERS = "This provider has been closed";
    public static final String REASON_DUPLICATE_PROVIDERS = "Duplicate QTV on same Provider";
    public static final String VERSIONFALCON = "5.3";
    public static final String REASON_NOT_UPDATED = "Form filled with old format";
}
