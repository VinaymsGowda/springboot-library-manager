package com.learning.librarysystem.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


public class AuditImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Vinay");
    }
}
