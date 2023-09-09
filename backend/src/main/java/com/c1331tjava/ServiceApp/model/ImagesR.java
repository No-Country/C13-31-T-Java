package com.c1331tjava.ServiceApp.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "imagesr")
@Data
@NoArgsConstructor
public class ImagesR {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageLink;

    public ImagesR(String imageLink) {
        this.imageLink = imageLink;
    }
}
