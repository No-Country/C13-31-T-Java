package com.c1331tjava.ServiceApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Rating {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity client;

    @ManyToOne
    private UserEntity provider;

    @ManyToOne
    private Request request;

    @Column
    private Integer value;

    @Column (length = 200)
    private String comment;
}
