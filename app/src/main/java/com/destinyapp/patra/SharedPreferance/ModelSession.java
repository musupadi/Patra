package com.destinyapp.patra.SharedPreferance;

public class ModelSession {
    private String id;
    private String random_unik;
    private String email;
    private String oauth_uid;
    private String oauth_provider;
    private String username;
    private String full_name;
    private String ktp;
    private String avatar;
    private String banned;
    private String last_login;
    private String last_activity;
    private String date_created;
    private String forgot_exp;
    private String remember_time;
    private String remember_exp;
    private String verification_code;
    private String top_secret;
    private String ip_address;
    private String password;
    private String token;

    public ModelSession(){

    }
    public ModelSession(String random_unik,
                        String id,
                        String email,
                        String username,
                        String full_name,
                        String avatar,
                        String token){
        this.random_unik=random_unik;
        this.id=id;
        this.email=email;
        this.username=username;
        this.full_name=full_name;
        this.avatar=avatar;
        this.token=token;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
