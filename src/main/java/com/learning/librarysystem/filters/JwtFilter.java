package com.learning.librarysystem.filters;

import com.learning.librarysystem.entity.User;
import com.learning.librarysystem.repository.UserRepository;
import com.learning.librarysystem.service.JwtService;
import com.learning.librarysystem.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {


        System.out.println("Arrived at JWT filter");
        final String requestTokenHeader=request.getHeader("Authorization");
        System.out.println(requestTokenHeader);

        if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

            String token=requestTokenHeader.split("Bearer ")[1];

            UUID userId=jwtService.getUserIdFromToken(token);
            logger.info("Extracted userId from token: " + userId);
        System.out.println();

            if(userId==null || SecurityContextHolder.getContext().getAuthentication()!=null){
                filterChain.doFilter(request,response);
                return;
            }



            User user=userService.getUserById(userId);
        logger.info("Extracted user data from service: " + user);

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("auth token: " + authenticationToken.toString());
        System.out.println("name "+authenticationToken.getName());
        filterChain.doFilter(request,response);
        return;

        }catch (Exception ex){
            logger.error("Error in JWT filter: " + ex.getMessage(), ex);
            exceptionResolver.resolveException(request, response, null, ex);
        }


    }
}
