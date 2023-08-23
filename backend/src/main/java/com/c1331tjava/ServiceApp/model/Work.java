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
public class Work {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User provider;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date starDate;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @OneToOne
    private Request request;

    @OneToOne
    private Bid bid;

    @OneToMany (mappedBy = "work")
    private List<ImagesW> images;

    @Column (columnDefinition = "boolean default false")
    private Boolean ended;

    @Column (columnDefinition = "boolean default true")
    private Boolean active;

}
