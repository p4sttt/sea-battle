package net.d4y2k.seabattle.ship;

import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.ship.exception.ShipNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShipService {

    private final ShipRepository shipRepository;

    public List<Ship> getAll() {
        return shipRepository.findAll();
    }

    public Ship getById(UUID id) {
        return shipRepository.findById(id)
                .orElseThrow(() -> new ShipNotFoundException(id));
    }

    public Ship save(Ship ship) {
        return shipRepository.save(ship);
    }

    public void delete(UUID id) {
        shipRepository.deleteById(id);
    }

}
