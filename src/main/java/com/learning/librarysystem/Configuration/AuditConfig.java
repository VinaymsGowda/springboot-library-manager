package com.learning.librarysystem.Configuration;

import com.learning.librarysystem.auth.AuditImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig{

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditImpl();
    }
}
