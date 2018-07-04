package com.ypotato.myprogressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CircleProgressView circleProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleProgressView = findViewById(R.id.circleProgress);
        circleProgressView.setTextColor("#000000");
        circleProgressView.setProgressWidth(50);
        circleProgressView.setCircleProgress(80);
    }
}
