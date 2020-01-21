package com.destinyapp.patra.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatraProfile {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("uuid")
    @Expose
    public String uuid;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("hp")
    @Expose
    public String hp;

    @SerializedName("ktp")
    @Expose
    public String ktp;

    @SerializedName("jabatan")
    @Expose
    public String jabatan;

    @SerializedName("nama")
    @Expose
    public String nama;

    @SerializedName("tanggal_lahir")
    @Expose
    public String tanggal_lahir;

    @SerializedName("waktu_insert")
    @Expose
    public String waktu_insert;

    @SerializedName("status")
    @Expose
    public String status;

    @SerializedName("photo")
    @Expose
    public String photo;


}
