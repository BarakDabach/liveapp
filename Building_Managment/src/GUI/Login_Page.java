package GUI;
import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import Java_Classes.Building;
import Java_Classes.Main;
import Java_Classes.Resident;
import SQL.sqlDriver;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import org.w3c.dom.css.RGBColor;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Window.Type;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Cursor;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import java.awt.Rectangle;

public class Login_Page {

	public static  JFrame frmLoginPage;
	private JLabel lblPhoneNumber;
	public static JTextField phoneEntry;
	private JLabel lblLogin;
	public static JTextField PasswordEntry;
	private JLabel lblPassword;
	public JButton loginButton;
	private static sqlDriver driver;
	private static ResultSet rs;
	private static PreparedStatement preStatment;
	public static Connection con ;
	public static String windowType = "";
	public static String userName;
	public static String num_of_msg = "You Have ";
	public static int buildingIDSQL;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblWeWillHelp;
	private JLabel lblNewLabel;
	private JLabel lblToBeIn;
	private JPanel loginPanel;
	private JPanel signUpPanel;
	private JLabel label_2;
	private JPanel panel_4;
	private JLabel label_5;
	private JTextField signUpPhoneTextField;
	private JButton signUpButton;
	private JLabel lblSign;
	private JLabel lblPaswword;
	private JPasswordField signUpPasswordTextField;
	private JLabel lblEmail;
	private JTextField signUpEmailTextField;
	private JLabel lblCity;
	private JComboBox cityComboBox;
	private JComboBox streetComboBox;
	private JLabel lblStreet;
	private JComboBox buildingNumberComboBox;
	private JLabel lblBuildingNumber;
	//function that checks if a string contains only numbers
	
	public static void handleLoginCheck() throws SQLException {


  // barak

			
			String numRegex = "[0-9]+";
			String lettersRegex = "[a-zA-Z]+";
			
			
			if(phoneEntry.getText().equalsIgnoreCase(PasswordEntry.getText())) {
				
				if(phoneEntry.getText().matches(numRegex)) {
					rs = driver.sendQuery("select phone,f_Name,l_Name,buildingID from Resident where phone = "+PasswordEntry.getText());
					
					while(rs.next()) {
						buildingIDSQL = rs.getInt("buildingID");
						if (rs.getString("phone").equalsIgnoreCase(PasswordEntry.getText() )) {
							userName = rs.getString("f_Name")+" "+rs.getString("l_Name");
							rs = driver.sendQuery("select count(receive) from Message where receive ="+PasswordEntry.getText());
							while(rs.next()) {
								
								num_of_msg += Integer.toString(rs.getInt(1)) + " Messages";
								
								
							}
							frmLoginPage.dispose();
							windowType = "User";
							
							return;
						}
						
						
					
					}
					alertMsg alert = new alertMsg();
					alert.errordetlbl.setText("User Is Not Exists");
					alert.alertFrame.setVisible(true);
				}
				
				else if(phoneEntry.getText().matches(lettersRegex))  {
					
					
					preStatment = con.prepareStatement("select userName from Admin where userName = ?");
					preStatment.setString(1,phoneEntry.getText());
					rs = preStatment.executeQuery();
					
					while(rs.next()) {
						if (rs.getString("userName").equalsIgnoreCase(PasswordEntry.getText())) {
							frmLoginPage.dispose();
							windowType = "Admin";
							return;
						}
					
					}
					alertMsg alert = new alertMsg();
					alert.errordetlbl.setText("Admin Is Not Exists");
					alert.alertFrame.setVisible(true);
				}
				
				
				else {
					
					
				}
				
				
			}	
				
			
			
			
			else {
				
				alertMsg alert = new alertMsg();
				alert.alertFrame.setVisible(true);
				
			}
			
		
		}
	
	
	
	
		
	

	public static JTextField getPhoneEntry() {
		return phoneEntry;
	}




	public static JTextField getPasswordEntry() {
		return PasswordEntry;
	}




	/**
	 * Create the application.
	 */
	public Login_Page() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		sqlDriver conec = new sqlDriver();
		con =conec.connect();
		
		frmLoginPage = new JFrame();
		frmLoginPage.getContentPane().setLocation(143, 0);
		loginPanel = new JPanel();
		JLabel loginLabel = new JLabel("Login");
		JLabel signUpLabel = new JLabel("Sign up");
		loginPanel.setVisible(true);
		frmLoginPage.setUndecorated(true);
		frmLoginPage.getContentPane().setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		
		frmLoginPage.setIconImage(new ImageIcon(Resident_Window.class.getResource("/Media/windowIcon.png")).getImage());
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.getContentPane().setBackground(new Color(34, 36, 39));
		frmLoginPage.setBounds(100, 100, 668, 809);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		
		JLabel closeIcon = new JLabel("");
		closeIcon.setHorizontalAlignment(SwingConstants.CENTER);
		closeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		closeIcon.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/close.png")));
		closeIcon.setAlignmentX(1.0f);
		closeIcon.setBounds(624, 17, 24, 24);
		frmLoginPage.getContentPane().add(closeIcon);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51,53,56));
		panel_1.setBounds(0, 0, 305, 807);
		frmLoginPage.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(22, 145, 271, 280);
		panel_1.add(label_3);
		label_3.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/logo_transparent.png")));
		
		JLabel lblLoginPage = new JLabel("LiveApp");
		lblLoginPage.setBounds(109, 343, 108, 34);
		panel_1.add(lblLoginPage);
		lblLoginPage.setForeground(new Color(102, 153, 255));
		lblLoginPage.setBackground(new Color(0, 0, 0));
		lblLoginPage.setFont(new Font("Yu Gothic UI", Font.BOLD, 28));
		
		lblWeWillHelp = new JLabel("We will help you Manage and Maintain");
		lblWeWillHelp.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWeWillHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeWillHelp.setVerticalTextPosition(SwingConstants.TOP);
		lblWeWillHelp.setVerifyInputWhenFocusTarget(false);
		lblWeWillHelp.setRequestFocusEnabled(false);
		lblWeWillHelp.setForeground(Color.WHITE);
		lblWeWillHelp.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblWeWillHelp.setBackground(new Color(34, 36, 39));
		lblWeWillHelp.setBounds(22, 408, 271, 27);
		panel_1.add(lblWeWillHelp);
		
		lblNewLabel = new JLabel("your Buidling Like with supposed ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel.setVerifyInputWhenFocusTarget(false);
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(32, 440, 249, 27);
		panel_1.add(lblNewLabel);
		
		lblToBeIn = new JLabel("to be in the 21th Century");
		lblToBeIn.setVerticalTextPosition(SwingConstants.TOP);
		lblToBeIn.setVerticalAlignment(SwingConstants.BOTTOM);
		lblToBeIn.setVerifyInputWhenFocusTarget(false);
		lblToBeIn.setRequestFocusEnabled(false);
		lblToBeIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblToBeIn.setForeground(Color.WHITE);
		lblToBeIn.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblToBeIn.setBackground(new Color(34, 36, 39));
		lblToBeIn.setBounds(22, 477, 271, 16);
		panel_1.add(lblToBeIn);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/minimize.png")));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setAlignmentX(1.0f);
		label_1.setBounds(586, 17, 24, 24);
		frmLoginPage.getContentPane().add(label_1);
		
		
		loginPanel.setBounds(306, 130, 362, 714);
		frmLoginPage.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		loginPanel.setBackground(new Color(34, 36, 39));
		label = new JLabel("");
		label.setBounds(108, 79, 128, 128);
		loginPanel.add(label);
		label.setIcon(new ImageIcon(Login_Page.class.getResource("/Media/user-login.png")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblLogin = new JLabel("Login");
		lblLogin.setBounds(152, 214, 73, 33);
		loginPanel.add(lblLogin);
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblLogin.setBackground(new Color(34, 36, 39));
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 260, 310, 2);
		loginPanel.add(panel);
		panel.setBackground(new Color(102, 0, 204));
		
		lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(126, 326, 143, 25);
		loginPanel.add(lblPhoneNumber);
		lblPhoneNumber.setBackground(new Color(34, 36, 39));
		lblPhoneNumber.setForeground(new Color(255, 255, 255));
		lblPhoneNumber.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		
		phoneEntry = new JTextField();
		phoneEntry.setCaretColor(new Color(123, 104, 238));
		phoneEntry.setBounds(88, 357, 198, 31);
		loginPanel.add(phoneEntry);
		phoneEntry.setForeground(new Color(255, 255, 255));
		phoneEntry.setAlignmentX(Component.RIGHT_ALIGNMENT);
		phoneEntry.setSelectionColor(new Color(102, 51, 255));
		phoneEntry.setToolTipText("");
		phoneEntry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		phoneEntry.setBackground(new Color(34, 36, 39));
		phoneEntry.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		phoneEntry.setColumns(10);
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(132, 420, 112, 25);
		loginPanel.add(lblPassword);
		lblPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPassword.setBackground(Color.WHITE);
		
		PasswordEntry = new JTextField();
		PasswordEntry.setCaretColor(new Color(123, 104, 238));
		PasswordEntry.setBounds(88, 442, 198, 31);
		loginPanel.add(PasswordEntry);
		PasswordEntry.setForeground(new Color(255, 255, 255));
		PasswordEntry.setAlignmentX(Component.RIGHT_ALIGNMENT);
		PasswordEntry.setSelectionColor(new Color(102, 51, 255));
		PasswordEntry.setToolTipText("");
		PasswordEntry.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		PasswordEntry.setBackground(new Color(34, 36, 39));
		PasswordEntry.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(51, 204, 153)));
		PasswordEntry.setColumns(10);
		
		
		
		loginButton = new JButton("Login");
		loginButton.setBounds(88, 527, 198, 41);
		loginPanel.add(loginButton);
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					handleLoginCheck();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				btnLogin.setBackground(new Color(46,139,87));
				loginButton.setBackground(new Color(51, 204, 153));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBackground(new Color(34,36,39));
				
			}
		
		});
		
		loginButton.setFocusPainted(false);
		loginButton.setBackground(new Color(34, 36, 39));
		loginButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		loginButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(127, 255, 212)));
		
		
		signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signUpLabel.setForeground(Color.WHITE);
		signUpLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		signUpLabel.setBounds(386, 65, 83, 42);
		signUpLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				loginPanel.setVisible(false);
				signUpPanel.setVisible(true);
				signUpLabel.setForeground(new Color(51, 204, 153));
				loginLabel.setForeground(new Color(255, 255, 255));
			}
		});
		frmLoginPage.getContentPane().add(signUpLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(480, 75, 4, 30);
		frmLoginPage.getContentPane().add(panel_2);
		panel_2.setBackground(new Color(102, 0, 204));
		
		loginLabel.setForeground(new Color(51, 204, 153));
		loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		loginLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		loginLabel.setBounds(500, 65, 59, 42);
		loginLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				signUpPanel.setVisible(false);
				loginPanel.setVisible(true);
				signUpLabel.setForeground(new Color(255, 255, 255));
				loginLabel.setForeground(new Color(51, 204, 153));
			}
		});
		frmLoginPage.getContentPane().add(loginLabel);
		signUpPanel = new JPanel();
		signUpPanel.setVisible(false);
		
		
		signUpPanel.setLayout(null);
		signUpPanel.setBackground(new Color(34, 36, 39));
		signUpPanel.setBounds(306, 130, 362, 677);
		frmLoginPage.getContentPane().add(signUpPanel);
		
		label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(109, 41, 128, 128);
		signUpPanel.add(label_2);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(102, 0, 204));
		panel_4.setBounds(44, 59, 280, 2);
		signUpPanel.add(panel_4);
		
		label_5 = new JLabel("Phone Number");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		label_5.setBackground(new Color(34, 36, 39));
		label_5.setBounds(110, 106, 143, 25);
		signUpPanel.add(label_5);
		
		signUpPhoneTextField = new JTextField();
		signUpPhoneTextField.setCaretColor(new Color(123, 104, 238));
		signUpPhoneTextField.setToolTipText("");
		signUpPhoneTextField.setSelectionColor(new Color(102, 51, 255));
		signUpPhoneTextField.setForeground(Color.WHITE);
		signUpPhoneTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		signUpPhoneTextField.setColumns(10);
		signUpPhoneTextField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		signUpPhoneTextField.setBackground(new Color(34, 36, 39));
		signUpPhoneTextField.setAlignmentX(1.0f);
		signUpPhoneTextField.setBounds(82, 138, 198, 31);
		signUpPanel.add(signUpPhoneTextField);
		
		signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					handleLoginCheck();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		signUpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				signUpButton.setBackground(new Color(51, 204, 153));
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				signUpButton.setBackground(new Color(34,36,39));
				
			}
		
		});
		signUpButton.setForeground(Color.WHITE);
		signUpButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		signUpButton.setFocusPainted(false);
		signUpButton.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(127, 255, 212)));
		signUpButton.setBackground(new Color(34, 36, 39));
		signUpButton.setBounds(82, 616, 198, 41);
		signUpPanel.add(signUpButton);
		
		lblSign = new JLabel("Sign Up");
		lblSign.setToolTipText("");
		lblSign.setForeground(Color.WHITE);
		lblSign.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblSign.setBackground(new Color(34, 36, 39));
		lblSign.setBounds(131, 13, 100, 33);
		signUpPanel.add(lblSign);
		
		lblPaswword = new JLabel("Password");
		lblPaswword.setForeground(Color.WHITE);
		lblPaswword.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblPaswword.setBackground(new Color(34, 36, 39));
		lblPaswword.setBounds(138, 190, 86, 25);
		signUpPanel.add(lblPaswword);
		
		signUpPasswordTextField = new JPasswordField();
		signUpPasswordTextField.setForeground(new Color(255, 255, 255));
		signUpPasswordTextField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		signUpPasswordTextField.setSelectionColor(new Color(147, 112, 219));
		signUpPasswordTextField.setCaretColor(new Color(147, 112, 219));
		signUpPasswordTextField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		signUpPasswordTextField.setBounds(82, 228, 198, 31);
		signUpPasswordTextField.setBackground(new Color(34, 36, 39));
		signUpPanel.add(signUpPasswordTextField);
		
		lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblEmail.setBackground(new Color(34, 36, 39));
		lblEmail.setBounds(156, 284, 50, 25);
		signUpPanel.add(lblEmail);
		
		signUpEmailTextField = new JTextField();
		signUpEmailTextField.setCaretColor(new Color(147, 112, 219));
		signUpEmailTextField.setToolTipText("");
		signUpEmailTextField.setSelectionColor(new Color(102, 51, 255));
		signUpEmailTextField.setForeground(Color.WHITE);
		signUpEmailTextField.setFont(new Font("Yu Gothic UI", Font.PLAIN, 19));
		signUpEmailTextField.setColumns(10);
		signUpEmailTextField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(51, 204, 153)));
		signUpEmailTextField.setBackground(new Color(34, 36, 39));
		signUpEmailTextField.setAlignmentX(1.0f);
		signUpEmailTextField.setBounds(82, 314, 198, 31);
		signUpPanel.add(signUpEmailTextField);
		
		lblCity = new JLabel("City");
		lblCity.setForeground(Color.WHITE);
		lblCity.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblCity.setBackground(new Color(34, 36, 39));
		lblCity.setBounds(161, 358, 40, 25);
		signUpPanel.add(lblCity);
		
		cityComboBox = new JComboBox();
		cityComboBox.setFocusTraversalKeysEnabled(false);
		cityComboBox.setFocusable(false);
		cityComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		cityComboBox.setBackground(new Color(34, 36, 39));
		cityComboBox.setBounds(82, 396, 198, 22);
		cityComboBox.setUI(new BasicComboBoxUI());
		signUpPanel.add(cityComboBox);
		
		streetComboBox = new JComboBox();
		streetComboBox.setFocusable(false);
		streetComboBox.setFocusTraversalKeysEnabled(false);
		streetComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		streetComboBox.setBackground(new Color(34, 36, 39));
		streetComboBox.setBounds(82, 473, 198, 22);
		streetComboBox.setUI(new BasicComboBoxUI());
		signUpPanel.add(streetComboBox);
		
		lblStreet = new JLabel("Street");
		lblStreet.setForeground(Color.WHITE);
		lblStreet.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblStreet.setBackground(new Color(34, 36, 39));
		lblStreet.setBounds(151, 435, 60, 25);
		signUpPanel.add(lblStreet);
		
		buildingNumberComboBox = new JComboBox();
		buildingNumberComboBox.setFocusTraversalKeysEnabled(false);
		buildingNumberComboBox.setFocusable(false);
		buildingNumberComboBox.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buildingNumberComboBox.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(51, 204, 153)));
		buildingNumberComboBox.setBackground(new Color(34, 36, 39));
		buildingNumberComboBox.setBounds(82, 546, 198, 22);
		buildingNumberComboBox.setUI(new BasicComboBoxUI());
		signUpPanel.add(buildingNumberComboBox);
		
		lblBuildingNumber = new JLabel("Building Number");
		lblBuildingNumber.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblBuildingNumber.setForeground(Color.WHITE);
		lblBuildingNumber.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblBuildingNumber.setBackground(new Color(34, 36, 39));
		lblBuildingNumber.setBounds(105, 508, 153, 25);
		signUpPanel.add(lblBuildingNumber);
		frmLoginPage.setLocationRelativeTo(null);
		
	
		
	}
}
