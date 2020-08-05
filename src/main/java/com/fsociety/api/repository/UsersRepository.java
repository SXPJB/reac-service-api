package com.fsociety.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fsociety.api.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	@Query(value = "SELECT u FROM Users u WHERE u.email= :email")
	Users findByEmailAddress(@Param("email") String email);
	
	List<Users> findAllOderByNameDesc(@Param("name") String name);

}
