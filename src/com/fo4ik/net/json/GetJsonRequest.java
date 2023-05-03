package com.fo4ik.net.json;

import com.fo4ik.config.ConfigFileValue;
import com.fo4ik.config.FileProperties;
import com.fo4ik.gui.Terminal;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GetJsonRequest {

    public String newVersion;
    ConfigFileValue configFileValue;
    Terminal terminal;
    FileProperties fileProperties;


    public String getUpdate() {
        terminal = new Terminal();
        fileProperties = new FileProperties();
        try {
            Properties properties = fileProperties.getProperties();
            URL urlConfig = new URL(properties.getProperty(
                    ConfigFileValue.LAUNCHER_URL.getKey()) + properties.getProperty(ConfigFileValue.LAUNCHER_ID.getKey()));

            terminal.text("Getting data from server... \n");
            HttpURLConnection connection = (HttpURLConnection) urlConfig.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200) {
                //Get data from server
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    response.append(output);
                }
                br.close();

                //Get data from server to Json
                Gson gson = new Gson();
                File data = gson.fromJson(response.toString(), File.class);

                terminal.text("Data received successfully\n");
                terminal.text("Current version: " + data.getVersion() + "\n");
                newVersion = data.getVersion();
                return data.getVersion();
                //Check version
            } else {
                terminal.text("Connection error: " + connection.getResponseCode() + "\n");
            }

        } catch (Exception e) {
            terminal.text("Error: " + e.getMessage() + "\n" + e + "\n");
        }
        return null;
    }
}

class File {
    Map<String, String> links = new HashMap<>();
    private long id;
    private String name, version;

    public File() {
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public void addLink(String url, String pathToFileInDB) {
        this.links.put(url, pathToFileInDB);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
