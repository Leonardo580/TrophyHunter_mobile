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
import com.codename1.components.SpanLabel;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.trophy.entity.Competitions;
import com.trophy.entity.Teams;
import com.trophy.services.CompetitionsService;
import com.trophy.services.TeamsService;
import com.trophy.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Syrine
 */
public class TeamsForm extends BaseForm{
    
     Form add;
    Resources r;
    Competitions competition;
    public TeamsForm(Resources res, Competitions c) {
        r=res;
        competition=c;
        getAllStyles().setBgColor(DefaultRenderer.BACKGROUND_COLOR);
         EncodedImage eimg=EncodedImage.createFromImage(res.getImage("icon.png"),false);
        Storage.getInstance().clearCache();
        
            ImageViewer imgv = new ImageViewer(res.getImage("icon.png"));
            Container cnt=new Container(BoxLayout.y());
          
           cnt.add (new Label(c.getGame_name()));
            cnt.add(imgv);
            cnt.add(new Label(c.getDateofcomp()));
            
        
            
                  for (Teams t : TeamsService.getInstance().getAllTeams())
                     
                    if(t.getCompetitions().getId_competion()==c.getId_competion()) 
                    {
                        Container con =new Container(BoxLayout.x());
                        con.addAll(new Label(t.getTeam_name()),
                                new Label(t.getCompetitions().getGame_name())
                     );
                        Container b=new Container(BoxLayout.x());
                        Button update=new Button("Update");
                        Button delete=new Button("Delete");
                        update.addActionListener(l-> {
                        updateTeams(t).show();
                        });
                        delete.addActionListener(l-> {
                        con.remove();
                        b.remove();
                        deleteTeams(t);
                        });
                       
                        b.addAll(update, delete);
                        cnt.addAll(con,b);
                    }
           
            add(cnt);
          getToolbar().addMaterialCommandToLeftBar("Back ", FontImage.MATERIAL_ARROW_BACK, e->{new CompetitionForm(res).show();});
            getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_ADD, e->{addTeams(c).show();});
            
              getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_DELETE, e->{
                deleteCompetitions(c);
                new CompetitionForm(res).show();});
                getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_UPDATE, e->{

                    updateCompetitions(c).show();
                });
 
    }
    
     public Form addTeams(Competitions c){
        Form f=new Form(BoxLayout.y());
        Container cnt=new Container(BoxLayout.y());
        TextField tt=new TextField();
        tt.setUIID("TextFieldBlack");
        Validator v=new Validator();
        v.addConstraint(tt, new LengthConstraint(10));
        TextField td=new TextField();
        td.setUIID("TextFieldBlack");
        Button btn=new Button("Add Teams");
        btn.addActionListener(l-> {
            Teams t=new Teams();
            t.setTeam_name(tt.getText());
            t.setCompetitions(c);
           
           
            TeamsService.getInstance().addTeams(t);
            new TeamsForm(r, c).show();
        });
        
        
        cnt.addAll(new Label("teamName", "Label"),tt,btn);
        f.add(cnt);
         Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        f.getToolbar().setTitleComponent(menuButton);
         f.getToolbar().addCommandToLeftSideMenu("Back", null, ev-> this.show());
        return f;
    }
     
     
       private void FormButton(Form f){
         Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        f.getToolbar().setTitleComponent(menuButton);
    }
       
       
     public Form updateTeams(Teams t){
        Form f=new Form(BoxLayout.y());
        
        Container cnt=new Container(BoxLayout.y());
        TextField tt=new TextField();
        tt.setUIID("TextFieldBlack");
        Validator v=new Validator();
        v.addConstraint(tt, new LengthConstraint(4));
        TextArea td=new TextArea();
        td.setUIID("TextFieldBlack");
        Button btn=new Button("Update Teams");
        tt.setText(t.getTeam_name());
  
        btn.addActionListener(l-> {
            if (v.isValid()) {
                t.setTeam_name(tt.getText());
              
                TeamsService.getInstance().updateTeams(t);
                new TeamsForm(r,competition).show();
            }
        });
        
             cnt.addAll(new Label("teamName", "Label")
                ,tt,btn);
        f.add(cnt);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        f.getToolbar().setTitleComponent(menuButton);
        f.getToolbar().addCommandToLeftSideMenu("Back", null, ev-> this.show());
        return f;
    }
     
     
      public void deleteTeams(Teams t){
        TeamsService.getInstance().deleteTeams(t.getId_team());
    }
      
      
         public void deleteCompetitions(Competitions c){
        CompetitionsService.getInstance().deleteCompetitions(c.getId_competion());
        new CompetitionForm(r).show();
    }
         
          public Form updateCompetitions(Competitions c){
        Form a=new Form(BoxLayout.y());
        String s[]={"game_name", "dateofcomp"};
        
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
        tt.get(0).setText(c.getGame_name());
       tt.get(1).setText(c.getDateofcomp());

        Button btn=new Button("Update Competition");
        btn.addActionListener(l-> {
           c.setGame_name(tt.get(0).getText());
           c.setDateofcomp(tt.get(1).getText());
         
            CompetitionsService.getInstance().updateCompetitions(c);
//            Competitions competitions=CompetitionsService.getInstance().getAllCompetitions().stream()
//                    .filter(g1->g1.getId_competion()==c.getId_competion())
//                    .findFirst().orElse(null);
//            assert competition != null;
            new TeamsForm(r, c).show();
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

