package net.d4y2k.seabattle.prize;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrizeRepository extends JpaRepository<Prize, UUID> {
}
