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
		MonetaInglese mi1 = new MonetaInglese(5, 17, 8);
		
		MonetaInglese mi2 = new MonetaInglese(3, 4, 10);
		
		MonetaInglese atteso = new MonetaInglese(9, 2, 6);
		
		MonetaInglese risultato = mi1.somma(mi2);
		
		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " + " + mi2.toString() + " = " + risultato.toString());
	}

	@Test
	@Order(2)
	void sottrazione() {
		MonetaInglese mi1 = new MonetaInglese(5, 17, 8);

		MonetaInglese mi2 = new MonetaInglese(3, 4, 10);
	
		MonetaInglese atteso = new MonetaInglese(2, 12, 10);
		
		MonetaInglese risultato = mi1.sottrazione(mi2);
		
		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " - " + mi2.toString() + " = " + risultato.toString());
	}
	
	@Test
	@Order(3)
	void sottrazioneNegativa() {
		MonetaInglese mi1 = new MonetaInglese(3, 4, 10);
		
		MonetaInglese mi2 = new MonetaInglese(5, 17, 8);
	
		MonetaInglese atteso = new MonetaInglese(true, 2, 12, 10);
		
		MonetaInglese risultato = mi1.sottrazione(mi2);
		
		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " - " + mi2.toString() + " = " + risultato.toString());
	}
	
	@Test
	@Order(4)
	void moltiplicazione() {
		MonetaInglese mi1 = new MonetaInglese(5, 17, 8);
		int fattore = 2;
	
		MonetaInglese atteso = new MonetaInglese(11, 15, 4);
		
		MonetaInglese risultato = mi1.moltiplicazione(fattore);
		
		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " * " + fattore + " = " + risultato.toString());
	}
	
	@Test
	@Order(5)
	void divisione1() {
		MonetaInglese mi1 = new MonetaInglese(5, 17, 8);
		int dividendo = 3;
	
		MonetaInglese atteso = new MonetaInglese(1, 19, 2);
		
		MonetaInglese risultato = mi1.divisione(dividendo);
		
		assertEquals(risultato, atteso);
		System.out.println( mi1.toString() + " / " + dividendo + " = " + risultato.toString());
	}
	
	@Test
	@Order(6)
	void divisione2() {
		MonetaInglese mi1 = new MonetaInglese(18, 16, 1);
		int dividendo = 15;
	
		MonetaInglese atteso = new MonetaInglese(1, 5, 0);
		
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
}
