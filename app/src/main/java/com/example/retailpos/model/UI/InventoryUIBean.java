package com.example.retailpos.model.UI;

public class InventoryUIBean {
    private int product_id;
    private String name;
    private String generic;
    private String quantity;
    private String cost;
    private String price;

    public InventoryUIBean(int product_id, String name, String generic, String quantity, String cost, String price) {
        this.product_id = product_id;
        this.name = name;
        this.generic = generic;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "InventoryUIBean{" +
                "product_id=" + product_id +
                ", name='" + name + '\'' +
                ", generic='" + generic + '\'' +
                ", quantity='" + quantity + '\'' +
                ", cost='" + cost + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
