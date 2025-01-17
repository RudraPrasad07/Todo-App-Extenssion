package com.example.demo1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.Entity.Todo;
import com.example.demo1.ResponseStructor.ResponseStructor;
import com.example.demo1.Service.TodoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "chrome-extension://hpfecjiplnfckhnklajlfakhjcnngofb")
@RestController
@RequestMapping("/app/todo")
public class TodoCalss {
	@Autowired
	private TodoService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructor<Todo>> Save(@RequestBody Todo entity) {
		ResponseStructor<Todo> Save=service.save(entity);
		return new ResponseEntity<>(Save, HttpStatus.OK);
	}
	
	@DeleteMapping("/Delete/{id}")
	public ResponseEntity<ResponseStructor<String>> delete(@PathVariable int id){
		ResponseStructor<String> delete = service.delete(id);
		return new ResponseEntity<>(delete, HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public  ResponseEntity<ResponseStructor<List<Todo>>> get() {
		ResponseStructor<List<Todo>> responseStructor = service.get();
		return new ResponseEntity<>(responseStructor, HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<ResponseStructor<Todo>> update(@PathVariable int id, @RequestBody Todo entity) {	
		ResponseStructor<Todo> update = service.update(id, entity);
		return new ResponseEntity<ResponseStructor<Todo>>(update, HttpStatus.OK);
	}
}
