package net.d4y2k.seabattle.map.cell;

import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.map.cell.exception.CellNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CellService {

    private final CellRepository cellRepository;

    public Cell getById(UUID cellId) {
        return cellRepository.findById(cellId)
                .orElseThrow(() -> new CellNotFoundException(cellId));
    }

    public Cell save(Cell cell) {
        return cellRepository.save(cell);
    }

    public void delete(UUID cellId) {
        cellRepository.deleteById(cellId);
    }

    public void removeShip(Cell cell) {
        cell.setShip(null);
        cellRepository.save(cell);
    }

}
