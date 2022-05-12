/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;

import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.trophy.entity.Category;
import com.trophy.entity.Product;
import com.trophy.services.CategoryService;
import com.trophy.services.ProductService;
import com.trophy.utils.Statics;




import java.util.ArrayList;
import static java.util.Collections.list;


/**
 *
 * @author rihab bns
 */
public class DisplayCategory extends BaseForm {
    Form current;
       Resources r;
    public DisplayCategory (Resources res){
    
        super("Dsiplay Categories",BoxLayout.y());
         getAllStyles().setBgColor(DefaultRenderer.BACKGROUND_COLOR);
         Toolbar tb = new Toolbar(true);
         current = this;
         setToolbar(tb);
         getTitleArea().setUIID("Container");
         setTitle("Categories");
         getContentPane().setScrollVisible(false);
         tb.addSearchCommand(e->{});
         Tabs swipe = new Tabs();
         Label s1 = new Label();
         Label s2 = new Label();   
         //addTab(swipe,s1,res.getImage("tof.jpg"),"","",res);
         
         swipe.setUIID("Container");
         swipe.getContentPane().setUIID("Container");
         swipe.hideTabs();
         
         ButtonGroup bg = new ButtonGroup();
         int size = Display.getInstance().convertToPixels(1);
         Image unselectedWalkthru = Image.createImage(size,size,0);
         Graphics g = unselectedWalkthru.getGraphics();
         g.setColor(0xffffff);
         g.setAlpha(100);
         g.setAntiAliased(true);
         g.fillArc(0, 0, size, size, 0, 360);
         Image selectedWalkthru = Image.createImage(size,size,0);
         g=selectedWalkthru.getGraphics();
         g.setColor(0xffffff);
         g.setAntiAliased(true);
         g.fillArc(0, 0, size, size, 0, 360);
         RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
         FlowLayout flow = new FlowLayout(CENTER);
         flow.setValign(BOTTOM);
         Container radioContainer = new Container(flow);
         
        ArrayList<Category>listc = CategoryService.getInstance().displayCategories();
        Container cntc=new Container(new GridLayout(2));   
        for(Category c :listc){
         Container cnc=new Container(BoxLayout.y());
        Label namec=new Label(c.getCategory());
   
       
        cnc.addAll(namec);
        cntc.add(cnc);
        
        }
//         
//super(BoxLayout.y());
//        r=res;
//        Button menuButton = new Button("");
//        getAllStyles().setBgColor(DefaultRenderer.BACKGROUND_COLOR);
//        menuButton.setUIID("Title");
//        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
//        menuButton.addActionListener(e -> getToolbar().openSideMenu());
//        getToolbar().setTitleComponent(menuButton);
//        
//        ArrayList<Category>list = CategoryService.getInstance().displayCategories();
//        Container cnt=new Container(new GridLayout(2));
////        
//        for(Category c :list){
//         Container cn=new Container(BoxLayout.y());
//        Label name=new Label(c.getCategory());
//        cn.addAll(name);
//        cnt.add(cn);
//        }
//        
//        
//              
//        
//        getToolbar().setVisible(true);
//        this.add(cnt);
////display

//ArrayList<Product>list = ProductService.getInstance().displayProduct();
//        Container cnt=new Container(new GridLayout(2));
////        
//        for(Product p :list){
//         Container cn=new Container(BoxLayout.y());
//          EncodedImage eimg=EncodedImage.createFromImage(res.getImage("loading.jpg"),false);
//            Image imgs = URLImage.createToStorage(eimg, Statics.BASE_URL+"/"+p.getImage(),
//                    Statics.BASE_URL+"/"+p.getImage(),URLImage.RESIZE_SCALE_TO_FILL);
//              ImageViewer imgv = new ImageViewer(imgs.scaled(200, 600));
//        Label name=new Label(p.getProdName());
//         Label desc =new Label(p.getDescription());
//        
//        
//        
//        cn.addAll(imgv,name);
//        cnt.add(cn);
//        }













//
//for( Category rec : list){
////    String urlImage="E:\\s8\\mobile\\tof.jpg";
////    Image placeHolder = Image.createImage(120,90);
////    EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
////    URLImage urlim = URLImage.createToStorage(enc,urlImage, urlImage, URLImage.RESIZE_SCALE );
//// addButton(rec.getCategory(),rec);
//// ScaleImageLabel image = new ScaleImageLabel(urlim);
//// Container containerImg = new Container();
//// image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
// MultiButton mb = new MultiButton("Categories"+rec);
// radioContainer.add(mb);
//}
//  Form hi = new Form("Basic", new BorderLayout());
//  hi.add(CENTER,radioContainer);
//         hi.show();
//         
//         
//    }
//
//    private void addButton(String category, Category rec) {
////        int height = Display.getInstance().convertToPixels(11.5f);
////        int width = Display.getInstance().convertToPixels(14f);
////        Button image = new Button(img.fill(width,height));
////        image.setUIID("Label");
//        
//        
//        Container cnt = null ;
//         cnt.setLayout(new BorderLayout());
////        cnt.addComponent(BorderLayout.WEST, image);
//        TextArea ta = new TextArea(category);
//        ta.setUIID("NewsTopLine");
//        ta.setEditable(false);
//        cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(ta));
//        add(cnt);
//        
//    }

    

}}
    
    
    
    
    
    
    

