package com.mycompany.onCommandPatternTelegramBot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
