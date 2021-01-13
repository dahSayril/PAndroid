package com.example.lab_2017_02_13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView n_domanda;
    TextView riposte_corrette;
    TextView cercaColore;
    GridView griglia;
    CustomAdapter customAdapter;
    Colore c,c0,c1,c2,c3,c4,c5,c6,c7;
    int counter_domanda=1;
    int risposteCorrette=0;
    int coloreCorrente;
    int coloreScelto=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cercaColore=findViewById(R.id.colore);
        griglia=findViewById(R.id.gridView);
        n_domanda=findViewById(R.id.n_domanda);
        riposte_corrette=findViewById(R.id.rispostaCorretta);
        coloreCorrente=Color.RED;
        cercaColore.setBackgroundColor(coloreCorrente);
        n_domanda.setText("Domanda n"+counter_domanda+"/10");
        c0=new Colore(Color.BLACK);
        c1=new  Colore(Color.BLUE);
        c2=new Colore(Color.RED);
        c3=new Colore(Color.WHITE);
        c4=new Colore(Color.GREEN);
        c5=new Colore(Color.YELLOW);
        c6=new Colore(Color.CYAN);
        c7=new Colore(Color.MAGENTA);

        ArrayList<Colore> elementi=new ArrayList<Colore>();


        customAdapter=new CustomAdapter(this,R.layout.list_element,elementi);
        griglia.setAdapter(customAdapter);
        customAdapter.add(c0);
        customAdapter.add(c1);
        customAdapter.add(c2);
        customAdapter.add(c3);
        customAdapter.add(c4);
        customAdapter.add(c5);
        customAdapter.add(c6);
        customAdapter.add(c7);


        griglia.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                c  = (Colore) griglia.getItemAtPosition(position);
                if(c.getColore()==coloreCorrente){
                    risposteCorrette++;
                    counter_domanda++;
                }else{
                    counter_domanda++;
                }
                do {
                    int coloreRandom = (int) (Math.random() * 8);
                    switch (coloreRandom) {
                        case 0:
                            coloreScelto = Color.RED;
                            break;
                        case 1:
                            coloreScelto = Color.BLACK;
                            break;
                        case 2:
                            coloreScelto = Color.BLUE;
                            break;
                        case 3:
                            coloreScelto = Color.MAGENTA;
                            break;
                        case 4:
                            coloreScelto = Color.YELLOW;
                            break;
                        case 5:
                            coloreScelto = Color.GREEN;
                            break;
                        case 6:
                            coloreScelto = Color.WHITE;
                            break;
                        case 7:
                            coloreScelto = Color.CYAN;
                            break;

                    }
                }while (coloreScelto==coloreCorrente);
                cercaColore.setBackgroundColor(coloreScelto);
                coloreCorrente=coloreScelto;
                n_domanda.setText("Domanda n"+counter_domanda+"/10");
                riposte_corrette.setText(" Risposte Corrette: "+risposteCorrette);
                if(counter_domanda>10){
                    showDialog();
                    counter_domanda=1;
                    risposteCorrette=0;
                }

            }
        });

    }

    public void  showDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        break;

                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Fine").setPositiveButton("Ok", dialogClickListener).show();
            return;



    }

}