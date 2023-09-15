package com.c1331tjava.ServiceApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Notification {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @Column (columnDefinition = "boolean default false")
    private Boolean viewed;
}
