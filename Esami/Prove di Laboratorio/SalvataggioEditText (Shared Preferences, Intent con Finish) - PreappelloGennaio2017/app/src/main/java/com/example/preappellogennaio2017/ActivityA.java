package com.example.preappellogennaio2017;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.app.Activity;
import android.widget.Toast;

public class ActivityA extends Activity {
    private EditText stringa;
    private String str;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        stringa = findViewById(R.id.casellaStringa);
    }

    public void vaiMain (View view) {
        Intent data = new Intent(getApplicationContext(), MainActivity.class);
        str = stringa.getText().toString();
        if (str.contains("1") | str.contains("2") | str.contains("3") | str.contains("4") | str.contains("5") | str.contains("6") | str.contains("7") | str.contains("8") | str.contains("9") | str.contains("0")) {
            Toast.makeText(getApplicationContext(), "Inserire solo lettere!", Toast.LENGTH_LONG).show();
            stringa.setText("");
        }
        else {
            data.putExtra("String:", str);
            setResult(RESULT_OK, data);
            this.finish();
        }
    }
}
