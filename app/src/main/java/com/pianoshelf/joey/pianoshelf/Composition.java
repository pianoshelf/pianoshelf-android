package com.pianoshelf.joey.pianoshelf;

import com.google.gson.JsonArray;

/**
 * Created by joey on 12/13/14.
 */
public class Composition {
    // Files fetched from network
    private int id;
    private String title;
    private String style;
    private String key;
    private String date;
    private int file_size;
    private String composer_name;
    private String description;
    private int pop;
    private String uniqueurl;
    private String sheetmusic_pdf_url;
    private String thumbnail_url;
    private int difficulty;
    private String[] images;
    private JsonArray videos;
    private JsonArray comments;

    // Custom fields added offline
    private String[] offline_files;

    // Getters and Setters, auto-generated by android studio
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public String getComposer_name() {
        return composer_name;
    }

    public void setComposer_name(String composer_name) {
        this.composer_name = composer_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public String getUniqueurl() {
        return uniqueurl;
    }

    public void setUniqueurl(String uniqueurl) {
        this.uniqueurl = uniqueurl;
    }

    public String getSheetmusic_pdf_url() {
        return sheetmusic_pdf_url;
    }

    public void setSheetmusic_pdf_url(String sheetmusic_pdf_url) {
        this.sheetmusic_pdf_url = sheetmusic_pdf_url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public JsonArray getVideos() {
        return videos;
    }

    public void setVideos(JsonArray videos) {
        this.videos = videos;
    }

    public JsonArray getComments() {
        return comments;
    }

    public void setComments(JsonArray comments) {
        this.comments = comments;
    }

    public String[] getOffline_files() {
        return offline_files;
    }

    public void setOffline_files(String[] offline_files) {
        this.offline_files = offline_files;
    }
}