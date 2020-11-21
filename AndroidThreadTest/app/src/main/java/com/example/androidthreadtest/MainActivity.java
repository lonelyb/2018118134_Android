package com.example.androidthreadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    public static final int UPDATA_TEXT=1;
    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            switch (message.what){
                case UPDATA_TEXT:
                    text.setText("打工魂");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //text.setText("打工魂");
                        Message message = new Message();
                        message.what = UPDATA_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
        }
    }
}
