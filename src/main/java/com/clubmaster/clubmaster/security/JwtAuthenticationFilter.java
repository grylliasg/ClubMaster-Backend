package com.clubmaster.clubmaster.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // 1. Αν δεν υπάρχει Authorization header ή δεν ξεκινάει με "Bearer ", προχωράμε
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Παίρνουμε το token (αφαιρώντας τη λέξη "Bearer ")
        jwt = authHeader.substring(7);

        // 3. Εξάγουμε το username από το token
        username = jwtService.extractUsername(jwt);

        // 4. Αν βρέθηκε username και ο χρήστης δεν είναι ήδη συνδεδεμένος
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Φορτώνουμε τον χρήστη από τη βάση μέσω του CustomUserDetailsService
            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

            // 5. Ελέγχουμε αν το token είναι έγκυρο
            if (jwtService.validateToken(jwt, userDetails.getUsername())) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Ενημερώνουμε το Spring Security Context ότι ο χρήστης είναι authenticated
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 6. Συνεχίζουμε κανονικά το αίτημα
        filterChain.doFilter(request, response);
    }
}