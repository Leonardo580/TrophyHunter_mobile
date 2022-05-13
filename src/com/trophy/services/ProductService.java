/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trophy.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.trophy.entity.Product;
import com.trophy.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author rihab bns
 */
public class ProductService {
    public static ProductService instance = null;
    public ArrayList<Product>proucts;
    private ConnectionRequest req;
    
    
    
    
    
    
    
    public static ProductService getInstance(){
    if (instance == null)
        instance = new ProductService();
    return instance;}
    
    public ProductService(){
    req = new ConnectionRequest();}
    
    public void addProduct(Product product){
        
        String url =Statics.BASE_URL+"/mobile/add?prodName="+product.getProdName()+"&description="
                +product.getDescription()+"&price="+product.getPrice()+"&discount="+product.getDiscount()+"&quantity="+product.getQuantity()
                ;//+"&category"+product.getCategory()+"&image"+product.getImage();
        
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
                String str = new String(req.getResponseData());//json Response
                System.out.println("data == "+ str);
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution
}


    
    //display
    
    
    
    
   

}
   
