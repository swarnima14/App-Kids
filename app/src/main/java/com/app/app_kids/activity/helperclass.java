package com.app.app_kids.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.View;

public class helperclass {

    private Activity context;


    public helperclass(Activity context) {
        this.context = context;

    }

    /**
     * call this method to enter full screen
     */
    public void enterFullScreen() {
        View decorView = context.getWindow().getDecorView();

        hideSystemUi(decorView);
        context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);


    }

    /**
     * call this method to exit full screen
     */
    public void exitFullScreen() {
        View decorView = context.getWindow().getDecorView();

        showSystemUi(decorView);
        context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);


    }

    private void hideSystemUi(View mDecorView) {
        mDecorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void showSystemUi(View mDecorView) {
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
}
