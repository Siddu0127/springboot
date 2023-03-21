package com.example.repository;

import com.example.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Login findByUserNameAndPassword(String userName, String password);

    Login findByUserName(String userName);
}
