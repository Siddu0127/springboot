package com.example.service;

import com.example.dto.RegistrationDto;
import com.example.entity.Login;
import com.example.repository.CustomerRepository;
import com.example.repository.LoginRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{


    private LoginRepository loginRepository;

    public LoginServiceImpl(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public Login save(RegistrationDto registrationDto) {
        Login login=new Login(registrationDto.getUserName(), registrationDto.getPassword());


        return loginRepository.save(login);
    }



    @Override
    public Login findByUserNameAndPassword(String userName, String password) {
        return  loginRepository.findByUserNameAndPassword(userName, password);
    }

    @Override
    public Login findByUserName(String userName) {
        return loginRepository.findByUserName(userName);
    }
}
