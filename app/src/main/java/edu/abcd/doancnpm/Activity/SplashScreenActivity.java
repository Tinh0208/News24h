package edu.abcd.doancnpm.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import edu.abcd.doancnpm.R;

public class SplashScreenActivity extends AppCompatActivity {
    TextView txtNews, txtDes;
    Animation txtAnimation, layoutAnimation,titleAnimation;
    LottieAnimationView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        txtAnimation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.anim_fall_down);
        layoutAnimation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.anim_bot_to_top);
        titleAnimation = AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.anim_top);

        txtNews = findViewById(R.id.txt_title);
        txtDes = findViewById(R.id.txt_description);
        splash = findViewById(R.id.splash_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splash.setVisibility(View.VISIBLE);
                splash.setAnimation(layoutAnimation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txtNews.setVisibility(View.VISIBLE);
                        txtDes.setVisibility(View.VISIBLE);

                        txtNews.setAnimation(titleAnimation);
                        txtDes.setAnimation(txtAnimation);
                    }
                }, 1000);
            }
        },500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,StartActivity.class);
                startActivity(intent);
                finish();
            }
        }, 6000);
    }
}