package com.greenstar.entity.qtv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 10/07/2019
 */
@Entity
@Table(name="IDMANAGER")
public class IDMANAGER {

    @Id
    @Column(name="ID")
    private int id;

    @Column(name="LAST_ID")
    private long lastID;

    @Column(name="DTC_LAST_ID")
    private long DTClastID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLastID() {
        return lastID;
    }

    public void setLastID(long lastID) {
        this.lastID = lastID;
    }

    public long getDTClastID() {
        return DTClastID;
    }

    public void setDTClastID(long DTClastID) {
        this.DTClastID = DTClastID;
    }
}
