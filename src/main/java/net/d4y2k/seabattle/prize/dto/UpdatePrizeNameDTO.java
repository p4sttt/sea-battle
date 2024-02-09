package net.d4y2k.seabattle.prize.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePrizeNameDTO {

    @NotBlank(message = "Name is required")
    private String name;

}
