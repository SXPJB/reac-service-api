package com.fsociety.api.serviceImp;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsociety.api.entity.Users;
import com.fsociety.api.repository.UsersRepository;
import com.fsociety.api.service.UserService;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	@Override
	public Users login(Users user) {
		
		List<Users> listUser=null;
		Users user1=null;
		try {
			listUser=usersRepository.findAll();
			listUser=listUser.stream().filter(u-> u.getEmail().equals(user.getEmail()) &&u.getPass().equals(user.getPass())).collect(Collectors.toList());
			if(!listUser.isEmpty()) {
				user1= listUser.get(0);
			}
			
		} catch (Exception e) {
			user1=null;
		}
		return user1;
	}


	@Override
	public void insertUser(Users user) {
		try {
			user.setId(0);
			usersRepository.save(user);
		}catch (Exception e) {
			
		}
		
	}


	@Override
	public List<Users> getAllUsers() {
		List<Users> listUsers=null;	
		try {
			listUsers=usersRepository.findAll();
			if(listUsers.isEmpty()) {
				throw new Exception("No hay datos");
			}
		} catch (Exception e) {
			listUsers=null;
		}
		return listUsers;
	}


	@Override
	public Users getByEmail(String email) {
		Users us = null;
		try {
			
			us=usersRepository.findByEmailAddress(email);
			
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}
		
		
		return us;
	}


	@Override
	public List<Users> findAllOderByNameDesc(String name) {
		List<Users> listOrder=null;
		try {
			listOrder=usersRepository.findAllOderByNameDesc(name+"%");
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		}
		
		return listOrder;
	}

}
