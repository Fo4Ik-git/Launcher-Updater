package com.fo4ik.net.download;

import com.fo4ik.config.ConfigFileValue;
import com.fo4ik.config.FileProperties;
import com.fo4ik.gui.Terminal;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Download {

    public void download() throws Exception {
        Terminal terminal = new Terminal();
        FileProperties fileProperties = new FileProperties();

        terminal.text("Download file from server ... \n");

        Properties properties = fileProperties.getProperties();
        URL urlConfig = new URL(properties.getProperty(
                ConfigFileValue.LAUNCHER_URL.getKey()) + "download/" + properties.getProperty(ConfigFileValue.LAUNCHER_ID.getKey()));
        String path = properties.getProperty(ConfigFileValue.PATH_TO_GAME.getKey());

        if (Files.exists(Paths.get(path))) {
            terminal.text("Delete old file ... \n");
            Files.delete(Paths.get(path));
            terminal.text("Old file deleted successfully\n");
        }

        try (InputStream in = urlConfig.openStream()) {
            Path dest = Paths.get(path);
            Files.copy(in, dest);
        }
        terminal.text("File downloaded successfully\n");
    }
}
