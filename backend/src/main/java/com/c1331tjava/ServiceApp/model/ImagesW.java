package com.c1331tjava.ServiceApp.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "imagesw")
@Data
@NoArgsConstructor
public class ImagesW {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageLink;

    public ImagesW(String imageLink) {
        this.imageLink = imageLink;
    }

}
