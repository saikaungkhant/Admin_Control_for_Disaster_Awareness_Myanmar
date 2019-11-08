package com.example.awarenessadmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.awarenessadmin.MyRecyclerViewHolder2;
import com.example.awarenessadmin.R;
import com.example.awarenessadmin.model.DisasterInformation;
import com.example.awarenessadmin.model.NewsModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IUDMapActivity extends AppCompatActivity {

    private String disasterIdString="";
    private String longitudeString = "";
    private String latitudeString= "";
    private String disasterTypeString = "";
    private String otherInfoString = "";

    private RecyclerView recyclerView2;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerOptions<DisasterInformation> options;
    private FirebaseRecyclerAdapter<DisasterInformation, MyRecyclerViewHolder2> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showAllPost2();

    }// onCreate

    @Override
    protected void onStart() {
        super.onStart();

        disasterIdString= "";
        longitudeString = "";
        latitudeString = "";
        disasterTypeString = "";
        otherInfoString = "";

        showAllPost2();
    }

    public void showAllPost2(){
        getSupportActionBar().setTitle("သဘာဝဘေးအန္တရာယ် စာရင်းပြင်ဆင်ရန်");
        setContentView (R.layout.list_map_activity);
        recyclerView2 = (RecyclerView)findViewById(R.id.recycler_view2);

        //reverse the order
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView2.setLayoutManager(layoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Disaster").child("Data");

        options = new FirebaseRecyclerOptions.Builder<DisasterInformation>()
                .setQuery(databaseReference,DisasterInformation.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<DisasterInformation, MyRecyclerViewHolder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyRecyclerViewHolder2 myRecyclerViewHolder, final int position, @NonNull final DisasterInformation disasterInformation) {
                myRecyclerViewHolder.txtDisasterId.setText(disasterInformation.getId()+"");
                myRecyclerViewHolder.txtDisasterType.setText(disasterInformation.getDisaster());

                myRecyclerViewHolder.itemLayout2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        String keyId = getRef(position).getKey();
                        String id=String.valueOf(disasterInformation.getId());
                        String longitude = String.valueOf(disasterInformation.getLongitude());
                        String latitude = String.valueOf(disasterInformation.getLatitude());;
                        String disaster = disasterInformation.getDisaster();
                        String info = disasterInformation.getInfo();

                        Intent intent = new Intent(IUDMapActivity.this,ShowMapListDetailActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("longitude", longitude);
                        intent.putExtra("latitude", latitude);
                        intent.putExtra("info", info);
                        intent.putExtra("disaster", disaster);
                        intent.putExtra("keyId",keyId);

                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public MyRecyclerViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(getBaseContext()).inflate(R.layout.post_item2,parent,false);
                return new MyRecyclerViewHolder2(itemView);
            }
        };

        adapter.startListening();
        recyclerView2.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        if(adapter != null){
            adapter.stopListening();
        }
        super.onStop();
    }
}