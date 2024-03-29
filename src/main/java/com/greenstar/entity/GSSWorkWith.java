package com.greenstar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GSS_WORK_WITH")
public class GSSWorkWith {
    @Id
    @Column(name="ID")
    private int id;

    @Column(name="WORK_WITH")
    private String workWith;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkWith() {
        return workWith;
    }

    public void setWorkWith(String workWith) {
        this.workWith = workWith;
    }
}
