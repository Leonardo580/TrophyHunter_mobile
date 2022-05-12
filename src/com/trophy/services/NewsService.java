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
import com.codename1.ui.Form;

import com.codename1.ui.events.ActionListener;

import com.trophy.entity.Games;
import com.trophy.entity.Category;
import com.trophy.entity.News;
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
public class NewsService {
    public ArrayList<News> news;
    public static NewsService instance;
    public boolean resultOk;

    Form add;
    private ConnectionRequest req;
    private NewsService(){
        req=new ConnectionRequest();
    }
    public static NewsService getInstance(){
        if (instance==null)
            instance=new NewsService();
        return instance;
    }
    private void setRequest(News n){
        req.addArgument("content",String.valueOf(n.getContent()));
        req.addArgument("headline",String.valueOf(n.getHeadline()));
        req.addArgument("img",String.valueOf(n.getImgurl()));
        
    }
    public boolean addNews(News n){
        String url=Statics.BASE_URL+"mobile/addNews";
        req.setUrl(url);
        setRequest(n);
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
    public boolean updateNews(News n){
        String url=Statics.BASE_URL+"mobile/updateNews";
        req.setUrl(url);
        req.addArgument("idnews",String.valueOf(n.getNewsid()));
        setRequest(n);
        
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
    public boolean deleteNews(News n){
        String url=Statics.BASE_URL+"news/mobile/deleteNews";
        req.setUrl(url);
        System.out.println(n.getNewsid());
        System.out.println(n.toString());
        req.addArgument("idNews",String.valueOf(n.getNewsid()));
        req.addResponseListener(new ActionListener < NetworkEvent >() {
           
            @Override
            public void actionPerformed(NetworkEvent arg0) {
                System.out.println(req.getResponseErrorMessage());
                resultOk=req.getResponseCode()==200;
           req.removeResponseListener(this);
            }
 
        });
            
     
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    public ArrayList<News> parseJSON(String jsonText) throws  IOException{
         try {

            news=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> NewsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
             
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)NewsListJson.get("root");
           news.clear();
            for(Map<String,Object> obj : list){
                News ns = new News();
                float id = Float.parseFloat(obj.get("idNews").toString());
                ns.setNewsid((int)id);
                ns.setContent(obj.get("content").toString());
                String headline=obj.get("headline").toString();
                ns.setHeadline(headline);
                ns.setImgurl(obj.get("img").toString());
                news.add(ns);
                  
            }
            
            
        } catch (IOException ex) {
            
        }
        return news;
    }
    public ArrayList<News> getAllNews(){
         String url=Statics.BASE_URL+"mobile/getNews";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent arg0) {
           System.out.println(req.getResponseCode());
              try {

                 news= parseJSON(new String(req.getResponseData()));
              }
             catch (IOException ex) {
                     //Logger.getLogger(NewsService.class.getName()).log(Level.SEVERE, null, ex);
                 }
            req.removeResponseListener(this);
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return news;
    }
}
