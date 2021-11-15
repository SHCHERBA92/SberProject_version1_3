
/**
 * Интерфейс по взаимодействию с БД MessageFun
 * Унаследован от стандартного интерфейcа Spring JPA JpaRepository
 * */
package com.example.firstproject.repository;

import com.example.firstproject.model.MessageFun;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageFunRepository extends JpaRepository<MessageFun, Long> {

}
