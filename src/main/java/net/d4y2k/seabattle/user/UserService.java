package net.d4y2k.seabattle.user;

import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.user.role.Role;
import net.d4y2k.seabattle.user.exception.UserNotFoundException;
import net.d4y2k.seabattle.user.role.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User create(User user) {
        String password = user.getPassword();
        String passwordHash = passwordEncoder.encode(password);
        user.setPassword(passwordHash);

        Role defaultRole = roleService.getByAuthority("USER");
        user.setRole(defaultRole);

        return userRepository.save(user);
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(UUID userId) {
        userRepository.deleteById(userId);
    }

}
