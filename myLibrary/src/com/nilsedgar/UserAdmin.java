package com.nilsedgar;

import java.io.Serializable;

public class UserAdmin extends User implements Serializable {

    long serialVersionUID = 1;
    private String password = "password";

    public UserAdmin(String name) {
        super(name);
    }

    public String getPassword() {
        return password;
    }
}
