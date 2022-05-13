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
import com.trophy.entity.Comment;
import com.trophy.entity.News;
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ayoub
 */
public class CommentsService {
       public ArrayList<Comment> comments;

    public boolean resultOk;
    private ConnectionRequest req;
    private CommentsService(){
        req=new ConnectionRequest();
    }
 public static CommentsService instance;
    public static CommentsService getInstance(){
        if (instance==null)
            instance=new CommentsService();
        return instance;
    }
    private void setRequest(Comment c){
        req.addArgument("comment",String.valueOf(c.getComcontent()));
//        req.addArgument("likes",String.valueOf(0));
        req.addArgument("idnews",String.valueOf(47));
        
        
    }


public ArrayList<Comment> getAllComments(){
         String url=Statics.BASE_URL+"mobile/getComments";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent arg0) {
           System.out.println(req.getResponseCode());
              try {
              
                 comments= parseJSON(new String(req.getResponseData()));
              }
             catch (IOException ex) {
                     //Logger.getLogger(NewsService.class.getName()).log(Level.SEVERE, null, ex);
                 }
            req.removeResponseListener(this);
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }



public ArrayList<Comment> parseJSON(String jsonText) throws  IOException{
         try {

            comments=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> NewsListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
             
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)NewsListJson.get("root");
           comments.clear();
            for(Map<String,Object> obj : list){
                Comment c = new Comment();
                float id = Float.parseFloat(obj.get("idComment").toString());
                c.setIdcomm((int)id);
                Map<String,Object> mso=(Map<String,Object>)obj.get("idNews");
                c.setNews(new News((int)Float.parseFloat(mso.get("idNews").toString()),
                mso.get("headline").toString(),
                mso.get("content").toString(),mso.get("img").toString()));
                String comment=obj.get("comment").toString();
                c.setComcontent(comment);
                float likes = Float.parseFloat(obj.get("likes").toString());
                c.setLikes((int)likes);
                c.setDislikes(0);
                comments.add(c);
                  
            }
            
            
        } catch (IOException ex) {
            
        }
        return comments;
    }


public boolean addComment(Comment c){
        String url=Statics.BASE_URL+"mobile/addComments";
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
}
