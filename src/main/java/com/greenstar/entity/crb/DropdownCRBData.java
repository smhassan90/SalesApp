package com.greenstar.entity.crb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 19th December, 2019
 */
@Entity
@Table(name="CRB_DD_DATA")
public class DropdownCRBData {

    @Id
    @Column(name="ID")
    private long id;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="DETAIL_ENG")
    private String detailEnglish;

    @Column(name="DETAIL_URDU")
    private String detailUrdu;

    @Column(name="STATUS")
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetailEnglish() {
        return detailEnglish;
    }

    public void setDetailEnglish(String detailEnglish) {
        this.detailEnglish = detailEnglish;
    }

    public String getDetailUrdu() {
        return detailUrdu;
    }

    public void setDetailUrdu(String detailUrdu) {
        this.detailUrdu = detailUrdu;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
