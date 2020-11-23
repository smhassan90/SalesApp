package com.greenstar.controller.tb;

import com.greenstar.entity.tb.TBReport;
import com.greenstar.entity.tb.TBReportBlock2;
import com.greenstar.utils.HibernateUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Syed Muhammad Hassan
 * 21st October, 2020
 */

@Controller
public class ReportGenerator {
    @RequestMapping(value = "/choReport", method = RequestMethod.GET,params={"cho_code","quarter"})
    @ResponseBody
    public String generateCHOReport(String cho_code, String quarter){
        File file = null;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        JasperReport jasperReport = null;
        Map<String,Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = null;
        ArrayList<TBReport> objs = new ArrayList<>();
        objs = (ArrayList<TBReport>) HibernateUtil.getDBObjectsNew("FROM TBReport WHERE cho_code='"+cho_code+"' AND QUARTER='"+quarter+"'");
        parameters.put("CHO_NAME", objs.get(0).getCHO_NAME());
        parameters.put("CHO_CODE", objs.get(0).getCHO_CODE());
        parameters.put("DISTRICT", objs.get(0).getDISTRICT());
        parameters.put("QUARTER", objs.get(0).getQUARTER());
        parameters.put("CURRENTDATE", dateFormat.format(date));

        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDNEW", objs.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE", objs.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDUPT", objs.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT());
        parameters.put("PBNPBRPBUPT", objs.get(0).getPBNPBRPBUPT());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDTAF", objs.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDALF", objs.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDALF());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDOPT", objs.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT());
        parameters.put("PBTAFPBALFPBOPT", objs.get(0).getPBTAFPBALFPBOPT());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDNEW", objs.get(0).getPULMONARYCLINICALLYDIAGNOSEDNEW());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDRELAPSE", objs.get(0).getPULMONARYCLINICALLYDIAGNOSEDRELAPSE());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDUPT", objs.get(0).getPULMONARYCLINICALLYDIAGNOSEDUPT());
        parameters.put("PCDNPCDRPCDUPT", objs.get(0).getPCDNPCDRPCDUPT());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDTAF", objs.get(0).getPULMONARYCLINICALLYDIAGNOSEDTAF());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDALF", objs.get(0).getPULMONARYCLINICALLYDIAGNOSEDALF());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDOPT", objs.get(0).getPULMONARYCLINICALLYDIAGNOSEDOPT());
        parameters.put("PCDTAFPCDALFPCDOPT", objs.get(0).getPCDTAFPCDALFPCDOPT());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW", objs.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDNEW());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE", objs.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDRELAPSE());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT", objs.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDUPT());
        parameters.put("EPBNEPBREPBUPT", objs.get(0).getEPBNEPBREPBUPT());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF", objs.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDTAF());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDALF", objs.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDALF());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT", objs.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDOPT());
        parameters.put("EPBTAFEPBALFEPBOPT", objs.get(0).getEPBTAFEPBALFEPBOPT());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDNEW", objs.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDNEW());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDRELAPSE", objs.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDRELAPSE());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDUPT", objs.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDUPT());
        parameters.put("EPCDNEPCDREPCDUPT", objs.get(0).getEPCDNEPCDREPCDUPT());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDTAF", objs.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDTAF());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDALF", objs.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDALF());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDOPT", objs.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDOPT());
        parameters.put("EPCDTAFEPCDALFEPCDOPT", objs.get(0).getEPCDTAFEPCDALFEPCDOPT());
        parameters.put("BPSUM", objs.get(0).getBPSUM());
        parameters.put("NEWTOTAL", objs.get(0).getNEWTOTAL());
        parameters.put("TOTALRELAPSE", objs.get(0).getTOTALRELAPSE());
        parameters.put("TOTALPTH", objs.get(0).getTOTALPTH());
        parameters.put("TOTALNRUK", objs.get(0).getTOTALNRUK());
        parameters.put("TOTALTREATMENTAFTERFAILURE", objs.get(0).getTOTALTREATMENTAFTERFAILURE());
        parameters.put("TOTALAFTERLOSSTOFOLLOWUP", objs.get(0).getTOTALAFTERLOSSTOFOLLOWUP());
        parameters.put("TOTALOTERPREVIOUSLYTREATED", objs.get(0).getTOTALOTERPREVIOUSLYTREATED());
        parameters.put("BLOCK1TOTALTOTAL", objs.get(0).getBLOCK1TOTALTOTAL());
        parameters.put("TOTALNRUKPTDXPERTESTPOSITIVE", objs.get(0).getTOTALNRUKPTDXPERTESTPOSITIVE());
        parameters.put("TOTALNRUKHIVSCREENING", objs.get(0).getTOTALNRUKHIVSCREENING());
        parameters.put("TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVE", objs.get(0).getTOTALNRUKHIVSCREENINGHIVRESULTPOSITIVE());
        parameters.put("TOTALNRUKHIVSCREENINGHIVRESULTPOSITIVEARTNOTNULL", objs.get(0).getTOTALNRUKHIVSCREENINGHIVRESULTPOSITIVEARTNOTNULL());
        parameters.put("RIFAMPICINRESISTANTTOTAL", objs.get(0).getRIFAMPICINRESISTANTTOTAL());
        parameters.put("RIFAMPICINRESULTSTOTAL", objs.get(0).getRIFAMPICINRESULTSTOTAL());
        parameters.put("RIFAMPICINRESISTANTPREVIOUSLYTREATED", objs.get(0).getRIFAMPICINRESISTANTPREVIOUSLYTREATED());
        parameters.put("RIFAMPICINRESULTSNEW", objs.get(0).getRIFAMPICINRESULTSNEW());
        parameters.put("RIFAMPICINRESISTANTNEW", objs.get(0).getRIFAMPICINRESISTANTNEW());
        parameters.put("RIFAMPICINRESULTSRELAPSE", objs.get(0).getRIFAMPICINRESULTSRELAPSE());
        parameters.put("RIFAMPICINRESISTANTRELAPSE", objs.get(0).getRIFAMPICINRESISTANTRELAPSE());
        parameters.put("RIFAMPICINRESULTSPREVIOUSLYTREATED", objs.get(0).getRIFAMPICINRESULTSPREVIOUSLYTREATED());

        //TBREPORTBLOCK2 Working starts from here
        ArrayList<TBReportBlock2> objs2 = new ArrayList<>();
        objs2 = (ArrayList<TBReportBlock2>) HibernateUtil.getDBObjectsNew("FROM TBReportBlock2 WHERE cho_code='"+cho_code+"' AND QUARTER='"+quarter+"'");
        parameters.put("CHO_CODE", objs2.get(0).getCHO_CODE());
        parameters.put("CHO_NAME", objs2.get(0).getCHO_NAME());
        parameters.put("DISTRICT", objs2.get(0).getDISTRICT());
        parameters.put("QUARTER", objs2.get(0).getQUARTER());
        parameters.put("CURRENTDATE", dateFormat.format(date));
        //row 1
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN0AND4", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN0AND4());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN0AND4", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN0AND4());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN5AND14", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN5AND14());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN5AND14", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN5AND14());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN15AND24", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN15AND24());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN15AND24", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN15AND24());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN25AND34", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN25AND34());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN25AND34", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN25AND34());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN35AND44", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN35AND44());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN35AND44", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN35AND44());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN45AND54", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN45AND54());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN45AND54", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN45AND54());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN55AND64", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN55AND64());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN55AND64", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN55AND64());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDMALEABOVE65", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEABOVE65());
        parameters.put("PULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEABOVE65", objs2.get(0).getPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEABOVE65());
        //row 1 end

        //row 2 start
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN0AND4", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN0AND4());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN0AND4", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN0AND4());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN5AND14", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN5AND14());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN5AND14", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN5AND14());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN15AND24", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN15AND24());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN15AND24", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN15AND24());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN25AND34", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN25AND34());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN25AND34", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN25AND34());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN35AND44", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN35AND44());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN35AND44", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN35AND44());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN45AND54", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN45AND54());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN45AND54", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN45AND54());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN55AND64", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN55AND64());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN55AND64", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN55AND64());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDMALEABOVE65", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDMALEABOVE65());
        parameters.put("PULMONARYCLINICALLYDIAGNOSEDFEMALEABOVE65", objs2.get(0).getPULMONARYCLINICALLYDIAGNOSEDFEMALEABOVE65());
        //row 2 end

        //row 3 start
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN0AND4", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN0AND4());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN0AND4", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN0AND4());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN5AND14", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN5AND14());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN5AND14", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN5AND14());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN15AND24", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN15AND24());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN15AND24", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN15AND24());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN25AND34", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN25AND34());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN25AND34", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN25AND34());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN35AND44", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN35AND44());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN35AND44", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN35AND44());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN45AND54", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN45AND54());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN45AND54", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN45AND54());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN55AND64", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEBETWEEN55AND64());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN55AND64", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEBETWEEN55AND64());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEABOVE65", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDMALEABOVE65());
        parameters.put("EXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEABOVE65", objs2.get(0).getEXTRAPULMONARYBACTERIOLOGICALLYCONFIRMEDFEMALEABOVE65());
        //row 3 end

        //row 4 start
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN0AND4", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN0AND4());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN0AND4", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN0AND4());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN5AND14", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN5AND14());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN5AND14", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN5AND14());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN15AND24", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN15AND24());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN15AND24", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN15AND24());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN25AND34", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN25AND34());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN25AND34", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN25AND34());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN35AND44", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN35AND44());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN35AND44", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN35AND44());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN45AND54", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN45AND54());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN45AND54", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN45AND54());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN55AND64", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDMALEBETWEEN55AND64());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN55AND64", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEBETWEEN55AND64());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDMALEABOVE65", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDMALEABOVE65());
        parameters.put("EXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEABOVE65", objs2.get(0).getEXTRAPULMONARYCLINICALLYDIAGNOSEDFEMALEABOVE65());
        //row 4 end


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(objs);
        try {
            file = ResourceUtils.getFile("classpath:tb_report.jrxml");
            jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint,"E:\\sample_report.pdf");
        }catch (Exception e){
            return "Error in generating report"+e.getMessage();
        }

        return "Report Generated";
    }
}
