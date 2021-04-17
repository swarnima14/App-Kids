package com.app.app_kids.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.app_kids.Model.ModelClass;
import com.app.app_kids.R;
import com.app.app_kids.adapter.RhymesModelAdapter;
import com.app.app_kids.adapter.TutModelAdapter;
/*import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;*/
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RhymesVideoList extends AppCompatActivity {

    RecyclerView recyclerView;
    RhymesModelAdapter adapter;
    ArrayList<ModelClass> modelClasses;
    //AdView mAdView;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference reference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhymes_video_list);

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusbar));
        }

       /* MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });



        // Find Banner ad
        mAdView = findViewById(R.id.adViewRhy);
        AdRequest adRequest = new AdRequest.Builder().build();
        // Display Banner ad
        mAdView.loadAd(adRequest);*/
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        initRecyclerView();

        setData();

    }

    private void initRecyclerView()
    {

        recyclerView=findViewById(R.id.rvRhymes);
        layoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void setData() {

        reference= FirebaseDatabase.getInstance().getReference().child("Rhymes");

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                modelClasses=new ArrayList<>();
                for(DataSnapshot ds:snapshot.getChildren())
                {
                    String title=ds.child("title").getValue().toString();
                    String url=ds.child("url").getValue().toString();
                    ModelClass tutDetails=new ModelClass(title,url);
                    modelClasses.add(tutDetails);
                }
                adapter = new RhymesModelAdapter(RhymesVideoList.this,modelClasses);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RhymesVideoList.this, "error:"+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}