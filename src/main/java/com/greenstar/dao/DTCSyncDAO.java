package com.greenstar.dao;

import com.greenstar.entity.dtc.District;
import com.greenstar.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 18/9/2019
 */
public class DTCSyncDAO {

    /*
        Get Districts
     */
    public List<District> getDistricts(String code){

        String query = "FROM District";
        List<District> districts = new ArrayList<District>();
        districts = (List<District>) HibernateUtil.getDBObjects(query);

        return districts;
    }
    /*
    Get Staff Name
     */
    public String getStaffName(String code){
        return HibernateUtil.getSingleString("SELECT staff_Name FROM DTC_STAFF WHERE staff_Code= '"+code+"'");
    }

}
