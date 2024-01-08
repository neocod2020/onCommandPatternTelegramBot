package com.mycompany.onCommandPatternTelegramBot.command;

import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import jakarta.ws.rs.NotFoundException;
import java.util.stream.Collectors;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * {@link Command} for getting list of {@link GroupSub}
 */
public class ListGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegaUserService telegaUserService;
    public final static String LIST_MESSAGE = "Subscribes were founded: \n\n";

    public ListGroupSubCommand(SendBotMessageService sendBotMessageService, TelegaUserService telegaUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegaUserService = telegaUserService;
    }

    @Override
    public void execute(Update update) {
        TelegaUser telegaUser = telegaUserService.findByChatId(update.getMessage().getChatId().toString())
                .orElseThrow(NotFoundException::new);
        String collectedGroups = telegaUser.getGroupSubs().stream()
                .map(it -> "Group: " + it.getTitle() + ", ID = " + it.getId() + " \n")
                .collect(Collectors.joining());
        sendBotMessageService.sendMessage(telegaUser.getChatId(), LIST_MESSAGE + collectedGroups);

    }
}
