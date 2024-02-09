package net.d4y2k.seabattle.ship;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.d4y2k.seabattle.prize.Prize;
import net.d4y2k.seabattle.ship.dto.ShipDTO;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    @Column(name = "ship_id")
    private UUID id;

    @Column(name = "decks", nullable = false)
    private int decks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ship_prize",
            joinColumns = @JoinColumn(name = "ship_id"),
            inverseJoinColumns = @JoinColumn(name = "prize_id")
    )
    private Prize prize;

    public Ship(int decks, Prize prize) {
        this.decks = decks;
        this.prize = prize;
    }

    public ShipDTO toDTO() {
        return new ShipDTO(id, prize);
    }

}
