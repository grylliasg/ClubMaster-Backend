package com.clubmaster.clubmaster.dto.player;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferPlayerDto {

    @NotNull
    private Integer teamId;
}
