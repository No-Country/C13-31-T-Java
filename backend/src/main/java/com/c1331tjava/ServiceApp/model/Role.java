package com.c1331tjava.ServiceApp.model;


import com.c1331tjava.ServiceApp.model.enums.RolesNames;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RolesNames name;

}
