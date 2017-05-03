package com.example.n.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText title,word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        title = (EditText)findViewById(R.id.editText);
        word = (EditText)findViewById(R.id.editText2);
    }
    public void onBackPressed() {
        save();
        Intent it = new Intent();
        it.setClass(Main2Activity.this,MainActivity.class);
        startActivity(it);
    }
    private void save(){
        Intent it = getIntent();
        SQLiteDatabase db;
        DBopen open = new DBopen(getApplicationContext(),"note",null,1);;
        db = open.getWritableDatabase();
        Cursor cursor;
        ContentValues contentValues = new ContentValues();
        contentValues.put("number",it.getExtras().getInt("list_id"));
        contentValues.put("title",title.getText().toString());
        contentValues.put("word",word.getText().toString());
        db.insert("note",null,contentValues);
        db.close();
    }
}
class DBopen extends SQLiteOpenHelper{

    public DBopen(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table_name = "note";
        String ca = "create table "+table_name+" (number,title,word);";
        db.execSQL(ca);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
