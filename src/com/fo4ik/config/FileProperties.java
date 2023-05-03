package com.fo4ik.config;

import com.fo4ik.gui.Terminal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class FileProperties {
    public static final String SEPARATOR = System.getProperty("file.separator");
    public static final String FOLDER_PATH = System.getProperty("user.home") + SEPARATOR + "Mamma_Mia_Launcher";
    public static Properties properties = new Properties();
    Terminal terminal = new Terminal();


    public void initProperties(boolean rewrite) {
        properties.setProperty(ConfigFileValue.PATH_TO_FOLDER.getKey(), ConfigFileValue.PATH_TO_FOLDER.getValue());
        properties.setProperty(ConfigFileValue.PATH_TO_GAME.getKey(), ConfigFileValue.PATH_TO_GAME.getValue());
        properties.setProperty(ConfigFileValue.VERSION.getKey(), ConfigFileValue.VERSION.getValue());
        properties.setProperty(ConfigFileValue.LAUNCHER_URL.getKey(), ConfigFileValue.LAUNCHER_URL.getValue());
        properties.setProperty(ConfigFileValue.LAUNCHER_ID.getKey(), ConfigFileValue.LAUNCHER_ID.getValue());
        saveProperties(properties, rewrite);
    }


    public void saveProperties(Properties properties, boolean rewrite) {
        try {
            Path configFile = Path.of(FileProperties.FOLDER_PATH + FileProperties.SEPARATOR + "config.properties");
            Files.createDirectories(Path.of(FileProperties.FOLDER_PATH));
            if (Files.exists(configFile)) {
                if (rewrite) {
                    terminal.text("Delete old config file... \n");
                    Files.delete(configFile);
                    saveProperties(properties, false);
                }
                terminal.text("Config file exist! \n");
            } else {
                terminal.text("Creating config file... \n");
                FileOutputStream fileOutputStream = new FileOutputStream(configFile.toString());
                properties.store(fileOutputStream, "Launcher config");
                fileOutputStream.close();
                terminal.text("Config file created! \n");
                System.out.println("Config file created! \n");
            }
        } catch (Exception e) {
            terminal.text("Error while saving properties: " + e.getMessage() + "\n");
            System.out.println("Error while saving properties: " + e.getMessage() + "\n");
        }
    }

    public Properties getProperties() {
        try {
            Properties getProperties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(FOLDER_PATH + SEPARATOR + "config.properties");
            getProperties.load(fileInputStream);
            fileInputStream.close();
            return getProperties;
        } catch (Exception e) {
        }
        return properties;
    }


}
