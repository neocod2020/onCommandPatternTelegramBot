package com.mycompany.onCommandPatternTelegramBot.bot;

import com.mycompany.onCommandPatternTelegramBot.command.CommandContainer;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.NO;
import com.mycompany.onCommandPatternTelegramBot.command.MenueList;
import com.mycompany.onCommandPatternTelegramBot.configuration.BotConfig;
import com.mycompany.onCommandPatternTelegramBot.jRClient.JrGroupClient;
import com.mycompany.onCommandPatternTelegramBot.service.GroupSubService;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageServiceImpl;
import com.mycompany.onCommandPatternTelegramBot.service.StatisticsService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class OnCommandPatternTelegramBot extends TelegramLongPollingBot {

    final TelegaUserService telegaUserService;

    final BotConfig botConfig;

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    private final MenueList menueList = new MenueList();

    public OnCommandPatternTelegramBot(BotConfig botConfig, TelegaUserService telegaUserService,
            JrGroupClient jrGroupClient, GroupSubService groupSubService,
            @Value("#{'${bot.admins}'.split(',')}") List<String> admins, StatisticsService statisticsService) {
        this.botConfig = botConfig;
        this.telegaUserService = telegaUserService;
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this),
                telegaUserService, jrGroupClient, groupSubService, admins, statisticsService);
        SetMyCommands smc = new SetMyCommands(menueList.getBotCommands(), new BotCommandScopeDefault(), null);
        try {
            this.execute(smc);
        } catch (TelegramApiException ex) {
            log.error("Error executing menue of command " + ex.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String msg = update.getMessage().getText().trim();
            String username = update.getMessage().getFrom().getFirstName();
            if (msg.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = msg.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier, username).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName(), username).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getUsername();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}
