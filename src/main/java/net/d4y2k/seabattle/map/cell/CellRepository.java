package net.d4y2k.seabattle.map.cell;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CellRepository extends JpaRepository<Cell, UUID> {
}
