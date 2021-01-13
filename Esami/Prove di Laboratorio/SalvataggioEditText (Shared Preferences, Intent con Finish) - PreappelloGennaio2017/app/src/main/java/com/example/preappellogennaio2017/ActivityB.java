package com.example.preappellogennaio2017;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityB extends Activity {
    private String num;
    private EditText numero;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        numero = findViewById(R.id.casellaNumeri);
    }

    public void vaiAlMain(View view) {
        Intent data = new Intent(getApplicationContext(), MainActivity.class);
        num= numero.getText().toString();
        if (!num.contains ("1") && !num.contains("2") && !num.contains("3") && !num.contains("4") && !num.contains("5") && !num.contains("6") && !num.contains("7") && !num.contains("8") && !num.contains("9") && !num.contains("0")){
            Toast.makeText(getApplicationContext(), "Inserire solo numeri!", Toast.LENGTH_LONG).show();
            numero.setText(" ");
        }
        else {
            data.putExtra("Numero:", num);
            setResult(RESULT_OK, data);
            this.finish();
        }

    }

}

