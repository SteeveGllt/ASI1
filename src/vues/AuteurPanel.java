package vues;

import classes.Auteur;
import classes.Personne;
import classes.Realisateur;
import main.Main;
import models.AuteurTableModel;
import utils.PasswordGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AuteurPanel extends JPanel implements ActionListener, MouseListener {

    JButton annuler;
    JButton valider;
    JButton supprimer;
    JTextField nomT;
    JTextField prenomT;
    JFrame fenetre;
    JRadioButton radioAuteur;
    JRadioButton radioReal;
    ButtonGroup btnGroup;

    AuteurTableModel model;
    JTable tab;

    Auteur update;

    public AuteurPanel(JFrame mafenetre) {
        super();
        fenetre = mafenetre;
        //this.setLayout(new GridLayout(0, 2, 100, 100));
        this.add(FormulairePanel());
        this.add(ListerPanel());
    }

    public JPanel FormulairePanel() {
        JPanel me = new JPanel();
        me.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        JLabel nom = new JLabel("Nom :");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(nom, c);
        nomT = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(nomT, c);

        JLabel prenom = new JLabel("Prénom :");
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(prenom, c);
        prenomT = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(prenomT, c);

        JLabel type = new JLabel("Type :");
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10,100, 10,0);
        me.add(type, c);
        radioAuteur = new JRadioButton("Auteur");
        c.gridx = 1;
        c.insets = new Insets(10,10,10,10);
        me.add(radioAuteur,c);
        radioReal = new JRadioButton("Realisateur");
        c.gridx = 2;
        c.insets = new Insets(10,10,10,10);
        me.add(radioReal,c);



        JPanel btn = new JPanel();
        btn.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        annuler = new JButton("Annuler");
        annuler.addActionListener(this);
        btn.add(annuler, c);
        c.gridx = 1;
        c.gridy = 0;
        valider = new JButton("Valider");
        valider.addActionListener(this);
        btn.add(valider, c);
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10, 200, 10, 0);
        me.add(btn, c);
        return me;

    }

    public JPanel ListerPanel() {
        JPanel me = new JPanel();
        me.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(10, 100, 10, 100);
        c.gridy = 0;
        tab = new JTable();
        tab.setAutoCreateRowSorter(true);
        model = new AuteurTableModel();
        tab.setModel(model);
        tab.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(tab);
        me.add(scrollPane, c);
        supprimer = new JButton("Supprimer");
        supprimer.addActionListener(this);
        c.gridy = 1;
        c.weightx = 0.5;
        c.insets = new Insets(10, 700, 10, 100);
        me.add(supprimer, c);
        return me;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == valider) {
            if(radioAuteur.isSelected()){
                if (update == null) {
                    Auteur a = new Auteur(nomT.getText(), prenomT.getText());
                    Main.autReals.add(a);
                }
                else {
                    update.setNom(nomT.getText());
                    update.setPrenom(prenomT.getText());
                }
                model = new AuteurTableModel();
                tab.setModel(model);
                fenetre.revalidate();
                fenetre.repaint();
            }
            else if(radioReal.isSelected()){
                if (update == null) {
                    Realisateur a = new Realisateur(nomT.getText(), prenomT.getText());
                    Main.autReals.add(a);
                }
                else {
                    update.setNom(nomT.getText());
                    update.setPrenom(prenomT.getText());
                }
                model = new AuteurTableModel();
                tab.setModel(model);
                fenetre.revalidate();
                fenetre.repaint();
            }

        }
        if (e.getSource() == annuler) {
            nomT.setText("");
            prenomT.setText("");
            update = null;
        }
        if (e.getSource() == supprimer) {
            String text = "Voulez-vous vraiment supprimer les sélections ?";
            String title = "Confirmation";
            int optionType = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, text, title, optionType);
            if (result == JOptionPane.OK_OPTION) {
                int index = 0;
                for (int i = 0; i < Main.autReals.size(); i++) {
                    if (Boolean.parseBoolean(model.getValueAt(index, 3).toString()) == true) {
                        Main.autReals.remove(i);
                        i--;
                    }
                    index++;
                }
                model = new AuteurTableModel();
                tab.setModel(model);
                fenetre.revalidate();
                fenetre.repaint();
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) { // to detect doble click events
            JTable target = (JTable) e.getSource();
            update = (Auteur) Main.autReals.get(target.getSelectedRow());
            nomT.setText(update.getNom());
            prenomT.setText(update.getPrenom());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
