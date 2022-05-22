package com.pkb.data.api;

import java.util.List;

// create TodoServiceStub
// Test TodoBusinessImpl using TodoServiceStub
public interface TodoService {
	
	public List<String> retrieveTodos(String user);
	public void deleteTodo(String todo);
	

}
