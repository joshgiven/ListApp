package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import data.UserDAO;
import entities.User;

@RestController
public class UserController {
	
	@Autowired
	private UserDAO userDAO;

	@GetMapping("users/ping")
	String ping() {
		return userDAO.ping();
	}

	@GetMapping("users")
	List<User> index() {
		return userDAO.index();
	}
	
	@GetMapping("users/{id}")
	User show(@PathVariable int id) {
		return userDAO.show(id);
	}
	
	@PostMapping("users")
	User create(@RequestBody String fillupJSON, HttpServletResponse res) {
		return null;
	}
	
	@PutMapping("users/{id}")
	User update(@PathVariable int id, @RequestBody String fillupJSON, HttpServletResponse res) {
		return null;
	}
	
	@DeleteMapping("users/{id}")
	User destroy(@PathVariable int id, HttpServletResponse res) {
		return null;
	}
}
