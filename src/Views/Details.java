package Views;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

import Helpers.DossierActionListener;
import Helpers.UpdateDocxFile;
import Models.Dossier;

import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Details extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumDoss;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmQuitter;
	private JPanel panel;
	private JLabel lblDosTahsil;
	private JLabel lblValNumDoss;
	private JLabel lblDossOrig;
	private JLabel lblValDossOrig;
	private JLabel lblTotal;
	private JLabel lblValTotal;
	private JButton btnIchaar;
	private JButton btnFind;
	private Dossier currentDossier;
	private JButton deletebtn;
	private JButton amrTanfidiBtn;
	private JButton indarBtn;
	private JButton editBtn;
	private JButton inabaBtn;
	private SimpleDateFormat dteFormat = new SimpleDateFormat("yyyy/MM/dd");
	private DecimalFormat df = new DecimalFormat("#.00");

	/**
	 * Create the JDialog.
	 */
	public Details(Dossier dos) {
		setTitle("تدبير الملفات");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(true);

		currentDossier = dos;
		setBounds(100, 100, 794, 306);
		
		menuBar = new JMenuBar();
		menuBar.add(Box.createHorizontalGlue());
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Edit");
		menuBar.add(mnNewMenu);
		
		mntmQuitter = new JMenuItem("Quitter");
		mnNewMenu.add(mntmQuitter);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		lblDosTahsil = new JLabel("رقم ملف التحصيل :");
		lblDosTahsil.setFont(new Font("Dialog", Font.BOLD, 14));
		
		lblValNumDoss = new JLabel(Integer.toString(dos.getYear())+"/"+Integer.toString(dos.getNumDossier()));
		lblValNumDoss.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblValNumDoss.setForeground(Color.RED);
		
		lblDossOrig = new JLabel("رقم الملف اﻷصلي :");
		lblDossOrig.setFont(new Font("Dialog", Font.BOLD, 14));
		
		lblValDossOrig = new JLabel(dos.getNumDossierOrig());
		lblValDossOrig.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblValDossOrig.setForeground(Color.RED);
		
		lblTotal = new JLabel("المبلغ الاجمالي :");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 14));
		
		lblValTotal = new JLabel(Double.toString(dos.getTotal())+" درهم");
		lblValTotal.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		lblValTotal.setForeground(Color.RED);
		
		btnIchaar = new JButton("اشعار و شهادة التسليم");
		btnIchaar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				List<String> lstNumDossCondition = new LinkedList<String>();
				List<String> lstYearDossCondition = new LinkedList<String>();
				List<List<String>> conditions = new LinkedList<List<String>>();
				/******************************************/
				lstNumDossCondition.add("num_dossier");
				lstNumDossCondition.add(Integer.toString(currentDossier.getNumDossier()));
				/******************************************/
				/******************************************/
				lstYearDossCondition.add("annee_dossier");
				lstYearDossCondition.add(Integer.toString(currentDossier.getYear()));
				/******************************************/
				conditions.add(lstNumDossCondition);
				conditions.add(lstYearDossCondition);
				
				Dossier x = currentDossier.getDossiers(conditions, 1, true).get(0);
				
				/////////////////////////////////////////////////
				Map<String, String> params = new LinkedHashMap<String, String>();
				if (x.getParties().get(0).getPrenom().matches("--")) {
					x.getParties().get(0).setPrenom("");
				}
				params.put("Debiteur", x.getParties().get(0).getNom()+" "+x.getParties().get(0).getPrenom());
				params.put("NumDossTah",Integer.toString(x.getYear())+"/"+Integer.toString(x.getNumDossier()));
				params.put("MntIndem", df.format(x.getMontantIndemnisation()));
				if (x.getDroitPlaidoirie() == 0) {
					params.put("DroitPlaid", "0,00");
				}else{
					params.put("DroitPlaid", df.format(x.getDroitPlaidoirie()));
				}
				
				if (x.getAutreCharge() == 0) {
					params.put("AutreIndem", "0,00");
				}else{
					params.put("AutreIndem", df.format(x.getAutreCharge()));
				}
				params.put("NumVerd", Integer.toString(x.getNumVerdict()));
				params.put("DateVerd", dteFormat.format(x.getDateVerdict()));
				params.put("DateIsdar", dteFormat.format(x.getDateIsdar()));
				params.put("DossOrig", x.getNumDossierOrig());
				params.put("AddrDeb", x.getParties().get(0).getAdresse()+" "+x.getParties().get(0).getVille());
				params.put("Total", df.format(x.getTotal()));
				UpdateDocxFile udf = new UpdateDocxFile("ichaarTemplate.docx", "rsltIchaar"+System.currentTimeMillis()+".docx", params);
				udf.UpdateFile();
			}
		});
		
		amrTanfidiBtn = new JButton("الأمر التنفيذي");
		amrTanfidiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String docTemplate = "amrTanfidiTemplate.docx";
				String[] choices = { "الذي قضى بالاذن للمدعي بحيازة... ","الذي قضى على المدعي بنزع... ", "الذي قضى باﻷشهاد..." };
			    String input = (String) JOptionPane.showInputDialog(null, "إختر الجملة التي تريد ادراجها بالإنذار؟",
			        "إختر الجملة التي تريد ادراجها بالإنذار؟", JOptionPane.QUESTION_MESSAGE, null, // Use
			                                                                        // default                                                          // icon
			        choices, // Array of choices
			        choices[1]); // Initial choice
			    
			    if (input != null) {
			    	
			    	List<String> lstNumDossCondition = new LinkedList<String>();
					List<String> lstYearDossCondition = new LinkedList<String>();
					List<List<String>> conditions = new LinkedList<List<String>>();
					/******************************************/
					lstNumDossCondition.add("num_dossier");
					lstNumDossCondition.add(Integer.toString(currentDossier.getNumDossier()));
					/******************************************/
					/******************************************/
					lstYearDossCondition.add("annee_dossier");
					lstYearDossCondition.add(Integer.toString(currentDossier.getYear()));
					/******************************************/
					conditions.add(lstNumDossCondition);
					conditions.add(lstYearDossCondition);
					
					Dossier x = currentDossier.getDossiers(conditions, 2, false).get(0);
					Map<String, String> params = new LinkedHashMap<String, String>();
					
					String strtoinsert = "الذي قضى بالاشهاد على المدعي بسحب دعواه مع تحميله الصائر الذي يبلغ بالحروف: ............ دراهم";
			    	if (input.matches("الذي قضى على المدعي بنزع... ")) {
			    		strtoinsert = "الذي قضى على المدعي بنزع ملكية المدعى عليه مقابل تعوييض قدره "+df.format(x.getMontantVerdict())+"درهم مع تحميله صائر الدعوى الذي يبلغ بالحروف: ........... دراهم";
					}
					
			    	if (input.matches("الذي قضى بالاذن للمدعي بحيازة... ")) {
			    		docTemplate = "amrTanfidiIstiaajaliTemplate.docx";
			    	}
			    	params.put("Phrase", strtoinsert);
					/////////////////////////////////////////////////
		
					if (x.getParties().get(0).getRole_partie().matches("مدعي")) {
						if(x.getParties().get(0).getPrenom().matches("--")){
							x.getParties().get(0).setPrenom("");
						}else if(x.getParties().get(1).getPrenom().matches("--")){
								x.getParties().get(1).setPrenom("");
						}
						params.put("Md1", x.getParties().get(0).getNom()+" "+x.getParties().get(0).getPrenom());
						params.put("AdressM1", x.getParties().get(0).getAdresse()+" "+x.getParties().get(0).getVille());
						params.put("Md2", x.getParties().get(1).getNom()+" "+x.getParties().get(1).getPrenom());
						params.put("AdressM2", x.getParties().get(1).getAdresse()+" "+x.getParties().get(1).getVille());
					}else{
						if(x.getParties().get(0).getPrenom().matches("--")){
							x.getParties().get(0).setPrenom("");
							x.getParties().get(0).setAdresse(x.getParties().get(0).getAdresse()+x.getParties().get(0).getVille());
						}else if(x.getParties().get(1).getPrenom().matches("--")){
								x.getParties().get(1).setPrenom("");
								x.getParties().get(1).setAdresse(x.getParties().get(1).getAdresse()+x.getParties().get(1).getVille());
						}
						params.put("Md1", x.getParties().get(1).getNom()+" "+x.getParties().get(1).getPrenom());
						params.put("AdressM1", x.getParties().get(1).getAdresse()+" "+x.getParties().get(1).getVille());
						params.put("Md2", x.getParties().get(0).getNom()+" "+x.getParties().get(0).getPrenom());
						params.put("AdressM2", x.getParties().get(0).getAdresse()+" "+x.getParties().get(0).getVille());
					}
					
					params.put("NumDossTah",Integer.toString(x.getYear())+"/"+Integer.toString(x.getNumDossier()));
					params.put("MntIndem", df.format(x.getMontantIndemnisation()));
					if (x.getDroitPlaidoirie() == 0) {
						params.put("DroitPlaid", "0,00");
					}else{
						params.put("DroitPlaid", df.format(x.getDroitPlaidoirie()));
					}
					
					if (x.getAutreCharge() == 0) {
						params.put("AutreIndem", "0,00");
					}else{
						params.put("AutreIndem", df.format(x.getAutreCharge()));
					}

					params.put("NumVerd", Integer.toString(x.getNumVerdict()));
					params.put("MntVerd", df.format(x.getMontantVerdict()));
					params.put("DateVerd", dteFormat.format(x.getDateVerdict()));
					params.put("DossOrig", x.getNumDossierOrig());
					params.put("AddrDeb", x.getParties().get(0).getAdresse());
					params.put("Total", df.format(x.getTotal()));
					
					UpdateDocxFile udf = new UpdateDocxFile(docTemplate, "rsltAmr"+System.currentTimeMillis()+".docx", params);
					udf.UpdateFile();
		
				}
					}
		});
		
		indarBtn = new JButton("الإنذار و شهادة التسليم");
		indarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> lstNumDossCondition = new LinkedList<String>();
				List<String> lstYearDossCondition = new LinkedList<String>();
				List<List<String>> conditions = new LinkedList<List<String>>();
				//System.out.println("INDAR : "+currentDossier.toString());
				/******************************************/
				lstNumDossCondition.add("num_dossier");
				lstNumDossCondition.add(Integer.toString(currentDossier.getNumDossier()));
				/******************************************/
				/******************************************/
				lstYearDossCondition.add("annee_dossier");
				lstYearDossCondition.add(Integer.toString(currentDossier.getYear()));
				/******************************************/
				conditions.add(lstNumDossCondition);
				conditions.add(lstYearDossCondition);
				
				Dossier x = currentDossier.getDossiers(conditions, 1, true).get(0);
				//System.out.println("getDossier"+x.toString());
				/////////////////////////////////////////////////
				Map<String, String> params = new LinkedHashMap<String, String>();
				if (x.getParties().get(0).getPrenom().matches("--")) {
					x.getParties().get(0).setPrenom("");
				}
				params.put("Debiteur", x.getParties().get(0).getNom()+" "+x.getParties().get(0).getPrenom());
				params.put("NumDossTah",Integer.toString(x.getYear())+"/"+Integer.toString(x.getNumDossier()));
				params.put("MntIndem", df.format(x.getMontantIndemnisation()));
				if (x.getDroitPlaidoirie() == 0) {
					params.put("DroitPlaid", "0,00");
				}else{
					params.put("DroitPlaid", df.format(x.getDroitPlaidoirie()));
				}
				
				if (x.getAutreCharge() == 0) {
					params.put("AutreIndem", "0,00");
				}else{
					params.put("AutreIndem", df.format(x.getAutreCharge()));
				}
				params.put("NumVerd", Integer.toString(x.getNumVerdict()));
				params.put("DateVerd", dteFormat.format(x.getDateVerdict()));
				if (x.getDateIsdar() != null) {
					params.put("DateIsdar", dteFormat.format(x.getDateIsdar()));
				}
				params.put("DossOrig", x.getNumDossierOrig());
				params.put("AddrDeb", x.getParties().get(0).getAdresse()+" "+x.getParties().get(0).getVille());
				params.put("Total", df.format(x.getTotal()));
				UpdateDocxFile udf = new UpdateDocxFile("indarTemplate.docx", "rsltIchaar"+System.currentTimeMillis()+".docx", params);
				udf.UpdateFile();
			}
		});
		
		editBtn = new JButton("");
		editBtn.addActionListener(new DossierActionListener(currentDossier, getThis(),null,null, "updateDialog"));
		editBtn.setBackground(new Color(238, 238, 238));
		editBtn.setIcon(new ImageIcon(Home.class.getResource("/ressources/icons/edit.png")));
		editBtn.setBorder(BorderFactory.createEmptyBorder());
		editBtn.setBounds(459, 36, 25, 25);
		
		deletebtn = new JButton("");
		deletebtn.setBackground(new Color(238, 238, 238));
		deletebtn.setIcon(new ImageIcon(Home.class.getResource("/ressources/icons/trash.png")));
		deletebtn.setBorder(BorderFactory.createEmptyBorder());
		deletebtn.setBounds(459, 36, 25, 25);		
		List<JLabel> jpJLabels = new LinkedList<JLabel>();
		List<JButton> jpJButtons = new LinkedList<JButton>();

		jpJLabels.add(lblValNumDoss);
		jpJLabels.add(lblValDossOrig);
		jpJLabels.add(lblValTotal);
		jpJButtons.add(btnIchaar);
		jpJButtons.add(amrTanfidiBtn);
		jpJButtons.add(indarBtn);
		jpJButtons.add(editBtn);
		jpJButtons.add(deletebtn);
		
		
		deletebtn.addActionListener(new DossierActionListener(currentDossier, getThis(),jpJLabels,jpJButtons, "delete"));
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		
		inabaBtn = new JButton("انابة");
		inabaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/////
				List<String> lstNumDossCondition = new LinkedList<String>();
				List<String> lstYearDossCondition = new LinkedList<String>();
				List<List<String>> conditions = new LinkedList<List<String>>();
				/******************************************/
				lstNumDossCondition.add("num_dossier");
				lstNumDossCondition.add(Integer.toString(currentDossier.getNumDossier()));
				/******************************************/
				/******************************************/
				lstYearDossCondition.add("annee_dossier");
				lstYearDossCondition.add(Integer.toString(currentDossier.getYear()));
				/******************************************/
				conditions.add(lstNumDossCondition);
				conditions.add(lstYearDossCondition);
				
				Dossier x = currentDossier.getDossiers(conditions, 1, true).get(0);
				
				/////////////////////////////////////////////////
				Map<String, String> params = new LinkedHashMap<String, String>();
				if (x.getParties().get(0).getPrenom().matches("--")) {
					x.getParties().get(0).setPrenom("");
				}
				params.put("Debiteur", x.getParties().get(0).getNom()+" "+x.getParties().get(0).getPrenom());
				params.put("NumDossTah",Integer.toString(x.getYear())+"/"+Integer.toString(x.getNumDossier()));
				String MntData = "";
				if(x.getAutreCharge() > 0){
					MntData += " + "+df.format(x.getAutreCharge());
				}
				if (x.getDroitPlaidoirie() > 0) {
					MntData += df.format(x.getDroitPlaidoirie());
				}
				MntData += " + "+df.format(x.getMontantIndemnisation());

				params.put("DATA", MntData);
				params.put("NumVerd", Integer.toString(x.getNumVerdict()));
				params.put("DateVerd", dteFormat.format(x.getDateVerdict()));
				params.put("DossOrig", x.getNumDossierOrig());
				params.put("AddrDeb", x.getParties().get(0).getAdresse()+" "+x.getParties().get(0).getVille());
				UpdateDocxFile udf = new UpdateDocxFile("inabaTemplate.docx", "rsltInaba"+System.currentTimeMillis()+".docx", params);
				udf.UpdateFile();
				
			}
		});
		
		if (dos.getNumDossier() != 0) {
			btnIchaar.setEnabled(true);
			amrTanfidiBtn.setEnabled(true);
			indarBtn.setEnabled(true);
			deletebtn.setEnabled(true);
			editBtn.setEnabled(true);
			inabaBtn.setEnabled(true);
		}else{
			btnIchaar.setEnabled(false);
			amrTanfidiBtn.setEnabled(false);
			indarBtn.setEnabled(false);
			deletebtn.setEnabled(false);
			editBtn.setEnabled(false);
			inabaBtn.setEnabled(false);
			
		}
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblValTotal)
							.addGap(18)
							.addComponent(lblTotal)
							.addGap(18)
							.addComponent(lblValDossOrig)
							.addGap(18)
							.addComponent(lblDossOrig)
							.addGap(18)
							.addComponent(lblValNumDoss)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDosTahsil)
							.addGap(9)))
					.addContainerGap())
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addComponent(deletebtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(editBtn)
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addComponent(inabaBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(indarBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(amrTanfidiBtn)
					.addGap(18)
					.addComponent(btnIchaar)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(editBtn)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblValDossOrig)
								.addComponent(lblDossOrig)
								.addComponent(lblValNumDoss)
								.addComponent(lblDosTahsil)
								.addComponent(lblTotal)
								.addComponent(lblValTotal))
							.addGap(43)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnIchaar)
								.addComponent(amrTanfidiBtn)
								.addComponent(indarBtn)
								.addComponent(inabaBtn)))
						.addComponent(deletebtn))
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JLabel lblNumDoss = new JLabel("رقم ملف التحصيل (الرقم/السنة)");
		lblNumDoss.setFont(new Font("Dialog", Font.BOLD, 14));
		
		txtNumDoss = new JTextField();
		txtNumDoss.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumDoss.setColumns(10);
		
		btnFind = new JButton("بحث");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNumDoss.getText().isEmpty()) {
					JOptionPane.showMessageDialog(getThis(), "المرجو إدخال الرقم الكامل للملف (الرقم/السنة)","خطأ",JOptionPane.ERROR_MESSAGE);
				}else{
					if (!txtNumDoss.getText().contains("/") || txtNumDoss.getText().split("/").length == 0) {
						JOptionPane.showMessageDialog(getThis(), " المرجو التحقق من المعطيات المدخلة (الرقم/السنة)","خطأ",JOptionPane.ERROR_MESSAGE);
					}else{
						Controllers.Dossier doscontrol = new Controllers.Dossier();
						Dossier tmpDos = doscontrol.getDossier(txtNumDoss.getText());

						currentDossier.setId(tmpDos.getId());
						currentDossier.setNumDossier(tmpDos.getNumDossier());
						currentDossier.setYear(tmpDos.getYear());
						
						if (tmpDos.getNumDossier() != 0) {
							lblValNumDoss.setText(Integer.toString(tmpDos.getYear())+"/"+Integer.toString(tmpDos.getNumDossier()));
							lblValDossOrig.setText(tmpDos.getNumDossierOrig());
							lblValTotal.setText(Double.toString(tmpDos.getTotal())+" درهم");
							btnIchaar.setEnabled(true);
							amrTanfidiBtn.setEnabled(true);
							indarBtn.setEnabled(true);
							inabaBtn.setEnabled(true);
							deletebtn.setEnabled(true);
							editBtn.setEnabled(true);
						}else{
							JOptionPane.showMessageDialog(getThis(), "الملف المطلوب غير موجود، المرجو التحقق من المعطيات المدخلة (الرقم/السنة)","خطأ",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(292, Short.MAX_VALUE)
					.addComponent(btnFind)
					.addGap(18)
					.addComponent(txtNumDoss, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNumDoss)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumDoss)
						.addComponent(txtNumDoss, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFind))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	
	private JDialog getThis(){
		return this;
	}
}
