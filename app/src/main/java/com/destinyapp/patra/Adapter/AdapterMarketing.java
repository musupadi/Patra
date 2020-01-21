package com.destinyapp.patra.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.destinyapp.patra.Model.PatraMarketing;
import com.destinyapp.patra.Model.PatraProject;
import com.destinyapp.patra.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMarketing extends RecyclerView.Adapter<AdapterMarketing.HolderData> implements Filterable {
    private List<PatraMarketing> mList;
    private List<PatraMarketing> mListFull;
    private Context ctx;
    public AdapterMarketing(Context ctx, List<PatraMarketing> mList){
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
    public void onBindViewHolder(@NonNull AdapterMarketing.HolderData holderData, int posistion) {
        final PatraMarketing dm = mList.get(posistion);
        holderData.nama.setText(dm.nama_mor);
        holderData.dm=dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView nama;
        PatraMarketing dm;
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
            List<PatraMarketing> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                filteredList.addAll(mListFull);
            }else{
                String fillterPattern = constraint.toString().toLowerCase().trim();

                for (PatraMarketing dm : mListFull){
                    if (dm.nama_mor.toLowerCase().contains(fillterPattern)){
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

