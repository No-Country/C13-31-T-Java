package com.c1331tjava.ServiceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Request {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserEntity client;
    @Column
    private LocalDateTime date;
    @ManyToOne
    private Zone zone;
    @Column (length = 200)
    private String description;
    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ImagesR> images;
    @OneToMany (fetch = FetchType.EAGER)
    private Set<Bid> bids;
    @OneToMany (fetch = FetchType.EAGER)
    private Set<UserEntity> providers;
    @Column (length = 200)
    private String comments;
    @Column (columnDefinition = "boolean default false")
    private Boolean ended;
    @Column (columnDefinition = "boolean default true")
    private Boolean active;

}
