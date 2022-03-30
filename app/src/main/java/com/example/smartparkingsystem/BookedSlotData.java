package com.example.smartparkingsystem;

public class BookedSlotData {


    public String TimeStamp,car_number,document_id,slot_no,userID,user_email_id,user_name;
    int slot_time;

    public BookedSlotData(String TimeStamp, String car_number, String document_id, String slot_no, int slot_time, String userID, String user_email_id, String user_name) {
        this.TimeStamp = TimeStamp;
        this.car_number = car_number;
        this.document_id = document_id;
        this.slot_no = slot_no;
        this.slot_time = slot_time;
        this.userID = userID;
        this.user_email_id = user_email_id;
        this.user_name = user_name;
    }
    public BookedSlotData(){

    }
}