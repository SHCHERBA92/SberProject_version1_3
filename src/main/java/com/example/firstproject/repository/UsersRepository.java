/**
 * Интерфейс по взаимодействию с БД User
 * Унаследован от стандартного интерфейча Spring JPA JpaRepository
 * */

package com.example.firstproject.repository;

import com.example.firstproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
}
