package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.SearchDAO;
import entities.Trail;

@RestController
public class SearchController {
	
	@Autowired
	SearchDAO dao;
	
	@GetMapping("search/ping")
	String ping() {
		return dao.ping();
	}
	
	@GetMapping("search/trails")
	List<Trail> searchBy(
			@RequestParam(required=false) String city, 
			@RequestParam(required=false) String state, 
			@RequestParam(required=false) Integer radius, 
			@RequestParam(required=false) Double lat, 
			@RequestParam(required=false) Double lng, 
			@RequestParam(required=false) Integer lengthMin, 
			@RequestParam(required=false) Integer lengthMax, 
			HttpServletRequest req, 
			HttpServletResponse resp) {
		
		//System.out.println("lat in controller" + lat);
		
		return dao.searchBy(city, state, radius, lengthMin, lengthMax, lat, lng);
	}

}
