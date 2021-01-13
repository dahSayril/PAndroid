package com.example.lab_2017_07_12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView griglia;
    LinearLayout linear;
    EditText input;
    ArrayAdapter<String> adapter;
    int posizione;
    int n;
    String[]  valori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        griglia=findViewById(R.id.gridView);
        linear=findViewById(R.id.inserimento);
        input=findViewById(R.id.input);
        linear.setVisibility(View.INVISIBLE);
        valori=new String[]{"1","2","3"," ","8"," ","1","4"," "};
        adapter=new ArrayAdapter<String>(this,R.layout.list_element,R.id.textViewList,valori);
        griglia.setAdapter(adapter);


        griglia.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str  = griglia.getItemAtPosition(position).toString();
                if(str.equals(" ")){
                    linear.setVisibility(View.VISIBLE);
                    posizione=position;
                }else{
                    String numero=valori[position];
                    int selezione=(int) Integer.parseInt(numero);
                    for(int i=0;i<9;i++){
                        if(!valori[i].equals(" ")){
                            String numeroX=valori[i];
                            n=(int) Integer.parseInt(numeroX);
                            n=(n+selezione)%10;
                            valori[i] = Integer.toString(n);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void inserimentoNum(View view) {
        EditText txt=findViewById(R.id.input);
        int x=Integer.parseInt(txt.getText().toString());
        if(x<0 || x>9)
            Toast.makeText(getApplicationContext(), "Inserire un numero compreso tra 0 e 9", Toast.LENGTH_LONG).show();
        else{
            valori[posizione]=Integer.toString(x);
            adapter.notifyDataSetChanged();
            txt.setText("");
            linear.setVisibility(View.INVISIBLE);
            }
    }

    public void azzera(View view) {
        valori[0]="1";
        valori[1]="2";
        valori[2]="3";
        valori[3]=" ";
        valori[4]="8";
        valori[5]=" ";
        valori[6]="1";
        valori[7]="4";
        valori[8]=" ";
        adapter.notifyDataSetChanged();
    }
}