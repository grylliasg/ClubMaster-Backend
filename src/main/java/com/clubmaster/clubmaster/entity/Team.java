package com.clubmaster.clubmaster.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String country;

    protected Team() {}

    public Team(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
