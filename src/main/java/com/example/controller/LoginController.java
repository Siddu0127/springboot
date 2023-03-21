package com.example.controller;

import com.example.dto.RegistrationDto;
import com.example.entity.Login;
import com.example.service.JwtService;
import com.example.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
@RequestMapping("/registration")
public class LoginController {

    private LoginService loginService;


    @Autowired
    private JwtService jwtService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ModelAttribute("login")
    public RegistrationDto registrationDto() {
        return new RegistrationDto();
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("login") RegistrationDto registrationDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/registration";
        }
        loginService.save(registrationDto);
        return "redirect:/registration?success";
    }

    @GetMapping
    public String showRegister() {
        return "registration";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@RequestParam String userName, @RequestParam String password, HttpSession session, Model model) throws URISyntaxException, InvalidKeySpecException, NoSuchAlgorithmException {
        Login user = loginService.findByUserNameAndPassword(userName, password);
        String token=jwtService.generateToken(userName);
        System.out.println(token);
        model.addAttribute("Authorization",token);

        if (user == null) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        } else {
            session.setAttribute("login",user);
            model.addAttribute("token",token);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);

            // Return response with header


         /*   HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("http://localhost:8080/registration/login"))
                    .header("Authorization", token)
                    .build();*/


            return "redirect:/customer/showCustomer";
        }
    }


    /*


    @PostMapping("/authenticate")
    public  String authenticationAndGetToken(@RequestBody AuthRequest authRequest) {
        return jwtService.generateToken(authRequest.getUserName());
    }

     */








}
