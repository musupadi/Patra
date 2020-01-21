package com.destinyapp.patra.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatraDepot {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("nama_depot")
    @Expose
    public String nama_depot;

    @SerializedName("side_supervisor")
    @Expose
    public String side_supervisor;
}
