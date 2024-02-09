package net.d4y2k.seabattle.map.strike;

import net.d4y2k.seabattle.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StrikeRepository extends JpaRepository<Strike, UUID> {

    Optional<Strike> findByUser(User user);

}
