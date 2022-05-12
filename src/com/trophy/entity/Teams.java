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
public class Teams {
     private int id_team;
     private Competitions competitions;
     private String team_name;
   

    public Teams() { 
    }

    public Teams(int id_team, Competitions competitions, String team_name) {
        this.id_team = id_team;
        this.competitions = competitions;
        this.team_name = team_name;
        
    }

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public Competitions getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Competitions competitions) {
        this.competitions = competitions;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

//    public String getCreator() {
//        return creator;
//    }
//
//    public void setCreator(String creator) {
//        this.creator = creator;
//    }

//    @Override
//    public String toString() {
//        return "Teams{" + "id_team=" + id_team + ", competitions=" + competitions + ", team_name=" + team_name + ", creator=" + creator + '}';
//    }

    @Override
    public String toString() {
        return "Teams{" + "id_team=" + id_team + ", competitions=" + competitions + ", team_name=" + team_name + '}';
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id_team;
        hash = 79 * hash + Objects.hashCode(this.competitions);
        hash = 79 * hash + Objects.hashCode(this.team_name);
       // hash = 79 * hash + Objects.hashCode(this.creator);
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
        final Teams other = (Teams) obj;
        if (this.id_team != other.id_team) {
            return false;
        }
        if (!Objects.equals(this.team_name, other.team_name)) {
            return false;
        }
//        if (!Objects.equals(this.creator, other.creator)) {
//            return false;
//        }
        if (!Objects.equals(this.competitions, other.competitions)) {
            return false;
        }
        return true;
    }
     
}
