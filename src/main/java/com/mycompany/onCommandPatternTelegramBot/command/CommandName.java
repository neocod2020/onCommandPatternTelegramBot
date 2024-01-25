package com.mycompany.onCommandPatternTelegramBot.command;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum CommandName {

    START("/start " + CommandDescription.START_DESCRIPTION.getDescription()),
    STOP("/stop " + CommandDescription.STOP_DESCRIPTION.getDescription()),
    HELP("/help " + CommandDescription.HELP_DESCRIPTION.getDescription()),
    NO("no command"),
    STATISTIC("/statistic " + CommandDescription.STATISTIC_DESCRIPTION.getDescription()),
    LIST_GROUP_SUB("/list " + CommandDescription.LIST_GROUP_SUB.getDescription()),
    ADD_GROUP_SUB("/add " + CommandDescription.ADD_GROUP_SUB.getDescription()),
    DELETE_GROUP_SUB("/delete " + CommandDescription.DELETE_GROUP_SUB.getDescription());

    @Getter
    private final String commandName;

    private CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getSimpleName(){
        String name = "";
        if(commandName.startsWith("/")){
            name = commandName.split(" ")[0].trim();
        }
        return name;
    }
    public String getDescription(){
        return  commandName.substring(commandName.indexOf(" ")).trim(); 
    }   

}
