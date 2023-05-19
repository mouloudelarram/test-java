package animal;

import ecosysteme.Elementnaturel;

public class Animal extends Elementnaturel {
	private int sante;
	private int genre;
	private boolean empoisonne;

	public Animal(double tauxReproduction, int sante, int genre, boolean empoisonne) {
		super(tauxReproduction);
		this.sante = sante;
		this.genre = genre;
		this.empoisonne = empoisonne;
	}

	public int getSante() {
		return sante;
	}

	public void setSante(int nouvelleSante) {
		this.sante = nouvelleSante;
	}

	public int getGenre() {
		return genre;
	}

	public boolean isEmpoisonne() {
		return empoisonne;
	}
}
