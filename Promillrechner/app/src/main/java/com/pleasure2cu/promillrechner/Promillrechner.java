package com.pleasure2cu.promillrechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Promillrechner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promillrechner);
    }

    public void computeBloodAlc(View view){
        float bierProzent, fuenfer, farbigProzent, zwanziger, purProzent, vierziger, zeit;
        EditText gewichtText = (EditText) findViewById(R.id.gewicht);
        EditText fuenferText = (EditText) findViewById(R.id.fuenfer);
        EditText zwanzigerText = (EditText) findViewById(R.id.zwanziger);
        EditText vierzigerText = (EditText) findViewById(R.id.vierziger);
        EditText zeitText = (EditText) findViewById(R.id.zeit);

        EditText bierProzentText = (EditText) findViewById(R.id.bierProzent);
        EditText farbigProzentText = (EditText) findViewById(R.id.farbigProzent);
        EditText purProzentText = (EditText) findViewById(R.id.farbigProzent);


        try {
            float gewicht = Float.parseFloat(gewichtText.getText().toString());

            if (bierProzentText.getText().toString().equals(""))
                bierProzent = (float) 0.05;
            else
                bierProzent = Float.parseFloat(bierProzentText.getText().toString())/100;

            if (fuenferText.getText().toString().equals(""))
                fuenfer = 0;
            else
                fuenfer = Float.parseFloat(fuenferText.getText().toString())*1000;

            if (farbigProzentText.getText().toString().equals(""))
                farbigProzent = (float) 0.2;
            else
                farbigProzent = Float.parseFloat(farbigProzentText.getText().toString())/100;

            if (zwanzigerText.getText().toString().equals(""))
                zwanziger = 0;
            else
                zwanziger = Float.parseFloat(zwanzigerText.getText().toString())*1000;

            if (purProzentText.getText().toString().equals(""))
                purProzent = (float) 0.4;
            else
                purProzent = Float.parseFloat(purProzentText.getText().toString())/100;

            if (vierzigerText.getText().toString().equals(""))
                vierziger = 0;
            else
                vierziger = Float.parseFloat(vierzigerText.getText().toString())*1000;

            if (zeitText.getText().toString().equals(""))
                zeit = 0;
            else
                zeit = Float.parseFloat(zeitText.getText().toString());

            TextView textViewMannPMHigh = (TextView) findViewById(R.id.textViewMannPMHigh);
            TextView textViewFrauPMHigh = (TextView) findViewById(R.id.textViewFrauPMHigh);
            TextView textViewMannPMAvg = (TextView) findViewById(R.id.textViewMannPMAvg);
            TextView textViewFrauPMAvg = (TextView) findViewById(R.id.textViewFrauPMAvg);
            TextView textViewMannPMLow = (TextView) findViewById(R.id.textViewMannPMLow);
            TextView textViewFrauPMLow = (TextView) findViewById(R.id.textViewFrauPMLow);

            double drunkenAlc = (fuenfer*bierProzent+zwanziger*farbigProzent+vierziger*purProzent)*0.8;
            double absorbedHigh = drunkenAlc*0.9;
            double absorbedAvg = drunkenAlc*0.8;
            double absorbedLow = drunkenAlc*0.7;
            textViewMannPMHigh.setText(Double.toString(Math.max(round(absorbedHigh/gewicht/0.69-0.1*zeit, 2), 0)));
            textViewFrauPMHigh.setText(Double.toString(Math.max(round(absorbedHigh/gewicht/0.57-0.1*zeit, 2), 0)));
            textViewMannPMAvg.setText(Double.toString(Math.max(round(absorbedAvg/gewicht/0.69-0.1*zeit, 2), 0)));
            textViewFrauPMAvg.setText(Double.toString(Math.max(round(absorbedAvg/gewicht/0.57-0.1*zeit, 2), 0)));
            textViewMannPMLow.setText(Double.toString(Math.max(round(absorbedLow/gewicht/0.69-0.2*zeit, 2), 0)));
            textViewFrauPMLow.setText(Double.toString(Math.max(round(absorbedLow/gewicht/0.57-0.2*zeit, 2), 0)));

        } catch (NumberFormatException e){}

    }

    private double round(double a, int b){
        double factor = Math.pow(10, (double) b);
        return Math.round(a * factor)/factor;
    }
}
