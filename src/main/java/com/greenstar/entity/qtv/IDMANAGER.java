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

    @Column(name="LAST_ID")
    private int lastID;
    @Id
    @Column(name="ID")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }
}
