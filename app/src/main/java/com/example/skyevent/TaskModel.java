package com.example.skyevent;

public class TaskModel {

    private String detail;

    private String status;

    private String date;

    public TaskModel(String detail,String status, String date)
    {
        this.detail=detail;
        this.status=status;
        this.date=date;
    }

    public void setDetail(String detail)
    {
        this.detail=detail;
    }

    public void setStatus(String status)
    {
        this.status=status;
    }

    public void setDate(String date)
    {
        this.date=date;
    }

    public String getDetail()
    {
        return detail;
    }

    public String getDate()
    {
        return date;
    }

    public String getStatus()
    {
        return status;
    }
}
