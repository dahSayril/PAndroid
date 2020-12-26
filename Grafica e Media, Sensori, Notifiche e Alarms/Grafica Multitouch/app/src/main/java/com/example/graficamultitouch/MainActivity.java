package com.example.graficamultitouch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    public static final String TAG = "DEBUG";
    private Map<Integer, CircleTouch> hashMap = new HashMap<Integer, CircleTouch>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        final FrameLayout main_window = (FrameLayout) findViewById(R.id.container);

		/*
		CircleTouch ct = new CircleTouch(getApplicationContext(), 0);
		main_window.addView(ct);
		ct.setNewCoordinates(80,80);
		ct.setSize(50);
		ct.invalidate();

		ct = new CircleTouch(getApplicationContext(), 1);
		main_window.addView(ct);
		ct.setNewCoordinates(200,250);
		ct.setSize(50);
		ct.invalidate();

		ct = new CircleTouch(getApplicationContext(), 2);
		main_window.addView(ct);
		ct.setNewCoordinates(60,300);
		ct.setSize(50);
		ct.invalidate();

		ct = new CircleTouch(getApplicationContext(), 3);
		main_window.addView(ct);
		ct.setNewCoordinates(250,80);
		ct.setSize(50);
		ct.invalidate();
		*/

//		/*
        main_window.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int pointer_index;
                int pointer_id;
                CircleTouch ct;

                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        pointer_index = event.getActionIndex();
                        pointer_id = event.getPointerId(pointer_index);
                        Log.d(TAG,"Action down: index="+pointer_index+"  ID="+pointer_id);
                        if (hashMap.containsKey(pointer_id)) {
                            ct = hashMap.get(pointer_id);
                            Log.d(TAG,"Found ct ID="+pointer_id);
                        }
                        else {
                            ct = new CircleTouch(getApplicationContext(), pointer_id);
                            hashMap.put(pointer_id, ct);
                            Log.d(TAG,"Creating new ct and inserting it into hashMap");
                            main_window.addView(ct);
                        }
                        Log.d(TAG,"ct="+ct.toString());
                        ct.setNewCoordinates(event.getX(pointer_index),event.getY(pointer_index));
                        ct.setSize(50);
                        ct.invalidate();
                        break;


                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        pointer_index = event.getActionIndex();
                        pointer_id = event.getPointerId(pointer_index);
                        Log.d(TAG,"Action up: index="+pointer_index+"  ID="+pointer_id);
                        if (hashMap.containsKey(pointer_id)) {
                            ct = hashMap.get(pointer_id);
                            Log.d(TAG,"Found ct ID="+pointer_id);
                            ct.setSize(0);
                            ct.invalidate();
                            main_window.removeView(ct);
                            hashMap.remove(pointer_id);
                        }
                        break;

                    case MotionEvent.ACTION_MOVE: {
                        for (int i = 0; i < event.getPointerCount(); i++) {
                            pointer_id = event.getPointerId(i);
                            ct = hashMap.get(pointer_id);
                            if (null != ct) {
                                ct.setNewCoordinates(event.getX(i),event.getY(i));
                                ct.invalidate();
                            }
                        }
                    }

                    break;
                }
                return true;
            }
        });
//		*/
    }

}