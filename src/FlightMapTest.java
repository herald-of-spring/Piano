import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlightMapTest {

	/**
	 * Environment setup.
	 * <p>
	 * ...A<-E
	 * <p>
	 * ../|^.|
	 * <p>
	 * .v.v.\v
	 * <p>
	 * C->B->D
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		FlightMap a = new FlightMap('A');
		FlightMap b = new FlightMap('B');
		FlightMap c = new FlightMap('C');
		FlightMap d = new FlightMap('D');
		FlightMap e = new FlightMap('E');
		a.add('B', 200);
		a.add('C', 400);
		b.add('D', 1000);
		c.add('B', 500);
		d.add('A', 700);
		e.add('A', 100);
		e.add('D', 900);
	}

	@Test
	void listTest() {
		
		assertEquals("Not yet implemented");
	}

}
