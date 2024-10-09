package id.smartpesantren.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DotenvConfig {

    @Bean
    @Primary
    public Environment addDotenvToEnvironment(Environment environment) {
        Dotenv dotenv = Dotenv.load();

        // Buat map dari dotenv variables
        Map<String, Object> dotenvMap = new HashMap<>();
        dotenv.entries().forEach(entry -> dotenvMap.put(entry.getKey(), entry.getValue()));

        // Tambahkan dotenvMap ke Spring Environment
        MutablePropertySources propertySources = ((StandardEnvironment) environment).getPropertySources();
        propertySources.addLast(new MapPropertySource("dotenvProperties", dotenvMap));

        return environment;
    }
}