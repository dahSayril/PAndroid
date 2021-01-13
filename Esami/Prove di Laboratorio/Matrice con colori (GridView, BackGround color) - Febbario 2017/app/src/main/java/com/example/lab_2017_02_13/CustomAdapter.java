package com.example.lab_2017_02_13;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.R.color.black;


public class CustomAdapter extends ArrayAdapter<Colore> {

    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resource, List<Colore> object) {
        super(context, resource);
        inflater=LayoutInflater.from(context);
    }

    public View getView(int position, View v, ViewGroup parent){
        if(v==null){
            v=inflater.inflate(R.layout.list_element,null);
         }
        Colore c = getItem(position);
        TextView colore=(TextView) v.findViewById(R.id.list_element);;
        int coloreSet=c.getColore();
        colore.setBackgroundColor(coloreSet);
        return v;
    }
}
