package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.TrailDAO;
import entities.Trail;

@RestController
public class TrailController {

	@Autowired
	TrailDAO trailDAO;
	
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
		return trailDAO.index();
	}
	
	@GetMapping("trails/{id}")
	Trail show(@PathVariable int id) {
		return trailDAO.show(id);
	}
	
	@PostMapping("trails")
	Trail create(@RequestBody String trailJSON, HttpServletResponse res) {
		
		Trail trail = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			trail = mapper.readValue(trailJSON, Trail.class);
		} catch (IOException e) {
			e.printStackTrace();

			res.setStatus(400);
			return null;
		}
		
		trail = trailDAO.create(trail);

		res.setStatus(201);
		return trail;
	}
	
	@PutMapping("trails/{id}")
	Trail update(@PathVariable int id, @RequestBody String fillupJSON, HttpServletResponse res) {
		Trail trail = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			trail = mapper.readValue(fillupJSON, Trail.class);
		} 
		catch (IOException e) {
			e.printStackTrace();

			res.setStatus(400);
			return null;
		}
		
		trail = trailDAO.update(id, trail);

		res.setStatus(202); // 202: Accepted

		return trail;
	}
	
	@DeleteMapping("trails/{id}")
	Trail destroy(@PathVariable int id, HttpServletResponse res) {
		return trailDAO.destroy(id);
	}
}
