package com.pkb.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {

	@Test
	public void test() {
		/*List arrayListMock = mock(ArrayList.class);
		assertEquals(0, arrayListMock.size());*/
		//mocks return default value
		/*stub(arrayListMock.size()).toReturn(5);
		assertEquals(5, arrayListMock.size());*/
		// this mocking add will not effect actual size of arrayList
		/*arrayListMock.add("Dummy");
		assertEquals(5, arrayListMock.size());*/
		
		//spy will effect the size of the actual ArrayList
		List arrayListSpy = spy(ArrayList.class);
		/*assertEquals(0, arrayListSpy.size());
		arrayListSpy.add("Dummy");
		assertEquals(1, arrayListSpy.size());
		arrayListSpy.remove("Dummy");
		assertEquals(0, arrayListSpy.size());*/
		
		// spy enables you to watch real action! As well as change or override specific behavior when needed!
		/*stub(arrayListSpy.size()).toReturn(5);
		assertEquals(5, arrayListSpy.size());*/
		
		//verify invocation of certain method
		arrayListSpy.add("Dummy");
		// check specific method call
		verify(arrayListSpy).add("Dummy");
		// check specific method are not called
		verify(arrayListSpy, never()).clear();
		
	}

}
