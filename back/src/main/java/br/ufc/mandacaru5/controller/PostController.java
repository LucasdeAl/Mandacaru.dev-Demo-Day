package br.ufc.mandacaru5.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;

import br.ufc.mandacaru5.model.Post;
import br.ufc.mandacaru5.model.Property;
import br.ufc.mandacaru5.service.PostService;
import br.ufc.mandacaru5.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Post")
@RestController
@RequestMapping(path = "/api/posts")
public class PostController {

	@Autowired
	PostService service;

	@Autowired
	PropertyService pservice;

	@Operation(summary = "Get all posts")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "203", description = "Found the posts", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Post.class)) }),
			@ApiResponse(responseCode = "200", description = "Found the posts", content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Post.class))) }),
			@ApiResponse(responseCode = "404", description = "Not Found the post", content = @Content) })
	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		return new ResponseEntity<List<Post>>(service.findAllPostsByRequest(), HttpStatus.OK);
	}

	@PostMapping(path = "/create")
	public String createProcessAndFile(@RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getName());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println("Uploaded !!");
			} catch (Exception e) {
				System.out.println("Failed");
			}
		} else {
			System.out.println("Empty");
		}

		service.getToken();
		String idProcess = service.createProcess();
		service.createFile();
		service.uploadFile(file);
		service.patchProcess();//
		System.out.println(PropertyController.ID);
		Property prop = new Property();
		prop = pservice.find(PropertyController.ID);
		prop.setIdProcess(idProcess);
		pservice.update(PropertyController.ID, prop);
		return idProcess;
	}

	@GetMapping(path = "/status/{process}")
	public String getStatus(@PathVariable("process") String process) {
		service.getToken();
		return service.checkReady(process);
	}

	@Operation(summary = "Get one post by id")
	@GetMapping(path = "{id}")
	public ResponseEntity<Post> find(@Parameter(description = "id of post to be searched") @PathVariable("id") int id) {
		Post post = service.findPostByRequest(id);

		if (post != null) {
			return new ResponseEntity<Post>(post, HttpStatus.OK);
		} else {
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Save one post")
	@PostMapping
	public ResponseEntity<Post> save(@RequestBody Post post) {
		return new ResponseEntity<Post>(service.saveByRequest(post), HttpStatus.OK);
	}

	@Operation(summary = "Update one post")
	@PutMapping(path = "{id}")
	public ResponseEntity<Post> update(@PathVariable("id") int id, @RequestBody Post post) {
		return new ResponseEntity<Post>(service.updateByRequest(id, post), HttpStatus.OK);
	}

	@Operation(summary = "Delete one post by id")
	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable("id") int id) {
		service.deleteByRequest(id);
	}
}
