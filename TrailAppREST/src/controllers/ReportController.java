package controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.ReportDAO;
import entities.Report;

@RestController
public class ReportController {

	@Autowired
	private ReportDAO reportDAO;
	
	@GetMapping("reports/ping")
	String ping() {
		return "pong";
	}

	@GetMapping("trails/{tid}/reports")
	List<Report> index(@PathVariable int tid) {
		return reportDAO.index(tid);
	}
	
	@GetMapping("trails/{tid}/reports/{rid}")
	Report show(@PathVariable int tid, @PathVariable int rid) {
		return reportDAO.show(rid);
	}
	
	@PostMapping("auth/trails/{tid}/reports")
	Report create(
			@PathVariable int tid,
			@RequestBody String fillupJSON, 
			HttpServletResponse res, HttpServletRequest req) {
		ObjectMapper mapper = new ObjectMapper();
		Report r = null;
		int uid = (int) req.getAttribute("userId");
		try {
		  r = mapper.readValue(fillupJSON, Report.class);
		} catch (Exception e) {
		  e.printStackTrace();
		  return null;
		}
		return reportDAO.create(r, tid, uid);
	}
	
	@PutMapping("auth/trails/{tid}/reports/{rid}")
	Report update(
			@PathVariable int tid, 
			@PathVariable int rid, 
			@RequestBody String fillupJSON, 
			HttpServletResponse res) {
		
		ObjectMapper mapper = new ObjectMapper();
		Report r = null;
		try {
		  r = mapper.readValue(fillupJSON, Report.class);
		} catch (Exception e) {
		  e.printStackTrace();
		  return null;
		}
		return reportDAO.update(rid,r);
	}
	
	@DeleteMapping("auth/trails/{tid}/reports/{rid}")
	Report destroy(
			@PathVariable int tid, 
			@PathVariable int rid, 
			HttpServletResponse res ) {
		
		return reportDAO.destroy(rid);
	}
	
	@GetMapping("trails/{tid}/latestreport")
	Report mostRecentReport(
			@PathVariable int tid, 
			@PathVariable int rid, 
			HttpServletResponse res ) {
		
		return reportDAO.mostRecentReport(tid);
	}
}
