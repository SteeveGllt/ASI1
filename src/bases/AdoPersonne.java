package bases;

import classes.Client;
import classes.Personne;

import java.sql.*;

public class AdoPersonne {


    public static void save(Client c) {
        long id = 0;
        //String requete = "INSERT INTO personne VALUES (2, 'emmanuel', 'macron', 'manu.marcon@gmail.com', '123')";
        try {

            PreparedStatement insertPersonne = Ado.open().prepareStatement("INSERT INTO personne (nom, prenom, mail, mdp) VALUES (?,?,?,?)");
            insertPersonne.setString(1, c.getNom());
            insertPersonne.setString(2, c.getPrenom());
            insertPersonne.setString(3, c.getMail());
            insertPersonne.setString(4, c.getMdp());

            insertPersonne.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static Personne update(Personne p) {
        String requete = "UPDATE personne SET nom = 'Chantal' WHERE id_personne=1";
        try {
            Statement stmt = Ado.open().createStatement();
            stmt.executeUpdate(requete);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return p;
    }

}
