package com.greenstar.controller.hs;

import com.greenstar.utils.HibernateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 20th August, 2020
 */

@Controller
public class ProviderTagging {

    @RequestMapping(value = "/ProviderTagging", method = RequestMethod.GET,params={"team"})
    @ResponseBody
    public String index(String team){
        String html="<html>";

        html +=getCheckForNull();
        html+=checkActiveInActiveProvider();
        html+=checkNewProviderCode();
        html+=checkNewTerritoryCode();
        html+=checkNewAMCode();
        html+=checkNewDonor();
        html+=checkDifferentDonors();
        html+=checkDataNeedToTag(team);
        html+=checkDataNeedToTag(team);
        html+=checkAllMappingSummary();

        html+="</html>";

        return html;
    }

    /*
    --CHECK FOR NULL
     */
    private String getCheckForNull(){
        String query = "SELECT PROVIDER_CODE, PROVIDER_NAME, TERRITORY_CODE, cho_code, cho_name, am_code, am_name, donor\n" +
                "FROM HS_MAPPING_TEMP\n" +
                "WHERE (AM_CODE IS NULL) OR \n" +
                "(AM_NAME IS NULL) OR  \n" +
                "(CHO_CODE IS NULL) OR \n" +
                "(CHO_NAME IS NULL) OR \n" +
                "(DONOR IS NULL) OR \n" +
                "(PROVIDER_CODE IS NULL) OR \n" +
                "(PROVIDER_NAME IS NULL) OR \n" +
                "(TERRITORY_CODE IS NULL)";
        ArrayList<String> headers = new ArrayList<>();
        headers.add("providerCode");
        headers.add("providerName");
        headers.add("TerritoryCode");
        headers.add("CHOCode");
        headers.add("CHOName");
        headers.add("AMCode");
        headers.add("AMName");
        headers.add("Donor");


        return HibernateUtil.getHTMLForQuery(query, headers, "Empty Fields");
    }

    /*
    CHECK ACTIVE / INACTIVE PROVIDERS
     */
    private String checkActiveInActiveProvider(){
        String query = "SELECT DECODE(STATUS,1,'Active',0,'Inactive') AS STATUS, COUNT(*) AS Count FROM HS_PROVIDERS WHERE CODE IN (SELECT PROVIDER_CODE FROM HS_MAPPING_TEMP)GROUP BY STATUS";
        ArrayList<String> headers = new ArrayList<>();
        headers.add("STATUS");
        headers.add("COUNT");


        return HibernateUtil.getHTMLForQuery(query,headers, "Number of Active and In Active Providers");
    }

    /*
    NEW PROVIDER_CODE
     */
    private String checkNewProviderCode(){
        String query = "SELECT DISTINCT(PROVIDER_CODE) FROM HS_MAPPING_TEMP \n" +
                "WHERE PROVIDER_CODE NOT IN (SELECT CODE FROM HS_PROVIDERS)";
        ArrayList<String> headers = new ArrayList<>();
        headers.add("PROVIDERCODE");


        return HibernateUtil.getHTMLForQuery(query,headers , "Provider Code not in Landscape");
    }

    /*
    NEW TERRITORY_CODE
     */
    private String checkNewTerritoryCode(){
        String query = "SELECT DISTINCT(TERRITORY_CODE) FROM HS_MAPPING_TEMP \n" +
                "WHERE TERRITORY_CODE NOT IN (SELECT TERRITORY_CODE FROM HS_PROVIDER_CHO)";
        ArrayList<String> headers = new ArrayList<>();
        headers.add("TerritoryCode");


        return HibernateUtil.getHTMLForQuery(query,headers, "Not exist TerritoryCode of CHO/QAM");
    }

    /*
   NEW AMCode
    */
    private String checkNewAMCode(){
        String query = "SELECT DISTINCT(AM_CODE) FROM HS_MAPPING_TEMP \n" +
                "WHERE AM_CODE NOT IN (SELECT CODE FROM HS_AM)";
        ArrayList<String> headers = new ArrayList<>();
        headers.add("AMCode");


        return HibernateUtil.getHTMLForQuery(query,headers, "AM Code not exist");
    }

    /*
   NEW Donor
    */
    private String checkNewDonor(){
        String query = "SELECT PROVIDER_CODE, DONOR FROM HS_MAPPING_TEMP \n" +
                "WHERE DONOR NOT IN (SELECT DONOR FROM HS_PROVIDERS)";

        ArrayList<String> headers = new ArrayList<>();
        headers.add("Providercode");
        headers.add("Donor");


        return HibernateUtil.getHTMLForQuery(query,headers, "Donor that does not exist in Landscape");
    }

    /*
  Different Donors
   */
    private String checkDifferentDonors(){
        String query = "SELECT A.CODE, A.NAME, A.DONOR, B.DONOR AS MAPPING_DONOR FROM HS_PROVIDERS A\n" +
                "INNER JOIN HS_MAPPING_TEMP B ON A.CODE = B.PROVIDER_CODE\n" +
                "WHERE A.DONOR<>B.DONOR";

        ArrayList<String> headers = new ArrayList<>();
        headers.add("Providercode");
        headers.add("ProviderName");
        headers.add("Already Added Donor");
        headers.add("New Donor for provider");

        return HibernateUtil.getHTMLForQuery(query,headers, "Difference in donor in landscape and mentioned in file");
    }

    /*
  CORRECT MAPPING ALREADY
   */
    private String checkCorrectMapping(String team){
        String query = "SELECT A.PROVIDER_CODE, A.TERRITORY_CODE, A.UPDATED_DATE FROM HS_PROVIDER_CHO A\n" +
                "INNER JOIN HS_MAPPING_TEMP T ON A.PROVIDER_CODE = T.PROVIDER_CODE\n" +
                "AND A.TERRITORY_CODE = T.TERRITORY_CODE\n" +
                "AND A.TERRITORY_CODE LIKE '%"+team+"%'";
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Providercode");
        headers.add("TERRITORY_CODE");
        headers.add("UPDATED_DATE");

        return HibernateUtil.getHTMLForQuery(query,headers, "Already Corrected Mapping");
    }

    /*
 HS MAPPING DATA - DATA NEED TO TAG
  */
    private String checkDataNeedToTag(String team){
        String query = "SELECT B.PROVIDER_CODE, B.TERRITORY_CODE FROM HS_PROVIDER_CHO A\n" +
                "LEFT JOIN HS_MAPPING_TEMP B ON B.PROVIDER_CODE <> A.PROVIDER_CODE\n" +
                "WHERE --A.TERRITORY_CODE = B.TERRITORY_CODE\n" +
                "B.TERRITORY_CODE LIKE '%"+team+"%'\n" +
                "MINUS\n" +
                "SELECT A.PROVIDER_CODE, A.TERRITORY_CODE FROM HS_PROVIDER_CHO A\n" +
                "INNER JOIN HS_MAPPING_TEMP B ON A.PROVIDER_CODE = B.PROVIDER_CODE\n" +
                "WHERE --A.TERRITORY_CODE = B.TERRITORY_CODE\n" +
                "A.TERRITORY_CODE LIKE '%"+team+"%'\n";
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Providercode");
        headers.add("TERRITORY_CODE");

        return HibernateUtil.getHTMLForQuery(query,headers , "Data need to Tag");
    }

    /*
ALL MAPPING RECORDS FROM PROVIDER CHO IN (HS/IPC/QAM)
  */
    private String checkAllMappingSummary(){
        String query = "SELECT 'QAM' AS Department, COUNT(*) AS COUNT FROM HS_PROVIDER_CHO\n" +
                "WHERE PROVIDER_CODE IN (SELECT PROVIDER_CODE FROM HS_MAPPING_TEMP)\n" +
                "AND TERRITORY_CODE LIKE 'QAM%'\n" +
                "UNION \n" +
                "SELECT 'IPC' AS Department, COUNT(*) AS COUNT FROM HS_PROVIDER_CHO\n" +
                "WHERE PROVIDER_CODE IN (SELECT PROVIDER_CODE FROM HS_MAPPING_TEMP)\n" +
                "AND TERRITORY_CODE LIKE 'IPC%'\n" +
                "UNION\n" +
                "SELECT 'HS' AS Department, COUNT(*) AS COUNT FROM HS_PROVIDER_CHO\n" +
                "WHERE PROVIDER_CODE IN (SELECT PROVIDER_CODE FROM HS_MAPPING_TEMP)\n" +
                "AND TERRITORY_CODE LIKE 'HS%'";
        ArrayList<String> headers = new ArrayList<>();
        headers.add("Department");
        headers.add("Count");

        return HibernateUtil.getHTMLForQuery(query,headers, "Tagging Summary");
    }
}
