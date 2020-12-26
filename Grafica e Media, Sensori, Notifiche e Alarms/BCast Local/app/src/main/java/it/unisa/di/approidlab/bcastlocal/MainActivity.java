package it.unisa.di.approidlab.bcastlocal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	BroadcastReceiver dReceiver;
	BroadcastReceiver sReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected void onResume() {
	    super.onResume();
		IntentFilter intentFilter =
				new IntentFilter("it.unisa.di.approidlab.bcastlocal.my_string");

		//Statically created receiver
		sReceiver = new Receiver();
		registerReceiver(sReceiver, intentFilter);

		//Dynamically created receiver
		dReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				Log.d("DEBUG", "dynamically registered receiver");
				Toast.makeText(context,
						"Intent received by the dynamically registered receiver",
						Toast.LENGTH_LONG).show();

			}
		};
		registerReceiver(dReceiver, intentFilter);
	}

	@Override
	protected void onPause() {
	    super.onPause();
		unregisterReceiver(sReceiver);
		unregisterReceiver(dReceiver);
	}

	public void sendBroadcast(View v) {
		sendBroadcast(new Intent("it.unisa.di.approidlab.bcastlocal.my_string"));
	}
}
