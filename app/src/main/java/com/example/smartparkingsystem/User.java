package com.example.smartparkingsystem;

public class User {
    public String full_name, email_id,phone_number,car_number,user_name,user_email_id,slot_no,document_id,userID;
    String TimeStamp;
    int slot_time;
    public User(){

    }

    public User(String full_name,String email_id,String phone_number,String car_number){
        this.full_name = full_name;
        this.email_id = email_id;
        this.phone_number = phone_number;
        this.car_number = car_number;
    }



}