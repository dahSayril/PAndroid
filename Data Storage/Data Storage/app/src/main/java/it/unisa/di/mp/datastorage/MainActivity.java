package it.unisa.di.mp.datastorage;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MainActivity extends Activity {
    EditText filename;
    EditText sourcetext;
    TextView tv_sdcardStatus;
    TextView tv_operationStatus;
    RadioButton rb_private_internal_env;
    RadioButton rb_private_external_env;
    RadioButton rb_public_external_env;
    RadioButton rb_private_internal_context;
    RadioButton rb_private_external_context;
    RadioButton rb_public_external_context;

    String BASEPATH = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //Size
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenw_px = size.x;
        //Densità
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float density = metrics.density;

        int textSize = (int)(screenw_px/(30*density));

        filename = (EditText) findViewById(R.id.textFileName);
        sourcetext = (EditText) findViewById(R.id.textEditor);
        tv_sdcardStatus = (TextView) findViewById(R.id.tv_sdcardStatus);
        tv_operationStatus = (TextView) findViewById(R.id.tv_operationStatus);
        rb_private_internal_env = (RadioButton) findViewById(R.id.rb_private_internal_env);
        rb_private_external_env = (RadioButton) findViewById(R.id.rb_private_external_env);
        rb_public_external_env = (RadioButton) findViewById(R.id.rb_public_external_env);
        rb_private_internal_context = (RadioButton) findViewById(R.id.rb_private_internal_context);
        rb_private_external_context = (RadioButton) findViewById(R.id.rb_private_external_context);
        rb_public_external_context = (RadioButton) findViewById(R.id.rb_public_external_context);

        filename.setTextSize(textSize);
        sourcetext.setTextSize(textSize);
        tv_sdcardStatus.setTextSize(textSize);
        tv_operationStatus.setTextSize(textSize);
        rb_private_internal_env.setTextSize(textSize);
        rb_private_external_env.setTextSize(textSize);
        rb_public_external_env.setTextSize(textSize);
        rb_private_internal_context.setTextSize(textSize);
        rb_private_external_context.setTextSize(textSize);
        rb_public_external_context.setTextSize(textSize);



        if (isExternalStorageWritable()) {
            tv_sdcardStatus.setText("External Storage is readable and writable");
        } else if (isExternalStorageReadable()) {
            tv_sdcardStatus.setText("External Storage is only readable");
        } else {
            tv_sdcardStatus.setText("External Storage is not available");
        }

        String str;
        str = Environment.getDataDirectory().toString();
        rb_private_internal_env.setText("Private internal path (Environ): " + str);
        str = getApplicationContext().getFilesDir().toString();
        rb_private_internal_context.setText("Private internal path (Context): " + str);
        str = Environment.getExternalStorageDirectory().toString();
        rb_private_external_env.setText("Private external path (Environ): " + str);
        str = getApplicationContext().getExternalFilesDir("").toString();
        rb_private_external_context.setText("Private external path (Context): " + str);
        str = Environment.getExternalStoragePublicDirectory("").toString();
        rb_public_external_env.setText("Public path (Environ): " + str);
        //Richiede API 19
        str = getApplicationContext().getExternalFilesDir("").toString();
        rb_public_external_context.setText("Public path (Context): "+str);
        //rb_public_external_context.setText("Richiede API 19");
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public void onRadioButtonClicked(View v) {
        RadioButton rb = (RadioButton) v;
        tv_operationStatus.setText("");
        switch (rb.getId()) {
            case R.id.rb_private_internal_env:
                BASEPATH = Environment.getDataDirectory().toString();
                break;
            case R.id.rb_private_internal_context:
                BASEPATH = getApplicationContext().getFilesDir().toString();
                break;
            case R.id.rb_private_external_env:
                BASEPATH = Environment.getExternalStorageDirectory().toString();;
                break;
            case R.id.rb_private_external_context:
                BASEPATH = getApplicationContext().getExternalFilesDir("").toString();
                break;
            case R.id.rb_public_external_env:
                BASEPATH = Environment.getExternalStoragePublicDirectory("").toString();
                break;
            case R.id.rb_public_external_context:
                BASEPATH = getApplicationContext().getExternalFilesDir("").toString();
                break;
        }
    }

    public void buttonReset(View v) {
        sourcetext.setText("");
        tv_operationStatus.setText("");
    }

    public void buttonSave(View v) {
        tv_operationStatus.setText("Save button pressed");
        String str = BASEPATH + "/" + filename.getText().toString();
        String msg;
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(str), "utf-8"));
            writer.write(sourcetext.getText().toString());
            writer.close();
            msg = "File " + str + " saved!";
        } catch (FileNotFoundException e) {
            msg = "File " + str + " not found!";
        } catch (IOException e) {
            msg = "Save IOException";
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        tv_operationStatus.setText(msg);
    }

    public void buttonLoad(View v) {
        tv_operationStatus.setText("Load button pressed");
        String str = BASEPATH + "/" + filename.getText().toString();
        FileInputStream fis = null;
        String msg;
        try {
            BufferedReader br = new BufferedReader(new FileReader(str));
            String line;
            String data = "";
            while (null != (line = br.readLine())) {
                data += line + "\n";
            }
            br.close();
            sourcetext.setText(data);
            msg = "File " + str + " loaded.";
        } catch (FileNotFoundException e) {
            msg = "File " + str + " not found!";
        } catch (IOException e) {
            msg = "Load IOException";
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        tv_operationStatus.setText(msg);
    }

}
