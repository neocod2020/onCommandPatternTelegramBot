package com.mycompany.onCommandPatternTelegramBot.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<String> getNames() {
        List<String> getNames = Arrays.stream(values())
                .filter(v -> v.getCommandName().startsWith("/"))
                .map(s -> s.getCommandName())
                .collect(Collectors.toList());
        return getNames;
    }

}
