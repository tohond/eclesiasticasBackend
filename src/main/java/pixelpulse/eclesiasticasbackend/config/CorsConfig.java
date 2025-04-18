package pixelpulse.eclesiasticasbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Allow all origins or specify your frontend URL
        config.addAllowedOrigin("https://github.com/Diegoj17/FrontendEclesiasticas.git");
        config.addAllowedOrigin("https://parroquiasanluisgonzaga.vercel.app");
        
        config.addAllowedOrigin("http://localhost:3000");// Add your frontend URL here
        config.addAllowedOrigin("http://localhost:3001");
        // Allow all HTTP methods
        config.addAllowedMethod("*");
        
        // Allow all headers
        config.addAllowedHeader("*");
        
        // Allow credentials (cookies, authorization headers, etc.)
        config.setAllowCredentials(true);
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

