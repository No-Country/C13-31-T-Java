package com.c1331tjava.ServiceApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Request {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User client;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    private Zone zone;

    @Column (length = 200)
    private String description;

    @OneToMany (mappedBy = "request")
    private List<ImagesR> images;

    @OneToMany (mappedBy = "request")
    private List<Bid> bids;

    @Column (length = 200)
    private String comments;

    @Column (columnDefinition = "boolean default false")
    private Boolean ended;

    @Column (columnDefinition = "boolean default true")
    private Boolean active;

}
