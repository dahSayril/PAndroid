package com.example.laboratorio_2017_11_14;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.zip.ZipEntry;

public class Cassa extends AppCompatActivity {

    private TextView totale;
    private TextView primo;
    private TextView secondo;
    private TextView contorno;
    private TextView frutta;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cassa);

        int totaleSpesa= getIntent().getExtras().getInt("Totale");
        int primoSpesa=getIntent().getExtras().getInt("Primo");
        int secondoSpesa=getIntent().getExtras().getInt("Secondo");
        int contornoSpesa=getIntent().getExtras().getInt("Contorno");
        int fruttaSpesa=getIntent().getExtras().getInt("Frutta");

        totale=findViewById(R.id.cassaTotale);
        primo=findViewById(R.id.cassaPrimo);
        secondo=findViewById(R.id.cassaSecondo);
        contorno=findViewById(R.id.cassaContorno);
        frutta=findViewById(R.id.cassaFrutta);

        totale.setText("Totale pasto: "+totaleSpesa+"$");
        primo.setText("Primo:"+ primoSpesa+"$");
        secondo.setText("Secondo:"+secondoSpesa+"$");
        contorno.setText("Contorno:"+contornoSpesa+"$");
        frutta.setText("Frutta:"+fruttaSpesa+"$");

    }

}
