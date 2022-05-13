/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.trophy.entity.Competitions;
import com.trophy.services.CompetitionsService;

/**
 *
 * @author Syrine
 */
public class formasearch extends Form {
    
   Form current;
   Form previous;

    public formasearch(Form previous, Competitions c) {
        current = this;
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        CompetitionsService cs = new CompetitionsService();
        add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {

            Display.getInstance().callSerially(() -> {
                removeAll();
                setLayout(BoxLayout.y());

                {
                    MultiButton m = new MultiButton();
                    m.setTextLine1("gameName: " + c.getGame_name());
                    m.setTextLine2("dateofcomp: " + c.getDateofcomp());
                    add(m);
                        Button btnBack = new Button("back");
                         btnBack.addActionListener((evt) -> {
                             new CompetitionForm(previous);
                         });
                         addAll(btnBack);
                    //previous.showBack();
                }

                revalidate();
            });
        });
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                


    }

    
}
