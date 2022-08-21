package br.ufc.mandacaru5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru5.model.Post;
import br.ufc.mandacaru5.service.PostService;
import br.ufc.mandacaru5.util.Properties;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping(path = "/api/posts")
public class PostController {

	@Autowired
	PostService service;
	
	@Autowired
	Properties properties;

	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		System.out.println(properties.getPost());
		
		return new ResponseEntity<List<Post>>(service.findAllPostsByRequest(), HttpStatus.OK);
	}
	
	@GetMapping(path = "{id}")
	public ResponseEntity<Post> find(@PathVariable("id") int id) {
		return new ResponseEntity<Post>(service.findPostByRequest(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Post> save(@RequestBody Post post) {
		return new ResponseEntity<Post>(service.saveByRequest(post), HttpStatus.OK);
	}

	@PutMapping(path = "{id}")
	public ResponseEntity<Post> update(@PathVariable("id") int id, @RequestBody Post post) {
		return new ResponseEntity<Post>(service.updateByRequest(id, post), HttpStatus.OK);
	}

	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable("id") int id) {
		service.deleteByRequest(id);
	}
}