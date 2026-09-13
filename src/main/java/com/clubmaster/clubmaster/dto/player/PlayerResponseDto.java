package com.clubmaster.clubmaster.dto.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PlayerResponseDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String position;
    private String description;
    private LocalDate dateOfBirth;
    private Integer teamId;
}
