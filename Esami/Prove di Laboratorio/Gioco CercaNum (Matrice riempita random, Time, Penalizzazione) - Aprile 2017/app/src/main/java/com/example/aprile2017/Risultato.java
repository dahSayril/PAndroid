package com.example.aprile2017;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Risultato extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.risultato);
        txt=findViewById(R.id.tempo);
        float risultato=getIntent().getFloatExtra("Tempo",0);
        txt.setText(Float.toString(risultato));

    }

    public void nuovaPartita(View view) {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
