package nl.codesquad.library.java;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloTest {
    @Test
    public void canary() {
        Tuple2<String, Integer> testTuple = Tuple.of("Java", 8);
        assertEquals(9, testTuple._2);
    }
}
