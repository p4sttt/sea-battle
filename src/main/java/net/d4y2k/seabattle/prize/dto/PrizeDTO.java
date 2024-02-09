package net.d4y2k.seabattle.prize.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PrizeDTO {

    private UUID id;
    private String name;
    private String description;
    private String picture;

    public PrizeDTO(UUID id, String name, String description, String picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
    }
}
