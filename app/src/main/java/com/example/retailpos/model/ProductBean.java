package com.example.retailpos.model;

public class ProductBean {
    private int product_id;
    private String generic;
    private String name;
    private String price_buying;
    private String price_selling;
    private int minimum;
    private int maximum;

    public ProductBean(int product_id, String generic, String name, String price_buying, String price_selling, int minimum, int maximum) {
        this.product_id = product_id;
        this.generic = generic;
        this.name = name;
        this.price_buying = price_buying;
        this.price_selling = price_selling;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "product_id=" + product_id +
                ", generic='" + generic + '\'' +
                ", name='" + name + '\'' +
                ", price_buying=" + price_buying +
                ", price_selling=" + price_selling +
                ", minimum=" + minimum +
                ", maximum=" + maximum +
                '}';
    }
}
