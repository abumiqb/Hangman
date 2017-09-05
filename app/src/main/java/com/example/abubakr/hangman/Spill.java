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
    ImageView game_image_view;
    LinearLayout my_output_layout;
    int wrong_attempts = 0,successfull_attempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);

        knapp = (Button)findViewById(R.id.knpOK);

        inputBokstav = (EditText)findViewById(R.id.inputBokstav);

        game_image_view = (ImageView)findViewById(R.id.game_images);

        my_output_layout = (LinearLayout)findViewById(R.id.output_layout);

        Ord = getResources().getStringArray(R.array.OrdListe);

        int index = (int)(Math.random()*Ord.length);

        final String selected_string = Ord[index];

        final ArrayList<String> used_characters = new ArrayList<>();

        final TextView[] myTextViews = new TextView[selected_string.length()];

        for (int i = 0; i < selected_string.length(); i++)
        {
            final TextView rowTextView = new TextView(this);
            rowTextView.setText(" _ ");
            rowTextView.setTextColor(0xFF303F9F);
            rowTextView.setTextSize(25);
            my_output_layout.addView(rowTextView);
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

                    if(used_characters.contains(entered))
                    {
                        Toast.makeText(Spill.this,"Allerede brukt!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    used_characters.add(entered);

                    int matched = 0;

                    for(int i=0;i<selected_string.length();i++)
                    {
                        if(String.valueOf(selected_string.charAt(i)).equals(entered))
                        {
                            myTextViews[i].setText(entered);
                            matched = 1;
                            successfull_attempts+=1;
                        }
                    }

                    if(matched!= 1)
                    {
                        wrong_attempts += 1;

                        switch(wrong_attempts)
                        {
                            case 1 : game_image_view.setImageResource(R.drawable.galge1);
                                break;
                            case 2 : game_image_view.setImageResource(R.drawable.galge2);
                                break;
                            case 3 : game_image_view.setImageResource(R.drawable.galge3);
                                break;
                            case 4 : game_image_view.setImageResource(R.drawable.galge4);
                                break;
                            case 5 : game_image_view.setImageResource(R.drawable.galge5);
                                break;
                            case 6 : game_image_view.setImageResource(R.drawable.galge6);
                                break;
                        }
                    }

                    if(successfull_attempts == selected_string.length())
                    {
                        Intent i = new Intent(Spill.this,MainActivity.class);
                        i.putExtra("game_report","Du vant! \n\t Won");
                        startActivity(i);
                    }
                    else if(wrong_attempts == 6)
                    {
                        Intent i = new Intent(Spill.this,MainActivity.class);
                        i.putExtra("game_report","Du har tapt!\n\t" + selected_string);
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
