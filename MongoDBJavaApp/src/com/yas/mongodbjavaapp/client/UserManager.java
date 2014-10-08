package com.yas.mongodbjavaapp.client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.yas.mongodbjavaapp.dao.DataManager;
import com.yas.mongodbjavaapp.domain.User;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.Color;

/**
 * @author YAS
 * @version 1.2
 * @category Used to get user inputs to manage users in the app.
 */
public class UserManager extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;

	private DataManager dataMgr = new DataManager();

	private final String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private final String digitsRegex = "[0-9]+";
	private JTable table;
	private JTextField txtUpdateId;
	private JTextField txtUpdateFirstName;
	private JTextField txtUpdateLastName;
	private JTextField txtUpdateEmail;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable tableAllUsers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManager frame = new UserManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public UserManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 46, 568, 284);
		contentPane.add(tabbedPane);

		JPanel addUserPanel = new JPanel();
		tabbedPane.addTab("Add User", null, addUserPanel, null);
		addUserPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("User ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(58, 29, 58, 16);
		addUserPanel.add(lblNewLabel);

		txtId = new JTextField();
		txtId.setBounds(126, 28, 294, 20);
		addUserPanel.add(txtId);
		txtId.setColumns(10);

		JLabel label = new JLabel("First Name");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(58, 63, 58, 16);
		addUserPanel.add(label);

		JLabel label_1 = new JLabel("Last Name");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(58, 100, 58, 16);
		addUserPanel.add(label_1);

		JLabel label_2 = new JLabel("Email");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(58, 138, 58, 16);
		addUserPanel.add(label_2);

		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(126, 62, 294, 20);
		addUserPanel.add(txtFirstName);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(126, 99, 294, 20);
		addUserPanel.add(txtLastName);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(126, 137, 294, 20);
		addUserPanel.add(txtEmail);

		final JLabel addUserInfo = new JLabel("");
		addUserInfo.setBounds(163, 217, 257, 14);
		addUserPanel.add(addUserInfo);

		// validate form inputs and pass new user to insert into data manager
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// check id is integer value or not
				if (txtId.getText().matches(digitsRegex)) {

					// check user email is valid or not
					if (txtEmail.getText().matches(emailRegex)) {

						if (txtFirstName.getText().equals("")
								|| txtLastName.getText().equals("")) {
							addUserInfo.setText("Fill all the fileds.");
						} else {
							// create new user object and pass it to insert
							User newUser = new User();
							newUser.setId(Integer.parseInt(txtId.getText()));
							newUser.setFirstName(txtFirstName.getText());
							newUser.setLastName(txtLastName.getText());
							newUser.setEmail(txtEmail.getText());
							dataMgr.addUser(newUser);
							addUserInfo.setText("New user added succesfully.");

							txtId.setText("");
							txtFirstName.setText("");
							txtLastName.setText("");
							txtEmail.setText("");
							// addUserInfo.setText("");
						}

					} else {
						// not match email
						addUserInfo.setText("Email is not valid one.");
					}
				} else {
					// not match id
					addUserInfo
							.setText("Id should contains only digits(integer).");
				}
			}
		});
		btnSave.setBounds(331, 185, 89, 23);
		addUserPanel.add(btnSave);

		// clear new user tab form
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtId.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");
				txtEmail.setText("");
				addUserInfo.setText("");
			}
		});
		btnClear.setBounds(232, 185, 89, 23);
		addUserPanel.add(btnClear);

		JPanel updateUserPanel = new JPanel();
		tabbedPane.addTab("Update User", null, updateUserPanel, null);
		updateUserPanel.setLayout(null);

		// updateUserPanel.add

		table = new JTable();
		table.setBounds(44, 125, 177, -59);
		updateUserPanel.add(table);

		JLabel label_3 = new JLabel("User ID");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(92, 53, 58, 16);
		updateUserPanel.add(label_3);

		txtUpdateId = new JTextField();
		txtUpdateId.setColumns(10);
		txtUpdateId.setBounds(160, 52, 294, 20);
		updateUserPanel.add(txtUpdateId);

		JLabel label_4 = new JLabel("First Name");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(92, 87, 58, 16);
		updateUserPanel.add(label_4);

		txtUpdateFirstName = new JTextField();
		txtUpdateFirstName.setColumns(10);
		txtUpdateFirstName.setBounds(160, 86, 294, 20);
		updateUserPanel.add(txtUpdateFirstName);

		JLabel label_5 = new JLabel("Last Name");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(92, 124, 58, 16);
		updateUserPanel.add(label_5);

		txtUpdateLastName = new JTextField();
		txtUpdateLastName.setColumns(10);
		txtUpdateLastName.setBounds(160, 123, 294, 20);
		updateUserPanel.add(txtUpdateLastName);

		JLabel label_6 = new JLabel("Email");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(92, 162, 58, 16);
		updateUserPanel.add(label_6);

		txtUpdateEmail = new JTextField();
		txtUpdateEmail.setColumns(10);
		txtUpdateEmail.setBounds(160, 161, 294, 20);
		updateUserPanel.add(txtUpdateEmail);

		final JLabel updateInfo = new JLabel("");
		updateInfo.setBounds(170, 192, 271, 14);
		updateUserPanel.add(updateInfo);

		// update user
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// check id is integer value or not
				if (txtUpdateId.getText().matches(digitsRegex)) {

					// check user email is valid or not
					if (txtUpdateEmail.getText().matches(emailRegex)) {

						if (txtUpdateFirstName.getText().equals("")
								|| txtUpdateLastName.getText().equals("")) {
							updateInfo.setText("Fill all the fileds.");
						} else {
							// create new user object and pass it to insert
							User updatedUser = new User();
							updatedUser.setId(Integer.parseInt(txtUpdateId
									.getText()));
							updatedUser.setFirstName(txtUpdateFirstName
									.getText());
							updatedUser.setLastName(txtUpdateLastName.getText());
							updatedUser.setEmail(txtUpdateEmail.getText());
							dataMgr.updateUser(updatedUser);
							updateInfo.setText("User updated succesfully.");
						}

					} else {
						// not match email
						updateInfo.setText("Email is not valid one.");
					}
				} else {
					// not match id
					updateInfo
							.setText("Id should contains only digits(integer).");
				}

			}
		});
		updateBtn.setBounds(365, 209, 89, 23);
		updateUserPanel.add(updateBtn);

		// clear update user from
		JButton updateCancelBtn = new JButton("Clear");
		updateCancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				clearUpdateFormData();
				updateInfo.setText("");

			}
		});
		updateCancelBtn.setBounds(266, 209, 89, 23);
		updateUserPanel.add(updateCancelBtn);

		final JComboBox<String> comboBoxUserFName = new JComboBox<String>();
		comboBoxUserFName.setBounds(205, 9, 189, 20);
		updateUserPanel.add(comboBoxUserFName);

		// delete user
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dataMgr.deleteUser(Integer.parseInt(txtUpdateId.getText()));
				clearUpdateFormData();
				comboBoxUserFName.removeAllItems();
				dataMgr.getAllUsers();
				for (User usr : dataMgr.getAllUsers()) {
					comboBoxUserFName.addItem(usr.getFirstName());
				}

			}
		});
		btnDelete.setBounds(167, 209, 89, 23);
		updateUserPanel.add(btnDelete);

		JLabel lblNewLabel_1 = new JLabel("Search by first name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(71, 11, 124, 14);
		updateUserPanel.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 36, 510, 186);
		panel.add(scrollPane);

		tableAllUsers = new JTable();
		// make model to set
		final DefaultTableModel tbleModel = new DefaultTableModel(new String[] {
				"User ID", "First Name", "Last Name", "Email" }, 0);

		tableAllUsers.setModel(tbleModel);
		tbleModel.setRowCount(0);
		for (User usr : dataMgr.getAllUsers()) {

			Object[] objs = { usr.getId(), usr.getFirstName(),
					usr.getLastName(), usr.getEmail() };
			tbleModel.addRow(objs);
		}

		scrollPane.setViewportView(tableAllUsers);

		// get user by drop down box
		comboBoxUserFName.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				if (e.getStateChange() == 1) {
					clearUpdateFormData();

					User usr = dataMgr
							.getUserByFName((String) comboBoxUserFName
									.getSelectedItem());
					txtUpdateId.setText(String.valueOf(usr.getId()));
					txtUpdateFirstName.setText(usr.getFirstName());
					txtUpdateLastName.setText(usr.getLastName());
					txtUpdateEmail.setText(usr.getEmail());
					updateInfo.setText("");
				}
			}
		});

		// set all users first names into combo box
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedIndex() == 1) {
					comboBoxUserFName.removeAllItems();
					dataMgr.getAllUsers();
					for (User usr : dataMgr.getAllUsers()) {
						comboBoxUserFName.addItem(usr.getFirstName());
					}
				} else if (tabbedPane.getSelectedIndex() == 2) {
					tbleModel.setRowCount(0);
					for (User usr : dataMgr.getAllUsers()) {

						Object[] objs = { usr.getId(), usr.getFirstName(),
								usr.getLastName(), usr.getEmail() };
						tbleModel.addRow(objs);
					}
				}
			}
		});

		JLabel lblUserManager = new JLabel("User Manager ");
		lblUserManager.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUserManager.setBounds(10, 11, 259, 24);
		contentPane.add(lblUserManager);

		// exit the mongoDBjavaapp
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});
		btnNewButton.setBounds(489, 341, 89, 23);
		contentPane.add(btnNewButton);
	}

	private void clearUpdateFormData() {
		txtUpdateId.setText("");
		txtUpdateFirstName.setText("");
		txtUpdateLastName.setText("");
		txtUpdateEmail.setText("");
	}
}
