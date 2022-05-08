/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.mycompany.gui.GamesForm;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.components.FileTree;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.trophy.entity.Category;
import com.trophy.entity.Games;
import com.trophy.entity.Trophies;
import com.trophy.gui.SideMenuBaseForm;
import com.trophy.services.GamesService;
import com.trophy.services.TrophiesService;
import com.trophy.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author anasb
 */
public class TrophiesForm extends BaseForm{

  Resources r;
  Games game;
    public TrophiesForm( Resources res,Games g) {
        r=res;
        game=g;
        getAllStyles().setBgColor(DefaultRenderer.BACKGROUND_COLOR);
         EncodedImage eimg=EncodedImage.createFromImage(res.getImage("icon.png"),false);
            Image imgs = URLImage.createToStorage(eimg, Statics.BASE_URL+ g.getImg(),
                    Statics.BASE_URL+g.getImg());
          FormButton(this);
            ImageViewer imgv = new ImageViewer(imgs.scaled(800, 600));
            
            Container cnt=new Container(BoxLayout.y());
            cnt.add(new Label(g.getName()));
            cnt.add(imgv);
            cnt.add(new SpanLabel(g.getDescription()));
            cnt.add(new Label("Rate"+g.getRate()));
            cnt.add(new Label("Category"+g.getCategory().getCategory()));
            for (Trophies t : TrophiesService.getInstance().getAllTrophies())
                    if(t.getGame().getId_game()==g.getId_game()) 
                    {
                        Container c=new Container(BoxLayout.x());
                        c.addAll(new Label(t.getTitle()),
                                new Label(t.getDescription()),
                                new Label(t.getPlatform()),
                                new Label(t.getDifficulty()));
                        Container b=new Container(BoxLayout.x());
                        Button update=new Button("Update");
                        Button delete=new Button("Delete");
                        update.addActionListener(l-> {
                        updateTrophy(t).show();
                        });
                        delete.addActionListener(l-> {
                        c.remove();
                        b.remove();
                        deleteTrophy(t);
                        });
                       
                        b.addAll(update, delete);
                        cnt.addAll(c,b);
                    }
           
            add(cnt);
             getToolbar().addMaterialCommandToLeftBar("Back ", FontImage.MATERIAL_ARROW_BACK, e->{new GamesForm(res).show();});
            getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_ADD, e->{addTrophy(g).show();});
           
            getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_DELETE, e->{
                deleteGame(g);
                new GamesForm(res).show();});
                getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_UPDATE, e->{updateGame(g).show();});
                getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_ARROW_CIRCLE_DOWN, e->{
                    GamesService.getInstance().fetchOnline(g);
                    new TrophiesForm(r, g).show();
                });
                getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_TEXT_FORMAT, e->{
                    GamesService.getInstance().TranslateOnline(g);
                    new TrophiesForm(r, g).show();
                });
            getToolbar().setVisible(true);

            
    }
    public Form addTrophy(Games g){
        Form f=new Form(BoxLayout.y());
        Container cnt=new Container(BoxLayout.y());
        TextField tt=new TextField();
        tt.setUIID("TextFieldBlack");
        TextField td=new TextField();
        td.setUIID("TextFieldBlack");
        ComboBox<String> cd=new ComboBox<>("Very Easy", "Easy", "Medium", "Hard", "Very Hard");
        ComboBox<String> cp=new ComboBox<>("PS4", "PS5", "XBox Series X|S", "XBox One", "Nintendo Switch", "PC" );
        Button btn=new Button("Add Trophy");
        btn.addActionListener(l-> {
            Trophies t=new Trophies();
            t.setTitle(tt.getText());
            t.setDescription(td.getText());
            t.setPlatform(cp.getSelectedItem().toString());
            t.setDifficulty(cd.getSelectedItem().toString());
            t.setGame(g);
           
            TrophiesService.getInstance().addtrophy(t);
            new TrophiesForm(r, g).show();
        });
        
        cnt.addAll(new Label("Title", "Label"),tt,new Label("Description", "Label")
                ,td,new Label("Platform", "Label"),cp,new Label("Difficulty", "Label"),cd,btn);
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
    public Form updateTrophy(Trophies t){
        Form f=new Form(BoxLayout.y());
        
        Container cnt=new Container(BoxLayout.y());
        TextField tt=new TextField();
        tt.setUIID("TextFieldBlack");
        TextArea td=new TextArea();
        td.setUIID("TextFieldBlack");
        ComboBox<String> cd=new ComboBox<>("Very Easy", "Easy", "Medium", "Hard", "Very Hard");
        ComboBox<String> cp=new ComboBox<>("PS4", "PS5", "XBox Series X|S", "XBox One", "Nintendo Switch", "PC" );
        Button btn=new Button("Update Trophy");
        tt.setText(t.getTitle());
        td.setText(t.getDescription());
        cp.setSelectedItem(t.getPlatform());
        cd.setSelectedItem(t.getDifficulty());
        btn.addActionListener(l-> {
            t.setTitle(tt.getText());
            t.setDescription(td.getText());
            t.setPlatform(cp.getSelectedItem().toString());
            t.setDifficulty(cd.getSelectedItem().toString());
            TrophiesService.getInstance().updatetrophy(t);
            new TrophiesForm(r,game ).show();
        });
        cnt.addAll(new Label("Title", "Label")
                ,tt,new Label("Description", "Label")
                ,td,new Label("Platform", "Label")
                ,cp,new Label("Difficulty", "Label"),cd,btn);
        f.add(cnt);
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        f.getToolbar().setTitleComponent(menuButton);
        f.getToolbar().addCommandToLeftSideMenu("Back", null, ev-> this.show());
        return f;
    }
    public void deleteTrophy(Trophies t){
        TrophiesService.getInstance().deletetrophy(t);
    }
    public Form updateGame(Games g){
        Form a=new Form(BoxLayout.y());
        String s[]={"Name", "Description","Rate", "Category"};
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
        tt.get(0).setText(g.getName());
        td.setText(g.getDescription());
        tt.get(2).setText(String.valueOf(g.getRate()));
        tt.get(3).setText(g.getCategory().getCategory());

        
        a.add(new Container(BoxLayout.x()).addAll(
                    new Label("Image"),new FileTree()
            ));
        Button btn=new Button("Update Game");
        btn.addActionListener(l-> {
            g.setName(tt.get(0).getText());
            g.setDescription(td.getText());
            g.setRate(Float.parseFloat(tt.get(2).getText()));
            g.setCategory(new Category(tt.get(3).getText()));
            GamesService.getInstance().updateGame(g);
            new TrophiesForm(r, g).show();
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
    public void deleteGame(Games g){
        GamesService.getInstance().deleteGame(g);
        new GamesForm(r).show();
    }
}
