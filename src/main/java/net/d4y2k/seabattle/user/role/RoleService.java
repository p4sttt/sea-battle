package net.d4y2k.seabattle.user.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getByAuthority(String authority) {
        return roleRepository.findByAuthority(authority)
                .orElseThrow(() -> new RoleNotFoundException(authority));
    }

    public Role save(String authority) {
        return roleRepository.save(new Role(authority));
    }

}
