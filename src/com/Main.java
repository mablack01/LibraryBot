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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
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
		email = new JTextField();
		email.setColumns(10);

		JLabel passwordLabel = new JLabel("Password:");
		password = new JTextField();
		password.setColumns(10);

		JLabel periodLabel = new JLabel("Period:");
		period = new JComboBox <String> ();
		period.setModel(new DefaultComboBoxModel <String> (new String[] {"R1", "R2", "R3", "R4", "R6", "R7"}));
		period.addActionListener(this);
		period.setActionCommand("chosePeriod");

		saveAccount = new JButton("Save Account");
		saveAccount.addActionListener(this);
		saveAccount.setActionCommand("saveAccount");

		loadAccount = new JButton("Load Account");
		loadAccount.addActionListener(this);
		loadAccount.setActionCommand("loadAccount");

		startBot = new JButton("Start Bot!");
		startBot.addActionListener(this);
		startBot.setActionCommand("startBot");

		currentStatus = new JLabel("Current Status:");

		status = new JLabel("Idle...");

		JLabel instructions = new JLabel("Instructions:");

		JLabel line1 = new JLabel("Make sure that you enter all of your proper information into");

		JLabel line2 = new JLabel("the following fields and click \"Save Account\". Also, make sure");

		JLabel line3 = new JLabel("that you have FireFox installed, this is required to run the");

		JLabel line4 = new JLabel("program.");

		JLabel beatTheSixies = new JLabel("ENJOY BEATING THE SIXIES!");
		beatTheSixies.setFont(new Font("Tahoma", Font.BOLD, 16));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(startBot, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(currentStatus, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
												.addComponent(status, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
												.addComponent(instructions)
												.addComponent(line1)
												.addComponent(line2)
												.addComponent(line3)
												.addComponent(line4)
												.addComponent(beatTheSixies))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(password, Alignment.LEADING)
														.addComponent(loadAccount, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(saveAccount, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(periodLabel, Alignment.LEADING)
														.addComponent(passwordLabel, Alignment.LEADING)
														.addComponent(emailLabel, Alignment.LEADING)
														.addComponent(email, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
														.addComponent(period, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(18)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(emailLabel)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(passwordLabel)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(11)
														.addComponent(periodLabel)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(period, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(saveAccount)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
																.addComponent(loadAccount)
																.addComponent(status)))
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addGap(13)
																		.addComponent(line1)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(line2)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(line3)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(line4)
																		.addGap(36)
																		.addComponent(beatTheSixies)
																		.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																				.addComponent(currentStatus)
																				.addComponent(startBot, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))))
																				.addGroup(gl_contentPane.createSequentialGroup()
																						.addContainerGap()
																						.addComponent(instructions)))
																						.addContainerGap(38, Short.MAX_VALUE))
				);
		contentPane.setLayout(gl_contentPane);
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
