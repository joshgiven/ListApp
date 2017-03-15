package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("search/radius")
	List<Trail> searchByLatLong(Double originLat, Double originLong, Integer radius) {
		originLat = 39.0;
		originLong = -105.0;
		radius = 10;
		
		return dao.searchByLatLong(originLat, originLong, radius);
		
	}

	
	
}
