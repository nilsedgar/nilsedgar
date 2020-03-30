package com.nilsedgar;

import java.io.Serializable;

public class Book implements Serializable {

    User borrower;
    long serialVersionUID = 1;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable = true;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public boolean getIsAvailable(){
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getGenre() {
        return genre;
    }

}
