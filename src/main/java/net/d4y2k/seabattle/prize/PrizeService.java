package net.d4y2k.seabattle.prize;

import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.prize.exception.PrizeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrizeService {

    private final PrizeRepository prizeRepository;

    public List<Prize> getAll() {
        return prizeRepository.findAll();
    }

    public Prize getById(UUID id) {
        return prizeRepository.findById(id)
                .orElseThrow(() -> new PrizeNotFoundException(id));
    }

    public Prize save(Prize prize){
        return prizeRepository.save(prize);
    }

    public void delete(UUID id) {
        prizeRepository.deleteById(id);
    }

}
