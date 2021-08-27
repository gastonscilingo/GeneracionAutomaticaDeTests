package practicos.ejercicio11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

public class SimpleRandomGenSupplier extends ParameterSupplier {
	
	Random rand;

	@Override
	public List<PotentialAssignment> getValueSources(ParameterSignature sig) throws Throwable {
		// TODO Auto-generated method stub
		SimpleRandomGen anotation = sig.getAnnotation(SimpleRandomGen.class);
		int maxInt = anotation.maxInt();
		int minInt = anotation.minInt();
		int maxLength = anotation.maxLength();
		int minLength = anotation.minLength();
		int numLists = anotation.numLists();
		List<PotentialAssignment> values = new ArrayList<PotentialAssignment> ();
		rand = new Random();
		for (int i = 0; i < numLists ; i++) {
			int length = nextIntBetween(minLength,maxLength) ;
			SinglyLinkedList l = new SinglyLinkedList();
			for(int j = 0; j < length; j++) {
				l.addFirst(rand.nextInt(maxInt - minInt + 1)  + minInt);
			}
			values.add(PotentialAssignment.forValue(l.toString(), l));
		}
		
		return values;
	}
	
	private int nextIntBetween(int min, int max) {
		return rand.nextInt(max - min +1) + min ;
		
	}

}
