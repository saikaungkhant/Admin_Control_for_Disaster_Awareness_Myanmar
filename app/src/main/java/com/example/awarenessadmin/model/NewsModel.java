package com.example.awarenessadmin.model;

public class NewsModel {
    private String id;
    private String title;
    private String description;
    private String date;
    private String important;

    public NewsModel() {
    }

    public NewsModel(String id, String title, String description, String date, String important) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.important = important;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImportant() {
        return important;
    }

    public void setImportant(String important) {
        this.important = important;
    }
}