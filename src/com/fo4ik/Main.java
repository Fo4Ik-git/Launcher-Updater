package com.fo4ik;

import com.fo4ik.config.FileProperties;
import com.fo4ik.gui.MainFrame;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.util.Locale;


public class Main {


    public static void main(String[] args) {
        FlatLightLaf.setup();

        try{
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch (Exception e) {
            System.out.println("Error set look and feel");
        }
        Locale locale = Locale.getDefault();
        //Locale locale = new Locale("ru");
        Locale.setDefault(locale);

        FileProperties fileProperties = new FileProperties();
        fileProperties.initProperties(false);


        MainFrame mainFrame = new MainFrame();

    }
}