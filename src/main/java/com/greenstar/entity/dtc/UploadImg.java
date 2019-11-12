package com.greenstar.entity.dtc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 25/10/2019
 */

@Entity
@Table(name="DTC_MEETING_IMG")
public class UploadImg {
    @Id
    private int id;
    @Column(name="IMG")
    private byte[] img;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

}
