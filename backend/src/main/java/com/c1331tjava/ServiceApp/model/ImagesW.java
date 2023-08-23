package com.c1331tjava.ServiceApp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "imagesw")
@AllArgsConstructor
@Getter @Setter
public class ImagesW {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageLink;

    @ManyToOne
    private Work work;

}
