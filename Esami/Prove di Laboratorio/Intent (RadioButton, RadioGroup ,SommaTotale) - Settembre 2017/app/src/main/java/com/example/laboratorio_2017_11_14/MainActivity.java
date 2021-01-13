package com.example.laboratorio_2017_11_14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int primo=0;
    private int secondo=0;
    private int contorno=0;
    private int frutta=0;
    private int totale=0;
    TextView stampatotale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stampatotale=findViewById(R.id.totale);
    }



    public void sommatre(View v){
        RadioButton button=(RadioButton) v;
        RadioGroup gruppo=(RadioGroup) button.getParent();
        String pietanza=gruppo.getTag().toString();
        switch (pietanza) {
            case "primo":
                primo = 3;
                break;
            case "secondo":
                secondo = 3;
                break;
            case "contorno":
                contorno = 3;
                break;
            case "frutta":
                frutta = 3;
                break;
        }
        totale=primo+secondo+contorno+frutta;
        stampatotale.setText("Totale: "+totale+"$");

    }

    public void sommacinque(View v){
        RadioButton button=(RadioButton) v;
        RadioGroup gruppo=(RadioGroup) button.getParent();
        String pietanza=gruppo.getTag().toString();
        switch (pietanza) {
            case "primo":
                primo = 5;
                break;
            case "secondo":
                secondo = 5;
                break;
            case "contorno":
                contorno = 5;
                break;
            case "frutta":
                frutta = 5;
                break;
        }
        totale=primo+secondo+contorno+frutta;
        stampatotale.setText("Totale: "+totale+"$");
    }

    public void sommasette(View v){
        RadioButton button=(RadioButton) v;
        RadioGroup gruppo=(RadioGroup) button.getParent();
        String pietanza=gruppo.getTag().toString();
        switch (pietanza) {
            case "primo":
                primo = 7;
                break;
            case "secondo":
                secondo = 7;
                break;
            case "contorno":
                contorno = 7;
                break;
            case "frutta":
                frutta = 7;
                break;
        }
        totale=primo+secondo+contorno+frutta;
        stampatotale.setText("Totale: "+totale+"$");
    }

    public void cassa(View view) {
        Intent i=new Intent(getApplicationContext(),Cassa.class);
        i.putExtra("Primo",primo);
        i.putExtra("Secondo",secondo);
        i.putExtra("Contorno",contorno);
        i.putExtra("Frutta",frutta);
        i.putExtra("Totale",totale);
        startActivity(i);
    }
}