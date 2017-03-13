package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import data.UserDAO;
import entities.User;

@RestController
public class HelloController {
	@Autowired
	UserDAO dao;
	
	@GetMapping("ping")
	String ping() {
		return "pong";
	}
	
	@GetMapping("users")
	List<User> index() {
		return dao.index();
	}
	
	
}
