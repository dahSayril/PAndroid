package com.example.trisesame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


public class Tris extends Fragment {
    private String giocatore;
    private GridView griglia;
    private ArrayAdapter<String> adapter;
    private String[] valori;
    private comunicaGiocata listener=null;

    public Tris (String giocatore){
        this.giocatore=giocatore;
    }

    public interface comunicaGiocata{
        public void setGiocata(Tris t,String gioca,int posizione);
    }

    public void getGiocata(Tris t,String giocata,int pos){
        if(pos!=-1)
        t.duplicaGiocata(giocata,pos);
        else
            t.reset();
        t.attivazione();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment, container, false);
        griglia=v.findViewById(R.id.gridView);
        valori=new String[]{" "," "," "," "," "," "," "," "," "};
        adapter=new ArrayAdapter<String>(getActivity(),R.layout.list_element,R.id.textViewList,valori);
        griglia.setAdapter(adapter);

        griglia.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str=griglia.getItemAtPosition(position).toString();
                if(str.equals(" ")){
                    Tris t = null;
                    FragmentManager fm = getFragmentManager();
                    if(giocatore.equals("Giocatore X")){
                        t = (Tris) fm.findFragmentById(R.id.frameTris1);
                        valori[position]="X";
                        disattivazione();
                        if(vittoria()){
                            showDialog();
                        }
                        if(pareggio()){
                            reset();
                            position=-1;
                        }
                        listener.setGiocata(t,"X",position);
                    }
                    else{
                        t = (Tris) fm.findFragmentById(R.id.frameTris0);
                        valori[position]="O";
                        disattivazione();
                        if(vittoria()){
                            showDialog();
                        }
                        if(pareggio()){
                            reset();
                            position=-1;
                        }
                        listener.setGiocata(t,"O",position);

                    }

                    adapter.notifyDataSetChanged();
                }

            }
        });


        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (comunicaGiocata) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement comunicoGiocata");
        }
    }

    public void duplicaGiocata(String giocata, int posizione) {
            valori[posizione]=giocata;
            adapter.notifyDataSetChanged();
    }

    public void attivazione(){
        griglia.setEnabled(true);
    }

    public void disattivazione(){
        griglia.setEnabled(false);
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

    public void reset(){
        for(int i=0;i<9;i++)
            valori[i]=" ";
        adapter.notifyDataSetChanged();
    }

    public boolean pareggio(){
        for(int i=0;i<9;i++)
            if(valori[i].equals(" "))
                return false;
            return true;
    }

    public void  showDialog() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Tris t = null;
                        FragmentManager fm = getFragmentManager();
                        if(giocatore.equals("Giocatore X")){
                            t = (Tris) fm.findFragmentById(R.id.frameTris1);
                            reset();
                            listener.setGiocata(t,"X",-1);
                        }else{
                            t = (Tris) fm.findFragmentById(R.id.frameTris0);
                            reset();
                            listener.setGiocata(t,"X",-1);
                        }

                        break;

                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(giocatore.equals("Giocatore X")){
            builder.setMessage("Ha vinto il segno X")
                    .setPositiveButton("Ok", dialogClickListener).show();
            return;
        }
        else{
            builder.setMessage("Ha vinto il segno O")
                    .setPositiveButton("Ok", dialogClickListener).show();
            return;
        }


    }


}
