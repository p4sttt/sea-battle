package net.d4y2k.seabattle.prize.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePrizeDescriptionDTO {

    @NotBlank(message = "Description is requried")
    private String description;

}
