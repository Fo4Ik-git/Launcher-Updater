package com.fo4ik.commands;

public enum Commands {
    CLEAR("clear", "clear terminal"),
    PLAY("play", "start launcher"),
    UPDATECONFIG("updateConfig", "rewrite config file by default"),
    UPGRADE("upgrade", "update launcher and config file"),
    CHECKUPDATE("checkUpdate", "check update"),
    DOWNLOADLAUNCHER("downloadLauncher", "download launcher"),
    SYNC("sync", "Synchronize logs in terminal"),
    QUIT("quit", "quit from terminal"),
    EXIT("exit", "quit application"),;

    private String command;
    private String description;

    Commands(String command, String description) {
        this.command = command;
        this.description = description;
    }

    Commands() {
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

}
