package com.app.app_kids.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.app_kids.Model.ModelClass;
import com.app.app_kids.R;
import com.app.app_kids.activity.PlayVideo;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TutModelAdapter extends RecyclerView.Adapter<TutModelAdapter.TutViewHolder> {

    Context context;
    ArrayList<ModelClass> classes;

    public TutModelAdapter(Context context,ArrayList<ModelClass> classes)
    {
        this.context=context;
        this.classes=classes;
    }

    @NonNull
    @Override
    public TutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.row_view_tut,parent,false);
        return new TutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TutViewHolder holder, final int position) {

        holder.tutName.setText(classes.get(position).getTitle());
        //holder.rhyImg.

        holder.tutLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Toast.makeText(context, "url:"+classes.get(position).getUrl(), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context, PlayVideo.class);
                intent.putExtra("url",classes.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    class TutViewHolder extends RecyclerView.ViewHolder {

        ImageView tutImg;
        TextView tutName;
        MaterialCardView tutLay;

        public TutViewHolder(@NonNull View itemView) {
            super(itemView);

            tutImg=itemView.findViewById(R.id.ivTut);
            tutName=itemView.findViewById(R.id.tvTutName);
            tutLay=itemView.findViewById(R.id.tutLay);

        }
    }
}
