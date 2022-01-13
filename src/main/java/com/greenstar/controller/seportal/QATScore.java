package com.greenstar.controller.seportal;

import com.google.gson.Gson;
import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.*;
import com.greenstar.entity.qat.Area;
import com.greenstar.entity.qat.Question;
import com.greenstar.utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 27th October, 2021
 */

@Controller
public class QATScore {
    @RequestMapping(value = "/qatgraph", method = RequestMethod.GET,params={"providerCode"})
    @ResponseBody
    public String getQATGraph(String providerCode){
        List<String> labels = new ArrayList<>();
        List<Datasets> datasets = new ArrayList<>();
        List<Double> dataAverage = new ArrayList<>();
        List<Double> data = new ArrayList<>();

        String qatAverage = "SELECT a.area, ROUND(SUM(ad.totalcriticalindicators)/(SELECT COUNT(*) from QAT_FORMHEADER WHERE approvalstatus=1),2) as averageScore FROM qat_areadetail ad \n" +
                "INNER JOIN qat_area a ON a.id = ad.areaid \n" +
                "INNER JOIN QAT_FORMHEADER FH ON FH.id = ad.formid where fh.approvalstatus=1 \n" +
                "GROUP BY a.area, a.id order by a.id";


        ArrayList<Object> objAverage = HibernateUtil.getDBObjectsFromSQLQueryNew(qatAverage);

        if(objAverage!=null){
            for(Object obj : objAverage){
                if(obj!=null){
                    Object[] objArray = (Object[]) obj;
                    labels.add(objArray[0].toString());
                    dataAverage.add(Double.valueOf(objArray[1].toString()));
                }
            }
        }
        if(providerCode!=null) {
            String queryName = "SELECT * FROM SAL_SM_PROVIDER WHERE MPV_CODE = '" + providerCode + "'";
            String name = "";
            ArrayList<Object> obs = HibernateUtil.getDBObjectsFromSQLQuery(queryName);

            if (obs != null && obs.size() > 0) {
                Object[] objArray = (Object[]) obs.get(0);
                if (objArray != null && objArray.length > 0) {
                    name = objArray[3].toString();
                }
            }

            String queryProviderData = "SELECT a.area, ad.totalcriticalindicators as SCORE FROM qat_areadetail ad \n" +
                    "INNER JOIN qat_area a ON a.id = ad.areaid \n" +
                    "where ad.formid in (SELECT MAX(ID) FROM qat_formheader WHERE providercode='"+providerCode+"' and approvalstatus=1) order by a.id";

            ArrayList<Object> objs = HibernateUtil.getDBObjectsFromSQLQueryNew(queryProviderData);

            if(objs!=null){
                for(Object obj : objs){
                    if(obj!=null){
                        Object[] objArray = (Object[]) obj;
                        data.add(Double.valueOf(objArray[1].toString()));
                    }
                }
            }
            if(data!=null && data.size()>0) {
                Datasets datasetDoctor = new Datasets();
                datasetDoctor.setLabel(name);
                datasetDoctor.setData(data);
                datasetDoctor.setBackgroundColor("rgba(255, 95, 32, 0.8)");
                datasetDoctor.setBorderColor("transparent");
                datasetDoctor.setPointBackgroundColor("rgba(148,159,177,1)");
                datasetDoctor.setPointBorderColor("#fff");
                datasetDoctor.setPointHoverBackgroundColor("#fff");
                datasetDoctor.setPointHoverBorderColor("#fff");
                datasets.add(datasetDoctor);
            }
        }





        Datasets datasetAvg = new Datasets();
        datasetAvg.setLabel("Average");
        datasetAvg.setData(dataAverage);
        datasetAvg.setBackgroundColor("rgba(3, 168, 78, 1)");
        datasetAvg.setBorderColor("transparent");
        datasetAvg.setPointBackgroundColor("rgba(148,159,177,1)");
        datasetAvg.setPointBorderColor("#fff");
        datasetAvg.setPointHoverBackgroundColor("#fff");
        datasetAvg.setPointHoverBorderColor("#fff");
        datasets.add(datasetAvg);


        BarChart barChart = new BarChart();

        barChart.setLabels(labels);
        barChart.setDatasets(datasets);

        return new Gson().toJson(barChart);
    }

    @RequestMapping(value = "/qatareas", method = RequestMethod.GET)
    @ResponseBody
    public String getQATAreas(){
        List<Area> areas = (List<Area>) HibernateUtil.getDBObjects("from Area");
        AreaDetail areaDetailTemp = new AreaDetail();
        List<AreaDetail> areaDetailListJSON = new ArrayList<>();
        for(Area area : areas){
            areaDetailTemp = new AreaDetail();
            areaDetailTemp.setHeading(area.getArea());
            List<Question> questions = (List<Question>) HibernateUtil.getDBObjects("from Question where isCritical=1 and areaId = "+area.getId());
            areaDetailTemp.setQuestions(questions);
            areaDetailListJSON.add(areaDetailTemp);
        }

        return new Gson().toJson(areaDetailListJSON);
    }
}

