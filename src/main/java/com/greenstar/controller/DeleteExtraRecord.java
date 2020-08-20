package com.greenstar.controller;

import com.greenstar.entity.qat.QATFormHeader;
import com.greenstar.utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Syed Muhammad Hassan
 * $Date
 */
@Controller
public class DeleteExtraRecord {
    @RequestMapping(value = "/DeleteExtraRecord", method = RequestMethod.GET)
    @ResponseBody
    public String index(){
        ArrayList<Object> objs = new ArrayList<>();
        ArrayList<Object> forms = new ArrayList<>();
     //   forms = HibernateUtil.getDBObjectsFromSQLQueryNew("SELECT * FROM QAT_FORMHEADER",this);
        ArrayList<Long> formIds = new ArrayList<>();
        ArrayList<Long> areaIds = new ArrayList<>();
        String query="";
        for(int i=0; i<forms.size();i++){
            Object[] idTempList = new Object[20];
            idTempList = (Object[]) forms.get(i);
            formIds.add(Long.parseLong(idTempList[0].toString()));
        }
        for(long id : formIds) {
            query = "SELECT min(id) AS ID,areaId FROM qat_areadetail where formId='" + id + "' group by areaId order by areaId";

            ArrayList<Object> areaId = new ArrayList<>();
           // areaId = HibernateUtil.getDBObjectsFromSQLQueryNew(query,this);

            for (int i = 0; i < areaId.size(); i++) {
                Object[] idTempList = new Object[20];
                idTempList = (Object[]) areaId.get(i);
                areaIds.add(Long.parseLong(idTempList[0].toString()));
            }
        }
        String finalIDS="";
        for(long ii : areaIds){
            finalIDS += ii+",";
        }

        return finalIDS;
    }

    @RequestMapping(value = "/DeleteExtraRecordQuestion", method = RequestMethod.GET)
    @ResponseBody
    public String indexQuestion(){
        ArrayList<Object> objs = new ArrayList<>();
        ArrayList<Object> forms = new ArrayList<>();
      //  forms = HibernateUtil.getDBObjectsFromSQLQueryNew("SELECT * FROM QAT_FORMHEADER");
        ArrayList<Long> formIds = new ArrayList<>();
        ArrayList<Long> areaIds = new ArrayList<>();
        String query="";
        for(int i=0; i<forms.size();i++){
            Object[] idTempList = new Object[20];
            idTempList = (Object[]) forms.get(i);
            formIds.add(Long.parseLong(idTempList[0].toString()));
        }
        String formNoDataID="";
        for(long id : formIds) {
            query = "SELECT min(id) AS ID, questionid FROM qat_formquestion where formId='" + id + "' group by questionid order by questionid";

            ArrayList<Object> areaId = new ArrayList<>();
         //   areaId = HibernateUtil.getDBObjectsFromSQLQueryNew(query);
            if (areaId.size() != 140) {
                int check = 0;
                formNoDataID += id + ",";
            }
        }/*
            for (int i = 0; i < areaId.size(); i++) {
                Object[] idTempList = new Object[20];
                idTempList = (Object[]) areaId.get(i);
                areaIds.add(Long.parseLong(idTempList[0].toString()));
            }
        }
        String finalIDS="";
        for(long ii : areaIds){
            finalIDS += ii+",";
        }
*/
        return formNoDataID;
    }
}
