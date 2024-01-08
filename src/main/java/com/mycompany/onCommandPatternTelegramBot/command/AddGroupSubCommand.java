package com.mycompany.onCommandPatternTelegramBot.command;

import static com.mycompany.onCommandPatternTelegramBot.command.CommandName.ADD_GROUP_SUB;
import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.jRClient.JrGroupClient;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupDiscussionInfo;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupRequestArgs;
import com.mycompany.onCommandPatternTelegramBot.service.GroupSubService;
import com.mycompany.onCommandPatternTelegramBot.service.SendBotMessageService;
import static java.util.Objects.isNull;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Add subscription to group {@link Command}
 */
@Slf4j
public class AddGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final JrGroupClient jrGroupClient;
    private final GroupSubService groupSubService;

    public final static String ADD_MESSAGE = "To suscribe to the group send command with groupID. \n"
            + "For example: /add 30 \n\n"
            + "Here list of the groups: \n\n"
            + "Group name - group ID \n\n";
    private final String GROUP_NOT_FOUND_MESSAGE = "There is no group with ID = ";
    private final String INCORRECT_GROUP_ID_MESSAGE = "Incorrect group ID = ";

    public AddGroupSubCommand(SendBotMessageService sendBotMessageService, JrGroupClient jrGroupClient,
            GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.jrGroupClient = jrGroupClient;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        if ((update.getMessage().getText()).equalsIgnoreCase(ADD_GROUP_SUB.getCommandName())) {
            sendGroupIdList(chatId);
            return;
        }
        String groupId = update.getMessage().getText().split(" ")[1];
        if (isNumeric(groupId)) {
            GroupDiscussionInfo groupDiscussionInfoById = jrGroupClient.getGroupById(Integer.parseInt(groupId));
            if (isNull(groupDiscussionInfoById.getId())) {
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub savedGroupSub = groupSubService.save(String.valueOf(chatId), groupDiscussionInfoById);
            sendBotMessageService.sendMessage(String.valueOf(chatId),
                    "You are subscribed on group " + savedGroupSub.getTitle());
        } else {
            sendNotValidGroupId(chatId, groupId);
        }
    }

    private void sendGroupIdList(Long chatId) {
        // Collect all groups titles and IDs
        String groupIds = jrGroupClient.getGroupList(GroupRequestArgs.builder().build()).stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());
        String message = ADD_MESSAGE + "%s";
        sendBotMessageService.sendMessage(String.valueOf(chatId), String.format(message, groupIds));
    }

    private void sendGroupNotFound(Long chatId, String groupId) {
        sendBotMessageService.sendMessage(String.valueOf(chatId),
                String.format(GROUP_NOT_FOUND_MESSAGE + "%s", groupId));
    }

    private void sendNotValidGroupId(Long chatId, String groupId) {
        sendBotMessageService.sendMessage(String.valueOf(chatId),
                String.format(INCORRECT_GROUP_ID_MESSAGE + "%s", groupId));
    }
}
