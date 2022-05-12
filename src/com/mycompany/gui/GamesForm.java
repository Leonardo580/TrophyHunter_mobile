/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.trophy.entity.Category;
import com.trophy.entity.Games;
import com.trophy.services.GamesService;
import com.trophy.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author anasb
 */
public class GamesForm extends BaseForm{

    Form add;
    Resources r;
    public GamesForm(Resources res){
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
        ArrayList<Games> games=GamesService.getInstance().getAllGames();
        Container cnt=new Container(BoxLayout.y());
        cnt.setScrollableY(true);
        //lazem minha bech tupdati
        Storage.getInstance().clearCache();
        for (Games g: games){
            Container cn=new Container(BoxLayout.x());
            EncodedImage eimg=EncodedImage.createFromImage(res.getImage("icon.png"),false);
            Image imgs = URLImage.createToStorage(eimg, Statics.BASE_URL+ g.getImg(),
                    Statics.BASE_URL+ g.getImg(),URLImage.RESIZE_SCALE);

            ImageViewer imgv = new ImageViewer(imgs.scaled(426,240));

           
            Label name=new Label(g.getName());
            name.getStyle().setBgColor(0xffffff);
           name.setUIID("Label");
           name.setAutoSizeMode(true);

          cn.addAll(imgv,name);
            Button btn=new Button();
            btn.addActionListener(l-> {
                new TrophiesForm(res, g).show();
            });
           cn.setLeadComponent(btn);
            cnt.add(cn);
        }
        this.add(cnt);
        add=SetUpAdd();
   //     setupSideMenu(res);     
        addSideMenu(res);
        getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_ADD_CIRCLE, ev -> add.show());
        getToolbar().setVisible(true);
       
    }
   
    public Form SetUpAdd(){
        Form a=new Form(BoxLayout.y());
        String s[]={"Name", "Description","Rate", "Category"};
      ArrayList<TextField> tt=new ArrayList<>();
        for (String v:s){
            TextField t=new TextField(null, v);
            t.setUIID("TextFieldBlack");
            tt.add(t);
            Label lab=new Label(v);
            lab.setUIID("Label");
            a.add(new Container(BoxLayout.x()).addAll(
                    lab,t
            ));
            
        }
      /*  a.add(new Container(BoxLayout.x()).addAll(
                    new Label("Image"),new FileTree()
            ));*/
        Button btn=new Button("Add Game");
        btn.addActionListener(l-> {
            Games g=new Games();
            g.setName(tt.get(0).getText());
            g.setDescription(tt.get(1).getText());
            g.setRate(Float.parseFloat(tt.get(2).getText()));
            g.setCategory(new Category(tt.get(3).getText()));
            //upload image
            MultipartRequest cr = new MultipartRequest();
            String filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);

            cr.setUrl(Statics.BASE_URL+"mobile/uploadImg");
            cr.setPost(true);
            String mime = "image/"+filePath.substring(filePath.lastIndexOf(".")+1);
            try {
                cr.addData("file", filePath, mime);
            } catch (IOException ex) {
                Dialog.show("Error", ex.getMessage(), "OK", null);
            }
            cr.setFilename("file", g.getName());//any unique name you want

            InfiniteProgress prog = new InfiniteProgress();
            Dialog dlg = prog.showInifiniteBlocking();
            cr.setDisposeOnCompletion(dlg);
            NetworkManager.getInstance().addToQueueAndWait(cr);
            //toufa houni
           // Dialog.show("Success", "Image uploaded", "OK", null);
            g.setImg("BackAssets\\images\\GameImgs\\"+g.getName() + ".jpg");
            GamesService.getInstance().addGame(g);
            new GamesForm(r).show();
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
