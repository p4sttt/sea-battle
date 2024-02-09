package net.d4y2k.seabattle.map.strike;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.d4y2k.seabattle.map.strike.dto.StrikeDTO;
import net.d4y2k.seabattle.user.User;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class Strike {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "strike_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int count = 2;

    @Column(name = "is_possible_to_delete", nullable = false)
    private boolean isPossibleToDelete = true;

    public Strike(User user, int count) {
        this.user = user;
        this.count = count;
    }

    public StrikeDTO toDTO() {
        return new StrikeDTO(id, user, count);
    }

}
