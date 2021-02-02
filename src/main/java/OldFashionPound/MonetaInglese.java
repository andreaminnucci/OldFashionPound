package OldFashionPound;

import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MonetaInglese {
	private static final int fattoreSterlineScellini = 20 * 12;
	private static final int fattoreScelliniPennies = 12;
	
	public static MonetaInglese parseFromPennies(int pennies) {
		boolean negativo = false;
		if (pennies < 0) {
			negativo = true;
			pennies = -pennies;
		}
		int newSterline = pennies / fattoreSterlineScellini;
		int q = pennies % fattoreSterlineScellini;
		int newScellini = q / fattoreScelliniPennies;
		int newPennies = q % fattoreScelliniPennies;
		return new MonetaInglese(negativo, newSterline, newScellini, newPennies);
	}
	
	private boolean negativo;
	private int sterline;
	private int scellini;
	private int pennies;
	@Setter
	private int eventualeResto = 0;
	
	public MonetaInglese(int sterline, int scellini, int pennies) {
		this.negativo = false;
		this.sterline = sterline;
		this.scellini = scellini;
		this.pennies = pennies;
	}
	
	public MonetaInglese(boolean negativo, int sterline, int scellini, int pennies) {
		this.negativo = negativo;
		this.sterline = sterline;
		this.scellini = scellini;
		this.pennies = pennies;
	}
	
	public MonetaInglese(String parser) throws Exception {
		if ( Pattern.matches("^\\d+[p]\s\\d+[s]\s\\d+[d]$", parser) ) {
			this.sterline = Integer.parseInt( parser.split("[p]")[0] );
			parser = parser.split("[p]")[1].trim();
			this.scellini = Integer.parseInt( parser.split("[s]")[0] );
			parser = parser.split("[s]")[1].trim();;
			this.pennies = Integer.parseInt( parser.split("[d]")[0] );
		} else {
			throw new Exception("Invalid parser input");
		}
	}
	
	private int convertToPennies() {
		return ( sterline * fattoreSterlineScellini + scellini * fattoreScelliniPennies + pennies );
	}

	public MonetaInglese somma(MonetaInglese addendo) {
		int r = this.convertToPennies() + addendo.convertToPennies();
		return MonetaInglese.parseFromPennies(r);
	}
	
	public MonetaInglese sottrazione(MonetaInglese sottraendo) {
		int r = this.convertToPennies() - sottraendo.convertToPennies();
		return MonetaInglese.parseFromPennies(r);
	}
	
	public MonetaInglese moltiplicazione(int fattore) {
		int m = this.convertToPennies() * fattore;
		return MonetaInglese.parseFromPennies(m);
	}
	
	public MonetaInglese divisione(int dividendo) {
		int m = this.convertToPennies() / dividendo;
		MonetaInglese mi = MonetaInglese.parseFromPennies(m);
		int eventualeResto = this.convertToPennies() % dividendo;
		if (eventualeResto > 0) {
			mi.setEventualeResto(eventualeResto);
		}
		return mi;
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
