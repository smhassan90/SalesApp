package com.greenstar.controller.tb;

import com.greenstar.entity.tb.TBReport;
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
