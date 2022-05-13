/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.trophy.services.ServiceUser;

/**
 *
 * @author lenovo
 */
public class registration extends Form {
    
    public registration(Resources res) {
        setTitle("sign up");
        setLayout(BoxLayout.y());
         
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        TextField tfTitle = new TextField("","username");
        TextField tftime = new TextField("","Email");
        TextField tfDuration = new TextField("","password");
//        TextField tfDescription = new TextField("","Description");
//        TextField tfprice = new TextField("","price");
//        TextField image = new TextField("","image");

        
     
        Button btnAdd = new Button("Sign up");
        btnAdd.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
                ServiceUser service;
                service = new ServiceUser();
                service.signup(tfTitle,tftime,tfDuration,null);
                    
            }
        } );
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,(evt)->{
//            previous.showBack();
//        });
        addAll(tfTitle,tftime,tfDuration,btnAdd);
       
  }
 
//    protected void showOtherForm(Resources res) {
//        new NewsfeedForm(res).show();
//    }
}


