package com.example.abubakr.hangman;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Statistikk extends AppCompatActivity
{
    TextView tv_score;
    int lastScore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistikk);
        tv_score = (TextView) findViewById(R.id.tv_score);
        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastScore = preferences.getInt("lastScore",0);
        tv_score.setText("Score: "  + lastScore );
    }
}


