package com.greenstar.entity;

import javax.persistence.*;

@Entity
@Table(name="GSS_PRODUCT_ORDER")
public class ProductOrder {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="GSS_PRODUCT_ORDER_SEQ")
    @SequenceGenerator(name="GSS_PRODUCT_ORDER_SEQ", sequenceName="GSS_PRODUCT_ORDER_SEQ", allocationSize=1)
    private int id;
    @Column(name="NAME")
    private String name;
    @Column(name="ORDER_ID")
    private int orderId;
    @Column(name="PRODUCT_ID")
    private String productId;
    @Column(name="QUANTITY")
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
