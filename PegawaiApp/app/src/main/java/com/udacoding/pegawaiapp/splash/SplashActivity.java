package com.udacoding.pegawaiapp.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.udacoding.pegawaiapp.R;
import com.udacoding.pegawaiapp.home.MainActivity;
import com.udacoding.pegawaiapp.login.LoginActivity;
import com.udacoding.pegawaiapp.utils.SessionManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SessionManager sessionManager = new SessionManager(this);
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(sessionManager.isLogin()){

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                else{
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                }

                finish();


            }
        },4000);
    }
}
