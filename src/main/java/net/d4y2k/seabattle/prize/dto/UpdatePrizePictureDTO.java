package net.d4y2k.seabattle.prize.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePrizePictureDTO {

    @NotBlank(message = "Picture is required")
    private String picture;

}
