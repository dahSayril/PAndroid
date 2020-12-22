package it.unisa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomerAdapter extends ArrayAdapter <Contatto> {

    private LayoutInflater inflater;

    public CustomerAdapter(@NonNull Context context, int resource, @NonNull List<Contatto> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            v = inflater.inflate(R.layout.list_element, null);
        }

        Contatto c = getItem(position);

        TextView nomeContatto = (TextView) v.findViewById(R.id.nomeContatto);
        TextView telefonoContatto = (TextView) v.findViewById(R.id.telefonoContatto);
        ImageView immagineContatto = (ImageView) v.findViewById(R.id.immagineContatto);

        nomeContatto.setText(c.getNome());
        telefonoContatto.setText(c.getTelefono());
        if(c.getImmagine() != null) immagineContatto.setImageDrawable(c.getImmagine());

        return v;

    }

}