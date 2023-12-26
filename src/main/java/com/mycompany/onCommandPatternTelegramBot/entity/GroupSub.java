package com.mycompany.onCommandPatternTelegramBot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "group_sub")
@Data
@EqualsAndHashCode
public class GroupSub {

    @Id
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "last_article_id")
    private Integer lastArticleId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "group_x_user",
            joinColumns = @JoinColumn(name = "group_sub_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<TelegaUser> users;

    public void addUser(TelegaUser telegaUser) {
        if (isNull(users)) {
            users = new ArrayList<>();
        }
        users.add(telegaUser);
    }
}
