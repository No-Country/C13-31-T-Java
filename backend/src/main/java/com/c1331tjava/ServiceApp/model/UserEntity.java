package com.c1331tjava.ServiceApp.model;



import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserEntity {
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String imageLink;
    @Column (length = 20, nullable = false)
    private String userName;
    @Column (length = 20, nullable = false)
    private String userLastname;
    @Column (length = 40, nullable = false)
    private String email;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;
    @Column (length = 60, nullable = false)
    private String password;
    @Column (length = 30)
    private String te;
    @Column (columnDefinition = "boolean default true")
    private Boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Area> areas;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Notification> notifications;
}
