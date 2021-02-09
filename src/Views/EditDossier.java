package Views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import com.toedter.calendar.JDateChooser;

import Helpers.PartieActionListener;
import Helpers.TahsilUtils;
import Models.Dossier;

import java.util.Date;
import java.sql.Timestamp;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditDossier extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6041291218096182734L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumDossier;
	private JTextField txtDossOrig;
	private JTextField txtNumVerdict;
	private JTextField txtMntVerdict;
	private JTextField txtAutreIndem;
	private JPanel partie1Panel;
	private JPanel partie2Panel;
	private JCheckBox chkPlaidoirie;
	private JComboBox<String> yearsCombo;
	private JPanel infoDossPanel;
	private JPanel partiesPanel;
	private JButton btnCancel;
	private JButton btnUpdate;
	private JDateChooser dteVerdict;
	private JLabel lblIconUser1;
	private JLabel lblPartie1Nom;
	private JLabel lblPartie1Prenom;
	private JLabel lblPartie1Role;
	private JCheckBox chkPartie1IsDebiteur;
	private JButton editPartie1Btn;
	private JLabel lblNewLabel;
	private JLabel lblPartie2Nom;
	private JLabel lblPartie2Prenom;
	private JLabel lblPartie2Role;
	private JCheckBox chkPartie2IsDebiteur;
	private JButton editPartie2Btn;
	private JLabel lblPartie1Ville;
	private JLabel lblPartie2Ville;
	private Dossier currentDossier;
	private String dossierOrig;
	private int dossier_id;
	TahsilUtils utils = new TahsilUtils();
	private JLabel numTerrainlbl;
	private JTextField numTerraintxt;
	private JLabel numMarsoumlbl;
	private JTextField numMarsoumtxt;
	private JLabel numJaridalbl;
	private JTextField numJRtxt;
	private JLabel dateJaridalbl;
	private JDateChooser datejaridaChooser;
	private JDateChooser dateIsdarChooser;
	private JLabel dateisdar;

	public EditDossier(Dossier dos) {
		dossier_id = dos.getId();
		dossierOrig = dos.getYear()+"/"+dos.getNumDossier();

		setTitle("تغيير معلومات الملف");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(true);
		setBounds(100, 100, 901, 479);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		partiesPanel = new JPanel();
		partiesPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\u0623\u0637\u0631\u0627\u0641 \u0627\u0644\u0645\u0644\u0641", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		infoDossPanel = new JPanel();
		
		btnCancel = new JButton("إلغاء");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 16));
		
		btnUpdate = new JButton("تغيير");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controllers.Dossier dossController = new Controllers.Dossier();

				currentDossier = new Dossier(
							utils.VarParseInt(txtNumDossier.getText()), 
							utils.VarParseInt(yearsCombo.getSelectedItem().toString()), 
							txtDossOrig.getText(), 
							new Timestamp(new Date(Calendar.getInstance().getTimeInMillis()).getTime()), 
							utils.VarParseInt(txtNumVerdict.getText()), 
							(Date) dteVerdict.getDate(), 
							utils.VarParseDouble(txtMntVerdict.getText()), 
							0.0, 
							chkPlaidoirie.isSelected(), 
							utils.VarParseDouble(txtAutreIndem.getText()), 
							0.0,
							numTerraintxt.getText(), 
							numMarsoumtxt.getText(), 
							numJRtxt.getText(), 
							(Date) datejaridaChooser.getDate(),
							(Date) dateIsdarChooser.getDate()
							
						);

					//*************** OptionPan dyal la Confirmation+OptionPane dyal le Succes ou l'Erreur
					if (JOptionPane.showConfirmDialog(getThis(), "هل تريد فعلاً تغير معلومات هذا الملف؟", " تغير معلومات الملف",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						dossController.update(currentDossier, getThis(), dossierOrig, dossier_id);
						
					}
					
			}
		});
		
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 16));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(infoDossPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(btnCancel)
							.addGap(18)
							.addComponent(btnUpdate))
						.addComponent(partiesPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addComponent(infoDossPanel, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(partiesPanel, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnUpdate))
					.addContainerGap())
		);
		
		partie1Panel = new JPanel();
		partie1Panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		partie2Panel = new JPanel();
		partie2Panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		GroupLayout gl_partiesPanel = new GroupLayout(partiesPanel);
		gl_partiesPanel.setHorizontalGroup(
			gl_partiesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_partiesPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_partiesPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(partie2Panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
						.addComponent(partie1Panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_partiesPanel.setVerticalGroup(
			gl_partiesPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_partiesPanel.createSequentialGroup()
					.addComponent(partie1Panel, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(partie2Panel, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addGap(13))
		);
		partie2Panel.setLayout(new MigLayout("", "[grow,center][::80px,grow,center][::90px,grow,right][grow,right][grow,right][grow,right][::40px,grow,center]", "[grow,fill]"));
		
		editPartie2Btn = new JButton("");
		editPartie2Btn.addActionListener(new PartieActionListener(dos,1, getThis(), null, null, "updateDialog"));		
		editPartie2Btn.setBackground(new Color(238, 238, 238));
		editPartie2Btn.setIcon(new ImageIcon(Home.class.getResource("/ressources/icons/edit.png")));
		editPartie2Btn.setBorder(BorderFactory.createEmptyBorder());
		editPartie2Btn.setBounds(459, 36, 25, 25);
		partie2Panel.add(editPartie2Btn, "cell 0 0");
		
		chkPartie2IsDebiteur = new JCheckBox("مدين");
		chkPartie2IsDebiteur.setFont(new Font("Dialog", Font.BOLD, 14));
		chkPartie2IsDebiteur.setHorizontalTextPosition(SwingConstants.LEFT);
		if (dos.getParties().get(1).getDebiteur() == 1) {
			chkPartie2IsDebiteur.setSelected(true);
		}
		chkPartie2IsDebiteur.setEnabled(false);
		partie2Panel.add(chkPartie2IsDebiteur, "cell 1 0");
		
		lblPartie2Ville = new JLabel(dos.getParties().get(1).getVille());
		partie2Panel.add(lblPartie2Ville, "cell 2 0");
		
		lblPartie2Role = new JLabel(dos.getParties().get(1).getRole_partie());
		lblPartie2Role.setFont(new Font("Dialog", Font.BOLD, 14));
		partie2Panel.add(lblPartie2Role, "cell 3 0");
		
		if (dos.getParties().get(1).getPrenom().length() > 15) {
			lblPartie2Prenom = new JLabel(dos.getParties().get(1).getPrenom().substring(0, Math.min(dos.getParties().get(1).getPrenom().length(), 15))+"...");

		}else{
			lblPartie2Prenom = new JLabel(dos.getParties().get(1).getPrenom());

		}
		
		lblPartie2Prenom.setFont(new Font("Dialog", Font.BOLD, 14));
		partie2Panel.add(lblPartie2Prenom, "cell 4 0");
		
		if (dos.getParties().get(1).getNom().length() > 15) {
			lblPartie2Nom = new JLabel(dos.getParties().get(1).getNom().substring(0, Math.min(dos.getParties().get(1).getNom().length(), 15))+"...");

		}else{
			lblPartie2Nom = new JLabel(dos.getParties().get(1).getNom());

		}
		lblPartie2Nom.setFont(new Font("Dialog", Font.BOLD, 14));
		partie2Panel.add(lblPartie2Nom, "cell 5 0");
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(EditDossier.class.getResource("/ressources/icons/user.png")));
		partie2Panel.add(lblNewLabel, "cell 6 0");
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		partie1Panel.setLayout(new MigLayout("", "[grow,center][::80px,grow,center][::90px,grow,right][grow,right][grow,right][grow,right][::40px,grow,center]", "[grow,fill]"));
		
		editPartie1Btn = new JButton("");
		editPartie1Btn.setBackground(new Color(238, 238, 238));
		editPartie1Btn.setIcon(new ImageIcon(Home.class.getResource("/ressources/icons/edit.png")));
		editPartie1Btn.setBorder(BorderFactory.createEmptyBorder());
		editPartie1Btn.setBounds(459, 36, 25, 25);
		editPartie1Btn.addActionListener(new PartieActionListener(dos,0, getThis(), null, partie1Panel, "updateDialog"));
		partie1Panel.add(editPartie1Btn, "cell 0 0");
		
		chkPartie1IsDebiteur = new JCheckBox("مدين");
		chkPartie1IsDebiteur.setFont(new Font("Dialog", Font.BOLD, 14));
		chkPartie1IsDebiteur.setHorizontalTextPosition(SwingConstants.LEFT);
		if (dos.getParties().get(0).getDebiteur() == 1) {
			chkPartie1IsDebiteur.setSelected(true);
		}
		chkPartie1IsDebiteur.setEnabled(false);
		partie1Panel.add(chkPartie1IsDebiteur, "cell 1 0");
		
		lblPartie1Ville = new JLabel(dos.getParties().get(0).getVille());
		partie1Panel.add(lblPartie1Ville, "cell 2 0");
		
		lblPartie1Role = new JLabel(dos.getParties().get(0).getRole_partie());
		lblPartie1Role.setFont(new Font("Dialog", Font.BOLD, 14));
		partie1Panel.add(lblPartie1Role, "cell 3 0");
		
		if (dos.getParties().get(0).getPrenom().length() > 15) {
			lblPartie1Prenom = new JLabel(dos.getParties().get(0).getPrenom().substring(0, Math.min(dos.getParties().get(0).getPrenom().length(), 15))+"...");

		}else{
			lblPartie1Prenom = new JLabel(dos.getParties().get(0).getPrenom());

		}
		lblPartie1Prenom.setFont(new Font("Dialog", Font.BOLD, 14));
		partie1Panel.add(lblPartie1Prenom, "cell 4 0");
		
		if (dos.getParties().get(0).getNom().length() > 15) {
			lblPartie1Nom = new JLabel(dos.getParties().get(0).getNom().substring(0, Math.min(dos.getParties().get(0).getNom().length(), 15))+"...");
		}else{
			lblPartie1Nom = new JLabel(dos.getParties().get(0).getNom());
		}
		lblPartie1Nom.setFont(new Font("Dialog", Font.BOLD, 14));
		partie1Panel.add(lblPartie1Nom, "cell 5 0");
		
		lblIconUser1 = new JLabel("");
		lblIconUser1.setIcon(new ImageIcon(EditDossier.class.getResource("/ressources/icons/user.png")));
		partie1Panel.add(lblIconUser1, "cell 6 0");
		partiesPanel.setLayout(gl_partiesPanel);
		infoDossPanel.setLayout(new MigLayout("", "[grow,right][grow,right][grow,right][grow,right][grow,right][grow,right][::90px,grow,right][grow,right]", "[20px:25px:25px][10px:10px:10px][20px:25px:25px,grow][10px:10px:10px][20px:25px:25px,grow][][20px:25px:25px,grow]"));
		
		chkPlaidoirie = new JCheckBox("حقوق المرافعة");
		chkPlaidoirie.setHorizontalTextPosition(SwingConstants.LEFT);
		if (dos.getDroitPlaidoirie() > 0) {
			chkPlaidoirie.setSelected(true);
		}
		infoDossPanel.add(chkPlaidoirie, "cell 0 0,growy");
		
		txtDossOrig = new JTextField();
		txtDossOrig.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtDossOrig.setHorizontalAlignment(SwingConstants.CENTER);
		txtDossOrig.setText(dos.getNumDossierOrig());
		infoDossPanel.add(txtDossOrig, "cell 2 0,grow");
		txtDossOrig.setColumns(10);
		
		JLabel lblNumDossOrig = new JLabel("رقم م.أ");
		lblNumDossOrig.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(lblNumDossOrig, "cell 3 0,alignx left,aligny center");
		
		yearsCombo = new JComboBox<String>();
		String listValues[] = new String[5];
		int x = 0;
		for (int i = 0; i < 5; i++) {
			String s = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-x);
			listValues[i] = s;
			x++;
		}
		yearsCombo.setModel(new DefaultComboBoxModel<String>(listValues));
		for (int i = 0; i < listValues.length; i++) {
			if (listValues[i].matches(Integer.toString(dos.getYear()))) {
				yearsCombo.setSelectedIndex(i);
			}
		}
		yearsCombo.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(yearsCombo, "cell 4 0,alignx right");
		
		JLabel lblAnneeDoss = new JLabel("السنة");
		lblAnneeDoss.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(lblAnneeDoss, "cell 5 0,alignx left,aligny center");
		
		txtNumDossier = new JTextField();
		txtNumDossier.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtNumDossier.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumDossier.setText(Integer.toString(dos.getNumDossier()));
		infoDossPanel.add(txtNumDossier, "cell 6 0,grow");
		txtNumDossier.setColumns(10);
		
		JLabel lblNumDoss = new JLabel("رقم م.ت");
		lblNumDoss.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(lblNumDoss, "cell 7 0,alignx right,aligny center");
		
		txtAutreIndem = new JTextField();
		txtAutreIndem.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtAutreIndem.setHorizontalAlignment(SwingConstants.CENTER);
		txtAutreIndem.setText(Double.toString(dos.getAutreCharge()));
		infoDossPanel.add(txtAutreIndem, "cell 0 2,alignx right,growy");
		txtAutreIndem.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("صوائر أخرى");
		lblNewLabel_6.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(lblNewLabel_6, "cell 1 2,alignx left,aligny center");
		
		txtMntVerdict = new JTextField();
		txtMntVerdict.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtMntVerdict.setHorizontalAlignment(SwingConstants.CENTER);
		txtMntVerdict.setText(Double.toString(dos.getMontantVerdict()));
		infoDossPanel.add(txtMntVerdict, "cell 2 2,grow");
		txtMntVerdict.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("مبلغ الحكم");
		lblNewLabel_5.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(lblNewLabel_5, "cell 3 2,alignx left,aligny center");
		
		dteVerdict = new JDateChooser();
		dteVerdict.setDateFormatString("yyyy/MM/dd");
		dteVerdict.setDate(dos.getDateVerdict());
		infoDossPanel.add(dteVerdict, "cell 4 2,grow");
		
		JLabel lblDateVerdict = new JLabel("تاريخ الحكم");
		lblDateVerdict.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(lblDateVerdict, "cell 5 2,alignx left,aligny center");
		
		txtNumVerdict = new JTextField();
		txtNumVerdict.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtNumVerdict.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumVerdict.setText(Integer.toString(dos.getNumVerdict()));
		infoDossPanel.add(txtNumVerdict, "cell 6 2,grow");
		txtNumVerdict.setColumns(10);
		
		JLabel lblNumVerdict = new JLabel("رقم الحكم");
		lblNumVerdict.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(lblNumVerdict, "cell 7 2,alignx right,aligny center");
		
		datejaridaChooser = new JDateChooser();
		datejaridaChooser.setDateFormatString("yyyy/MM/dd");
		datejaridaChooser.setDate(dos.getDateJarida());
		infoDossPanel.add(datejaridaChooser, "cell 0 4,grow");
		
		dateJaridalbl = new JLabel("تاريخ ج.ر");
		dateJaridalbl.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(dateJaridalbl, "cell 1 4,alignx left");
		
		numJRtxt = new JTextField();
		numJRtxt.setText("0.0");
		numJRtxt.setHorizontalAlignment(SwingConstants.CENTER);
		numJRtxt.setFont(new Font("Dialog", Font.PLAIN, 13));
		numJRtxt.setText(dos.getNumJarida());
		numJRtxt.setColumns(10);
		infoDossPanel.add(numJRtxt, "cell 2 4,grow");
		
		numJaridalbl = new JLabel("رقم ج.ر");
		numJaridalbl.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(numJaridalbl, "cell 3 4,alignx left");
		
		numMarsoumtxt = new JTextField();
		numMarsoumtxt.setText("0");
		numMarsoumtxt.setHorizontalAlignment(SwingConstants.CENTER);
		numMarsoumtxt.setFont(new Font("Dialog", Font.PLAIN, 13));
		numMarsoumtxt.setText(dos.getNumMarsoum());
		numMarsoumtxt.setColumns(10);
		infoDossPanel.add(numMarsoumtxt, "cell 4 4,grow");
		
		numMarsoumlbl = new JLabel("رقم المرسوم");
		numMarsoumlbl.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(numMarsoumlbl, "cell 5 4,alignx left");
		
		numTerraintxt = new JTextField();
		numTerraintxt.setText("0");
		numTerraintxt.setHorizontalAlignment(SwingConstants.CENTER);
		numTerraintxt.setFont(new Font("Dialog", Font.PLAIN, 13));
		numTerraintxt.setText(dos.getNumTerrain());
		numTerraintxt.setColumns(10);
		infoDossPanel.add(numTerraintxt, "cell 6 4,grow");
		
		numTerrainlbl = new JLabel("رقم القطعة");
		numTerrainlbl.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(numTerrainlbl, "cell 7 4");
		
		dateIsdarChooser = new JDateChooser();
		dateIsdarChooser.setDateFormatString("yyyy/MM/dd");
		dateIsdarChooser.setDate(dos.getDateIsdar());
		infoDossPanel.add(dateIsdarChooser, "cell 2 6,grow");
		
		dateisdar = new JLabel("تاريخ الإصدار");
		dateisdar.setFont(new Font("Dialog", Font.BOLD, 14));
		infoDossPanel.add(dateisdar, "cell 3 6");
		contentPanel.setLayout(gl_contentPanel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(Box.createHorizontalGlue());
		setJMenuBar(menuBar);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnEdit.add(mntmQuit);
	}
	
	public JDialog getThis(){
		return this;
	}
}
