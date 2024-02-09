package net.d4y2k.seabattle.ship;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.exceptions.ValidationException;
import net.d4y2k.seabattle.prize.Prize;
import net.d4y2k.seabattle.prize.PrizeService;
import net.d4y2k.seabattle.ship.dto.CreateShipDTO;
import net.d4y2k.seabattle.ship.dto.ShipDTO;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/ships")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class ShipAdminController {

    private final ShipService shipService;
    private final PrizeService prizeService;

    @GetMapping("")
    public ResponseEntity<Response> getAll(HttpServletRequest request) {
        List<Ship> shipList = shipService.getAll();
        List<ShipDTO> shipDTOList = shipList.stream().map(Ship::toDTO).toList();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, shipDTOList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shipId}")
    public ResponseEntity<Response> getById(@PathVariable UUID shipId, HttpServletRequest request) {
        Ship ship = shipService.getById(shipId);
        ShipDTO shipDTO = ship.toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, shipDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<Response> create(
            @RequestBody @Valid CreateShipDTO createShipDTO,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Prize prize = prizeService.getById(createShipDTO.getPrizeId());
        Ship ship = new Ship(1, prize);
        ShipDTO shipDTO = shipService.save(ship).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, shipDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{shipId}")
    public ResponseEntity<Response> delete(@PathVariable UUID shipId, HttpServletRequest request) {
        shipService.delete(shipId);

        Response response = new Response(
                request.getRequestURI(),
                HttpStatus.OK,
                "Ship with id: " + shipId + " successfully deleted"
        );
        return ResponseEntity.ok(response);
    }

}
