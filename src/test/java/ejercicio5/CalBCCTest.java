package ejercicio5;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalBCCTest {

	
	// test 1 Base (C1-B1, C2-B3, C3-B1)
	@Test
	public final void testCalBaseCase() {
		assertEquals(348, Cal.cal(1, 1, 12, 15, 2001));
	}
	
	// test 2 (C1-B1, C2-B3, C3-B2)
	@Test
	public final void testCal2() {
		assertEquals(334, Cal.cal(1, 20, 12, 20, 2001));
	}
	
	// test 3 (C1-B1, C2-B3, C3-B3) 
	@Test
	public final void testCal3() {
		assertEquals(304, Cal.cal(1, 31, 12, 1, 2001));
	}
		
	// test 4 (C1-B1, C2-B2, C3-B1)
	@Test
	public final void testCal4() {
		assertEquals(45, Cal.cal(7, 1, 8, 15, 2001));
	}
	
	// test 5 (C1-B1, C2-B1, C3-B1)
	@Test
	public final void testCal5() {
		assertEquals(14, Cal.cal(2, 1, 2, 15, 2001));
	}
	
	// test 6 (C1-B2, C2-B3, C3-B1)
	@Test
	public final void testCal6() {
		assertEquals(349, Cal.cal(1, 1, 12, 15, 2000));
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

}
