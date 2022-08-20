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

import br.ufc.mandacaru5.model.Owner;
import br.ufc.mandacaru5.service.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping
	public ResponseEntity<List<Owner>> findAll() {
		return new ResponseEntity<List<Owner>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping(path = "{id}")
	public ResponseEntity<Owner> find(@PathVariable("id") int id) {
		Owner owner = service.find(id);

		if (owner != null) {
			return new ResponseEntity<Owner>(owner, HttpStatus.OK);
		} else {
			return new ResponseEntity<Owner>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/search")
	public ResponseEntity<Owner> findByName(@RequestParam("name") String name) {
		Owner owner = service.findByName(name);

		if (owner != null) {
			return new ResponseEntity<Owner>(owner, HttpStatus.OK);
		} else {
			return new ResponseEntity<Owner>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public void save(@RequestBody Owner owner) {
		service.save(0, owner);
	}

	@PutMapping(path = "{id}")
	public void update(@PathVariable("id") int id, @RequestBody Owner owner) {
		service.save(id, owner);
	}

	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable("id") int id) {
		service.delete(id);
	}
}
