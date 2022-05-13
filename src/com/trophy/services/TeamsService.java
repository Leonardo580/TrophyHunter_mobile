/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.trophy.entity.Competitions;
import com.trophy.entity.Teams;
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Syrine
 */
public class TeamsService {

public static TeamsService instance = null;
      public ArrayList<Teams>teams;
      private ConnectionRequest req;
    
    
    public static TeamsService getInstance(){
    if (instance == null)
        instance = new TeamsService();
    return instance;}
    private boolean resultOk;
    
    public TeamsService(){
    req = new ConnectionRequest();}

    
    
            public void addTeams(Teams teams){
        
        String url =Statics.BASE_URL+"mobile/addTeams?" 
                +"idCompetion="+teams.getCompetitions().getId_competion()
                +"&teamName="+teams.getTeam_name();
           
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
                String str = new String(req.getResponseData());//json Response
                System.out.println("data == "+ str);
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution
}
            
                   //Delete 
    public boolean deleteTeams(int id) {
        String url = Statics.BASE_URL + "deleteTeamsMobile?idTeam=" + id;

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
    
       //Update 
    public boolean updateTeams (Teams teams) {
        String url = Statics.BASE_URL + "mobile/updateTeams?idTeam=" 
                +teams.getId_team()
                +"&gameName="+teams.getCompetitions().getGame_name()
                +"&teamName="+teams.getTeam_name()
                +"&idCompetion="+teams.getCompetitions().getId_competion();
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;

    }
    
    
    public ArrayList<Teams> parseJSON(String jsonText) throws  IOException{
         try {
             
            teams=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> gamesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)gamesListJson.get("root");
           teams.clear();
//             ObjectMapper ob=new ObjectMapper();
//             JsonNode jn=ob.readTree(Json);
            
            for(Map<String,Object> obj : list){
                Teams t = new Teams();
                
                      float idTeam = Float.parseFloat(obj.get("idTeam").toString());
               t.setId_team(((int)idTeam));
              String teamName = obj.get("teamName").toString();
              t.setTeam_name(teamName);
            
          
              String comp =((Map<String,Object>)obj.get("idCompetion")).get("gameName").toString();
              int id=(int)Float.parseFloat(((Map<String,Object>)obj.get("idCompetion")).get("idCompetion").toString());

              t.setCompetitions(new Competitions(id,comp));
                
                
//             String id=obj.get("idTeam").toString().substring(0,
//                     obj.get("idTeam").toString().indexOf("."));
//                t.setId_team(Integer.parseInt(id));
//                
//                String comp=((Map<String,Object>)obj.get("idCompetion")).get("gameName").toString();
//                id=((Map<String,Object>)obj.get("idCompetion")).get("idCompetion").toString();
//               id=id.substring(0,
//                     id.indexOf("."));
//               
//                t.setCompetitions(new Competitions(Integer.parseInt(id),comp));
//                
//                t.setTeam_name(obj.get("teamName").toString());
                
                teams.add(t);
                
                
                  
            }
  
        } catch (IOException ex) {
            
        }
        return teams;
        
    }
    
    
        
    
        public ArrayList<Teams> getAllTeams(){
         String url=Statics.BASE_URL+"Teams/getTeams";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent arg0) {
              try {
                 teams= parseJSON(new String(req.getResponseData()));
              }
             catch (IOException ex) {
                     //Logger.getLogger(GamesService.class.getName()).log(Level.SEVERE, null, ex);
                 }
            req.removeResponseListener(this);
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return teams;
    }
    
    
            
            
            
            
            
            
            
    
}

