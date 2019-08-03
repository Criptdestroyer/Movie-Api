package com.criptdestroyer.moviecatalogueapi.model;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieItems {
    private int id;
    private String title;
    private String description;
    private String date;
    private String photo;

    public MovieItems(JSONObject object){
        try{
            this.id = object.getInt("id");
            this.title = object.getString("title");
            this.description= object.getString("overview");
            this.date= object.getString("release_date");
            this.photo= object.getString("backdrop_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
