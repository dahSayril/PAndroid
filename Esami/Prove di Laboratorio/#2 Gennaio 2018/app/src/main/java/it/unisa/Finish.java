package it.unisa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Finish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        Intent i = getIntent();
        String result = i.getStringExtra("result");

        TextView tv = (TextView) findViewById(R.id.tvWin);
        tv.setText("Ha vinto il giocatore: " + ((result.equals("X")) ? "X" : "O"));

    }

    public void start(View v) {
        Intent i = new Intent();
        i.setClass(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
        this.finish();
    }

}
