package com.example.smartparkingsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class BuySlot extends AppCompatActivity {
    FirebaseUser user;
    DatabaseReference reference;
    String userID;
    Slot slot;
    private String randomKey;
    int t2;



    private String user_name,user_email_id,car_number,slot_time,slot_no,slot_no_key;
    private TextInputEditText user_name_textinputedittext,car_number_textinputedittext,user_email_textinputedittext,slot_no_textinputedittext,slot_time_textinputedittext;
    private Button buy_slot_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_slot);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            slot_no_key = extras.getString("key");
        }


        user_email_textinputedittext=(TextInputEditText) findViewById(R.id.user_email_textinputedittext);
        slot_no_textinputedittext = (TextInputEditText) findViewById(R.id.slot_no_textinputedittext);
        user_name_textinputedittext = (TextInputEditText) findViewById(R.id.user_name_textinputedittext);
        car_number_textinputedittext = (TextInputEditText) findViewById(R.id.car_number_textinputedittext);
        slot_time_textinputedittext = (TextInputEditText) findViewById(R.id.slot_time_textinputedittext);
        slot_no_textinputedittext = (TextInputEditText) findViewById(R.id.slot_no_textinputedittext);
        buy_slot_button = (Button) findViewById(R.id.buy_slot_button);






        buy_slot_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomKey = UUID.randomUUID().toString();

                user_name = user_name_textinputedittext.getText().toString();
                car_number = car_number_textinputedittext.getText().toString();
                user_email_id = user_email_textinputedittext.getText().toString();
                slot_time = slot_time_textinputedittext.getText().toString();
                slot_no = slot_no_textinputedittext.getText().toString();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String TimeStamp = dateFormat.format(new Date());
                slot = new Slot(randomKey.toString(),userID,user_name,user_email_id,car_number,slot_time,slot_no,TimeStamp);


                FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("Slot Booked Data").child(randomKey).setValue(slot).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            if(slot_no_key.equals("Slot 1")){
                                FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot1TimeData").addValueEventListener(
                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                int t = ((Number)snapshot.getValue()).intValue();

                                                System.out.println("Time data:"+t);
                                                int t1 = Integer.parseInt(slot_time);
                                                t2 = t+t1;
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) { }
                                        }
                                );
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Time data outside addvalue function: "+t2);
                                        FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot1TimeData").setValue(t2);
                                        Toast.makeText(BuySlot.this,"Now "+slot_no+"is yours for "+slot_time+" Hours",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(BuySlot.this,HomeScreen.class));
                                    }
                                }, 5000);

                            }

                            if(slot_no_key.equals("Slot 2")){
                                FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot2TimeData").addValueEventListener(
                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                int t = ((Number)snapshot.getValue()).intValue();

                                                System.out.println("Time data:"+t);
                                                int t1 = Integer.parseInt(slot_time);
                                                t2 = t+t1;
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) { }
                                        }
                                );
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Time data outside addvalue function: "+t2);
                                        FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot2TimeData").setValue(t2);
                                        Toast.makeText(BuySlot.this,"Now "+slot_no+"is yours for "+slot_time+" Hours",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(BuySlot.this,HomeScreen.class));
                                    }
                                }, 5000);

                            }
                            if(slot_no_key.equals("Slot 3")){
                                FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot3TimeData").addValueEventListener(
                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                int t = ((Number)snapshot.getValue()).intValue();

                                                System.out.println("Time data:"+t);
                                                int t1 = Integer.parseInt(slot_time);
                                                t2 = t+t1;
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) { }
                                        }
                                );
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Time data outside addvalue function: "+t2);
                                        FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot3TimeData").setValue(t2);
                                        Toast.makeText(BuySlot.this,"Now "+slot_no+"is yours for "+slot_time+" Hours",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(BuySlot.this,HomeScreen.class));
                                    }
                                }, 5000);

                            }
                            if(slot_no_key.equals("Slot 4")){
                                FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot4TimeData").addValueEventListener(
                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                int t = ((Number)snapshot.getValue()).intValue();

                                                System.out.println("Time data:"+t);
                                                int t1 = Integer.parseInt(slot_time);
                                                t2 = t+t1;
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) { }
                                        }
                                );
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        System.out.println("Time data outside addvalue function: "+t2);
                                        FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("slot4TimeData").setValue(t2);
                                        Toast.makeText(BuySlot.this,"Now "+slot_no+"is yours for "+slot_time+" Hours",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(BuySlot.this,HomeScreen.class));
                                    }
                                }, 5000);

                            }

                        }else{
                            Toast.makeText(BuySlot.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }

        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        display_fetched_data();
    }

    protected void display_fetched_data(){
        // Display data fetched from firebase in the TextInputEditText
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://smart-parking-system-1b2ec-default-rtdb.firebaseio.com/").getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile!=null){
                    user_name = userProfile.full_name;
                    user_email_id = userProfile.email_id;
                    car_number = userProfile.car_number;
                    user_name_textinputedittext.setText(user_name);
                    car_number_textinputedittext.setText(car_number);
                    user_email_textinputedittext.setText(user_email_id);
                    slot_no_textinputedittext.setText(slot_no_key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BuySlot.this,"Something wrong happened!",Toast.LENGTH_LONG).show();
            }
        });
    }
}