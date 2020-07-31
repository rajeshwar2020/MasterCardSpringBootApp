package com.mastercard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mastercard.service.MasterCardService;

@Controller
public class CityController {

	@Autowired
	private MasterCardService service;
	

	@GetMapping("/connected")
	public @ResponseBody String connection(@RequestParam String origin,@RequestParam String destination) {
		
		/* Calling the getConnection() method from MasterCardService class. */
		return service.getConnection(origin, destination);
		
	}
}
