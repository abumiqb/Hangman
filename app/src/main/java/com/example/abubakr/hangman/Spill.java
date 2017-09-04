package com.example.abubakr.hangman;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class Spill extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);
        String[] array = getResources().getStringArray(R.array.OrdListe); //Henter array med ord fra arrays.xml
        String randomStr = array[new Random().nextInt(array.length)]; //Velger random ord fra arrayet
        TextView textView = (TextView)findViewById(R.id.txt); // Oppretter textview
        textView.setText(randomStr);
    }
}
