package net.d4y2k.seabattle.auth.exception;

public class LoginException extends RuntimeException{
    public LoginException() {
        super("Incorrect password or user not found");
    }
}
