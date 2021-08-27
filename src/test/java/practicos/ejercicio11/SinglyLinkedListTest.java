package practicos.ejercicio11;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeThat;

import org.junit.runner.RunWith;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;


import static org.hamcrest.Matchers.*;



@RunWith(Theories.class)
public class SinglyLinkedListTest {
	
	final int defMaxInt = 100;
	final int defMinInt = -100;
	final int defMaxLength = 10;
	final int defMinLength = 0;
	final int defNumLists = 10;
	
	@Theory
	public void singlyLinkedListTest(@SimpleRandomGen(maxInt = defMaxInt, maxLength = defMaxLength, minInt = defMinInt, minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, notNullValue());
		SinglyLinkedList origin = new SinglyLinkedList(l);
		assertThat(origin, equalTo(l));
	}
	
	@Theory
	public void addFirstTest(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = defMaxLength,  minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, not(nullValue()));
		l.addFirst(0);
		assertThat(l.toString(), startsWith("[0"));
	}
	
	@Theory
	public void addFirstTest2(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = defMaxLength,  minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, not(nullValue()));
		assumeThat(l.toString(),not(containsString("0")));
		l.addFirst(0);
		assertThat(l.toString(), containsString("0"));
	}
	
	@Theory
	public void undoAddFirstTest(@SimpleRandomGen(maxInt = defMaxInt, maxLength = defMaxLength, minInt = defMinInt, minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, notNullValue());
		SinglyLinkedList origin = new SinglyLinkedList(l);
		l.addFirst(0);
		l.remove(0);
		assertThat(l, equalTo(origin));
	}
	
	@Theory
	public void removeFromEmptyListTest(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = 0,  minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, not(equalTo(null)));
		assumeThat("empty list",l.isEmpty(), is(true));
		SinglyLinkedList origin = new SinglyLinkedList(l);
		l.remove(0);
		assertThat(l, equalTo(origin));
	}
	
	@Theory
	public void removeFromNonEmptyListTest(@SimpleRandomGen(maxInt = 0, minInt = 0, maxLength = defMaxLength,  minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, not(equalTo(null)));
		assumeThat("size ",l.getSize(), greaterThan(0));
		int originSize = l.getSize();
		l.remove(0);
		assertThat(l.getSize(), lessThan(originSize));
	}
	
	@Theory
	public void removeFromNonEmptyListTest2(@SimpleRandomGen(maxInt = 1, minInt = 0, maxLength = defMaxLength,  minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, not(equalTo(null)));
		int originSize = l.getSize();
		l.addFirst(2);
		l.remove(0);
		assertThat(l.getSize(), lessThanOrEqualTo(originSize+1));
	}

	@Theory
	public void toStringTest(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = defMaxLength,  minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, notNullValue());
		String origin = l.toString();
		l.addFirst(0);
		l.remove(0);
		assertThat(l, hasToString(origin));
	}
	
	@Theory
	public void toStringTest2(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = defMaxLength,  minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, notNullValue());
		l.addFirst(defMaxInt+1);
		String newElem = String.valueOf(defMaxInt+1);
		assertThat(l.toString(), containsString(newElem));
	}
	
	@Theory
	public void containsTest(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = defMaxLength,  minLength = 1, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, notNullValue());
		l.addFirst(50);
		boolean contains = l.contains(50);
		assertThat(contains, is(true));
	}
	
	@Theory
	public void containsFalseTest(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = defMaxLength,  minLength = 0, numLists = defNumLists) SinglyLinkedList l) {
		assumeThat(l, notNullValue());
		String asString = l.toString();
		boolean contains = l.contains(101);
		assertThat(asString, not(containsString("101")));
		assertThat(contains, is(false));
	}
	
	@Theory
	public void isEmptyTest(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = 0,  minLength = 0, numLists = 1) SinglyLinkedList l) {
		assumeThat(l, notNullValue());
		boolean isEmpty = l.isEmpty();
		assertThat(isEmpty, is(true));
	}
	
	@Theory
	public void isEmptyTest2(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = defMaxLength,  minLength = 1, numLists = 1) SinglyLinkedList l) {
		assumeThat(l, notNullValue());
		boolean isEmpty = l.isEmpty();
		assertThat(isEmpty, not(true));
	}
	
	@Theory
	public void notEqualsTest(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = defMaxLength,  minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, not(equalTo(null)));
		SinglyLinkedList origin = new SinglyLinkedList(l);
		l.addFirst(0);
		assertThat(l, not(equalTo(origin)));
	}
	
	@Theory
	public void notEqualsTest2(@SimpleRandomGen(maxInt = defMaxInt, minInt = defMinInt, maxLength = defMaxLength,  minLength = defMinLength, numLists = 10) SinglyLinkedList l) {
		assumeThat(l, not(equalTo(null)));
		SinglyLinkedList q = new SinglyLinkedList(l);
		l.addFirst(0);
		q.addFirst(1);
		assertThat(l, not(equalTo(q)));
	}
}
