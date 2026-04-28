package mx.edu.unpa.reporteanualbackend.configs;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.repositories.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response); return;
        }
        String token = header.substring(7);
        if (!jwtConfig.esValido(token)) {
            chain.doFilter(request, response); return;
        }
        String numeroTrabajo = jwtConfig.extraerNumeroTrabajo(token);
        String rol = jwtConfig.extraerRol(token);

        usuarioRepository.findByNumeroTrabajo(numeroTrabajo).ifPresent(u -> {
            var auth = new UsernamePasswordAuthenticationToken(
                    u, null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + rol))
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        });
        chain.doFilter(request, response);
    }
}