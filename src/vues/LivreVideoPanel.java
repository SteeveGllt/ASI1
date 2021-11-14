package vues;

import classes.*;
import main.Main;
import models.AuteurTableModel;
import models.LivreVideoTableModel;
import models.VideoTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class LivreVideoPanel extends JPanel implements ActionListener, MouseListener {
    JButton annuler;
    JButton valider;
    JButton supprimer;
    JRadioButton rdLivre;
    JRadioButton rdVideo;
    JTextField desingationT;
    JTextField referenceT;
    JTextField prixT;
    JTextField isbnT;
    JTextField nbPages;
    JTextField dureeT;
    JComboBox<Personne> auteurJComboBox;
    JComboBox<Realisateur> realisateurJComboBox;
    JFrame fenetre;
    ButtonGroup btnGroup;

    LivreVideoTableModel model;
    JTable tab;

    Article update;

    public LivreVideoPanel(JFrame mafenetre) {
        super();
        fenetre = mafenetre;
        //	this.setLayout(new GridLayout(0, 2, 100, 100));
        this.add(FormulairePanel());
        this.add(ListerPanel());
    }

    public JPanel FormulairePanel() {



        JPanel me = new JPanel();
        me.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        JLabel reference = new JLabel("Référence :");
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(reference, c);
        referenceT = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(referenceT, c);

        JLabel designation = new JLabel("Désignation :");
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(designation, c);
        desingationT = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(desingationT, c);

        JLabel prix = new JLabel("Prix :");
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(prix, c);
        prixT = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(prixT, c);


        JLabel type = new JLabel("Type :");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(type, c);
        JPanel radio = new JPanel();
        GridBagConstraints radGBC = new GridBagConstraints();
        radio.setLayout(new GridBagLayout());
        radGBC.gridx = 0;
        radGBC.gridy = 0;
        rdLivre=new JRadioButton("Livre");
        radio.add(rdLivre,radGBC);
        radGBC.gridx = 1;
        rdVideo= new JRadioButton("Vidéo");
        radio.add(rdVideo,radGBC);
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdLivre);
        btnGroup.add(rdVideo);

        c.gridx = 1;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(10, 0, 10, 0);
        me.add(radio,c);

        JLabel duree = new JLabel("Durée :");
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(duree, c);
        dureeT = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(dureeT, c);

        JLabel realisateur = new JLabel("Réalisateur :");
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(realisateur, c);
        realisateurJComboBox = new JComboBox<>(Main.realisateurs.toArray(new Realisateur[0]));
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(realisateurJComboBox, c);

        JLabel isbn = new JLabel("ISBN :");
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(isbn, c);
        isbnT = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(isbnT, c);

        JLabel nbPage = new JLabel("NbPage :");
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(nbPage, c);
        nbPages = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(nbPages, c);

        JLabel auteur = new JLabel("Auteur :");
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(auteur, c);
        auteurJComboBox = new JComboBox<>(Main.auteurs.toArray(new Auteur[0]));

        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(auteurJComboBox, c);




        duree.setVisible(false);
        dureeT.setVisible(false);
        realisateur.setVisible(false);
        realisateurJComboBox.setVisible(false);

        isbn.setVisible(false);
        isbnT.setVisible(false);
        nbPage.setVisible(false);
        nbPages.setVisible(false);
        auteur.setVisible(false);
        auteurJComboBox.setVisible(false);



        rdLivre.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    isbn.setVisible(true);
                    isbnT.setVisible(true);
                    nbPage.setVisible(true);
                    nbPages.setVisible(true);
                    auteur.setVisible(true);
                    auteurJComboBox.setVisible(true);


                } else {//checkbox has been deselected
                    isbn.setVisible(false);
                    isbnT.setVisible(false);
                    nbPage.setVisible(false);
                    nbPages.setVisible(false);
                   auteur.setVisible(false);
                    auteurJComboBox.setVisible(false);
                };
            }
        });
        rdVideo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    duree.setVisible(true);
                    dureeT.setVisible(true);
                    realisateur.setVisible(true);
                    realisateurJComboBox.setVisible(true);
                } else {//checkbox has been deselected
                    duree.setVisible(false);
                    dureeT.setVisible(false);
                    realisateur.setVisible(false);
                    realisateurJComboBox.setVisible(false);
                };
            }
        });


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
        c.gridy = 7;
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
        model = new LivreVideoTableModel();
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
            if(rdLivre.isSelected()) {
                if (update == null) {
                    Livre l = new Livre(referenceT.getText(), desingationT.getText(), Double.parseDouble(prixT.getText()), isbnT.getText(), Integer.parseInt(nbPages.getText()), (Auteur) auteurJComboBox.getSelectedItem());
                    Main.articles.add(l);
                } else {
                    update.setReference(referenceT.getText());
                    update.setDesignation(desingationT.getText());
                    update.setPrix(Double.parseDouble(prixT.getText()));
                    ((Livre) update).setIsbn(isbnT.getText());
                    ((Livre) update).setNbPages(Integer.parseInt(nbPages.getText()));
                    ((Livre) update).setAuteur((Auteur) auteurJComboBox.getSelectedItem());
                }
            }
            else if(rdVideo.isSelected()){
                if (update == null) {
                    Video v = new Video(referenceT.getText(), desingationT.getText(), Double.parseDouble(prixT.getText()), Integer.parseInt(dureeT.getText()), (Realisateur) realisateurJComboBox.getSelectedItem());
                    Main.articles.add(v);
                }
                else {
                    update.setReference(referenceT.getText());
                    update.setDesignation(desingationT.getText());
                    update.setPrix(Double.parseDouble(prixT.getText()));
                    ((Video) update).setDuree(Integer.parseInt(dureeT.getText()));
                    ((Video) update).setRealisateur((Realisateur) realisateurJComboBox.getSelectedItem());
                }
                model = new LivreVideoTableModel();
                tab.setModel(model);
                fenetre.revalidate();
                fenetre.repaint();
            }

            model = new LivreVideoTableModel();
            tab.setModel(model);
            fenetre.revalidate();
            fenetre.repaint();
        }
        if (e.getSource() == annuler) {
            referenceT.setText("");
            desingationT.setText("");
            prixT.setText("");
            dureeT.setText("");
            update = null;
        }
        if (e.getSource() == supprimer) {
            String text = "Voulez-vous vraiment supprimer les sélections ?";
            String title = "Confirmation";
            int optionType = JOptionPane.YES_NO_OPTION;
            int result = JOptionPane.showConfirmDialog(null, text, title, optionType);
            if (result == JOptionPane.OK_OPTION) {
                int index = 0;
                for (int i = 0; i < Main.articles.size(); i++) {
                    if (Boolean.parseBoolean(model.getValueAt(index, 8).toString()) == true) {
                        Main.articles.remove(i);
                        i--;
                    }
                    index++;
                }
                model = new LivreVideoTableModel();
                tab.setModel(model);
                fenetre.revalidate();
                fenetre.repaint();
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) { // to detect doble click events
            if(update instanceof Livre){
                JTable target = (JTable) e.getSource();
                update = (Article) Main.articles.get(target.getSelectedRow());
                referenceT.setText(update.getReference());
                desingationT.setText(update.getDesignation());
                prixT.setText(update.getPrix()+"");
                isbnT.setText(((Livre) update).getIsbn());
                nbPages.setText(((Livre) update).getNbPages()+"");
            }else {
                JTable target = (JTable) e.getSource();
                update = (Article) Main.articles.get(target.getSelectedRow());
                referenceT.setText(update.getReference());
                desingationT.setText(update.getDesignation());
                prixT.setText(update.getPrix()+"");
                dureeT.setText(((Video) update).getDuree()+"");
            }

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
