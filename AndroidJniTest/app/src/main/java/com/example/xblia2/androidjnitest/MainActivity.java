package com.example.xblia2.androidjnitest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.util.Utils;

import java.util.Random;


public class MainActivity extends ActionBarActivity implements View.OnClickListener
{

    private EditText editNumber1;
    private EditText editNumber2;
    private Button calcBtn;
    private Button jniWriteBtn;
    private Button startServiceBtn;
    private Button startBoradcastBtn;
    private TextView tvResult;
    private EditText editTrace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    public void initView()
    {
        editNumber1 = (EditText)this.findViewById(R.id.edit_number1);
        editNumber2 = (EditText)this.findViewById(R.id.edit_number2);
        calcBtn = (Button)this.findViewById(R.id.btn_calc);
        jniWriteBtn = (Button)this.findViewById(R.id.btn_jni_writeFile);
        startServiceBtn = (Button)this.findViewById(R.id.btn_startService);
        startBoradcastBtn = (Button)this.findViewById(R.id.btn_startBroadCast);
        tvResult = (TextView)this.findViewById(R.id.tv_result);
        editTrace = (EditText)this.findViewById(R.id.edit_Trance);

        calcBtn.setOnClickListener(this);
        jniWriteBtn.setOnClickListener(this);
        startServiceBtn.setOnClickListener(this);
        startBoradcastBtn.setOnClickListener(this);


        tvResult.setText(Utils.stringFromJNI());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_calc:
                calc();
                break;
            case R.id.btn_jni_writeFile:
                Utils.writeContentToFile("Write File from JNI.");
                printTrace("Write info to file by JNI access, random number: " + new Random().nextInt());
                break;
            case R.id.btn_startService:
                Intent intent = new Intent();
                intent.setClass(this.getApplicationContext(), JNIService.class);
                startService(intent);
                break;
            case R.id.btn_startBroadCast:
                Intent intentBroad = new Intent();
                intentBroad.setAction(IConstant.JNI_RECEIVER_ACTION);
                sendBroadcast(intentBroad);
                break;
        }
    }

    private void calc()
    {
        String str1 = editNumber1.getText().toString();
        String str2 = editNumber2.getText().toString();
        if(null != str1 && !str1.isEmpty()
                && null != str2 && !str2.isEmpty())
        {
            try {
                int a = Integer.parseInt(str1);
                int b = Integer.parseInt(str2);
                int sum = Utils.calc(a, b);
                tvResult.setText("Result: " + sum);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                toastInfo(e.getMessage());
            }
            return;
        }
        toastInfo("Value1 and Value2 input values must be number.");

    }

    private void toastInfo(String info)
    {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    private void printTrace(String info)
    {
        editTrace.append(info + System.getProperty("line.separator"));
        editTrace.setSelection(editTrace.getText().length(), editTrace.getText().length());

    }
}
