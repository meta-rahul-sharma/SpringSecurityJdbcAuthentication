package com.security.springsecuritywithdatabaseconnectivity.entities;

import com.security.springsecuritywithdatabaseconnectivity.enums.Role;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String username;
		private String password;
		private Boolean enabled;

		@Enumerated(value = EnumType.STRING)
		private Role role;

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

		public String getPassword() {
				return password;
		}

		public void setPassword(String password) {
				this.password = password;
		}

		public Boolean getEnabled() {
				return enabled;
		}

		public void setEnabled(Boolean enabled) {
				this.enabled = enabled;
		}

		public Role getRole() {
				return role;
		}

		public void setRole(Role role) {
				this.role = role;
		}

		@Override
		public String toString() {
				return "User{" +
								"id=" + id +
								", username='" + username + '\'' +
								", password='" + password + '\'' +
								", enabled=" + enabled +
								", role=" + role +
								'}';
		}
}
