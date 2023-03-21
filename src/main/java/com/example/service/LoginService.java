package com.example.service;

import com.example.dto.RegistrationDto;
import com.example.entity.Login;

public interface LoginService {
    Login save(RegistrationDto registrationDto);



    Login findByUserNameAndPassword(String userName, String password);

    Login findByUserName(String userName);

}
