/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trophy.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
/*import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;*/

import com.trophy.entity.Games;
import com.trophy.entity.Category;
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
/**
 *
 * @author anasb
 */
public class GamesService {
    public ArrayList<Games> games;
    public static GamesService instance;
    public boolean resultOk;
    private ConnectionRequest req;
    private GamesService(){
        req=new ConnectionRequest();
    }
    public static GamesService getInstance(){
        if (instance==null)
            instance=new GamesService();
        return instance;
    }
    private void setRequest(Games g){
        req.addArgument("idGame",String.valueOf(g.getId_game()));
        req.addArgument("category",String.valueOf(g.getCategory().getCategory()));
        req.addArgument("name",String.valueOf(g.getName()));
        req.addArgument("description",String.valueOf(g.getDescription()));
        req.addArgument("rate",String.valueOf(g.getRate()));
        req.addArgument("img",String.valueOf(g.getImg()));

    }
    public boolean addGame(Games g){
        String url=Statics.BASE_URL+"admin/mobile/addGames";
        req.setUrl(url);
        setRequest(g);
        req.addResponseListener((new ActionListener < NetworkEvent >() {
            @Override
            public void actionPerformed(NetworkEvent arg0) {
                resultOk=req.getResponseCode()==200;
            req.removeResponseListener(this);
            }
        })
            
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    public boolean updateGame(Games g){
        String url=Statics.BASE_URL+"admin/mobile/updateGames";
        req.setUrl(url);
        setRequest(g);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent arg0) {
                resultOk=req.getResponseCode()==200;
           req.removeResponseListener(this);
            }
        }  
            
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    public boolean deleteGame(Games g){
        String url=Statics.BASE_URL+"admin/mobile/deleteGames";
        req.setUrl(url);
        req.addArgument("idGame",String.valueOf(g.getId_game()));
        req.addResponseListener(new ActionListener < NetworkEvent >() {
            @Override
            public void actionPerformed(NetworkEvent arg0) {
                resultOk=req.getResponseCode()==200;
           req.removeResponseListener(this);
            }
        });
            
     
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    public ArrayList<Games> parseJSON(String jsonText) throws  IOException{
         try {
             
            games=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> gamesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)gamesListJson.get("root");
           games.clear();
//             ObjectMapper ob=new ObjectMapper();
//             JsonNode jn=ob.readTree(Json);
            
            for(Map<String,Object> obj : list){
                Games t = new Games();
             String id=obj.get("idGame").toString().substring(0,
                     obj.get("idGame").toString().indexOf("."));
                t.setId_game(Integer.parseInt(id));
                t.setName(obj.get("name").toString());
                if (obj.get("Category")!=null) {
                    String cat = ((Map<String, Object>) obj.get("Category")).get("category").toString();
                    id = ((Map<String, Object>) obj.get("Category")).get("idCategory").toString();
                    id = id.substring(0,
                            id.indexOf("."));
                    t.setCategory(new Category(Integer.parseInt(id), cat));
                }
                t.setDescription(obj.get("description").toString());
                t.setRate(Float.parseFloat(obj.get("rate").toString()));
                t.setImg(obj.get("img").toString());
                games.add(t);
                  
            }
            
            
        } catch (IOException ex) {
            
        }
        return games;
    }
    public ArrayList<Games> getAllGames(){
         String url=Statics.BASE_URL+"mobile/getGames";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent arg0) {
              try {
                 games= parseJSON(new String(req.getResponseData()));
              }
             catch (IOException ex) {
                     //Logger.getLogger(GamesService.class.getName()).log(Level.SEVERE, null, ex);
                 }
            req.removeResponseListener(this);
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return games;
    }
    public Games parseOne(String jsonText) throws  IOException{
        Games t = new Games(); 
        try {
             
 
            
            JSONParser j = new JSONParser();
            Map<String,Object> gamesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            Map<String,Object>obj = (Map<String,Object>)gamesListJson.get("root");
         
//             ObjectMapper ob=new ObjectMapper();
//             JsonNode jn=ob.readTree(Json);
            
           
                
             String id=obj.get("idGame").toString().substring(0,
                     obj.get("idGame").toString().indexOf("."));
                t.setId_game(Integer.parseInt(id));
                t.setName(obj.get("name").toString());
                String cat=((Map<String,Object>)obj.get("Category")).get("category").toString();
                id=((Map<String,Object>)obj.get("Category")).get("idCategory").toString();
               id=id.substring(0,
                     id.indexOf("."));
                t.setCategory(new Category(Integer.parseInt(id),cat));
                t.setDescription(obj.get("description").toString());
                t.setRate(Float.parseFloat(obj.get("rate").toString()));
                t.setImg(obj.get("img").toString());
              
                  
            
            
            
        } catch (IOException ex) {
            
        }
        return t;
    }
    public void fetchOnline(Games g){
        String url=Statics.BASE_URL+"admin/fetchOnline/"+g.getId_game();
        req.setUrl(url);
        req.addResponseListener(new ActionListener < NetworkEvent >() {
            @Override
            public void actionPerformed(NetworkEvent arg0) {
                resultOk=req.getResponseCode()==200;
           req.removeResponseListener(this);
            }
        });
            
     
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void TranslateOnline(Games g){
        String url=Statics.BASE_URL+"admin/translate/"+g.getId_game();
        req.setUrl(url);
        req.addResponseListener(new ActionListener < NetworkEvent >() {
            @Override
            public void actionPerformed(NetworkEvent arg0) {
                resultOk=req.getResponseCode()==200;
           req.removeResponseListener(this);
            }
        });

     
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     public Games getOneGames(){
         String url=Statics.BASE_URL+"mobile/getOneGames";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent arg0) {
              try {
                 games= parseJSON(new String(req.getResponseData()));
              }
             catch (IOException ex) {
                     //Logger.getLogger(GamesService.class.getName()).log(Level.SEVERE, null, ex);
                 }
            req.removeResponseListener(this);
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return games.get(0);
    }

}
