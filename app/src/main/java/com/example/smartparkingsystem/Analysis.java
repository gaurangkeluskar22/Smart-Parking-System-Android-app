


package com.example.smartparkingsystem;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.widget.ScrollView;
        import android.widget.TextView;

        import com.github.mikephil.charting.charts.LineChart;
        import com.github.mikephil.charting.components.XAxis;
        import com.github.mikephil.charting.data.Entry;
        import com.github.mikephil.charting.data.LineData;
        import com.github.mikephil.charting.data.LineDataSet;
        import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
        import com.google.android.gms.fitness.data.DataPoint;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.lang.reflect.Array;
        import java.util.ArrayList;
        import java.util.Map;

public class Analysis extends AppCompatActivity {
    private RecyclerView recyclerview_analysis_screen;
    private ArrayList<DataPonts> list;
    private MyAdaptar adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    LineChart lineChartTemperature,lineChartHumidity;
    LineDataSet lineDataSetTemperature = new LineDataSet(null,null);
    LineDataSet lineDataSetHumidity = new LineDataSet(null,null);
    ArrayList<ILineDataSet> iLineDataSetsTemperature = new ArrayList<>();
    ArrayList<ILineDataSet> iLineDataSetsHumidity = new ArrayList<>();
    LineData lineDataTemperature,lineDataHumidity;
    TextView display_live_temperature,display_live_humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        lineChartTemperature = findViewById(R.id.linechartTemperature);
        lineChartHumidity = findViewById(R.id.linechartHumidity);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference("DHT11");
        display_live_temperature = (TextView) findViewById(R.id.display_live_temperature);
        display_live_humidity = (TextView) findViewById(R.id.display_live_humidity);

        XAxis xAxisTemperature = lineChartTemperature.getXAxis();
        xAxisTemperature.setValueFormatter(new LineChartXAxisValueFormatter());
        XAxis xAxisHumidity = lineChartHumidity.getXAxis();
        xAxisHumidity.setValueFormatter(new LineChartXAxisValueFormatter());
        retriveData();


        recyclerview_analysis_screen = (RecyclerView) findViewById(R.id.recyclerview_analysis_screen);
        recyclerview_analysis_screen.setHasFixedSize(true);
        recyclerview_analysis_screen.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list = new ArrayList<>();
        adapter = new MyAdaptar(this,list);
        recyclerview_analysis_screen.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    DataPonts data = dataSnapshot1.getValue(DataPonts.class);
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



    private void retriveData(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Entry> dataValsTemperature = new ArrayList<Entry>();
                ArrayList<Entry> dataValsHumidity = new ArrayList<Entry>();
                if(snapshot.hasChildren()){
                    for(DataSnapshot myDataSnapshot : snapshot.getChildren()){
                        DataPonts dataPoints = myDataSnapshot.getValue(DataPonts.class);
                        dataValsTemperature.add(new Entry(dataPoints.getTimeStamp(),dataPoints.getTemperature()));
                        dataValsHumidity.add(new Entry(dataPoints.getTimeStamp(),dataPoints.getHumidity()));
                    }
                    showChartTemperature(dataValsTemperature);
                    showChartHumidity(dataValsHumidity);
                }
                else{
                    lineChartTemperature.clear();
                    lineChartTemperature.invalidate();
                    lineChartHumidity.clear();
                    lineChartHumidity.invalidate();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void showChartTemperature(ArrayList<Entry> dataVals) {
        lineDataSetTemperature.setValues(dataVals);
        lineDataSetTemperature.setColor(Color.BLUE);
        lineDataSetTemperature.setLabel("Temperature");
        iLineDataSetsTemperature.clear();
        iLineDataSetsTemperature.add(lineDataSetTemperature);
        lineDataTemperature = new LineData(iLineDataSetsTemperature);
        lineChartTemperature.clear();
        lineChartTemperature.setData(lineDataTemperature);
        lineChartTemperature.invalidate();
    }
    private void showChartHumidity(ArrayList<Entry> dataVals) {
        lineDataSetHumidity.setValues(dataVals);
        lineDataSetHumidity.setLabel("Humidity");
        lineDataSetHumidity.setColor(Color.BLUE);
        iLineDataSetsHumidity.clear();
        iLineDataSetsHumidity.add(lineDataSetHumidity);
        lineDataHumidity = new LineData(iLineDataSetsHumidity);
        lineChartHumidity.clear();
        lineChartHumidity.setData(lineDataHumidity);
        lineChartHumidity.invalidate();
    }
    protected void onStart(){
        super.onStart();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("Temperature").addValueEventListener(
            new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    float t = ((Number)snapshot.getValue()).floatValue();
                    display_live_temperature.setText(Float.toString(t));
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) { }
            }
        );
        firebaseDatabase.getReference("Humidity").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        float h = ((Number)snapshot.getValue()).floatValue();
                        display_live_humidity.setText(Float.toString(h));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                }
        );
    }
}