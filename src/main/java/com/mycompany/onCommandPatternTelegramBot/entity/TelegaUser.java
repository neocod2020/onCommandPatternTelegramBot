package com.mycompany.onCommandPatternTelegramBot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Entity
@Table(name="tg_user")
@Data
public class TelegaUser {
    
    @Id
    @Column(name="chatId")
    private String chatId;
    
    @Column(name="actives")
    private boolean actives;
    
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<GroupSub> groupSubs;
}
