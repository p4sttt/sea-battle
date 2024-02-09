package net.d4y2k.seabattle.prize.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePrizeDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Picture is required")
    private String picture;

}
