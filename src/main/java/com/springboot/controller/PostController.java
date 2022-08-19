package com.springboot.controller;

import java.util.List;

import com.springboot.dto.EmployeeDto;
import com.springboot.model.Employee;
import com.springboot.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springboot.service.PostService;

import javax.xml.ws.Response;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/springboot")
public class PostController {
	@Autowired
	private PostService postService;

	public PostController() {
		super();

	}
	
	// build create post REST API
	@PostMapping()
	public ResponseEntity<Post> savePost(@RequestBody Post post){
		return new ResponseEntity<Post>(postService.savePost(post), HttpStatus.CREATED);
	}

	@PostMapping("/saveEmployee")
	public Employee saveEmployee(@RequestBody EmployeeDto newEmployee) {
		Employee e = new Employee();
		e.setFirstName(newEmployee.getFirstName());
		e.setLastName(newEmployee.getLastName());
		e.setEmail(newEmployee.getEmail());
		System.out.println("Incoming Data - "+ newEmployee.getFirstName()+" "+newEmployee.getLastName());
		return postService.saveEmployee(e);
	}
	
	// build get all post REST API
	@GetMapping
	public List<Post> getAllPost(){
		return postService.getAllPost();
	}
	
	// build get post by id REST API
	// http://localhost:8080/api/springboot/1
	@GetMapping("{id}")
	public ResponseEntity<Post> getPostById(@PathVariable("id") long id){
		return new ResponseEntity<Post>(postService.getPostById(id), HttpStatus.OK);
	}
	
	// build update post REST API
	// http://localhost:8080/api/springboot/1
	@PutMapping("{id}")
	public ResponseEntity<Post> updatePost(@PathVariable("id") long id
												  ,@RequestBody Post post){
		return new ResponseEntity<Post>(postService.updatePost(post, id), HttpStatus.OK);
	}
	
	// build delete post REST API
	// http://localhost:8080/api/springboot/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePost(@PathVariable("id") long id){
		
		// delete post from DB
		postService.deletePost(id);
		
		return new ResponseEntity<String>("Post deleted successfully!.", HttpStatus.OK);
	}
	
}
