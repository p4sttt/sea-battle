package net.d4y2k.seabattle.map.cell;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.exceptions.ValidationException;
import net.d4y2k.seabattle.map.Map;
import net.d4y2k.seabattle.map.MapService;
import net.d4y2k.seabattle.map.cell.dto.AddShipDTO;
import net.d4y2k.seabattle.map.cell.dto.CellDTO;
import net.d4y2k.seabattle.map.cell.dto.RemoveShipDTO;
import net.d4y2k.seabattle.map.dto.MapDTO;
import net.d4y2k.seabattle.map.exception.CellAlreadyBusy;
import net.d4y2k.seabattle.ship.Ship;
import net.d4y2k.seabattle.ship.ShipService;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/maps/{mapId}/cells")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class CellAdminController {

    private final CellService cellService;
    private final MapService mapService;
    private final ShipService shipService;

    @GetMapping("")
    public ResponseEntity<Response> fetchAll(@PathVariable UUID mapId, HttpServletRequest request) {
        Map map = mapService.getById(mapId);
        List<CellDTO> cellDTOList = map.getCells().stream().map(Cell::toDTO).toList();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, cellDTOList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<Response> placeShipToCell(
            @PathVariable UUID mapId,
            @RequestBody @Valid AddShipDTO addShipDTO,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Map map = mapService.getById(mapId);
        if (addShipDTO.getHorizontalCoordinate() >= map.getSize()
                || addShipDTO.getVerticalCoordinate() >= map.getSize()) {
            throw new ValidationException();
        }

        boolean isCellBusy = mapService.hasCellWithCoordinate(map, addShipDTO.getHorizontalCoordinate(), addShipDTO.getVerticalCoordinate());
        if (isCellBusy) {
            throw new CellAlreadyBusy(addShipDTO.getHorizontalCoordinate(), addShipDTO.getVerticalCoordinate());
        }

        Ship ship = shipService.getById(addShipDTO.getShipId());
        Cell cell = new Cell(addShipDTO.getHorizontalCoordinate(), addShipDTO.getVerticalCoordinate(), ship);
        cellService.save(cell);

        map.getCells().add(cell);
        MapDTO mapDTO = mapService.save(map).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, mapDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("")
    public ResponseEntity<Response> removeShipFromCell(
            @PathVariable UUID mapId,
            @RequestBody @Valid RemoveShipDTO removeShipDTO,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Map map = mapService.getById(mapId);
        Cell cell = mapService.getCellByCoordinate(map, removeShipDTO.getHorizontalCoordinate(), removeShipDTO.getVerticalCoordinate());

        map.getCells().remove(cell);
        cellService.delete(cell.getId());
        MapDTO mapDTO = mapService.save(map).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, mapDTO);
        return ResponseEntity.ok(response);
    }

}
