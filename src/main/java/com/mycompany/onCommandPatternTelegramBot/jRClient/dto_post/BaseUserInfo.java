package com.mycompany.onCommandPatternTelegramBot.jRClient.dto_post;

import lombok.Data;

/**
 * DTO represents base user information
 */
@Data
public class BaseUserInfo {
    private String city;
    private String country;
    private String displayName;
    private Integer id;
    private String job;
    private String key;
    private Integer level;
    private String pictureUrl;
    private String position;
    private UserPublicStatus publicStatus;
    private String publicStatusMessage;
    private Integer rating;
    private Integer userId;
}
