package com.security.springsecuritywithdatabaseconnectivity.services;

import com.security.springsecuritywithdatabaseconnectivity.dtos.UserDto;
import com.security.springsecuritywithdatabaseconnectivity.dtos.UserInfoDto;
import com.security.springsecuritywithdatabaseconnectivity.entities.User;
import com.security.springsecuritywithdatabaseconnectivity.entities.UserInfo;
import com.security.springsecuritywithdatabaseconnectivity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author rahul sharma
 */
@Service
public class UserService {

		@Autowired
		private UserRepository userRepository;

		public UserDto getUser(String username) {
				User user = userRepository.findByUsername(username);
				return userToUserDto(user);
		}

		private UserDto userToUserDto(User user) {
				UserDto dto = new UserDto();
				dto.setId(user.getId());
				dto.setUsername(user.getUsername());
				dto.setEnabled(user.getEnabled());
				dto.setRole(user.getRole().toString());
				return dto;
		}
}
