/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.trophy.entity.Category;
import com.trophy.entity.Competitions;
import com.trophy.services.CompetitionsService;
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Syrine
 */
public class CompetitionForm extends BaseForm{
  Form add;
    Resources r;
    public CompetitionForm(Resources res) {
        
        
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
        ArrayList<Competitions> competitions=CompetitionsService.getInstance().getAllCompetitions();
        Container cnt=new Container(BoxLayout.y());
        cnt.setScrollableY(true);
        //lazem minha bech tupdati
        Storage.getInstance().clearCache();
        for (Competitions c: competitions){
            Container cn=new Container(BoxLayout.x());
            

            ImageViewer imgv = new ImageViewer(res.getImage("icon.png").scaled(200, 100));

           
            Label name=new Label(c.getGame_name());
            name.getStyle().setBgColor(0xffffff);
           name.setUIID("Label");
           name.setAutoSizeMode(true);

          cn.addAll(imgv,name);
            Button btn=new Button();
            btn.addActionListener(l-> {
                new TeamsForm(res, c).show();
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
        String s[]={"game_name", "dateofcomp"};
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
        Button btn=new Button("Add Competitions");
        btn.addActionListener(l-> {
            Competitions c=new Competitions();
            c.setGame_name(tt.get(0).getText());
            c.setDateofcomp(tt.get(1).getText());
           
           
         
            CompetitionsService.getInstance().addCompetitions(c);
            new CompetitionForm(r).show();
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
