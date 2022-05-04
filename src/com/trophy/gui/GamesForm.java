/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trophy.gui;

import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.components.FileTree;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.trophy.entity.Category;
import com.trophy.entity.Games;
import com.trophy.services.GamesService;
import com.trophy.utils.Statics;
import java.util.ArrayList;


/**
 *
 * @author anasb
 */
public class GamesForm extends SideMenuBaseForm{

    Form add;
    Resources r;
    public GamesForm(Resources res){
        super(BoxLayout.y());
        r=res;
        Button menuButton = new Button("");
        getAllStyles().setBgColor(DefaultRenderer.BACKGROUND_COLOR);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        getToolbar().setTitleComponent(menuButton);
        ArrayList<Games> games=GamesService.getInstance().getAllGames();
        Container cnt=new Container(new GridLayout(2));
        for (Games g: games){
            Container cn=new Container(BoxLayout.y());
            EncodedImage eimg=EncodedImage.createFromImage(res.getImage("loading.png"),false);
            Image imgs = URLImage.createToStorage(eimg, Statics.BASE_URL+ g.getImg(),
                    Statics.BASE_URL+g.getImg(),URLImage.RESIZE_SCALE_TO_FILL);
          
            ImageViewer imgv = new ImageViewer(imgs.scaled(200, 600));
           
            Label name=new Label(g.getName());
            Button btn=new Button();
            btn.addActionListener(l-> {
            new TrophiesForm(res, g).show();
            });
            cn.setLeadComponent(btn);
            cn.addAll(imgv,name);
            cnt.add(cn);
        }
        this.add(cnt);
        add=SetUpAdd();
        setupSideMenu(res);       
        getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_ADD_CIRCLE, ev -> add.show());
        getToolbar().setVisible(true);
       
    }
    @Override
    protected void showOtherForm(Resources res) {
        new ProfileForm(res).show();
    }
    public Form SetUpAdd(){
        Form a=new Form(BoxLayout.y());
        String s[]={"Name", "Description","Rate", "Category"};
      ArrayList<TextField> tt=new ArrayList<>();
        for (String v:s){
            TextField t=new TextField(null, v);
            tt.add(t);
            a.add(new Container(BoxLayout.x()).addAll(
                    new Label(v),t
            ));
            
        }
        a.add(new Container(BoxLayout.x()).addAll(
                    new Label("Image"),new FileTree()
            ));
        Button btn=new Button("Add Game");
        btn.addActionListener(l-> {
            Games g=new Games();
            g.setName(tt.get(0).getText());
            g.setDescription(tt.get(1).getText());
            g.setRate(Float.parseFloat(tt.get(2).getText()));
            g.setCategory(new Category(tt.get(3).getText()));
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
