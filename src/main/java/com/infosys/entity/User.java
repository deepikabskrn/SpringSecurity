package com.infosys.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class User {
	@Id ObjectId databaseId;
	public int id;
	public String name;
	public String password;
	private String[] Role;
	public String firstName;
	public String lastName;
	public ObjectId getDatabaseId() {
		return databaseId;
	}
	public void setDatabaseId(ObjectId databaseId) {
		this.databaseId = databaseId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getRole() {
		return Role;
	}
	public void setRole(String[] role) {
		Role = role;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
