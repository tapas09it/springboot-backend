package com.springboot.service;

import java.util.List;

import com.springboot.model.Employee;
import com.springboot.model.Post;

public interface PostService {
	Post savePost(Post post);
	List<Post> getAllPost();
	Post getPostById(long id);
	Post updatePost(Post post, long id);
	void deletePost(long id);

	Employee saveEmployee(Employee e);
}
