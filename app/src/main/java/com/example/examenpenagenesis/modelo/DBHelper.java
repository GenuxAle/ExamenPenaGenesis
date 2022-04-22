package com.example.examenpenagenesis.modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "productos.db";
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_TABLE_NAME = "productos";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + DATABASE_TABLE_NAME
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT,NOMBRE TEXT,DESCRIPCION TEXT, IMAGEN BLOB, PRECIO INTEGER, STOCK INTEGER )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqld = "DROP TABLE " + DATABASE_TABLE_NAME;
        sqLiteDatabase.execSQL(sqld);
        onCreate(sqLiteDatabase);
    }

    public void noQuery(String nsql) {
        this.getWritableDatabase().execSQL(nsql);
    }

    public Cursor query(String sql) {
        return this.getReadableDatabase().rawQuery(sql, null);
    }

}
