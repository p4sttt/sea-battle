package net.d4y2k.seabattle.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.exceptions.ValidationException;
import net.d4y2k.seabattle.user.dto.UpdateUserRoleDTO;
import net.d4y2k.seabattle.user.dto.UserDTO;
import net.d4y2k.seabattle.user.role.Role;
import net.d4y2k.seabattle.user.role.RoleService;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("")
    public ResponseEntity<Response> fetchAll(HttpServletRequest request) {
        List<UserDTO> userDTOList = userService.getAll().stream().map(User::toDTO).toList();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, userDTOList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Response> fetchById(@PathVariable UUID userId, HttpServletRequest request) {
        UserDTO user = userService.getById(userId).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{userId}/role")
    public ResponseEntity<Response> changeUserRole(
            @RequestBody @Valid UpdateUserRoleDTO updateUserRoleDTO,
            BindingResult bindingResult,
            @PathVariable UUID userId,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }

        User user = userService.getById(userId);
        Role role = roleService.getByAuthority(updateUserRoleDTO.getAuthority());

        user.setRole(role);
        UserDTO userDTO = userService.save(user).toDTO();

        Response response = new Response(request.getRequestURI(), HttpStatus.OK, userDTO);
        return ResponseEntity.ok(response);
    }

}
