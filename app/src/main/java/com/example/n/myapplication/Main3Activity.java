package com.example.n.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    EditText title_text,word_text;
    SQLiteDatabase db;
    DBopen open;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        title_text = (EditText)findViewById(R.id.editText3);
        word_text = (EditText)findViewById(R.id.editText4);
        open  = new DBopen(getApplicationContext(),"note",null,1);;
        update();
    }
    String name[];
    private void update(){
        Intent it = getIntent();
        db = open.getWritableDatabase();
        cursor = db.rawQuery("select * from note where number = "+it.getExtras().getInt("listid".toString()),null);
        name = cursor.getColumnNames();
        cursor.moveToFirst();
        title_text.setText(cursor.getString(cursor.getColumnIndex(name[1])));
        word_text.setText(cursor.getString(cursor.getColumnIndex(name[2])));
    }

    public void onBackPressed() {
        save();
        Intent it = new Intent();
        it.setClass(Main3Activity.this,MainActivity.class);
        startActivity(it);
    }

    private void save(){
        Intent it = getIntent();
        String id = Integer.toString(it.getExtras().getInt("listid"));
        ContentValues cv = new ContentValues();
        cv.put("word",word_text.getText().toString());
        db.update("note",cv,"number = "+id,null);
        db.close();
    }
}
