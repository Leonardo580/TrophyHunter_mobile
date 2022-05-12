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
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Syrine
 */
public class CompetitionsService {
      public static CompetitionsService instance = null;
      public ArrayList<Competitions>competitions;
      private ConnectionRequest req;
    
    
    public static CompetitionsService getInstance(){
    if (instance == null)
        instance = new CompetitionsService();
    return instance;}
    private boolean resultOk;
    
    public CompetitionsService(){
    req = new ConnectionRequest();}
    
    
        public void addCompetitions(Competitions competitions){
        
        String url =Statics.BASE_URL+"mobile/addCompetitions?"
                +"gameName="+competitions.getGame_name()
                +"&dateofcomp="+competitions.getDateofcomp();

        
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
                String str = new String(req.getResponseData());//json Response
                System.out.println("data == "+ str);
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution
}
        
 
    
        //Delete 
    public boolean deleteCompetitions(int id) {
        String url = Statics.BASE_URL + "mobile/deleteCompetitions?idCompetion=" + id;

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
    public boolean updateCompetitions (Competitions competitions) {
        String url = Statics.BASE_URL + "mobile/updateCompetitions?idCompetion=" + competitions.getId_competion() +"&gameName="+competitions.getGame_name()+"&dateofcomp="+competitions.getDateofcomp();
       
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
    
    public ArrayList<Competitions> parseJSON(String jsonText) throws  IOException{
         try {
             
            competitions=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> gamesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
             List<Map<String,Object>> list = (List<Map<String,Object>>)gamesListJson.get("root");
           competitions.clear();
//             ObjectMapper ob=new ObjectMapper();
//             JsonNode jn=ob.readTree(Json);
            
            for(Map<String,Object> obj : list){
                Competitions c = new Competitions();
             String id=obj.get("idCompetion").toString().substring(0,
                     obj.get("idCompetion").toString().indexOf("."));
                c.setId_competion(Integer.parseInt(id));
                c.setGame_name(obj.get("gameName").toString());  
                c.setDateofcomp(obj.get("dateofcomp").toString());
                
                competitions.add(c);       
            }
            
        } catch (IOException ex) {
            
        }
        return competitions;
    }
    
    
        public ArrayList<Competitions> getAllCompetitions(){
         String url=Statics.BASE_URL+"Competitions/getCompetitions";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent arg0) {
              try {
                 competitions= parseJSON(new String(req.getResponseData()));
              }
             catch (IOException ex) {
                     //Logger.getLogger(GamesService.class.getName()).log(Level.SEVERE, null, ex);
                 }
            req.removeResponseListener(this);
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return competitions;
    }
   
    
    

    
  
}
