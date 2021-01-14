package it.unisa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView risultato, memoria;
    EditText parola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        risultato = (TextView) findViewById(R.id.risultato);
        memoria = (TextView) findViewById(R.id.memoria);
        parola = (EditText) findViewById(R.id.parola);

        if(savedInstanceState != null) {
            risultato.setText(savedInstanceState.getString("risultato"));
            memoria.setText(savedInstanceState.getString("memoria"));
            parola.setText(savedInstanceState.getString("parola"));
        }

    }

    public void start(View view) {

        String str = parola.getText().toString();

        switch(view.getId()) {

            case R.id.conta:
                if(!check(str)) return;
                risultato.setText(str.length() + "");
                break;
            case R.id.inverti:
                if(!check(str)) return;
                risultato.setText(new StringBuilder(str).reverse().toString());
                break;
            case R.id.troncaDX:
                if(!check(str)) return;
                risultato.setText(str.substring(0, str.length() / 2));
                break;
            case R.id.troncaSX:
                if(!check(str)) return;
                risultato.setText(str.substring(str.length() / 2, str.length()));
                break;
            case R.id.raddoppia:
                if(!check(str)) return;
                risultato.setText(str + str);
                break;
            case R.id.memorizza:
                memoria.setText(str);
                Toast.makeText(getApplicationContext(), "Parola salvata in memoria!",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.concatena:
                risultato.setText(str + memoria.getText().toString());
                break;
            case R.id.clearParola:
                parola.setText("");
                Toast.makeText(getApplicationContext(), "Campo parola svuotato!",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.clearMemoria:
                Toast.makeText(getApplicationContext(), "Memoria pulita!",
                        Toast.LENGTH_LONG).show();
                memoria.setText("");
                break;

        }

    }

    public boolean check(String str) {

        if(str.equals("")) {
            parola.setBackgroundResource(R.drawable.backgrounderror);
            Toast.makeText(getApplicationContext(), "Parola non valida!", Toast.LENGTH_LONG).show();
            return false;
        } else parola.setBackgroundResource(R.drawable.background);

        return true;

    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("risultato", risultato.getText().toString());
        bundle.putString("memoria", memoria.getText().toString());
        bundle.putString("parola", parola.getText().toString());
    }

}