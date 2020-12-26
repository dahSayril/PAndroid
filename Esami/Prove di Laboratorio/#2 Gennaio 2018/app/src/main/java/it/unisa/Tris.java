package it.unisa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Tris extends Fragment {

    public Tris(String nome) {
        this.nome = nome;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tris, container, false);

        uno = (Button) rootView.findViewById(R.id.uno);
        due = (Button) rootView.findViewById(R.id.due);
        tre = (Button) rootView.findViewById(R.id.tre);
        quattro = (Button) rootView.findViewById(R.id.quattro);
        cinque = (Button) rootView.findViewById(R.id.cinque);
        sei = (Button) rootView.findViewById(R.id.sei);
        sette = (Button) rootView.findViewById(R.id.sette);
        otto = (Button) rootView.findViewById(R.id.otto);
        nove = (Button) rootView.findViewById(R.id.nove);

        uno.setOnClickListener(new MyListener());
        due.setOnClickListener(new MyListener());
        tre.setOnClickListener(new MyListener());
        quattro.setOnClickListener(new MyListener());
        cinque.setOnClickListener(new MyListener());
        sei.setOnClickListener(new MyListener());
        sette.setOnClickListener(new MyListener());
        otto.setOnClickListener(new MyListener());
        nove.setOnClickListener(new MyListener());

        return rootView;
    }

    public void duplicaGiocata(String giocata, int posizione) {
        switch(posizione) {
            case 1: uno.setText(giocata); break;
            case 2: due.setText(giocata); break;
            case 3: tre.setText(giocata); break;
            case 4: quattro.setText(giocata); break;
            case 5: cinque.setText(giocata); break;
            case 6: sei.setText(giocata); break;
            case 7: sette.setText(giocata); break;
            case 8: otto.setText(giocata); break;
            case 9: nove.setText(giocata); break;
        }
    }

    public void attiva() {
        uno.setEnabled(true);
        due.setEnabled(true);
        tre.setEnabled(true);
        quattro.setEnabled(true);
        cinque.setEnabled(true);
        sei.setEnabled(true);
        sette.setEnabled(true);
        otto.setEnabled(true);
        nove.setEnabled(true);
    }

    public void disattiva() {
        uno.setEnabled(false);
        due.setEnabled(false);
        tre.setEnabled(false);
        quattro.setEnabled(false);
        cinque.setEnabled(false);
        sei.setEnabled(false);
        sette.setEnabled(false);
        otto.setEnabled(false);
        nove.setEnabled(false);
    }

    public String controllaVincita() {
        if(nome.equals("Giocatore X")) {
            if(uno.getText().toString().equals("X")) {
                if(due.getText().toString().equals("X") && tre.getText().toString().equals("X"))
                    return "X";
                if(quattro.getText().toString().equals("X") && sette.getText().toString().equals("X"))
                    return "X";
                if(cinque.getText().toString().equals("X") && nove.getText().toString().equals("X"))
                    return "X";;
            } else if(cinque.getText().toString().equals("X")) {
                if (due.getText().toString().equals(("X")) && otto.getText().toString().equals("X"))
                    return "X";
                if (quattro.getText().toString().equals("X") && sei.getText().toString().equals("X"))
                    return "X";
                if (sette.getText().toString().equals("X") && tre.getText().toString().equals("X"))
                    return "X";
            } else if(nove.getText().toString().equals("X")) {
                if(tre.getText().toString().equals("X") && sei.getText().toString().equals("X"))
                    return "X";
                if(sette.getText().toString().equals("X") && otto.getText().toString().equals("X"))
                    return "X";
            }
            return null;
        } else {
            if(uno.getText().toString().equals("O")) {
                if(due.getText().toString().equals("O") && tre.getText().toString().equals("O"))
                    return "O";
                if(quattro.getText().toString().equals("O") && sette.getText().toString().equals("O"))
                    return "O";
                if(cinque.getText().toString().equals("O") && nove.getText().toString().equals("O"))
                    return "O";
            } else if(cinque.getText().toString().equals("O")) {
                if (due.getText().toString().equals(("O")) && otto.getText().toString().equals("O"))
                    return "O";
                if (quattro.getText().toString().equals("O") && sei.getText().toString().equals("O"))
                    return "O";
                if (sette.getText().toString().equals("O") && tre.getText().toString().equals("O"))
                    return "O";
            } else if(nove.getText().toString().equals("O")) {
                if(tre.getText().toString().equals("O") && sei.getText().toString().equals("O"))
                    return "O";
                if(sette.getText().toString().equals("O") && otto.getText().toString().equals("O"))
                    return "O";
            }
            return null;
        }
    }

    public class MyListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            Tris t = null;
            FragmentManager fm = getFragmentManager();
            if(nome.equals("Giocatore X"))
                t = (Tris) fm.findFragmentById(R.id.tris2);
            else
                t = (Tris) fm.findFragmentById(R.id.tris1);

            switch(v.getId()) {
                case R.id.uno:
                    if(nome.equals("Giocatore X")) {
                        uno.setText("X");
                        t.duplicaGiocata("X", 1);
                    } else {
                        uno.setText("O");
                        t.duplicaGiocata("O", 1);
                    }
                    break ;
                case R.id.due:
                    if(nome.equals("Giocatore X")) {
                        due.setText("X");
                        t.duplicaGiocata("X", 2);
                    } else {
                        due.setText("O");
                        t.duplicaGiocata("O", 2);
                    }
                    break ;
                case R.id.tre:
                    if(nome.equals("Giocatore X")) {
                        tre.setText("X");
                        t.duplicaGiocata("X", 3);
                    } else {
                        tre.setText("O");
                        t.duplicaGiocata("O", 3);
                    }
                    break ;
                case R.id.quattro:
                    if(nome.equals("Giocatore X")) {
                        quattro.setText("X");
                        t.duplicaGiocata("X", 4);
                    } else {
                        quattro.setText("O");
                        t.duplicaGiocata("O", 4);
                    }
                    break ;
                case R.id.cinque:
                    if(nome.equals("Giocatore X")) {
                        cinque.setText("X");
                        t.duplicaGiocata("X", 5);
                    } else {
                        cinque.setText("O");
                        t.duplicaGiocata("O", 5);
                    }
                    break ;
                case R.id.sei:
                    if(nome.equals("Giocatore X")) {
                        sei.setText("X");
                        t.duplicaGiocata("X", 6);
                    } else {
                        sei.setText("O");
                        t.duplicaGiocata("O", 6);
                    }
                    break ;
                case R.id.sette:
                    if(nome.equals("Giocatore X")) {
                        sette.setText("X");
                        t.duplicaGiocata("X", 7);
                    } else {
                        sette.setText("O");
                        t.duplicaGiocata("O", 7);
                    }
                    break ;
                case R.id.otto:
                    if(nome.equals("Giocatore X")) {
                        otto.setText("X");
                        t.duplicaGiocata("X", 8);
                    } else {
                        otto.setText("O");
                        t.duplicaGiocata("O", 8);
                    }
                    break ;
                case R.id.nove:
                    if(nome.equals("Giocatore X")) {
                        nove.setText("X");
                        t.duplicaGiocata("X", 9);
                    } else {
                        nove.setText("O");
                        t.duplicaGiocata("O", 9);
                    }
                    break ;
            }

            String result = controllaVincita();

            if(result == null) {
                disattiva();
                t.attiva();
                return;
            } else {
                ((MainActivity) getActivity()).finish(result);
                return;
            }

        }

    }

    private String nome;
    private Button uno, due, tre, quattro, cinque, sei, sette, otto, nove;
}