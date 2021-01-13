package com.example.preappellogennaio2017;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView stringa, numero;
    private String str, num;
    SharedPreferences prefs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("File",MODE_PRIVATE);
        stringa=findViewById(R.id.casellaStringa);
        numero=findViewById(R.id.casellaNumeri);
        stringa.setText(prefs.getString("ValoreActivityA"," "));
        numero.setText(prefs.getString("ValoreActivityB"," "));
    }

    public void vaiAdA(View view){
        Intent i = new Intent();
        i.setClass(this, ActivityA.class);
        startActivityForResult(i, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (data == null) return;
        switch(requestCode) {
            case 0:
                stringa.setText(str=data.getStringExtra("String:"));
                break;
            case 1:
                numero.setText(num=data.getStringExtra("Numero:"));
                break;
        }
    }
            
    public void vaiAdB(View view){
        Intent i = new Intent(this, ActivityB.class);
        startActivityForResult(i, 1);
    }

    public void onSaveInstanceState(Bundle outState) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ValoreActivityA",stringa.getText().toString());
        editor.putString("ValoreActivityB",numero.getText().toString());
        editor.commit();
        super.onSaveInstanceState(outState);
    }
}