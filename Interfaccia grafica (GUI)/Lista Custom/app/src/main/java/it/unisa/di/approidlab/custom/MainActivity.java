package it.unisa.di.approidlab.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
	public ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		String[] nomi = {"Pasquale","Maria","Michele","Antonella", "Vincenzo",
				"Teresa", "Roberto", "Rossella", "Antonio", "Luca", "Liliana", "Stefania",
				"Francesca", "Andrea", "Marco", "Elisa", "Anna", "Lorenzo"};
	
        
		listView = findViewById(R.id.mylistview);
		
		CustomAdapter customAdapter =
                new CustomAdapter(this, R.layout.list_element, new ArrayList<Contatto>());

        listView.setAdapter(customAdapter);


        for (int i=0; i<nomi.length; i++) {
        	Contatto c = new Contatto(
        			nomi[i],
        			"111-2222-333",
        			getResources().getDrawable(R.drawable.faceplaceholder));
        	customAdapter.add(c);
        }
        
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			 
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              String str  = listView.getItemAtPosition(position).toString();
              // Show Toast 
              Toast.makeText(getApplicationContext(),
                "Click su posizione n."+position+": " +str, Toast.LENGTH_LONG)
                .show();
            }
       }); 
		
	}

	public void pulsantePremuto(View v) {
		Toast.makeText(getApplicationContext(),"Foto cliccata!", Toast.LENGTH_LONG).show();
	}
	


}
