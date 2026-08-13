package com.clubmaster.clubmaster.dto.player;

import com.clubmaster.clubmaster.entity.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CreatePlayerDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String position;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private Team team;

}
