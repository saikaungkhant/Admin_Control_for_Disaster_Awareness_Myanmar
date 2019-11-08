package com.example.awarenessadmin.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.awarenessadmin.R;
import com.example.awarenessadmin.model.NewsModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowMapListDetailActivity extends AppCompatActivity {

    private TextView textDetailDisasterType,textDetailDisasterId,
            textDetailLongitude,textDetailLatitude,textDetailOtherInfo;

    private Button btnDeletePost;
    private String receiveUserId;

    private DatabaseReference mDatabase;
    private ArrayList<String> arrayList = new ArrayList<>();
    NewsModel newsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_maplist_detail);

        showPostDetail();
        btnDeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePost();
            }
        });
    }//onCreate

    protected void deletePost(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.delete_post_confirm_dialog, null);
        dialogBuilder.setView(dialogView);

        final Button buttonDeletePostOK = (Button)dialogView.findViewById(R.id.buttonDeletePostOK);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonDeletePostOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference deleteReference = FirebaseDatabase.getInstance().getReference("Disaster").child("Data").child(receiveUserId);

                deleteReference.removeValue();
                Toast.makeText(getApplicationContext(), "Disaster Info is successfully deleted!!!", Toast.LENGTH_LONG).show();
                b.dismiss();
                onBackPressed();
            }
        });
    }//deletePost

    protected void showPostDetail(){
        setContentView(R.layout.activity_show_maplist_detail);

        textDetailDisasterType = (TextView)findViewById(R.id.textDetailDisasterType);
        textDetailDisasterId = (TextView)findViewById(R.id.textDetailDisasterId);
        textDetailLongitude = (TextView)findViewById(R.id.textDetailLongitude);
        textDetailLatitude = (TextView)findViewById(R.id.textDetailLatitude);
        textDetailOtherInfo = (TextView)findViewById(R.id.textDetailOtherInfo);

        btnDeletePost = (Button)findViewById(R.id.btnDeletePost);

        String disasterType = (String)getIntent().getExtras().get("disaster");
        String disasterId = (String)getIntent().getExtras().get("id");
        String latitude = (String)getIntent().getExtras().get("latitude");
        String longitude = (String)getIntent().getExtras().get("longitude");
        String info = (String)getIntent().getExtras().get("info");

        getSupportActionBar().setTitle("Disaster "+disasterId);

       textDetailDisasterId.setText("Disaster Id: "+ disasterId);
       textDetailDisasterType.setText(disasterType);
       textDetailLatitude.setText("Latitude: "+ latitude);
       textDetailLongitude.setText("Longitude: "+longitude);
       textDetailOtherInfo.setText("Remark: "+ info);

        receiveUserId  = getIntent().getExtras().get("keyId").toString();
        System.out.println("ID: "+receiveUserId);
    }//showPostDetail
}