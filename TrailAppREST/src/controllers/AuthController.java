package controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.AuthDAO;
import security.JsonWebTokenGenerator;

@RestController
public class AuthController {
	
	 @Autowired
	 JsonWebTokenGenerator jwtGen;

	@Autowired
	AuthDAO authDAO;
	
	@GetMapping("auth/ping")
	String ping() {
		return "pong";
	}
	

	  @PostMapping(value = "/login")
	  public Map<String,String> login(HttpServletRequest req, HttpServletResponse res, @RequestBody String userJsonString) {
	    System.out.println("in login method");
		  System.out.println(userJsonString);
		 ObjectMapper mapper = new ObjectMapper();
	    User user = null;
	    // Parse User from JSON
	    try {
	      user = mapper.readValue(userJsonString, User.class);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    // Find managed User, return it if password is correct
	    try {
	      user = authDAO.authenticateUser(user);
	    } catch (Exception e) {
	      // User not authenticated
	      e.printStackTrace();
	      res.setStatus(401);
	      return null;
	    }
	    
	    String jws = jwtGen.generateUserJwt(user);
	    Map<String, String> responseJson = new HashMap<>();
	    responseJson.put("jwt", jws);
	    System.out.println(jws);
	    return responseJson;
	  }
	  
	  @PostMapping(value = "/signup")
	  public Map<String,String> register(HttpServletRequest req, HttpServletResponse res, @RequestBody String userJsonString) {
		  System.out.println("in signup method");
		  System.out.println(userJsonString);
		  ObjectMapper mapper = new ObjectMapper();
		  User user = null;
		  // Parse User from JSON
		  try {
			  user = mapper.readValue(userJsonString, User.class);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  // Find managed User, return it if password is correct
		  try {
			  user = authDAO.create(user);
		  } catch (Exception e) {
			  // User not authenticated
			  e.printStackTrace();
			  res.setStatus(401);
			  return null;
		  }
		  
		  String jws = jwtGen.generateUserJwt(user);
		  Map<String, String> responseJson = new HashMap<>();
		  responseJson.put("jwt", jws);
		  return responseJson;
	  }
	
//	@PostMapping(path="/register")
//		public User register(@RequestBody String userJSON){
//		ObjectMapper mapper = new ObjectMapper();
//		User newUser = null;
//		try {
//			newUser = mapper.readValue(userJSON, User.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return	authDAO.register(newUser);
//	}

}

