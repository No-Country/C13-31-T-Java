package com.c1331tjava.ServiceApp.model;


import com.c1331tjava.ServiceApp.model.enums.NotificationsNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Notification {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationsNames name;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column (columnDefinition = "boolean default false")
    private Boolean viewed;
}
