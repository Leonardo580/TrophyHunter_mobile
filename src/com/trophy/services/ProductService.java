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
import static com.codename1.ui.BrowserComponent.JSType.get;
import com.codename1.ui.events.ActionListener;
import com.trophy.entity.Category;
import com.trophy.entity.Product;
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rihab bns
 */
public class ProductService {
    
    public  boolean resultOk;
    public static ProductService instance =null ;
    public ArrayList<Product>products;
    private ConnectionRequest req;
     private void setRequest(Product p){
         req.addArgument("idProduct",String.valueOf(p.getIdProduct()));
          req.addArgument("prodName",String.valueOf(p.getProdName()));
         req.addArgument("description",String.valueOf(p.getDescription()));
         req.addArgument("price",String.valueOf(p.getPrice()));
         req.addArgument("discount",String.valueOf(p.getDiscount()));
         req.addArgument("quantity",String.valueOf(p.getQuantity()));
         req.addArgument("category",String.valueOf(p.getCategory().getCategory()));
        
           
     }
    
    
    
    
    
    
    public static ProductService getInstance(){
    if (instance == null)
        instance = new ProductService();
    return instance;}
    
    public ProductService(){
    req = new ConnectionRequest();}
    
    
    public void addProduct(Product pro) {

        String url = Statics.BASE_URL + "mobile/add?"
                +"prodName="+pro.getProdName()
                + "&description=" + pro.getDescription()
                + "&price=" + pro.getPrice()
                + "&discount=" + pro.getDiscount()
                + "&quantity=" +pro.getQuantity()
                +"&image="+pro.getImage()
                +"&category="+pro.getCategory()
                ;
                
        req.setUrl(url);

        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());//json Response
            System.out.println("data == " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution}
    }

//update
    
     public boolean update(Product pro) {
        String url ="http://127.0.0.1:8000/mobile/update";
        req.setUrl(url);
        setRequest(pro);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    public ArrayList<Product> parseJSON(String jsonText) throws  IOException{
         try {
             
          products=new ArrayList<>();
            
            JSONParser j = new JSONParser();
            Map<String,Object> gamesListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)gamesListJson.get("root");
           products.clear();
//             ObjectMapper ob=new ObjectMapper();
//             JsonNode jn=ob.readTree(Json);
            
            for(Map<String,Object> obj : list){
                 Product p = new Product();
              float idProduct = Float.parseFloat(obj.get("idProduct").toString());
               p.setIdProduct(((int)idProduct));
              String prodName = obj.get("prodName").toString();
              p.setProdName(prodName);
              String description = obj.get("description").toString();
              p.setDescription(description);
              float price = Float.parseFloat(obj.get("price").toString());
              p.setPrice(price);
              float discount = Float.parseFloat(obj.get("discount").toString());
              p.setDiscount(discount);
              float quantity = Float.parseFloat(obj.get("quantity").toString());
              p.setQuantity((int) quantity);
               String cat=((Map<String,Object>)obj.get("idCategory")).get("category").toString();
              int id=(int)Float.parseFloat(((Map<String,Object>)obj.get("idCategory")).get("idCategory").toString());
//              int idcategory = Integer.valueOf(obj.get("idCategory").toString().substring(12, obj.get("idCategory").toString().indexOf(".")));
//            Category cat = (Category) obj.get("category");
//            int id = cat.getId_category();
              p.setCategory(new Category(id,cat));

               String image = obj.get("image").toString();
              p.setImage(image);
                //int id_category = Integer.parseInt(obj.get("idCategory").toString());
                //float price = Float.parseFloat(obj.get("price).toString());
               products.add(p);
                  
            }
            
            
        } catch (IOException ex) {
            
        }
        return products;
    }
    
    
    //display
    
     public ArrayList<Product>displayProduct(){
    
    
   String url = Statics.BASE_URL+"mobile/display";
    req.setUrl(url);
 req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent arg0) {
              try {
                 products= parseJSON(new String(req.getResponseData()));
              }
             catch (IOException ex) {
                     //Logger.getLogger(GamesService.class.getName()).log(Level.SEVERE, null, ex);
                 }
            req.removeResponseListener(this);
             }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return products;
    
    
    
    }
     
     
     //delete
     public boolean deleteproduct(Product pro){
    String url = Statics.BASE_URL+"mobile/delete?idProduct="+pro.getIdProduct()+"";
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>(){
     @Override
            public void actionPerformed(NetworkEvent arg0) {
                resultOk=req.getResponseCode()==200;
           req.removeResponseListener(this);
            }
        });
            
     
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;}
     
     
    
    
   

}
   
