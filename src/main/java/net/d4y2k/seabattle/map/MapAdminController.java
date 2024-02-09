package net.d4y2k.seabattle.map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.exceptions.ValidationException;
import net.d4y2k.seabattle.map.dto.CreateMapDTO;
import net.d4y2k.seabattle.map.dto.MapDTO;
import net.d4y2k.seabattle.map.dto.UpdateMapSizeDTO;
import net.d4y2k.seabattle.map.exception.MapNotAbleToEdit;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/maps")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class MapAdminController {

    private final MapService mapService;

    @GetMapping("")
    public ResponseEntity<Response> fetchAll(HttpServletRequest request) {
        List<Map> mapList = mapService.getAll();
        List<MapDTO> mapDTOList = mapList.stream().map(Map::toDTO).toList();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, mapDTOList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{mapId}")
    public ResponseEntity<Response> fetchById(@PathVariable UUID mapId, HttpServletRequest request) {
        Map map = mapService.getById(mapId);
        MapDTO mapDTO = map.toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, mapDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{mapId}/size")
    public ResponseEntity<Response> updateMapSize(
            @RequestBody @Valid UpdateMapSizeDTO updateMapSizeDTO,
            BindingResult bindingResult,
            @PathVariable UUID mapId,
            HttpServletRequest request
            ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Map map = mapService.getById(mapId);
        if (!map.isEditable()) {
            throw new MapNotAbleToEdit(mapId);
        }

        map.setSize(updateMapSizeDTO.getSize());
        MapDTO mapDTO = mapService.save(map).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, mapDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<Response> create(
            @RequestBody @Valid CreateMapDTO createMapDTO,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Map map = new Map(createMapDTO.getSize());
        MapDTO mapDTO = mapService.save(map).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, mapDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{mapId}")
    public ResponseEntity<Response> delete(@PathVariable UUID mapId, HttpServletRequest request) {
        Map map = mapService.getById(mapId);
        if (!map.isEditable()) {
            throw new MapNotAbleToEdit(mapId);
        }

        mapService.delete(mapId);

        Response response = new Response(
                request.getRequestURI(),
                HttpStatus.OK,
                "Map with id: " + mapId + " successfully created"
        );
        return ResponseEntity.ok(response);
    }

}
