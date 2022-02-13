package com.example.dakirni.sonObjects;

public class SonResponse {
    private String name;
    private String email;
    private String password;
    private String _id;

    public SonResponse(String name, String email, String password, String _id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this._id = _id;
    }

    public SonResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}

