package com.periculam.aravi.dice;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.Delayed;

import static java.lang.Thread.sleep;


public class MainActivity extends AppCompatActivity {

    TextView t1,t2;

    Button roll,hold,reset;

    ImageView dice;

    int us=0,cs=0;
    int ucs=0,ccs=0;
    int dd;
    Handler hm = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);

        hold = (Button) findViewById(R.id.h);
        reset = (Button) findViewById(R.id.res);
        roll = (Button) findViewById(R.id.r);

        dice = (ImageView) findViewById(R.id.i);

        t1.setText("User Score = " + us);
        t2.setText("Computer Score = " + cs);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t1.setText("User Score = " + us);
                t2.setText("User Current Score = " + ucs);

                int score = Randomizer();

                if (Randomizer()!= 1) {
                    ucs+= score;
                    t2.setText("User Current Score = " + ucs);
                    ImageChanger(score);
                    int tempCheck = us+ucs;
                    WinnerCheck(tempCheck,"Computer");
                }
                else {
                    try {
                        ComputerTurn();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    t1.setText("User Score = " + us);
                    t2.setText("Computer Score = " + cs);
                    ucs=0;

                }
            }


        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                us+= ucs;
                ucs=0;
                try {
                    ComputerTurn();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                us=0; cs=0; ucs = 0; ccs=0;
                t1.setText("User Score = " + us);
                t2.setText("Computer Score = " + cs);
            }
        });
    }

    public void ComputerTurn() throws Exception
    {
        t1.setText("Computer Score = " + cs);
        t2.setText("Computer Current Score = " + ccs);

        int score = 0;
        while (score<20)
        {
            roll.setEnabled(false);
            hold.setEnabled(false);
            reset.setEnabled(false);
            dd = Randomizer();
                ImageChanger(dd);
                ccs+=dd;
                score+=dd;
                t2.setText("Computer Current Score = " + ccs);
                int tempCheck = cs+ccs;
                WinnerCheck(tempCheck,"Computer");


               // less20();
            if(dd==1) {
                cs+=ccs;
                ccs=0;
                roll.setEnabled(true);
                hold.setEnabled(true);
                reset.setEnabled(true);
                return;
            }
        }
        cs+=ccs;
        ccs=0;
        roll.setEnabled(true);
        hold.setEnabled(true);
        reset.setEnabled(true);

    }

    public void less20()
    {
        if(ccs>20)
        {
            ccs-=dd;
            cs+=ccs;
            ccs=0;
            roll.setEnabled(true);
            hold.setEnabled(true);
            reset.setEnabled(true);
            return;

        }
    }

    public void BreakGame(String player)
    {
        us=0;cs=0;
        t1.setText("User Score = " + us);
        t2.setText("Computer Score = " + cs);
        Toast.makeText(getApplicationContext(),player+" Wins the GAME",Toast.LENGTH_LONG).show();
    }

    public void WinnerCheck(int dataCheck,String str)
    {
        if (dataCheck>=100)
        {
            BreakGame(str);
        }
    }

    public void ImageChanger(int n)
    {
        switch (n)
        {
            case 1: dice.setImageResource(R.drawable.dice1);ccs=0;return;
            case 2: dice.setImageResource(R.drawable.dice2);break;
            case 3: dice.setImageResource(R.drawable.dice3);break;
            case 4: dice.setImageResource(R.drawable.dice4);break;
            case 5: dice.setImageResource(R.drawable.dice5);break;
            case 6: dice.setImageResource(R.drawable.dice6);break;
        }
    }

    public int Randomizer()
    {
        Random rn = new Random();

        int rno = rn.nextInt(6)+1;

        return rno;
    }
}
