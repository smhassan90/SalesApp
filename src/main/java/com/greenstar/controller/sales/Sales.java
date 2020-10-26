package com.greenstar.controller.sales;

import com.greenstar.utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * @author Syed Muhammad Hassan
 * 26th October, 2020
 */

@Controller
public class Sales {

    @RequestMapping(value = "/salesUpdate", method = RequestMethod.GET)
    @ResponseBody
    public String salesUpdate(){

        String maxVID = getMaxVID();
        String minDate = getMinTransactionDate(maxVID);
        String maxDate = getMaxTransactionDate(maxVID);
        ArrayList<Object> rows = new ArrayList<>();
        String strquery = "";
        String strDestID ="";

        rows = getDestinationRecords(minDate,maxDate);


        if(rows!=null && rows.size()>0){
            strDestID = getDestinationVID(minDate, maxDate);

            strquery = "delete from SD_MONTHLY_FINAL_DATA where vid='" + strDestID + "'|||";
        }

        strquery += "insert into  SD_MONTHLY_FINAL_DATA(select * from SAL_DAILY_DATA_HIS_COPY where vid='" + maxVID + "')|||";


        return "Completed";
    }

    private String getDestinationVID(String minDate, String maxDate){

        String maxVID = "";
        String query = "select max(vid) from SD_MONTHLY_FINAL_DATA where TRANSACTION_DATE BETWEEN TO_DATE('" + getOnlyDate(minDate) + "','YYYY-MM-DD') AND TO_DATE('" + getOnlyDate(maxDate) + "','YYYY-MM-DD')";

        maxVID = HibernateUtil.getSingleString(query);

        return maxVID;
    }

    private String getOnlyDate(String date){
        if(date!="" && date.contains(" ")) {
            String[] onlyDate;
            onlyDate = date.split(" ");
            return onlyDate[0];
        }else{
            return "";
        }
    }

    private ArrayList<Object> getDestinationRecords(String minDate, String maxDate){
        ArrayList<Object> rows = new ArrayList<>();
        String query = "select vid from SD_MONTHLY_FINAL_DATA where TRANSACTION_DATE BETWEEN TO_DATE('" + getOnlyDate(minDate) + "','YYYY-MM-DD') AND TO_DATE('" + getOnlyDate(maxDate) + "','YYYY-MM-DD')";

        rows = HibernateUtil.getDBObjectsFromSQLQuery(query);

        return rows;
    }

    private String getMaxVID(){
        String vid =  HibernateUtil.getSingleString("SELECT MAX(VID) FROM sal_daily_data_his_copy");

        return vid;
    }

    private String getMinTransactionDate(String maxVID){
        String date = "";
        String query = "select min(transaction_date) as mindate from SAL_DAILY_DATA_HIS_COPY where vid='" + maxVID + "'";

        date = HibernateUtil.getSingleString(query);

        return date;
    }

    private String getMaxTransactionDate(String maxVID){
        String date = "";
        String query = "select max(transaction_date) as mindate from SAL_DAILY_DATA_HIS_COPY where vid='" + maxVID + "'";

        date = HibernateUtil.getSingleString(query);

        return date;
    }
}
