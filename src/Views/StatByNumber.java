package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.Dossier;
import Helpers.TahsilUtils;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatByNumber extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumFrom;
	private JTextField txtNumTo;
	private JComboBox yearFromCombo = new JComboBox();
	private JComboBox yearToCombo = new JComboBox();
	private Dossier dossierController = new Dossier(); 
	private static TahsilUtils tu;

	/**
	 * Create the dialog.
	 */
	public StatByNumber() {
		setAlwaysOnTop(true);
		setModal(true);
		
		tu = new TahsilUtils();
		setBounds(100, 100, 414, 272);
		
		JLabel lblFromNum = new JLabel("من رقم");
		lblFromNum.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel lblToNum = new JLabel("الى رقم");
		lblToNum.setFont(new Font("Dialog", Font.BOLD, 14));
		
		txtNumFrom = new JTextField();
		txtNumFrom.setHorizontalAlignment(JTextField.RIGHT) ;
		txtNumFrom.setColumns(10);
		
		txtNumTo = new JTextField();
		txtNumTo.setHorizontalAlignment(JTextField.RIGHT) ;
		txtNumTo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("سنة");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JLabel label = new JLabel("سنة");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		
		String listValues[] = new String[5];
		int x = 0;
		for (int i = 0; i < 5; i++) {
			String s = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-x);
			listValues[i] = s;
			x++;
		}
		yearFromCombo.setModel(new DefaultComboBoxModel<String>(listValues));
		yearFromCombo.setFont(new Font("Dialog", Font.BOLD, 14));
		
		yearToCombo.setModel(new DefaultComboBoxModel<String>(listValues));
		yearToCombo.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton downloadBtn = new JButton("تحميــــــل");
		downloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNumFrom.getText().trim().isEmpty() || txtNumTo.getText().trim().isEmpty()){
					JOptionPane.showMessageDialog(getThis(), "المرجو ملأ الخانات","خطأ",JOptionPane.ERROR_MESSAGE);
				}else{
					try{
						int fromNum = tu.VarParseInt(txtNumFrom.getText());
						int toNum = tu.VarParseInt(txtNumTo.getText());
						int fromYear = tu.VarParseInt(yearFromCombo.getSelectedItem().toString());
						int toYear = tu.VarParseInt(yearToCombo.getSelectedItem().toString());
						
						dossierController.statisticsByNum(fromNum, toNum, fromYear, toYear, getThis());
					}catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(getThis(), "المرجو ادخال أعداد صحيحة","خطأ",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(80)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(yearFromCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(yearToCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(txtNumTo, 0, 0, Short.MAX_VALUE)
						.addComponent(txtNumFrom, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblToNum, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFromNum, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(2)))
					.addGap(40))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(57)
					.addComponent(downloadBtn, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFromNum)
						.addComponent(txtNumFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(yearFromCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(69)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblToNum)
						.addComponent(txtNumTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(yearToCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addComponent(downloadBtn)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
	public JDialog getThis(){
		return this;
	}
}
