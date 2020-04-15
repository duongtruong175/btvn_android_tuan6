package com.example.gmaildisplay;

public class ContactModel {
    String color;
    String name;
    String subject;
    String content;
    String time;
    boolean isFavorite;

    public ContactModel(String color, String name, String subject, String content, String time) {
        this.color = color;
        this.name = name;
        this.subject = subject;
        this.content = content;
        this.time = time;
        this.isFavorite = false;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
