package ar.edu.unq.desapp.grupoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ar.edu.unq.desapp.grupoa.*", "ar.edu.unq.desapp.grupoa"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
