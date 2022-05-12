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
public class News {
    
    private int newsid;
    private String headline;
    private String content;
    private String imgurl;

    public News() {
    }
    

    public News(String headline, String content, String imgurl) {
        this.headline = headline;
        this.content = content;
        this.imgurl = imgurl;
    }

    public News(int newsid, String headline, String content, String imgurl) {
        this.newsid = newsid;
        this.headline = headline;
        this.content = content;
        this.imgurl = imgurl;
    }

    public int getNewsid() {
        return newsid;
    }

    public void setNewsid(int newsid) {
        this.newsid = newsid;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.newsid;
        hash = 29 * hash + Objects.hashCode(this.headline);
        hash = 29 * hash + Objects.hashCode(this.content);
        hash = 29 * hash + Objects.hashCode(this.imgurl);
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
        final News other = (News) obj;
        if (this.newsid != other.newsid) {
            return false;
        }
        if (!Objects.equals(this.headline, other.headline)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.imgurl, other.imgurl)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "News{" + "newsid=" + newsid + ", headline=" + headline + ", content=" + content + ", imgurl=" + imgurl + '}';
    }
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
