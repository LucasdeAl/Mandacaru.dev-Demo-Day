package br.ufc.mandacaru5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru5.model.Property;
import br.ufc.mandacaru5.model.User;
import br.ufc.mandacaru5.service.PropertyService;

@RestController
@RequestMapping(path = "/api")
public class PropertyController {

	@Autowired
	PropertyService service;

	@GetMapping("/user/{id}/properties")
	public ResponseEntity<List<Property>> findAll(@PathVariable(value = "id") int id) {
		return new ResponseEntity<List<Property>>(service.findAll(id), HttpStatus.OK);
	}

	@GetMapping("/properties/{id}")
	public ResponseEntity<Property> findById(@PathVariable("id") int id) {
		Property property = service.find(id);

		if (property != null) {
			return new ResponseEntity<Property>(property, HttpStatus.OK);
		} else {
			return new ResponseEntity<Property>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/properties/search")
	public ResponseEntity<Property> findByTitle(@RequestParam("title") String title) {
		
		Property property = service.findByTitle(title);

		if (property != null) {
			return new ResponseEntity<Property>(property, HttpStatus.OK);
		} else {
			return new ResponseEntity<Property>(HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping("/properties")
	public ResponseEntity<List<Property>> findAllProperties() {
		return new ResponseEntity<List<Property>>(service.findAllProperties(), HttpStatus.OK);
	}
	
	@PostMapping("/user/{id}/properties")
	public void save(@PathVariable("id") int user_id, @RequestBody Property property) {
		service.save(user_id, property);
	}

	@PutMapping("/properties/{id}")
	public void update(@PathVariable("id") int id, @RequestBody Property property) {
		service.update(id, property);
	}

	@DeleteMapping("/properties/{id}")
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}
}
