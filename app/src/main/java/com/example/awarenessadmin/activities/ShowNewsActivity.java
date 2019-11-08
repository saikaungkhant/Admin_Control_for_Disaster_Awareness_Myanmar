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

import com.example.awarenessadmin.MyRecyclerViewHolder;
import com.example.awarenessadmin.R;
import com.example.awarenessadmin.model.NewsModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowNewsActivity extends AppCompatActivity {

    private String dateString="";
    private String titletring = "";
    private String descriptionString= "";
    private String importantString = "";

    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseRecyclerOptions<NewsModel> options;
    private FirebaseRecyclerAdapter<NewsModel, MyRecyclerViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showAllPost2();

    }// onCreate

    @Override
    protected void onStart() {
        super.onStart();

        titletring= "";
        dateString = "";
        descriptionString = "";
        importantString = "";

        showAllPost2();
    }

    public void showAllPost2(){
        getSupportActionBar().setTitle("သတင်းများကို ပြင်ဆင်ရန်");
        setContentView (R.layout.acticity_show_post2);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        //reverse the order
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Disaster").child("news");

        options = new FirebaseRecyclerOptions.Builder<NewsModel>()
                .setQuery(databaseReference,NewsModel.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<NewsModel, MyRecyclerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyRecyclerViewHolder myRecyclerViewHolder, final int position, @NonNull final NewsModel newsModel) {
                myRecyclerViewHolder.txtTitle.setText(newsModel.getTitle());
                myRecyclerViewHolder.txtDate.setText(newsModel.getDate());

                myRecyclerViewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        String keyId = getRef(position).getKey();
                        String description=newsModel.getDescription();
                        String title=newsModel.getTitle();
                        String date=newsModel.getDate();

                        Intent intent = new Intent(ShowNewsActivity.this,ShowPostDetailActivity.class);
                        intent.putExtra("description", description);
                        intent.putExtra("title", title);
                        intent.putExtra("date", date);
                        intent.putExtra("keyId",keyId);

                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(getBaseContext()).inflate(R.layout.post_item,parent,false);
                return new MyRecyclerViewHolder(itemView);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        if(adapter != null){
            adapter.stopListening();
        }
        super.onStop();
    }
}