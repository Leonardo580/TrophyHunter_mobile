package com.trophy.entity;


public class Trophies {
    @Override
    public String toString() {
        return "Trophies{" +
                "id_trophy=" + id_trophy +
                ", game=" + game +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", platform='" + platform + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }

    private int id_trophy;
    private Games game;
    private String title,description,platform;
    private String difficulty;

    public Trophies() {
    }

    public Trophies(Games game, String title, String description, String platform, String difficulty) {
        this.game = game;
        this.title = title;
        this.description = description;
        this.platform = platform;
        this.difficulty = difficulty;
    }

    public Trophies(int id_trophy, Games game, String title, String description, String platform, String difficulty) {
        this.id_trophy = id_trophy;
        this.game = game;
        this.title = title;
        this.description = description;
        this.platform = platform;
        this.difficulty = difficulty;
    }

    public int getId_trophy() {
        return id_trophy;
    }

    public void setId_trophy(int id_trophy) {
        this.id_trophy = id_trophy;
    }

    public Games getGame() {
        return game;
    }

    public void setGame(Games game) {
        this.game = game;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
