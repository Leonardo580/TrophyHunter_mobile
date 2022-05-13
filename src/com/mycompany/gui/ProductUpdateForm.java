/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.trophy.entity.Category;
import com.trophy.entity.Product;
import com.trophy.services.ProductService;
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author rihab bns
 */
public class ProductUpdateForm extends BaseForm {

    Form add;
    Resources r;
    public ProductUpdateForm(Resources res, Product p){
     super(BoxLayout.y());
        r=res;
        setScrollable(true);
        Button menuButton = new Button("");
//        getAllStyles().setBgColor(DefaultRenderer.BACKGROUND_COLOR);
        setScrollable(true);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        getToolbar().setTitleComponent(menuButton);
        
        Container cnt=new Container(new GridLayout(2));
        Form a=new Form(BoxLayout.y());
     String s[]={"Product name","Description","Price","Discount","Quantity","Category"};
     ArrayList<TextField> tt=new ArrayList<>();
        for (String v:s){
            
            TextField t=new TextField(null , v);
            t.setUIID("TextFieldBlack");
            tt.add(t);
            Label lab=new Label(v);
            lab.setUIID("Label");
            add(new Container(BoxLayout.x()).addAll(
                    lab,t
            ));
           
           
            
        }
         tt.get(0).setText(p.getProdName());
            tt.get(1).setText(p.getDescription());
            tt.get(2).setText(String.valueOf(p.getPrice()));
            tt.get(3).setText(String.valueOf(p.getDiscount()));
            tt.get(4).setText(String.valueOf(p.getQuantity()));
            tt.get(5).setText(p.getCategory().getCategory());
         Button btn=new Button("Update Product");
         btn.addActionListener(l-> {
            
            p.setProdName(tt.get(0).getText());
            p.setDescription(tt.get(1).getText());
            p.setPrice(Float.parseFloat(tt.get(2).getText()));
            p.setDiscount(Float.parseFloat(tt.get(3).getText()));
            p.setQuantity((int)Float.parseFloat(tt.get(4).getText()));
            p.setCategory(new Category(tt.get(5).getText()));
            
            //uploadimage
            
               
            ProductService.getInstance().update(p);
            new ProductForm(r).show();
        });
   add(btn);
        a.getToolbar().addCommandToLeftSideMenu("Back", null, ev-> new ProductForm(r).show());
       
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//        a.getToolbar().setTitleComponent(menuButton);
        //this.show();
    }
    }
    

