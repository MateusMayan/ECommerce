package com.mayan.ecommerce.services;

import com.mayan.ecommerce.entities.User;
import com.mayan.ecommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long userId) {
        User me = userService.authenticated();
        if (!me.getId().equals(userId) && !me.hasRole("ROLE_ADMIN")) {
            throw new ForbiddenException("Acesso negado.");
        }
    }
}
