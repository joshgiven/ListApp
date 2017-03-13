package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entities.TrailReport;

@RestController
public class ReportController {

	@GetMapping("reports/ping")
	String ping() {
		return "pong";
	}

	@GetMapping("trails/{tid}/reports")
	List<TrailReport> index(@PathVariable int tid) {
		return null;
	}
	
	@GetMapping("trails/{tid}/reports/{rid}")
	TrailReport show(@PathVariable int tid, @PathVariable int rid) {
		return null;
	}
	
	@PostMapping("trails/{tid}/reports")
	TrailReport create(
			@PathVariable int tid,
			@RequestBody String fillupJSON, 
			HttpServletResponse res) {
		return null;
	}
	
	@PutMapping("trails/{tid}/reports/{rid}")
	TrailReport update(
			@PathVariable int tid, 
			@PathVariable int rid, 
			@RequestBody String fillupJSON, 
			HttpServletResponse res) {
		
		return null;
	}
	
	@DeleteMapping("trails/{tid}/reports/{rid}")
	TrailReport destroy(
			@PathVariable int tid, 
			@PathVariable int rid, 
			HttpServletResponse res ) {
		
		return null;
	}
}
