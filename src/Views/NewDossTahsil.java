package Views;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import Controllers.Dossier;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import java.awt.ComponentOrientation;
import javax.swing.JSeparator;

public class NewDossTahsil extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumDoss;
	private JTextField txtDossOrig;
	private JTextField txtNumVerdict;
	private JTextField txtMntVerdict;
	private JComboBox<String> yearsCombo;
	private JDateChooser dteVerdict;
	private Dossier doss = new Dossier();
	private JTextField txtAutreIndem;
	private JCheckBox chkPlaidoirie;
	private JTextField txtNomP2;
	private JTextField txtPrenomP2;
	private JTextField txtNomP1;
	private JTextField txtPrenomP1;
	private JRadioButton persMoralP1;
	private JRadioButton persMoralP2;
	private JComboBox<Object> comboRoleP1;
	private JComboBox<Object> comboCitiesP1;
	private JLabel lblReqPrenomP1;
	private JLabel lblPrenomP1;
	private JLabel lblReqRoleP1;
	private JLabel lblRoleP1;
	private JLabel lblReqCityP1;
	private JLabel lblCityP1;
	private JLabel lblReqAdressP1;
	private JLabel lblAdressP1;
	private JLabel lblReqNomP1;
	private JLabel lblNomP1;

	private JComboBox<Object> comboRoleP2;
	private JComboBox<Object> comboCitiesP2;
	private JTextArea txtAdressP2;
	private JLabel lblReqPrenomP2;
	private JLabel lblPrenomP2;
	private JLabel lblReqRoleP2;
	private JLabel lblRoleP2;
	private JLabel lblReqCityP2;
	private JLabel lblCityP2;
	private JLabel lblReqAdressP2;
	private JLabel lblAdressP2;
	private JLabel lblReqNomP2;
	private JLabel lblNomP2;
	private JCheckBox debiteurP1;
	private JCheckBox debiteurP2;
	private JTextArea txtAdressP1;
	private JTextField NumTerraintxt;
	private JTextField NumMarsoumtxt;
	private JDateChooser DateMarsoumChooser;
	private JTextField NumJRtxt;
	private JLabel DateJRlbl;
	private JDateChooser DateJRChooser;
	private JLabel dteisdarlbl;
	private JDateChooser DateIsdar;

	public NewDossTahsil() {
		setTitle("إضافة ملف تحصيل جديد");
		setModal(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 844, 829);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(Box.createHorizontalGlue());
		setJMenuBar(menuBar);
		
		JMenu FichierMenu = new JMenu("ملف");
		menuBar.add(FichierMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("رقم ملف التحصيل");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		
		txtNumDoss = new JTextField();
		txtNumDoss.setHorizontalAlignment(JTextField.CENTER);
		txtNumDoss.setText(Integer.toString(doss.getLastId()+1));
		txtNumDoss.setColumns(10);
		
		JLabel label = new JLabel("السنة");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		
		yearsCombo = new JComboBox<String>();
		String listValues[] = new String[5];
		int x = 0;
		for (int i = 0; i < 5; i++) {
			String s = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-x);
			listValues[i] = s;
			x++;
		}
		yearsCombo.setModel(new DefaultComboBoxModel<String>(listValues));
		yearsCombo.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblOrigDoss = new JLabel("رقم الملف اﻷصلي");
		lblOrigDoss.setFont(new Font("Dialog", Font.BOLD, 14));
		
		txtDossOrig = new JTextField();
		txtDossOrig.setHorizontalAlignment(JTextField.CENTER);
		txtDossOrig.setColumns(10);
		
		chkPlaidoirie = new JCheckBox("حقوق المرافعة");
		chkPlaidoirie.setHorizontalTextPosition(SwingConstants.LEFT);
		
		JLabel lblNumVerdict = new JLabel("رقم الحكم");
		lblNumVerdict.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumVerdict.setFont(new Font("Dialog", Font.BOLD, 14));
		
		txtNumVerdict = new JTextField();
		txtNumVerdict.setHorizontalAlignment(JTextField.CENTER);
		txtNumVerdict.setColumns(10);
		
		JLabel lblDteVerdict = new JLabel("تاريخ الحكم");
		lblDteVerdict.setFont(new Font("Dialog", Font.BOLD, 14));
		
		dteVerdict = new JDateChooser();
		dteVerdict.setDateFormatString("yyyy/MM/dd");
		
		JLabel lblMntVerdict = new JLabel("مبلغ الحكم");
		lblMntVerdict.setFont(new Font("Dialog", Font.BOLD, 14));
		
		txtMntVerdict = new JTextField();
		txtMntVerdict.setHorizontalAlignment(JTextField.CENTER);
		txtMntVerdict.setColumns(10);
		
		JButton btnNewButton = new JButton("اضافة الملف و الطرفين");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateForm()) {
					List<List<Object>> lst = getDossFormValues();
					
					if (lst != null) {
						if (doss.addDossier(lst, getThis())) {
							txtNumDoss.setText(Integer.toString(doss.getLastId()+1));
							txtDossOrig.setText("");
							txtMntVerdict.setText("");
							txtNumVerdict.setText("");
							dteVerdict.setCalendar(null);
						}
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		
		JLabel ث = new JLabel("صوائر أخرى");
		ث.setFont(new Font("Dialog", Font.BOLD, 14));
		
		txtAutreIndem = new JTextField();
		txtAutreIndem.setText("0");
		txtAutreIndem.setHorizontalAlignment(SwingConstants.CENTER);
		txtAutreIndem.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\u0627\u0636\u0627\u0641\u0629 \u0627\uFEF7\u0637\u0631\u0627\u0641", TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		
		JLabel NumTerrainlbl = new JLabel("رقم ق. اﻷرضية");
		NumTerrainlbl.setFont(new Font("Dialog", Font.BOLD, 14));
		
		NumTerraintxt = new JTextField();
		NumTerraintxt.setColumns(10);
		
		JLabel NumMarsoumlbl = new JLabel("رقم المرسوم");
		NumMarsoumlbl.setFont(new Font("Dialog", Font.BOLD, 14));
		
		NumMarsoumtxt = new JTextField();
		NumMarsoumtxt.setColumns(10);
		
		JLabel NumJRlbl = new JLabel("رقم ج.ر");
		NumJRlbl.setFont(new Font("Dialog", Font.BOLD, 14));
		
		NumJRtxt = new JTextField();
		NumJRtxt.setColumns(10);
		
		DateJRlbl = new JLabel("تاريخ ج.ر");
		DateJRlbl.setFont(new Font("Dialog", Font.BOLD, 14));
		
		DateJRChooser = new JDateChooser();
		DateJRChooser.setDateFormatString("yyyy/MM/dd");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		
		JLabel dateMarsoumlbl = new JLabel("تاريخ المرسوم");
		dateMarsoumlbl.setFont(new Font("Dialog", Font.BOLD, 14));
		
		DateMarsoumChooser = new JDateChooser();
		DateMarsoumChooser.setDateFormatString("yyyy/MM/dd");
		
		dteisdarlbl = new JLabel("تاريخ الاصدار");
		dteisdarlbl.setFont(new Font("Dialog", Font.BOLD, 14));
		
		DateIsdar = new JDateChooser();
		DateIsdar.setDateFormatString("yyyy/MM/dd");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtAutreIndem, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(ث)
									.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
									.addComponent(txtMntVerdict, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblMntVerdict))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(chkPlaidoirie)
									.addGap(18)
									.addComponent(txtDossOrig, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblOrigDoss)))
							.addPreferredGap(ComponentPlacement.UNRELATED, 41, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(dteVerdict, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(yearsCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label)
									.addGap(16)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtNumDoss, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDteVerdict))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtNumVerdict, 0, 0, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNumVerdict))
								.addComponent(lblNewLabel)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(DateJRChooser, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(DateJRlbl, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(NumJRtxt, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(NumJRlbl, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(NumMarsoumtxt, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(NumMarsoumlbl, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(NumTerraintxt, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(NumTerrainlbl)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(331, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(311))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(209, Short.MAX_VALUE)
					.addComponent(DateIsdar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(dteisdarlbl)
					.addGap(108)
					.addComponent(DateMarsoumChooser, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(dateMarsoumlbl)
					.addGap(42))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtNumDoss, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(chkPlaidoirie)
						.addComponent(txtDossOrig, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrigDoss)
						.addComponent(yearsCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dteVerdict, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(37)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNumVerdict)
									.addComponent(txtNumVerdict, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblDteVerdict)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(txtAutreIndem, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addComponent(ث)
									.addComponent(txtMntVerdict, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
									.addComponent(lblMntVerdict)))))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(NumTerrainlbl)
								.addComponent(NumTerraintxt, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(NumMarsoumtxt, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(NumJRlbl, GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
								.addComponent(NumJRtxt, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(DateJRlbl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(5)
								.addComponent(NumMarsoumlbl, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
						.addComponent(DateJRChooser, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(DateMarsoumChooser, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(dateMarsoumlbl)
							.addGap(8))
						.addComponent(dteisdarlbl)
						.addComponent(DateIsdar, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		
		txtNomP1 = new JTextField();
		txtNomP1.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtNomP1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNomP1.setColumns(10);
		
		persMoralP1 = new JRadioButton("شخص معنوي");
		persMoralP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblReqPrenomP1.setVisible(false);
				lblNomP1.setText("اسم الطرف");
				lblPrenomP1.setText("رقم السجل التجاري");
			}
		});
		persMoralP1.setHorizontalTextPosition(SwingConstants.LEFT);
		persMoralP1.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JRadioButton persPhysiqueP1 = new JRadioButton("شخص ذاتي");
		persPhysiqueP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblReqPrenomP1.setVisible(true);
				lblNomP1.setText("الاسم العائلي");
				lblPrenomP1.setText("الاسم الشخصي");
			}
		});
		persPhysiqueP1.setSelected(true);
		persPhysiqueP1.setHorizontalTextPosition(SwingConstants.LEFT);
		persPhysiqueP1.setFont(new Font("Dialog", Font.BOLD, 16));
		
		
		txtPrenomP1 = new JTextField();
		txtPrenomP1.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtPrenomP1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrenomP1.setColumns(10);
		
		comboRoleP1 = new JComboBox<Object>();
		comboRoleP1.setModel(new DefaultComboBoxModel<Object>(new String[] {"مدعي", "مدعى عليه"}));
		((JLabel)comboRoleP1.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		
		comboCitiesP1 = new JComboBox<Object>();
		comboCitiesP1.setModel(new DefaultComboBoxModel<Object>(new String[] {"الرباط", "القنيطرة", "الجديدة", "سلا", "تمارة", "بن سليمان", "الخميسات", "سيدي قاسم", "الدار البيضاء", "المحمدية", "أزيلال", "بني ملال", "خريبكة", "سطات", "فاس", "صفرو", "الحسيمة", "بولمان", "تاونات", "تازة", "مراكش", "شيشاوة", "قلعة السراغنة", "الصويرة", "ورزازات", "آسفي", "مكناس", "الراشيدية", "إيفران", "خنيفرة", "وجدة", "فكيك", "الناضور", "العيون", "بوجدور", "السمارة", "الداخلة", "أكادير", "كلميم", "طانطان", "تارودانت", "طاطا", "تيزنيت", "طنجة", "شفشاون", "العرائش", "تطوان", "وزان", "الصخيرات", "بركان", "القصر الكبير", "برشيد", "تيفلت", "ميدلت", "سيدي بنور", "ابن جرير", "الفقيه بن صالح", "انزكان", "بوزنيقة", "تزنيت", "سيدي سليمان", "اليوسفية", "ازرو", "تاوريرة", "مشرع بالقصيري", "تنغير", "جرسيف", "حد السوالم", "دائرة الكارة", "دمنات", "ابي الجعد", "ارفود", "ازمور", "البروج", "الحاجب", "الرماني", "اوطاط الحاج", "زاوية الشيخ", "سوق الاربعاء", "سوق السبت", "سيدي يحيى زعير", "سيدي يحيى الغرب", "غفساي", "قصبة تادلة", "مريرت", "واد زم", "احفير", "توجطات", "اصيلة", "بن أحمد", "بيوكرة", "جرادة", "دريوش", "فنيدق", "كرسيف", " قرية بامحمد", "المديق", "مرتيل", "ماسة", "مشرع بلقصيري", "النواصر", "أولاد تايمة", "تافراوت", "تيط مليل", "زاكورة", "زايو", "زغنغن", "بوعرفة", "روما", "ايموزار", "اكليم", "بني انصار", "الشماعية", "شتوكة أيت باها", "تنجداد", "إيمنتانوت", "الريش", "الدشيرة", "حد كورت", "مراش", "جمعة اسحيم", "أيت أورير", "خميس الزمامرة", "أكنول", "ترجيست", "ميضار", "البئر الجديد", "ميسور", "تاهلة", "تغزوت", "تامسنا‎", "اربعاء العونات", "الرحامنة", "السراغنة", "غير متوفر"}));
		comboCitiesP1.setSelectedIndex(20);
		//aligner le text du JComboBox à droite ++++++++
		((JLabel)comboCitiesP1.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		
		lblReqPrenomP1 = new JLabel("(*)");
		lblReqPrenomP1.setForeground(Color.RED);
		lblReqPrenomP1.setFont(new Font("Dialog", Font.BOLD, 13));
		
		lblPrenomP1 = new JLabel("الاسم الشخصي");
		lblPrenomP1.setFont(new Font("Dialog", Font.BOLD, 16));
		
		lblReqRoleP1 = new JLabel("(*)");
		lblReqRoleP1.setForeground(Color.RED);
		lblReqRoleP1.setFont(new Font("Dialog", Font.BOLD, 13));
		
		lblRoleP1 = new JLabel("الصفة");
		lblRoleP1.setFont(new Font("Dialog", Font.BOLD, 16));
		
		lblReqCityP1 = new JLabel("(*)");
		lblReqCityP1.setForeground(Color.RED);
		lblReqCityP1.setFont(new Font("Dialog", Font.BOLD, 13));
		
		lblCityP1 = new JLabel("المدينة");
		lblCityP1.setFont(new Font("Dialog", Font.BOLD, 16));
		
		lblReqAdressP1 = new JLabel("(*)");
		lblReqAdressP1.setForeground(Color.RED);
		lblReqAdressP1.setFont(new Font("Dialog", Font.BOLD, 13));
		
		lblAdressP1 = new JLabel("العنوان");
		lblAdressP1.setFont(new Font("Dialog", Font.BOLD, 16));

		
		lblReqNomP1 = new JLabel("(*)");
		lblReqNomP1.setForeground(Color.RED);
		lblReqNomP1.setFont(new Font("Dialog", Font.BOLD, 13));
		
		lblNomP1 = new JLabel("الاسم العائلي");
		lblNomP1.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblFirstPart = new JLabel("الطرف اﻷول");
		lblFirstPart.setForeground(Color.BLUE);
		lblFirstPart.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		
		debiteurP1 = new JCheckBox("مدين");
		debiteurP1.setSelected(true);
		debiteurP1.setFont(new Font("Dialog", Font.BOLD, 15));
		debiteurP1.setHorizontalTextPosition(SwingConstants.LEFT);
		
		txtAdressP1 = new JTextArea();
		txtAdressP1.setLineWrap(true);
		txtAdressP1.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtAdressP1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtAdressP1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(132)
					.addComponent(lblFirstPart)
					.addContainerGap(127, Short.MAX_VALUE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(debiteurP1)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtAdressP1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNomP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(persMoralP1)
								.addComponent(txtPrenomP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboRoleP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboCitiesP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblReqPrenomP1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPrenomP1))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblReqRoleP1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblRoleP1))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblReqCityP1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCityP1))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblReqAdressP1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAdressP1))
								.addComponent(persPhysiqueP1)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblReqNomP1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNomP1)))))
					.addGap(21))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFirstPart)
					.addGap(36)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(persMoralP1)
						.addComponent(persPhysiqueP1))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNomP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomP1)
						.addComponent(lblReqNomP1))
					.addGap(30)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrenomP1)
						.addComponent(txtPrenomP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReqPrenomP1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRoleP1)
						.addComponent(comboRoleP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReqRoleP1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCityP1)
						.addComponent(comboCitiesP1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReqCityP1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdressP1)
						.addComponent(lblReqAdressP1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAdressP1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(debiteurP1)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblSecondPart = new JLabel("الطرف الثاني");
		lblSecondPart.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 20));
		lblSecondPart.setForeground(Color.BLUE);
		
		lblNomP2 = new JLabel("الاسم العائلي");
		lblNomP2.setFont(new Font("Dialog", Font.BOLD, 16));
		
		lblPrenomP2 = new JLabel("الاسم الشخصي");
		lblPrenomP2.setFont(new Font("Dialog", Font.BOLD, 16));
		
		lblRoleP2 = new JLabel("الصفة");
		lblRoleP2.setFont(new Font("Dialog", Font.BOLD, 16));
		
		lblCityP2 = new JLabel("المدينة");
		lblCityP2.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JRadioButton persPhysiqueP2 = new JRadioButton("شخص ذاتي");
		persPhysiqueP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblReqPrenomP2.setVisible(true);
				lblNomP2.setText("الاسم العائلي");
				lblPrenomP2.setText("الاسم الشخصي");
			}
		});
		persPhysiqueP2.setSelected(true);
		persPhysiqueP2.setFont(new Font("Dialog", Font.BOLD, 16));
		persPhysiqueP2.setHorizontalTextPosition(SwingConstants.LEFT);
		
		persMoralP2 = new JRadioButton("شخص معنوي");
		persMoralP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblReqPrenomP2.setVisible(false);
				lblNomP2.setText("اسم الطرف");
				lblPrenomP2.setText("رقم السجل التجاري");
			}
		});
		persMoralP2.setFont(new Font("Dialog", Font.BOLD, 16));
		persMoralP2.setHorizontalTextPosition(SwingConstants.LEFT);
		
		lblAdressP2 = new JLabel("العنوان");
		lblAdressP2.setFont(new Font("Dialog", Font.BOLD, 16));
		
		txtNomP2 = new JTextField();
		txtNomP2.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtNomP2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNomP2.setColumns(10);
		
		txtPrenomP2 = new JTextField();
		txtPrenomP2.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtPrenomP2.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrenomP2.setColumns(10);
		
		comboCitiesP2 = new JComboBox<Object>();
		comboCitiesP2.setModel(new DefaultComboBoxModel<Object>(new String[] {"الرباط", "القنيطرة", "الجديدة", "سلا", "تمارة", "بن سليمان", "الخميسات", "سيدي قاسم", "الدار البيضاء", "المحمدية", "أزيلال", "بني ملال", "خريبكة", "سطات", "فاس", "صفرو", "الحسيمة", "بولمان", "تاونات", "تازة", "مراكش", "شيشاوة", "قلعة السراغنة", "الصويرة", "ورزازات", "آسفي", "مكناس", "الراشيدية", "إيفران", "خنيفرة", "وجدة", "فكيك", "الناضور", "العيون", "بوجدور", "السمارة", "الداخلة", "أكادير", "كلميم", "طانطان", "تارودانت", "طاطا", "تيزنيت", "طنجة", "شفشاون", "العرائش", "تطوان", "وزان", "الصخيرات", "بركان", "القصر الكبير", "برشيد", "تيفلت", "ميدلت", "سيدي بنور", "ابن جرير", "الفقيه بن صالح", "انزكان", "بوزنيقة", "تزنيت", "سيدي سليمان", "اليوسفية", "ازرو", "تاوريرة", "مشرع بالقصيري", "تنغير", "جرسيف", "حد السوالم", "دائرة الكارة", "دمنات", "ابي الجعد", "ارفود", "ازمور", "البروج", "الحاجب", "الرماني", "اوطاط الحاج", "زاوية الشيخ", "سوق الاربعاء", "سوق السبت", "سيدي يحيى زعير", "سيدي يحيى الغرب", "غفساي", "قصبة تادلة", "مريرت", "واد زم", "احفير", "توجطات", "اصيلة", "بن أحمد", "بيوكرة", "جرادة", "دريوش", "فنيدق", "كرسيف", " قرية بامحمد", "المديق", "مرتيل", "ماسة", "مشرع بلقصيري", "النواصر", "أولاد تايمة", "تافراوت", "تيط مليل", "زاكورة", "زايو", "زغنغن", "بوعرفة", "روما", "ايموزار", "اكليم", "بني انصار", "الشماعية", "شتوكة أيت باها", "تنجداد", "إيمنتانوت", "الريش", "الدشيرة", "حد كورت", "مراش", "جمعة اسحيم", "أيت أورير", "خميس الزمامرة", "أكنول", "ترجيست", "ميضار", "البئر الجديد", "ميسور", "تاهلة", "تغزوت", "تامسنا‎", "اربعاء العونات", "الرحامنة", "السراغنة", "غير متوفر"}));
		comboCitiesP2.setSelectedIndex(20);
		((JLabel)comboCitiesP2.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		
		comboRoleP2 = new JComboBox<Object>();
		comboRoleP2.setModel(new DefaultComboBoxModel<Object>(new String[] {"مدعي", "مدعى عليه"}));
		((JLabel)comboRoleP2.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
		
		lblReqNomP2 = new JLabel("(*)");
		lblReqNomP2.setForeground(Color.RED);
		lblReqNomP2.setFont(new Font("Dialog", Font.BOLD, 13));
		
		lblReqPrenomP2 = new JLabel("(*)");
		lblReqPrenomP2.setForeground(Color.RED);
		lblReqPrenomP2.setFont(new Font("Dialog", Font.BOLD, 13));
		
		lblReqRoleP2 = new JLabel("(*)");
		lblReqRoleP2.setForeground(Color.RED);
		lblReqRoleP2.setFont(new Font("Dialog", Font.BOLD, 13));
		
		lblReqCityP2 = new JLabel("(*)");
		lblReqCityP2.setForeground(Color.RED);
		lblReqCityP2.setFont(new Font("Dialog", Font.BOLD, 13));
		
		lblReqAdressP2 = new JLabel("(*)");
		lblReqAdressP2.setForeground(Color.RED);
		lblReqAdressP2.setFont(new Font("Dialog", Font.BOLD, 13));
		
		debiteurP2 = new JCheckBox("مدين");
		debiteurP2.setFont(new Font("Dialog", Font.BOLD, 15));
		debiteurP2.setHorizontalTextPosition(SwingConstants.LEFT);
		
		txtAdressP2 = new JTextArea();
		txtAdressP2.setLineWrap(true);
		txtAdressP2.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtAdressP2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtAdressP2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(132)
					.addComponent(lblSecondPart)
					.addContainerGap(120, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(debiteurP2)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtNomP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(persMoralP2)
								.addComponent(txtPrenomP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboRoleP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboCitiesP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAdressP2, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblReqPrenomP2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPrenomP2))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblReqRoleP2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblRoleP2))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblReqCityP2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblCityP2))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblReqAdressP2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAdressP2))
								.addComponent(persPhysiqueP2)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblReqNomP2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNomP2)))))
					.addGap(21))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSecondPart)
					.addGap(36)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(persMoralP2)
						.addComponent(persPhysiqueP2))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNomP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomP2)
						.addComponent(lblReqNomP2))
					.addGap(30)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrenomP2)
						.addComponent(txtPrenomP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReqPrenomP2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRoleP2)
						.addComponent(comboRoleP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReqRoleP2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCityP2)
						.addComponent(comboCitiesP2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReqCityP2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdressP2)
						.addComponent(lblReqAdressP2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAdressP2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(debiteurP2)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		
		ButtonGroup P1BtnGroup = new ButtonGroup();
		P1BtnGroup.add(persPhysiqueP1);
		P1BtnGroup.add(persMoralP1);
		
		ButtonGroup P2BtnGroup = new ButtonGroup();
		P2BtnGroup.add(persPhysiqueP2);
		P2BtnGroup.add(persMoralP2);
		
		ButtonGroup debiteurBtnGrp = new ButtonGroup();
		debiteurBtnGrp.add(debiteurP1);
		debiteurBtnGrp.add(debiteurP2);
		
		contentPane.setLayout(gl_contentPane);
		
	}
	//Check if a field is empty
	public boolean validateForm(){
		if(dteVerdict.getDate() == null ||  String.valueOf(yearsCombo.getSelectedItem()).isEmpty() || txtNumDoss.getText().isEmpty() || txtDossOrig.getText().isEmpty() || txtNumVerdict.getText().isEmpty() || txtMntVerdict.getText().isEmpty()){
			JOptionPane.showMessageDialog(getThis(), "المرجو ملء جميع الخانات الإجبارية","خطأ",JOptionPane.ERROR_MESSAGE);
			return false;
		}else{
			return true;
		}
	}
	//Get current JFrame
	private JDialog getThis(){
		return this;
	}
	
	private List<List<Object>> getDossFormValues(){
		List<List<Object>> listOfLists = new ArrayList<List<Object>>();
		List<Object> params = new LinkedList<Object>();
		List<Object> paramsP1 = new LinkedList<Object>();
		List<Object> paramsP2 = new LinkedList<Object>();
		
			/********* Information Dossier ************/
			//Numero Dossier (0)
			params.add(txtNumDoss.getText());
			//Annee Dossier (1)
			params.add(yearsCombo.getSelectedItem());
			//Date enregistrement (2)
			params.add(new Date());
			//Numero Dossier Original (3)
			params.add(txtDossOrig.getText());
			//Numero Verdict (4)
			params.add(txtNumVerdict.getText());
			//Date Vardict (5)
			params.add(dteVerdict.getDate());
			//Montant Verdict (6)
			params.add(txtMntVerdict.getText());
			//Montant Indemnisation (7)
			params.add(0);
			//Droit Plaidoirie (8)
			if (chkPlaidoirie.isSelected()) {
				params.add(10);
			}else{
				params.add(0);
			}
			//Autre Montant (9)
			params.add(txtAutreIndem.getText());
			
			//Total (vide pour calcule) (10)
			params.add(0);
			
			//Numero Terrain (11)
			if (NumTerraintxt.getText().isEmpty()) {
				params.add("");
			}else{
				params.add(NumTerraintxt.getText());
			}
			
			
			//Numero Marsoum (12)
			if (NumMarsoumtxt.getText().isEmpty()) {
				params.add("");
			}else{
				params.add(NumMarsoumtxt.getText());
			}
			
			//Date marsoum (13)
			if (DateMarsoumChooser.getDate() == null) {
				params.add("");
			}else{
				params.add(DateMarsoumChooser.getDate());
			}
			
			//Numero Jarida (14)
			if (NumJRtxt.getText().isEmpty()) {
				params.add("");
			}else{
				params.add(NumJRtxt.getText());
			}
			
			//Date Jarida (15)
			if (DateJRChooser.getDate() == null) {
				params.add("");
			}else{
				params.add(DateJRChooser.getDate());
			}
			
			//Date Jarida (16)
			if (DateIsdar.getDate() == null) {
				params.add("");
			}else{
				params.add(DateIsdar.getDate());
			}
			/********* Fin Information Dossier ************/
			
			/********* Information Partie1 ************/
			if (persMoralP1.isSelected()) {
				txtPrenomP1.setText("--");
				paramsP1.add("morale");
			}else{
				paramsP1.add("physique");
			}

			if (txtNomP1.getText().isEmpty() || txtPrenomP1.getText().isEmpty() || txtAdressP1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(getThis(), "المرجو ملء جميع الخانات الإجبارية للطرف اﻷول","خطأ",JOptionPane.ERROR_MESSAGE);
				return null;
			}else{
				paramsP1.add(txtNomP1.getText());
				paramsP1.add(txtPrenomP1.getText());
				paramsP1.add(comboCitiesP1.getSelectedItem().toString());
				paramsP1.add(txtAdressP1.getText());
				paramsP1.add(comboRoleP1.getSelectedItem().toString());
			}
			/********* Fin Information Partie1 ************/
			
			/********* Information Partie2 ************/
			if (persMoralP2.isSelected()) {
				txtPrenomP2.setText("--");
				paramsP2.add("morale");
			}else{
				paramsP2.add("physique");
			}
			
			if (txtNomP2.getText().isEmpty() || txtPrenomP2.getText().isEmpty() || txtAdressP2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(getThis(), "المرجو ملء جميع الخانات الإجبارية للطرف الثاني","خطأ",JOptionPane.ERROR_MESSAGE);
				return null;
			}else{
				paramsP2.add(txtNomP2.getText());
				paramsP2.add(txtPrenomP2.getText());
				paramsP2.add(comboCitiesP2.getSelectedItem().toString());
				paramsP2.add(txtAdressP2.getText());
				paramsP2.add(comboRoleP2.getSelectedItem().toString());
			}
			/********* Fin Information Partie2 ************/
			if (debiteurP1.isSelected()) {
				paramsP1.add(1);
				paramsP2.add(0);
			}else{
				paramsP1.add(0);
				paramsP2.add(1);
			}

			listOfLists.add(params);
			listOfLists.add(paramsP1);
			listOfLists.add(paramsP2);

		return listOfLists;
	}
}
