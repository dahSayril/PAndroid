package it.unisa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tris X = new Tris("Giocatore X");
        Tris O = new Tris("Giocatore O");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.tris1, X);
        ft.add(R.id.tris2, O);
        ft.commit();

    }

    public void finish(String player) {
        Intent i = new Intent();
        i.setClass(getApplicationContext(), Finish.class);
        i.putExtra("result", player);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        this.finish();
    }

}