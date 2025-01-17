package com.example.demo1.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo1.Entity.Todo;
import com.example.demo1.Repositry.TodoRepositry;
import com.example.demo1.ResponseStructor.ResponseStructor;

@Service
public class TodoService {

	@Autowired
	private TodoRepositry repositry;

	public ResponseStructor<Todo> save(Todo entity) {
		 entity.setStatus("Pending");
		Todo save = repositry.save(entity);
		return new ResponseStructor<Todo>(201, "Created Sucessfully", save, LocalDateTime.now());
	}

	public ResponseStructor<String> delete(int id) {
		Optional<Todo> byId = repositry.findById(id);
		if (byId.isPresent()) {
			repositry.deleteById(id);
			return new ResponseStructor<String>(200, "Deleted Sucessfully", null, LocalDateTime.now());
		} else {
			return new ResponseStructor<>(400, "Todo Not Found", null, LocalDateTime.now());
		}
	}

	public ResponseStructor<List<Todo>> get() {
		List<Todo> all = repositry.findAll();
		return new ResponseStructor<>(200, "Data Fetched Sucessfully", all, LocalDateTime.now());
	}

	public ResponseStructor<Todo> update(int id, Todo entity) {
		Optional<Todo> byId = repositry.findById(id);
		if (byId.isPresent()) {
			Todo existingTodo = byId.get();
			existingTodo.setTask(entity.getTask());
			existingTodo.setStatus(entity.getStatus());
			existingTodo.setCreatedAt(entity.getCreatedAt());

			Todo updatedTodo = repositry.save(existingTodo);
			return new ResponseStructor<>(200, "Todo updated successfully", updatedTodo, LocalDateTime.now());
		} else {
			return new ResponseStructor<>(400, "Todo Not Found", null, LocalDateTime.now());
		}
	}
}
