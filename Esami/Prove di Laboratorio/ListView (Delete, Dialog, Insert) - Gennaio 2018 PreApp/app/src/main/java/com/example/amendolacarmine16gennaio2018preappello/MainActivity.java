package com.example.amendolacarmine16gennaio2018preappello;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ListView listView;
    public CustomAdapter customAdapter;
    public Contatto c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] nomi = {"Pasquale", "Maria", "Michele", "Antonella", "Vincenzo"};
        String[] cognomi = {"Rossi", "Maria", "Michele", "Antonella", "Vincenzo"};

        listView = (ListView) findViewById(R.id.Contatti);
        customAdapter = new CustomAdapter(this, R.layout.list_element, new ArrayList<Contatto>());
        listView.setAdapter(customAdapter);

        for (int i = 0; i < nomi.length; i++) {
            Contatto c = new Contatto(
                    "111-2222-333", nomi[i], cognomi[i],
                    getResources().getDrawable(R.drawable.faceplaceholder));
            customAdapter.add(c);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                c  = (Contatto) listView.getItemAtPosition(position);
                showDialog(c);
            }
        });
    }

    public void addContatto(View v){
        EditText nome = findViewById(R.id.addName);
        EditText cognome =  findViewById(R.id.addCognome);
        EditText telefono = findViewById(R.id.addTelefono);
        String name = nome.getText().toString();
        String surname = cognome.getText().toString();
        String phoneNumber = telefono.getText().toString();

        c = new Contatto(phoneNumber+" ",name+" ",surname, getResources().getDrawable(R.drawable.faceplaceholder));
        customAdapter.add(c);
    }

    public void showDialog(final Contatto c) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getApplicationContext(), "Ok!", Toast.LENGTH_LONG).show();
                        customAdapter.remove(c);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(getApplicationContext(),     "Azione annullata", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Eliminare il contatto?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
        return;
    }


}