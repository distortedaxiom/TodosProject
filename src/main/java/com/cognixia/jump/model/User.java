package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Table(name = "users")
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column( unique = true )
	private String username;
	
	@NotBlank
	@Column( nullable = false )
	private String password;
	
	@OneToMany( mappedBy = "user", cascade = CascadeType.ALL )
	@JsonManagedReference
	private List<Todos> todos;
	
	public User() {
		this(-1, "N/A", "N/A",  new ArrayList<Todos>());
	}

	public User(Integer id, @NotBlank String username, @NotBlank String password, List<Todos> todos) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.todos = todos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Todos> getTodos() {
		return todos;
	}	

	public void setTodos(List<Todos> todos) {
		this.todos = todos;
	}
	
	public void newTodos() {
		
		for(int i = 0; i < todos.size(); i++) {
			todos.get(i).setId(-1);
		}
		
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", todos=" + todos + "]";
	}
	
	
}
