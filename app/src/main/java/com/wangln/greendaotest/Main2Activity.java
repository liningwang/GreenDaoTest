package com.wangln.greendaotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wangln.greendaotest.relation.RelationActivity;
import com.wangln.greendaotest.simple.MainActivity;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void simpleSample(View view) {
        Intent starter = new Intent(this, MainActivity.class);
        startActivity(starter);
    }
    public void relationsSample(View view) {
        Intent starter = new Intent(this, RelationActivity.class);
        startActivity(starter);
    }
}
