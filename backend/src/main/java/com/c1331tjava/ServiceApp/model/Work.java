package com.c1331tjava.ServiceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Work {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.EAGER)
    private UserEntity client;

    @ManyToOne (fetch = FetchType.EAGER)
    private UserEntity provider;

    @Column
    private LocalDateTime starDate;

    @Column
    private LocalDateTime endDate;

    @ManyToOne (fetch = FetchType.EAGER)
    private Bid bid;

    @ManyToOne (fetch = FetchType.EAGER)
    private Request request;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ImagesW> images;

    @Column (columnDefinition = "boolean default false")
    private Boolean ended;

    @Column (columnDefinition = "boolean default true")
    private Boolean active;
}
