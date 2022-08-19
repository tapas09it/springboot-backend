package com.springboot.service.impl;

import java.util.List;

import com.springboot.model.Employee;
import com.springboot.model.Post;
import com.springboot.repository.EmployeeRepository;
import com.springboot.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public PostServiceImpl() {
		super();
	}

	@Override
	public Post savePost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public List<Post> getAllPost() {
		return postRepository.findAll();
	}

	@Override
	public Post getPostById(long id) {
//		Optional<Post> post = postRepository.findById(id);
//		if(post.isPresent()) {
//			return post.get();
//		}else {
//			throw new ResourceNotFoundException("Post", "Id", id);
//		}
		return postRepository.findById(id).orElseThrow(() ->
						new ResourceNotFoundException("Post", "Id", id));
		
	}

	@Override
	public Post updatePost(Post post, long id) {
		
		// we need to check whether employee with given id is exist in DB or not
		Post existingPost = postRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Post", "Id", id));

		existingPost.setDatePosted(post.getDatePosted());
		existingPost.setTitle(post.getTitle());
		existingPost.setCategory(post.getCategory());
		// save existing Post to DB
		postRepository.save(existingPost);
		return existingPost;
	}

	@Override
	public void deletePost(long id) {
		
		// check whether a employee exist in a DB or not
		postRepository.findById(id).orElseThrow(() ->
								new ResourceNotFoundException("Post", "Id", id));
		postRepository.deleteById(id);
	}

	@Override
	public Employee saveEmployee(Employee e) {return employeeRepository.save(e);}

}
