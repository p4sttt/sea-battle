package net.d4y2k.seabattle.map.cell;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.d4y2k.seabattle.map.cell.dto.CellDTO;
import net.d4y2k.seabattle.ship.Ship;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cell_id")
    private UUID id;

    @Column(name = "horizontal_coordinate", nullable = false)
    private int horizontalCoordinate;

    @Column(name = "vertical_coordinate", nullable = false)
    private int verticalCoordinate;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;

    public Cell(int horizontalCoordinate, int verticalCoordinate, Ship ship) {
        this.horizontalCoordinate = horizontalCoordinate;
        this.verticalCoordinate = verticalCoordinate;
        this.ship = ship;
    }

    public CellDTO toDTO() {
        return new CellDTO(id, horizontalCoordinate, verticalCoordinate, ship);
    }

}
