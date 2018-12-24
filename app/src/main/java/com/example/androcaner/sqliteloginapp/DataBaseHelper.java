package com.example.androcaner.sqliteloginapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    //Database...
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="Kayıt";
    public static final String TABLE_NAME="Kayıt_table";
    public static final String COL_1="id";
    public static final String COL_2="kullanıcı_adı";
    public static final String COL_3="sifre";
    public static final String COL_4="email";
    public static final String COL_5="cinsiyet";
    public static final String COL_6="calisma_durumu";



    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"( id INTEGER PRIMARY KEY AUTOINCREMENT, kullanıcı_adı VARCHAR, sifre VARCHAR, email VARCHAR, cinsiyet VARCHAR, calisma_durumu VARCHAR ) ";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
