package com.greenstar.entity.eagle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 13/10/2021
 */

@Entity
@Table(name="eagle_neighbour_attendees_form")
public class NeighbourAttendeesForm {
    @Id
    @Column(name="ID")
    private long id;
    @Column(name="clientId")
    private long clientId;
    @Column(name="neighbourhoodId")
    private long neighbourhoodId;
    @Column(name="isFPBrochureGiven")
    private int isFPBrochureGiven;
    @Column(name="isDiarrheaBrochureGiven")
    private int isDiarrheaBrochureGiven;
    @Column(name="otherIECMaterial")
    private String otherIECMaterial;
    @Column(name="remarks")
    private String remarks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getNeighbourhoodId() {
        return neighbourhoodId;
    }

    public void setNeighbourhoodId(long neighbourhoodId) {
        this.neighbourhoodId = neighbourhoodId;
    }

    public int getIsFPBrochureGiven() {
        return isFPBrochureGiven;
    }

    public void setIsFPBrochureGiven(int isFPBrochureGiven) {
        this.isFPBrochureGiven = isFPBrochureGiven;
    }

    public int getIsDiarrheaBrochureGiven() {
        return isDiarrheaBrochureGiven;
    }

    public void setIsDiarrheaBrochureGiven(int isDiarrheaBrochureGiven) {
        this.isDiarrheaBrochureGiven = isDiarrheaBrochureGiven;
    }

    public String getOtherIECMaterial() {
        return otherIECMaterial;
    }

    public void setOtherIECMaterial(String otherIECMaterial) {
        this.otherIECMaterial = otherIECMaterial;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
