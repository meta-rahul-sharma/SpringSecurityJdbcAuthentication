package com.security.springsecuritywithdatabaseconnectivity.dtos;

public class UserInfoDto {

		private Long id;
		private Long userId;
		private String role;
		private String firstName;
		private String middleName;
		private String lastName;
		private String email;
		private String mobileNumber;


		public Long getId() {
				return id;
		}

		public void setId(Long id) {
				this.id = id;
		}

		public Long getUserId() {
				return userId;
		}

		public void setUserId(Long userId) {
				this.userId = userId;
		}

		public String getFirstName() {
				return firstName;
		}

		public void setFirstName(String firstName) {
				this.firstName = firstName;
		}

		public String getMiddleName() {
				return middleName;
		}

		public void setMiddleName(String middleName) {
				this.middleName = middleName;
		}

		public String getLastName() {
				return lastName;
		}

		public void setLastName(String lastName) {
				this.lastName = lastName;
		}

		public String getEmail() {
				return email;
		}

		public void setEmail(String email) {
				this.email = email;
		}

		public String getMobileNumber() {
				return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
				this.mobileNumber = mobileNumber;
		}

		public String getRole() {
				return role;
		}

		public void setRole(String role) {
				this.role = role;
		}

		@Override
		public String toString() {
				return "UserInfoDto{" +
								"id=" + id +
								", userId=" + userId +
								", firstName='" + firstName + '\'' +
								", middleName='" + middleName + '\'' +
								", lastName='" + lastName + '\'' +
								", email='" + email + '\'' +
								", mobileNumber='" + mobileNumber + '\'' +
								'}';
		}
}
