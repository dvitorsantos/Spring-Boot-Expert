package io.github.dvitorsantos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Development
public class developmentConfiguration {
    @Bean
    public CommandLineRunner execute() {
        return args -> {
            System.out.println("Server running in development mode.");
        };
    }
}
