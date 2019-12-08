package com.example.skyevent;

public class guestModel {

    private String GuestName;
    private long GuestPhone;
    private String status;

    public guestModel(String Name, long Phone, String status)
    {
        this.GuestName= Name;
        this.GuestPhone=Phone;
        this.status=status;
    }
    public String getGuestName(){
        return GuestName;
    }
    public long getGuestPhone(){
        return GuestPhone;
    }
    public String getStatus()
    {
        return status;
    }
    public void setGuestName(String Name){
        this.GuestName=Name;
    }
    public void setGuestPhone(long Phone){
        this.GuestPhone=Phone;
    }
    public void setStatus(String status)
    {
        this.status=status;
    }
}
