package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import data.SearchDAO;

@RestController
public class SearchController {
	
	@Autowired
	SearchDAO dao;
	
	@GetMapping("search/ping")
	String ping() {
		return dao.ping();
	}

	
	
}
