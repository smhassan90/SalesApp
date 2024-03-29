package com.greenstar.dao;

import com.greenstar.controller.greensales.Codes;
import com.greenstar.dal.Packet;
import com.greenstar.dal.SyncObjectSS;
import com.greenstar.entity.*;
import com.greenstar.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Syed Muhammad Hassan
 * 24/06/2019
 */
public class SyncDAO {
    final static Logger LOG = Logger.getLogger(SyncDAO.class);

    /*
    Get all Staff
     */
    public List<SDStaff> getStaffs(){
        String query =  "from SDStaff";
        List<SDStaff> data = new ArrayList<SDStaff>();
        data = (List<SDStaff>) HibernateUtil.getDBObjects(query);

        return data;
    }

    /*
    Get  Staff
     */
    public String getStaffName(String code){
        String query =  "from SDStaff WHERE staffCode='"+code+"'";
        List<SDStaff> data = new ArrayList<>();
        data = (List<SDStaff>) HibernateUtil.getDBObjects(query);
        if(data!=null && data.size()>0){
            return data.get(0).getStaffName();
        }else{
            return "";
        }
    }

    /*
        Fetch all depots which are tagged to this person (Code).
     */
    public List<SDDepot> getDepots(String code){
        List<SDDepot> sdDepots = new ArrayList<SDDepot>();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery("SELECT DISTINCT(sd_depo.DEPO_CODE) ,sd_depo.DEPO_NAME FROM sd_depo" +
                    " JOIN sd_depo_staff ds on ds.DEPO_ID=sd_depo.DEPO_CODE WHERE ds.STAFF_CODE='"+code+"'")
                    .addEntity(SDDepot.class);
            sdDepots = query.list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return sdDepots;
    }

    /*
    Fetch All Towns which are tagged to person.
     */
    public List<SDTown> getTowns(String code){
        List<SDTown> sdTowns = new ArrayList<SDTown>();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery("SELECT DISTINCT(t.TOWN_ID), t.TOWN_NAME FROM SD_TOWN t " +
                    "INNER JOIN SD_TOWN_STAFF ts ON ts.TOWN_ID=t.TOWN_ID WHERE ts.STAFF_CODE='"+code+"'")
                    .addEntity(SDTown.class);
            sdTowns = query.list();
        }catch (Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }
        return sdTowns;
    }

    /*
    Get cll customers tagged to person who is logging in.
     */
    public List<SDCustomer> getCustomers(String code){
        List<SDCustomer> customers = new ArrayList<SDCustomer>();
        Session session = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            SQLQuery query = session.createSQLQuery("SELECT SPR.PROVIDER_CODE as custCode, sp.MPV_DESC as custName, CONCAT(MPV_ADD1,MPV_ADD2) AS custAdd,'1' as TYPE FROM SAL_SM_PROVIDER SP \n" +
                    "INNER JOIN SD_STAFF_PROVIDER SPR ON  SPR.PROVIDER_CODE = SP.MPV_CODE WHERE SP.DONOR_NEW != 'OLD' AND spr.staff_code='"+code+"'");

            SDCustomer dest=null;
            Iterator iterator= query.list().iterator();
            while(iterator.hasNext()){
                Object[] tuple= (Object[]) iterator.next();
                dest= new SDCustomer();
                dest.setCustCode((String)tuple[0]);
                dest.setCustName((String)tuple[1]);
                dest.setCustAdd((String)tuple[2]);
                dest.setType("");
                customers.add(dest);
            }
        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return customers;
    }

    /*
    Fetch the mapping of Depot with region.
     */
    public List<SDTownDepot> getDepotRegionMapping(String code){
        List<SDTownDepot> townDepots = new ArrayList<SDTownDepot>();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String sql = "SELECT DISTINCT(td.TOWN_ID), td.DEPO_ID FROM SD_TOWN_DEPO td " +
                    " INNER JOIN SD_DEPOCUST_STAFF DCS ON td.DEPO_ID=SUBSTR(DEPO_CUST,0,3) AND DCS.STAFF_CODE='"+code+"'" +
                    " INNER JOIN SD_TOWN_STAFF TS ON TS.TOWN_ID=td.TOWN_ID AND TS.STAFF_CODE='"+code+"'";
            SQLQuery query = session.createSQLQuery(sql)
                    .addEntity(SDTownDepot.class);

            townDepots = query.list();
        }catch (Exception e){
            LOG.error(e);
        }finally {
            session.close();
        }

        return townDepots;
    }

    /*
    Fetch the Region and customer mapping.
     */
    public List<SDTownCustomer> getRegionCustomerMapping(String code){
        List<SDTownCustomer> townCustomers = new ArrayList<SDTownCustomer>();
        Session session = null;
        String queryString="SELECT DISTINCT(TC.CUSTOMER_ID), TC.TOWN_ID\n" +
                "   FROM SD_TOWN_CUSTOMER TC\n" +
                "   INNER JOIN SD_TOWN_STAFF TS ON TS.TOWN_ID=TC.TOWN_ID AND TS.STAFF_CODE='"+code+"'" +
                "   INNER JOIN SD_DEPOCUST_STAFF DCS ON DCS.STAFF_CODE='"+code+"' " +
                " AND SUBSTR(DCS.DEPO_CUST,4)=TC.CUSTOMER_ID";
        try{
            session = HibernateUtil.getSessionFactory().openSession();

            SQLQuery query = session.createSQLQuery(queryString)
                    .addEntity(SDTownCustomer.class);
            townCustomers = query.list();
        }catch(Exception e ){
            LOG.error(e);
        }finally {
            session.close();
        }

        return townCustomers;
    }

    /*
    Fetch the status
     */
    public List<SDStatus> getStatuses(){
        List<SDStatus> statuses = new ArrayList<SDStatus>();
        String queryString="FROM SDStatus";
        statuses = (List<SDStatus>) HibernateUtil.getDBObjects(queryString);

        return statuses;
    }

    /*
    Get SKU
     */
    public List<SDSKUGroup> getSKUGroup(){
        List<SDSKUGroup> skuGroup = new ArrayList<SDSKUGroup>();
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            String queryString="SELECT DISTINCT(GRP_ID), GRP_NAME FROM SD_SKU_GRP WHERE PRD_ID NOT IN ('DOO','STH','TCH','JDL') ORDER BY GRP_NAME";
            SQLQuery query = session.createSQLQuery(queryString)
                    .addEntity(SDSKUGroup.class);
            skuGroup = query.list();
        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.close();
        }

        return skuGroup;
    }

    /*
    Get Work with (RSM/ASM)
     */
    public List<GSSWorkWith> getWorkWiths(){
        List<GSSWorkWith> workWiths = new ArrayList<GSSWorkWith>();

        String queryString="FROM GSSWorkWith";

        workWiths = (List<GSSWorkWith>) HibernateUtil.getDBObjects(queryString);

        return workWiths;
    }

    /*
    Insert the data coming from mobile.
     */
    public String insertData(SyncObjectSS syncObject, String staffCode) {
        Session session = null;
        String status="";
        String message="";

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            Packet[] packets = syncObject.getOrderProduct();
            Order tempOrder;
            if(packets!=null){
                for (Packet packet : packets) {
                    tempOrder = new Order();
                    tempOrder = packet.getOrder();
                    tempOrder.setStaffCode(staffCode);
                    int id = (Integer) session.save(tempOrder);

                    for (ProductOrder productOrder : packet.getProductOrders()) {
                        productOrder.setOrderId(id);
                        session.save(productOrder);
                    }
                }
            }


            List<UnapprovedSDCustomer> unapprovedSDCustomers = syncObject.getUnapprovedSDCustomers();

            if(unapprovedSDCustomers!=null){
                for (UnapprovedSDCustomer customer : unapprovedSDCustomers) {
                    customer.setStaffCode(staffCode);
                    customer.setDoc(new Date());
                    session.save(customer);
                }
            }

            List<LeaveEntry> leaveEntries = syncObject.getLeaveEntries();

            if(leaveEntries!=null){
                for (LeaveEntry leaveEntry : leaveEntries) {
                    leaveEntry.setStaffCode(staffCode);
                    session.save(leaveEntry);
                }
            }

            tx.commit();
            status= Codes.ALL_OK;
            message="Successfully Synced";

        }catch (Exception e){
            status=Codes.SOMETHING_WENT_WRONG;
            message="Synced failed. Something went wrong";

        }finally {
            if(session.isOpen()){
                session.close();
            }

        }

        return status;
    }

}
