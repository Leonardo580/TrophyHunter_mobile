/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

import com.trophy.entity.Product;
import com.trophy.services.ProductService;


/**
 *
 * @author rihab bns
 */
public class addProductForm extends BaseForm {

    Form current;
    
    public addProductForm(Resources res){
         super("Newsfeed",BoxLayout.y());
         Toolbar tb = new Toolbar(true);
         current = this;
         setToolbar(tb);
         getTitleArea().setUIID("Container");
         setTitle("Add new Product");
         getContentPane().setScrollVisible(false);
         
         
         TextField prodName=new TextField("","enter a product name");
         prodName.setUIID("TextFieldBlack");
         addStringValue("prodName",prodName);
         
         TextField description=new TextField("","enter a description");
         description.setUIID("TextFieldBlack");
         addStringValue("description",description);
         
          TextField price=new TextField("","enter a price");
         price.setUIID("TextFieldBlack");
         addStringValue("price",price);
         
          TextField discount=new TextField("","enter a discount");
         discount.setUIID("TextFieldBlack");
         addStringValue("discount",discount);
         
          TextField quantity=new TextField("","enter a quantity");
         quantity.setUIID("TextFieldBlack");
         addStringValue("quantity",quantity);
         
         
         
         Button btnadd = new Button("add");
         addStringValue("",btnadd);
         btnadd.addActionListener((e) ->{
         
             try{
             
             if(description.getText() =="" || prodName.getText()==""){Dialog.show("please verify us","done");}
             else{
                 InfiniteProgress ip = new InfiniteProgress();;
                 
                 final Dialog iDialog = ip.showInfiniteBlocking();
                 
                 Product p = new Product (String.valueOf(prodName.getText()).toString(),
                         String.valueOf(description.getText()).toString());
                 
                 System.out.println("product=="+p);
                 
                 ProductService.getInstance().addProduct(p);
                 iDialog.dispose();
                 refreshTheme();
                
             }
             }catch(Exception ex){
             ex.printStackTrace();}
         });
         
         
         
         
    }

    

    private void addStringValue(String word, Component v) {
        add(BorderLayout.west(new Label(word,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
     
    }
    
    
}
