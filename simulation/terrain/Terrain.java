package terrain;

import ecosysteme.Elementnaturel;

public class Terrain extends Elementnaturel {
	private Type type;
	private int nbrArbre;
	private int niveauEau;
	private int niveauxTemperature;

	public Terrain(Type type, double tauxReproduction, int nbrArbre) {
		super(tauxReproduction);
		this.type = type;
		this.nbrArbre = nbrArbre;
		 niveauEau = 0;
		 niveauxTemperature = 0;
	}

	public int getNbrArbre() {
		return nbrArbre;
	}

	public void setNbrArbre(int nbrArbre) {
		this.nbrArbre = nbrArbre;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean getStatus() {
		return (type == Type.FORET || type == Type.PLAIN);
	}

	public void newStatus() {
		double random = ((double) (Math.random()));
		if (random <= 0.3) {
			this.type = Type.FORET;
		} else if (random <= 0.95) {
			this.type = Type.PLAIN;
		} else {
			this.type = Type.DESERT;
		}
	}
}
