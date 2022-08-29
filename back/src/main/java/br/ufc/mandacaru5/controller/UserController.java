package br.ufc.mandacaru5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru5.model.Person;
import br.ufc.mandacaru5.service.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Person>> findAll() {
		return new ResponseEntity<List<Person>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Person> find(@PathVariable("id") int id) {
		Person user = service.find(id);

		if (user != null) {
			return new ResponseEntity<Person>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/search")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Person> findByName(@RequestParam("name") String name) {
		Person user = service.findByName(name);

		if (user != null) {
			return new ResponseEntity<Person>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public void save(@RequestBody Person user) {
		service.save(0, user);
	}

	@PutMapping(path = "{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void update(@PathVariable("id") int id, @RequestBody Person user) {
		service.save(id, user);
	}

	@DeleteMapping(path = "{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}
}
