package net.d4y2k.seabattle.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.d4y2k.seabattle.user.UserDetailsServiceImpl;
import net.d4y2k.seabattle.utils.JwtService;
import net.d4y2k.seabattle.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtTokenService;
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            sendUnauthorizedResponse(response, request, "authorization token not found");
            return;
        }

        String token = authorizationHeader.substring(7);
        Optional<String> optionalEmail = jwtTokenService.getEmailFromToken(token);

        if (optionalEmail.isEmpty()) {
            sendUnauthorizedResponse(response, request, "invalid authorization token");
            return;
        }

        String email = optionalEmail.get();

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetails applicationUser = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(applicationUser, null, applicationUser.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response);
    }

    public void sendUnauthorizedResponse(HttpServletResponse response, HttpServletRequest request, String message) throws IOException {

        Response responseObject = new Response(request.getRequestURI(), HttpStatus.FORBIDDEN, message);

        String jsonResponse = objectMapper.writeValueAsString(responseObject);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        response.getWriter().write(jsonResponse);
    }

}
