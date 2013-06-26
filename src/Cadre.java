import javax.swing.*;
import java.awt.*;


public class Cadre {
	public Cadre(){}
	private JTextArea Texte;

	public JPanel CreerCadre_1(String titre, int width, int height){
		JPanel temp = new JPanel();
        temp.setBackground(Color.white);
        temp.setPreferredSize(new Dimension(width,height));
        temp.setBorder(BorderFactory.createTitledBorder(titre));
        Texte = new JTextArea();
        Texte.setEditable(false);
        temp.add(Texte);
        temp.setVisible(true);
        return temp;
	}

    public JPanel CreerCadre_scrollpane(String titre, int width, int height){
		JPanel temp = new JPanel();
        temp.setBackground(Color.white);
        temp.setPreferredSize(new Dimension(width,height));
        temp.setBorder(BorderFactory.createTitledBorder(titre));
        Texte = new JTextArea(29,26);
        Texte.setEditable(false);
        temp.add(new JScrollPane(Texte));
        temp.setVisible(true);
        return temp;
	}
	
	public JTextArea getTexte() {
		return Texte;
	}
}
