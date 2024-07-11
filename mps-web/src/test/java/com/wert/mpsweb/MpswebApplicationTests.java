package com.wert.mpsweb;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.ParameterizedTest; 
import org.junit.jupiter.params.provider.MethodSource; 
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;



import com.wert.mpsweb.mps.Note;

@SpringBootTest
class MpswebApplicationTests {

	@Test
	void contextLoads() {
	}

	@ParameterizedTest
	@MethodSource("noteDurationBoundsChecksCases")
	void noteDurationBoundsChecks(int duration, boolean expectThrow) throws Exception {
		if (expectThrow) {
			// assertThrows will execute the given block of code and return the exception
			// that was thrown, if an exception of the specified type was thrown.
			// If an exception isn't thrown, it'll throw an exception itself, causing the test
			// to fail.
			Exception e = assertThrows(IllegalArgumentException.class, () -> {
				var x = new Note(7, duration);
			});
			var expectedMessage = "Duration must be greater than zero";
			assertTrue(e.getMessage().contains(expectedMessage));
		} else {
			// Simply create the note; if an exception is thrown, test will fail
			var x = new Note(7, duration);
		}
	}

	// Provides durations inside and outside of valid bounds for note duration,
	// and true/false indicating whether they're invalid and should cause an 
	// exception to be thrown when creating a Note
	static Stream<Arguments> noteDurationBoundsChecksCases() {
		return Stream.of(
			Arguments.of(-1, true),
			Arguments.of(0, true),
			Arguments.of(1, false),
			Arguments.of(15, false),
			Arguments.of(16, false),
			Arguments.of(17, true)
		);
	}
}
