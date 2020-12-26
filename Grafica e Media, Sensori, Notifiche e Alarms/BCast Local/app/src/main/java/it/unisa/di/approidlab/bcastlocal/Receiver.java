package it.unisa.di.approidlab.bcastlocal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("DEBUG", "statically registered receiver");
		Toast.makeText(context,
				   "Intent received by the statically registered receiver", 
				   Toast.LENGTH_LONG).show();
	}

}
