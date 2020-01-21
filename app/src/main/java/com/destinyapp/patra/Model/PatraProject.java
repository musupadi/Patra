package com.destinyapp.patra.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatraProject {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("nama_project")
    @Expose
    public String nama_project;

}
