package com.mycompany.onCommandPatternTelegramBot.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

/**
 *
 * @list of BotCommands descriptions of menue of commands
 */
public enum CommandDescription {
    START_DESCRIPTION("start to work with the bot"),
    STOP_DESCRIPTION("stop to work with the bot"),
    HELP_DESCRIPTION("help you how to work with the bot"),
    STATISTIC_DESCRIPTION("get statistic how many users use the bot");

    @Getter
    public final String description;

    private CommandDescription(String description) {
        this.description = description;
    }

    public static List<String> getDescriptions() {
        List<String> getText = Arrays.stream(values())
                .map(s -> s.getDescription())
                .collect(Collectors.toList());
        return getText;
    }
}
