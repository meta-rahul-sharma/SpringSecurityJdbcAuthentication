package com.security.springsecuritywithdatabaseconnectivity.dtos;

public class UserDto {

		private Long id;
		private String username;
		private Boolean enabled;
		private String role;

		public Long getId() {
				return id;
		}

		public void setId(Long id) {
				this.id = id;
		}

		public String getUsername() {
				return username;
		}

		public void setUsername(String username) {
				this.username = username;
		}

		public Boolean getEnabled() {
				return enabled;
		}

		public void setEnabled(Boolean enabled) {
				this.enabled = enabled;
		}

		public String getRole() {
				return role;
		}

		public void setRole(String role) {
				this.role = role;
		}

		@Override
		public String toString() {
				return "UserDto{" +
								"id=" + id +
								", username='" + username + '\'' +
								", enabled=" + enabled +
								", role='" + role + '\'' +
								'}';
		}
}
