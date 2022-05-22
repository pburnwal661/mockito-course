package com.pkb.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void letsMockListSize() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2);
		
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
	}
	
	@Test
	public void letsMockListSize_ReturnMultipleValues() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(2).thenReturn(3);
		
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}
	
	@Test
	public void letsMockListGet() {
		List listMock = mock(List.class);
		// Approach -1
		when(listMock.get(0)).thenReturn("pkb");
		when(listMock.get(anyInt())).thenReturn("pkb");
		assertEquals("pkb", listMock.get(0));
		assertEquals(null, listMock.get(1));
	}
	
	@Test
	public void letsMockListGet_UsingBDD() {
		//Given
		List<String> listMock = mock(List.class);
		given(listMock.get(anyInt())).willReturn("pkb");
		
		//When
		String firstElement = listMock.get(0);
		
		//Then
		assertThat(firstElement, is("pkb"));
		//assertEquals("pkb", firstElement);
		//assertEquals(null, listMock.get(1));
	}
	
	@Test
	public void letsMockListGet_ArgumentMatcher() {
		List listMock = mock(List.class);
		// Approach -2
		when(listMock.get(anyInt())).thenReturn("pkb");
		assertEquals("pkb", listMock.get(0));
		assertEquals("pkb", listMock.get(1));
	}
	
	@Test(expected=RuntimeException.class)
	public void letsMockListGet_throwException() {
		List listMock = mock(List.class);
		// Approach -2
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("something"));
		listMock.get(0);
	}
	
	@Test(expected=RuntimeException.class)
	public void letsMockListGet_mxingUp() {
		List listMock = mock(List.class);
		// Approach -2
		when(listMock.subList(anyInt(), 5)).thenThrow(new RuntimeException("something"));
		listMock.get(0);
	}
}
