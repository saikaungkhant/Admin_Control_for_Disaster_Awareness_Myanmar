package com.example.awarenessadmin.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.awarenessadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class NewPostActivity extends AppCompatActivity {

   // private Spinner spinner;
    private EditText editTitle;
    private EditText editDescription;
    private Button btnPost;

    private String titleString = "";
    private String descriptionString = "";
   // private String importantString = "";
    private String dateString = "";

    private DatabaseReference mDatabase;
    private int totalId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        getSupportActionBar().setTitle("သတင်းအသစ်ထည့်ရန်");

        editTitle = (EditText)findViewById(R.id.editTitle);
        editDescription = (EditText)findViewById(R.id.editDescription);
        btnPost = (Button)findViewById(R.id.button1);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Disaster").child("news");

        //important news spinner
 /*       spinner =(Spinner)findViewById(R.id.spinnerImportant);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                importantString = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.chooseArray, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("It is an important new.");
*/
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNew();
            }
        });
    }

    public void addNew() {
        titleString = editTitle.getText().toString().trim();
        descriptionString = editDescription.getText().toString().trim();

        //current date
        DateFormat df = new SimpleDateFormat("d/MM/yyyy h:mm a");
        dateString = df.format(Calendar.getInstance().getTime());

        if(titleString.equals("")){
            Toast.makeText(getApplicationContext(),"You need to fill 'Title'!!", Toast.LENGTH_LONG).show();
        }else if(descriptionString.equals("")){
            Toast.makeText(getApplicationContext(),"You need to fill 'Description'!!", Toast.LENGTH_LONG).show();
        }else{
            String idString = String.valueOf(++totalId);

            HashMap<String, String> dataMap = new HashMap<>();
            dataMap.put("id",idString);
            dataMap.put("title",titleString);
            dataMap.put("description",descriptionString);
            dataMap.put("date",dateString);
           // dataMap.put("important",importantString);

            mDatabase.push()
                    .setValue(dataMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(NewPostActivity.this, "Successfully posted...", Toast.LENGTH_SHORT).show();
                                editTitle.setText("");
                                editDescription.setText("");
                            }else{
                                Toast.makeText(NewPostActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}

