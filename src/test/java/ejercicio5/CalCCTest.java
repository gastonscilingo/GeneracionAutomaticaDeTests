package ejercicio5;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

import org.junit.Test;

public class CalCCTest {

	
	// Test 1: (month1 = 1, day1 = 1, month2 = 3, day2 = 28, year = 2001)
	@Test
	public final void testCal() {
		assertEquals(86, Cal.cal(1, 1, 3, 28, 2001));
	}
	
	// Test 2: (month1 = -1, day1 = 0, month2 = -2, day2 = 0, year = 0)
	@Test (expected=Exception.class)
	public final void testCal2() {
		Cal.cal(-1, 0, -2, 0, 0);
		fail("no exception was thrown ");

	}
	
	// Test 3: (month1 = 13, day1 = 32, month2 = 13, day2 = 32, year = 10001) 
	@Test
	public final void testCal3() {
		assertEquals(0,Cal.cal(13, 32, 13, 32, 10001));
	}
		
	// purpose achieve 100% of branch coverage
	
	@Test
	public final void testCal7_M4M100NoM400() {
		assertEquals(348, Cal.cal(1, 1, 12, 15, 1000));
	}
	
	@Test
	public final void testCal7No100NoM400() {
		assertEquals(349, Cal.cal(1, 1, 12, 15, 4));
	}
	
	// purpose achieve 100% of mutation coverage	
	// test 6 (C1-B2, C2-B3, C3-B1)
	@Test
	public final void testCal6() {
		assertEquals(349, Cal.cal(1, 1, 12, 15, 2000));
	}

}
