package com.example.luglio2017;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Scontrino extends Activity {
    double totale=0, pizza=0, pasta=0, carne=0, insalata=0, acqua=0,vino=0,gelato=0,caffe=0,amaro=0;
    int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scontrino);
        LinearLayout layout=findViewById(R.id.layout);

        totale=getIntent().getExtras().getDouble("Totale");
        pizza =getIntent().getExtras().getDouble("Pizza");
        pasta=getIntent().getExtras().getDouble("Pasta");
        carne=getIntent().getExtras().getDouble("Carne");
        insalata=getIntent().getExtras().getDouble("Insalata");
        acqua=getIntent().getExtras().getDouble("Acqua");
        vino=getIntent().getExtras().getDouble("Vino");
        gelato=getIntent().getExtras().getDouble("Gelato");
        caffe=getIntent().getExtras().getDouble("Caffe");
        amaro=getIntent().getExtras().getDouble("Amaro");

        if(pizza>0){
            TextView view = new TextView(this);
            view.setText(Integer.toString(count)+". Pizza "+ Double.toString(pizza));
            view.setTextSize(40);
            layout.addView(view);
            count++;
        }
        if(pasta>0){
            TextView view = new TextView(this);
            view.setText(Integer.toString(count)+". Pasta "+ Double.toString(pasta));
            view.setTextSize(40);
            layout.addView(view);
            count++;
        }
        if(carne>0){
            TextView view = new TextView(this);
            view.setText(Integer.toString(count)+". Carne "+ Double.toString(carne));
            view.setTextSize(40);
            layout.addView(view);
            count++;
        }
        if(insalata>0){
            TextView view = new TextView(this);
            view.setText(Integer.toString(count)+". Insalata "+ Double.toString(insalata));
            view.setTextSize(40);
            layout.addView(view);
            count++;
        }
        if(acqua>0){
            TextView view = new TextView(this);
            view.setText(Integer.toString(count)+". Acqua "+ Double.toString(acqua));
            view.setTextSize(40);
            layout.addView(view);
            count++;
        }
        if(vino>0){
            TextView view = new TextView(this);
            view.setText(Integer.toString(count)+". Vino "+ Double.toString(vino));
            view.setTextSize(40);
            layout.addView(view);
            count++;
        }
        if(gelato>0){
            TextView view = new TextView(this);
            view.setText(Integer.toString(count)+". Gelato "+ Double.toString(gelato));
            view.setTextSize(40);
            layout.addView(view);
            count++;
        }
        if(caffe>0){
            TextView view = new TextView(this);
            view.setText(Integer.toString(count)+". Caffe "+ Double.toString(caffe));
            view.setTextSize(40);
            layout.addView(view);
            count++;
        }
        if(amaro>0){
            TextView view = new TextView(this);
            view.setText(Integer.toString(count)+". Amaro "+ Double.toString(amaro));
            view.setTextSize(40);
            layout.addView(view);
            count++;
        }
        if(totale>0){
            TextView view = new TextView(this);
            view.setText("Totale: "+ Double.toString(totale));
            view.setTextSize(40);
            layout.addView(view);
            count++;
        }












    }
}
