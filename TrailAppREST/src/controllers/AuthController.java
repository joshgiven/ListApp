package controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@GetMapping("auth/ping")
	String ping() {
		return "pong";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, String> login(
			@RequestBody String userJsonString,
			HttpServletRequest req, HttpServletResponse res ) {
	
		return null;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Map<String, String> signup(
			@RequestBody String userJson,
			HttpServletRequest req, HttpServletResponse res) {

		return null;
	}
}
