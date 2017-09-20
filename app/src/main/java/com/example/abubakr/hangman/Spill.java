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


public class Spill extends AppCompatActivity implements View.OnClickListener
{
    String[] Ord;
    EditText inputBokstav;
    ImageView galge;
    LinearLayout gjettMeg;
    int antallFeil = 0;
    int antallRiktig = 0;
    int index;
    String nan;
    ArrayList<String> brukteOrd = new ArrayList<>();
    TextView[] myTextViews;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spill);

        findViewById(R.id.a).setOnClickListener(this);
        findViewById(R.id.b).setOnClickListener(this);
        findViewById(R.id.c).setOnClickListener(this);
        findViewById(R.id.d).setOnClickListener(this);
        findViewById(R.id.e).setOnClickListener(this);
        findViewById(R.id.f).setOnClickListener(this);
        findViewById(R.id.g).setOnClickListener(this);
        findViewById(R.id.h).setOnClickListener(this);
        findViewById(R.id.i).setOnClickListener(this);
        findViewById(R.id.j).setOnClickListener(this);
        findViewById(R.id.k).setOnClickListener(this);
        findViewById(R.id.l).setOnClickListener(this);
        findViewById(R.id.m).setOnClickListener(this);
        findViewById(R.id.n).setOnClickListener(this);
        findViewById(R.id.o).setOnClickListener(this);
        findViewById(R.id.p).setOnClickListener(this);
        findViewById(R.id.q).setOnClickListener(this);
        findViewById(R.id.r).setOnClickListener(this);
        findViewById(R.id.s).setOnClickListener(this);
        findViewById(R.id.t).setOnClickListener(this);
        findViewById(R.id.u).setOnClickListener(this);
        findViewById(R.id.v).setOnClickListener(this);
        findViewById(R.id.w).setOnClickListener(this);
        findViewById(R.id.x).setOnClickListener(this);
        findViewById(R.id.y).setOnClickListener(this);
        findViewById(R.id.z).setOnClickListener(this);

        inputBokstav = (EditText) findViewById(R.id.inputBokstav);
        galge = (ImageView) findViewById(R.id.galge);
        gjettMeg = (LinearLayout) findViewById(R.id.output_layout);
        Ord = getResources().getStringArray(R.array.OrdListe);

        index = (int) (Math.random() * Ord.length);
        nan = Ord[index];
        brukteOrd = new ArrayList<>();
        myTextViews = new TextView[nan.length()];

        for (int i = 0; i < nan.length(); i++)
        {
            final TextView rowTextView = new TextView(this);
            rowTextView.setText(" _ ");
            rowTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
            rowTextView.setTextSize(50);
            gjettMeg.addView(rowTextView);
            myTextViews[i] = rowTextView;
        }
    }

    @Override
    public void onClick(View v)
    {
        char hentBokstaver = ((Button) v).getText().charAt(0);
        String entered = String.valueOf(hentBokstaver);
        inputBokstav.setText("");

        if (entered.matches(""))
        {
            Toast.makeText(Spill.this, "Skriv inn bokstaver", Toast.LENGTH_SHORT).show();
            return;
        }

        if (brukteOrd.contains(entered))
        {
            Toast.makeText(Spill.this, "Allerede brukt!", Toast.LENGTH_SHORT).show();
            return;
        }
        brukteOrd.add(entered);

        int matched = 0;

        for (int i = 0; i < nan.length(); i++)
        {
            if (String.valueOf(nan.charAt(i)).toLowerCase().equals(entered.toLowerCase())) {
                if (i > 0)
                    myTextViews[i].setText(entered.toLowerCase());
                else
                    myTextViews[i].setText(entered.toUpperCase());
                matched = 1;
                antallRiktig += 1;
            }
        }
        if (matched != 1)
        {
            antallFeil += 1;

            switch (antallFeil)
            {
                case 1:
                    galge.setImageResource(R.drawable.galge1);
                    break;
                case 2:
                    galge.setImageResource(R.drawable.galge2);
                    break;
                case 3:
                    galge.setImageResource(R.drawable.galge3);
                    break;
                case 4:
                    galge.setImageResource(R.drawable.galge4);
                    break;
                case 5:
                    galge.setImageResource(R.drawable.galge5);
                    break;
                case 6:
                    galge.setImageResource(R.drawable.galge6);
                    break;
            }
        }
        if (antallRiktig == nan.length())
        {
            Intent i = new Intent(Spill.this, MainActivity.class);
            i.putExtra("NAN", "Du vant! \n\t Won");
            startActivity(i);
        } else if (antallFeil == 6) {
            Intent i = new Intent(Spill.this, MainActivity.class);
            i.putExtra("NAN", "Du har tapt!\n\t" + nan);
            startActivity(i);
        }
    }
}