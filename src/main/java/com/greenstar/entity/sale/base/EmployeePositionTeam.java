package com.greenstar.entity.sale.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 29th April, 2021
 */
@Entity
@Table(name="BASE_EMP_POSITION_TEAM")
public class EmployeePositionTeam {
    @Id
    @Column(name="TEAM_ID")
    private int TEAM_ID;
    @Column(name="POSITION_ID")
    private String POSITION_ID;

    public int getTEAM_ID() {
        return TEAM_ID;
    }

    public void setTEAM_ID(int TEAM_ID) {
        this.TEAM_ID = TEAM_ID;
    }

    public String getPOSITION_ID() {
        return POSITION_ID;
    }

    public void setPOSITION_ID(String POSITION_ID) {
        this.POSITION_ID = POSITION_ID;
    }
}
