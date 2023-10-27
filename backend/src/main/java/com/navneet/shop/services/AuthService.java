package com.navneet.shop.services;

import com.navneet.shop.dto.controller.auth.LoginDTO;
import com.navneet.shop.dto.controller.auth.RegisterDTO;
import com.navneet.shop.dto.service.LoginRolesResponseDTO;
import com.navneet.shop.entities.user.User;

public interface AuthService {
    LoginRolesResponseDTO login(LoginDTO loginDto);

    User register(RegisterDTO registerDto);
}
