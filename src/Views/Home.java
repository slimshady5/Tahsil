package Views;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Controllers.User;
import Helpers.DossierActionListener;
import Helpers.Session;
import Models.Dossier;
import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JMenuItem;


public class Home extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -681951135967242370L;
	
	public JFrame getThis(){
		return this;
	}

	public Home() {
		setTitle("الرئيسية - تدبير ملفات التحصيل");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 604);
		getContentPane().setLayout(null);
		setResizable(true);
		getContentPane().setLayout(null);
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		
		JMenuBar homeMenuBar = new JMenuBar();
		homeMenuBar.add(Box.createHorizontalGlue());
		homeMenuBar.setBounds(0, 0, 758, 21);
		getContentPane().add(homeMenuBar);
		
		JMenu statistic = new JMenu("احصائيات");
		homeMenuBar.add(statistic);
		
		JMenuItem statbynum = new JMenuItem("حسب الرقم");
		statbynum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatByNumber sbn = new StatByNumber();
				sbn.setLocationRelativeTo(getThis());
				sbn.setVisible(true);
			}
		});
		statistic.add(statbynum);
		
		JMenu mnTest = new JMenu("إعدادات");
		mnTest.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
		homeMenuBar.add(mnTest);
		Session.read();
		JLabel fullnamebtn = new JLabel(Session.userfirstname+" "+Session.userlastname, SwingConstants.RIGHT);
		fullnamebtn.setFont(new Font("Dialog", Font.BOLD, 15));
		fullnamebtn.setIcon(null);
		fullnamebtn.setBounds(500, 33, 216, 22);
		getContentPane().add(fullnamebtn);
		
		JButton logoutbtn = new JButton("");
		logoutbtn.setBackground(new Color(238, 238, 238));
		
		logoutbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User usr = new User();
				usr.logout();
				setVisible(false);
				Main main = new Main();
				main.getFrame().setVisible(true);
			}
		});
		logoutbtn.setIcon(new ImageIcon(Home.class.getResource("/ressources/icons/logout.png")));
		logoutbtn.setBorder(BorderFactory.createEmptyBorder());
		logoutbtn.setBounds(721, 33, 25, 25);
		getContentPane().add(logoutbtn);
		
		JButton newBtn = new JButton("اضافة ملف تحصيل جديد");
		newBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		newBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewDossTahsil nesdoss = new NewDossTahsil();
				nesdoss.setLocationRelativeTo(getThis());
				nesdoss.setVisible(true);
			}
		});
		newBtn.setBounds(426, 90, 250, 73);
		getContentPane().add(newBtn);
		
		JButton mngDossBtn = new JButton("تدبير ملف تحصيل");
		mngDossBtn.setFont(new Font("Dialog", Font.BOLD, 16));
		mngDossBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Details det = new Details(new Dossier());
				det.setLocationRelativeTo(getThis());
				det.setVisible(true);
			}
		});
		mngDossBtn.setBounds(52, 90, 250, 73);
		getContentPane().add(mngDossBtn);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 175, 734, 377);
		getContentPane().add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JPanel tblPanel = new JPanel();
		scrollPane.setViewportView(tblPanel);
		float CellMaxWidth = (getThis().getWidth())/7;
		String miglayouttmplate = "[40px:40px:40px,grow,center][100px:100px:"+CellMaxWidth+"px,grow,center][100px:100px:"+CellMaxWidth+"px,grow,center][100px:100px:"+CellMaxWidth+"px,grow,center][100px:100px:"+CellMaxWidth+"px,grow,center][100px:100px:"+CellMaxWidth+"px,grow,center][100px:100px:"+CellMaxWidth+"px,grow,center]";
		tblPanel.setLayout(new MigLayout());
		
		// JPanel de description
				JPanel jp1 = new JPanel();
				jp1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				jp1.setLayout(new MigLayout("", miglayouttmplate, "[]"));
				
				JButton deletebtn1 = new JButton("");
				deletebtn1.setBackground(new Color(238, 238, 238));
				deletebtn1.setIcon(new ImageIcon(Home.class.getResource("/ressources/icons/trash.png")));
				deletebtn1.setBorder(BorderFactory.createEmptyBorder());
				deletebtn1.setBounds(459, 36, 25, 25);
				jp1.add(deletebtn1, "cell 0 0");
				
				JLabel printlbl = new JLabel("طباعة");
				jp1.add(printlbl, "cell 1 0");
				
				JLabel lastenamelbl1 = new JLabel("الرسوم القضائية");
				jp1.add(lastenamelbl1, "cell 2 0");
				
				JLabel phonelbl1 = new JLabel("الملف اﻷصلي");
				jp1.add(phonelbl1, "cell 3 0");
				
				JLabel namelbl1 = new JLabel("المدعى عليه");
				jp1.add(namelbl1, "cell 4 0");
				
				JLabel imglbl1 = new JLabel("المدعي");
				jp1.add(imglbl1, "cell 5 0");
				
				JLabel lblnumDoss1 = new JLabel("رقم م.ت");
				jp1.add(lblnumDoss1, "cell 6 0");
				tblPanel.add(jp1,"wrap, pushx, growx");
				
				Dossier dos = new Dossier();
				List<Dossier> lstDoss = dos.getDossiers(null, 10, false);

				
		for (int i = 0; i < lstDoss.size(); i++) {
			JPanel jp = new JPanel();
			jp.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			jp.setLayout(new MigLayout("", miglayouttmplate, "[]"));
			
			JButton deletebtn = new JButton("");
			deletebtn.setBackground(new Color(238, 238, 238));
			deletebtn.setIcon(new ImageIcon(Home.class.getResource("/ressources/icons/trash.png")));
			deletebtn.setBorder(BorderFactory.createEmptyBorder());
			deletebtn.setBounds(459, 36, 25, 25);
			deletebtn.addActionListener(new DossierActionListener(lstDoss.get(i), getThis(),tblPanel, jp, "delete"));

			jp.add(deletebtn, "cell 0 0");
			
			JButton printbtn = new JButton("");
			printbtn.setBackground(new Color(238, 238, 238));
			printbtn.setIcon(new ImageIcon(Home.class.getResource("/ressources/icons/print.png")));
			printbtn.setBorder(BorderFactory.createEmptyBorder());
			printbtn.setBounds(459, 36, 25, 25);
			printbtn.addActionListener(new DossierActionListener(lstDoss.get(i), getThis(),tblPanel, jp, "show"));

			jp.add(printbtn, "cell 1 0");
			
			JLabel phonelbl = new JLabel(Double.toString(lstDoss.get(i).getMontantIndemnisation()));
			jp.add(phonelbl, "cell 2 0");
			
			JLabel lastenamelbl = new JLabel(lstDoss.get(i).getNumDossierOrig());
			jp.add(lastenamelbl, "cell 3 0");
			
			String mouda3i = "";
			String mouda3a3alayh = "";
			if (lstDoss.get(i).getParties().size() < 2) {
				String numdossier = lstDoss.get(i).getYear()+"/"+lstDoss.get(i).getNumDossier();
				JOptionPane.showMessageDialog(getThis(), "خطأ في تحميل أحد أطراف الملف رقم "+numdossier+"","",JOptionPane.ERROR_MESSAGE);
			}else{
				if (lstDoss.get(i).getParties().get(0).getRole_partie() == "مدعى عليه") {
					mouda3i = lstDoss.get(i).getParties().get(0).getNom()+" "+lstDoss.get(i).getParties().get(0).getPrenom();
					mouda3a3alayh = lstDoss.get(i).getParties().get(1).getNom()+" "+lstDoss.get(i).getParties().get(1).getPrenom();
				}else{
					mouda3i = lstDoss.get(i).getParties().get(1).getNom()+" "+lstDoss.get(i).getParties().get(1).getPrenom();
					mouda3a3alayh = lstDoss.get(i).getParties().get(0).getNom()+" "+lstDoss.get(i).getParties().get(0).getPrenom();
				}
			}
			
			
			
			jp.add(new JLabel(mouda3a3alayh.substring(0, Math.min(mouda3a3alayh.length(), 10))+"..."), "cell 4 0");
			
			jp.add(new JLabel(mouda3i.substring(0, Math.min(mouda3i.length(), 10))+"..."), "cell 5 0");
			
			jp.add(new JLabel(Integer.toString(lstDoss.get(i).getYear())+"/"+Integer.toString(lstDoss.get(i).getNumDossier())), "cell 6 0");
			tblPanel.add(jp,"wrap, pushx, growx");
		}
		panel.setLayout(gl_panel);
	}
}