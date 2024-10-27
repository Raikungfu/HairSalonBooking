package com.prmproject.hairsalonbooking.data.model.dataToSend;

public class UserLogin {
    private String Username;
    private String Password;

    public UserLogin(String username, String password) {
        this.Username = username;
        this.Password = password;
    }
}