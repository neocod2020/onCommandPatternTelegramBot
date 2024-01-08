package com.mycompany.onCommandPatternTelegramBot.service;

import com.mycompany.onCommandPatternTelegramBot.entity.GroupSub;
import com.mycompany.onCommandPatternTelegramBot.entity.TelegaUser;
import com.mycompany.onCommandPatternTelegramBot.jRClient.dto.GroupDiscussionInfo;
import com.mycompany.onCommandPatternTelegramBot.repository.GroupSubRepository;
import jakarta.ws.rs.NotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupSubServiceImpl implements GroupSubService {

    private final GroupSubRepository groupSubRepository;
    private final TelegaUserService telegaUserService;

    @Autowired
    public GroupSubServiceImpl(GroupSubRepository groupSubRepository, TelegaUserService telegaUserService) {
        this.groupSubRepository = groupSubRepository;
        this.telegaUserService = telegaUserService;
    }

    @Override
    public GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo) {
        TelegaUser telegaUser = telegaUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
        GroupSub groupSub;
        Optional<GroupSub> groupSubFromDB = groupSubRepository.findById(groupDiscussionInfo.getId());
        if (groupSubFromDB.isPresent()) {
            groupSub = groupSubFromDB.get();
            Optional<TelegaUser> firstUser = groupSub.getUsers().stream()
                    .filter(it -> it.getChatId().equalsIgnoreCase(chatId))
                    .findFirst();
            if (firstUser.isEmpty()) {
                groupSub.addUser(telegaUser);
            }
        } else {
            groupSub = new GroupSub();
            groupSub.addUser(telegaUser);
            groupSub.setId(groupDiscussionInfo.getId());
            groupSub.setTitle(groupDiscussionInfo.getTitle());
        }
        return groupSubRepository.save(groupSub);
    }

    @Override
    public Optional<GroupSub> findById(Integer groupID) {
        return groupSubRepository.findById(groupID);
    }

    @Override
    public GroupSub save(GroupSub groupSub) {
        return groupSubRepository.save(groupSub);
    }

}
