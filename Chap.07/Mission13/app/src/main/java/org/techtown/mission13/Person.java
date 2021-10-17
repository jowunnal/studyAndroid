package org.techtown.mission13;

public class Person {
    String name;
    String mobile;
    String date;

    public Person(String name, String mobile, String date) {
        this.name = name;
        this.mobile = mobile;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
