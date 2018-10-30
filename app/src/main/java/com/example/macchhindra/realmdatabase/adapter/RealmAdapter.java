package com.example.macchhindra.realmdatabase.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.macchhindra.realmdatabase.R;
import com.example.macchhindra.realmdatabase.module.MyModule;

import java.util.List;

import io.realm.Realm;


public class RealmAdapter extends RecyclerView.Adapter<RealmAdapter.MyViewHolder>  {

    public RealmAdapter(List<MyModule> list, Realm realm,Context context) {

        this.list = list;
        this.realm = realm;
        this.context = context;
    }

    List<MyModule> list;
    Realm realm;
    Context context;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_row_recycler_view, viewGroup,false);
        final MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
     MyModule myData = this.list.get(position);
     myViewHolder.tvTitle.setText(myData.getTitle());
     myViewHolder.tvBody.setText(myData.getBody());
     try{
         Glide.with(context).load("http://via.placeholder.com/300.png").into(myViewHolder.imageView);
     }catch (Exception e) {
         Log.e("Exception",e.getMessage());

     }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvTitle, tvBody;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.my_image_view);
            tvTitle = itemView.findViewById(R.id.my_title);
            tvBody = itemView.findViewById(R.id.my_body);
        }
    }
}
