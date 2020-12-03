package com.app.desiaustralia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.desiaustralia.HomePageDetailsActivity;
import com.app.desiaustralia.R;
import com.app.desiaustralia.model.home.TopNews;

import java.util.ArrayList;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.MyHolder> {
    private Context context;
    private ArrayList<TopNews> arrayList;
    private LayoutInflater inflater;
    public HomePageAdapter(Context context, ArrayList<TopNews> arrayList){
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.home_adapter_layout, null);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Glide.with(context).load(arrayList.get(position).getUrl()
        ).into(holder.imageView);
        holder.titleTv.setText(arrayList.get(position).getTitle());
        holder.descTv.setText(arrayList.get(position).getDescription());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomePageDetailsActivity.class);
                intent.putExtra("title", arrayList.get(position).getTitle());
                intent.putExtra("image", arrayList.get(position).getUrl());
                intent.putExtra("desc", arrayList.get(position).getDesc());
                intent.putExtra("page", "home");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTv, descTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTv = itemView.findViewById(R.id.titleTv);
            descTv = itemView.findViewById(R.id.descTv);

        }
    }
}
