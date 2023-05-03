/*
 * Created by JFormDesigner on Tue May 02 21:02:51 CEST 2023
 */

package com.fo4ik.gui;

import com.fo4ik.config.ConfigFileValue;
import com.fo4ik.config.FileProperties;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author terra
 */
public class Settings extends JFrame {

    Properties properties;
    FileProperties fileProperties;
    Terminal terminal = new Terminal();

    public Settings() {
        initComponents();
        fileProperties = new FileProperties();
        properties = fileProperties.getProperties();
        setTextPathToFolder(properties.getProperty(ConfigFileValue.PATH_TO_FOLDER.getKey()));
    }

    private void setTextPathToFolder(String text) {
        textPathToFolder.setText(text);
    }

    private void selectPathToFolderMouseClicked(MouseEvent e) {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setCurrentDirectory(new File(Path.of(properties.getProperty(ConfigFileValue.PATH_TO_FOLDER.getKey())).toUri()));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            updatePathToFolder(Paths.get(selectedFile.getAbsolutePath()));
            terminal.text("New path to folder: " + selectedFile.getAbsolutePath() + "\n");
        }
    }

    private void updatePathToFolder(Path path) {
        properties = fileProperties.getProperties();
        properties.setProperty(ConfigFileValue.PATH_TO_FOLDER.getKey(), path.toString());
        properties.setProperty(ConfigFileValue.PATH_TO_GAME.getKey(), path.toString() + FileProperties.SEPARATOR + "Launcher.jar");
        fileProperties.saveProperties(properties, true);

        // TODO delete all files from this folder
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("resources.language");
        panel1 = new JPanel();
        label1 = new JLabel();
        textPathToFolder = new JLabel();
        selectPathToFolder = new JButton();
        window1 = new JWindow();
        fileChooser = new JFileChooser();

        //======== this ========
        setTitle(bundle.getString("settings"));
        setPreferredSize(new Dimension(400, 300));
        setMinimumSize(new Dimension(400, 300));
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);

            //---- label1 ----
            label1.setText(bundle.getString("pathToFolder"));

            //---- textPathToFolder ----
            textPathToFolder.setBorder(LineBorder.createBlackLineBorder());

            //---- selectPathToFolder ----
            selectPathToFolder.setText(bundle.getString("select"));
            selectPathToFolder.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectPathToFolderMouseClicked(e);
                }
            });

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textPathToFolder, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selectPathToFolder, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(textPathToFolder, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(selectPathToFolder, GroupLayout.Alignment.TRAILING))))
                        .addContainerGap(233, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());

        //======== window1 ========
        {
            var window1ContentPane = window1.getContentPane();

            GroupLayout window1ContentPaneLayout = new GroupLayout(window1ContentPane);
            window1ContentPane.setLayout(window1ContentPaneLayout);
            window1ContentPaneLayout.setHorizontalGroup(
                window1ContentPaneLayout.createParallelGroup()
                    .addGroup(window1ContentPaneLayout.createSequentialGroup()
                        .addComponent(fileChooser, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
            );
            window1ContentPaneLayout.setVerticalGroup(
                window1ContentPaneLayout.createParallelGroup()
                    .addGroup(window1ContentPaneLayout.createSequentialGroup()
                        .addComponent(fileChooser, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE))
            );
            window1.pack();
            window1.setLocationRelativeTo(window1.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JLabel textPathToFolder;
    private JButton selectPathToFolder;
    private JWindow window1;
    private JFileChooser fileChooser;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
