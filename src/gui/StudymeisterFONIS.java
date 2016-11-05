package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import core.Task;
import net.miginfocom.swing.MigLayout;
import resources.Day;
import resources.TimeCounter;
import javax.swing.JCheckBox;

public class StudymeisterFONIS extends JFrame {

	private static final long serialVersionUID = 5447418481059235006L;

	// Days
	private LinkedList<Task> tasks = new LinkedList<Task>();
	private boolean listWasLoaded = false;
	private TimeCounter time = new TimeCounter(0);
	private int currentDay = new GregorianCalendar().get(Calendar.DAY_OF_WEEK) - 1;
	private Task currentTask;
	private String selectedTask;
	//
	private JPanel contentPane;
	private JTextField txtCurrentDay;
	private JTextField txtPagesDone;
	private JTextField txtTotalPages;
	private JComboBox<String> comboBoxTasks;
	private JLabel lblTaskSelection;
	private JLabel lblTaskname;
	private JTextArea txtTaskDescription;
	private JButton btnFinishPage;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
					StudymeisterFONIS frame = new StudymeisterFONIS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StudymeisterFONIS() {
		setTitle("StudymeisterFONIS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load();
			}
		});
		mnFile.add(mntmLoad);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Ask user to save, then exit
				exit();
			}
		});
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][]"));

		JLabel lblUserName = new JLabel("Welcome, " + System.getProperty("user.name") + "!");
		lblUserName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblUserName, "cell 0 0,alignx center,aligny center");

		JLabel lblTodayIs = new JLabel("Today is : ");
		lblTodayIs.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTodayIs.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTodayIs, "cell 0 2,alignx center,aligny center");

		txtCurrentDay = new JTextField();
		txtCurrentDay.setEditable(false);
		txtCurrentDay.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrentDay.setText(Day.values()[currentDay].name());
		panel.add(txtCurrentDay, "cell 0 3,growx,aligny center");
		txtCurrentDay.setColumns(10);

		lblTaskSelection = new JLabel("No tasks for today!");
		panel.add(lblTaskSelection, "cell 0 4,alignx center,aligny center");

		comboBoxTasks = new JComboBox<String>();
		comboBoxTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxTasks.getSelectedItem() != null) {
					selectedTask = comboBoxTasks.getSelectedItem().toString();
				}
				for (int i = 0; i < tasks.size(); i++) {
					if (tasks.get(i).getTaskName().equals(selectedTask)) {
						currentTask = tasks.get(i);
					}
				}
				displayTask();
			}
		});
		comboBoxTasks.setEnabled(false);
		panel.add(comboBoxTasks, "cell 0 5,growx,aligny center");

		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskAdder ta = new TaskAdder(tasks, currentDay);
				ta.setVisible(true);
				while(ta.isVisible()){}
				listTasks();
			}
		});
		panel.add(btnAddTask, "cell 0 8,alignx center,aligny center");

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JCheckBox chckbxStudying = new JCheckBox("Studying");
		chckbxStudying.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_1.add(chckbxStudying);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		scrollPane.setRowHeaderView(panel_2);
		panel_2.setLayout(new MigLayout("", "[grow]", "[][][][][][][]"));

		JLabel lblPagesDone = new JLabel("Pages done");
		panel_2.add(lblPagesDone, "cell 0 0");

		txtPagesDone = new JTextField();
		txtPagesDone.setEditable(false);
		panel_2.add(txtPagesDone, "cell 0 1,growx");
		txtPagesDone.setColumns(10);

		JLabel lblTotalPages = new JLabel("Total pages");
		panel_2.add(lblTotalPages, "cell 0 2");

		txtTotalPages = new JTextField();
		txtTotalPages.setEditable(false);
		panel_2.add(txtTotalPages, "cell 0 3,growx");
		txtTotalPages.setColumns(10);

		btnFinishPage = new JButton("Finish Page");
		btnFinishPage.setEnabled(false);
		btnFinishPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentTask.finishAPage();
				txtPagesDone.setText("" + currentTask.getPagesDone());
				if (currentTask.getPagesDone() == currentTask.getTotalPages()) {
					// Display message and remove task from list
					tasks.remove(currentTask);
					if (!tasks.isEmpty()) {
						currentTask = tasks.getFirst();
						listTasks();
					}else{
						listTasks();
					}
				}
			}
		});
		panel_2.add(btnFinishPage, "cell 0 6");

		txtTaskDescription = new JTextArea();
		txtTaskDescription.setEditable(false);
		txtTaskDescription.setLineWrap(true);
		scrollPane.setViewportView(txtTaskDescription);
		txtTaskDescription.setWrapStyleWord(true);

		lblTaskname = new JLabel("TaskName");
		scrollPane.setColumnHeaderView(lblTaskname);

	}

	private void save() {
		if (listWasLoaded || !(new File("tasks.out").isFile())) { // Counts
																	// first
																	// load as
																	// well
			try (ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("tasks.out")))) {
				for (int i = 0; i < tasks.size(); i++) {
					out.writeObject(tasks.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// Displays a message you need to load the list first
		}
	}

	private void load() {
		if (new File("tasks.out").isFile()) { // Check if there is something to
												// load
			try (ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream("tasks.out")))) {
				while (true) {
					Task task = (Task) ois.readObject();
					tasks.add(task);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// Displays a message that no data about tasks was found.
		}
		listWasLoaded = true;
	}

	private void listTasks() {
		if (!tasks.isEmpty()) {
			boolean tasksForToday = false;
			comboBoxTasks.setEnabled(false);
			comboBoxTasks.removeAllItems();
			// Make it select previously selected item if cb already filled
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).getDay() == Day.values()[currentDay]) {
					comboBoxTasks.addItem(tasks.get(i).getTaskName());
					tasksForToday = true;
				}
			}
			if (tasksForToday) {
				lblTaskSelection.setText("Select a task: ");
				comboBoxTasks.setEnabled(true);
				btnFinishPage.setEnabled(true);
			} else {
				lblTaskSelection.setText("No tasks for today!");
				comboBoxTasks.setEnabled(false);
				btnFinishPage.setEnabled(false);
			}
		} else {
			comboBoxTasks.removeAllItems();
			lblTaskSelection.setText("No tasks for today!");
			comboBoxTasks.setEnabled(false);
			btnFinishPage.setEnabled(false);
			lblTaskname.setText("");
			txtTaskDescription.setText("");
			txtTotalPages.setText("");
			txtPagesDone.setText("");
		}
	}

	private void displayTask() {
		lblTaskname.setText(currentTask.getTaskName());
		txtTaskDescription.setText(currentTask.getTaskDescription());
		txtTotalPages.setText("" + currentTask.getTotalPages());
		txtPagesDone.setText("" + currentTask.getPagesDone());
	}

	private void exit() {
		System.exit(0);
	}

}
