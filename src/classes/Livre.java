package classes;

public class Livre extends Article {

	private String isbn;
	private int nbPages;
	private Auteur auteur;

	public Livre(String uneRef, String uneDesi, Double unPrix, String unIsbn, int unNbPages) {
		super(uneRef, uneDesi, unPrix);
		this.isbn = unIsbn;
		this.nbPages = unNbPages;
	}

	public Auteur getAuteur() {
		return auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getNbPages() {
		return nbPages;
	}

	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	@Override
	public String toString() {
		return super.toString() + "Nombre de pages : " + this.nbPages;
	}
}
