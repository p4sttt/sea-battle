package net.d4y2k.seabattle.user.role;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "role_id")
    private UUID id;

    @Column(name = "authority", nullable = false, unique = true)
    private String authority;

    Role(String authority) {
        this.authority = authority;
    }

}
