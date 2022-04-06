package com.hotel.project.Model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "CustomerDetails")
public class Customer {

	/*
	 * @Id private int id;
	 */

	private String username;

	private String password;
	
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String email;
	
	@DBRef
    private Set<Role> roles;
	
	/*
	 * public Customer(String userName, String password, String email) {
	 * this.username = userName; this.password = password; this.email = email; }
	 */

	// User getters and setters fields

	/*
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 */

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
