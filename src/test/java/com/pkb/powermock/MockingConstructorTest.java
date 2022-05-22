package com.pkb.powermock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUnderTest.class)
public class MockingConstructorTest {
	
	//PrepareForTest => SystemUnderTest.class which is using Constructor
	//use specific method to override the constructor
	
	@InjectMocks
	SystemUnderTest systemUnderTest;
	
	@Mock
	ArrayList mockList;

	@Test
	public void testBadName() throws Exception {

		List<Integer> stats = Arrays.asList(1, 2, 3);
		
		when(mockList.size()).thenReturn(5);
		
		PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);

		int size = systemUnderTest.methodUsingAnArrayListConstructor();
		// by dedault it return 0 but mocked in line-37 to return 5
		assertEquals(5, size);
		

	}

}
