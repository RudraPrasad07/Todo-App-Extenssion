package com.example.demo1.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.Entity.Todo;


public interface TodoRepositry extends JpaRepository<Todo, Integer> {

}
