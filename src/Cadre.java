import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Cadre {
	public Cadre(){}
	
	/*
	public Cadre(String titre, int width, int height){
		JPanel temp = new JPanel();
        temp.setBackground(Color.white);
        temp.setPreferredSize(new Dimension(width,height));
        temp.setBorder(BorderFactory.createTitledBorder(titre));
        JTextArea texte = new JTextArea(); 
        texte.setEditable(false);
        temp.add(texte);
        temp.setVisible(true);
	}
	
	public Cadre(int width, int height, String titre){
		JPanel temp = new JPanel();
		temp.setBackground(Color.white);
        temp.setPreferredSize(new Dimension(width,height));
        JLabel title = new JLabel(titre);
        JTextArea texte = new JTextArea();
        texte.setEditable(false);
        temp.add(texte);
        temp.add(title);
        temp.setVisible(true);
	}
	*/
	
	public JPanel CreerCadre_1(String titre, int width, int height){
		JPanel temp = new JPanel();
        temp.setBackground(Color.white);
        temp.setPreferredSize(new Dimension(width,height));
        temp.setBorder(BorderFactory.createTitledBorder(titre));
        JTextArea texte = new JTextArea(); 
        texte.setEditable(false);
        temp.add(texte);
        temp.setVisible(true);
        return temp;
	}
	public JPanel CreerCadre_2(String titre, int width, int height){
		JPanel temp = new JPanel();
		temp.setBackground(Color.white);
        temp.setPreferredSize(new Dimension(width,height));
        JLabel title = new JLabel(titre);
        JTextArea texte = new JTextArea();
        texte.setEditable(false);
        temp.add(texte);
        temp.add(title);
        temp.setVisible(true);
        return temp;
	}
}
