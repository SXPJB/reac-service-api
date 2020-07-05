package com.fsociety.api.enpoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsociety.api.entity.Users;
import com.fsociety.api.service.UserService;

@RestController
@RequestMapping("/acceso")
@CrossOrigin("*")
public class LoginEnpoint {
	
	private static final Logger LOG = LoggerFactory.getLogger(LoginEnpoint.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/loging")
	public ResponseEntity<Users> logig(@RequestBody Users user){
		ResponseEntity<Users> res=null;
		Users us=null;
		try {
			LOG.debug(">>>>>User: {}",user);
			System.out.println(user);
			us=userService.login(user);
			if(us!=null) {
				res=new ResponseEntity<Users>(us,HttpStatus.OK);	
			}else {
				throw new Exception("No exsite");
			}
		} catch (Exception e) {
			res= new ResponseEntity<Users>(us,HttpStatus.NOT_FOUND);
		}
		LOG.debug("<<<<<<respuesta: {}",res);
		return res;
	}
	
	@PostMapping("/insertUser")
	public ResponseEntity<String> insertUser(@RequestBody Users user){
		ResponseEntity<String> res=null;
		try {
			LOG.debug(">>>>>insertUser() User: {}",user);
			userService.insertUser(user);
			res=new ResponseEntity<String>("Sucsses",HttpStatus.CREATED);	
		} catch (Exception e) {
			res=new ResponseEntity<String>("Errpr"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		return res;
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<Users>>getAllUsers(){
		ResponseEntity<List<Users>> res=null;
		List<Users> listUsers=null;
		try {
			listUsers=userService.getAllUsers();
			LOG.debug(">>>>>list: {}",listUsers);
			res=new ResponseEntity<List<Users>>(listUsers,HttpStatus.OK);
		} catch (Exception e) {
			res=new ResponseEntity<List<Users>>(listUsers,HttpStatus.NOT_FOUND);
		}
		LOG.debug("<<<<<<respuesta: {}",res);
		return res;
	}

}
