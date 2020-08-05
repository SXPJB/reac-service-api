package com.fsociety.api.service;

import java.util.List;

import com.fsociety.api.entity.Users;

public interface UserService {
	
	Users login(Users user);
	
	void insertUser(Users user);
	
	List<Users>getAllUsers();
	
	Users getByEmail(String email);
	
	List<Users> findAllOderByNameDesc(String name);
}
