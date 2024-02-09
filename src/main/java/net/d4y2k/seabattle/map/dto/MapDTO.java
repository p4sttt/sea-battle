package net.d4y2k.seabattle.map.dto;

import lombok.Data;
import net.d4y2k.seabattle.map.cell.Cell;
import net.d4y2k.seabattle.map.cell.dto.CellDTO;
import net.d4y2k.seabattle.map.strike.Strike;
import net.d4y2k.seabattle.map.strike.dto.StrikeDTO;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
public class MapDTO {

    private UUID id;
    private int size;
    private List<StrikeDTO> users;
    private List<CellDTO> cells;

    public MapDTO(UUID id, int size, Collection<Strike> strikes, Collection<Cell> cells) {
        this.id = id;
        this.size = size;
        this.users = strikes.stream().map(Strike::toDTO).toList();
        this.cells = cells.stream().map(Cell::toDTO).toList();
    }
}
