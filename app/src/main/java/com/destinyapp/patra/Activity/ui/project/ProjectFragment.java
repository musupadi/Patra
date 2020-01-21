package com.destinyapp.patra.Activity.ui.project;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.patra.API.ApiRequest;
import com.destinyapp.patra.API.RetroServer;
import com.destinyapp.patra.Activity.AccountActivity;
import com.destinyapp.patra.Adapter.AdapterProject;
import com.destinyapp.patra.Model.Data;
import com.destinyapp.patra.Model.PatraProject;
import com.destinyapp.patra.Model.ResponseModel;
import com.destinyapp.patra.R;
import com.destinyapp.patra.SharedPreferance.DB_Helper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectFragment extends Fragment {
    private List<PatraProject> mItems = new ArrayList<>();
    ProgressBar pd;
    RecyclerView recycler;
    String uuid,id,email,username,name,avatar,token;
    Dialog myDialog;
    EditText NamaProject;
    Button submit;
    FloatingActionButton fabAdd;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_project, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDialog = new Dialog(getActivity());
        myDialog.setContentView(R.layout.dialog_project_add);

        NamaProject=myDialog.findViewById(R.id.etProject);
        submit=myDialog.findViewById(R.id.btnSubmit);
        recycler=view.findViewById(R.id.recycler);
        fabAdd=view.findViewById(R.id.fabPlus);
        DB_Helper dbHelper=new DB_Helper(getActivity());
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
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.show();
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Input();
                    }
                });
            }
        });
    }
    private void Input(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Mencoba Menginput Data");
        pd.setCancelable(false);
        pd.show();
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> input = api.InputProject("3899CE8456DEE44F894044EDB678969F",
                token,
                "application/x-www-form-urlencoded",
                NamaProject.getText().toString()
        );
        input.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.hide();
                Toast.makeText(getActivity(), response.body().message, Toast.LENGTH_SHORT).show();
                myDialog.hide();
                Logic();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Logic(){
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setMessage("Sedang Mengambil List Project");
        pd.setCancelable(false);
        pd.show();
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> project = api.AllProject("3899CE8456DEE44F894044EDB678969F",
                token,
                "application/x-www-form-urlencoded",
                uuid);
        project.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.hide();
                mItems =response.body().data.getPatraProject();
                AdapterProject adapter = new AdapterProject(getActivity(),mItems);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(getActivity(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}