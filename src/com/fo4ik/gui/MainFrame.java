/*
 * Created by JFormDesigner on Tue May 02 17:48:07 CEST 2023
 */

package com.fo4ik.gui;

import com.fo4ik.config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

/**
 * @author terra
 */
public class MainFrame extends JFrame {

    Terminal terminal;
    Settings settings;
    Config config;

    public MainFrame() {
        initComponents();
        config = new Config();
        config.addLog("Start program\n");
    }


    private void menuTerminal(ActionEvent e) {
        terminal = new Terminal();
        terminal.setVisible(true);
    }

    private void menuExit(ActionEvent e) {
        if (terminal != null) terminal.dispose();
        System.exit(0);
    }

    private void upgradeButtonMouseClicked(MouseEvent e) {
        Config.upgrade();
        terminal = new Terminal();
        terminal.sync();
    }

    private void playButtonMouseClicked(MouseEvent e) {
        Config.play();
        terminal = new Terminal();
        terminal.sync();
    }

    private void menuSettingsMouseClicked(MouseEvent e) {
        settings = new Settings();
        settings.setVisible(true);
    }

    private void menuSettings(ActionEvent e) {
        settings = new Settings();
        settings.setVisible(true);
    }

    private void thisWindowClosing(WindowEvent e) {
        System.exit(0);
        System.out.println("Exit");
    }

    private void menuHelp(ActionEvent e) {
        Help help = new Help();
        help.setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("resources.language");
        menuBar = new JMenuBar();
        menu = new JMenu();
        menuSettings = new JMenuItem();
        menuTerminal = new JMenuItem();
        menuExit = new JMenuItem();
        help = new JMenu();
        menuHelp = new JMenuItem();
        mainPanel = new JPanel();
        updateButton = new JButton();
        playButton = new JButton();

        //======== this ========
        setBackground(Color.gray);
        setTitle("Updater");
        setForeground(Color.white);
        setPreferredSize(new Dimension(330, 230));
        setMinimumSize(new Dimension(330, 230));
        setMaximumSize(new Dimension(330, 230));
        setVisible(true);
        setResizable(false);
        setAutoRequestFocus(false);
        setAlwaysOnTop(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        var contentPane = getContentPane();

        //======== menuBar ========
        {
            menuBar.setBackground(Color.white);

            //======== menu ========
            {
                menu.setText(bundle.getString("menu"));
                menu.setBackground(Color.lightGray);
                menu.setForeground(Color.black);

                //---- menuSettings ----
                menuSettings.setText(bundle.getString("settings"));
                menuSettings.addActionListener(e -> menuSettings(e));
                menu.add(menuSettings);

                //---- menuTerminal ----
                menuTerminal.setText(bundle.getString("terminal"));
                menuTerminal.setHorizontalAlignment(SwingConstants.LEFT);
                menuTerminal.addActionListener(e -> menuTerminal(e));
                menu.add(menuTerminal);

                //---- menuExit ----
                menuExit.setText(bundle.getString("exit"));
                menuExit.setBackground(Color.lightGray);
                menuExit.setForeground(Color.black);
                menuExit.addActionListener(e -> menuExit(e));
                menu.add(menuExit);
            }
            menuBar.add(menu);

            //======== help ========
            {
                help.setText(bundle.getString("help"));

                //---- menuHelp ----
                menuHelp.setText(bundle.getString("help"));
                menuHelp.addActionListener(e -> menuHelp(e));
                help.add(menuHelp);
            }
            menuBar.add(help);
        }
        setJMenuBar(menuBar);

        //======== mainPanel ========
        {
            mainPanel.setBackground(Color.white);
            mainPanel.setRequestFocusEnabled(false);

            //---- updateButton ----
            updateButton.setText(bundle.getString("upgrade"));
            updateButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    upgradeButtonMouseClicked(e);
                }
            });

            //---- playButton ----
            playButton.setText(bundle.getString("playButton.text"));
            playButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    playButtonMouseClicked(e);
                }
            });

            GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
            mainPanel.setLayout(mainPanelLayout);
            mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup()
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                            .addComponent(playButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(87, Short.MAX_VALUE))
            );
            mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addContainerGap(23, Short.MAX_VALUE)
                        .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(playButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuSettings;
    private JMenuItem menuTerminal;
    private JMenuItem menuExit;
    private JMenu help;
    private JMenuItem menuHelp;
    private JPanel mainPanel;
    private JButton updateButton;
    private JButton playButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
