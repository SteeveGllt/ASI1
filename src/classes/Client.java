package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client extends Personne {

	private String mail;
	private String mdp;
	private ArrayList<Emprunter> articles;

	public Client() {
	}

	public Client(String nom, String prenom, String mail, String mdp) {
		super(nom, prenom);
		this.mail = mail;
		this.mdp = mdp;
		this.articles = new ArrayList<Emprunter>();
	}

	public void AjouterArticle(Article a) {
		Emprunter emp = new Emprunter(LocalDate.now(), a);
		articles.add(emp);
	}

	public ArrayList<Emprunter> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Emprunter> articles) {
		this.articles = articles;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

}
