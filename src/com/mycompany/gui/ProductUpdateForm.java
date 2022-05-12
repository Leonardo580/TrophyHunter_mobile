/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
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

    ProductUpdateForm(Product pro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//Form a=new Form(BoxLayout.y());
//    ProductUpdateForm(Product pro) {
//       setTitle("Update Product " + pro.getProdName() + "");
//        setLayout(BoxLayout.y());
//        Form a=new Form(BoxLayout.y());
//     String s[]={"Product name","Description","Price","Discount","Quantity","Category"};
//     ArrayList<TextField> tt=new ArrayList<>();
//        for (String v:s){
//            
//            TextField t=new TextField(null , v);
//            t.setUIID("TextFieldBlack");
//            tt.add(t);
//            Label lab=new Label(v);
//            lab.setUIID("Label");
//            a.add(new Container(BoxLayout.x()).addAll(
//                    lab,t
//            ));
//            
//        }
//         Button btn=new Button("Add Product");
//         btn.addActionListener(l-> {
//            Product p=new Product();
//            p.setProdName(tt.get(0).getText());
//            p.setDescription(tt.get(1).getText());
//            p.setPrice(Float.parseFloat(tt.get(2).getText()));
//            p.setDiscount(Float.parseFloat(tt.get(3).getText()));
//            p.setQuantity((int)Float.parseFloat(tt.get(4).getText()));
//            p.setCategory(new Category(tt.get(5).getText()));
//            
//            //uploadimage
//            MultipartRequest cr = new MultipartRequest();
//            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
//
//            cr.setUrl(Statics.BASE_URL+"mobile/uploadImgriri");
//            cr.setPost(true);
//            String mime = "image/"+filePath.substring(filePath.lastIndexOf(".")+1);
//            try {
//                cr.addData("file", filePath, mime);
//            } catch (IOException ex) {
//                Dialog.show("Error", ex.getMessage(), "OK", null);
//            }
//            cr.setFilename("file",filePath.substring(filePath.lastIndexOf("/")).substring(1));//any unique name you want
//
//            InfiniteProgress prog = new InfiniteProgress();
//            Dialog dlg = prog.showInifiniteBlocking();
//            cr.setDisposeOnCompletion(dlg);
//            NetworkManager.getInstance().addToQueueAndWait(cr);
//            //toufa houni
//            p.setImage(filePath.substring(filePath.lastIndexOf("/")).substring(1));
//               
//            ProductService.getInstance().addProduct(p);
//            new ProductForm(r).show();
//        });
//    a.add(btn);
//        a.getToolbar().addCommandToLeftSideMenu("Back", null, ev-> this.show());
//        Button menuButton = new Button("");
//        menuButton.setUIID("Title");
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//        a.getToolbar().setTitleComponent(menuButton);
//        return a;
//
//    }
//        
        
    }
    

