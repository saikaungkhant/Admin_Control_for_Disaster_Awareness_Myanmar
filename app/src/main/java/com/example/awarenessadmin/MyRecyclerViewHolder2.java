package com.example.awarenessadmin;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewHolder2 extends RecyclerView.ViewHolder {

    public RelativeLayout itemLayout2;
    public TextView txtDisasterId, txtDisasterType;

    public MyRecyclerViewHolder2(@NonNull View itemView) {
        super(itemView);

        txtDisasterId = (TextView)itemView.findViewById(R.id.txtDisasterId);
        txtDisasterType = (TextView)itemView.findViewById(R.id.txtDisasterType);
        itemLayout2 = (RelativeLayout)itemView.findViewById(R.id.itemLayout2);
    }
}