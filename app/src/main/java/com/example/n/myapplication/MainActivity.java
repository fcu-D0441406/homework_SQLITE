package com.example.n.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button;
    ListView listView;
    String [] list_title2 = new String [10];
    String [] list_id = new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        listView = (ListView)findViewById(R.id.listview);
        button.setOnClickListener(listen);
        save_load();
        ArrayList<String> array = new ArrayList<String>();
        for(int i = 0;i<list_title2.length;i++){
            if(list_title2[i]==null){
                break;
            }
            array.add(list_id[i] +" "+ list_title2[i]);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent it = new Intent();
                it.putExtra("listid",position);
                it.setClass(MainActivity.this,Main3Activity.class);
                startActivity(it);
            }
        });
        //Toast.makeText(MainActivity.this,Integer.toString(listView.getCount()),Toast.LENGTH_LONG).show();
    }
    private View.OnClickListener listen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent();
            it.setClass(MainActivity.this,Main2Activity.class);
            it.putExtra("list_id",listView.getCount());
            startActivity(it);
        }
    };
    private void save_load(){
        SQLiteDatabase db;
        DBopen open = new DBopen(getApplicationContext(),"note",null,1);;
        db = open.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from note",null);
        //cursor = db.rawQuery("delete from note where title = 'listview0'; ",null);
        String name [] = cursor.getColumnNames();
        cursor.moveToFirst();
        for(int i = 0;i<cursor.getCount();i++){
            list_title2[i] = cursor.getString(cursor.getColumnIndex(name[1]));
            list_id[i] = cursor.getString(cursor.getColumnIndex(name[0]));
            cursor.moveToNext();
        }
        db.close();
    }
}
