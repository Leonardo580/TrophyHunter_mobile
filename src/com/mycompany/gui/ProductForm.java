/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.capture.Capture;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.components.FileTree;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
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
public class ProductForm  extends BaseForm{
    Form add;
    Resources r;
    public ProductForm(Resources res){
     super(BoxLayout.y());
        r=res;
        setScrollable(true);
        Button menuButton = new Button("");
  getAllStyles().setBgColor(DefaultRenderer.BACKGROUND_COLOR);
        setScrollable(true);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        getToolbar().setTitleComponent(menuButton);
        
        Container cnt=new Container(new GridLayout(2));
        //cnt.setScrollableY(true);
       // Storage.getInstance().clearCache();
        
        
        //display
        
    ArrayList<Product>list  = ProductService.getInstance().displayProduct();
        
    for(Product p :list){
     EncodedImage eimg = EncodedImage.createFromImage(res.getImage("dog.jpg"), false);
            Image imgs = URLImage.createToStorage(eimg, Statics.BASE_URL+"/uploads/product_images/"+ p.getImage(),
                   Statics.BASE_URL+"/uploads/product_images/" +p.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
            addButton(imgs, p.getProdName(), p.getPrice(),p);
        }
    add=SetUpAdd();
          addSideMenu(res);
        getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_ADD_CIRCLE, ev -> add.show());
        getToolbar().setVisible(true);
    
    }

    private void addButton(Image imgs, String prodName, float price,Product pro) {
       int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button(imgs.fill(width, height));
        image.setUIID("Label");
        
        Container cnti = BorderLayout.west(image);
        cnti.setLeadComponent(image);
        
        TextArea productName = new TextArea(prodName);
        
//        productName.setUIID("product");
//        
//        productName.setEditable(false);
    
        TextArea pricep = new TextArea(String.valueOf(price));
//        pricep.setUIID("price");
//        pricep.setEditable(false);
        
        Container productInfoContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        productInfoContainer.addAll(productName, pricep);
//         Label likes = new Label(" description : " + carDescription, "NewsBottomLine");
//        likes.setTextPosition(RIGHT);
//
//
//        //Label comments = new Label(commentCount + " Comments", "NewsBottomLine");
//        FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);

cnti.add(BorderLayout.CENTER,
                BoxLayout.encloseY(
                        productInfoContainer
//                        BoxLayout.encloseX(likes)
                ));
        add(cnti);

         image.addActionListener(e -> {
            if (
                    Dialog.show("Confirmation", "What do you want?", "Delete", "Update")) {

                if (ProductService.getInstance().deleteproduct(pro)) {
                    {
                        new ProductForm(r).show();
                        //Dialog.show("Success", "product " + pro.getProdName() + " is deleted !! ", new Command("OK"));
                    }
                }
            } else {
                ProductUpdateForm t = new ProductUpdateForm(pro);
                t.show();
            }
//Dialog.show("hiiii","ok",null);
        });
        
        
        
        
    }
    public Form SetUpAdd(){
     Form a=new Form(BoxLayout.y());
     String s[]={"Product name","Description","Price","Discount","Quantity","Category"};
     ArrayList<TextField> tt=new ArrayList<>();
        for (String v:s){
            
            TextField t=new TextField(null , v);
            t.setUIID("TextFieldBlack");
            tt.add(t);
            Label lab=new Label(v);
            lab.setUIID("Label");
            a.add(new Container(BoxLayout.x()).addAll(
                    lab,t
            ));
            
        }
         Button btn=new Button("Add Product");
         btn.addActionListener(l-> {
            Product p=new Product();
            p.setProdName(tt.get(0).getText());
            p.setDescription(tt.get(1).getText());
            p.setPrice(Float.parseFloat(tt.get(2).getText()));
            p.setDiscount(Float.parseFloat(tt.get(3).getText()));
            p.setQuantity((int)Float.parseFloat(tt.get(4).getText()));
            p.setCategory(new Category(tt.get(5).getText()));
            
            //uploadimage
            MultipartRequest cr = new MultipartRequest();
            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);

            cr.setUrl(Statics.BASE_URL+"/mobile/uploadImg");
            cr.setPost(true);
            String mime = "image/"+filePath.substring(filePath.lastIndexOf(".")+1);
            try {
                cr.addData("file", filePath, mime);
            } catch (IOException ex) {
                Dialog.show("Error", ex.getMessage(), "OK", null);
            }
            cr.setFilename("file",filePath.substring(filePath.lastIndexOf("/")).substring(1));//any unique name you want

            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            cr.setDisposeOnCompletion(dlg);
            NetworkManager.getInstance().addToQueueAndWait(cr);
            //toufa houni
            p.setImage(filePath.substring(filePath.lastIndexOf("/")).substring(1));
               
            ProductService.getInstance().addProduct(p);
            new ProductForm(r).show();
        });
    a.add(btn);
        a.getToolbar().addCommandToLeftSideMenu("Back", null, ev-> this.show());
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        a.getToolbar().setTitleComponent(menuButton);
        return a;
    }
}
    
//        
//        for(Product p :list){
//         Container cn=new Container(BoxLayout.y());
//          EncodedImage eimg=EncodedImage.createFromImage(res.getImage("loading.jpg"),false);
//            Image imgs = URLImage.createToStorage(eimg, Statics.BASE_URL+"/uploads/product_images/"+p.getImage(),
//                    Statics.BASE_URL+"/uploads/product_images/"+p.getImage(),URLImage.RESIZE_SCALE_TO_FILL);
////             ImageViewer imgv = new ImageViewer(imgs.scaledHeight(
////                    Display.getInstance().getDisplayHeight()/3
////            ));
//            ImageViewer imgv = new ImageViewer(imgs.scaled(500, 500));
//        Label name=new Label(p.getProdName());
//        name.setUIID("Label");
//         Label desc =new Label(p.getDescription());
//       cn.addAll(imgv,name);
//        cnt.add(cn);
//        }
        
        
              
//        getToolbar().setVisible(true);
       // this.add(cnt);
//        add=SetUpAdd();
    
   //     setupSideMenu(res);    
   //     setupSideMenu(res);     
        
       
//    public Form SetUpAdd(){
//        Form a=new Form(BoxLayout.y());
//        String s[]={"Name", "Description","Rate", "Category"};
//      ArrayList<TextField> tt=new ArrayList<>();
//        for (String v:s){
//            TextField t=new TextField(null, v);
//            t.setUIID("TextFieldBlack");
//            tt.add(t);
//            Label lab=new Label(v);
//            lab.setUIID("Label");
//            a.add(new Container(BoxLayout.x()).addAll(
//                    lab,t
//            ));
//        Button btn=new Button("Add Product");
//        btn.addActionListener(l-> {
//            Product p = new Product();
//            p.setProdName(tt.get(0).getText());
//            p.setDescription(tt.get(2).getText());
//            p.setPrice(Float.parseFloat(tt.get(3).getText()));
//            p.setDiscount(Float.parseFloat(tt.get(4).getText()));
//           
//            p.setQuantity((int)Float.parseFloat(tt.get(5).getText()));
//             p.setCategory(new Category(tt.get(7).getText()));
//             //upload image
//            MultipartRequest cr = new MultipartRequest();
//            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
//
//            cr.setUrl(Statics.BASE_URL+"mobile/uploadImg");
//            cr.setPost(true);
//            String mime = "image/"+filePath.substring(filePath.lastIndexOf(".")+1);
//            try {
//                cr.addData("file", filePath, mime);
//            } catch (IOException ex) {
//                Dialog.show("Error", ex.getMessage(), "OK", null);
//            }
//            cr.setFilename("file", p.getProdName());//any unique name you want
//
//            InfiniteProgress prog = new InfiniteProgress();
//            Dialog dlg = prog.showInifiniteBlocking();
//            cr.setDisposeOnCompletion(dlg);
//            NetworkManager.getInstance().addToQueueAndWait(cr);
//            
//            p.setImage("uploads\\product_images"+p.getImage()+".jpg");
//            ProductService.getInstance().addProduct(p);
//            new ProductForm(r).show();
//            });
//         a.add(btn);
//        a.getToolbar().addCommandToLeftSideMenu("Back", null, ev-> this.show());
//        Button menuButton = new Button("");
//        menuButton.setUIID("Title");
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//        a.getToolbar().setTitleComponent(menuButton);
//        return a;
//        }
    

