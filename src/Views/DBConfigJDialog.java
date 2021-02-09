package Views;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Helpers.XmlParser;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DBConfigJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField addressTextField;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTextField dbnameTextField;
	private JTextField portTextField;


	 /**
	 * Create the dialog.
	 */
	public DBConfigJDialog() {
		setTitle("إعدادات قاعدة البيانات");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		XmlParser xmlparser = new XmlParser();
		xmlparser.readXML();
		setBounds(100, 100, 288, 403);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel dbConfigLabel = new JLabel("إعدادات قاعدة البيانات");
		dbConfigLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		dbConfigLabel.setBounds(25, 29, 225, 24);
		contentPanel.add(dbConfigLabel);
		
		JLabel dbUsernameLabel = new JLabel("إسم المستخدم");
		dbUsernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbUsernameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		dbUsernameLabel.setBounds(157, 150, 104, 15);
		contentPanel.add(dbUsernameLabel);
		
		JLabel addressLabel = new JLabel("عنوان IP");
		addressLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		addressLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		addressLabel.setBounds(192, 107, 69, 15);
		contentPanel.add(addressLabel);
		
		JLabel label = new JLabel("كلمة المرور");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(173, 196, 89, 15);
		contentPanel.add(label);
		
		addressTextField = new JTextField();
		addressTextField.setBounds(25, 105, 114, 24);
		contentPanel.add(addressTextField);
		addressTextField.setColumns(10);
		addressTextField.setText(xmlparser.getAddress());
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(25, 148, 114, 24);
		contentPanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		usernameTextField.setText(xmlparser.getUsername());
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(25, 194, 114, 24);
		contentPanel.add(passwordTextField);
		passwordTextField.setColumns(10);
		passwordTextField.setText(xmlparser.getPassword());
		
		JLabel dbnameLabel = new JLabel("قاعدة البيانات");
		dbnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dbnameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		dbnameLabel.setBounds(157, 238, 104, 15);
		contentPanel.add(dbnameLabel);
		
		dbnameTextField = new JTextField();
		dbnameTextField.setBounds(25, 236, 114, 24);
		contentPanel.add(dbnameTextField);
		dbnameTextField.setColumns(10);
		dbnameTextField.setText(xmlparser.getDatabase());
		
		JLabel portLabel = new JLabel("رقم Port");
		portLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		portLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		portLabel.setBounds(192, 276, 69, 15);
		contentPanel.add(portLabel);
		
		portTextField = new JTextField();
		portTextField.setBounds(25, 274, 114, 25);
		contentPanel.add(portTextField);
		portTextField.setColumns(10);
		portTextField.setText(xmlparser.getPortnumber());
		
		JButton restoreBtn = new JButton("إرجاع");
		restoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addressTextField.setText("localhost");
				usernameTextField.setText("root");
				passwordTextField.setText("");
				dbnameTextField.setText("tamarrakech");
				portTextField.setText("3306");
			}
		});
		restoreBtn.setBounds(152, 338, 97, 25);
		contentPanel.add(restoreBtn); 
		
		JButton modifyBtn = new JButton("تغير");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XmlParser xpars = new XmlParser();
				xpars.setAddress(addressTextField.getText());
				xpars.setUsername(usernameTextField.getText());
				xpars.setPassword(passwordTextField.getText());
				xpars.setDatabase(dbnameTextField.getText());
				if(xpars.writeXML()){
					JOptionPane.showMessageDialog(getThis(),"configuration enregistré avec succes");
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog(getThis(),"erreur lors de l'enregistrement, veuillez reessayer");
				}
			}
		});
		modifyBtn.setBounds(25, 338, 97, 25);
		contentPanel.add(modifyBtn);
		
	}
	
	private JDialog getThis(){
		return this;
	}
}
