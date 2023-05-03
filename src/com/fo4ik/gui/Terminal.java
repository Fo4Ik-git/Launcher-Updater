/*
 * Created by JFormDesigner on Tue May 02 18:27:08 CEST 2023
 */

package com.fo4ik.gui;

import com.fo4ik.commands.Commands;
import com.fo4ik.config.Config;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

/**
 * @author terra
 */
public class Terminal extends JFrame {


    DefaultStyledDocument doc = new DefaultStyledDocument();
    StyleContext styleContext = StyleContext.getDefaultStyleContext();
    StringBuilder stringBuilder = Config.logs;

    public Terminal() {
        initComponents();
        sync();
    }

    private void termianlInputKeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            printTextFromInput();
        }
    }

    private void printTextFromInput() {
        String text = termianlInput.getText();
        text(text + "\n\n");
        termianlInput.setText("");
        checkCommand(text);
    }

    public void text(String text) {

        Config.addLog(text);
        terminal.append(text);
    }

    private void sentTextButtonMouseClicked(MouseEvent e) {
        printTextFromInput();
    }


    private void checkCommand(String command) {
        if (command.equals("help")) {
            for (Commands cmd : Commands.values()) {
                //TODO add color
                //TODO add delete java and run launcher with java full
                text(cmd.getCommand() + " - ");
                text(cmd.getDescription() + "\n");
            }
        } else {
            try {
                switch (Commands.valueOf(command.toUpperCase())) {
                    case CLEAR:
                        terminal.setText("");
                        break;
                    case PLAY:
                        Config.play();
                        sync();
                        break;
                    case UPDATECONFIG:
                        break;
                    case UPGRADE:
                        Config.upgrade();
                        sync();
                        break;
                    case CHECKUPDATE:
                        Config.checkUpdate();
                        sync();
                        break;
                    case DOWNLOADLAUNCHER:
                        Config.downloadLauncher();
                        sync();
                        break;
                    case QUIT:
                        dispose();
                        break;
                    case SYNC:
                        sync();
                        break;
                    case EXIT:
                        System.exit(0);
                        break;
                }
            } catch (IllegalArgumentException e) {
                text("Command not found, type 'help' to see all commands\n");
            }
        }
    }

    public void sync() {
        terminal.setText("");
        terminal.append(Config.getLogs());
    }

    private void clearTerminal() {
        terminal.setText("");
        Config.clearLogs();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        ResourceBundle bundle = ResourceBundle.getBundle("resources.language");
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        terminal = new JTextArea();
        termianlInput = new JTextField();
        button1 = new JButton();
        sentTextButton = new JButton();

        //======== this ========
        setResizable(false);
        setPreferredSize(new Dimension(400, 315));
        setMinimumSize(new Dimension(400, 315));
        setTitle(bundle.getString("terminal"));
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(terminal);
            }

            //---- termianlInput ----
            termianlInput.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    termianlInputKeyPressed(e);
                }
            });

            //---- sentTextButton ----
            sentTextButton.setIcon(new ImageIcon(getClass().getResource("/resources/img/javaVersion.png")));
            sentTextButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sentTextButtonMouseClicked(e);
                }
            });

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(button1))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(termianlInput, GroupLayout.PREFERRED_SIZE, 317, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sentTextButton, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                            .addComponent(scrollPane1, GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(termianlInput, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                            .addComponent(sentTextButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                        .addGap(280, 280, 280)
                        .addComponent(button1))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextArea terminal;
    private JTextField termianlInput;
    private JButton button1;
    private JButton sentTextButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
