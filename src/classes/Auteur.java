package classes;

import java.util.ArrayList;

public class Auteur extends Personne {

	private ArrayList<Livre> livres;

	public Auteur(String nom, String prenom) {
		super(nom, prenom);
		this.livres = new ArrayList<Livre>();
	}

	public ArrayList<Livre> getLivres() {
		return livres;
	}

	public void setVideos(ArrayList<Livre> livres) {
		this.livres = livres;
	}

	public void AjouterLivre(Livre l) {
		this.livres.add(l);
		l.setAuteur(this);
	}
}
