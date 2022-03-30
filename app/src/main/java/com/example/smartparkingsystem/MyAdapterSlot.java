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

public class MyAdapterSlot extends RecyclerView.Adapter<MyAdapterSlot.MyViewHolder> {
    private ArrayList<BookedSlotData> mList;
    private Context context;

    public MyAdapterSlot(Context context, ArrayList<BookedSlotData> mList){
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

        holder.no.setText(mList.get(position).TimeStamp);
        holder.date.setText(mList.get(position).user_name);
        holder.temperature.setText(mList.get(position).slot_no);
        holder.humidity.setText(mList.get(position).car_number);
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

