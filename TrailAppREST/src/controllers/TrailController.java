package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entities.Trail;

@RestController
public class TrailController {

	@GetMapping("trails/ping")
	String ping() {
		return "pong";
	}

	@GetMapping("trails/search")
	List<Trail> searchByState(
			@RequestParam String state, 
			@RequestParam String activity, 
			@RequestParam String status, 
			HttpServletRequest req,
			HttpServletResponse res ) {
		
		return null;
	}
	
	@PutMapping("trails/search")
	List<Trail> advancedSearch(
			@RequestBody String fillupJSON, 
			HttpServletResponse res) {
		
		return null;
	}
	
	@GetMapping("trails")
	List<Trail> index() {
		return null;
	}
	
	@GetMapping("trails/{id}")
	Trail show(@PathVariable int id) {
		return null;
	}
	
	@PostMapping("trails")
	Trail create(@RequestBody String fillupJSON, HttpServletResponse res) {
		return null;
	}
	
	@PutMapping("trails/{id}")
	Trail update(@PathVariable int id, @RequestBody String fillupJSON, HttpServletResponse res) {
		return null;
	}
	
	@DeleteMapping("trails/{id}")
	Trail destroy(@PathVariable int id, HttpServletResponse res) {
		return null;
	}
}
