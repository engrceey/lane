package com.zurum.lanefinance.service;

import com.zurum.lanefinance.dtos.request.UpdateUserRequestDto;
import com.zurum.lanefinance.dtos.request.UserRegistrationRequestDto;
import com.zurum.lanefinance.dtos.response.RegistrationResponseDto;
import com.zurum.lanefinance.entity.AppUser;

public interface UserService {
    RegistrationResponseDto registerUser(UserRegistrationRequestDto registrationRequestDto);
    AppUser getLoggedInUser();
    void updateUser(UpdateUserRequestDto updateUserDto, String id);
    String verifyAccount(String token);
}
