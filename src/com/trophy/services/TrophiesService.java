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
import com.trophy.entity.Trophies;
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anasb
 */
public class TrophiesService {
     public ArrayList<Trophies> trophies;
    public static TrophiesService instance;
    public boolean resultOk;
    private ConnectionRequest req;
    private TrophiesService(){
        req=new ConnectionRequest();
    }
    public static TrophiesService getInstance(){
        if (instance==null)
            instance=new TrophiesService();
        return instance;
    }
    private void setRequest(Trophies g){
        req.addArgument("idTrophy",String.valueOf(g.getId_trophy()));
        req.addArgument("title",String.valueOf(g.getTitle()));
        req.addArgument("difficulity",String.valueOf(g.getDifficulty()));
        req.addArgument("description",String.valueOf(g.getDescription()));
        req.addArgument("platform",String.valueOf(g.getPlatform()));
        req.addArgument("idGame",String.valueOf(g.getGame().getId_game()));
    }
    public boolean addtrophy(Trophies g){
        String url=Statics.BASE_URL+"admin/mobile/addTrophies";
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
    public boolean updatetrophy(Trophies g){
        String url=Statics.BASE_URL+"admin/mobile/updateTrophies";
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
    public boolean deletetrophy(Trophies g){
        String url=Statics.BASE_URL+"admin/mobile/deleteTrophies";
        req.setUrl(url);
        req.addArgument("idTrophy",String.valueOf(g.getId_trophy()));
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
    public ArrayList<Trophies> parseJSON(String jsonText) throws  IOException{
         try {
            trophies=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> trophiesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)trophiesListJson.get("root");
            trophies.clear();
            for(Map<String,Object> obj : list){
                Trophies t = new Trophies();
             String id=obj.get("idTrophy").toString().substring(0,
                     obj.get("idTrophy").toString().indexOf("."));
             t.setId_trophy(Integer.parseInt(id));
             t.setTitle(obj.get("title").toString());
             t.setDescription(obj.get("description").toString());
             t.setDifficulty(obj.get("difficulity").toString());
             //t.setGame(game);
             t.setPlatform(obj.get("platform").toString());
             
                trophies.add(t);
                  
            }
            
            
        } catch (IOException ex) {
            
        }
        return trophies;
    }
    public ArrayList<Trophies> getAllTrophies(){
         String url=Statics.BASE_URL+"mobile/getTrophies";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent arg0) {
              try {
                 trophies= parseJSON(new String(req.getResponseData()));
              }
             catch (IOException ex) {
                     //Logger.getLogger(TrophiesService.class.getName()).log(Level.SEVERE, null, ex);
                 }
            req.removeResponseListener(this);
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return trophies;
    }
    
}
