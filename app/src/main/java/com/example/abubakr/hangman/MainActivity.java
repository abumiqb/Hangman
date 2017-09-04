package com.example.abubakr.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Spill (View view)
    {
        Intent intent = new Intent (this, Spill.class);
        startActivity(intent);
    }

    public void Regler (View view)
    {
        Intent intent = new Intent (this, Regler.class);
        startActivity(intent);
    }

    public void Sprak (View view)
    {
        Intent intent = new Intent (this, Sprak.class);
        startActivity(intent);
    }

    public void Stat (View view)
    {
        Intent intent = new Intent (this, Statistikk.class);
        startActivity(intent);
    }
}
