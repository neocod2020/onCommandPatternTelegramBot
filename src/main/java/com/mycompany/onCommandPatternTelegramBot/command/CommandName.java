package com.mycompany.onCommandPatternTelegramBot.command;

import lombok.Getter;



public enum CommandName {

START("/start"),
STOP("/stop"),
HELP("/help"),
NO("no command"),
STATISTIC("/statistic");


@Getter
private final String commandName;

    private CommandName(String commandName) {
        this.commandName = commandName;
    }
    
}
