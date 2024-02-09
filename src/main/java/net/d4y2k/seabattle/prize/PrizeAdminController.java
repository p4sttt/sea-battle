package net.d4y2k.seabattle.prize;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.prize.dto.*;
import net.d4y2k.seabattle.exceptions.ValidationException;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/prizes")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class PrizeAdminController {

    private final PrizeService prizeService;

    @GetMapping("")
    public ResponseEntity<Response> fetchAll(HttpServletRequest request) {
        List<Prize> prizeList = prizeService.getAll();
        List<PrizeDTO> prizeDTOList = prizeList.stream().map(Prize::toDTO).toList();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, prizeDTOList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{prizeId}")
    public ResponseEntity<Response> fetchById(@PathVariable UUID prizeId, HttpServletRequest request) {
        Prize prize = prizeService.getById(prizeId);
        PrizeDTO prizeDTOList = prize.toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, prizeDTOList);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<Response> create(
            @RequestBody @Valid CreatePrizeDTO createPrizeDTO,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Prize prize = new Prize(
                createPrizeDTO.getName(),
                createPrizeDTO.getDescription(),
                createPrizeDTO.getPicture()
        );
        PrizeDTO prizeDTO = prizeService.save(prize).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, prizeDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{prizeId}/name")
    public ResponseEntity<Response> updateName(
            @RequestBody @Valid UpdatePrizeNameDTO updatePrizeNameDTO,
            BindingResult bindingResult,
            HttpServletRequest request,
            @PathVariable UUID prizeId
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Prize prize = prizeService.getById(prizeId);
        prize.setName(updatePrizeNameDTO.getName());
        PrizeDTO prizeDTO = prizeService.save(prize).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, prizeDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{prizeId}/description")
    public ResponseEntity<Response> updateDescription(
            @RequestBody @Valid UpdatePrizeDescriptionDTO updatePrizeDescriptionDTO,
            BindingResult bindingResult,
            HttpServletRequest request,
            @PathVariable UUID prizeId
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Prize prize = prizeService.getById(prizeId);
        prize.setDescription(updatePrizeDescriptionDTO.getDescription());
        PrizeDTO prizeDTO = prizeService.save(prize).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, prizeDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{prizeId}/picture")
    public ResponseEntity<Response> updatePicture(
            @RequestBody @Valid UpdatePrizePictureDTO updatePrizePictureDTO,
            BindingResult bindingResult,
            HttpServletRequest request,
            @PathVariable UUID prizeId
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Prize prize = prizeService.getById(prizeId);
        prize.setPicture(updatePrizePictureDTO.getPicture());
        PrizeDTO prizeDTO = prizeService.save(prize).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, prizeDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{prizeId}")
    public ResponseEntity<Response> delete(@PathVariable UUID prizeId, HttpServletRequest request) {
        prizeService.delete(prizeId);

        Response response = new Response(
                request.getRequestURI(),
                HttpStatus.OK,
                "Prize with id: " + prizeId + " successfully deleted"
        );
        return ResponseEntity.ok(response);
    }

}
