package it.unisa.di.approidlab.bcastglobal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	BroadcastReceiver receiver;
	TextView tv;
	int counter = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setBroadcastReceiver();
		tv = (TextView) findViewById(R.id.textView);
	}
	
	@Override
	protected void onDestroy() {
	  super.onDestroy();
	  unregisterReceiver(receiver);
	}
	
	private void setBroadcastReceiver() {
		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);

		receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				Log.d("DEBUG","Hello, another minute is gone!!!");
				counter++;
				String min = "minuti";
				if (counter == 1) min = "minuto";
				tv.setText(counter+" "+min);
			}
		};
		
		registerReceiver(receiver, intentFilter);
	}
}
