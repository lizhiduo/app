package com.example.lizd.touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

//    private TextView tv;
//
//    private double start_X, start_Y, offset_X, offset_Y;
//    private double pressure;
//
//    private static String TAG = "onTouch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        tv = (TextView) findViewById(R.id.tv);
//
//        tv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        start_X = motionEvent.getX();
//                        start_Y = motionEvent.getY();
//                        pressure = motionEvent.getPressure();
//
//                        break;
//
//                    case MotionEvent.ACTION_UP:
//                        offset_X = start_X - motionEvent.getX();
//                        offset_Y = start_Y -  motionEvent.getY();
//
//                        if(Math.abs(offset_X) > Math.abs(offset_Y)){
//                            if(offset_X <= -5){
//                                Log.d(TAG, "right" );
//                            }else if(offset_X >= 5){
//                                Log.d(TAG, "left" );
//                            }
//                        }else{
//                            if(offset_Y <= -5){
//                                Log.d(TAG, "down" );
//                            }else if(offset_Y >= 5){
//                                Log.d(TAG, "up" );
//                            }
//                        }
//                        break;
//
//                    default:
//                        break;
//                }
////                Log.d(TAG, "onTouch: start_X" + start_X + "start_Y : " + start_Y);
//                tv.setText("start_X" + start_X + "\nstart_Y : " + start_Y);
//                return true;
//            }
//        });
        
    }


    
}
