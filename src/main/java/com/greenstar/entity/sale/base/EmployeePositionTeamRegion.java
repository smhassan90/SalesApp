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
@Table(name="BASE_EMP_POSITION_TEAMREGION")
public class EmployeePositionTeamRegion {
    @Id
    @Column(name="TEAMREGION_ID")
    private int TEAMREGION_ID;
    @Column(name="POSITION_ID")
    private String POSITION_ID;

    public int getTEAMREGION_ID() {
        return TEAMREGION_ID;
    }

    public void setTEAMREGION_ID(int TEAMREGION_ID) {
        this.TEAMREGION_ID = TEAMREGION_ID;
    }

    public String getPOSITION_ID() {
        return POSITION_ID;
    }

    public void setPOSITION_ID(String POSITION_ID) {
        this.POSITION_ID = POSITION_ID;
    }
}
