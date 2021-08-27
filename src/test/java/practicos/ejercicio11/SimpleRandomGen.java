package practicos.ejercicio11;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

import org.junit.experimental.theories.ParametersSuppliedBy;

@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(SimpleRandomGenSupplier.class)
public @interface SimpleRandomGen {
	int maxInt();
	int minInt();
	int maxLength();
	int minLength();
	int numLists();
}
