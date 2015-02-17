package com.example.app.model;

public class Product {

    private int id;
    private String name;
    private String description;
    private double costPrice;
    private double salePrice;
    private int quantity;

    public Product(int id, String nm, String d, double cp, double sp, int qty) {
        this.id = id;
        this.name = nm;
        this.description = d;
        this.costPrice = cp;
        this.salePrice = sp;
        this.quantity = qty;
    }

    public Product(String nm, String d, double cp, double sp, int qty) {
        this(-1, nm , d, cp, sp, qty);
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
