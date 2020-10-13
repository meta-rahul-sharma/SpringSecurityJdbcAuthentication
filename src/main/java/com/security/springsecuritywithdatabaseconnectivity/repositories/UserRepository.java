package com.security.springsecuritywithdatabaseconnectivity.repositories;

import com.security.springsecuritywithdatabaseconnectivity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
		User findByUsername(String username);
}
