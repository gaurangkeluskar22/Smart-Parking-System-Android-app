package com.example.smartparkingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyAdaptar extends RecyclerView.Adapter<MyAdaptar.MyViewHolder> {
    private ArrayList<DataPonts> mList;
    private Context context;

    public MyAdaptar(Context context, ArrayList<DataPonts> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        long emissionsMilliSince1970Time = ((long) mList.get(position).TimeStamp) * 1000;
        Date timeMilliseconds = new Date(emissionsMilliSince1970Time);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy hh:mm:ss");

        holder.no.setText(Integer.toString(position));
        holder.date.setText(dateTimeFormat.format(timeMilliseconds));
        holder.temperature.setText(Float.toString(mList.get(position).temperature));
        holder.humidity.setText(Float.toString(mList.get(position).humidity));
    }


    @Override
    public int getItemCount() {

        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date,temperature,humidity,no;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            temperature = itemView.findViewById(R.id.temperature);
            humidity = itemView.findViewById(R.id.humidiy);
            no = itemView.findViewById(R.id.no);
        }
    }
}

