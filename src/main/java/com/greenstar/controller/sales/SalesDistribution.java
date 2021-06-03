package com.greenstar.controller.sales;

import com.greenstar.controller.hs.HSSync;
import com.greenstar.entity.sale.SDMonthlyFinalData;
import com.greenstar.entity.sale.SaleDetailTemp;
import com.greenstar.entity.sale.base.*;
import com.greenstar.utils.HibernateUtil;
import com.greenstar.utils.LogToFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Syed Muhammad Hassan
 * 8th April, 2021
 */
@Controller
public class SalesDistribution {
    final static Logger LOG = Logger.getLogger("SalesDistribution");

    @RequestMapping(value = "/distributeSales", method = RequestMethod.GET,params={"huid"})
    @ResponseBody
    public String distributeSales(String huid){

        PRDGroupOn prdgrpon = null;

        List<SDMonthlyFinalData> sdMonthlyFinalDataList = new ArrayList<>();
        sdMonthlyFinalDataList = (List<SDMonthlyFinalData>) HibernateUtil.getDBObjects("from SDMonthlyFinalData where BOOKED_BY='-' and TRANSACTION_DATE like '%-MAR-21'");
        //     sdMonthlyFinalDataList = (List<SDMonthlyFinalData>) HibernateUtil.getDBObjects("from SDMonthlyFinalData where HUID="+huid);
        long startCurrentMilis = Calendar.getInstance().getTimeInMillis();
        int i =0;
        if(sdMonthlyFinalDataList!=null && sdMonthlyFinalDataList.size()>0) {
            for (SDMonthlyFinalData sdMonthlyFinalData : sdMonthlyFinalDataList) {

                SaleDetailTemp saleDetail = new SaleDetailTemp();
                saleDetail.setHUID(sdMonthlyFinalData.getHUID());
                saleDetail.setSUID(sdMonthlyFinalData.getSUID());
                saleDetail.setSNDPOP(sdMonthlyFinalData.getSNDPOP());
                saleDetail.setCUST_NUMBER(sdMonthlyFinalData.getCUST_NUMBER());
                saleDetail.setCUST_NAME(sdMonthlyFinalData.getCUST_NAME());
                saleDetail.setADDRESS(sdMonthlyFinalData.getADDRESS());
                saleDetail.setPROVIDER_CODE(sdMonthlyFinalData.getPROVIDER_CODE());
                saleDetail.setLETTER(sdMonthlyFinalData.getLETTER());
                saleDetail.setTERRITORY(sdMonthlyFinalData.getTERRITORY());
                saleDetail.setTERRITORY_NAME(sdMonthlyFinalData.getTERRITORY_NAME());
                saleDetail.setDEPOT(sdMonthlyFinalData.getDEPOT());
                saleDetail.setSGP(sdMonthlyFinalData.getSGP());
                saleDetail.setGRP_NAME(sdMonthlyFinalData.getGRP_NAME());
                saleDetail.setPRODUCT_NO(sdMonthlyFinalData.getPRODUCT_NO());
                saleDetail.setPRD_NAME(sdMonthlyFinalData.getPRD_NAME());
                saleDetail.setPRD_SIZE(sdMonthlyFinalData.getPRD_SIZE());
                saleDetail.setPRICE(sdMonthlyFinalData.getPRICE());

                saleDetail.setTRANSACTION_DATE(sdMonthlyFinalData.getTRANSACTION_DATE());

                saleDetail.setINVOICE_NO(sdMonthlyFinalData.getINVOICE_NO());
                saleDetail.setBATCH(sdMonthlyFinalData.getBATCH());

//            saleDetail.setEXPIRY(sdMonthlyFinalData.getEXPIRY());

                saleDetail.setCLASS(sdMonthlyFinalData.getCLASS());
                String DIT_UNIT = "";
                double NET_VALUE = 0;
                double NET_QTY_NUM = 0;
                int unit = 0;
                DIT_UNIT = HibernateUtil.getSingleString("SELECT DIT_UNIT FROM SD_SKU where sku_id='" + sdMonthlyFinalData.getGSM_PRODUCT_CODE() + "'");
                if (DIT_UNIT != null && !DIT_UNIT.equals("")) {
                    unit = Integer.valueOf(DIT_UNIT);
                }
                double sum = Double.valueOf(sdMonthlyFinalData.getNET_QTY()) + sdMonthlyFinalData.getBONUS();

                NET_QTY_NUM = Double.valueOf(sdMonthlyFinalData.getNET_QTY());
                NET_VALUE = sdMonthlyFinalData.getNET_VALUE();

                if (sdMonthlyFinalData.getTYPE().equals("R")) {
                    NET_QTY_NUM = NET_QTY_NUM * -1;
                    NET_VALUE = NET_VALUE * -1;
                    sum = sum *-1;
                }
                double EACH_QTY_NUM = sum * unit;
                saleDetail.setNET_QTY(String.valueOf(NET_QTY_NUM));
                saleDetail.setNET_VALUE(NET_VALUE);
                saleDetail.setE_QTY(EACH_QTY_NUM);
                saleDetail.setBONUS(sdMonthlyFinalData.getBONUS());
                saleDetail.setDISCOUNTS(sdMonthlyFinalData.getDISCOUNTS());
                saleDetail.setCYP(sdMonthlyFinalData.getCYP());
                saleDetail.setGSM_PRODUCT_CODE(sdMonthlyFinalData.getGSM_PRODUCT_CODE());

                saleDetail.setGSM_TOWN(sdMonthlyFinalData.getGSM_TOWN());
                saleDetail.setSALEDISTRICT(sdMonthlyFinalData.getDISTRICT());
                saleDetail.setSALETEHSIL(sdMonthlyFinalData.getTEHSIL());
                saleDetail.setTYPE(sdMonthlyFinalData.getTYPE());
                saleDetail.setREMARKS(sdMonthlyFinalData.getREMARKS());
                saleDetail.setVID(sdMonthlyFinalData.getVID());
                //saleDetail.setSYSTEM_DATE(sdMonthlyFinalData.getSYSTEM_DATE());
                saleDetail.setBONUS_DISCOUNT(sdMonthlyFinalData.getBONUS_DISCOUNT());
                saleDetail.setFOC_DISCOUNT(sdMonthlyFinalData.getFOC_DISCOUNT());
                saleDetail.setBOOKED_BY(sdMonthlyFinalData.getBOOKED_BY());
                saleDetail.setSECTION_CODE(sdMonthlyFinalData.getSECTION_CODE());
                saleDetail.setSECTION_NAME(sdMonthlyFinalData.getSECTION_NAME());
                saleDetail.setNATURE(sdMonthlyFinalData.getNATURE());

                //VIEW
                try {
                    String POSITION_ID = HibernateUtil.getSingleString("SELECT position_id from BASE_EMP_TAGGING where tagged_to = '" + sdMonthlyFinalData.getPROVIDER_CODE() + "'");
                    String sas_id = HibernateUtil.getSingleString("SELECT sas_id from BASE_EMPID_POSITIONID_MAPPING where position_id = '" + POSITION_ID + "'");
                    String EMPLOYEE_ID = HibernateUtil.getSingleString("SELECT EMPLOYEE_ID from BASE_EMPID_POSITIONID_MAPPING where position_id = '" + POSITION_ID + "'");
                    String teamregion_id = HibernateUtil.getSingleString("SELECT teamregion_id from base_emp_position_teamregion where position_id = '" + POSITION_ID + "'");
                    String teamregion = HibernateUtil.getSingleString("SELECT NAME from base_team_region where id =" + teamregion_id);
                    String tehsil_id = "";

                    prdgrpon = null;
                    // Fetch Group on against each Sale record
                    String query = "from PRDGroupOn where PRD_NAME='" + sdMonthlyFinalData.getPRD_NAME() + "'";
                    List<PRDGroupOn> prdgrpons = (List<PRDGroupOn>) HibernateUtil.getDBObjects(query);

                    if (prdgrpons != null && prdgrpons.size() > 0) {
                        prdgrpon = new PRDGroupOn();
                        prdgrpon = prdgrpons.get(0);
                    }

                    if (sdMonthlyFinalData.getPROVIDER_CODE() != null) {
                        tehsil_id = HibernateUtil.getSingleString("SELECT TEHSIL_ID from BASE_PROVIDER_TEHSIL where PROVIDERCODE = '" + sdMonthlyFinalData.getPROVIDER_CODE() + "'");
                    } else {
                        tehsil_id = HibernateUtil.getSingleString("SELECT MAX(tehsil_id) from BASE_TEHSIL_SNDPOP where sndpop_id = SUBSTR('" + sdMonthlyFinalData.getSNDPOP() + "',1,9) || SUBSTR('" + sdMonthlyFinalData.getDEPOT() + "',1,3) || '" + sdMonthlyFinalData.getTERRITORY() + "'");
                    }

                    String TEHSIL = HibernateUtil.getSingleString("SELECT name from base_tehsil_master where id = " + tehsil_id);
                    String dist_id = HibernateUtil.getSingleString("SELECT dist_id from base_dist_tehsil where tehsil_id = " + tehsil_id);
                    String DISTRICT = HibernateUtil.getSingleString("SELECT name from base_district_master where id = " + dist_id);
                    String region_id = HibernateUtil.getSingleString("SELECT region_id from base_dist_region_mapping where dist_id = '" + dist_id + "'");
                    String region = HibernateUtil.getSingleString("SELECT name from base_region_master where id = '" + region_id + "'");
                    String province_id = HibernateUtil.getSingleString("SELECT province_id from base_dist_province where dist_id = '" + dist_id + "'");
                    String province = HibernateUtil.getSingleString("SELECT name from base_province_master where id = '" + province_id + "'");
                    String channel = HibernateUtil.getSingleString("SELECT channel from base_mnp_channel_mapping where class_code = '" + sdMonthlyFinalData.getCLASS() + "'");
                    String TEAM_ID = HibernateUtil.getSingleString("SELECT TEAM_ID from BASE_EMP_POSITION_TEAM where POSITION_ID = '" + POSITION_ID + "'");
                    String TEAM = HibernateUtil.getSingleString("SELECT name from BASE_TEAM_DEPT where id = " + TEAM_ID);
                    String zone_id = HibernateUtil.getSingleString("SELECT zone_id from base_emp_zone_mapping where position_id = '" + POSITION_ID + "'");
                    String zone = HibernateUtil.getSingleString("SELECT zone from BASE_ZONE where id = " + zone_id);
                    String subzone = HibernateUtil.getSingleString("SELECT subzone from BASE_ZONE where id = " + zone_id);


                    ArrayList<PRDGroupOn> prdGroupOns = (ArrayList<PRDGroupOn>) HibernateUtil.getDBObjects("from PRDGroupOn where PRD_NAME='" + sdMonthlyFinalData.getPRD_NAME() + "'");
                    PRDGroupOn prdGroupOn = new PRDGroupOn();
                    if (prdGroupOns != null && prdGroupOns.size() > 0) {
                        prdGroupOn = prdGroupOns.get(0);
                    }

                    saleDetail.setPOSITION_CODE(POSITION_ID);
                    saleDetail.setSASCODE(sas_id);
                    saleDetail.setEMPLOYEEID(EMPLOYEE_ID);
                    saleDetail.setTEAMREGION(teamregion);
                    saleDetail.setZONE(zone);
                    saleDetail.setSUBZONE(subzone);
                    saleDetail.setDISTRICT(DISTRICT);
                    saleDetail.setTEHSIL(TEHSIL);
                    saleDetail.setPROVINCE(province);
                    saleDetail.setREGION(region);
                    saleDetail.setTEAM(TEAM);
                    saleDetail.setCHANNEL(channel);
                    saleDetail.setGROUPON(prdGroupOn.getGROUP_ON());
                    saleDetail.setGRP(prdGroupOn.getGRP());
                    saleDetail.setGRP_CATEGORY(prdGroupOn.getPRD_CAT());
                    saleDetail.setPRODUCTGROUP(prdGroupOn.getPRD_GRP());
                    saleDetail.setPROVIDERCODE(sdMonthlyFinalData.getPROVIDER_CODE());


                    if (sdMonthlyFinalData.getPROVIDER_CODE() != null) {
                        //When provider code is not null
                        String nature = sdMonthlyFinalData.getNATURE();
                        if (nature != null && nature.equals("Direct HS")) {
                            if (!saleDetail.getTEAM().equals("Health Service")) {
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                //Fetching Employee information
                                saleDetail = getSaleDetailObject(saleDetail, "CHO", territory);
                            }
                        } else if (nature != null && nature.equals("Direct IPC")) {
                            if (!saleDetail.getTEAM().equals("Inter Personal Communication")) {
                                String remarks = "Position Mapping Required";
                                saleDetail.setGSM_REMARKS(remarks);
                            }
                        } else if (nature != null && nature.equals("Direct Marketing")) {
                            if (!saleDetail.getTEAM().equals("Pharmaceutical")) {
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                //Fetching Employee information
                                saleDetail = getSaleDetailObject(saleDetail, "MIO", territory);
                            }
                        } else if (prdgrpon.getGRP().equals("Nutraceutical")) {
                            //  Sale belongs to CHO
                            if (POSITION_ID.contains("CHO")) {
                                //Already tagged cho will get the sales
                            } else if (POSITION_ID.contains("MIO")) {
                                //We need to tag it to CHO through town staff mapping.
                                //We can have Town from SDMonthlyFinalData
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                //Fetching Employee information
                                saleDetail = getSaleDetailObject(saleDetail, "CHO", territory);

                            }
                        } else if (prdgrpon.getPRD_GRP().contains("Novaject")
                                || prdgrpon.getPRD_GRP().contains("Femi Ject")
                                || prdgrpon.getPRD_GRP().contains("Enofer")) {
                            if (!POSITION_ID.contains("MIO")) {
                                String territory = sdMonthlyFinalData.getTERRITORY();
                                //Fetching Employee information
                                saleDetail = getSaleDetailObject(saleDetail, "MIO", territory);
                            }

                        } else {
                            //  Depends on tagging

                        }

                    } else {
                        //When provider code is null

                        if (!sdMonthlyFinalData.getBOOKED_BY().equals("-")) {
                            //Sales belongs to tagged SPO
                        } else if (sdMonthlyFinalData.getBOOKED_BY() == "-") {
                            if (sdMonthlyFinalData.getSGP() > 83
                                    && prdgrpon.getPRD_GRP().equals("CONDOM")) {
                                //Sales belongs to SPO
                            } else if (sdMonthlyFinalData.getSGP() < 83) {
                                if (prdgrpon.getGROUP_ON().contains("Do Ultra Thin")
                                        || prdgrpon.getPRD_GRP().contains("Sathi")) {
                                    //Sale belongs to Sales ASM
                                } else {
                                    //Sale belongs to MIO
                                    saleDetail = getSaleDetailObject(saleDetail, "MIO", sdMonthlyFinalData.getTERRITORY());
                                }
                            }
                        }
                    }
                    saleDetail = getManagedChannel(saleDetail);
                    HibernateUtil.saveOrUpdate(saleDetail);
                } catch (Exception e) {
                    LOG.severe(e.getMessage());
                    printError(e, sdMonthlyFinalData.getHUID());
                }

            }
        }

        long endCurrentMilis = Calendar.getInstance().getTimeInMillis();
        long duration = endCurrentMilis - startCurrentMilis;

        return String.valueOf(duration);
    }

    private void printError(Exception e, double huid) {
        LogToFile.log(null,"severe", "e.getMessage() : "+ e.getMessage() +
                " \n e.getCause() : "+ e.getCause() +
                " \n e.e.getStackTrace()"+e.getStackTrace().toString() +
                " \n HUID : "+huid
        );

    }

    private SaleDetailTemp getManagedChannel(SaleDetailTemp saleDetail) {
        if(saleDetail.getPROVIDER_CODE()!=null && !saleDetail.getPROVIDER_CODE().equals("")
                && !saleDetail.getCHANNEL().equals("Provider")){
            saleDetail.setCHANNEL("Provider");
        } else if(saleDetail.getCHANNEL().equals("Provider")
                && saleDetail.getPROVIDER_CODE()==null){
            saleDetail.setCHANNEL("Pharmacies");
        }
        return saleDetail;
    }

    private SaleDetailTemp getSaleDetailObject(SaleDetailTemp saleDetail, String saleTo, String territory){
        /*
                        String tehsilId = HibernateUtil.getSingleString("SELECT tehsil_id FROM hs_providers where code='"+sdMonthlyFinalData.getPROVIDER_CODE()+"'");
                        TehsilMaster tehsilMaster = (TehsilMaster) HibernateUtil.getDBObjects("from TehsilMaster where ");

                        String district = HibernateUtil.getSingleString("SELECT name from base_district_master,  base_dist_tehsil dt where dt.tehsil_id = '"+tehsilId+"'");
                        String districtId = HibernateUtil.getSingleString("SELECT dm.id from base_district_master dm,  base_dist_tehsil dt where dt.tehsil_id = '"+tehsilId+"'");
                        String province = HibernateUtil.getSingleString("SELECT name from base_province_master,  base_dist_province dp where dp.dist_id = '"+districtId+"'");
                        */
        /*
                        saleDetail.setTEHSIL(tehsilMaster.getNAME());
                        saleDetail.setPROVINCE(province);
                        saleDetail.setDISTRICT(district);
                        */

        //Fetching Employee information
        TerritoryEmployeeMapping territoryEmployeeMapping = new TerritoryEmployeeMapping();
        String employeePositionId = HibernateUtil.getSingleString("SELECT EMP_ID FROM BASE_TERRITORY_EMP_MAPPING where emp_id like '%"+saleTo+"%' and territory_code='"+territory+"'");
        String employeeID = HibernateUtil.getSingleString("SELECT EMPLOYEE_ID FROM BASE_EMPID_POSITIONID_MAPPING where POSITION_ID='"+employeePositionId+"'");
        ArrayList<Employee> employees =
                (ArrayList<Employee>) HibernateUtil.getDBObjects("from Employee where ID='"+employeeID+"'");
        Employee employee = new Employee();
        if(employees!=null && employees.size()>0){
            employee = employees.get(0);
        }
        String teamRegionId = HibernateUtil.getSingleString("SELECT TEAMREGION_ID FROM BASE_EMP_POSITION_TEAMREGION where POSITION_ID='"+employeePositionId+"'");
        String teamRegion = HibernateUtil.getSingleString("SELECT NAME FROM BASE_TEAM_REGION where id= "+teamRegionId);
        String zoneId =  HibernateUtil.getSingleString("SELECT ZONE_ID from BASE_EMP_ZONE_MAPPING where POSITION_ID='"+employeePositionId+"'");
        ArrayList<Zone> zoneList = (ArrayList<Zone>) HibernateUtil.getDBObjects("from Zone where id =  "+zoneId);
        Zone zone = new Zone();
        if(zoneList!=null && zoneList.size()>0){
            zone = zoneList.get(0);
        }
        String teamId = HibernateUtil.getSingleString("SELECT TEAM_ID FROM BASE_EMP_POSITION_TEAM where POSITION_ID='"+employeePositionId+"'");
        String team = HibernateUtil.getSingleString("SELECT NAME FROM BASE_TEAM_DEPT where id= "+teamId);
        String strZone = zone.getZONE();
        String strSubZone = zone.getSUBZONE();
        saleDetail.setTEAM(team);
        saleDetail.setZONE(strZone);
        saleDetail.setSUBZONE(strSubZone);
        saleDetail.setPOSITION_CODE(employeePositionId);
        saleDetail.setEMPLOYEEID(employeeID);
        saleDetail.setTEAMREGION(teamRegion);


        return saleDetail;
    }
}
