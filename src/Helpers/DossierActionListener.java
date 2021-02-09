package Helpers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Models.Dossier;
import Views.Details;
import Views.EditDossier;

public class DossierActionListener implements ActionListener {
	
	private Dossier dos;
	private JFrame frame;
	private JDialog dialog;
	private String action;
	private JPanel jp;
	private JPanel parrentPanel;
	private List<JLabel>jpJLabels;
	private List<JButton>jpJButtons;

    public DossierActionListener(Dossier dos, JFrame frame,JPanel parrentPanel, JPanel jp, String action) {
    		this.dos = dos;
    		this.frame = frame;
    		this.action = action;
    		this.jp = jp;
    		this.parrentPanel = parrentPanel;
    }

    public DossierActionListener(Dossier dos, JDialog dialog,List<JLabel>jpJLabels,List<JButton>jpJButtons, String action) {
		this.dos = dos;
		this.dialog = dialog;
		this.jpJButtons = jpJButtons;
		this.jpJLabels = jpJLabels;
		this.action = action;
    }
    
    public void actionPerformed(ActionEvent e) {
    	if (action == "show") {
    		Details fd =new Details(dos);
    		fd.setLocationRelativeTo(frame);
    		fd.setVisible(true);
		}else if(action == "delete") {
			Object[] options = {"إلغاء","لا","نعم"};
			//IF DELETE is Called from Home
			if (frame != null) {
				int n = JOptionPane.showOptionDialog(frame,
							"سيتم حذف الملف رقم"+dos.getYear()+"/"+dos.getNumDossier()+" هل أنت متأكد ؟ ",
							"حذف الملف", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[2]
						);
				if(n == 2){
					if (dos.deleteDossier(dos.getId())) {
						JOptionPane.showMessageDialog(frame, "تم حذف الملف بنجاح","",JOptionPane.INFORMATION_MESSAGE);
						parrentPanel.remove(jp);
						parrentPanel.revalidate();
						parrentPanel.repaint();
					}else{
						JOptionPane.showMessageDialog(frame, "حدث خطأ خلال عملية الحذف، المرجوا المحاولة لاحقاً","",JOptionPane.ERROR_MESSAGE);

					}
				}
			//IF DELETE is Called from JDialog (tadbir almilafat)
			}else{
				int n = JOptionPane.showOptionDialog(dialog,
							"سيتم حذف الملف رقم"+dos.getYear()+"/"+dos.getNumDossier()+" هل أنت متأكد ؟ ",
							"حذف الملف", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,
							options,
							options[2]
						);
				//IF YES is choosen
				if(n == 2){
					if (dos.deleteDossier(dos.getId())) {
						JOptionPane.showMessageDialog(dialog, "تم حذف الملف بنجاح","",JOptionPane.INFORMATION_MESSAGE);
						jpJLabels.get(0).setText("0/0");
						jpJLabels.get(1).setText("0/0/0");
						jpJLabels.get(2).setText(" درهم 0.0");
						for (int i = 0; i < jpJButtons.size(); i++) {
							jpJButtons.get(i).setEnabled(false);
						}
					}else{
						JOptionPane.showMessageDialog(frame, "حدث خطأ خلال عملية الحذف، المرجوا المحاولة لاحقاً","",JOptionPane.ERROR_MESSAGE);

					}
				}
			}
		//SHOW UPDATE Dialog
		}else if(action == "updateDialog"){
			List<Object> params = new LinkedList<Object>();
			params.add(Integer.toString(dos.getNumDossier()));
			params.add(Integer.toString(dos.getYear()));
			
			Dossier x = dos.getDossier(params);
			EditDossier editor = new EditDossier(x);
			editor.setLocationRelativeTo(dialog);
			editor.setVisible(true);
		}
    	
    }
    
    
}