package com.jony.model;


/**
 * 用户
 * @author jony.lai
 *
 */
public class UserEntity {
	
	  	private long id;
	    private String userName;
	    private String password;
	    private long phone;
	    private String chineseName;
	    private String email;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public long getPhone() {
			return phone;
		}
		public void setPhone(long phone) {
			this.phone = phone;
		}
		public String getChineseName() {
			return chineseName;
		}
		public void setChineseName(String chineseName) {
			this.chineseName = chineseName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}

}
