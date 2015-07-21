package com.example.xblia2.androidjnitest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class JNIReceiver extends BroadcastReceiver {
    public JNIReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        if(action.equals(IConstant.JNI_RECEIVER_ACTION))
        {
            Toast.makeText(context, "JNI_BroadcastReciver valid.", Toast.LENGTH_SHORT).show();
            Log.e("JNI_TEST", "JNI_BroadcastReciver valid.");
        }else
        {
            Toast.makeText(context, "JNI_BroadcastReciver" + action, Toast.LENGTH_SHORT).show();
            Log.e("JNI_TEST", "JNI_BroadcastReciver valid.");
        }
    }
}
