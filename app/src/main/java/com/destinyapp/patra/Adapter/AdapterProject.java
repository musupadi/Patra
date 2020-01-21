package com.destinyapp.patra.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.patra.Model.DataModel;
import com.destinyapp.patra.Model.PatraProject;
import com.destinyapp.patra.R;
import com.destinyapp.patra.SharedPreferance.DB_Helper;

import java.util.ArrayList;
import java.util.List;

public class AdapterProject extends RecyclerView.Adapter<AdapterProject.HolderData> implements Filterable {
    private List<PatraProject> mList;
    private List<PatraProject> mListFull;
    private Context ctx;
    public AdapterProject(Context ctx, List<PatraProject> mList){
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
    public void onBindViewHolder(@NonNull AdapterProject.HolderData holderData, int posistion) {
        final PatraProject dm = mList.get(posistion);
        holderData.nama.setText(dm.nama_project);
        holderData.dm=dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView nama;
        PatraProject dm;
        HolderData(View v){
            super(v);
            nama = v.findViewById(R.id.tvNamaProject);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PatraProject> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mListFull);
            }else{
                String fillterPattern = constraint.toString().toLowerCase().trim();

                for (PatraProject dm : mListFull){
                    if (dm.nama_project.toLowerCase().contains(fillterPattern)){
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
}
