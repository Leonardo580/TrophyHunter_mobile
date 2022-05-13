/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author lenovo
 */
public class SessionManager {
    
     
    public static Preferences pref ;
    private  static int ID_USER;
    private  static String FULL_NAME;
    private  static String EMAIL;
    private static String PASSWORD;
    private static int ISACTIVE;
    private static int PRIVILEGE_; 
    private static String img;

    
    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("ID_USER",ID_USER);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("ID_USER",ID_USER);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getUserName() {
        return pref.get("FULL_NAME",FULL_NAME);
    }

    public static void setUserName(String userName) {
         pref.set("FULL_NAME",FULL_NAME);
    }

    public static String getEmail() {
        return pref.get("EMAIL",EMAIL);
    }

    public static void setEmail(String email) {
         pref.set("EMAIL",EMAIL);
    }

    public static String getPassowrd() {
        return pref.get("PASSWORD",PASSWORD);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getPhoto() {
        return pref.get("img",img);
    }

    public static void setPhoto(String photo) {
         pref.set("img",img);
    }

    public static int getISACTIVE() {
      return pref.get("ISACTIVE",ISACTIVE);
    }

    public static int getPRIVILEGE_() {
      return pref.get("PRIVILEGE_",PRIVILEGE_);
     
    }

    public static void setISACTIVE(int ISACTIVE) {
       pref.set("ISACTIVE",ISACTIVE);
    }

    public static void setPRIVILEGE_(int PRIVILEGE_) {
    pref.set("PRIVILEGE_",PRIVILEGE_);
    }
    
    
    
    
    
}
