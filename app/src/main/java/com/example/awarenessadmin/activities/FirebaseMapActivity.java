package com.example.awarenessadmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.awarenessadmin.MapsTestActivity;
import com.example.awarenessadmin.R;
import com.example.awarenessadmin.model.DisasterInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseMapActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAnalytics mFirebaseAnalytics;
    private DatabaseReference mDatabase;
    private Button btnsave;
    private Button btnview;
    private Button btngps;
    private EditText editId;
    private EditText editTextName;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private Spinner spinner;
    String text;
    long maxid=0;
    int i=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        String[]disaster= new String[]{
                "ဆိုင်ကလုန်း","မြေငလျင်","ရေကြီးခြင်း","မြေပြိုခြင်း","မိုးခေါင်ရေရှား","ခရမ်းလွန်ရောင်ခြည်မြင့်မား"

        };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.iud_maps_activity);
        getSupportActionBar().setTitle("ဘေးအန္တရာယ် အခြေအနေ");
        btnview=(Button)findViewById(R.id.btn_view);
        btngps = (Button)findViewById(R.id.btn_gps);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mDatabase= FirebaseDatabase.getInstance().getReference().child("Disaster");

        editId=(EditText)findViewById(R.id.edt_data);
        editTextName=(EditText)findViewById(R.id.edt_info);
        editTextLatitude=(EditText)findViewById(R.id.edt_lat);
        editTextLongitude=(EditText)findViewById(R.id.edt_long);
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(FirebaseMapActivity.this, R.layout.support_simple_spinner_dropdown_item,disaster);
        spinner.setAdapter(adapter);
        btnsave=(Button)findViewById(R.id.btn_save);
        btnsave.setOnClickListener(this);


        btngps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FirebaseMapActivity.this, MapsTestActivity.class);
                startActivity(i);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FirebaseMapActivity.this,FirebaseMapsViewActivity.class);
                startActivity(i);
            }
        });

    }
    int id;String disaster;String info;double latitude,longitude;
    private void saveUserInformation(){
        if(editId.getText().toString().equals("")) {


            //check null or not
            if (editId.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "You need to fill 'ဘေးအန္တရာယ် အမှတ်'!!", Toast.LENGTH_LONG).show();
            } else if (disaster.equals("")) {
                Toast.makeText(getApplicationContext(), "You need to fill 'ဘေးအန္တရာယ် အမျိုးအစားများ'!!", Toast.LENGTH_LONG).show();
            } else if (String.valueOf(latitude).equals("")) {
                Toast.makeText(getApplicationContext(), "You need to fill 'လတ္တီတွဒ်'!!", Toast.LENGTH_LONG).show();
            } else if (String.valueOf(longitude).equals("")) {
                Toast.makeText(getApplicationContext(), "You need to fill 'လောင်ဂျီတွဒ်'!!", Toast.LENGTH_LONG).show();
            }
        }else{
            id = Integer.parseInt(editId.getText().toString());
            disaster = text.trim();
            info = editTextName.getText().toString().trim();
            latitude = Double.parseDouble(editTextLatitude.getText().toString().trim());
            longitude = Double.parseDouble(editTextLongitude.getText().toString().trim());
            DisasterInformation disasterInformation =new DisasterInformation(id,disaster,info,latitude,longitude);
           // mDatabase.child("Data").child("Data"+id).setValue(disasterInformation);

            mDatabase.child("Data").child("Data"+id)
                    .setValue(disasterInformation)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(FirebaseMapActivity.this, "ထည့်သွင်းမှုအောင်မြင်ပါသည်..", Toast.LENGTH_SHORT).show();
                                editId.getText().clear();
                                editTextName.getText().clear();
                                editTextLatitude.getText().clear();
                                editTextLongitude.getText().clear();
                            }else{
                                Toast.makeText(FirebaseMapActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onClick(View view) {
        if(view==btnview){
            finish();
        }
        if (view==btnsave){
            saveUserInformation();
        }
    }
}
