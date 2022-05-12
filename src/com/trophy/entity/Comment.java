/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trophy.entity;

import java.util.Objects;


/**
 *
 * @author Mouelhi
 */
public class Comment {
    
    private int idcomm;
    private String comcontent;
    private int likes;
    private int dislikes;
    private News news;

    public Comment() {
    }

    public Comment(String comcontent, int likes, int dislikes,News news) {
        this.comcontent = comcontent;
        this.likes = 0;
        this.dislikes = 0;
        this.news=news;
    }

    public Comment(int idcomm, String comcontent, int likes, int dislikes,News news) {
        this.idcomm = idcomm;
        this.comcontent = comcontent;
        this.likes = 0;
        this.dislikes = 0;
        this.news=news;
    }

    public int getIdcomm() {
        return idcomm;
    }

    public void setIdcomm(int idcomm) {
        this.idcomm = idcomm;
    }

    public String getComcontent() {
        return comcontent;
    }

    public void setComcontent(String comcontent) {
        this.comcontent = comcontent;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "Comment{" + "idcomm=" + idcomm + ", comcontent=" + comcontent + ", likes=" + likes + ", dislikes=" + dislikes + '}';
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.idcomm;
        hash = 89 * hash + Objects.hashCode(this.comcontent);
        hash = 89 * hash + this.likes;
        hash = 89 * hash + this.dislikes;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comment other = (Comment) obj;
        if (this.idcomm != other.idcomm) {
            return false;
        }
        if (this.likes != other.likes) {
            return false;
        }
        if (this.dislikes != other.dislikes) {
            return false;
        }
        if (!Objects.equals(this.comcontent, other.comcontent)) {
            return false;
        }
        return true;
    }
    
    
    
    

    
}
