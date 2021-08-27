package ejercicio5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalCACCTest {

	
	// P == c1:(month1 >= 1) & c2:(month2 >= 1) & c3:(month1<= 12) & c4:(month2 <= 12) &
	// c5:(day1 >= 1) & c6:(day2 >= 1) & c7:(day1 <= 31) & c8:(day2 <= 31) &
	// c9:(month1 <= month2) & c10:(year >= 1) & c11:(year <= 10000)

	
	// Test c1:T: (month1 >= 1)
	@Test
	public final void testCalC1T() {
		assertEquals(27, Cal.cal(1, 1, 1, 28, 2001));
	}
	
	// Test c1:F: !(month1 >= 1)
	@Test
	public final void testCalC1F() {
		assertEquals(86, Cal.cal(0, 1, 3, 28, 2001));
	}
	
	// Test c2:F !(month2 >= 1)
	@Test
	public final void testCalC2F() {
		assertEquals(58, Cal.cal(1, 1, 0, 28, 2001));
	}
	
	// Test c3:F !(month1<= 12)
	@Test (expected=Exception.class)
	public final void testCalC3F() {
		assertEquals(86, Cal.cal(13, 1, 3, 28, 2001));
	}
	
	// Test c4:F !(month2 <= 12)
	@Test
	public final void testCalC4F() {
		assertEquals(392, Cal.cal(1, 1, 13, 28, 2001));
	}
	
	// Test c5:F !(day1 >= 1)
	@Test
	public final void testCalC5F() {
		assertEquals(87, Cal.cal(1, 0, 3, 28, 2001));
	}
	
	// Test c6:F !(day2 >= 1)
	@Test
	public final void testCalC6F() {
		assertEquals(58, Cal.cal(1, 1, 3, 0, 2001));
	}
	
	// Test c7:F !(day1 <= 31)
	@Test
	public final void testCalC7F() {
		assertEquals(55, Cal.cal(1, 32, 3, 28, 2001));
	}
	
	// Test c8:F !(day2 <= 31)
	@Test
	public final void testCalC8F() {
		assertEquals(90, Cal.cal(1, 1, 3, 32, 2001));
	}
	
	// Test c9:F !(month1 <= month2)
	@Test
	public final void testCalC9F() {
		assertEquals(58, Cal.cal(3, 1, 1, 28, 2001));
	}
	
	// Test c10:F !(year >= 1)
	@Test
	public final void testCalC10F() {
		assertEquals(87, Cal.cal(1, 1, 3, 28, 0));
	}
	
	// Test c11:F !(year <= 10000)
	@Test
	public final void testCalC11F() {
		assertEquals(86, Cal.cal(1, 1, 3, 28, 10001));
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
