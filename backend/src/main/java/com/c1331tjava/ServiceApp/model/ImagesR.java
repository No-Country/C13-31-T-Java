package com.c1331tjava.ServiceApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "imagesr")
@AllArgsConstructor
@Getter @Setter
public class ImagesR {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageLink;

    @ManyToOne
    private Request request;

}
