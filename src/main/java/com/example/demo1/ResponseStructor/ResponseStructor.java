package com.example.demo1.ResponseStructor;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructor<T> {
	private int StatusCode;
	private String message;
	private T Data;
	private LocalDateTime time;
}
