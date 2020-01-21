package com.destinyapp.patra.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.destinyapp.patra.API.ApiRequest;
import com.destinyapp.patra.API.RetroServer;
import com.destinyapp.patra.Model.ResponseModel;
import com.destinyapp.patra.R;
import com.destinyapp.patra.SharedPreferance.DB_Helper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    String uuid,id,email,username,name,avatar,token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        DB_Helper dbHelper=new DB_Helper(AccountActivity.this);
        Cursor cursor = dbHelper.checkSession();
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                uuid = cursor.getString(0);
                id = cursor.getString(1);
                email = cursor.getString(2);
                username = cursor.getString(3);
                name = cursor.getString(4);
                avatar = cursor.getString(5);
                token = cursor.getString(6);
            }
        }
        final ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> getData =api.ProfileDetail("3899CE8456DEE44F894044EDB678969F",
                token,
                "application/x-www-form-urlencoded",
                uuid
        );
        getData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Toast.makeText(AccountActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AccountActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
