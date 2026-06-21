package com.learning.librarysystem.Configuration;

import com.learning.librarysystem.auth.AuditImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig{
    private final ModelMapper modelMapper;

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditImpl(modelMapper);
    }
}
