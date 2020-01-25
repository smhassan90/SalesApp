package com.greenstar.dao;

import com.greenstar.entity.crb.CRBForm;
import com.greenstar.entity.crb.DropdownCRBData;
import com.greenstar.entity.qtv.Providers;
import com.greenstar.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 29th November, 2019
 */
public class CRBSyncDAO {

    /*
        Get Provider name
     */
    public Providers getProvider(String code){
        List<Providers> providers = new ArrayList<>();
        String query = "FROM Providers WHERE code ='"+code+"'";
        providers = (List<Providers>) HibernateUtil.getDBObjects(query);

        return providers.get(0);
    }

    /*
        Get CRB Forms
     */
    public List<CRBForm> getCRBForms(String code){
        List<CRBForm> crbForms = new ArrayList<>();
        String query = "FROM CRBForm WHERE providerCode ='"+code+"'";
        crbForms = (List<CRBForm>) HibernateUtil.getDBObjects(query);

        return crbForms;
    }

    /*
        Get Dropdown data
     */
    public List<DropdownCRBData> getDropdownData(){
        List<DropdownCRBData> dropdownCRBData = new ArrayList<>();
        String query = "FROM DropdownCRBData";
        dropdownCRBData = (List<DropdownCRBData>) HibernateUtil.getDBObjects(query);

        return dropdownCRBData;
    }

    /*
    Get Staff Name
     */
    public String getStaffName(String code){
        return HibernateUtil.getSingleString("SELECT staff_Name FROM DTC_STAFF WHERE staff_Code= '"+code+"'");
    }
}
