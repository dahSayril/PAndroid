package com.example.lab_01_27_2017;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int stato=1;
    private String[]  valori;
    private GridView griglia;
    private ArrayAdapter<String> adapter;
    private TextView giocata;
    private TextView pareggio;
    private TextView segno;
    private Button sceltaSegno;
    private Button resetPartita;
    private String segno1="X";
    private String segno2="O";
    private String segnoRisposta;
    private String segnoScelto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        griglia=findViewById(R.id.gridView);
        giocata=findViewById(R.id.giocata);
        pareggio=findViewById(R.id.pareggio);
        segno=findViewById(R.id.Segno);
        sceltaSegno=findViewById(R.id.SceltaSegno);
        resetPartita=findViewById(R.id.Reset);

        segno.setVisibility(View.INVISIBLE);
        sceltaSegno.setVisibility(View.INVISIBLE);
        resetPartita.setVisibility(View.INVISIBLE);

        segnoScelto=segno1;
        segnoRisposta=segno2;
        valori=new String[]{" "," "," "," "," "," "," "," "," "};
        adapter=new ArrayAdapter<String>(this,R.layout.list_element,R.id.textViewList,valori);
        griglia.setAdapter(adapter);

        griglia.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str  = griglia.getItemAtPosition(position).toString();
                if(str.equals(" ")){
                    if(stato%2==0){
                        valori[position]=segnoRisposta;
                        adapter.notifyDataSetChanged();
                        if(vittoria()==true) {
                            showDialog();
                        }
                        stato++;
                        giocata.setText("Ora gioca il segno "+segnoScelto);
                            if(stato==10){
                                giocata.setText("Fine");
                                pareggio.setText("Partita finita in parità");
                                resetPartita.setVisibility(View.VISIBLE);}


                    }
                    else{
                        valori[position]=segnoScelto;
                        adapter.notifyDataSetChanged();
                        if(vittoria()==true) {
                            showDialog();
                        }
                        stato++;
                        giocata.setText("Ora gioca il segno "+segnoRisposta);
                        if(stato==10){
                            giocata.setText("Fine");
                            pareggio.setText("Partita finita in parità");
                            resetPartita.setVisibility(View.VISIBLE);}

                        }
                    }

            }
        });


    }



    public boolean vittoria(){
        if(!valori[0].equals(" ") && valori[1].equals(valori[0]) && valori[2].equals(valori[0])) return true;
        if(!valori[3].equals(" ") && valori[4].equals(valori[3]) && valori[5].equals(valori[3])) return true;
        if(!valori[6].equals(" ") && valori[7].equals(valori[6]) && valori[8].equals(valori[6])) return true;
        if(!valori[0].equals(" ") && valori[3].equals(valori[0]) && valori[6].equals(valori[0])) return true;
        if(!valori[1].equals(" ") && valori[4].equals(valori[1]) && valori[7].equals(valori[1])) return true;
        if(!valori[2].equals(" ") && valori[5].equals(valori[2]) && valori[8].equals(valori[2])) return true;
        if(!valori[0].equals(" ") && valori[4].equals(valori[0]) && valori[8].equals(valori[0])) return true;
        if(!valori[2].equals(" ") && valori[4].equals(valori[2]) && valori[6].equals(valori[2])) return true;
        return false;
    }

    public void  showDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        segno.setVisibility(View.VISIBLE);
                        sceltaSegno.setVisibility(View.VISIBLE);
                        resetPartita.setVisibility(View.VISIBLE);
                        break;

                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(stato%2==0){
        builder.setMessage("Ha vinto il segno "+segnoRisposta)
                .setPositiveButton("Ok", dialogClickListener).show();
            return;
        }
        else{
            builder.setMessage("Ha vinto il segno "+segnoScelto)
                    .setPositiveButton("Ok", dialogClickListener).show();
            return;
        }


    }

    public void reset(View view) {
        for(int i=0;i<9;i++){
            valori[i]=" ";
        }
        segno.setVisibility(View.INVISIBLE);
        sceltaSegno.setVisibility(View.INVISIBLE);
        resetPartita.setVisibility(View.INVISIBLE);
        stato=1;

        pareggio.setText(" ");
        adapter.notifyDataSetChanged();
        segnoScelto=segno.getText().toString();
        if(segno.getText().toString().equals(segno1))
            segnoRisposta=segno2;
        else
            segnoRisposta=segno1;
        giocata.setText("Ora gioca il segno "+segnoScelto);
    }


    public void scegliSegno(View view) {
        if(segno.getText().toString().equals(segno1))
            segno.setText(segno2);
        else
            segno.setText(segno1);
    }
}