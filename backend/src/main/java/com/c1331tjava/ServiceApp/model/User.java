package com.c1331tjava.ServiceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageLink;

    @Column (length = 20, nullable = false)
    private String userName;

    @Column (length = 20, nullable = false)
    private String userLastname;

    @Column (length = 20, nullable = false)
    private String email;

    @Column (length = 20, nullable = false)
    private String password;

    @Column (length = 20)
    private String te;

    @Column (columnDefinition = "boolean default true")
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Area> areas;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Notification> notifications;

}
