package net.d4y2k.seabattle.map;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MapRepository extends JpaRepository<Map, UUID> {
}
