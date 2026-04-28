package mx.edu.unpa.reporteanualbackend.configs;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import mx.edu.unpa.reporteanualbackend.entities.enums.Rol;
import mx.edu.unpa.reporteanualbackend.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataSeedConfig {

    // Spring buscará automáticamente el PasswordEncoder que está en tu SecurityConfig
    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            // Verificamos si ya existe el admin para no duplicarlo
            if (!usuarioRepository.existsByNumeroTrabajo("ADMIN001")) {
                Usuario admin = new Usuario();
                admin.setNumeroTrabajo("ADMIN001");
                admin.setNombre("Administrador");
                admin.setApellidos("Desarrollador");
                admin.setCorreo("adminDesarrollo@unpa.edu.mx");

                // Aquí usamos el encriptador que viene de SecurityConfig
                admin.setContrasenaHash(passwordEncoder.encode("admin1234"));

                // Asegúrate de usar el enum o string correcto según como lo tengas en tu entidad Usuario
                admin.setRol(Rol.ADMIN); // O Rol.ADMIN si usas un Enum

                usuarioRepository.save(admin);
                System.out.println("✅ Usuario ADMIN001 creado exitosamente en la base de datos.");
            }
        };
    }
}