package com.greenstar.entity.tb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 12th November, 2020
 */

@Entity
@Table(name="TBREPORT")
public class TBReport {
    @Id
    @Column(name="CHO_NAME")
    private String CHO_NAME;

    @Column(name="CHO_CODE")
    private String CHO_CODE;

    @Column(name="PULMONARYBACTERIOLOGICALLYCONFIRMEDNEW")
    private double PULMONARYBACTERIOLOGICALLYCONFIRMEDNEW;

    @Column(name="PULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE")
    private double PULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE;

    @Column(name="PULMONARYBACTERIOLOGICALLYCONFIRMEDUPT")
    private double PULMONARYBACTERIOLOGICALLYCONFIRMEDUPT;

    @Column(name="PBNPBRPBUPT")
    private double PBNPBRPBUPT;

    @Column(name="PULMONARYBACTERIOLOGICALLYCONFIRMEDTAF")
    private double PULMONARYBACTERIOLOGICALLYCONFIRMEDTAF;

    @Column(name="PULMONARYBACTERIOLOGICALLYCONFIRMEDALF")
    private double PULMONARYBACTERIOLOGICALLYCONFIRMEDALF;

    @Column(name="PULMONARYBACTERIOLOGICALLYCONFIRMEDOPT")
    private double PULMONARYBACTERIOLOGICALLYCONFIRMEDOPT;

    @Column(name="PBTAFPBALFPBOPT")
    private double PBTAFPBALFPBOPT;

    @Column(name="PULMONARYCLINICALLYDIAGNOSEDNEW")
    private double PULMONARYCLINICALLYDIAGNOSEDNEW;

    @Column(name="PULMONARYCLINICALLYDIAGNOSEDRELAPSE")
    private double PULMONARYCLINICALLYDIAGNOSEDRELAPSE;

    @Column(name="PULMONARYCLINICALLYDIAGNOSEDUPT")
    private double PULMONARYCLINICALLYDIAGNOSEDUPT;

    @Column(name="PCDNPCDRPCDUPT")
    private double PCDNPCDRPCDUPT;

    @Column(name="PULMONARYCLINICALLYDIAGNOSEDTAF")
    private double PULMONARYCLINICALLYDIAGNOSEDTAF;

    @Column(name="PULMONARYCLINICALLYDIAGNOSEDALF")
    private double PULMONARYCLINICALLYDIAGNOSEDALF;

    @Column(name="PULMONARYCLINICALLYDIAGNOSEDOPT")
    private double PULMONARYCLINICALLYDIAGNOSEDOPT;

    @Column(name="PCDTAFPCDALFPCDOPT")
    private double PCDTAFPCDALFPCDOPT;

    @Column(name="EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW")
    private double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW;

    @Column(name="EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE")
    private double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE;

    @Column(name="EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT")
    private double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT;

    @Column(name="EPBNEPBREPBUPT")
    private double EPBNEPBREPBUPT;

    @Column(name="EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF")
    private double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF;

    @Column(name="EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDALF")
    private double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDALF;

    @Column(name="EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT")
    private double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT;

    @Column(name="EPBTAFEPBALFEPBOPT")
    private double EPBTAFEPBALFEPBOPT;

    @Column(name="EXTRAPULMONARYCLINICALLYDIAGNOSEDNEW")
    private double EXTRAPULMONARYCLINICALLYDIAGNOSEDNEW;

    @Column(name="EXTRAPULMONARYCLINICALLYDIAGNOSEDRELAPSE")
    private double EXTRAPULMONARYCLINICALLYDIAGNOSEDRELAPSE;

    @Column(name="EXTRAPULMONARYCLINICALLYDIAGNOSEDUPT")
    private double EXTRAPULMONARYCLINICALLYDIAGNOSEDUPT;

    @Column(name="EPCDNEPCDREPCDUPT")
    private double EPCDNEPCDREPCDUPT;

    @Column(name="EXTRAPULMONARYCLINICALLYDIAGNOSEDTAF")
    private double EXTRAPULMONARYCLINICALLYDIAGNOSEDTAF;

    @Column(name="EXTRAPULMONARYCLINICALLYDIAGNOSEDALF")
    private double EXTRAPULMONARYCLINICALLYDIAGNOSEDALF;

    @Column(name="EXTRAPULMONARYCLINICALLYDIAGNOSEDOPT")
    private double EXTRAPULMONARYCLINICALLYDIAGNOSEDOPT;

    @Column(name="EPCDTAFEPCDALFEPCDOPT")
    private double EPCDTAFEPCDALFEPCDOPT;

    @Column(name="BPSUM")
    private double BPSUM;

    @Column(name="NEWTOTAL")
    private double NEWTOTAL;

    @Column(name="TOTALRELAPSE")
    private double TOTALRELAPSE;

    @Column(name="TOTALPTH")
    private double TOTALPTH;

    @Column(name="TOTALNRUK")
    private double TOTALNRUK;

    @Column(name="TOTALTREATMENTAFTERFAILURE")
    private double TOTALTREATMENTAFTERFAILURE;

    @Column(name="TOTALAFTERLOSSTOFOLLOWUP")
    private double TOTALAFTERLOSSTOFOLLOWUP;

    @Column(name="TOTALOTERPREVIOUSLYTREATED")
    private double TOTALOTERPREVIOUSLYTREATED;

    @Column(name="BLOCK1TOTALTOTAL")
    private double BLOCK1TOTALTOTAL;

    @Column(name="TOTALNRUKPTDXPERTESTPOSITIVE")
    private double TOTALNRUKPTDXPERTESTPOSITIVE;

    @Column(name="TOTALNRUKHIVSCREENING")
    private double TOTALNRUKHIVSCREENING;

    @Column(name="TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVE")
    private double TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVE;

    @Column(name="TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVEARTNOTNULL")
    private double TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVEARTNOTNULL;

    public String getCHO_NAME() {
        return CHO_NAME;
    }

    public void setCHO_NAME(String CHO_NAME) {
        this.CHO_NAME = CHO_NAME;
    }

    public String getCHO_CODE() {
        return CHO_CODE;
    }

    public void setCHO_CODE(String CHO_CODE) {
        this.CHO_CODE = CHO_CODE;
    }

    public double getPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW() {
        return PULMONARYBACTERIOLOGICALLYCONFIRMEDNEW;
    }

    public void setPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW(double PULMONARYBACTERIOLOGICALLYCONFIRMEDNEW) {
        this.PULMONARYBACTERIOLOGICALLYCONFIRMEDNEW = PULMONARYBACTERIOLOGICALLYCONFIRMEDNEW;
    }

    public double getPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE() {
        return PULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE;
    }

    public void setPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE(double PULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE) {
        this.PULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE = PULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE;
    }

    public double getPBNPBRPBUPT() {
        return PBNPBRPBUPT;
    }

    public void setPBNPBRPBUPT(double PBNPBRPBUPT) {
        this.PBNPBRPBUPT = PBNPBRPBUPT;
    }

    public double getPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF() {
        return PULMONARYBACTERIOLOGICALLYCONFIRMEDTAF;
    }

    public void setPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF(double PULMONARYBACTERIOLOGICALLYCONFIRMEDTAF) {
        this.PULMONARYBACTERIOLOGICALLYCONFIRMEDTAF = PULMONARYBACTERIOLOGICALLYCONFIRMEDTAF;
    }

    public double getPULMONARYBACTERIOLOGICALLYCONFIRMEDALF() {
        return PULMONARYBACTERIOLOGICALLYCONFIRMEDALF;
    }

    public void setPULMONARYBACTERIOLOGICALLYCONFIRMEDALF(double PULMONARYBACTERIOLOGICALLYCONFIRMEDALF) {
        this.PULMONARYBACTERIOLOGICALLYCONFIRMEDALF = PULMONARYBACTERIOLOGICALLYCONFIRMEDALF;
    }

    public double getPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT() {
        return PULMONARYBACTERIOLOGICALLYCONFIRMEDOPT;
    }

    public void setPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT(double PULMONARYBACTERIOLOGICALLYCONFIRMEDOPT) {
        this.PULMONARYBACTERIOLOGICALLYCONFIRMEDOPT = PULMONARYBACTERIOLOGICALLYCONFIRMEDOPT;
    }

    public double getPBTAFPBALFPBOPT() {
        return PBTAFPBALFPBOPT;
    }

    public void setPBTAFPBALFPBOPT(double PBTAFPBALFPBOPT) {
        this.PBTAFPBALFPBOPT = PBTAFPBALFPBOPT;
    }

    public double getPULMONARYCLINICALLYDIAGNOSEDNEW() {
        return PULMONARYCLINICALLYDIAGNOSEDNEW;
    }

    public void setPULMONARYCLINICALLYDIAGNOSEDNEW(double PULMONARYCLINICALLYDIAGNOSEDNEW) {
        this.PULMONARYCLINICALLYDIAGNOSEDNEW = PULMONARYCLINICALLYDIAGNOSEDNEW;
    }

    public double getPULMONARYCLINICALLYDIAGNOSEDRELAPSE() {
        return PULMONARYCLINICALLYDIAGNOSEDRELAPSE;
    }

    public void setPULMONARYCLINICALLYDIAGNOSEDRELAPSE(double PULMONARYCLINICALLYDIAGNOSEDRELAPSE) {
        this.PULMONARYCLINICALLYDIAGNOSEDRELAPSE = PULMONARYCLINICALLYDIAGNOSEDRELAPSE;
    }

    public double getPCDNPCDRPCDUPT() {
        return PCDNPCDRPCDUPT;
    }

    public void setPCDNPCDRPCDUPT(double PCDNPCDRPCDUPT) {
        this.PCDNPCDRPCDUPT = PCDNPCDRPCDUPT;
    }

    public double getPULMONARYCLINICALLYDIAGNOSEDTAF() {
        return PULMONARYCLINICALLYDIAGNOSEDTAF;
    }

    public void setPULMONARYCLINICALLYDIAGNOSEDTAF(double PULMONARYCLINICALLYDIAGNOSEDTAF) {
        this.PULMONARYCLINICALLYDIAGNOSEDTAF = PULMONARYCLINICALLYDIAGNOSEDTAF;
    }

    public double getPULMONARYCLINICALLYDIAGNOSEDALF() {
        return PULMONARYCLINICALLYDIAGNOSEDALF;
    }

    public void setPULMONARYCLINICALLYDIAGNOSEDALF(double PULMONARYCLINICALLYDIAGNOSEDALF) {
        this.PULMONARYCLINICALLYDIAGNOSEDALF = PULMONARYCLINICALLYDIAGNOSEDALF;
    }

    public double getPULMONARYCLINICALLYDIAGNOSEDOPT() {
        return PULMONARYCLINICALLYDIAGNOSEDOPT;
    }

    public void setPULMONARYCLINICALLYDIAGNOSEDOPT(double PULMONARYCLINICALLYDIAGNOSEDOPT) {
        this.PULMONARYCLINICALLYDIAGNOSEDOPT = PULMONARYCLINICALLYDIAGNOSEDOPT;
    }

    public double getPCDTAFPCDALFPCDOPT() {
        return PCDTAFPCDALFPCDOPT;
    }

    public void setPCDTAFPCDALFPCDOPT(double PCDTAFPCDALFPCDOPT) {
        this.PCDTAFPCDALFPCDOPT = PCDTAFPCDALFPCDOPT;
    }

    public double getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW() {
        return EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW;
    }

    public void setEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW(double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW) {
        this.EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW = EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW;
    }

    public double getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE() {
        return EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE;
    }

    public void setEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE(double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE) {
        this.EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE = EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE;
    }

    public double getEPBNEPBREPBUPT() {
        return EPBNEPBREPBUPT;
    }

    public void setEPBNEPBREPBUPT(double EPBNEPBREPBUPT) {
        this.EPBNEPBREPBUPT = EPBNEPBREPBUPT;
    }

    public double getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF() {
        return EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF;
    }

    public void setEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF(double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF) {
        this.EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF = EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF;
    }

    public double getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDALF() {
        return EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDALF;
    }

    public void setEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDALF(double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDALF) {
        this.EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDALF = EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDALF;
    }

    public double getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT() {
        return EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT;
    }

    public void setEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT(double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT) {
        this.EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT = EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT;
    }

    public double getEPBTAFEPBALFEPBOPT() {
        return EPBTAFEPBALFEPBOPT;
    }

    public void setEPBTAFEPBALFEPBOPT(double EPBTAFEPBALFEPBOPT) {
        this.EPBTAFEPBALFEPBOPT = EPBTAFEPBALFEPBOPT;
    }

    public double getEXTRAPULMONARYCLINICALLYDIAGNOSEDNEW() {
        return EXTRAPULMONARYCLINICALLYDIAGNOSEDNEW;
    }

    public void setEXTRAPULMONARYCLINICALLYDIAGNOSEDNEW(double EXTRAPULMONARYCLINICALLYDIAGNOSEDNEW) {
        this.EXTRAPULMONARYCLINICALLYDIAGNOSEDNEW = EXTRAPULMONARYCLINICALLYDIAGNOSEDNEW;
    }

    public double getEXTRAPULMONARYCLINICALLYDIAGNOSEDRELAPSE() {
        return EXTRAPULMONARYCLINICALLYDIAGNOSEDRELAPSE;
    }

    public void setEXTRAPULMONARYCLINICALLYDIAGNOSEDRELAPSE(double EXTRAPULMONARYCLINICALLYDIAGNOSEDRELAPSE) {
        this.EXTRAPULMONARYCLINICALLYDIAGNOSEDRELAPSE = EXTRAPULMONARYCLINICALLYDIAGNOSEDRELAPSE;
    }
    public double getEPCDNEPCDREPCDUPT() {
        return EPCDNEPCDREPCDUPT;
    }

    public void setEPCDNEPCDREPCDUPT(double EPCDNEPCDREPCDUPT) {
        this.EPCDNEPCDREPCDUPT = EPCDNEPCDREPCDUPT;
    }

    public double getEXTRAPULMONARYCLINICALLYDIAGNOSEDTAF() {
        return EXTRAPULMONARYCLINICALLYDIAGNOSEDTAF;
    }

    public void setEXTRAPULMONARYCLINICALLYDIAGNOSEDTAF(double EXTRAPULMONARYCLINICALLYDIAGNOSEDTAF) {
        this.EXTRAPULMONARYCLINICALLYDIAGNOSEDTAF = EXTRAPULMONARYCLINICALLYDIAGNOSEDTAF;
    }

    public double getEXTRAPULMONARYCLINICALLYDIAGNOSEDALF() {
        return EXTRAPULMONARYCLINICALLYDIAGNOSEDALF;
    }

    public void setEXTRAPULMONARYCLINICALLYDIAGNOSEDALF(double EXTRAPULMONARYCLINICALLYDIAGNOSEDALF) {
        this.EXTRAPULMONARYCLINICALLYDIAGNOSEDALF = EXTRAPULMONARYCLINICALLYDIAGNOSEDALF;
    }

    public double getEXTRAPULMONARYCLINICALLYDIAGNOSEDOPT() {
        return EXTRAPULMONARYCLINICALLYDIAGNOSEDOPT;
    }

    public void setEXTRAPULMONARYCLINICALLYDIAGNOSEDOPT(double EXTRAPULMONARYCLINICALLYDIAGNOSEDOPT) {
        this.EXTRAPULMONARYCLINICALLYDIAGNOSEDOPT = EXTRAPULMONARYCLINICALLYDIAGNOSEDOPT;
    }

    public double getEPCDTAFEPCDALFEPCDOPT() {
        return EPCDTAFEPCDALFEPCDOPT;
    }

    public void setEPCDTAFEPCDALFEPCDOPT(double EPCDTAFEPCDALFEPCDOPT) {
        this.EPCDTAFEPCDALFEPCDOPT = EPCDTAFEPCDALFEPCDOPT;
    }

    public double getBPSUM() {
        return BPSUM;
    }

    public void setBPSUM(double BPSUM) {
        this.BPSUM = BPSUM;
    }

    public double getNEWTOTAL() {
        return NEWTOTAL;
    }

    public void setNEWTOTAL(double NEWTOTAL) {
        this.NEWTOTAL = NEWTOTAL;
    }

    public double getTOTALRELAPSE() {
        return TOTALRELAPSE;
    }

    public void setTOTALRELAPSE(double TOTALRELAPSE) {
        this.TOTALRELAPSE = TOTALRELAPSE;
    }

    public double getTOTALPTH() {
        return TOTALPTH;
    }

    public void setTOTALPTH(double TOTALPTH) {
        this.TOTALPTH = TOTALPTH;
    }

    public double getTOTALNRUK() {
        return TOTALNRUK;
    }

    public void setTOTALNRUK(double TOTALNRUK) {
        this.TOTALNRUK = TOTALNRUK;
    }

    public double getTOTALTREATMENTAFTERFAILURE() {
        return TOTALTREATMENTAFTERFAILURE;
    }

    public void setTOTALTREATMENTAFTERFAILURE(double TOTALTREATMENTAFTERFAILURE) {
        this.TOTALTREATMENTAFTERFAILURE = TOTALTREATMENTAFTERFAILURE;
    }

    public double getTOTALAFTERLOSSTOFOLLOWUP() {
        return TOTALAFTERLOSSTOFOLLOWUP;
    }

    public void setTOTALAFTERLOSSTOFOLLOWUP(double TOTALAFTERLOSSTOFOLLOWUP) {
        this.TOTALAFTERLOSSTOFOLLOWUP = TOTALAFTERLOSSTOFOLLOWUP;
    }

    public double getTOTALOTERPREVIOUSLYTREATED() {
        return TOTALOTERPREVIOUSLYTREATED;
    }

    public void setTOTALOTERPREVIOUSLYTREATED(double TOTALOTERPREVIOUSLYTREATED) {
        this.TOTALOTERPREVIOUSLYTREATED = TOTALOTERPREVIOUSLYTREATED;
    }

    public double getBLOCK1TOTALTOTAL() {
        return BLOCK1TOTALTOTAL;
    }

    public void setBLOCK1TOTALTOTAL(double BLOCK1TOTALTOTAL) {
        this.BLOCK1TOTALTOTAL = BLOCK1TOTALTOTAL;
    }

    public double getTOTALNRUKPTDXPERTESTPOSITIVE() {
        return TOTALNRUKPTDXPERTESTPOSITIVE;
    }

    public void setTOTALNRUKPTDXPERTESTPOSITIVE(double TOTALNRUKPTDXPERTESTPOSITIVE) {
        this.TOTALNRUKPTDXPERTESTPOSITIVE = TOTALNRUKPTDXPERTESTPOSITIVE;
    }

    public double getTOTALNRUKHIVSCREENING() {
        return TOTALNRUKHIVSCREENING;
    }

    public void setTOTALNRUKHIVSCREENING(double TOTALNRUKHIVSCREENING) {
        this.TOTALNRUKHIVSCREENING = TOTALNRUKHIVSCREENING;
    }

    public double getTOTALNRUKHIVSCREENINGHIVRESULTPOSITIVE() {
        return TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVE;
    }

    public void setTOTALNRUKHIVSCREENINGHIVRESULTPOSITIVE(double TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVE) {
        this.TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVE = TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVE;
    }

    public double getTOTALNRUKHIVSCREENINGHIVRESULTPOSITIVEARTNOTNULL() {
        return TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVEARTNOTNULL;
    }

    public void setTOTALNRUKHIVSCREENINGHIVRESULTPOSITIVEARTNOTNULL(double TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVEARTNOTNULL) {
        this.TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVEARTNOTNULL = TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVEARTNOTNULL;
    }

    public double getPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT() {
        return PULMONARYBACTERIOLOGICALLYCONFIRMEDUPT;
    }

    public void setPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT(double PULMONARYBACTERIOLOGICALLYCONFIRMEDUPT) {
        this.PULMONARYBACTERIOLOGICALLYCONFIRMEDUPT = PULMONARYBACTERIOLOGICALLYCONFIRMEDUPT;
    }

    public double getPULMONARYCLINICALLYDIAGNOSEDUPT() {
        return PULMONARYCLINICALLYDIAGNOSEDUPT;
    }

    public void setPULMONARYCLINICALLYDIAGNOSEDUPT(double PULMONARYCLINICALLYDIAGNOSEDUPT) {
        this.PULMONARYCLINICALLYDIAGNOSEDUPT = PULMONARYCLINICALLYDIAGNOSEDUPT;
    }

    public double getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT() {
        return EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT;
    }

    public void setEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT(double EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT) {
        this.EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT = EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT;
    }

    public double getEXTRAPULMONARYCLINICALLYDIAGNOSEDUPT() {
        return EXTRAPULMONARYCLINICALLYDIAGNOSEDUPT;
    }

    public void setEXTRAPULMONARYCLINICALLYDIAGNOSEDUPT(double EXTRAPULMONARYCLINICALLYDIAGNOSEDUPT) {
        this.EXTRAPULMONARYCLINICALLYDIAGNOSEDUPT = EXTRAPULMONARYCLINICALLYDIAGNOSEDUPT;
    }
}