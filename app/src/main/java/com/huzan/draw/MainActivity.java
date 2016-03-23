package com.huzan.draw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.umeng.update.UmengUpdateAgent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawLine(this));
        UmengUpdateAgent.update(this);
    }
}
