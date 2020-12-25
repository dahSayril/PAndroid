package it.unisa;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity {

    TextView nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        nome = (TextView) findViewById(R.id.nomeTv);
    }

    public void startGame(View v) {

        String regEx = "^[\\p{L} .'-]+$";;
        String sNome = nome.getText().toString();

        if(!sNome.matches(regEx))
            nome.setBackgroundResource(R.drawable.errorborder);

    }

}
