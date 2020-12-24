package it.unisa;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Game extends AppCompatActivity {

    Quesito q = null;
    Button b;
    Button b1, b2, b3, b4, b5, b6, b7, b8;
    TextView tv;
    Colore[] colori = new Colore[11];
    String ultimoColore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        b = (Button) findViewById(R.id.colore);
        b1 = (Button) findViewById(R.id.colore1);
        b2 = (Button) findViewById(R.id.colore2);
        b3 = (Button) findViewById(R.id.colore3);
        b4 = (Button) findViewById(R.id.colore4);
        b5 = (Button) findViewById(R.id.colore5);
        b6 = (Button) findViewById(R.id.colore6);
        b7 = (Button) findViewById(R.id.colore7);
        b8 = (Button) findViewById(R.id.colore8);
        tv = (TextView) findViewById(R.id.textView);

        Intent intent = getIntent();
        q = intent.getParcelableExtra("quesito");

        if(q == null) {

            colori[0] = new Colore("Giallo", "#ffff00");
            colori[1] = new Colore("Bianco", "#ffffff");
            colori[2] = new Colore("Argento", "#c0c0c0");
            colori[3] = new Colore("Rosso", "#ff0000");
            colori[4] = new Colore("Viola", "#800080");
            colori[5] = new Colore("Oliva", "#808000");
            colori[6] = new Colore("Lime", "#00ff00");
            colori[7] = new Colore("Verde", "#008000");
            colori[8] = new Colore("Grigio", "#808080");
            colori[9] = new Colore("Fucsia", "#ff00ff");
            colori[10] = new Colore("Blue", "#0000ff");

            q = new Quesito(1, 0, 0,
               "", colori);

        } else {

            Log.d("Debug", q.toString());

            for(int i = 0; i < q.getColori().length; i++) {
                q.getColori()[i].setScelto(false);
            }
            Log.d("Debug", "Setto tutti i colori su non scelti.");


        }

        tv.setText("Domanda n.: " + q.getDomandaCorrente() + "/10");

        Colore c = q.getRandomColor();
        Random r = new Random();
        int posizione = r.nextInt(8);
        b.setBackgroundColor(Color.parseColor(c.getHexCode()));
        ultimoColore = c.getHexCode();
        switch (posizione + 1) {
            case 1: b1.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
            case 2: b2.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
            case 3: b3.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
            case 4: b4.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
            case 5: b5.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
            case 6: b6.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
            case 7: b7.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
            case 8: b8.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
        }

        Log.d("Debug", "In posizione: " + (posizione + 1) + " è stato inserito il colore: "
         + c.getColore());

        for(int i = 1; i < 9; i++) {
            if(i != (posizione + 1)) {
                c = q.getRandomColor();
                switch (i) {
                    case 1: b1.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
                    case 2: b2.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
                    case 3: b3.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
                    case 4: b4.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
                    case 5: b5.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
                    case 6: b6.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
                    case 7: b7.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
                    case 8: b8.setBackgroundColor(Color.parseColor(c.getHexCode())); break;
                }
                Log.d("Debug", "In posizione: " + i + " è stato inserito il colore: "
                        + c.getColore());
            }
        }

    }

    public void clickColore(View v) {
        Button tmp = null;
        switch (v.getId()) {
            case R.id.colore1:
                tmp = findViewById(R.id.colore1);
                break;
            case R.id.colore2:
                tmp = findViewById(R.id.colore2);
                break;
            case R.id.colore3:
                tmp = findViewById(R.id.colore3);
                break;
            case R.id.colore4:
                tmp = findViewById(R.id.colore4);
                break;
            case R.id.colore5:
                tmp = findViewById(R.id.colore5);
                break;
            case R.id.colore6:
                tmp = findViewById(R.id.colore6);
                break;
            case R.id.colore7:
                tmp = findViewById(R.id.colore7);
                break;
            case R.id.colore8:
                tmp = findViewById(R.id.colore8);
                break;
        }
        if(((ColorDrawable) b.getBackground()).getColor() == ((ColorDrawable) tmp.getBackground()).getColor()) {
            Log.d("Debug", "Scelto il colore giusto.");
            Quesito q1 = new Quesito(q.getDomandaCorrente() + 1, q.getDomandeEsatte() + 1,
                q.getDomandeErrate(), ultimoColore, q.getColori());
            Intent i = new Intent();
            if(q1.getDomandaCorrente() <= 10)
                i.setClass(getApplicationContext(), Game.class);
            else
                i.setClass(getApplicationContext(), Finish.class);
            i.putExtra("quesito", q1);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(i);
            this.finish();
        } else {
            Log.d("Debug", "Scelto il colore sbagliato.");
            Quesito q1 = new Quesito(q.getDomandaCorrente() + 1, q.getDomandeEsatte(),
                    q.getDomandeErrate() + 1, ultimoColore, q.getColori());
            Intent i = new Intent();
            if(q1.getDomandaCorrente() <= 10)
                i.setClass(getApplicationContext(), Game.class);
            else
                i.setClass(getApplicationContext(), Finish.class);
            i.putExtra("quesito", q1);
            i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(i);
            this.finish();
        }
    }
}
