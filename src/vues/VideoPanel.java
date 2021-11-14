package vues;

import classes.*;
import main.Main;
import models.ClientTableModel;
import models.VideoTableModel;
import utils.PasswordGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VideoPanel extends JPanel implements ActionListener, MouseListener {

    JButton annuler;
    JButton valider;
    JButton supprimer;
    JTextField desingationT;
    JTextField referenceT;
    JTextField prixT;
    JTextField dureeT;
    JComboBox<Realisateur> realisateurJComboBox;
    JFrame fenetre;

    VideoTableModel model;
    JTable tab;

    Video update;

    public VideoPanel(JFrame mafenetre) {
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

        JLabel duree = new JLabel("Durée :");
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(duree, c);
        dureeT = new JTextField();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(dureeT, c);

        JLabel realisateur = new JLabel("Réalisateur :");
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10, 100, 10, 0);
        me.add(realisateur, c);
        realisateurJComboBox = new JComboBox<Realisateur>();
        c.gridx = 1;
        c.insets = new Insets(10, 10, 10, 10);
        me.add(realisateurJComboBox, c);

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
        c.gridy = 5;
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
        model = new VideoTableModel();
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
            if (update == null) {
                Video v = new Video(referenceT.getText(), desingationT.getText(), Double.parseDouble(prixT.getText()), Integer.parseInt(dureeT.getText()));
                Main.videos.add(v);
            } else {
                update.setReference(referenceT.getText());
                update.setDesignation(desingationT.getText());
                update.setPrix(Double.parseDouble(prixT.getText()));
                update.setDuree(Integer.parseInt(dureeT.getText()));
            }

            model = new VideoTableModel();
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
                for (int i = 0; i < Main.videos.size(); i++) {
                    if (Boolean.parseBoolean(model.getValueAt(index, 4).toString()) == true) {
                        Main.videos.remove(i);
                        i--;
                    }
                    index++;
                }
                model = new VideoTableModel();
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
            update = (Video) Main.videos.get(target.getSelectedRow());
            referenceT.setText(update.getReference());
            desingationT.setText(update.getDesignation());
            prixT.setText(update.getPrix()+"");
            dureeT.setText(update.getDuree()+"");
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
