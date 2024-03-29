package com.ahsanf.submission3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(3000);
                    }catch(InterruptedException ex){
                        ex.printStackTrace();
                    }finally {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                }
            });
            thread.start();
        }
    }

