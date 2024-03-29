package com.greenstar.dal;

import com.greenstar.entity.LeaveEntry;
import com.greenstar.entity.UnapprovedSDCustomer;

import java.util.List;

public class SyncObjectSS {
    Packet[] orderProduct;
    List<UnapprovedSDCustomer>  unapprovedSDCustomers;
    List<LeaveEntry> leaveEntries;

    public Packet[] getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Packet[] orderProduct) {
        this.orderProduct = orderProduct;
    }

    public List<UnapprovedSDCustomer> getUnapprovedSDCustomers() {
        return unapprovedSDCustomers;
    }

    public void setUnapprovedSDCustomers(List<UnapprovedSDCustomer> unapprovedSDCustomers) {
        this.unapprovedSDCustomers = unapprovedSDCustomers;
    }

    public List<LeaveEntry> getLeaveEntries() {
        return leaveEntries;
    }

    public void setLeaveEntries(List<LeaveEntry> leaveEntries) {
        this.leaveEntries = leaveEntries;
    }
}
