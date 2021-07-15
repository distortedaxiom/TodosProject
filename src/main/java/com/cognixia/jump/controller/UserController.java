package com.cognixia.jump.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.EntityAlreadyExistException;
import com.cognixia.jump.exception.InvalidInputException;
import com.cognixia.jump.model.Todos;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.TodosRepository;
import com.cognixia.jump.repository.UserRepository;

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	TodosRepository todoRepo;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUser() {
		
		return ResponseEntity.status(200)
		 .body(userRepo.findAll());
	}	
	
	@GetMapping("/users/{user_id}/todos")
	public ResponseEntity<User> getUsersById(@Valid @PathVariable("user_id") int user_id) throws InvalidInputException {
		
		Optional<User> userOpt = userRepo.findById(user_id);
		
		if (userOpt.isPresent()) {
			return ResponseEntity.status(200)
					 .body(userOpt.get());
		}
		
		else {
			throw new InvalidInputException(user_id);
		}
	}	
	
	@PostMapping("/users")
	public ResponseEntity<Optional<User>> addUser(@Valid @RequestBody User user) throws EntityAlreadyExistException {
		
		if (userRepo.findByUsername(user.getUsername()) != null) {
			throw new EntityAlreadyExistException(user.getUsername());
		}
		
		user.setId(-1);
		
		user.newTodos();
		
		User newUser = userRepo.save(user);
		
		return ResponseEntity.status(201)
							 .body(userRepo.findByUsername(user.getUsername()));
	}
	
	@PutMapping("/users/{user_id}/todos/{todo_id}")
	public ResponseEntity<Todos> updateTodosById(@Valid @PathVariable("todo_id") int todo_id, @PathVariable("user_id") int user_id, @RequestBody Todos todo) {
		
		Todos updatedTodo = new Todos();
		
		User userHolder = new User(user_id, "N/A", "N/A", new ArrayList<Todos>());
		
		updatedTodo.setUser(userHolder);
		
		updatedTodo.setId(todo_id);
		updatedTodo.setDescription(todo.getDescription());
		updatedTodo.setDue_date(todo.getDue_date());
		
		todoRepo.save(updatedTodo);
		
		return ResponseEntity.status(201)
				.body(updatedTodo);
		
	}
	
	@PostMapping("/users/{user_id}/todos")
	public ResponseEntity<Todos> addUserTodo(@Valid @RequestBody Todos todo, @PathVariable("user_id") int user_id) throws Exception {
		
		User userHolder = new User(user_id, "N/A", "N/A", new ArrayList<Todos>());
		
		todo.setUser(userHolder);
		
		todoRepo.save(todo);
		
		return ResponseEntity.status(201)
							 .body(todo);
	}
	
}