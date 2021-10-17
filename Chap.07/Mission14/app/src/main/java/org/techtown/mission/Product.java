package org.techtown.mission;

public class Product {
    String name;
    String price;
    String info;
    int resId;

    public Product(String name, String price, String info, int resId) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
