package com.fo4ik.config;

import com.fo4ik.net.download.Download;
import com.fo4ik.net.json.GetJsonRequest;

import java.util.Properties;

public class Config {


    public static StringBuilder logs = new StringBuilder();


    public Config() {
    }

    public static void addLog(String log) {
        logs.append(log);
    }

    public static void clearLogs() {
        logs = new StringBuilder();
    }

    public static String getLogs() {
        return logs.toString();
    }


    public static void checkUpdate() {
        try {
            System.out.println("Check update");
            GetJsonRequest jsonGetRequest = new GetJsonRequest();
            String versionFromServer = jsonGetRequest.getUpdate();
            System.out.println("Get version from server");
            FileProperties fileProperties = new FileProperties();
            Properties properties = fileProperties.getProperties();
            String versionFromProperties = properties.getProperty(ConfigFileValue.VERSION.getKey());
            if (Double.parseDouble(versionFromServer) == Double.parseDouble(versionFromProperties)) {
                Config.addLog("Launcher is up to date! \n");
            } else if (Double.parseDouble(versionFromServer) > Double.parseDouble(versionFromProperties)) {
                oldVersion(versionFromServer);
            }

        } catch (Exception e) {
            Config.addLog("Error: " + e.getMessage() + "\n");
        }
    }


    private static void oldVersion(String newVersion) throws Exception {
        Config.addLog("Find new launcher version: " + newVersion + " click on button or write in terminal upgrade\n");
    }

    public static void upgrade() {
        try {
            checkUpdate();
            downloadLauncher();

            //Get new version from server
            GetJsonRequest jsonGetRequest = new GetJsonRequest();
            String versionFromServer = jsonGetRequest.getUpdate();
            FileProperties fileProperties = new FileProperties();
            Properties properties = fileProperties.getProperties();
            properties.setProperty(ConfigFileValue.VERSION.getKey(), versionFromServer);
            fileProperties.saveProperties(properties, true);
        } catch (Exception e) {
            addLog("Error: " + e.getMessage() + "\n");
        }
    }

    public static void downloadLauncher() {
        Download downloadByUrl = new Download();
        try {
            downloadByUrl.download();
        } catch (Exception e) {
            addLog("Error: " + e.getMessage() + "\n");
        }
    }


    public static void play() {
        try {
            FileProperties fileProperties = new FileProperties();
            Properties properties = fileProperties.getProperties();
            String command = "cmd /c start cmd.exe /K \"java -jar " + properties.getProperty(ConfigFileValue.PATH_TO_GAME.getKey()) + "\"";

            Process process = Runtime.getRuntime().exec(new String[]{"java", "-jar", properties.getProperty(ConfigFileValue.PATH_TO_GAME.getKey())});
        } catch (Exception e) {
            addLog("Error: " + e.getMessage() + "\n");
        }
    }
}
