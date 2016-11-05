package gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import core.Task;
import net.miginfocom.swing.MigLayout;
import resources.Day;

public class TaskAdder extends JDialog {

	private static final long serialVersionUID = 6719122565413781805L;
	private JTextField txtTaskName;
	private JTextField txtTotalPages;
	private JTextArea txtTaskDescription;
	private JComboBox<Day> cbDays;

	public TaskAdder(LinkedList<Task> tasks, int currentDay) {
		setTitle("StudymeisterFONIS - Task Adder");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Done");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				{
					JButton btnAddTask = new JButton("Add Task");
					buttonPane.add(btnAddTask);
					btnAddTask.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String taskName = txtTaskName.getText();
							String taskDescription = txtTaskDescription.getText();
							int totalPages = Integer.parseInt(txtTotalPages.getText());
							int taskDay = cbDays.getSelectedIndex();
							// Test if the object already exists
							boolean exists = false;
							for (int i = 0; i < tasks.size(); i++) {
								if (tasks.get(i).getTaskName().equals(taskName)) {
									exists = true;
									return;
								}
							}
							if (exists) {
								// Needs to display that the tasks already
								// exists
								return;
							}
							try {
								Task task = new Task(taskName, taskDescription, totalPages, taskDay);
								tasks.add(task);
								TaskConfirmation tc = new TaskConfirmation(true);
								tc.setVisible(true);
								clearFieds();
							} catch (Exception e) {
								System.out.println(0);
							}
						}
					});
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.WEST);
			panel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][]"));
			{
				JLabel lblTaskName = new JLabel("Task Name");
				panel.add(lblTaskName, "cell 0 0");
			}
			{
				txtTaskName = new JTextField();
				panel.add(txtTaskName, "cell 0 1,growx");
				txtTaskName.setColumns(10);
			}
			{
				JLabel lblTotalPages = new JLabel("Total Pages");
				panel.add(lblTotalPages, "cell 0 2");
			}
			{
				txtTotalPages = new JTextField();
				panel.add(txtTotalPages, "cell 0 3,growx");
				txtTotalPages.setColumns(10);
			}
			{
				JLabel lblSelectDay = new JLabel("Select Day");
				panel.add(lblSelectDay, "cell 0 4");
			}
			{
				cbDays = new JComboBox<Day>();
				panel.add(cbDays, "cell 0 5,growx");
				for (int i = 0; i < 7; i++) {
					cbDays.addItem(Day.values()[i]);
				}
				cbDays.setSelectedIndex(currentDay);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				JLabel lblTaskName_1 = new JLabel("Task Description");
				scrollPane.setColumnHeaderView(lblTaskName_1);
			}
			{
				txtTaskDescription = new JTextArea();
				txtTaskDescription.setLineWrap(true);
				txtTaskDescription.setWrapStyleWord(true);
				scrollPane.setViewportView(txtTaskDescription);

			}
		}
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	}

	private void clearFieds() {
		txtTaskName.setText(null);
		txtTaskDescription.setText(null);
		txtTotalPages.setText(null);
	}

}
