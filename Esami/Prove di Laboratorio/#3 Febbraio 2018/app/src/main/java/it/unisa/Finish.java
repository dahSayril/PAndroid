package it.unisa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Finish extends AppCompatActivity {

    TextView esatte, errate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);
        Intent i = getIntent();
        Quesito q = i.getParcelableExtra("quesito");

        esatte = (TextView) findViewById(R.id.domandeEsatte);
        errate = (TextView) findViewById(R.id.domandeErrate);

        esatte.setText("Domande esatte " + q.getDomandeEsatte());
        errate.setText("Domande errate: " + q.getDomandeErrate());

    }

    public void startNewGame(View v) {
        Intent i = new Intent();
        i.setClass(getApplicationContext(), Game.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        this.finish();
    }

}
