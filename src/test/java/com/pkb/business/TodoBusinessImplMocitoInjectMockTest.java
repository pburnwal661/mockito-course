package com.pkb.business;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import com.pkb.data.api.TodoService;

public class TodoBusinessImplMocitoInjectMockTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	TodoService todoServiceMock;
	//TodoService todoServiceMock = mock(TodoService.class);
	
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	//TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	//ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingMock() {

		List<String> todo = Arrays.asList("Learn Spring", "Learn Spring MVC", "Learn to Dance");

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todo);

		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals(2, filteredTodos.size());
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_withEmptyList() {

		List<String> todo = Arrays.asList();

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todo);

		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals(0, filteredTodos.size());
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_usingBDD() {

		// Given  - do setUp
		List<String> todo = Arrays.asList("Learn Spring", "Learn Spring MVC", "Learn to Dance");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todo);
		
		// When  -  actual method call - actual method invocation  
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		// Then - check all post codition are satisfy
		assertThat(filteredTodos.size(), is(2));
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD() {

		// Given  - do setUp
		List<String> todo = Arrays.asList("Learn Spring", "Learn Spring MVC", "Learn to Dance");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todo);
		
		// When  -  actual method call - actual method invocation  
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

		// Then - check all post codition are satisfy
		//check actual method call happen or not
		//verify(todoServiceMock).deleteTodo("Learn to Dance");
		then(todoServiceMock).should().deleteTodo("Learn to Dance"); // -- BDD Approach
		//verify(todoServiceMock).deleteTodo("Learn spring"); //failed -never called
		// check method call never happen
		verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring");
		// how many times specific method called
		verify(todoServiceMock, times(1)).deleteTodo("Learn to Dance");
		then(todoServiceMock).should(times(1)).deleteTodo("Learn to Dance");
		//verify(todoServiceMock, times(2)).deleteTodo("Learn to Dance"); // failed - as not exist for 2nd call
		//then(todoServiceMock).should(times(2)).deleteTodo("Learn to Dance"); // failed - as not exist
		verify(todoServiceMock, atLeastOnce()).deleteTodo("Learn to Dance");
		//verify(todoServiceMock, atLeast(5)).deleteTodo("Learn to Dance"); // failed -expected 5 actual-1
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCapture() {
		
		// 1.Declare Argument Captor
		//ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
				
		// Given
		List<String> todo = Arrays.asList("Learn Spring", "Learn Spring MVC", "Learn to Dance");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todo);
		
		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

		// Then
		// 2.Define Argument Captor on specific method call
		then(todoServiceMock).should().deleteTodo(stringArgumentCaptor.capture());
		
		assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_usingBDD_argumentCaptureMultipleTimes() {
		
		// 1.Declare Argument Captor
		//ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
				
		// Given
		List<String> todo = Arrays.asList("Learn to Rock and Roll", "Learn Spring", "Learn Spring MVC", "Learn to Dance");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todo);
		
		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

		// Then
		// 2.Define Argument Captor on specific method call
		then(todoServiceMock).should(times(2)).deleteTodo(stringArgumentCaptor.capture());
		
		assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
	}
}
