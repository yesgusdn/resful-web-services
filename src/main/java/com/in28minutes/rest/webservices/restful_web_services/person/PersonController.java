package com.in28minutes.rest.webservices.restful_web_services.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	
//	@GetMapping("/person")
//	public PersonV1 getPerson() {
//		PersonV1 person = new PersonV1("h", "noh");
//		return person;
//	}
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=1")
	public PersonV1 getV1Person() {
		PersonV1 person = new PersonV1("h23123", "noh");
		return person;
	}
	
	@GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json")
	public PersonV2 getV2Person() {
		PersonV2 person = new PersonV2(new Name("h23123", "noh"));
		return person;
	}		
}
