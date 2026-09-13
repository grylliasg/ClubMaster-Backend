package com.clubmaster.clubmaster.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(
        name = "players",
        indexes = {
                @Index(name = "idx_players_last_name", columnList = "last_name")
        }
)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotBlank
    @Column(length = 30)
    private String position;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    protected Player() {}

    public Player(String firstName, String lastName, String position, String description, LocalDate dateOfBirth, Team team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.team = team;
    }

}