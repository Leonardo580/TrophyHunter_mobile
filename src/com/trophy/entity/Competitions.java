/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.entity;


import java.util.Objects;



/**
 *
 * @author Syrine
 */
public class Competitions {
 
    private int Id_competion;
    private String game_name;
    private String dateofcomp;

    public Competitions() {
    }

    public Competitions(int Id_competion, String game_name) {
        this.Id_competion = Id_competion;
        this.game_name = game_name;
    }
   
    public Competitions(String game_name, String dateofcomp) {
     
        this.game_name = game_name;
        this.dateofcomp = dateofcomp;
    }
   
    public int getId_competion() {
        return Id_competion;
    }

    public void setId_competion(int Id_competion) {
        this.Id_competion = Id_competion;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getDateofcomp() {
        return dateofcomp;
    }

    public void setDateofcomp(String dateofcomp) {
        this.dateofcomp = dateofcomp;
    }

//    public SimpleStringProperty getGame_namee() {
//        return game_namee;
//    }
    

    @Override
    public String toString() {
        return  game_name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.Id_competion;
        hash = 79 * hash + Objects.hashCode(this.game_name);
        hash = 79 * hash + Objects.hashCode(this.dateofcomp);
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
        final Competitions other = (Competitions) obj;
        if (this.Id_competion != other.Id_competion) {
            return false;
        }
        if (!Objects.equals(this.game_name, other.game_name)) {
            return false;
        }
        if (!Objects.equals(this.dateofcomp, other.dateofcomp)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}





