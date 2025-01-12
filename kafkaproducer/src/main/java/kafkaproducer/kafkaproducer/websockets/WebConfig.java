package kafkaproducer.kafkaproducer.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow CORS for all endpoints
                .allowedOrigins("http://localhost:63342")  // Frontend URL (adjust accordingly)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow credentials (cookies, HTTP authentication)
    }
}
