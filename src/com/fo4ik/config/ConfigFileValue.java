package com.fo4ik.config;


public enum ConfigFileValue {

    PATH_TO_FOLDER("pathToFolder", FileProperties.FOLDER_PATH),
    PATH_TO_GAME("pathToGame", PATH_TO_FOLDER + FileProperties.SEPARATOR + "Launcher.jar"),
    VERSION("version", "0.0"),
    LAUNCHER_URL("launcherUrl", "http://130.61.147.228:9000/api/file/"),
    LAUNCHER_ID("launcherId", "1");


    private String key, value;

    ConfigFileValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
