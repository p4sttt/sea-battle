package net.d4y2k.seabattle.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.user.dto.UserDTO;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/self")
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("")
    public ResponseEntity<Response> getSelfProfile(
            @AuthenticationPrincipal User user,
            HttpServletRequest request
    ) {
        UserDTO userDTO = user.toDTO();
        Response response = new Response(request.getRequestURI(), HttpStatus.OK, userDTO);
        return ResponseEntity.ok(response);
    }

}
