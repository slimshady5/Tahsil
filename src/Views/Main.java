package Views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Controllers.User;
import Helpers.Session;
import Helpers.TahsilUtils;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;

public class Main {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private User user = new User();
	private static TahsilUtils tu;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          } catch (Exception e) {
            System.err.println("Look and feel not set.");
          }
		tu = new TahsilUtils();
		tu.configToHome();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(Session.username != null){
						if(Session.isValide()){
							Home home = new Home();
							home.setVisible(true);
						}else{
							new User().logout();
							Main window = new Main();
							window.getFrame().setVisible(true);
						}
						
					}else{	
						Main window = new Main();
						window.getFrame().setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
			initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setFrame(new JFrame());
		getFrame().setTitle("المحكمة الإدارية بمراكش");
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		getFrame().setResizable(false);
		getFrame().setLocation(dim.width/2-getFrame().getSize().width/2, dim.height/2-getFrame().getSize().height/2);
		
		JLabel usernameLabel = new JLabel("إسم المستخدم");
		usernameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		usernameLabel.setBounds(247, 67, 114, 15);
		getFrame().getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("كلمة المرور");
		passwordLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		passwordLabel.setBounds(266, 119, 95, 15);
		getFrame().getContentPane().add(passwordLabel);
		
		usernameField = new JTextField();
		usernameField.setBounds(97, 65, 114, 25);
		getFrame().getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(97, 117, 114, 25);
		getFrame().getContentPane().add(passwordField);
		
		JButton validateBtn = new JButton("دخول");
		validateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pass = new String(passwordField.getPassword());
				String username = usernameField.getText();
				String hashpass ="";
				if(username.isEmpty() || pass.isEmpty()){
					JOptionPane.showMessageDialog(null,"Veuillez remplir tous les champs");
				}else{
					StringBuffer sb = new StringBuffer();
					try {
						MessageDigest md = MessageDigest.getInstance("MD5");
						md.update(pass.getBytes());
						byte byteData[] = md.digest();
						
				        for (int i = 0; i < byteData.length; i++) {
				         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				        }
				        hashpass = sb.toString();
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
					
					if(user.login(username, hashpass)){
						Home home = new Home();
						home.setVisible(true);
						getFrame().setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null,"Nom d'utilisateur ou mot de passe incorrect");
					}
				}
				
			}
		});
		validateBtn.setBounds(52, 183, 96, 25);
		getFrame().getContentPane().add(validateBtn);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(12, 238, 422, 2);
		getFrame().getContentPane().add(separator);
		
		JMenuBar menuBar = new JMenuBar();
		getFrame().setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFichier.add(mntmQuitter);
		
		JMenu mnOutils = new JMenu("Outils");
		menuBar.add(mnOutils);
		
		JMenuItem mntmConfigDatabaseSettings = new JMenuItem("Config. Base de Données");
		mntmConfigDatabaseSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConfigJDialog dbconfigdialog = new DBConfigJDialog();
				dbconfigdialog.setResizable(false);
				dbconfigdialog.setLocationRelativeTo(getFrame());
				dbconfigdialog.setAlwaysOnTop(true);
				dbconfigdialog.setModal(true);
				dbconfigdialog.setVisible(true);

			}
		});
		mnOutils.add(mntmConfigDatabaseSettings);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		
		this.frame = frame;
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				TahsilUtils.DeleteTmps();
			}
		});
	}

}
