package com.nilsedgar;

import java.io.Serializable;
import java.util.ArrayList;

public class UserCustomer extends User implements Serializable {

    long serialVersionUID = 1;
    ArrayList<Book> borrowedBooks = new ArrayList<>();
    private String password = "password";

    public UserCustomer(String name) {
        super(name);
    }

    public String getPassword() {
        return password;
    }
}
