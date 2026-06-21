package com.learning.librarysystem.auth;

import com.learning.librarysystem.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

public class AuditImpl implements AuditorAware<String> {

    private final ModelMapper modelMapper;
    public AuditImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication==null || authentication.getPrincipal()==null){
            return Optional.empty();
        }
        System.out.println("authentication:"+authentication.getPrincipal());


        User user = (User) authentication.getPrincipal();
        return Optional.of(user.getId().toString());
    }
}
