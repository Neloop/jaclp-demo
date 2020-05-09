package cz.polankam.jaclp.demo.config;

import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Martin Polanka on 09.05.2020.
 */
@Configuration
public class JacksonConfig {

    /**
     * All Jackson modules found in IOC container are automatically registered
     * in object mapper, so we do not have to do it manually.
     */
    @Bean
    public AfterburnerModule afterburnerModule() {
        return new AfterburnerModule();
    }
}
