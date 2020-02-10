package com.destinyapp.patra.Adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.patra.API.ApiRequest;
import com.destinyapp.patra.API.RetroServer;
import com.destinyapp.patra.Activity.MainActivity;
import com.destinyapp.patra.Model.Method;
import com.destinyapp.patra.Model.PatraMarketing;
import com.destinyapp.patra.Model.PatraProject;
import com.destinyapp.patra.Model.PatraSupervisor;
import com.destinyapp.patra.Model.ResponseModel;
import com.destinyapp.patra.R;
import com.destinyapp.patra.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterSiteSupervisor extends RecyclerView.Adapter<AdapterSiteSupervisor.HolderData> implements Filterable {
    private List<PatraSupervisor> mList;
    private List<PatraSupervisor> mListFull;
    private List<PatraMarketing> mItemss = new ArrayList<>();
    private Context ctx;
    Dialog myDialog,DialogEdit;
    Button edit,delete;
    String uuid,id,email,username,name,avatar,token;
    Method method = new Method();
    Spinner spinner;
    EditText nama;
    Button submit;
    TextView ids;
    public AdapterSiteSupervisor(Context ctx, List<PatraSupervisor> mList){
        this.ctx = ctx;
        this.mList = mList;
        mListFull = new ArrayList<>(mList);
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_project,viewGroup,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSiteSupervisor.HolderData holderData, int posistion) {
        final PatraSupervisor dm = mList.get(posistion);
        holderData.nama.setText(dm.nama_ss);

        DB_Helper dbHelper=new DB_Helper(ctx);
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
        myDialog = new Dialog(ctx);
        myDialog.setContentView(R.layout.dialog_pilihan);
        DialogEdit = new Dialog(ctx);
        DialogEdit.setContentView(R.layout.dialog_site_supervisor);
        edit = myDialog.findViewById(R.id.btnEdit);
        delete = myDialog.findViewById(R.id.btnDelete);
        ids=DialogEdit.findViewById(R.id.tvID);
        spinner=DialogEdit.findViewById(R.id.Spinner);
        nama=DialogEdit.findViewById(R.id.etSiteSupervisor);
        submit=DialogEdit.findViewById(R.id.btnSubmit);

        holderData.nama.setText(dm.nama_ss);
        holderData.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpinner();
                myDialog.show();
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogicDelete(dm.id);
                    }
                });
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.hide();
                        DialogEdit.show();
                        nama.setText(dm.nama_ss);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                PatraMarketing clickedItem = (PatraMarketing) parent.getItemAtPosition(position);
                                ids.setText(clickedItem.id);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UpdateData(dm.id,nama.getText().toString(),ids.getText().toString());
                            }
                        });
                    }
                });
            }
        });
        holderData.dm=dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView nama;
        PatraSupervisor dm;
        LinearLayout linear;
        HolderData(View v){
            super(v);
            nama = v.findViewById(R.id.tvNamaProject);
            linear = v.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PatraSupervisor> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mListFull);
            }else{
                String fillterPattern = constraint.toString().toLowerCase().trim();

                for (PatraSupervisor dm : mListFull){
                    if (dm.nama_ss.toLowerCase().contains(fillterPattern)){
                        filteredList.add(dm);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList.clear();
            mList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    private void LogicDelete(String ID){
        final ProgressDialog pd = new ProgressDialog(ctx);
        pd.setMessage("Sedang Mencoba Menghapus Data");
        pd.setCancelable(false);
        pd.show();
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> input = api.DeleteSupervisor("3899CE8456DEE44F894044EDB678969F",
                token,
                "application/x-www-form-urlencoded",
                ID
        );
        input.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.hide();
                try {
                    Toast.makeText(ctx, response.body().message, Toast.LENGTH_SHORT).show();
                    myDialog.hide();
                    Intent goInput = new Intent(ctx, MainActivity.class);
                    goInput.putExtra("NAVIGATE",String.valueOf(R.id.nav_site_supervisor));
                    ctx.startActivity(goInput);
                }catch (Exception e){
                    Toast.makeText(ctx, "Token Expired", Toast.LENGTH_SHORT).show();
                    method.AutoLogout(ctx);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getSpinner(){
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> project = api.AllMarketing("3899CE8456DEE44F894044EDB678969F",
                token,
                "application/x-www-form-urlencoded",
                uuid);
        project.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                try {
                    mItemss=response.body().data.getPatraMarketing();
                    SpinnerAdapterMarketing adapter = new SpinnerAdapterMarketing(ctx,mItemss);
                    spinner.setAdapter(adapter);
                }catch (Exception e){
                    Toast.makeText(ctx, "Token Expired", Toast.LENGTH_SHORT).show();
                    method.AutoLogout(ctx);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UpdateData(String ID,String nama,String IDMarketing){
        final ProgressDialog pd = new ProgressDialog(ctx);
        pd.setMessage("Sedang Mencoba Mengupdate Data");
        pd.setCancelable(false);
        pd.show();
        ApiRequest api = RetroServer.getClient().create(ApiRequest.class);
        Call<ResponseModel> input = api.UpdateSupervisor("3899CE8456DEE44F894044EDB678969F",
                token,
                "application/x-www-form-urlencoded",
                ID,
                nama,
                IDMarketing
        );
        input.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.hide();
                try {
                    Toast.makeText(ctx, response.body().message, Toast.LENGTH_SHORT).show();
                    myDialog.hide();
                    method.AutoLogout(ctx);
//                    Intent goInput = new Intent(ctx, MainActivity.class);
//                    goInput.putExtra("NAVIGATE",String.valueOf(R.id.nav_site_supervisor));
//                    ctx.startActivity(goInput);
                }catch (Exception e){
                    Toast.makeText(ctx, "Token Expired", Toast.LENGTH_SHORT).show();
                    method.AutoLogout(ctx);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(ctx, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


