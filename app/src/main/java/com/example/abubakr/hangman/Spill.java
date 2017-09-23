package com.example.abubakr.hangman;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
    String[] Words;
    EditText inputBokstav;
    ImageView galge;
    LinearLayout gjettMeg;
    int antallFeil = 0;
    int antallRiktig = 0;
    int score = 600;
    int index;
    String nan;
    TextView sp;
    ArrayList<String> brukteOrd = new ArrayList<>();
    TextView[] tekst;

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
        findViewById(R.id.æ).setOnClickListener(this);
        findViewById(R.id.ø).setOnClickListener(this);
        findViewById(R.id.å).setOnClickListener(this);
        View a = findViewById(R.id.æ);
        View b = findViewById(R.id.ø);
        View c = findViewById(R.id.å);

        sp = (TextView) findViewById(R.id.sp);
        String st = sp.getText().toString();
        inputBokstav = (EditText) findViewById(R.id.inputBokstav);
        galge = (ImageView) findViewById(R.id.galge);
        gjettMeg = (LinearLayout) findViewById(R.id.output_layout);
        Ord = getResources().getStringArray(R.array.OrdListe);
        Words = getResources().getStringArray(R.array.WordList);


        index = (int) (Math.random() * Ord.length);
        nan = Ord[index];
        brukteOrd = new ArrayList<>();
        tekst = new TextView[nan.length()];

        if(st.contains("en"))
        {
            a.setVisibility(View.GONE);
            b.setVisibility(View.GONE);
            c.setVisibility(View.GONE);
            Ord = Words;
            nan = Words[index];

        }
        else if(st.contains("no"))
        {
            a.setVisibility(View.VISIBLE);
            b.setVisibility(View.VISIBLE);
            c.setVisibility(View.VISIBLE);
        }

        for (int i = 0; i < nan.length(); i++)
        {
            final TextView streker = new TextView(this);
            streker.setText(" _ ");
            streker.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            streker.setTextSize(35);
            gjettMeg.addView(streker);
            tekst[i] = streker;
        }
    }

    @Override
    public void onClick(View v)
    {
        char hentBokstaver = ((Button) v).getText().charAt(0);
        String tastatur = String.valueOf(hentBokstaver);
        inputBokstav.setText("");
        v.setBackgroundColor(Color.BLACK);

        if (brukteOrd.contains(tastatur))
        {
            Toast.makeText(Spill.this, R.string.used, Toast.LENGTH_SHORT).show();

            return;
        }
        brukteOrd.add(tastatur);

        int samme = 0;

        for (int i = 0; i < nan.length(); i++)
        {
            if (String.valueOf(nan.charAt(i)).toLowerCase().equals(tastatur.toLowerCase()))
            {
                if (i > 0)

                    tekst[i].setText(tastatur.toLowerCase());
                else
                    tekst[i].setText(tastatur.toUpperCase());
                samme = 1;
                antallRiktig += 1;
                score += 0;

            }
        }
        if (samme != 1)
        {
            antallFeil += 1;
            score -= 100;

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
        String t = getResources().getString(R.string.Riktigord);
        String s = getResources().getString(R.string.dinScore);
        if (antallRiktig == nan.length())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.Duvant);
            builder.setMessage(t +": "+ nan + "\n" + s + ": " + score)

                    .setCancelable(false)
                    .setPositiveButton(R.string.NyttSpill, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    })

                    .setNegativeButton(R.string.hovedM, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            Spill.this.finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        if (antallFeil == 6)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.Dutapte);
            builder.setMessage(t +": "+ nan + "\n" + s + ": " + score)

                    .setCancelable(false)

                    .setPositiveButton(R.string.NyttSpill, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.hovedM, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            Spill.this.finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}