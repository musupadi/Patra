package com.destinyapp.patra.SharedPreferance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.LinkedList;
import java.util.List;

public class DB_Helper extends SQLiteOpenHelper {

//    this.id=id;
//        this.email=email;
//        this.username=username;
//        this.full_name=full_name;
//        this.avatar=avatar;
    public static final String DATABASE_NAME = "session.db";
    private static final int DATABASE_VERSION = 2;
    //Account
    public static final String TABLE_NAME_ACCOUNT = "account";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_FULLNAME = "full_name";
    public static final String COLUMN_AVATAR = "avatar";
    public DB_Helper(Context context){super(
            context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME_ACCOUNT+" (" +
                COLUMN_ID+" TEXT NOT NULL, "+
                COLUMN_EMAIL+" TEXT NOT NULL, "+
                COLUMN_USERNAME+" TEXT NOT NULL, "+
                COLUMN_FULLNAME+" TEXT NOT NULL, "+
                COLUMN_AVATAR+" TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT);
        this.onCreate(db);
    }



    public Cursor checkerUser(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME_ACCOUNT+" WHERE "+COLUMN_ID+" = '"+username+"'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
    public Cursor checkUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME_ACCOUNT;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public void saveSession(ModelSession session){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,session.getId());
        values.put(COLUMN_EMAIL, session.getEmail());
        values.put(COLUMN_USERNAME,session.getUsername());
        values.put(COLUMN_FULLNAME,session.getFull_name());
        values.put(COLUMN_USERNAME,session.getUsername());
        values.put(COLUMN_AVATAR,session.getAvatar());
        db.insert(TABLE_NAME_ACCOUNT,null,values);
        db.close();
    }

    public Cursor checkSession(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+TABLE_NAME_ACCOUNT+"";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}

