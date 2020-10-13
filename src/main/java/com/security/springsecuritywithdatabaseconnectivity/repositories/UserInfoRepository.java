package com.security.springsecuritywithdatabaseconnectivity.repositories;

import com.security.springsecuritywithdatabaseconnectivity.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
		UserInfo findByUserId(Long userId);
}
