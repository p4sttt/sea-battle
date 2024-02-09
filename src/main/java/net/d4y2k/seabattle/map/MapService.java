package net.d4y2k.seabattle.map;

import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.map.cell.Cell;
import net.d4y2k.seabattle.map.cell.exception.CellInMapNotFoundException;
import net.d4y2k.seabattle.map.exception.MapNotFoundException;
import net.d4y2k.seabattle.map.strike.Strike;
import net.d4y2k.seabattle.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    public List<Map> getAll() {
        return mapRepository.findAll();
    }

    public Map getById(UUID id) {
        return mapRepository.findById(id)
                .orElseThrow(() -> new MapNotFoundException(id));
    }

    public Map save(Map map) {
        return mapRepository.save(map);
    }

    public void delete(UUID id) {
        mapRepository.deleteById(id);
    }

    public Cell getCellByCoordinate(Map map, int x, int y) {
        return map.getCells().stream()
                .filter(cell -> cell.getHorizontalCoordinate() == x && cell.getVerticalCoordinate() == y)
                .findFirst()
                .orElseThrow(CellInMapNotFoundException::new);
    }

    public boolean hasCellWithCoordinate(Map map, int x, int y) {
        getCellByCoordinate(map, x, y);
        return true;
    }

    public Optional<Strike> getStrikeByUser(Map map, User user) {
        return map.getStrikes().stream()
                .filter(strike -> strike.getUser().equals(user))
                .findFirst();
    }

    public boolean hasStrikeWithUser(Map map, User user) {
        Optional<Strike> optionalStrike = getStrikeByUser(map, user);
        return optionalStrike.isPresent();
    }

}
