package OldFashionPound;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class MonetaIngleseTests {

	@Test
    @Order(1)
	void somma() {
		MonetaInglese mi1 = new MonetaInglese("5p 17s 8d");
		
		MonetaInglese mi2 = new MonetaInglese("3p 4s 10d");
		
		MonetaInglese atteso = new MonetaInglese("9p 2s 6d");
		
		MonetaInglese risultato = mi1.somma(mi2);
		
		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " + " + mi2.toString() + " = " + risultato.toString());
	}

	@Test
	@Order(2)
	void sottrazione() {
		MonetaInglese mi1 = new MonetaInglese("5p 17s 8d");

		MonetaInglese mi2 = new MonetaInglese("3p 4s 10d");
	
		MonetaInglese atteso = new MonetaInglese("2p 12s 10d");
		
		MonetaInglese risultato = mi1.sottrazione(mi2);
		
		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " - " + mi2.toString() + " = " + risultato.toString());
	}
	
	@Test
	@Order(3)
	void sottrazioneNegativa() {
		MonetaInglese mi1 = new MonetaInglese("3p 4s 10d");
		
		MonetaInglese mi2 = new MonetaInglese("5p 17s 8d");
	
		MonetaInglese atteso = new MonetaInglese("- 2p 12s 10d");
		
		MonetaInglese risultato = mi1.sottrazione(mi2);
		
		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " - " + mi2.toString() + " = " + risultato.toString());
	}
	
	@Test
	@Order(4)
	void moltiplicazione() {
		MonetaInglese mi1 = new MonetaInglese("5p 17s 8d");
		int fattore = 2;
	
		MonetaInglese atteso = new MonetaInglese("11p 15s 4d");
		
		MonetaInglese risultato = mi1.moltiplicazione(fattore);
		
		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " * " + fattore + " = " + risultato.toString());
	}
	
	@Test
	@Order(5)
	void divisione1() {
		MonetaInglese mi1 = new MonetaInglese("5p 17s 8d");
		int dividendo = 3;
	
		MonetaInglese atteso = new MonetaInglese("1p 19s 2d");
		
		MonetaInglese risultato = mi1.divisione(dividendo);
		
		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " / " + dividendo + " = " + risultato.toString());
	}
	
	@Test
	@Order(6)
	void divisione2() {
		MonetaInglese mi1 = new MonetaInglese("18p 16s 1d");
		int dividendo = 15;
	
		MonetaInglese atteso = new MonetaInglese("1p 5s 0d");
		
		MonetaInglese risultato = mi1.divisione(dividendo);

		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " / " + dividendo + " = " + risultato.toString());
	}
	
	@Test
	@Order(7)
	void parserInput() throws Exception {
		MonetaInglese mi = new MonetaInglese("18p 16s 1d");
	
		assertEquals(mi.getSterline(), 18);
		assertEquals(mi.getScellini(), 16);
		assertEquals(mi.getPennies(), 1);
		
		System.out.println( "parser: " + mi.toString() );
	}
	
	@Test
	@Order(8)
	void parserInputFailed() throws Exception {
		MonetaInglese mi = new MonetaInglese("18 16s 1d");
		assertEquals(mi.isValida(), false);
	}
}
