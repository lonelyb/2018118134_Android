package com.example.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        /*Intent intent = getIntent();
        String data = intent.getStringExtra("extra_data");
        Log.d("SecondActivity",data);*/ //重上一个活动获得数据

        /*Button button = (Button)  findViewById(R.id.button_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("data_return","Helllo FristActivity");
                setResult(RESULT_OK,intent);
                finish();
            }
        });*/                              //返回数据到上一个活动
        //Log.d("SecondActivity",this.toString());

        Log.d("SecondActivity","Task id is:"+getTaskId());
        Button button = (Button)  findViewById(R.id.button_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(SecondActivity.this,FristActivity.class);
                Intent intent= new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("SecondActivity","onDestroy");
    }
}
