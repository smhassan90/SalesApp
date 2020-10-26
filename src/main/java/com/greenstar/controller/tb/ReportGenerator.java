package com.greenstar.controller.tb;

import com.greenstar.entity.tb.TB01;
import com.greenstar.utils.HibernateUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Syed Muhammad Hassan
 * 21st October, 2020
 */

@Controller
public class ReportGenerator {
    @RequestMapping(value = "/choReport", method = RequestMethod.GET)
    @ResponseBody
    public String generateCHOReport(){
        File file = null;
        JasperReport jasperReport = null;
        Map<String,Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = null;
        ArrayList<TB01> tbForms = (ArrayList<TB01>) HibernateUtil.getDBObjectsNew("FROM TB01 where serial_no=635");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tbForms);
        try {
            file = ResourceUtils.getFile("classpath:cho_report.jrxml");
            jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
            JasperExportManager.exportReportToPdfFile(jasperPrint,"E:\\sample_report.pdf");
        }catch (Exception e){
            int i = 9;
        }




        return "Done";
    }
}
