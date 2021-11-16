package main;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import bases.Ado;
import bases.AdoPersonne;
import classes.*;
import utils.AddressGenerator;
import vues.Accueil;

public class Main {

		public static ArrayList<Client> clients;
		public static ArrayList<Personne> autReals;
		public static ArrayList<Article> articles;
		public static ArrayList<Livre> livres;
		public static ArrayList<Video> videos;

	public static ArrayList<Auteur> auteurs;
	public static ArrayList<Realisateur> realisateurs;

	public static void main(String[] args) {
		clients = new ArrayList<Client>();
		autReals = new ArrayList<Personne>();
		livres = new ArrayList<Livre>();
		videos = new ArrayList<Video>();
		articles = new ArrayList<Article>();
		auteurs = new ArrayList<Auteur>();
		realisateurs = new ArrayList<Realisateur>();
		Client c = new Client("courbez", "julian", "julian.courbez@gmail.com",
				utils.PasswordGenerator.generateStrongPassword());
		clients.add(c);
		c = new Client("a", "cdsscd", "julian.cdsscd@gmail.com", utils.PasswordGenerator.generateStrongPassword());
		clients.add(c);
		c = new Client("b", "cdsbhnh", "b.cdsbhnh@gmail.com", utils.PasswordGenerator.generateStrongPassword());
		clients.add(c);
		c = new Client("c", "likyhftgftg", "c.likyhftgftg@gmail.com", utils.PasswordGenerator.generateStrongPassword());
		clients.add(c);

		/** Auteurs **/
		Auteur a1 = new Auteur("Auteur 1","Auteur 1");
		auteurs.add(a1);
		Auteur a2 = new Auteur("Auteur 2","Auteur 2");
		auteurs.add(a2);
		Auteur a3 = new Auteur("Auteur 3","Auteur 3");
		auteurs.add(a3);
		Auteur a4 = new Auteur("Auteur 4","Auteur 4");
		auteurs.add(a4);

		/** Réalisateurs **/
		Realisateur r1 = new Realisateur("Réalisaeteur 1","Réalisateur 1");
		realisateurs.add(r1);
		Realisateur r2 = new Realisateur("Réalisaeteur 2","Réalisaeteur 2");
		realisateurs.add(r2);
		Realisateur r3 = new Realisateur("Réalisaeteur 3","Réalisaeteur 3");
		realisateurs.add(r3);
		Realisateur r4 = new Realisateur("Réalisaeteur 4","Réalisaeteur 4");
		realisateurs.add(r4);


		Accueil a = new Accueil();


	}

	public static void ExoObjet() {
		/*
		 * Article a = new Article(); a.setDesignation("PC hp"); a.setPrix(599.99);
		 * a.setReference("A545E");
		 */

		// System.out.println(a.toString());

		Video d = new Video("les dents de la mer", "JL45M", 15.99, 120);
		System.out.println(d.toString());

		Livre l = new Livre("le petit prince", "ak47", 1.00, "11111111", 127);
		System.out.println(l.toString());
	}

	public static void Exercice1() {
		int cptOut = 0;
		int cptGmail = 0;
		int cptGroupe = 0;
		String[] list = AddressGenerator.GenerateAddress(10);
		for (int i = 0; i < list.length; i++) {
			String[] slp = list[i].split("@");
			if (slp[1].equals("outlook.fr")) {
				cptOut++;
			} else if (slp[1].equals("gmail.com")) {
				cptGmail++;
			} else {
				cptGroupe++;
			}
		}

		System.out.println("Part de march� Outlook :" + cptOut * 10 + "%");
		System.out.println("Part de march� Gmail :" + cptGmail * 10 + "%");
		System.out.println("Part de march� GroupeMontRoland :" + cptGroupe * 10 + "%");
	}

	public static void Exercice2() {
		int nb1 = new Random().nextInt(1000);
		int nb2 = new Random().nextInt(1000);
		int nb3 = new Random().nextInt(1000);
		int cpt = 0;
		while (nb1 % 2 == 1) {
			nb1 = new Random().nextInt(1000);
			cpt++;
		}
		while (nb2 % 2 == 1) {
			nb2 = new Random().nextInt(1000);
			cpt++;
		}
		while (nb3 % 2 == 0) {
			nb3 = new Random().nextInt(1000);
			cpt++;
		}
		System.out.println("Nombres g�n�r�s: " + nb1 + " " + nb2 + " " + nb3);
		System.out.println("Nombre d'essais : " + cpt);

	}

	public static void Exercice3() {
		int find = new Random().nextInt(1000);
		int guess = 0;
		int cpt = 0;
		LocalDateTime start = LocalDateTime.now();
		System.out.println("Saisissez un nombre entre 0 et 1000 :");
		while (guess != find) {
			cpt++;

			guess = Integer.parseInt(new Scanner(System.in).nextLine());
			if (guess > find) {
				System.out.println("Vous �tes au dessus du nombre cherch�");
			}
			if (guess < find) {
				System.out.println("Vous �tes en dessous du nombre cherch�");
			}
		}
		LocalDateTime end = LocalDateTime.now();
		System.out.println("Bien jou� ! Le chiffre �tait : " + find);
		System.out.println(
				"Vous l'avez trouv� en " + cpt + " fois et en " + (start.until(end, ChronoUnit.SECONDS) + " secondes"));
	}
}
