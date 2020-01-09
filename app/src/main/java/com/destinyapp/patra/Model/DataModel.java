package com.destinyapp.patra.Model;

public class DataModel {
    String id,random_unik,email,oauth_uid,oauth_provider,username,full_name,ktp,avatar,banned,last_login,last_activity,date_created,forgot_exp,remember_time,remember_exp,verification_code,top_secret,ip_address;
    String xApiKey = "3899CE8456DEE44F894044EDB678969F";
    String BaseURL = "http://patra.doktorsiaga.co.id/api/";

    public String getBaseURL() {
        return BaseURL;
    }

    public void setBaseURL(String baseURL) {
        BaseURL = baseURL;
    }

    boolean status;
    public String getxApiKey() {
        return xApiKey;
    }

    public void setxApiKey(String xApiKey) {
        this.xApiKey = xApiKey;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRandom_unik() {
        return random_unik;
    }

    public void setRandom_unik(String random_unik) {
        this.random_unik = random_unik;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOauth_uid() {
        return oauth_uid;
    }

    public void setOauth_uid(String oauth_uid) {
        this.oauth_uid = oauth_uid;
    }

    public String getOauth_provider() {
        return oauth_provider;
    }

    public void setOauth_provider(String oauth_provider) {
        this.oauth_provider = oauth_provider;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBanned() {
        return banned;
    }

    public void setBanned(String banned) {
        this.banned = banned;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getLast_activity() {
        return last_activity;
    }

    public void setLast_activity(String last_activity) {
        this.last_activity = last_activity;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getForgot_exp() {
        return forgot_exp;
    }

    public void setForgot_exp(String forgot_exp) {
        this.forgot_exp = forgot_exp;
    }

    public String getRemember_time() {
        return remember_time;
    }

    public void setRemember_time(String remember_time) {
        this.remember_time = remember_time;
    }

    public String getRemember_exp() {
        return remember_exp;
    }

    public void setRemember_exp(String remember_exp) {
        this.remember_exp = remember_exp;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public String getTop_secret() {
        return top_secret;
    }

    public void setTop_secret(String top_secret) {
        this.top_secret = top_secret;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
}
