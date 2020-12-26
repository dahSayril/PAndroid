package it.unisa;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public ListView listView;
    public TextView nome, cognome, telefono;
    public CustomerAdapter customerAdapter;
    public ArrayList <Contatto> contatti = new ArrayList <Contatto> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerAdapter =
                new CustomerAdapter(this, R.layout.list_element, new ArrayList <Contatto> ());

        listView = findViewById(R.id.listaContatti);
        nome = (TextView) findViewById(R.id.nome);
        cognome = (TextView) findViewById(R.id.cognome);
        telefono = (TextView) findViewById(R.id.telefono);
        listView.setAdapter(customerAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                customerAdapter.remove(contatti.remove(position));
                Toast.makeText(getApplicationContext(), "Contatto rimosso.", Toast.LENGTH_LONG)
                        .show();
            }

        });

        if(savedInstanceState != null) {

            Log.d("DEBUG", "savedInstanceState != null");

            nome.setText(savedInstanceState.getString("nome"));
            cognome.setText(savedInstanceState.getString("cognome"));
            telefono.setText(savedInstanceState.getString("telefono"));
            contatti = savedInstanceState.getParcelableArrayList("contatti");

            for(Contatto c : contatti)
                customerAdapter.add(c);

        } else {

            Log.d("DEBUG", "savedInstanceState == null");


            Contatto c1 = new Contatto(
                    "Mario Rossi", "333112233", null
            );

            Contatto c2 = new Contatto(
                    "Giuseppe Verdi", "333112233", null
            );

            Contatto c3 = new Contatto(
                    "Antonio Bianco", "333112233", null
            );

            customerAdapter.add(c1);
            customerAdapter.add(c2);
            customerAdapter.add(c3);
            contatti.add(c1);
            contatti.add(c2);
            contatti.add(c3);

            listView.setAdapter(customerAdapter);

        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("nome", nome.getText().toString());
        savedInstanceState.putString("cognome", cognome.getText().toString());
        savedInstanceState.putString("telefono", telefono.getText().toString());
        savedInstanceState.putParcelableArrayList("contatti", contatti);
        super.onCreate(savedInstanceState);
    }

    public void aggiungiContatto(View v) {

        String nomeString = nome.getText().toString();
        if(nomeString == null || nomeString.equals("")) {
            Toast.makeText(getApplicationContext(), "Nome inserito non valido.", Toast.LENGTH_LONG)
                .show();
            return ;
        }
        String cognomeString = cognome.getText().toString();
        if(cognomeString == null || cognomeString.equals("")) {
            Toast.makeText(getApplicationContext(), "Cognome inserito non valido.", Toast.LENGTH_LONG)
                    .show();
            return ;
        }
        String telefonoString = telefono.getText().toString();
        if(telefonoString == null || telefonoString.equals("")) {
            Toast.makeText(getApplicationContext(), "Telefono inserito non valido.", Toast.LENGTH_LONG)
                    .show();
            return ;
        }

        Contatto tmp = new Contatto(nomeString + " " + cognomeString, telefonoString, null);
        nome.setText("");
        cognome.setText("");
        telefono.setText("");

        customerAdapter.add(tmp);
        contatti.add(tmp);
        Toast.makeText(getApplicationContext(), "Contatto aggiunto.", Toast.LENGTH_LONG)
                .show();

    }

}