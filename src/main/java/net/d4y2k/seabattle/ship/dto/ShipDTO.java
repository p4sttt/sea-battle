package net.d4y2k.seabattle.ship.dto;

import lombok.Data;
import net.d4y2k.seabattle.prize.Prize;
import net.d4y2k.seabattle.prize.dto.PrizeDTO;

import java.util.UUID;

@Data
public class ShipDTO {

    private UUID id;
    private PrizeDTO prize;

    public ShipDTO(UUID id, Prize prize) {
        this.id = id;
        this.prize = prize.toDTO();
    }
}
