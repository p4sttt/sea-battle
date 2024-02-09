package net.d4y2k.seabattle.auth.exception;

public class SignupException extends RuntimeException {
    public SignupException() {
        super("Password not matches or user with this email already exist");
    }
}
