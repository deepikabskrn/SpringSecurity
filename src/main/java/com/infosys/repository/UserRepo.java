package com.infosys.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infosys.entity.User;

public interface UserRepo extends MongoRepository<User,String> {
	public User findByName(String name);
}
