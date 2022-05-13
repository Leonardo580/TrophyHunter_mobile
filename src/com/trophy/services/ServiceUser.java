/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.services;

/**
 *
 * @author lenovo
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.addProductForm;
import com.trophy.entity.User;
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
public class ServiceUser {
    //singleton 
    public static ServiceUser instance = null ;
    public ArrayList<User>users;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUser getInstance() {
        if(instance == null )
            instance = new ServiceUser();
        return instance ;
    }
    
    
    
    public ServiceUser() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
    public void signup(TextField title, TextField EventTime, TextField Duration, Resources res) {
        
     
        String url = Statics.BASE_URL + "/add?fullName=" + title.getText().toString() + "&email=" + EventTime.getText().toString() +
                "&password=" + Duration.getText().toString();
        req.setUrl(url);
       
        //Control saisi
        if(title.getText().equals(" ") && EventTime.getText().equals(" ") && Duration.getText().equals(" ")) {
            
            Dialog.show("Erreur","You Must Fill the fields","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
//               req.setUrl(url);
//        
//        req.addResponseListener((e) ->{
//                String str = new String(req.getResponseData());//json Response
//                System.out.println("data == "+ str);
//        });
//        
//    NetworkManager.getInstance().addToQueueAndWait(req);//execution
//        
        }
    
    
    //SignIn
    
    public void signin(TextField username,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/loginuser?password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setUserName(user.get("username").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                //photo 
                
                if(user.get("photo") != null)
                    SessionManager.setPhoto(user.get("photo").toString());
                
                
                if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    new addProductForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    

  //heki 5dmtha taw nhabtha ala description
    public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
    
public ArrayList<User> parseJSON(String jsonText) throws  IOException{
         try {
             
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> gamesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
             List<Map<String,Object>> list = (List<Map<String,Object>>)gamesListJson.get("root");
           users.clear();
//             ObjectMapper ob=new ObjectMapper();
//             JsonNode jn=ob.readTree(Json);
            
            for(Map<String,Object> obj : list){
                User c = new User();
             String id=obj.get("id").toString().substring(0,
                     obj.get("id").toString().indexOf("."));
                c.setID_USER(Integer.parseInt(id));
                c.setEMAIL(obj.get("email").toString());
               // c.setFULL_NAME(obj.get("fullName").toString());  
                c.setPASSWORD(obj.get("password").toString());  
                
                
                users.add(c);       
            }
            
        } catch (IOException ex) {
            
        }
        return users;
    }
public ArrayList<User> getAllCompetitions(){
         String url=Statics.BASE_URL+"Competitions/getCompetitions";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent arg) {
              try {
                 users= parseJSON(new String(req.getResponseData()));
              }
             catch (IOException ex) {
                     //Logger.getLogger(GamesService.class.getName()).log(Level.SEVERE, null, ex);
                 }
            req.removeResponseListener(this);
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
public void addCompetitions(User competitions){
        
        String url =Statics.BASE_URL+"mobile/addCompetitions?"
                +"fullName="+competitions.getFULL_NAME()
              
                +"&password="+competitions.getPASSWORD();

        
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
                String str = new String(req.getResponseData());//json Response
                System.out.println("data == "+ str);
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution
}
    
}
