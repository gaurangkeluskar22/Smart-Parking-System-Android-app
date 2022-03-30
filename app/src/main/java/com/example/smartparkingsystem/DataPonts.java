package com.example.smartparkingsystem;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataPonts {
    float temperature,humidity;
    int TimeStamp;


    public DataPonts(int TimeStamp,float humidity,float temperature){
        this.temperature = temperature;
        this.humidity = humidity;
        this.TimeStamp = TimeStamp;

    }
    public DataPonts(){

    }
    public float getTemperature(){
        return temperature;
    }
    public float getHumidity(){
        return humidity;
    }
    public int getTimeStamp(){
        return TimeStamp;
    }

}
