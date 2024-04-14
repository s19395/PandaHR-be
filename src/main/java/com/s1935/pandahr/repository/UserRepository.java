package com.s1935.pandahr.repository;

import com.s1935.pandahr.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByLogin(String login);
}
