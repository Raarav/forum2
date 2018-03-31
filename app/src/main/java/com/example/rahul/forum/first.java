package com.example.rahul.forum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class first extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("loc","main");
        intent.putExtra("post_key","null");
        startActivity(intent);
    }
}
