package com.example.drawer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener ,
        AbsListView.OnScrollListener , AdapterView.OnItemClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private Context mContext;
    private ListView listview;
    private SimpleAdapter simp_adapter;
    private List<Map<String, Object>> dataList;
    private NotesDB DB;
    private SQLiteDatabase dbread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listview = findViewById(R.id.listview);
        dataList = new ArrayList<>();


        DB = new NotesDB(this);
        dbread = DB.getReadableDatabase();
        // 清空数据库表中内容
        //dbread.execSQL("delete from note");
        RefreshNotesList();

        listview.setOnItemClickListener(this);
        listview.setOnItemLongClickListener(this);
        listview.setOnScrollListener(this);

        mContext = this;
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                NoteEdit.ENTER_STATE = 0;
                Intent intent = new Intent(mContext, NoteEdit.class);
                Bundle bundle = new Bundle();
                bundle.putString("info", "");
                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
            }
        });




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void RefreshNotesList() {

        int size = dataList.size();
        if (size > 0) {
            dataList.removeAll(dataList);
            simp_adapter.notifyDataSetChanged();
            listview.setAdapter(simp_adapter);
        }
        simp_adapter = new SimpleAdapter(this, getData(), R.layout.item,
                new String[] { "tv_content", "tv_date" }, new int[] {
                R.id.tv_content, R.id.tv_date });
        listview.setAdapter(simp_adapter);
    }

    private List<Map<String, Object>> getData() {

        Cursor cursor = dbread.query("note", null, "content!=\"\"", null, null,
                null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("tv_content", name);
            map.put("tv_date", date);
            map.put("tv_id", id);
            dataList.add(map);
        }
        cursor.close();
        return dataList;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    // 接受上一个页面返回的数据
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            RefreshNotesList();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        final int n=arg2;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除该日志吗？");
        builder.setMessage("确认删除吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Map<String,String> itemMap = (Map<String, String>) listview.getItemAtPosition(n);
                Log.d("content", itemMap.get("tv_content"));
                String id = itemMap.get("tv_id");
                Log.d("ID", id);
                Cursor c = dbread.query("note", null,
                        "_id=" + "'" + id + "'", null, null, null, null);
                while (c.moveToNext()) {
                    String sql_del = "update note set content='' where _id="
                            + id;
                    dbread.execSQL(sql_del);
                    RefreshNotesList();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
        return true;
    }

    @Override
    public void onScrollStateChanged(AbsListView arg0, int arg1) {
        switch (arg1) {
            case SCROLL_STATE_FLING:
                Log.i("main", "");
            case SCROLL_STATE_IDLE:
                Log.i("main", "");
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("main", "");
        }

    }

    @Override
    public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {{
        NoteEdit.ENTER_STATE = 1;
        Log.d("arg2", arg2 + "");
        // TextView
        // content=(TextView)listview.getChildAt(arg2).findViewById(R.id.tv_content);
        // String content1=content.toString();
        Map<String,String> itemMap = (Map<String, String>) listview.getItemAtPosition(arg2);
        Log.d("content", itemMap.get("tv_content"));
        String id = itemMap.get("tv_id");
        Log.d("ID", id);
        Cursor c = dbread.query("note", null,
                "_id=" + "'" + id + "'", null, null, null, null);
        while (c.moveToNext()) {
            String No = c.getString(c.getColumnIndex("_id"));
            String noteContent = c.getString(c.getColumnIndex("content"));
            Log.d("TEXT", No);
            // Intent intent = new Intent(mContext, NoteEdit.class);
            // intent.putExtra("data", text);
            // setResult(4, intent);
            // // intent.putExtra("data",text);
            // startActivityForResult(intent, 3);
            Intent myIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("info", noteContent);
            NoteEdit.id = Integer.parseInt(No);
            myIntent.putExtras(bundle);
            myIntent.setClass(MainActivity.this, NoteEdit.class);
            startActivityForResult(myIntent, 1);
        }

    }

    }
}