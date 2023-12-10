package com.mycompany.onCommandPatternTelegramBot.bot;

import com.mycompany.onCommandPatternTelegramBot.command.CommandContainer;
import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.NO;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageServiceImpl;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class OnCommandPatternTelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String username;

    @Value("${bot.token}")
    private String token;

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;
    

    @Autowired
    public OnCommandPatternTelegramBot(TelegaUserService telegaUserService) {        
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this),
                telegaUserService);
    }

    @Override
    public void onUpdateReceived(Update update) {            
        if (update.hasMessage() && update.getMessage().hasText()) {
            String msg = update.getMessage().getText().trim();

            if (msg.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = msg.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
