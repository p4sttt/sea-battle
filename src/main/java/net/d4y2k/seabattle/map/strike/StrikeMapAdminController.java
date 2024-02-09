package net.d4y2k.seabattle.map.strike;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.exceptions.ValidationException;
import net.d4y2k.seabattle.map.Map;
import net.d4y2k.seabattle.map.MapService;
import net.d4y2k.seabattle.map.dto.MapDTO;
import net.d4y2k.seabattle.map.strike.dto.CreateStrikeDTO;
import net.d4y2k.seabattle.map.strike.dto.StrikeDTO;
import net.d4y2k.seabattle.map.strike.exception.StrikeNotPossibleToDeleteException;
import net.d4y2k.seabattle.user.User;
import net.d4y2k.seabattle.user.UserService;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/maps/{mapId}/strikes")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class StrikeMapAdminController {

    private final UserService userService;
    private final MapService mapService;
    private final StrikeService strikeService;

    @GetMapping("")
    public ResponseEntity<Response> getUsers(@PathVariable UUID mapId, HttpServletRequest request) {
        Map map = mapService.getById(mapId);
        List<StrikeDTO> strikeDTOList = map.getStrikes().stream().map(Strike::toDTO).toList();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, strikeDTOList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<Response> addUser(
            @PathVariable UUID mapId,
            @RequestBody @Valid CreateStrikeDTO createStrikeDTO,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        User user = userService.getById(createStrikeDTO.getUserId());
        Map map = mapService.getById(mapId);
        Strike strike = strikeService.save(new Strike(user, 2));
        map.getStrikes().add(strike);

        MapDTO mapDTO = mapService.save(map).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, mapDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{strikeId}")
    public ResponseEntity<Response> removeUser(
            @PathVariable UUID mapId,
            @PathVariable UUID strikeId,
            HttpServletRequest request
    ) {
        Strike strike = strikeService.getById(strikeId);
        if (strike.isPossibleToDelete()) {
            throw new StrikeNotPossibleToDeleteException(strikeId);
        }

        Map map = mapService.getById(mapId);
        map.getStrikes().remove(strike);

        MapDTO mapDTO = mapService.save(map).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, mapDTO);
        return ResponseEntity.ok(response);
    }

}
