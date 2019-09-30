package com.example.retailpos.model;

public class BatchBean {
    private int batch_id;
    private int product_id;
    private String batch_no;
    private String expiry_date;
    private int quantity;

    public BatchBean(int batch_id, int product_id, String batch_no, String expiry_date, int quantity) {
        this.batch_id = batch_id;
        this.product_id = product_id;
        this.batch_no = batch_no;
        this.expiry_date = expiry_date;
        this.quantity = quantity;
    }

    public int getBatch_id() {
        return batch_id;
    }

    public void setBatch_id(int batch_id) {
        this.batch_id = batch_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getBatch_no() {
        return batch_no;
    }

    public void setBatch_no(String batch_no) {
        this.batch_no = batch_no;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BatchBean{" +
                "batch_id=" + batch_id +
                ", product_id=" + product_id +
                ", batch_no='" + batch_no + '\'' +
                ", expiry_date='" + expiry_date + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
