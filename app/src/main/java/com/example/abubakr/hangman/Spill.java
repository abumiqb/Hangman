package com.example.abubakr.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Spill extends AppCompatActivity
{
    String[] Ord;
    Button knapp;
    EditText inputBokstav;
    ImageView galge;
    LinearLayout gjettMeg;
    int antallFeil = 0;
    int antallRiktig = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);

        knapp = (Button)findViewById(R.id.knpOK);
        inputBokstav = (EditText)findViewById(R.id.inputBokstav);

        galge = (ImageView)findViewById(R.id.galge);
        gjettMeg = (LinearLayout)findViewById(R.id.output_layout);

        Ord = getResources().getStringArray(R.array.OrdListe);

        int index = (int)(Math.random()*Ord.length);
        final String nan = Ord[index];
        final ArrayList<String> brukteOrd = new ArrayList<>();
        final TextView[] myTextViews = new TextView[nan.length()];

        for (int i = 0; i < nan.length(); i++)
        {
            final TextView rowTextView = new TextView(this);
            rowTextView.setText(" _ ");
            rowTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
            rowTextView.setTextSize(50);
            gjettMeg.addView(rowTextView);
            myTextViews[i] = rowTextView;
        }

        knapp.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    String entered = inputBokstav.getText().toString();



                    inputBokstav.setText("");

                    if(entered.matches(""))
                    {
                        Toast.makeText(Spill.this,"Skriv inn bokstaver",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(brukteOrd.contains(entered))
                    {
                        Toast.makeText(Spill.this,"Allerede brukt!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    brukteOrd.add(entered);

                    int matched = 0;

                    for(int i = 0; i < nan.length(); i++)
                    {
                        if(String.valueOf(nan.charAt(i)).toLowerCase().equals(entered.toLowerCase()))
                        {
                            if(i > 0)
                                myTextViews[i].setText(entered.toLowerCase());
                            else
                                myTextViews[i].setText(entered.toUpperCase());
                            matched = 1;
                            antallRiktig += 1;
                        }
                    }

                    if(matched!= 1)
                    {
                        antallFeil += 1;

                        switch(antallFeil)
                        {
                            case 1 : galge.setImageResource(R.drawable.galge1);
                                break;
                            case 2 : galge.setImageResource(R.drawable.galge2);
                                break;
                            case 3 : galge.setImageResource(R.drawable.galge3);
                                break;
                            case 4 : galge.setImageResource(R.drawable.galge4);
                                break;
                            case 5 : galge.setImageResource(R.drawable.galge5);
                                break;
                            case 6 : galge.setImageResource(R.drawable.galge6);
                                break;
                        }
                    }

                    if(antallRiktig == nan.length())
                    {
                        Intent i = new Intent(Spill.this,MainActivity.class);
                        i.putExtra("NAN","Du vant! \n\t Won");
                        startActivity(i);
                    }
                    else if(antallFeil == 6)
                    {
                        Intent i = new Intent(Spill.this,MainActivity.class);
                        i.putExtra("NAN","Du har tapt!\n\t" + nan);
                        startActivity(i);
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(Spill.this," Feil feil",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
