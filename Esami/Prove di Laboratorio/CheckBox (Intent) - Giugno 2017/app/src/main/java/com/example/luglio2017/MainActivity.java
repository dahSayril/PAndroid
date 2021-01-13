package com.example.luglio2017;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    double totale=0, pizza=0, pasta=0, carne=0, insalata=0, acqua=0,vino=0,gelato=0,caffe=0,amaro=0;

    Button total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total = findViewById(R.id.totale);

    }

    public void onClick(View view) {
        CheckBox box=(CheckBox) view;
        String pietanza=(String) box.getTag();
        String str_id = "prezzo"+pietanza;
        int id = getResources().getIdentifier(str_id, "id", getPackageName());
        TextView prezzo=this.findViewById(id);

        if(box.isChecked()){
            if(pietanza.equals("Pizza"))
                pizza=Double.parseDouble((String) prezzo.getText());
            if(pietanza.equals("Pasta"))
                pasta=Double.parseDouble((String) prezzo.getText());
            if(pietanza.equals("Carne"))
                carne=Double.parseDouble((String) prezzo.getText());
            if(pietanza.equals("Insalata"))
                insalata=Double.parseDouble((String) prezzo.getText());
            if(pietanza.equals("Acqua"))
                acqua=Double.parseDouble((String) prezzo.getText());
            if(pietanza.equals("Vino"))
                vino=Double.parseDouble((String) prezzo.getText());
            if(pietanza.equals("Gelato"))
                gelato=Double.parseDouble((String) prezzo.getText());
            if(pietanza.equals("Caffè"))
                caffe=Double.parseDouble((String) prezzo.getText());
            if(pietanza.equals("Amaro"))
                amaro=Double.parseDouble((String) prezzo.getText());
        }else{
            if(pietanza.equals("Pizza"))
                pizza=0;
            if(pietanza.equals("Pasta"))
                pasta=0;
            if(pietanza.equals("Carne"))
                carne=0;
            if(pietanza.equals("Insalata"))
                insalata=0;
            if(pietanza.equals("Acqua"))
                acqua=0;
            if(pietanza.equals("Vino"))
                vino=0;
            if(pietanza.equals("Gelato"))
                gelato=0;
            if(pietanza.equals("Caffè"))
                caffe=0;
            if(pietanza.equals("Amaro"))
                amaro=0;
        }

        total.setText(Double.toString(calcolaTotale()));

    }

    public double calcolaTotale(){
        return totale=pizza+pasta+carne+insalata+acqua+vino+gelato+caffe+amaro;
    }

    public void fine(View view) {
        Intent i=new Intent(getApplicationContext(),Scontrino.class);
        i.putExtra("Pizza",pizza);
        i.putExtra("Pasta",pasta);
        i.putExtra("Carne",carne);
        i.putExtra("Insalata",insalata);
        i.putExtra("Acqua",acqua);
        i.putExtra("Vino",vino);
        i.putExtra("Caffe",caffe);
        i.putExtra("Amaro",amaro);
        i.putExtra("Totale",totale);
        i.putExtra("Gelato",gelato);
        startActivity(i);
    }
}