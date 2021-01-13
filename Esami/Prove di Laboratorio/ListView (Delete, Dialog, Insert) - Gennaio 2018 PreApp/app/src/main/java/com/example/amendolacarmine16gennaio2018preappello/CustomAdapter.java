package com.example.amendolacarmine16gennaio2018preappello;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

    public class CustomAdapter extends ArrayAdapter<Contatto> {
        private LayoutInflater inflater;

        public CustomAdapter(Context context, int resourceId, List<Contatto> objects) {
            super(context, resourceId, objects);
            inflater = LayoutInflater.from(context);
        }

        public View getView(int position, View v, ViewGroup parent) {
            if (v == null) {
                v = inflater.inflate(R.layout.list_element, null);
            }

            Contatto c = getItem(position);

            ImageView profile;
            TextView phoneNumber;
            TextView name;
            TextView cognome;

            profile = (ImageView) v.findViewById(R.id.profile);
            phoneNumber = (TextView) v.findViewById(R.id.phoneNumber);
            name = (TextView) v.findViewById(R.id.name);
            cognome = (TextView) v.findViewById(R.id.cognome);

            profile.setImageDrawable(c.getProfilePic());
            phoneNumber.setText(c.getTelefono());
            name.setText(c.getNome());
            cognome.setText(c.getCognome());

            profile.setTag(position);
            phoneNumber.setTag(position);
            name.setTag(position);
            cognome.setTag(position);

            return v;
        }


}
