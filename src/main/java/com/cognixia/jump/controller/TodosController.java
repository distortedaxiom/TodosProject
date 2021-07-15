package com.cognixia.jump.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Todos;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.TodosRepository;

@RequestMapping("/api")
@RestController
public class TodosController {
	
	@Autowired
	TodosRepository repo;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Todos>> getTodos() {
		
		return ResponseEntity.status(200)
				 .body(repo.findAll());
	}	
	
	@GetMapping("/todos/{todo_id}")
	public ResponseEntity<Todos> getTodosById(@Valid @PathVariable("todo_id") int todo_id) {
		
		Optional<Todos> todoOpt = repo.findById(todo_id);
	
		
		if (todoOpt.isPresent()) {
			return ResponseEntity.status(200)
					 .body(todoOpt.get());
		}
		
		else {
			return ResponseEntity.status(400)
					 .body(todoOpt.get());
		}
	}	
	
	
	@DeleteMapping("/todos/{todo_id}")
	public ResponseEntity<Optional<Todos>> deleteTodoById(@Valid @PathVariable("todo_id") int todo_id) {
		
	    Optional<Todos> todo = repo.findById(todo_id);
	    
	    repo.deleteById(todo_id);
	    
	    if (todo.isPresent()) {
	    	return ResponseEntity.status(200)
					 .body(todo);
	    }

    	return ResponseEntity.status(400)
				 .body(todo);

	}

}