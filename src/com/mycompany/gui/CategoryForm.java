/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.components.FileTree;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
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
import com.mycompany.gui.BaseForm;
import com.trophy.entity.Category;
import com.trophy.entity.Games;
import com.trophy.services.CategoryService;
import com.trophy.services.GamesService;
import com.trophy.utils.Statics;
import java.util.ArrayList;
/**
 *
 * @author rihab bns
 */
public class CategoryForm extends BaseForm{
    Form add;
    Resources r;
    
    public CategoryForm(Resources res){
    super(BoxLayout.y());
        r=res;
        setScrollable(true);
        Button menuButton = new Button("");
        getAllStyles().setBgColor(DefaultRenderer.BACKGROUND_COLOR);
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        getToolbar().setTitleComponent(menuButton);
       
        Container cnt=new Container(new GridLayout(2));
    
        ArrayList<Category>listc = CategoryService.getInstance().displayCategories();
        
        for(Category c :listc){
         Container cnc=new Container(BoxLayout.y());
         Label namec=new Label(c.getCategory());    
//        cnc.add(namec);
//        cnt.add(cnc);
          add(namec);
          
          Button bupdate= new Button("Update");
          add(bupdate);
          bupdate.addActionListener(l->{
              updateCat(c).show();
        });
          
            Button bdelete= new Button("delete");
            add(bdelete);
            bdelete.addActionListener(l->{
        namec.remove();
        bupdate.remove();
        bdelete.remove();
        CategoryService.getInstance().deletecategory(c);
        });
        }
        
//         this.add(cnt);
         add=SetUpAdd();
          addSideMenu(res);
        getToolbar().addMaterialCommandToLeftBar(null, FontImage.MATERIAL_ADD_CIRCLE, ev -> add.show());
        getToolbar().setVisible(true);
    }
    public Form SetUpAdd(){
     Form a=new Form(BoxLayout.y());
     String s[]={"Category name"};
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
         Button btn=new Button("Add Category");
         btn.addActionListener(l-> {
            Category c=new Category();
            c.setCategory(tt.get(0).getText());
            CategoryService.getInstance().addCategory(c);
            new CategoryForm(r).show();
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
    public Form updateCat(Category c){
        Form a= new Form(BoxLayout.y());
        String s[]={"Category name"};
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
        tt.get(0).setText(c.getCategory());
         Button btn=new Button("UpdateCategory");
         btn.addActionListener(l-> {
          
            
            c.setCategory(tt.get(0).getText());
            CategoryService.getInstance().updatecategory(c);
            new CategoryForm(r).show();
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
