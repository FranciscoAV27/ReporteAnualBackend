package mx.edu.unpa.reporteanualbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // ← agrega esto
public class ReporteAnualBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReporteAnualBackendApplication.class, args);
    }

}
