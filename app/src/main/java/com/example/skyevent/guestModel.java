package com.example.skyevent;

public class guestModel {

    private String GuestName;
    private long GuestPhone;

    public guestModel(String Name,long Phone)
    {
        this.GuestName= Name;
        this.GuestPhone=Phone;
    }
    public String getGuestName(){
        return GuestName;
    }
    public long getGuestPhone(){
        return GuestPhone;
    }
    public void setGuestName(String Name){
        this.GuestName=Name;
    }
    public void setGuestPhone(long Phone){
        this.GuestPhone=Phone;
    }
}
