package com.symb.task.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //After splash activity it directly open partial activity
                Intent intent=new Intent(SplashActivity.this, PartialActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
