package net.d4y2k.seabattle.map.strike;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.exceptions.ValidationException;
import net.d4y2k.seabattle.map.strike.dto.UpdateStrikeCountDTO;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/strikes")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class StrikeAdminController {

    private final StrikeService strikeService;

    @PatchMapping("/{strikeId}/count")
    public ResponseEntity<Response> updateStrikeCount(
            @PathVariable UUID strikeId,
            @RequestBody @Valid UpdateStrikeCountDTO updateStrikeCountDTO,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        Strike strike = strikeService.getById(strikeId);
        strike.setCount(updateStrikeCountDTO.getCount());

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, strikeService.save(strike));
        return ResponseEntity.ok(response);
    }
}
