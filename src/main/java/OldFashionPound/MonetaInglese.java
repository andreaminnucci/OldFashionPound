package OldFashionPound;

import java.util.Objects;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MonetaInglese {
	private static final int FATTORE_STERLINE_SCELLINI = 20 * 12;
	private static final int FATTORE_SCELLINI_PENNIES = 12;
	
	private boolean valida = false;
	private boolean negativo;
	private int sterline;
	private int scellini;
	private int pennies;
	@Setter
	private int eventualeResto = 0;

	public MonetaInglese(int penniesTotali) {
		this.negativo = false;
		if (penniesTotali < 0) {
			this.negativo = true;
			penniesTotali = -penniesTotali;
		}
		this.sterline = penniesTotali / FATTORE_STERLINE_SCELLINI;
		int q = penniesTotali % FATTORE_STERLINE_SCELLINI;
		this.scellini = q / FATTORE_SCELLINI_PENNIES;
		this.pennies = q % FATTORE_SCELLINI_PENNIES;
		this.valida = true;
	}
	
	public MonetaInglese(String parser) {
		if (parser.startsWith("- ")) {
			this.negativo = true;
			parser = parser.substring(2);
		}
		if ( Pattern.matches("^\\d+[p]\s\\d+[s]\s\\d+[d]$", parser) ) {			
			this.sterline = Integer.parseInt( parser.split("[p]")[0] );
			parser = parser.split("[p]")[1].trim();
			this.scellini = Integer.parseInt( parser.split("[s]")[0] );
			parser = parser.split("[s]")[1].trim();;
			this.pennies = Integer.parseInt( parser.split("[d]")[0] );
			this.valida = true;
		}
	}
	
	private int trasformaInPennies() {
		return ( sterline * FATTORE_STERLINE_SCELLINI + scellini * FATTORE_SCELLINI_PENNIES + pennies );
	}

	public MonetaInglese somma(MonetaInglese addendo) {
		int penniesTotali = this.trasformaInPennies() + addendo.trasformaInPennies();
		return new MonetaInglese(penniesTotali);
	}
	
	public MonetaInglese sottrazione(MonetaInglese sottraendo) {
		int penniesTotali = this.trasformaInPennies() - sottraendo.trasformaInPennies();
		return new MonetaInglese(penniesTotali);
	}
	
	public MonetaInglese moltiplicazione(int fattore) {
		int penniesTotali = this.trasformaInPennies() * fattore;
		return new MonetaInglese(penniesTotali);
	}
	
	public MonetaInglese divisione(int dividendo) {
		int penniesTotali = this.trasformaInPennies() / dividendo;
		MonetaInglese mi = new MonetaInglese(penniesTotali);
		int eventualeResto = this.trasformaInPennies() % dividendo;
		if (eventualeResto > 0) {
			mi.setEventualeResto(eventualeResto);
		}
		return mi;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(negativo, sterline, scellini, pennies);
	}
	
	@Override
	public String toString() {
		String output = String.format("%dp %ds %dd", this.getSterline(), this.getScellini(), this.getPennies());
		if (eventualeResto > 0) {
			if (eventualeResto < 12) {
				output += String.format(" (%dd)", eventualeResto);
			} else {
				int scellini = eventualeResto / 12;
				int pennies = eventualeResto % 12;
				
				if (scellini < 20) {
					output += String.format(" (%ds %dd)", scellini, pennies);
				} else {
					int sterline = scellini / 20;
					scellini = scellini % 20;
					output += String.format(" (%dp %ds %dd)", sterline, scellini, pennies);
				}
			}
		}
		if (this.negativo)
			output = "- " + output; 
		
		return output;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof MonetaInglese)) return false;
		MonetaInglese mi = (MonetaInglese) obj;
		return	mi.isNegativo() == this.isNegativo() &&
				mi.getSterline() == this.getSterline() && 
				mi.getScellini() == this.getScellini() && 
				mi.getPennies() == this.getPennies();
	}

}
