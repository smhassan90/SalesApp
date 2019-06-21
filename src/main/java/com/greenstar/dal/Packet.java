package com.greenstar.dal;


import com.greenstar.entity.Order;
import com.greenstar.entity.ProductOrder;

import java.util.List;

public class Packet {
    Order order;
    List<ProductOrder> productOrders;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }
}
