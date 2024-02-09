package net.d4y2k.seabattle.map.cell;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.exceptions.ValidationException;
import net.d4y2k.seabattle.map.Map;
import net.d4y2k.seabattle.map.MapService;
import net.d4y2k.seabattle.map.cell.dto.ShotToCellDTO;
import net.d4y2k.seabattle.map.cell.exception.ShotsAreOverException;
import net.d4y2k.seabattle.map.strike.Strike;
import net.d4y2k.seabattle.map.strike.StrikeService;
import net.d4y2k.seabattle.prize.Prize;
import net.d4y2k.seabattle.user.User;
import net.d4y2k.seabattle.user.UserService;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/maps/{mapsId}/cells")
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class CellController {

    private final CellService cellService;
    private final MapService mapService;
    private final StrikeService strikeService;
    private final UserService userService;

    @PostMapping("/shot")
    public ResponseEntity<Response> shootToCell(
            @PathVariable UUID mapsId,
            @RequestBody @Valid ShotToCellDTO shotToCellDTO,
            BindingResult bindingResult,
            @AuthenticationPrincipal User user,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Map map = mapService.getById(mapsId);
        Strike strike = strikeService.getByUser(user);

        if (strike.getCount() <= 0) {
            throw new ShotsAreOverException();
        }

        strike.setCount(strike.getCount() - 1);
        strike.setPossibleToDelete(false);
        strikeService.save(strike);

        Cell cell = mapService.getCellByCoordinate(map, shotToCellDTO.getHorizontalCoordinate(), shotToCellDTO.getVerticalCoordinate());
        Prize prize = cell.getShip().getPrize();

        user.getPrizes().add(prize);
        userService.save(user);
        cellService.delete(cell.getId());

        map.setEditable(false);
        mapService.save(map);

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, prize.toDTO());
        return ResponseEntity.ok(response);
    }

}
