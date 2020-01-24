package com.destinyapp.patra.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.destinyapp.patra.API.ApiRequest;
import com.destinyapp.patra.API.RetroServer;
import com.destinyapp.patra.Model.Method;
import com.destinyapp.patra.Model.PatraProfile;
import com.destinyapp.patra.Model.ResponseModel;
import com.destinyapp.patra.R;
import com.destinyapp.patra.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    String uuid,id,email,username,name,avatar,token;
    TextView headerNama,headerJabatan,nama,jabatan,Email,KTP,HP,tanggal_lahir;
    List<PatraProfile> mItems = new ArrayList<>();
    Button EditAccount;
    Method method = new Method();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        headerNama = findViewById(R.id.tvNama);
        headerJabatan = findViewById(R.id.tvJabatan);
        nama = findViewById(R.id.tvNamaDetail);
        jabatan = findViewById(R.id.tvJabatanDetail);
        Email = findViewById(R.id.tvEmail);
        KTP = findViewById(R.id.tvNoKTP);
        HP = findViewById(R.id.tvNoHp);
        tanggal_lahir = findViewById(R.id.tvTglLahir);
        EditAccount = findViewById(R.id.btnEdit);
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
        Logic();
        EditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this,EditAccountActivity.class);
                startActivity(intent);
            }
        });
    }
    private void Logic(){
        final ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> getData =api.ProfileDetail("3899CE8456DEE44F894044EDB678969F",
                token,
                "application/x-www-form-urlencoded",
                uuid
        );
        getData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItems=response.body().data.getPatra_profile();
                    headerNama.setText(mItems.get(0).nama);
                    headerJabatan.setText(mItems.get(0).jabatan);
                    nama.setText(mItems.get(0).nama);
                    jabatan.setText(mItems.get(0).jabatan);
                    Email.setText(mItems.get(0).email);
                    KTP.setText(mItems.get(0).ktp);
                    HP.setText(mItems.get(0).hp);
                    tanggal_lahir.setText(mItems.get(0).tanggal_lahir);
                }catch (Exception e){
                    Toast.makeText(AccountActivity.this, "Token Expired", Toast.LENGTH_SHORT).show();
                    method.AutoLogout(AccountActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(AccountActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
