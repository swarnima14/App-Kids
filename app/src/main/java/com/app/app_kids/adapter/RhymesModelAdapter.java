package com.app.app_kids.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.app_kids.Model.ModelClass;
import com.app.app_kids.R;
import com.app.app_kids.activity.PlayVideo;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RhymesModelAdapter extends RecyclerView.Adapter<RhymesModelAdapter.ViewHolder>
{

    Context context;
    ArrayList<ModelClass> modelClasses;

    public RhymesModelAdapter(Context c, ArrayList<ModelClass> m)
    {
        context=c;
        modelClasses=m;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.row_view_rhymes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.rhyName.setText(modelClasses.get(position).getTitle());
        //holder.rhyImg.

        holder.rhyLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, PlayVideo.class);
                intent.putExtra("url",modelClasses.get(position).getUrl());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView rhyImg;
        TextView rhyName;
        MaterialCardView rhyLay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rhyLay=itemView.findViewById(R.id.rhyLay);
            rhyImg=itemView.findViewById(R.id.ivRhy);
            rhyName=itemView.findViewById(R.id.tvRhyName);

        }
    }
}
