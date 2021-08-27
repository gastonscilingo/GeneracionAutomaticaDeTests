package junitquickcheck;

import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assume.assumeThat;

import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.Property;

import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

import java.util.Random;

@RunWith(JUnitQuickcheck.class)
public class SinglyLinkedListQuickcheckTest {

	
	Random rand;
	

	@Property(trials = 10)
	public void singlyLinkedListTest(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, notNullValue());
		SinglyLinkedList origin = new SinglyLinkedList(l);
		assertThat(origin, equalTo(l));
	}
	
	@Property(trials = 10)
	public void singlyLinkedListTest2(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, notNullValue());
		SinglyLinkedList origin = new SinglyLinkedList(l);
		assertThat(origin, equalTo(l));
	}
	
	@Property(trials = 10)
	public void addFirstTest(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, not(nullValue()));
		l.addFirst(0);
		assertThat(l.toString(), startsWith("[0"));
	}
	
	@Property(trials = 10)
	public void addFirstTest2(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, not(nullValue()));
		assumeThat(l.toString(),not(containsString("0")));
		l.addFirst(0);
		assertThat(l.toString(), containsString("0"));
	}
	
	@Property(trials = 10)
	public void undoAddFirstTest(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, notNullValue());
		SinglyLinkedList origin = new SinglyLinkedList(l);
		l.addFirst(0);
		l.remove(0);
		assertThat(l, equalTo(origin));
	}
	
	@Property(trials = 10)
	public void removeFromEmptyListTest(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 0) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, not(equalTo(null)));
		assumeThat("empty list",l.isEmpty(), is(true));
		SinglyLinkedList origin = new SinglyLinkedList(l);
		l.remove(0);
		assertThat(l, equalTo(origin));
	}
	
	@Property(trials = 10)
	public void removeFromNonEmptyListTest(@InRange(minInt = 0, maxInt = 0) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, not(equalTo(null)));
		assumeThat("size ",l.getSize(), greaterThan(0));
		int originSize = l.getSize();
		l.remove(0);
		assertThat(l.getSize(), lessThan(originSize));
	}
	
	@Property(trials = 10)
	public void removeFromNonEmptyListTest2(@InRange(minInt = 0, maxInt = 1) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, not(equalTo(null)));
		int originSize = l.getSize();
		l.addFirst(2);
		l.remove(0);
		assertThat(l.getSize(), lessThanOrEqualTo(originSize+1));
	}
	
	@Property(trials = 10)
	public void toStringTest(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, notNullValue());
		String origin = l.toString();
		l.addFirst(0);
		l.remove(0);
		assertThat(l, hasToString(origin));
	}
	
	@Property(trials = 10)
	public void toStringTest2(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, notNullValue());
		l.addFirst(intValue + 1);
		String newElem = String.valueOf(intValue + 1);
		assertThat(l.toString(), containsString(newElem));
	}
	
	@Property(trials = 10)
	public void containsTest(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 1, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, notNullValue());
		l.addFirst(50);
		boolean contains = l.contains(50);
		assertThat(contains, is(true));
	}

	@Property(trials = 10)
	public void containsFalseTest(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, notNullValue());
		String asString = l.toString();
		boolean contains = l.contains(intValue + 1);
		assertThat(asString, not(containsString(new Integer(intValue+1).toString())));
		assertThat(contains, is(false));
	}
	
	@Property(trials = 1)
	public void isEmptyTest(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 0) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, notNullValue());
		boolean isEmpty = l.isEmpty();
		assertThat(isEmpty, is(true));
	}
	
	@Property(trials = 10)
	public void isEmptyTest2(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 1, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, notNullValue());
		boolean isEmpty = l.isEmpty();
		assertThat(isEmpty, not(true));
	}
	
	@Property(trials = 10)
	public void notEqualsTest(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, not(equalTo(null)));
		SinglyLinkedList origin = new SinglyLinkedList(l);
		l.addFirst(0);
		assertThat(l, not(equalTo(origin)));
	}
	
	@Property(trials = 10)
	public void notEqualsTest2(@InRange(minInt = 0, maxInt = 100) int intValue, @InRange(minInt = 0, maxInt = 10) int length) {
		SinglyLinkedList l = getValueSource(intValue, -intValue, length);
		assumeThat(l, not(equalTo(null)));
		SinglyLinkedList q = new SinglyLinkedList(l);
		l.addFirst(0);
		q.addFirst(1);
		assertThat(l, not(equalTo(q)));
	}
	
	/*
	 * getValueSource Auxiliar 
	 */
	private SinglyLinkedList getValueSource(int maxInt, int minInt, int length) {
		rand = new Random();
		SinglyLinkedList l = new SinglyLinkedList();
		for(int j = 0; j < length; j++) {
			l.addFirst(rand.nextInt(maxInt - minInt + 1)  + minInt);
		}		
		return l;
	}

	
}
