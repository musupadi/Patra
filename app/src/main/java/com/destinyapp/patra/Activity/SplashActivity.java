package com.destinyapp.patra.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.destinyapp.patra.R;
import com.destinyapp.patra.SharedPreferance.DB_Helper;

public class SplashActivity extends AppCompatActivity {
    DB_Helper dbHelper = new DB_Helper(SplashActivity.this);
    String id,email,username,name,avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Cursor cursor = dbHelper.checkSession();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                id = cursor.getString(0);
                email = cursor.getString(1);
                username = cursor.getString(2);
                name = cursor.getString(3);
                avatar = cursor.getString(4);
            }
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
        }else{
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }, 3000);
        }

    }
}
