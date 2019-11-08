package com.example.awarenessadmin.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.awarenessadmin.R;
import com.example.awarenessadmin.model.NewsModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ShowPostDetailActivity extends AppCompatActivity {

    //private TextView textDetailTitle;
    private TextView textDetailDate,textDetailDescription;
    private Button btnDeletePost;
    private String receiveUserId;

    private DatabaseReference mDatabase;
    private ArrayList<String> arrayList = new ArrayList<>();
    NewsModel newsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post_detail);

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

                DatabaseReference deleteReference = FirebaseDatabase.getInstance().getReference("Disaster").child("news").child(receiveUserId);

                deleteReference.removeValue();
                Toast.makeText(getApplicationContext(), "Post is successfully deleted!!!", Toast.LENGTH_LONG).show();
                b.dismiss();
                onBackPressed();
            }
        });
    }//deletePost

    protected void showPostDetail(){
        setContentView(R.layout.activity_show_post_detail);

        textDetailDate = (TextView)findViewById(R.id.textDetailDate);
        textDetailDescription = (TextView)findViewById(R.id.textDetailDescription);
        btnDeletePost = (Button)findViewById(R.id.btnDeletePost);

        String description= (String) getIntent().getExtras().get("description");
        String title=(String) getIntent().getExtras().get("title");
        String date=(String) getIntent().getExtras().get("date");

        getSupportActionBar().setTitle(title);

        textDetailDate.setText(date);
        textDetailDescription.setText(description);

        receiveUserId  = getIntent().getExtras().get("keyId").toString();
        System.out.println("ID: "+receiveUserId);
    }//showPostDetail
}
