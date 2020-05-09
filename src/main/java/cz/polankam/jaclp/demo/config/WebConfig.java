package cz.polankam.jaclp.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Do not enable the <code>@EnableWebMvc</code> annotation, Spring Boot will
 * take care of all the things with its own auto-configuration feature and there
 * is no need to add this annotation explicitly.
 * <p>
 * Created by Martin Polanka on 23.4.2018.
 */
@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
}
