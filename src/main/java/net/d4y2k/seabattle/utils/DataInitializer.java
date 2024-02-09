package net.d4y2k.seabattle.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.d4y2k.seabattle.user.User;
import net.d4y2k.seabattle.user.role.Role;
import net.d4y2k.seabattle.user.UserService;
import net.d4y2k.seabattle.user.role.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        Role defaultRole = roleService.save("USER");
        log.info("default role successfully created: {}", defaultRole);

        Role adminRole = roleService.save("ADMIN");
        log.info("admin role successfully created: {}", adminRole);

        User admin = userService.create(new User("admin@gmail.com", "password123"));
        admin.setRole(adminRole);
        User savedUser = userService.save(admin);
        log.info("admin user successfully created: {}", savedUser);
    }
}
