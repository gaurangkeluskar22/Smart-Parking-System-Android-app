package com.example.smartparkingsystem;

import java.sql.Time;
import java.util.UUID;

public class Slot {
    public String document_id;
    public String userID;
    public String user_name,user_email_id,car_number,slot_no;
    public int slot_time;
    public String TimeStamp;

    public Slot(){

    }

    public Slot(String document_id,String userID,String user_name,String user_email_id,String car_number ,String slot_time,String slot_no,String TimeStamp){
        this.document_id=document_id;
        this.userID = userID;
        this.user_email_id = user_email_id;
        this.user_name = user_name;
        this.car_number = car_number;
        this.slot_no = slot_no;
        this.slot_time = Integer.parseInt(slot_time);
        this.TimeStamp = TimeStamp;
    }
}