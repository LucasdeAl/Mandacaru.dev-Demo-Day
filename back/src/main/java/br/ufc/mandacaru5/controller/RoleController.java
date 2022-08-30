package br.ufc.mandacaru5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru5.model.Role;
import br.ufc.mandacaru5.service.RoleService;
//import io.swagger.v3.oas.annotations.tags.Tag;

//@Tag(name = "Role")
@RestController
@RequestMapping(path = "/api/roles")
public class RoleController {

	@Autowired
	RoleService service;

	@PostMapping
	public void save(@RequestBody Role role) {
		service.save(role);
	}
	
	@PostMapping(path = "{id}/user/{userId}")
	public void addRole(@PathVariable("id") int id, @PathVariable("userId") int userId) {
		service.addRole(id, userId);
	}

	@GetMapping
	public ResponseEntity<List<Role>> findAll() {
		return new ResponseEntity<List<Role>>(service.findAll(), HttpStatus.OK);
	}
}