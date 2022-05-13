/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.components.ImageViewer;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.trophy.entity.User;
import com.trophy.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class userForm extends BaseForm{
    Form add;
    Resources r;
    public userForm(Resources res) {
     
        
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
        ArrayList<User> competitions=ServiceUser.getInstance().getAllCompetitions();
        Container cnt=new Container(BoxLayout.y());
        cnt.setScrollableY(true);
        //lazem minha bech tupdati
        Storage.getInstance().clearCache();
        for (User c: competitions){
            Container cn=new Container(BoxLayout.x());
            

//            ImageViewer imgv = new ImageViewer(res.getImage("icon.png").scaled(200, 100));

           
            Label name=new Label(c.getFULL_NAME());
            name.getStyle().setBgColor(0xffffff);
           name.setUIID("Label");
           name.setAutoSizeMode(true);

        //  cn.addAll(imgv,name);
            Button btn=new Button();
//            btn.addActionListener(l-> {
//                new TeamsForm(res, c).show();
//            });
           cn.setLeadComponent(btn);
            cnt.add(cn);
        }
        this.add(cnt);
        add=SetUpAdd();
   // setupSideMenu(res);     
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
            User c=new User();
            c.setFULL_NAME(tt.get(0).getText());
            c.setEMAIL(tt.get(1).getText());
            c.setPASSWORD(tt.get(1).getText());
           
           
         
            ServiceUser.getInstance().addCompetitions(c);
            new userForm(r).show();
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
    
    
    
             public void deleteCompetitions(User u){
        ServiceUser.getInstance().deleteUser(u.getID_USER());
        new userForm(r).show();
    }
             
             
             
             
              public Form updateUser(User u){
        Form a=new Form(BoxLayout.y());
        String s[]={"ID_USER", "FULL_NAME","EMAIL","PASSWORD"};
        
        TextArea td=new TextArea();
      ArrayList<TextField> tt=new ArrayList<>();
        for (String v:s){
            TextField t=new TextField(null, v);
            t.setUIID("TextFieldBlack");
            tt.add(t);
            if(v.equals("Description"))
            a.add(new Container(BoxLayout.x()).addAll(
                    new Label(v, "Label"),td
                    
            ));
            else 
             a.add(new Container(BoxLayout.x()).addAll(
                    new Label(v, "Label"),t));
        }
      //  tt.get(2).setConstraint(TextField.NUMERIC);
        tt.get(0).setText(u.getFULL_NAME());
       tt.get(1).setText(u.getEMAIL());
        tt.get(2).setText(u.getPASSWORD());

        Button btn=new Button("Update user");
        btn.addActionListener(l-> {
          u.setFULL_NAME(tt.get(0).getText());
           u.setEMAIL(tt.get(1).getText());
           u.setPASSWORD(tt.get(2).getText());
            ServiceUser.getInstance().updateUser(u);
//            Competitions competitions=CompetitionsService.getInstance().getAllCompetitions().stream()
//                    .filter(g1->g1.getId_competion()==c.getId_competion())
//                    .findFirst().orElse(null);
//            assert competition != null;
            new userForm(r).show();
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
