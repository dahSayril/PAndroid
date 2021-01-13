package com.example.trisesame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Tris.comunicaGiocata {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Tris X = new Tris("Giocatore X");
        Tris O=new Tris("Giocatore O");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frameTris0, X);
        ft.add(R.id.frameTris1, O);
        ft.commit();


    }

    @Override
    public void setGiocata(Tris t, String gioca, int posizione) {
        t.getGiocata(t,gioca,posizione);
    }
}