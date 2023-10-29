package com.greenstar.entity.eagle;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Muhammad Hassan
 * 30/01/2023
 */

@Entity
@Table(name="EAGLE_PRODUCTSERVICE")
public class ProductService {
    @Id
    @Column(name="ID")
    private int id;
    @Column(name="text")
    private String text;
    @Column(name="formId")
    private long formId;
    @Column(name="type")
    private int type;
    @Column(name="price")
    private int price;
    @Column(name="status")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getFormId() {
        return formId;
    }

    public void setFormId(long formId) {
        this.formId = formId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
