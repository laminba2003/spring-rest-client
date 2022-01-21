package com.spring.training;

import com.spring.training.model.Country;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            List<Country> countries =
                    restTemplate.exchange(
                            "http://localhost:9090/countries",
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<List<Country>>() {
                            }
                    ).getBody();
            countries.forEach(country -> {
                System.out.println("name " + country.getName());
                System.out.println("capital " + country.getCapital());
                System.out.println("population " + country.getPopulation());
            });
        };
    }
}
