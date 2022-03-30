package com.example.smartparkingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SlotAnalysis extends AppCompatActivity {
    private RecyclerView recyclerview_Slot_analysis_screen;
    private ArrayList<BookedSlotData> list;
    private MyAdapterSlot adapter;
    BarChart barChart;
    int t1,t2,t3,t4;
    ArrayList<BarEntry> dataVals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_analysis);
        barChart = findViewById(R.id.slotBarchart);

        FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot1TimeData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                t1 = ((Number)snapshot.getValue()).intValue();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot2TimeData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                t2 = ((Number)snapshot.getValue()).intValue();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot3TimeData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                t3 = ((Number)snapshot.getValue()).intValue();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot4TimeData").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                t4 = ((Number)snapshot.getValue()).intValue();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
        retriveData();
        recyclerview_Slot_analysis_screen = (RecyclerView) findViewById(R.id.recyclerview_slot_analysis_screen);
        recyclerview_Slot_analysis_screen.setHasFixedSize(true);
        recyclerview_Slot_analysis_screen.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list = new ArrayList<>();
        adapter = new MyAdapterSlot(this,list);
        recyclerview_Slot_analysis_screen.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference("Slot Booked Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    BookedSlotData data = dataSnapshot1.getValue(BookedSlotData.class);
                    list.add(data);
                }
                System.out.println(list.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



      void retriveData(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("T1 Data:"+t1);

                dataVals.add(new BarEntry(0,t1));
                dataVals.add(new BarEntry(1,t2));
                dataVals.add(new BarEntry(2,t3));
                dataVals.add(new BarEntry(3,t4));


                BarDataSet barDataSet = new BarDataSet(dataVals,"Slot Data");
                BarData barData = new BarData();
                barData.addDataSet(barDataSet);
                barDataSet.setColor(Color.parseColor("#FF6200EE"));
                barChart.setData(barData);
                barChart.invalidate();
            }
        }, 5000);
    }

}