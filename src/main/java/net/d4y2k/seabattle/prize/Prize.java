package net.d4y2k.seabattle.prize;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.d4y2k.seabattle.prize.dto.PrizeDTO;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Prize {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "prize_id")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "picture", nullable = false)
    private String picture;

    public Prize(String name, String description, String picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public PrizeDTO toDTO() {
        return new PrizeDTO(id, name, description, picture);
    }
}
