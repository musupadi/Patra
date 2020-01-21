package com.destinyapp.patra.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("random_unik")
    @Expose
    public String random_unik;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("oauth_uid")
    @Expose
    public String oauth_uid;

    @SerializedName("oauth_provider")
    @Expose
    public String oauth_provider;

    @SerializedName("username")
    @Expose
    public String username;

    @SerializedName("full_name")
    @Expose
    public String full_name;

    @SerializedName("ktp")
    @Expose
    public String ktp;

    @SerializedName("avatar")
    @Expose
    public String avatar;

    @SerializedName("banned")
    @Expose
    public String banned;

    @SerializedName("last_login")
    @Expose
    public String last_login;

    @SerializedName("last_activity")
    @Expose
    public String last_activity;

    @SerializedName("date_created")
    @Expose
    public String date_created;

    @SerializedName("forgot_exp")
    @Expose
    public String forgot_exp;

    @SerializedName("remember_time")
    @Expose
    public String remember_time;

    @SerializedName("remember_exp")
    @Expose
    public String remember_exp;

    @SerializedName("verification_code")
    @Expose
    public String verification_code;

    @SerializedName("top_secret")
    @Expose
    public String top_secret;

    @SerializedName("ip_address")
    @Expose
    public String ip_address;

    @SerializedName("patra_project")
    List<PatraProject> patraProject;

    @SerializedName("patra_marketing")
    List<PatraMarketing> patraMarketing;

    @SerializedName("patra_side_supervisor")
    List<PatraSupervisor> patra_side_supervisor;

    @SerializedName("patra_depot")
    List<PatraDepot> patra_depot;


    public List<PatraProject> getPatraProject() {
        return patraProject;
    }

    public void setPatraProject(List<PatraProject> patraProject) {
        this.patraProject = patraProject;
    }

    public List<PatraMarketing> getPatraMarketing() {
        return patraMarketing;
    }

    public void setPatraMarketing(List<PatraMarketing> patraMarketing) {
        this.patraMarketing = patraMarketing;
    }

    public List<PatraSupervisor> getPatra_side_supervisor() {
        return patra_side_supervisor;
    }

    public void setPatra_side_supervisor(List<PatraSupervisor> patra_side_supervisor) {
        this.patra_side_supervisor = patra_side_supervisor;
    }

    public List<PatraDepot> getPatra_depot() {
        return patra_depot;
    }

    public void setPatra_depot(List<PatraDepot> patra_depot) {
        this.patra_depot = patra_depot;
    }
}
