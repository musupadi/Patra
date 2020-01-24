package com.destinyapp.patra.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAccountActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    TextView tglLahir;
    EditText Email,hp,ktp,jabatan,nama;
    String uuid,id,email,username,name,avatar,token;
    Button simpan,tgl;
    List<PatraProfile> mItems = new ArrayList<>();
    Method method = new Method();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        Email = findViewById(R.id.etEmail);
        hp = findViewById(R.id.etHP);
        ktp = findViewById(R.id.etKTP);
        jabatan = findViewById(R.id.etJabatan);
        nama = findViewById(R.id.etNama);
        tglLahir = findViewById(R.id.tvTglLahir);
        simpan = findViewById(R.id.btnSimpan);
        tgl = findViewById(R.id.btnTglLahir);
        DB_Helper dbHelper=new DB_Helper(EditAccountActivity.this);
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
        tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Save();
            }
        });

    }
    private void Save(){
        final ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> SaveData =api.EditData("3899CE8456DEE44F894044EDB678969F",
                token,
                "application/x-www-form-urlencoded",
                uuid,
                Email.getText().toString(),
                hp.getText().toString(),
                ktp.getText().toString(),
                jabatan.getText().toString(),
                nama.getText().toString(),
                tglLahir.getText().toString()
        );
        SaveData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    Toast.makeText(EditAccountActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditAccountActivity.this,MainActivity.class);
                    finishAffinity();
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(EditAccountActivity.this, "Token Expired", Toast.LENGTH_SHORT).show();
                    method.AutoLogout(EditAccountActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(EditAccountActivity.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
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
                    nama.setText(mItems.get(0).nama);
                    jabatan.setText(mItems.get(0).jabatan);
                    Email.setText(mItems.get(0).email);
                    ktp.setText(mItems.get(0).ktp);
                    hp.setText(mItems.get(0).hp);
                    tglLahir.setText(mItems.get(0).tanggal_lahir);
                }catch (Exception e){
                    Toast.makeText(EditAccountActivity.this, "Token Expired", Toast.LENGTH_SHORT).show();
                    method.AutoLogout(EditAccountActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(EditAccountActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDatePicker(){
        DatePickerDialog dialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String date = year+"-"+month+"-"+day;
        tglLahir.setText(date);
    }
}
