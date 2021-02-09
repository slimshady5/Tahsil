package Helpers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Models.Dossier;
import Models.Partie;
import Views.EditPartie;

public class PartieActionListener implements ActionListener{
	private Partie partie;
	private Dossier dos;
	private JDialog dialog;
	private String action;
	private int part;
	private JPanel parrentPanel;


    public PartieActionListener(Dossier dos,int part, JDialog dialog,List<JLabel>jpJLabels,JPanel parrentPanel, String action) {
    		this.dos = dos;
    		this.part = part;
    		this.dialog = dialog;
    		this.action = action;
    		this.parrentPanel = parrentPanel;
    }

	public void actionPerformed(ActionEvent e) {
		if (action.equals("updateDialog")) {

			Models.Partie prt = new Partie();
			Partie p = prt.getPartiesByDossId(dos.getId()).get(part);
			EditPartie editPartie = new EditPartie(p, parrentPanel);
			
			editPartie.setLocationRelativeTo(dialog);
			editPartie.setVisible(true);			
		}else if (action.equals("update")){
			if (partie.isDebiteur()) {
				partie.setDebiteur(1);
			}
			if (partie.isPhysiqueIsSelected()) {
				partie.setType("physique");
			}else{
				partie.setType("morale");
			}
		}
		
	}
}
