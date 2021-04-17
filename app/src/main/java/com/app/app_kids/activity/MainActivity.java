package com.app.app_kids.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.app.app_kids.R;
//import com.google.android.gms.ads.AdView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{

    TextView tvRhymes,tvTut;
    LottieAnimationView lottieTut,lottieRhymes;
    ImageView ivTut,ivRhymes;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    MaterialCardView cardTut,cardRhymes;
    Animation leftAnim,rightAnim;
    String btnClick;
    //AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));

            /* MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });



        // Find Banner ad
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        // Display Banner ad
        mAdView.loadAd(adRequest);*/

            toolbar=findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);
            drawerLayout=findViewById(R.id.drawerLayout);
            navigationView=findViewById(R.id.navView);

            drawerToggle=new ActionBarDrawerToggle(
                    this,
                    drawerLayout,
                    R.string.openNavDrawer,
                    R.string.closeNavDrawer);

            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setupDrawerContent(navigationView);
            navigationView.bringToFront();


            lottieRhymes=findViewById(R.id.lottieRhymes);
            lottieTut=findViewById(R.id.lottieTut);

            cardTut=findViewById(R.id.cardTut);
            cardRhymes=findViewById(R.id.cardRhymes); 
            rightAnim= AnimationUtils.loadAnimation(this,R.anim.right_animation);
            leftAnim= AnimationUtils.loadAnimation(this,R.anim.left_animation);
            cardTut.setAnimation(leftAnim);
            cardRhymes.setAnimation(rightAnim);
            
            cardTut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tutClicked();
                }
            });
            
            cardRhymes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rhymesClicked();
                }
            });
        }
    }

    private void rhymesClicked() {
        //btnClick="rhymes";
        Intent intent=new Intent(MainActivity.this, RhymesVideoList.class);
        intent.putExtra("btn",btnClick);
        startActivity(intent);

    }

    private void tutClicked() {
        //btnClick="tut";
        Intent intent=new Intent(MainActivity.this, TutVideoList.class);
        intent.putExtra("btn",btnClick);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.

        if (drawerToggle.onOptionsItemSelected(item))
            return true;


        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        //Fragment fragment = null;
        // Class fragmentClass;


        menuItem.setChecked(true);

        drawerLayout.closeDrawers();

    }


}