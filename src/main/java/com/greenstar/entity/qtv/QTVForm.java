package com.greenstar.entity.qtv;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Syed Muhammad Hassan
 * 09-July-2019
 */
@Entity
@Table(name="QTV_FORM_ANDROID")
public class QTVForm {


    @Id
    private int id;


    @Column(name="choName")
    private String choName;

    @Column(name="choCode")
    private String choCode;

    @Column(name="region")
    private String region;

    @Column(name="mobileSystemDate")
    private String mobileSystemDate;

    @Column(name="visitDate")
    private Date visitDate;

    @Column(name="donor")
    private int donor;

    @Column(name="providerCode")
    private String providerCode;

    @Column(name="supervisorCode")
    private String supervisorCode;

    @Column(name="supervisorName")
    private String supervisorName;
    /*
    Matrix 1
     */
    @Column(name="deliveryDataCondoms")
    private String deliveryDataCondoms;

    @Column(name="deliveryDataPills")
    private String deliveryDataPills;

    @Column(name="deliveryDataIUCD")
    private String deliveryDataIUCD;

    @Column(name="deliveryDataImplants")
    private String deliveryDataImplants;

    @Column(name="deliveryDataInjectables")
    private String deliveryDataInjectables;

    @Column(name="deliveryDataVSC")
    private String deliveryDataVSC;

    @Column(name="deliveryDataTotal")
    private int deliveryDataTotal;

    @Column(name="deliveryDataTotalColumn")
    private String deliveryDataTotalColumn;

    @Column(name="deliveryDataTotalRow")
    private String deliveryDataTotalRow;
    /*
    Matrix 2
     */

    @Column(name="postPartumCondoms")
    private String postPartumCondoms;

    @Column(name="postPartumPills")
    private String postPartumPills;

    @Column(name="postPartumIUCD")
    private String postPartumIUCD;

    @Column(name="postPartumImplants")
    private String postPartumImplants;

    @Column(name="postPartumInjectables")
    private String postPartumInjectables;

    @Column(name="postPartumVSC")
    private String postPartumVSC;

    @Column(name="postPartumTotal")
    private int postPartumTotal;

    @Column(name="postPartumTotalColumn")
    private String postPartumTotalColumn;

    @Column(name="postPartumTotalRow")
    private String postPartumTotalRow;

    /*
    Matrix 3
     */
    @Column(name="postPACCondoms")
    private String postPACCondoms;

    @Column(name="postPACPills")
    private String postPACPills;

    @Column(name="postPACIUCD")
    private String postPACIUCD;

    @Column(name="postPACImplants")
    private String postPACImplants;

    @Column(name="postPACInjectables")
    private String postPACInjectables;

    @Column(name="postPACVSC")
    private String postPACVSC;

    @Column(name="postPACTotal")
    private int postPACTotal;

    @Column(name="postPACTotalColumn")
    private String postPACTotalColumn;

    @Column(name="postPACTotalRow")
    private String postPACTotalRow;

    /*
    Matrix 4
     */
    @Column(name="newUserCondoms")
    private String newUserCondoms;

    @Column(name="newUserPills")
    private String newUserPills;

    @Column(name="newUserIUCD")
    private String newUserIUCD;

    @Column(name="newUserImplants")
    private String newUserImplants;

    @Column(name="newUserInjectables")
    private String newUserInjectables;

    @Column(name="newUserVSC")
    private String newUserVSC;

    @Column(name="newUserTotal")
    private int newUserTotal;

    @Column(name="newUserTotalColumn")
    private String newUserTotalColumn;

    @Column(name="newUserTotalRow")
    private String newUserTotalRow;

    @Column(name="totalCurrentUsers")
    private int totalCurrentUsers;

    @Column(name="totalMethodSwitcher")
    private int totalMethodSwitcher;

    @Column(name="totalMisoCases")
    private int totalMisoCases;

    @Column(name="totalPACCases")
    private int totalPACCases;

    @Column(name="totalEverUsersLess")
    private int totalEverUsersLess;

    @Column(name="totalEverUsersGreater")
    private int totalEverUsersGreater;

    @Column(name="totalNeverUsers")
    private int totalNeverUsers;

    @Column(name="totalNewUsers")
    private int totalNewUsers;

    @Column(name="totalDeliveryConducted")
    private int totalDeliveryConducted;

    @Column(name="totalFPClients")
    private int totalFPClients;

    @Column(name="totalCondomClients")
    private int totalCondomClients;

    @Column(name="totalPillsClient")
    private int totalPillsClient;

    @Column(name="totalIUDClients")
    private int totalIUDClients;

    @Column(name="totalImplantClients")
    private int totalImplantClients;

    @Column(name="totalInjectableClients")
    private int totalInjectableClients;

    @Column(name="totalVSCClients")
    private int totalVSCClients;

    @Column(name="totalPPIUDClients")
    private int totalPPIUDClients;

    @Column(name="oneMonthInjectables")
    private int oneMonthInjectables;

    @Column(name="twoMonthsInjectables")
    private int twoMonthsInjectables;

    @Column(name="threeMonthsInjectables")
    private int threeMonthsInjectables;

    @Column(name="totalInjectableClientsMonth")
    private int totalInjectableClientsMonth;

    @Column(name="totalPACLTMAdoptedOneWeek")
    private int totalPACLTMAdoptedOneWeek;

    @Column(name="totalPACLTMAdoptedHours")
    private int totalPACLTMAdoptedHours;

    @Column(name="totalPACFPAdoptedOneWeek")
    private int totalPACFPAdoptedOneWeek;

    @Column(name="totalPACFPAdoptedHours")
    private int totalPACFPAdoptedHours;


    //Second Section
    @Column(name="IUDRemovedSideEffects")
    private int IUDRemovedSideEffects;

    @Column(name="IUDRemovedDesire")
    private int IUDRemovedDesire;

    @Column(name="IUDRemovedAdverse")
    private int IUDRemovedAdverse;

    @Column(name="IUDRemovedOther")
    private int IUDRemovedOther;

    @Column(name="totalIUDRemovedCases")
    private int totalIUDRemovedCases;


    //Third Section

    @Column(name="placentalInsertion")
    private int placentalInsertion;

    @Column(name="immediatePostPartum")
    private int immediatePostPartum;

    @Column(name="postPartumInsertion48Hours")
    private int postPartumInsertion48Hours;

    @Column(name="extendedPostPartumInsertion")
    private int extendedPostPartumInsertion;

    @Column(name="immediateExpulsion")
    private int immediateExpulsion;

    @Column(name="delayedExpulsion")
    private int delayedExpulsion;


    //Fourth Section YES/NO
    @Column(name="IECMaterial")
    private int IECMaterial;

    @Column(name="lastQATAvailable")
    private int lastQATAvailable;

    @Column(name="clientRecordBook")
    private int clientRecordBook;

    @Column(name="clientDetails")
    private int clientDetails;

    @Column(name="trainingCertificates")
    private int trainingCertificates;

    @Column(name="adverseEventReferrals")
    private int adverseEventReferrals;

    @Column(name="counselingFlipChart")
    private int counselingFlipChart;

    //Fifth Section YES/NO

    @Column(name="autoClave")
    private int autoClave;

    @Column(name="chlorineUsed")
    private int chlorineUsed;

    @Column(name="instrumentStored")
    private int instrumentStored;

    @Column(name="boilingInstrument")
    private int boilingInstrument;

    @Column(name="glovesInUse")
    private int glovesInUse;

    @Column(name="safetyBoxInUse")
    private int safetyBoxInUse;

    @Column(name="dustbinDisposable")
    private int dustbinDisposable;


    //Section 6.1

    @Column(name="availabilityGreenstar")
    private String availabilityGreenstar;

    @Column(name="availabilityGovt")
    private String availabilityGovt;

    @Column(name="availabilityMSS")
    private String availabilityMSS;

    @Column(name="availabilityDKT")
    private String availabilityDKT;

    @Column(name="availabilityOther")
    private String availabilityOther;

    //Section 6.2
    @Column(name="stockGreenstar")
    private String stockGreenstar;

    @Column(name="stockGovt")
    private String stockGovt;

    @Column(name="stockMSS")
    private String stockMSS;

    @Column(name="stockDKT")
    private String stockDKT;

    @Column(name="stockOther")
    private String stockOther;

    @Column(name="comments")
    private String comments;

    @Column(name="APPROVAL_STATUS")
    private int approvalStatus;

    @Column(name="LAT_LON")
    private String latLong;

    @Column(name="providerName")
    private String providerName;

    @Column(name="reportingMonth")
    private String reportingMonth;

    @Column(name="providerDonor")
    private String providerDonor;

    @Column(name="reason")
    private String reason;

    @Column(name="TOTALCOUNSELINGCLIENTS")
    private String totalCounselingClients;

    @Column(name="DIARRHEA2TO5")
    private String diarrhea2To5;

    @Column(name="DIARRHEA6TO10")
    private String diarrhea6To10;

    @Column(name="DIARRHEA11TO14")
    private String diarrhea11To14;

    @Column(name="DIARRHEATOTAL")
    private String diarrheaTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChoName() {
        return choName;
    }

    public void setChoName(String choName) {
        this.choName = choName;
    }

    public String getChoCode() {
        return choCode;
    }

    public void setChoCode(String choCode) {
        this.choCode = choCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMobileSystemDate() {
        return mobileSystemDate;
    }

    public void setMobileSystemDate(String mobileSystemDate) {
        this.mobileSystemDate = mobileSystemDate;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public int getDonor() {
        return donor;
    }

    public void setDonor(int donor) {
        this.donor = donor;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getSupervisorCode() {
        return supervisorCode;
    }

    public void setSupervisorCode(String supervisorCode) {
        this.supervisorCode = supervisorCode;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getDeliveryDataCondoms() {
        return deliveryDataCondoms;
    }

    public void setDeliveryDataCondoms(String deliveryDataCondoms) {
        this.deliveryDataCondoms = deliveryDataCondoms;
    }

    public String getDeliveryDataPills() {
        return deliveryDataPills;
    }

    public void setDeliveryDataPills(String deliveryDataPills) {
        this.deliveryDataPills = deliveryDataPills;
    }

    public String getDeliveryDataIUCD() {
        return deliveryDataIUCD;
    }

    public void setDeliveryDataIUCD(String deliveryDataIUCD) {
        this.deliveryDataIUCD = deliveryDataIUCD;
    }

    public String getDeliveryDataImplants() {
        return deliveryDataImplants;
    }

    public void setDeliveryDataImplants(String deliveryDataImplants) {
        this.deliveryDataImplants = deliveryDataImplants;
    }

    public String getDeliveryDataInjectables() {
        return deliveryDataInjectables;
    }

    public void setDeliveryDataInjectables(String deliveryDataInjectables) {
        this.deliveryDataInjectables = deliveryDataInjectables;
    }

    public String getDeliveryDataVSC() {
        return deliveryDataVSC;
    }

    public void setDeliveryDataVSC(String deliveryDataVSC) {
        this.deliveryDataVSC = deliveryDataVSC;
    }

    public int getDeliveryDataTotal() {
        return deliveryDataTotal;
    }

    public void setDeliveryDataTotal(int deliveryDataTotal) {
        this.deliveryDataTotal = deliveryDataTotal;
    }

    public String getDeliveryDataTotalColumn() {
        return deliveryDataTotalColumn;
    }

    public void setDeliveryDataTotalColumn(String deliveryDataTotalColumn) {
        this.deliveryDataTotalColumn = deliveryDataTotalColumn;
    }

    public String getDeliveryDataTotalRow() {
        return deliveryDataTotalRow;
    }

    public void setDeliveryDataTotalRow(String deliveryDataTotalRow) {
        this.deliveryDataTotalRow = deliveryDataTotalRow;
    }

    public String getPostPartumCondoms() {
        return postPartumCondoms;
    }

    public void setPostPartumCondoms(String postPartumCondoms) {
        this.postPartumCondoms = postPartumCondoms;
    }

    public String getPostPartumPills() {
        return postPartumPills;
    }

    public void setPostPartumPills(String postPartumPills) {
        this.postPartumPills = postPartumPills;
    }

    public String getPostPartumIUCD() {
        return postPartumIUCD;
    }

    public void setPostPartumIUCD(String postPartumIUCD) {
        this.postPartumIUCD = postPartumIUCD;
    }

    public String getPostPartumImplants() {
        return postPartumImplants;
    }

    public void setPostPartumImplants(String postPartumImplants) {
        this.postPartumImplants = postPartumImplants;
    }

    public String getPostPartumInjectables() {
        return postPartumInjectables;
    }

    public void setPostPartumInjectables(String postPartumInjectables) {
        this.postPartumInjectables = postPartumInjectables;
    }

    public String getPostPartumVSC() {
        return postPartumVSC;
    }

    public void setPostPartumVSC(String postPartumVSC) {
        this.postPartumVSC = postPartumVSC;
    }

    public int getPostPartumTotal() {
        return postPartumTotal;
    }

    public void setPostPartumTotal(int postPartumTotal) {
        this.postPartumTotal = postPartumTotal;
    }

    public String getPostPartumTotalColumn() {
        return postPartumTotalColumn;
    }

    public void setPostPartumTotalColumn(String postPartumTotalColumn) {
        this.postPartumTotalColumn = postPartumTotalColumn;
    }

    public String getPostPartumTotalRow() {
        return postPartumTotalRow;
    }

    public void setPostPartumTotalRow(String postPartumTotalRow) {
        this.postPartumTotalRow = postPartumTotalRow;
    }

    public String getPostPACCondoms() {
        return postPACCondoms;
    }

    public void setPostPACCondoms(String postPACCondoms) {
        this.postPACCondoms = postPACCondoms;
    }

    public String getPostPACPills() {
        return postPACPills;
    }

    public void setPostPACPills(String postPACPills) {
        this.postPACPills = postPACPills;
    }

    public String getPostPACIUCD() {
        return postPACIUCD;
    }

    public void setPostPACIUCD(String postPACIUCD) {
        this.postPACIUCD = postPACIUCD;
    }

    public String getPostPACImplants() {
        return postPACImplants;
    }

    public void setPostPACImplants(String postPACImplants) {
        this.postPACImplants = postPACImplants;
    }

    public String getPostPACInjectables() {
        return postPACInjectables;
    }

    public void setPostPACInjectables(String postPACInjectables) {
        this.postPACInjectables = postPACInjectables;
    }

    public String getPostPACVSC() {
        return postPACVSC;
    }

    public void setPostPACVSC(String postPACVSC) {
        this.postPACVSC = postPACVSC;
    }

    public int getPostPACTotal() {
        return postPACTotal;
    }

    public void setPostPACTotal(int postPACTotal) {
        this.postPACTotal = postPACTotal;
    }

    public String getPostPACTotalColumn() {
        return postPACTotalColumn;
    }

    public void setPostPACTotalColumn(String postPACTotalColumn) {
        this.postPACTotalColumn = postPACTotalColumn;
    }

    public String getPostPACTotalRow() {
        return postPACTotalRow;
    }

    public void setPostPACTotalRow(String postPACTotalRow) {
        this.postPACTotalRow = postPACTotalRow;
    }

    public String getNewUserCondoms() {
        return newUserCondoms;
    }

    public void setNewUserCondoms(String newUserCondoms) {
        this.newUserCondoms = newUserCondoms;
    }

    public String getNewUserPills() {
        return newUserPills;
    }

    public void setNewUserPills(String newUserPills) {
        this.newUserPills = newUserPills;
    }

    public String getNewUserIUCD() {
        return newUserIUCD;
    }

    public void setNewUserIUCD(String newUserIUCD) {
        this.newUserIUCD = newUserIUCD;
    }

    public String getNewUserImplants() {
        return newUserImplants;
    }

    public void setNewUserImplants(String newUserImplants) {
        this.newUserImplants = newUserImplants;
    }

    public String getNewUserInjectables() {
        return newUserInjectables;
    }

    public void setNewUserInjectables(String newUserInjectables) {
        this.newUserInjectables = newUserInjectables;
    }

    public String getNewUserVSC() {
        return newUserVSC;
    }

    public void setNewUserVSC(String newUserVSC) {
        this.newUserVSC = newUserVSC;
    }

    public int getNewUserTotal() {
        return newUserTotal;
    }

    public void setNewUserTotal(int newUserTotal) {
        this.newUserTotal = newUserTotal;
    }

    public String getNewUserTotalColumn() {
        return newUserTotalColumn;
    }

    public void setNewUserTotalColumn(String newUserTotalColumn) {
        this.newUserTotalColumn = newUserTotalColumn;
    }

    public String getNewUserTotalRow() {
        return newUserTotalRow;
    }

    public void setNewUserTotalRow(String newUserTotalRow) {
        this.newUserTotalRow = newUserTotalRow;
    }

    public int getTotalCurrentUsers() {
        return totalCurrentUsers;
    }

    public void setTotalCurrentUsers(int totalCurrentUsers) {
        this.totalCurrentUsers = totalCurrentUsers;
    }

    public int getTotalMethodSwitcher() {
        return totalMethodSwitcher;
    }

    public void setTotalMethodSwitcher(int totalMethodSwitcher) {
        this.totalMethodSwitcher = totalMethodSwitcher;
    }

    public int getTotalMisoCases() {
        return totalMisoCases;
    }

    public void setTotalMisoCases(int totalMisoCases) {
        this.totalMisoCases = totalMisoCases;
    }

    public int getTotalPACCases() {
        return totalPACCases;
    }

    public void setTotalPACCases(int totalPACCases) {
        this.totalPACCases = totalPACCases;
    }

    public int getTotalEverUsersLess() {
        return totalEverUsersLess;
    }

    public void setTotalEverUsersLess(int totalEverUsersLess) {
        this.totalEverUsersLess = totalEverUsersLess;
    }

    public int getTotalEverUsersGreater() {
        return totalEverUsersGreater;
    }

    public void setTotalEverUsersGreater(int totalEverUsersGreater) {
        this.totalEverUsersGreater = totalEverUsersGreater;
    }

    public int getTotalNeverUsers() {
        return totalNeverUsers;
    }

    public void setTotalNeverUsers(int totalNeverUsers) {
        this.totalNeverUsers = totalNeverUsers;
    }

    public int getTotalNewUsers() {
        return totalNewUsers;
    }

    public void setTotalNewUsers(int totalNewUsers) {
        this.totalNewUsers = totalNewUsers;
    }

    public int getTotalDeliveryConducted() {
        return totalDeliveryConducted;
    }

    public void setTotalDeliveryConducted(int totalDeliveryConducted) {
        this.totalDeliveryConducted = totalDeliveryConducted;
    }

    public int getTotalFPClients() {
        return totalFPClients;
    }

    public void setTotalFPClients(int totalFPClients) {
        this.totalFPClients = totalFPClients;
    }

    public int getTotalCondomClients() {
        return totalCondomClients;
    }

    public void setTotalCondomClients(int totalCondomClients) {
        this.totalCondomClients = totalCondomClients;
    }

    public int getTotalPillsClient() {
        return totalPillsClient;
    }

    public void setTotalPillsClient(int totalPillsClient) {
        this.totalPillsClient = totalPillsClient;
    }

    public int getTotalIUDClients() {
        return totalIUDClients;
    }

    public void setTotalIUDClients(int totalIUDClients) {
        this.totalIUDClients = totalIUDClients;
    }

    public int getTotalImplantClients() {
        return totalImplantClients;
    }

    public void setTotalImplantClients(int totalImplantClients) {
        this.totalImplantClients = totalImplantClients;
    }

    public int getTotalInjectableClients() {
        return totalInjectableClients;
    }

    public void setTotalInjectableClients(int totalInjectableClients) {
        this.totalInjectableClients = totalInjectableClients;
    }

    public int getTotalVSCClients() {
        return totalVSCClients;
    }

    public void setTotalVSCClients(int totalVSCClients) {
        this.totalVSCClients = totalVSCClients;
    }

    public int getTotalPPIUDClients() {
        return totalPPIUDClients;
    }

    public void setTotalPPIUDClients(int totalPPIUDClients) {
        this.totalPPIUDClients = totalPPIUDClients;
    }

    public int getOneMonthInjectables() {
        return oneMonthInjectables;
    }

    public void setOneMonthInjectables(int oneMonthInjectables) {
        this.oneMonthInjectables = oneMonthInjectables;
    }

    public int getTwoMonthsInjectables() {
        return twoMonthsInjectables;
    }

    public void setTwoMonthsInjectables(int twoMonthsInjectables) {
        this.twoMonthsInjectables = twoMonthsInjectables;
    }

    public int getThreeMonthsInjectables() {
        return threeMonthsInjectables;
    }

    public void setThreeMonthsInjectables(int threeMonthsInjectables) {
        this.threeMonthsInjectables = threeMonthsInjectables;
    }

    public int getTotalInjectableClientsMonth() {
        return totalInjectableClientsMonth;
    }

    public void setTotalInjectableClientsMonth(int totalInjectableClientsMonth) {
        this.totalInjectableClientsMonth = totalInjectableClientsMonth;
    }

    public int getTotalPACLTMAdoptedOneWeek() {
        return totalPACLTMAdoptedOneWeek;
    }

    public void setTotalPACLTMAdoptedOneWeek(int totalPACLTMAdoptedOneWeek) {
        this.totalPACLTMAdoptedOneWeek = totalPACLTMAdoptedOneWeek;
    }

    public int getTotalPACLTMAdoptedHours() {
        return totalPACLTMAdoptedHours;
    }

    public void setTotalPACLTMAdoptedHours(int totalPACLTMAdoptedHours) {
        this.totalPACLTMAdoptedHours = totalPACLTMAdoptedHours;
    }

    public int getTotalPACFPAdoptedOneWeek() {
        return totalPACFPAdoptedOneWeek;
    }

    public void setTotalPACFPAdoptedOneWeek(int totalPACFPAdoptedOneWeek) {
        this.totalPACFPAdoptedOneWeek = totalPACFPAdoptedOneWeek;
    }

    public int getTotalPACFPAdoptedHours() {
        return totalPACFPAdoptedHours;
    }

    public void setTotalPACFPAdoptedHours(int totalPACFPAdoptedHours) {
        this.totalPACFPAdoptedHours = totalPACFPAdoptedHours;
    }

    public int getIUDRemovedSideEffects() {
        return IUDRemovedSideEffects;
    }

    public void setIUDRemovedSideEffects(int IUDRemovedSideEffects) {
        this.IUDRemovedSideEffects = IUDRemovedSideEffects;
    }

    public int getIUDRemovedDesire() {
        return IUDRemovedDesire;
    }

    public void setIUDRemovedDesire(int IUDRemovedDesire) {
        this.IUDRemovedDesire = IUDRemovedDesire;
    }

    public int getIUDRemovedAdverse() {
        return IUDRemovedAdverse;
    }

    public void setIUDRemovedAdverse(int IUDRemovedAdverse) {
        this.IUDRemovedAdverse = IUDRemovedAdverse;
    }

    public int getIUDRemovedOther() {
        return IUDRemovedOther;
    }

    public void setIUDRemovedOther(int IUDRemovedOther) {
        this.IUDRemovedOther = IUDRemovedOther;
    }

    public int getTotalIUDRemovedCases() {
        return totalIUDRemovedCases;
    }

    public void setTotalIUDRemovedCases(int totalIUDRemovedCases) {
        this.totalIUDRemovedCases = totalIUDRemovedCases;
    }

    public int getPlacentalInsertion() {
        return placentalInsertion;
    }

    public void setPlacentalInsertion(int placentalInsertion) {
        this.placentalInsertion = placentalInsertion;
    }

    public int getImmediatePostPartum() {
        return immediatePostPartum;
    }

    public void setImmediatePostPartum(int immediatePostPartum) {
        this.immediatePostPartum = immediatePostPartum;
    }

    public int getPostPartumInsertion48Hours() {
        return postPartumInsertion48Hours;
    }

    public void setPostPartumInsertion48Hours(int postPartumInsertion48Hours) {
        this.postPartumInsertion48Hours = postPartumInsertion48Hours;
    }

    public int getExtendedPostPartumInsertion() {
        return extendedPostPartumInsertion;
    }

    public void setExtendedPostPartumInsertion(int extendedPostPartumInsertion) {
        this.extendedPostPartumInsertion = extendedPostPartumInsertion;
    }

    public int getImmediateExpulsion() {
        return immediateExpulsion;
    }

    public void setImmediateExpulsion(int immediateExpulsion) {
        this.immediateExpulsion = immediateExpulsion;
    }

    public int getDelayedExpulsion() {
        return delayedExpulsion;
    }

    public void setDelayedExpulsion(int delayedExpulsion) {
        this.delayedExpulsion = delayedExpulsion;
    }

    public int getIECMaterial() {
        return IECMaterial;
    }

    public void setIECMaterial(int IECMaterial) {
        this.IECMaterial = IECMaterial;
    }

    public int getLastQATAvailable() {
        return lastQATAvailable;
    }

    public void setLastQATAvailable(int lastQATAvailable) {
        this.lastQATAvailable = lastQATAvailable;
    }

    public int getClientRecordBook() {
        return clientRecordBook;
    }

    public void setClientRecordBook(int clientRecordBook) {
        this.clientRecordBook = clientRecordBook;
    }

    public int getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(int clientDetails) {
        this.clientDetails = clientDetails;
    }

    public int getTrainingCertificates() {
        return trainingCertificates;
    }

    public void setTrainingCertificates(int trainingCertificates) {
        this.trainingCertificates = trainingCertificates;
    }

    public int getAdverseEventReferrals() {
        return adverseEventReferrals;
    }

    public void setAdverseEventReferrals(int adverseEventReferrals) {
        this.adverseEventReferrals = adverseEventReferrals;
    }

    public int getCounselingFlipChart() {
        return counselingFlipChart;
    }

    public void setCounselingFlipChart(int counselingFlipChart) {
        this.counselingFlipChart = counselingFlipChart;
    }

    public int getAutoClave() {
        return autoClave;
    }

    public void setAutoClave(int autoClave) {
        this.autoClave = autoClave;
    }

    public int getChlorineUsed() {
        return chlorineUsed;
    }

    public void setChlorineUsed(int chlorineUsed) {
        this.chlorineUsed = chlorineUsed;
    }

    public int getInstrumentStored() {
        return instrumentStored;
    }

    public void setInstrumentStored(int instrumentStored) {
        this.instrumentStored = instrumentStored;
    }

    public int getBoilingInstrument() {
        return boilingInstrument;
    }

    public void setBoilingInstrument(int boilingInstrument) {
        this.boilingInstrument = boilingInstrument;
    }

    public int getGlovesInUse() {
        return glovesInUse;
    }

    public void setGlovesInUse(int glovesInUse) {
        this.glovesInUse = glovesInUse;
    }

    public int getSafetyBoxInUse() {
        return safetyBoxInUse;
    }

    public void setSafetyBoxInUse(int safetyBoxInUse) {
        this.safetyBoxInUse = safetyBoxInUse;
    }

    public int getDustbinDisposable() {
        return dustbinDisposable;
    }

    public void setDustbinDisposable(int dustbinDisposable) {
        this.dustbinDisposable = dustbinDisposable;
    }

    public String getAvailabilityGreenstar() {
        return availabilityGreenstar;
    }

    public void setAvailabilityGreenstar(String availabilityGreenstar) {
        this.availabilityGreenstar = availabilityGreenstar;
    }

    public String getAvailabilityGovt() {
        return availabilityGovt;
    }

    public void setAvailabilityGovt(String availabilityGovt) {
        this.availabilityGovt = availabilityGovt;
    }

    public String getAvailabilityMSS() {
        return availabilityMSS;
    }

    public void setAvailabilityMSS(String availabilityMSS) {
        this.availabilityMSS = availabilityMSS;
    }

    public String getAvailabilityDKT() {
        return availabilityDKT;
    }

    public void setAvailabilityDKT(String availabilityDKT) {
        this.availabilityDKT = availabilityDKT;
    }

    public String getAvailabilityOther() {
        return availabilityOther;
    }

    public void setAvailabilityOther(String availabilityOther) {
        this.availabilityOther = availabilityOther;
    }

    public String getStockGreenstar() {
        return stockGreenstar;
    }

    public void setStockGreenstar(String stockGreenstar) {
        this.stockGreenstar = stockGreenstar;
    }

    public String getStockGovt() {
        return stockGovt;
    }

    public void setStockGovt(String stockGovt) {
        this.stockGovt = stockGovt;
    }

    public String getStockMSS() {
        return stockMSS;
    }

    public void setStockMSS(String stockMSS) {
        this.stockMSS = stockMSS;
    }

    public String getStockDKT() {
        return stockDKT;
    }

    public void setStockDKT(String stockDKT) {
        this.stockDKT = stockDKT;
    }

    public String getStockOther() {
        return stockOther;
    }

    public void setStockOther(String stockOther) {
        this.stockOther = stockOther;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getReportingMonth() {
        return reportingMonth;
    }

    public void setReportingMonth(String reportingMonth) {
        this.reportingMonth = reportingMonth;
    }

    public String getProviderDonor() {
        return providerDonor;
    }

    public void setProviderDonor(String providerDonor) {
        this.providerDonor = providerDonor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTotalCounselingClients() {
        return totalCounselingClients;
    }

    public void setTotalCounselingClients(String totalCounselingClients) {
        this.totalCounselingClients = totalCounselingClients;
    }

    public String getDiarrhea2To5() {
        return diarrhea2To5;
    }

    public void setDiarrhea2To5(String diarrhea2To5) {
        this.diarrhea2To5 = diarrhea2To5;
    }

    public String getDiarrhea6To10() {
        return diarrhea6To10;
    }

    public void setDiarrhea6To10(String diarrhea6To10) {
        this.diarrhea6To10 = diarrhea6To10;
    }

    public String getDiarrhea11To14() {
        return diarrhea11To14;
    }

    public void setDiarrhea11To14(String diarrhea11To14) {
        this.diarrhea11To14 = diarrhea11To14;
    }

    public String getDiarrheaTotal() {
        return diarrheaTotal;
    }

    public void setDiarrheaTotal(String diarrheaTotal) {
        this.diarrheaTotal = diarrheaTotal;
    }
}

