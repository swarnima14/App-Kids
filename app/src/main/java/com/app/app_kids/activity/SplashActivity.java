package com.app.app_kids.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.app.app_kids.R;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN=5000;

    Animation topAnim,bottomAnim;
    ImageView imageView;
    TextView name,desc;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.black));
        }

        //initialise
        imageView=findViewById(R.id.ivImage);
        name=findViewById(R.id.tvAppName);
        desc=findViewById(R.id.tvAppDesc);
        lottieAnimationView=findViewById(R.id.lottieSplash);


        //Animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //setAnim
        lottieAnimationView.setAnimation(topAnim);
        name.setAnimation(bottomAnim);
        desc.setAnimation(bottomAnim);
       // imageView.setAnimation(topAnim);




    }

    @Override
    protected void onStart() {
        super.onStart();

        //moving to next activity after delaying by few seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                SplashActivity.this.startActivity(intent);
            }
        },SPLASH_SCREEN);
    }
}