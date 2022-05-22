package com.pkb.data.api;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {
	// Dynamic Condition
	// Service Definition

	public List<String> retrieveTodos(String user) {
		return Arrays.asList("Learn Spring", "Learn Spring MVC", "Learn to Dance");
	}

	public void deleteTodo(String todo) {
		// TODO Auto-generated method stub
		
	}

}
