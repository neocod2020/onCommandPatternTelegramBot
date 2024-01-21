package com.mycompany.onCommandPatternTelegramBot.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @list of BotCommands descriptions of menue of commands
 */
@Slf4j
public enum CommandDescription {
    START_DESCRIPTION("start to work with the bot"),
    STOP_DESCRIPTION("stop to work with the bot"),
    HELP_DESCRIPTION("help you how to work with the bot"),
    STATISTIC_DESCRIPTION("statistic how many users use the bot, available for admin only"),
    LIST_GROUP_SUB("list of groups you are subscribed to"),
    ADD_GROUP_SUB("add subscription to the group of articles"), 
    DELETE_GROUP_SUB("delete subscription to the group of articles");
    

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
