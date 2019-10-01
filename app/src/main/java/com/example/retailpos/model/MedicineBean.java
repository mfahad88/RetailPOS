package com.example.retailpos.model;

public class MedicineBean {
    private int product_id;
    private int batch_id;
    private String name;
    private int quantity;
    private String price_buying;
    private String price_selling;

    public MedicineBean(int product_id, int batch_id, String name, int quantity, String price_buying, String price_selling) {
        this.product_id = product_id;
        this.batch_id = batch_id;
        this.name = name;
        this.quantity = quantity;
        this.price_buying = price_buying;
        this.price_selling = price_selling;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice_buying() {
        return price_buying;
    }

    public void setPrice_buying(String price_buying) {
        this.price_buying = price_buying;
    }

    public String getPrice_selling() {
        return price_selling;
    }

    public void setPrice_selling(String price_selling) {
        this.price_selling = price_selling;
    }

    @Override
    public String toString() {
        return "MedicineBean{" +
                "product_id=" + product_id +
                ", batch_id=" + batch_id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price_buying='" + price_buying + '\'' +
                ", price_selling='" + price_selling + '\'' +
                '}';
    }
}
