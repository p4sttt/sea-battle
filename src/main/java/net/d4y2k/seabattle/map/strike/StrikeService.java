package net.d4y2k.seabattle.map.strike;

import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.map.strike.exception.StrikeNotFoundException;
import net.d4y2k.seabattle.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StrikeService {

    private final StrikeRepository strikeRepository;

    public List<Strike> getAll() {
        return strikeRepository.findAll();
    }

    public Strike getById(UUID strikeId) {
        return strikeRepository.findById(strikeId)
                .orElseThrow(() -> new StrikeNotFoundException(strikeId));
    }

    public Strike getByUser(User user) {
        return strikeRepository.findByUser(user).orElseThrow(StrikeNotFoundException::new);
    }

    public Strike save(Strike strike) {
        return strikeRepository.save(strike);
    }

    public void delete(UUID strikeId) {
        strikeRepository.deleteById(strikeId);
    }

}
