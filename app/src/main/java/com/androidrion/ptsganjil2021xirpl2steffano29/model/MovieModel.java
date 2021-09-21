package com.androidrion.ptsganjil2021xirpl2steffano29.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MovieModel extends RealmObject {

    private String original_title, release_date, overview, poster_path;

    public MovieModel(String original_title, String release_date, String overview, String poster_path) {
        this.original_title = original_title;
        this.release_date = release_date;
        this.overview = overview;
        this.poster_path = poster_path;
    }

    public MovieModel() {

    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

}
