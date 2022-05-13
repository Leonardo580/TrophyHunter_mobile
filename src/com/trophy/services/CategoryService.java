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
import com.trophy.entity.Category;
import com.trophy.entity.Games;
import com.trophy.entity.Product;
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 *
 * @author rihab bns
 */
public class CategoryService {
      public static CategoryService instance ;
    public ArrayList<Category>categories;
    private ConnectionRequest req;
    public  boolean resultOk;
    public static CategoryService getInstance(){
    if (instance == null)
        instance = new CategoryService();
    return instance;}
    
    public CategoryService(){
    req = new ConnectionRequest();}
    
    //hethy request
     private void setRequest(Category c){
         req.addArgument("idCategory",String.valueOf(c.getId_category()));
         req.addArgument("category",String.valueOf(c.getCategory()));
     }
     
     
     public ArrayList<Category>parseJSON(String jsonText) throws IOException{
     try{
        categories =new ArrayList<>();
        JSONParser j = new JSONParser();
            Map<String,Object> gamesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)gamesListJson.get("root");
           categories.clear();
           
            for (Map<String,Object> obj: list){
                Category cat = new Category();
              float id_category = Float.parseFloat(obj.get("idCategory").toString());
                //int id_category = Integer.parseInt(obj.get("idCategory").toString());
                String category = obj.get("category").toString();
                //float price = Float.parseFloat(obj.get("price).toString());
                
                cat.setId_category((int)id_category);
                cat.setCategory(category);
                categories.add(cat);
                
            }
     }catch (IOException ex){
                    }
     return categories;
     }
     
     
     
     
     //add
     public boolean addCategory(Category c){
        
        String url =Statics.BASE_URL+"addcat";
        req.setUrl(url);
        setRequest(c);
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
    //display
    public ArrayList<Category>displayCategories(){
   
    
    String url = Statics.BASE_URL+"categoryjson";
    req.setUrl(url);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent arg0) {
              try {
                 categories= parseJSON(new String(req.getResponseData()));
              }
             catch (IOException ex) {
                     //Logger.getLogger(GamesService.class.getName()).log(Level.SEVERE, null, ex);
                 }
            req.removeResponseListener(this);
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    
    }
    
    public boolean deletecategory(Category cat){
    String url = Statics.BASE_URL+"categorydelete";
    req.setUrl(url);
    req.addArgument("idCategory",String.valueOf(cat.getId_category()));
    req.addResponseListener(new ActionListener<NetworkEvent>(){
     @Override
            public void actionPerformed(NetworkEvent arg0) {
                resultOk=req.getResponseCode()==200;
           req.removeResponseListener(this);
            }
        });
            
     
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;}
    
     

    
    
    public boolean updatecategory(Category cat){
        
         String url=Statics.BASE_URL+"updatecat";
        req.setUrl(url);
        setRequest(cat);
        
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
    
}
