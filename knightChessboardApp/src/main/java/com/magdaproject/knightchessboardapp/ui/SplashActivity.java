package com.magdaproject.knightchessboardapp.ui;

import android.content.Intent;
import android.os.Bundle;

import com.magdaproject.knightchessboardapp.R;
import com.magdaproject.knightchessboardapp.Utils.GlobalUtils;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //android.os.Debug.waitForDebugger();
        GlobalUtils.setstatusBarColor(this, R.color.colorBrown);
        super.onCreate(savedInstanceState);
        startActivity(new Intent(SplashActivity.this, ChessboardActivity.class));
        finish();
    }
}
