package com.destinyapp.patra.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatraSupervisor {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("nama_ss")
    @Expose
    public String nama_ss;

    @SerializedName("marketing_id")
    @Expose
    public String marketing_id;
}
