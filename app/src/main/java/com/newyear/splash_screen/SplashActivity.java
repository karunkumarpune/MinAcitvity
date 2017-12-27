package com.newyear.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.newyear.R;
import com.newyear.navigation_silder.MainActivity;



public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

      ImageView img= findViewById(R.id.img_splash);



        Glide.with(this)
                .load("http://www.karunkumar.in")
                .placeholder(R.drawable.progress_animation1)
                .into(img);

       // Log.d(TAG, "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            SplashActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        }, SPLASH_TIME_OUT);
    }
}
