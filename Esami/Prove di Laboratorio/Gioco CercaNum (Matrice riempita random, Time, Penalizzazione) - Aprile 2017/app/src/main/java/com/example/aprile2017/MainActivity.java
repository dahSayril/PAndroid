package com.example.aprile2017;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    private GridView griglia;
    private TextView txt;
    private int numero;
    private Long inizio;
    private Integer[] vettore=new Integer[9];
    private ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        griglia=findViewById(R.id.tabella);
        txt= findViewById(R.id.Number);

        numero=1;//Stato gioco
        txt.setText(Integer.toString(numero));
        inizio=getTime();//tempo inizio Activity
        vettore=genMatrice(); //matrice numeri

        adapter= new ArrayAdapter<Integer>(this, R.layout.list_element, R.id.elemento, vettore);
        griglia.setAdapter(adapter);
        griglia.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String str  = griglia.getItemAtPosition(position).toString();
                int num=Integer.parseInt(str);
                if(num==Integer.parseInt(txt.getText().toString())) { //numero selezionato corretto
                    numero=numero+1;//avanzo lo stato dell'gioco
                    if(numero==10){//il gioco arriva alla fine
                       Long tempoImpiegato=getTime()-inizio; //tempo durata gioco
                       Intent i=new Intent(getApplicationContext(),Risultato.class);
                       i.putExtra("Tempo",conversioneSecondi(tempoImpiegato));
                       startActivity(i);
                    }else{
                    txt.setText(Integer.toString(numero));
                    vettore=genMatrice();
                    adapter.notifyDataSetChanged();}
                }
                else
                    inizio=inizio-1000; //penalizzazione di un secondo

            }
        });
    }





    public Integer[] genMatrice(){
        //Riempio il vettore
        for(int j=0;j<9;j++)
            vettore[j]=11;

        int i=0;
        while(i<9) {
            int y = (int) (Math.random() * 9+1);
            if (checkNum(y) == false) {
                vettore[i] = y;
                i++;
            }
        }
        return vettore;
    }

    public boolean checkNum(int num){
        for(int i=0; i<9; i++){
            if (vettore[i] == num)
                return true;
        }
        return false;
    }

    public Long getTime(){
        Time time = new Time();
        time.setToNow();
        return time.toMillis(false);
    }

    public float conversioneSecondi(Long i){
         float tempo=i/1000;
         return tempo;
    }

}
