package com.example.smartparkingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeScreen extends AppCompatActivity {
    private LinearLayout c;
    private Button slot1,slot2,slot3,slot4;
    FirebaseDatabase firebaseDatabase;
    int slot1_status,slot2_status,slot3_status,slot4_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        c=findViewById(R.id.c);
        slot1 = (Button) findViewById(R.id.slot1);
        slot2 = (Button) findViewById(R.id.slot2);
        slot3 = (Button) findViewById(R.id.slot3);
        slot4 = (Button) findViewById(R.id.slot4);


        slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(slot1_status==0) {
                    slot1.setBackgroundColor(Color.BLACK);
                    update_booking_status();
                    // intent to go to rent the slot section
                    Intent i = new Intent(HomeScreen.this,BuySlot.class);
                    i.putExtra("key","Slot 1");
                    startActivity(i);
                }
                else{
                    Toast.makeText(HomeScreen.this,"Slot 1 is Already Occupied",Toast.LENGTH_LONG).show();
                }
            }});

            slot2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(slot2_status == 0) {
                        slot2.setBackgroundColor(Color.BLACK);
                        update_booking_status();
                        // intent to go to rent the slot section
                        Intent i = new Intent(HomeScreen.this,BuySlot.class);
                        i.putExtra("key","Slot 2");
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(HomeScreen.this,"Slot 2 is Already Occupied",Toast.LENGTH_LONG).show();
                    }
                }});


            slot3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(slot3_status == 0) {
                        slot3.setBackgroundColor(Color.BLACK);
                        update_booking_status();
                        // intent to go to rent the slot section
                        Intent i = new Intent(HomeScreen.this,BuySlot.class);
                        i.putExtra("key","Slot 3");
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(HomeScreen.this,"Slot 3 is Already Occupied",Toast.LENGTH_LONG).show();
                    }
                }});

            slot4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(slot4_status == 0) {
                        slot4.setBackgroundColor(Color.BLACK);
                        update_booking_status();
                        // intent to go to rent the slot section
                        Intent i = new Intent(HomeScreen.this,BuySlot.class);
                        i.putExtra("key","Slot 4");
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(HomeScreen.this,"Slot 4 is Already Occupied",Toast.LENGTH_LONG).show();
                    }
                }});

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.home:
                Intent i= new Intent(this,MainActivity.class);
                startActivity(i);
                break;
            case R.id.Analysis:
                Intent i1= new Intent(this,Analysis.class);
                startActivity(i1);
                break;
            case R.id.SlotAnalysis:
                Intent i2 = new Intent(this,SlotAnalysis.class);
                startActivity(i2);
                break;
            case R.id.signout:
                FirebaseAuth.getInstance().signOut();
                Intent i3 = new Intent(this,Login.class);
                startActivity(i3);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    protected void onStart(){
        super.onStart();
        update_booking_status();
    }

    void update_booking_status(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("slot1 status").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        slot1_status = ((Number)snapshot.getValue()).intValue();
                        System.out.println("slot1 status: "+slot1_status);
                        update_booking_color();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                }
        );

        firebaseDatabase.getReference("slot2 status").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        slot2_status = ((Number)snapshot.getValue()).intValue();
                        System.out.println("slot2 status: "+slot2_status);
                        update_booking_color();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                }
        );

        firebaseDatabase.getReference("slot3 status").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        slot3_status = ((Number)snapshot.getValue()).intValue();
                        System.out.println("slot3 status: "+slot3_status);
                        update_booking_color();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                }
        );
        firebaseDatabase.getReference("slot4 status").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        slot4_status = ((Number)snapshot.getValue()).intValue();
                        System.out.println("slot4 status: "+slot4_status);
                        update_booking_color();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                }
        );
    }

    void update_booking_color(){
        if(slot1_status!=0){
            slot1.setBackgroundColor(Color.BLACK);
        }
        else{
            slot1.setBackgroundColor(Color.parseColor("#FF6200EE"));
        }

        if(slot2_status!=0){
            slot2.setBackgroundColor(Color.BLACK);
        }
        else{
            slot2.setBackgroundColor(Color.parseColor("#FF6200EE"));
        }
        if(slot3_status!=0){
            System.out.println("call inside");
            slot3.setBackgroundColor(Color.BLACK);
        }
        else{
            slot3.setBackgroundColor(Color.parseColor("#FF6200EE"));
        }
        if(slot4_status!=0){
            slot4.setBackgroundColor(Color.BLACK);
        }
        else{
            slot4.setBackgroundColor(Color.parseColor("#FF6200EE"));
        }
    }
}