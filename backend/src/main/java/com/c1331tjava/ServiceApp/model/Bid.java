package com.c1331tjava.ServiceApp.model;

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
public class Bid {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity provider;

    @Column (length = 200)
    private String response;

    @Column
    private Float budget;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column (length = 200)
    private String comments;

    @Column (columnDefinition = "boolean default false")
    private Boolean interviewed;

    @Column (columnDefinition = "boolean default true")
    private Boolean active;
}
