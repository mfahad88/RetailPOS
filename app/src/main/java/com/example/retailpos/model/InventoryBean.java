package com.example.retailpos.model;

public class InventoryBean {

    private String name;
    private String generic;
    private String potency;
    private String batch_no;
    private String exp_date;
    private int quantity;
    private String cost;
    private String price;

    public InventoryBean(String name, String generic, String potency, String batch_no, String exp_date, int quantity, String cost, String price) {
        this.name = name;
        this.generic = generic;
        this.potency = potency;
        this.batch_no = batch_no;
        this.exp_date = exp_date;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
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

    public String getPotency() {
        return potency;
    }

    public void setPotency(String potency) {
        this.potency = potency;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
        return "InventoryBean{" +
                "name='" + name + '\'' +
                ", generic='" + generic + '\'' +
                ", potency='" + potency + '\'' +
                ", batch_no='" + batch_no + '\'' +
                ", exp_date='" + exp_date + '\'' +
                ", quantity=" + quantity +
                ", cost='" + cost + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
