package Views;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import Models.Partie;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.ComponentOrientation;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditPartie extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = new JPanel();
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JComboBox<Object> comboRole;
	private JComboBox<Object> comboCities;
	private JRadioButton radioMorale;
	private JRadioButton radioPhysique;
	private JTextArea txtAdress;
	private JCheckBox chkDebiteur;
	private static int partie_id=0;
	private static int dossier_id=0;


	public EditPartie(Partie partie, JPanel parentPanel) {
		setTitle("تغير الطرف");
		partie_id = partie.getId();
		dossier_id = partie.getDossier_id();
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setResizable(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 423, 526);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JLabel label = new JLabel("تغير الطرف");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		
		chkDebiteur = new JCheckBox("مدين");
		chkDebiteur.setHorizontalTextPosition(SwingConstants.LEFT);
		chkDebiteur.setFont(new Font("Dialog", Font.BOLD, 15));
		if (partie.getDebiteur() == 1) {
			chkDebiteur.setSelected(true);
		}
		txtAdress = new JTextArea();
		txtAdress.setLineWrap(true);
		txtAdress.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtAdress.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtAdress.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtAdress.setText(partie.getAdresse());
		
		txtNom = new JTextField();
		txtNom.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNom.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtNom.setColumns(10);
		txtNom.setText(partie.getNom());
		
		radioMorale = new JRadioButton("شخص معنوي");
		radioMorale.setHorizontalTextPosition(SwingConstants.LEFT);
		radioMorale.setFont(new Font("Dialog", Font.BOLD, 16));
		
		radioPhysique = new JRadioButton("شخص ذاتي");
		radioPhysique.setHorizontalTextPosition(SwingConstants.LEFT);
		radioPhysique.setFont(new Font("Dialog", Font.BOLD, 16));
		
		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(radioMorale);
		btnGrp.add(radioPhysique);
		if (partie.getType().equals("physique")) {
			radioPhysique.setSelected(true);
		}else{
			radioMorale.setSelected(true);
		}
		txtPrenom = new JTextField();
		txtPrenom.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrenom.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtPrenom.setColumns(10);
		txtPrenom.setText(partie.getPrenom());
		
		comboRole = new JComboBox<Object>();
		comboRole.setModel(new DefaultComboBoxModel<Object>(new String[] {"مدعي", "مدعى عليه"}));
		if (partie.getRole_partie().equals("مدعي")) {
			comboRole.setSelectedIndex(0);
		}else{
			comboRole.setSelectedIndex(1);
		}
		
		comboCities = new JComboBox<Object>();
		String[] cities = {"الرباط", "القنيطرة", "الجديدة", "سلا", "تمارة", "بن سليمان", "الخميسات", "سيدي قاسم", "الدار البيضاء", "المحمدية", "أزيلال", "بني ملال", "خريبكة", "سطات", "فاس", "صفرو", "الحسيمة", "بولمان", "تاونات", "تازة", "مراكش", "شيشاوة", "قلعة السراغنة", "الصويرة", "ورزازات", "آسفي", "مكناس", "الراشيدية", "إيفران", "خنيفرة", "وجدة", "فكيك", "الناضور", "العيون", "بوجدور", "السمارة", "الداخلة", "أكادير", "كلميم", "طانطان", "تارودانت", "طاطا", "تيزنيت", "طنجة", "شفشاون", "العرائش", "تطوان", "وزان", "الصخيرات", "بركان", "القصر الكبير", "برشيد", "تيفلت", "ميدلت", "سيدي بنور", "ابن جرير", "الفقيه بن صالح", "انزكان", "بوزنيقة", "تزنيت", "سيدي سليمان", "اليوسفية", "ازرو", "تاوريرة", "مشرع بالقصيري", "تنغير", "جرسيف", "حد السوالم", "دائرة الكارة", "دمنات", "ابي الجعد", "ارفود", "ازمور", "البروج", "الحاجب", "الرماني", "اوطاط الحاج", "زاوية الشيخ", "سوق الاربعاء", "سوق السبت", "سيدي يحيى زعير", "سيدي يحيى الغرب", "غفساي", "قصبة تادلة", "مريرت", "واد زم", "احفير", "توجطات", "اصيلة", "بن أحمد", "بيوكرة", "جرادة", "دريوش", "فنيدق", "كرسيف", " قرية بامحمد", "المديق", "مرتيل", "ماسة", "مشرع بلقصيري", "النواصر", "أولاد تايمة", "تافراوت", "تيط مليل", "زاكورة", "زايو", "زغنغن", "بوعرفة", "روما", "ايموزار", "اكليم", "بني انصار", "الشماعية", "شتوكة أيت باها", "تنجداد", "إيمنتانوت", "الريش", "الدشيرة", "حد كورت", "مراش", "جمعة اسحيم", "أيت أورير", "خميس الزمامرة", "أكنول", "ترجيست", "ميضار", "البئر الجديد", "ميسور", "تاهلة", "تغزوت", "تامسنا‎", "اربعاء العونات", "غير متوفر"};
		comboCities.setModel(new DefaultComboBoxModel<Object>(cities));
		for (int i = 0; i < cities.length; i++) {
			if (partie.getVille().equals(cities[i])) {
				comboCities.setSelectedIndex(i);
				break;
			}
		}
		
		JLabel label_1 = new JLabel("(*)");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel lblPrenom = new JLabel("الاسم الشخصي");
		lblPrenom.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel label_3 = new JLabel("(*)");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel lblRole = new JLabel("الصفة");
		lblRole.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel label_5 = new JLabel("(*)");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel lblCity = new JLabel("المدينة");
		lblCity.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel label_7 = new JLabel("(*)");
		label_7.setForeground(Color.RED);
		label_7.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel lblAdress = new JLabel("العنوان");
		lblAdress.setFont(new Font("Dialog", Font.BOLD, 16));
		
		
		
		JLabel label_9 = new JLabel("(*)");
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Dialog", Font.BOLD, 13));
		
		JLabel lblNom = new JLabel("الاسم العائلي");
		lblNom.setFont(new Font("Dialog", Font.BOLD, 16));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 385, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(132)
					.addComponent(label)
					.addContainerGap(122, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(chkDebiteur)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtAdress, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(radioMorale)
								.addComponent(txtPrenom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboCities, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPrenom))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblRole))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCity))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAdress))
								.addComponent(radioPhysique)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_9)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNom)))))
					.addGap(21))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 442, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(radioMorale)
						.addComponent(radioPhysique))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNom)
						.addComponent(label_9))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrenom)
						.addComponent(txtPrenom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRole)
						.addComponent(comboRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCity)
						.addComponent(comboCities, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdress)
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAdress, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(chkDebiteur)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JButton btnUpdate = new JButton("تغيير");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Partie p = new Partie(partie_id, 
						radioPhysique.isSelected(), 
						txtNom.getText(), 
						txtPrenom.getText(), 
						comboCities.getSelectedItem().toString(), 
						txtAdress.getText(), 
						comboRole.getSelectedItem().toString(), 
						chkDebiteur.isSelected(), 
						dossier_id);
				
				Controllers.Partie cp= new Controllers.Partie();
				if(cp.updatePartie(p)){
					JOptionPane.showMessageDialog(getThis(), "ثم تغير معلومات الطرف بنجاح","نجاح",JOptionPane.INFORMATION_MESSAGE);
					getThis().setVisible(false);
				}else{
					JOptionPane.showMessageDialog(getThis(), "حدث خطأ أثناء عملية التغيير، المرجو إعادة المحاولة","خطأ",JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnCancel = new JButton("إلغاء");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(btnUpdate)
							.addGap(18)
							.addComponent(btnCancel)))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	private JDialog getThis(){
		return this;
	}
}
