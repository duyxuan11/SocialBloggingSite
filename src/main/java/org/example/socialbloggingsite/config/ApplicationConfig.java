package org.example.socialbloggingsite.config;

import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.example.socialbloggingsite.users.models.UserEntity;
import org.example.socialbloggingsite.utils.constants.Role;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Slf4j
public class ApplicationConfig {
    private final UserRepository userRepository;
    private final String ADMIN_USERNAME = "admin";
    private final String ADMIN_PASSWORD = "admin123";

    public ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("User not found"));
    }

    //encode password
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        log.info("1");
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        log.info("Starting application runner ...");
        return args -> {
            if (!userRepository.existsByUsername(ADMIN_USERNAME)){
                UserEntity user = UserEntity.builder()
                                .username(ADMIN_USERNAME)
                                .password(passwordEncoder().encode(ADMIN_PASSWORD))
                                .email("admin@gmail.com")
                                .role(Role.ADMIN)
                                .createdBy(ADMIN_USERNAME)
                                .build();

                userRepository.save(user);
            }
        };
    }


}
