package com.example.activitytest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FristActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("FristActivity",this.toString());

        Log.d("FristActivity","Task id is:"+getTaskId());
        setContentView(R.layout.first_layout);
        Button button1=(Button)  findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent intent= new Intent(FristActivity.this,SecondActivity.class);  显式Intent
                /*Intent intent= new Intent(Intent.ACTION_VIEW);           //隐式Intent
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);*/

                /*String date ="Helle SecondActivity";
                Intent intent= new Intent(FristActivity.this,SecondActivity.class);
                intent.putExtra("extra_data",date);
                startActivity(intent);*/                                 //传数据到下一个活动

               /* Intent intent = new Intent(FristActivity.this,SecondActivity.class);
                startActivityForResult(intent,1);*/               //返回数据

               // Intent intent = new Intent(FristActivity.this,FristActivity.class);      //下个活动指向自己

               Intent intent = new Intent(FristActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("FristActivity","onRestart");
    }



    @Override
    protected void  onActivityResult(int requestCode, int resultCode,Intent data){
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String returnedData =data.getStringExtra("data_return");
                    Log.d("FristActivity",returnedData);
                }
                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"You clicked Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"You clicked Remove",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}