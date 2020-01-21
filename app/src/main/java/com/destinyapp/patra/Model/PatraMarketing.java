package com.destinyapp.patra.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatraMarketing {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("nama_mor")
    @Expose
    public String nama_mor;

    @SerializedName("project_id")
    @Expose
    public String project_id;
}
