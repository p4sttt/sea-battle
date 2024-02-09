package net.d4y2k.seabattle.user.role;

import net.d4y2k.seabattle.exceptions.EntityNotFoundException;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException(String authority) {
        super("Role with authority: " + authority + " not found!");
    }

    public RoleNotFoundException() {
        super("Role not found!");
    }
}
