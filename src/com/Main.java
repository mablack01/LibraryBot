package com;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Main class, handles the main interface and data.
 * @author Miles Black
 * April 15, 2014
 */

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = -2268907443031783190L;

	/**
	 * Fields
	 */
	private JButton loadAccount;
	private JButton saveAccount;
	private JButton startBot;
	private JComboBox <String> period;
	private JLabel currentStatus;
	private static JLabel status;
	private JPanel contentPane;
	private JTextField email;
	private JTextField password;

	private static Main frame;
	private User user = new User();
	
	private int selectedPeriod;

	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Creates the main frame.
	 */
	public Main() {
		setTitle("Library Bot by Miles Black");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(350, 23, 28, 14);
		email = new JTextField();
		email.setBounds(350, 48, 127, 20);
		email.setColumns(10);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(350, 79, 50, 14);
		password = new JTextField();
		password.setBounds(350, 104, 127, 20);
		password.setColumns(10);

		JLabel periodLabel = new JLabel("Period:");
		periodLabel.setBounds(350, 135, 34, 14);
		period = new JComboBox <String> ();
		period.setBounds(350, 155, 127, 20);
		period.setModel(new DefaultComboBoxModel <String> (new String[] {"R1", "R2", "R3", "R4", "R6", "R7"}));
		period.addActionListener(this);
		period.setActionCommand("chosePeriod");

		saveAccount = new JButton("Save Account");
		saveAccount.setBounds(350, 186, 127, 23);
		saveAccount.addActionListener(this);
		saveAccount.setActionCommand("saveAccount");

		loadAccount = new JButton("Load Account");
		loadAccount.setBounds(350, 220, 127, 23);
		loadAccount.addActionListener(this);
		loadAccount.setActionCommand("loadAccount");

		startBot = new JButton("Start Bot!");
		startBot.setBounds(15, 200, 120, 43);
		startBot.addActionListener(this);
		startBot.setActionCommand("startBot");

		currentStatus = new JLabel("Current Status:");
		currentStatus.setBounds(153, 200, 191, 14);

		status = new JLabel("Idle...");
		status.setBounds(153, 224, 191, 14);

		JLabel instructions = new JLabel("Instructions:");
		instructions.setBounds(15, 16, 61, 14);

		JLabel line1 = new JLabel("Make sure that you enter all of your proper information into");
		line1.setBounds(15, 36, 286, 14);

		JLabel line2 = new JLabel("the following fields and click \"Save Account\". Also, make sure");
		line2.setBounds(15, 56, 292, 14);

		JLabel line3 = new JLabel("that you have FireFox installed, this is required to run the");
		line3.setBounds(15, 76, 277, 14);

		JLabel line4 = new JLabel("program.");
		line4.setBounds(15, 96, 44, 14);

		JLabel beatTheSixies = new JLabel("ENJOY BEATING THE SIXIES!");
		beatTheSixies.setBounds(15, 146, 236, 20);
		beatTheSixies.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		contentPane.setLayout(null);
		contentPane.add(startBot);
		contentPane.add(currentStatus);
		contentPane.add(status);
		contentPane.add(instructions);
		contentPane.add(line1);
		contentPane.add(line2);
		contentPane.add(line3);
		contentPane.add(line4);
		contentPane.add(beatTheSixies);
		contentPane.add(password);
		contentPane.add(loadAccount);
		contentPane.add(saveAccount);
		contentPane.add(periodLabel);
		contentPane.add(passwordLabel);
		contentPane.add(emailLabel);
		contentPane.add(email);
		contentPane.add(period);
	}

	/**
	 * Handles the actions performed in the main frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("chosePeriod")) {
			@SuppressWarnings("unchecked")
			JComboBox <String> period = (JComboBox <String>)e.getSource();
			if (period.getSelectedIndex() == 0)
				selectedPeriod = 1;
			else if (period.getSelectedIndex() == 1)
				selectedPeriod = 2;
			else if (period.getSelectedIndex() == 2)
				selectedPeriod = 3;
			else if (period.getSelectedIndex() == 3)
				selectedPeriod = 4;
			else if (period.getSelectedIndex() == 4)
				selectedPeriod = 6;
			else if (period.getSelectedIndex() == 5)
				selectedPeriod = 7;
		} else if (e.getActionCommand().equals("saveAccount")) {
			if (user == null) {
				user = new User(email.getText(), password.getText(), selectedPeriod);
			} else {
				user.setEmail(email.getText());
				user.setPassword(password.getText());
				user.setPeriod(selectedPeriod);
			}
			try {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("account.ser"));
				out.writeObject(user);
				out.close();
			} catch(Throwable e1) {

			}
		} else if (e.getActionCommand().equals("loadAccount")) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("account.ser"));
				user = (User) in.readObject();
				in.close();
			} catch(Throwable e1) {

			}
			email.setText(user.getEmail());
			password.setText(user.getPassword());
			if (user.getPeriod() == 1)
				period.setSelectedIndex(0);
			else if (user.getPeriod() == 2)
				period.setSelectedIndex(1);
			else if (user.getPeriod() == 3)
				period.setSelectedIndex(2);
			else if (user.getPeriod() == 4)
				period.setSelectedIndex(3);
			else if (user.getPeriod() == 6)
				period.setSelectedIndex(4);
			else if (user.getPeriod() == 7)
				period.setSelectedIndex(5);
		} else if (e.getActionCommand().equals("startBot")) {
			if (user.getEmail() == null || user.getPassword() == null || user.getPeriod() <= -1) {
				JOptionPane.showMessageDialog(frame, "Please correctly fill out your information.");
				return;
			}
			final String time = getTime(user.getPeriod());
			Timer timer = new Timer();
			timer.schedule( new TimerTask() {
				@Override
				public void run() {
					if (getTime().contains("7:01")) {
						try {         
							WebDriver driver = new FirefoxDriver();
							driver.get("https://pickatime.com/client?ven=11607876&login=true&email="+user.getEmail()+"&password="+user.getPassword()+"");
							driver.findElement(By.linkText(time)).click();
							driver.findElement(By.name("bookIt")).click();
							driver.findElement(By.name("ret")).click();
							setStatus("Successfully signed up for the library!");
							cancel();
						} catch(Exception ex) {
							WebDriver driver = new FirefoxDriver();
							driver.get("https://pickatime.com/client?ven=11607876&login=true&email="+user.getEmail()+"&password="+user.getPassword()+"");
							driver.findElement(By.linkText("Study Hall Signup")).click();
							driver.findElement(By.linkText(time)).click();
							driver.findElement(By.name("bookIt")).click();
							driver.findElement(By.name("ret")).click();
							setStatus("Successfully signed up for the library!");
							cancel();
						}
					} else if (getTime().contains("7:00")) {
						setStatus("It is now 7PM, preparing to sign you up...");
					} else {
						setStatus("Waiting for it to be 7PM...");
					}
				}
			}, 0, 60*1000);
		}
	}

	/**
	 * Grabs the time in the proper format for the bot to check.
	 * @return The local computer time.
	 */
	public static String getTime() { 
		SimpleDateFormat format = new SimpleDateFormat("h:mm"); 
		return format.format(new Date());
	}

	/**
	 * Sets the status for the main frame.
	 * @param state The current status for the main frame.
	 */
	public static void setStatus(String state) {
		status.setText(state);
	}

	/**
	 * Grabs the text to search for on the sign up page.
	 * @param period The user's period to determine which time to select.
	 * @return The String that Selenium searches for on the page.
	 */
	public static String getTime(int period) {
		switch(period) {
		case 1:
			return "7:55 AM";
		case 2:
			return "8:59 AM";
		case 3:
			return "9:48 AM";
		case 4:
			return "10:37 AM";
		case 6:
			return "12:42 PM";
		case 7:
			return "1:30 PM";
		}
		return null;
	}
}
