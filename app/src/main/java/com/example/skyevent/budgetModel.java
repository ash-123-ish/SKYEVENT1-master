package com.example.skyevent;

public class budgetModel {

    private String name;

    private String status;

    private String date;


    private String cost;

    public budgetModel(String name,String status, String date, String cost)
    {
        this.name=name;
        this.status=status;
        this.date=date;
        this.cost=cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getCost() {
        return cost;
    }
}
