package com.example.test.entity;


import com.example.test.entity.commen.JPAEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = "id", callSuper = false)
@Table(name = "role")
public class Role extends JPAEntity
{
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns =@JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
}
