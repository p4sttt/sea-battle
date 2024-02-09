package net.d4y2k.seabattle.map;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.d4y2k.seabattle.map.cell.Cell;
import net.d4y2k.seabattle.map.dto.MapDTO;
import net.d4y2k.seabattle.map.strike.Strike;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "map_id")
    private UUID id;

    @Column(name = "size", nullable = false)
    private int size;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "map_strike",
            joinColumns = @JoinColumn(name = "map_id"),
            inverseJoinColumns = @JoinColumn(name = "strike_id")
    )
    private Set<Strike> strikes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "map_cell",
            joinColumns = @JoinColumn(name = "map_id"),
            inverseJoinColumns = @JoinColumn(name = "cell_id")
    )
    private Set<Cell> cells = new HashSet<>();

    @Column(name = "is_editable", nullable = false)
    private boolean isEditable = true;

    public Map(int size) {
        this.size = size;
    }

    public MapDTO toDTO() {
        return new MapDTO(id, size, strikes, cells);
    }
}
