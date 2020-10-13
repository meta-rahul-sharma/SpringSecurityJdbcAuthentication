package com.security.springsecuritywithdatabaseconnectivity.services;

import com.security.springsecuritywithdatabaseconnectivity.dtos.UserDto;
import com.security.springsecuritywithdatabaseconnectivity.dtos.UserInfoDto;
import com.security.springsecuritywithdatabaseconnectivity.entities.User;
import com.security.springsecuritywithdatabaseconnectivity.entities.UserInfo;
import com.security.springsecuritywithdatabaseconnectivity.enums.Role;
import com.security.springsecuritywithdatabaseconnectivity.repositories.UserInfoRepository;
import com.security.springsecuritywithdatabaseconnectivity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author rahul sharma
 */
@Service
public class UserInfoService {

		@Autowired
		private UserRepository userRepository;

		@Autowired
		private UserInfoRepository userInfoRepository;

		public UserInfoDto getUserInfo(String userName) {
				User user = userRepository.findByUsername(userName);
				UserInfo info = userInfoRepository.findByUserId(user.getId());

				UserInfoDto dto = userInfoToUserInfoDto(info);
				return dto;
		}

		public UserInfoDto getUserInfo(Long id) throws Exception {
				UserDetails loogedInUserDetails = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				User loggedInUser = userRepository.findByUsername(loogedInUserDetails.getUsername());

				UserInfo info = userInfoRepository.findById(id).get();
				User user = userRepository.findById(info.getUserId()).get();

				if (Role.ADMIN == loggedInUser.getRole() ||
								(Role.USER == loggedInUser.getRole() && Role.USER == user.getRole())
				) {
						UserInfoDto dto = userInfoToUserInfoDto(info);
						dto.setRole(user.getRole().toString());
						return dto;
				} else {
						throw new Exception("User Role Can't Read / Update Value of Admin Role");
				}
		}

		public List<UserInfoDto> getAllUsers() {
				List<UserInfo> infos = userInfoRepository.findAll();
				return userInfosToUserInfoDtos(infos);
		}

		public List<UserInfoDto> getAllUsersByRole(String role) {
				List<UserInfo> infos = userInfoRepository.findAll();
				List<UserInfo> filteredData = new ArrayList<>();
				if (Role.USER.toString().equalsIgnoreCase(role)) {
						for(UserInfo info: infos) {
								String userRole = userRepository.findById(info.getUserId()).get().getRole().toString();
								if(Role.USER.toString().equalsIgnoreCase(userRole)) {
										filteredData.add(info);
								}
						}
						infos.stream().filter(info -> Role.USER.toString().equalsIgnoreCase(userRepository.findById(info.getUserId()).get().getRole().toString()));
						return userInfosToUserInfoDtos(filteredData);
				}
				return userInfosToUserInfoDtos(infos);
		}

		public UserInfoDto createOrUpdate(UserInfoDto dto, String role) throws Exception {

				if(Role.USER.toString().equalsIgnoreCase(role) && Role.ADMIN.toString().equalsIgnoreCase(dto.getRole())) {
						throw new Exception("User Role Can't Read / Update Value of Admin Role");
				}

				UserInfo info = new UserInfo();
				info.setId(dto.getId());
				info.setUserId(dto.getUserId());
				info.setEmail(dto.getEmail());
				info.setFirstName(dto.getFirstName());
				info.setMiddleName(dto.getMiddleName());
				info.setLastName(dto.getLastName());
				info.setMobileNumber(dto.getMobileNumber());

				userInfoRepository.save(info);

				dto.setId(info.getId());
				return dto;
		}

		private UserInfoDto userInfoToUserInfoDto(UserInfo info) {
				UserInfoDto dto = new UserInfoDto();
				dto.setId(info.getId());
				dto.setUserId(info.getUserId());
				dto.setEmail(info.getEmail());
				dto.setFirstName(info.getFirstName());
				dto.setMiddleName(info.getMiddleName());
				dto.setLastName(info.getLastName());
				dto.setMobileNumber(info.getMobileNumber());
				return dto;
		}

		private List<UserInfoDto> userInfosToUserInfoDtos(List<UserInfo> infos) {
				List<UserInfoDto> dtos = new ArrayList<>();
				infos.forEach(info -> {
						dtos.add(userInfoToUserInfoDto(info));
				});
				return dtos;
		}
}
