/*
 * Created by JFormDesigner on Wed May 03 00:36:25 CEST 2023
 */

package com.fo4ik.gui;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author terra
 */
public class Help extends JFrame {
    public Help() {
        initComponents();
        helpPanel.setContentType("text/html");
        try {
            URL url = Help.class.getResource("/resources/help/help.html");
            //TODO Fix help
            helpPanel.setPage(url);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Add a hyperlink listener to detect clicks on links
        helpPanel.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    // Open the link in the default browser
                    try {
                        Desktop.getDesktop().browse(new URI(e.getURL().toString()));
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("resources.language");
        panel = new JFrame();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        helpPanel = new JEditorPane();

        //======== panel ========
        {
            panel.setTitle(bundle.getString("help"));
            panel.setPreferredSize(new Dimension(1024, 640));
            panel.setMinimumSize(new Dimension(1024, 640));
            var panelContentPane = panel.getContentPane();

            //======== panel1 ========
            {
                panel1.setBackground(Color.white);

                //======== scrollPane1 ========
                {

                    //---- helpPanel ----
                    helpPanel.setEditable(false);
                    helpPanel.setBackground(Color.white);
                    scrollPane1.setViewportView(helpPanel);
                }

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addComponent(scrollPane1)
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                );
            }

            GroupLayout panelContentPaneLayout = new GroupLayout(panelContentPane);
            panelContentPane.setLayout(panelContentPaneLayout);
            panelContentPaneLayout.setHorizontalGroup(
                panelContentPaneLayout.createParallelGroup()
                    .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            panelContentPaneLayout.setVerticalGroup(
                panelContentPaneLayout.createParallelGroup()
                    .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            panel.pack();
            panel.setLocationRelativeTo(panel.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame panel;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JEditorPane helpPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
