/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.*;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.gui.GamesForm;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.components.FileTree;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.trophy.entity.Category;
import com.trophy.entity.Games;
import com.trophy.entity.Trophies;
import com.trophy.gui.SideMenuBaseForm;
import com.trophy.services.GamesService;
import com.trophy.services.TrophiesService;
import com.trophy.utils.Statics;

import java.io.IOException;
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
        Storage.getInstance().clearCache();
            Image imgs = URLImage.createToStorage(eimg, Statics.BASE_URL+ g.getImg(),
                    Statics.BASE_URL+g.getImg());
          FormButton(this);
            ImageViewer imgv = new ImageViewer(imgs.scaled(800, 600));
            
            Container cnt=new Container(BoxLayout.y());
            cnt.add(new Label(g.getName()));
            cnt.add(imgv);
            cnt.add(new SpanLabel(g.getDescription()));
            cnt.add(new Label("Rate "+g.getRate()));
            cnt.add(new Label("Category "+g.getCategory().getCategory()));
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
            //embedVideo(g);
             getToolbar().addMaterialCommandToLeftBar("Back ", FontImage.MATERIAL_ARROW_BACK, e->{new GamesForm(res).show();});
            getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_ADD, e->{addTrophy(g).show();});
           
            getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_DELETE, e->{
                deleteGame(g);
                new GamesForm(res).show();});
                getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_UPDATE, e->{

                    updateGame(g).show();
                });
                getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_ARROW_CIRCLE_DOWN, e->{
                    GamesService.getInstance().fetchOnline(g);
                    Games game=GamesService.getInstance().getAllGames().stream()
                            .filter(g1->g1.getId_game()==g.getId_game())
                            .findFirst().orElse(null);
                    assert game != null;
                    new TrophiesForm(r, game).show();
                });
                getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_TEXT_FORMAT, e->{
                    GamesService.getInstance().TranslateOnline(g);
                    Games game=GamesService.getInstance().getAllGames().stream()
                            .filter(g1->g1.getId_game()==g.getId_game())
                            .findFirst().orElse(null);
                    assert game != null;
                    new TrophiesForm(r, game).show();
                });
            getToolbar().setVisible(true);

            
    }
    public Form addTrophy(Games g){
        Form f=new Form(BoxLayout.y());
        Container cnt=new Container(BoxLayout.y());
        TextField tt=new TextField();
        tt.setUIID("TextFieldBlack");
        Validator v=new Validator();
        v.addConstraint(tt, new LengthConstraint(10));
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
        Validator v=new Validator();
        v.addConstraint(tt, new LengthConstraint(10));
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
            if (v.isValid()) {
                t.setTitle(tt.getText());
                t.setDescription(td.getText());
                t.setPlatform(cp.getSelectedItem().toString());
                t.setDifficulty(cd.getSelectedItem().toString());
                TrophiesService.getInstance().updatetrophy(t);
                new TrophiesForm(r, game).show();
            }
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
        tt.get(2).setConstraint(TextField.NUMERIC);
        tt.get(0).setText(g.getName());
        td.setText(g.getDescription());
        tt.get(2).setText(String.valueOf(g.getRate()));
        tt.get(3).setText(g.getCategory().getCategory());

        
       /* a.add(new Container(BoxLayout.x()).addAll(
                    new Label("Image"),new FileTree()
            ));*/
        Button btn=new Button("Update Game");
        btn.addActionListener(l-> {
            g.setName(tt.get(0).getText());
            g.setDescription(td.getText());
            g.setRate(Float.parseFloat(tt.get(2).getText()));
            g.setCategory(new Category(tt.get(3).getText()));
            GamesService.getInstance().updateGame(g);
            Games game=GamesService.getInstance().getAllGames().stream()
                    .filter(g1->g1.getId_game()==g.getId_game())
                    .findFirst().orElse(null);
            assert game != null;
            new TrophiesForm(r, g).show();
        });
        Button image=new Button("Choose an Image");
        image.addActionListener(l-> {
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
            // Dialog.show("Success", "Image uploaded", "OK", null);
            g.setImg("BackAssets\\images\\GameImgs\\"+g.getName() + ".jpg");
                });
        a.add(new Container(BoxLayout.x()).addAll(
                new Label("Image"),image
        ));
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
    public void embedVideo(Games g) {
        String url="https://www.youtube.com/embed/"+TrophiesService.getInstance().getVId(g);
       //String url="https://www.google.;
        BrowserComponent browser = new BrowserComponent();
       //browser.setURL("https://www.google.com/");
       browser.setPage("" +
               "<!DOCTYPE  html>" +
               "<head></head>" +
               "<body>" +
               "<iframe src=\""+url+"\"></iframe>" +
               "</body>"
               ,null);
        //browser.setURL(url);
        //browser.setSize(500, 500);
       // browser.setURL(url);
        browser.getAllStyles().setPadding(0, 0, 0, 0);
        browser.getAllStyles().setMargin(0, 0, 0, 0);

        this.add(browser);
    }
}
