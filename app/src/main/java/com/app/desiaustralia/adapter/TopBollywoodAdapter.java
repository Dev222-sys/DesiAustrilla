package com.app.desiaustralia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.app.desiaustralia.R;
import com.app.desiaustralia.model.topbollywoodsong.BollywoodSongData;

import java.util.ArrayList;

public class TopBollywoodAdapter extends RecyclerView.Adapter<TopBollywoodAdapter.MyHolder>  {


    private Context context;
    private ArrayList<BollywoodSongData> arrayList;
    private LayoutInflater inflater;
    public TopBollywoodAdapter(Context context, ArrayList<BollywoodSongData> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.top_bollywood_adapter_layout, null);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Glide.with(context).load(arrayList.get(position).getUrl()
        ).into(holder.imageView);
        holder.titleTv.setText(arrayList.get(position).getTitle());
        holder.dateTv.setText(arrayList.get(position).getMovies_name());
        holder.singerTv.setText(arrayList.get(position).getSinger());
        holder.countTv.setText(arrayList.get(position).getCount().toString());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTv, dateTv, singerTv, countTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTv = itemView.findViewById(R.id.titleTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            singerTv = itemView.findViewById(R.id.singerTv);
            countTv = itemView.findViewById(R.id.countTv);

        }
    }
}
