package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.DELETE_GROUP_SUB;
import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.service.GroupSubService;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import com.mycompany.onCommandPatternTelegramBot.service.TelegaUserService;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import org.springframework.util.CollectionUtils;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Delete Group subscription {@link Command}
 */
public class DeleteGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegaUserService telegaUserService;
    private final GroupSubService groupSubService;
    
    public final static String DELETING_DONE_MESSAGE = "Subscription to the group %s is deleted \n\n";
    public final static String NO_SUBS_MESSAGE = "You have not subscribes yet. To add subscription "
            + "send '/add' please.";
    public final static String DELETE_MESSAGE = "To remove subscription to the group please"
            + " send '/delete' and group ID \n"
            + "for example: /delete 16 \n\n"
            + "Down here list of your subscriptions: \n\n"
            + "Group title(name) - group ID \n\n"
            + "%s";
    public final static String GROUP_NOT_FOUND_MESSAGE = "Group is not founded";
    public final static String INCORRECT_ID_MESSAGE = "Incorrect ID. ID is to be integer";
 
    public DeleteGroupSubCommand(SendBotMessageService sendBotMessageService, TelegaUserService telegaUserService, GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegaUserService = telegaUserService;
        this.groupSubService = groupSubService;
    }
    
    @Override
    public void execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        if ((update.getMessage().getText()).equalsIgnoreCase(DELETE_GROUP_SUB.getCommandName())) {
            sendGroupIdList(chatId);
            return;
        }        
        String groupId = update.getMessage().getText().split(" ")[1];
        if (isNumeric(groupId)){
            Optional<GroupSub> optionalGroupSub = groupSubService.findById(Integer.valueOf(groupId));
            if(optionalGroupSub.isPresent()){
                GroupSub groupSub = optionalGroupSub.get();
                TelegaUser telegaUser = telegaUserService.findByChatId(String.valueOf(chatId))
                        .orElseThrow(NotFoundException::new);
                groupSub.getUsers().remove(telegaUser);
                groupSubService.save(groupSub);
                sendBotMessageService.sendMessage(String.valueOf(chatId)
                        , String.format(DELETING_DONE_MESSAGE, groupSub.getTitle()));
            } else {
                sendBotMessageService.sendMessage(String.valueOf(chatId), GROUP_NOT_FOUND_MESSAGE);                
            }
        } else {
            sendBotMessageService.sendMessage(String.valueOf(chatId), INCORRECT_ID_MESSAGE);
        }        
    }
    private void sendGroupIdList(Long chatId) {
        // Collect all groups user subscribed
        String message;
        List<GroupSub> groupSubs = telegaUserService.findByChatId(String.valueOf(chatId))
                .orElseThrow(NotFoundException::new)
                .getGroupSubs();
        if(CollectionUtils.isEmpty(groupSubs)){
            message = NO_SUBS_MESSAGE;
        } else {
            message = DELETE_MESSAGE;
        }
        String userGroupSubData = groupSubs.stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());
        
        sendBotMessageService.sendMessage(String.valueOf(chatId), String.format(message, userGroupSubData));
    }
}
