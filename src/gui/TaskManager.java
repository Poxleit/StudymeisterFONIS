package gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.Task;
import net.miginfocom.swing.MigLayout;
import resources.Day;

public class TaskManager extends JDialog {

	private static final long serialVersionUID = 3943723375796586889L;
	private JComboBox<Day> comboBoxFrom;
	private JComboBox<Day> comboBoxTo;
	private JComboBox<String> comboBoxTasksTo;
	private JComboBox<String> comboBoxTasksFrom;

	public TaskManager(LinkedList<Task> tasks, int currentDay) {
		setTitle("StudymeisterFONIS - Task Manager");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnDone = new JButton("Done");
				btnDone.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				btnDone.setActionCommand("OK");
				buttonPane.add(btnDone);
				getRootPane().setDefaultButton(btnDone);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.WEST);
			panel.setLayout(new MigLayout("", "[grow]", "[][][grow]"));
			{
				JLabel lblFromList = new JLabel("From Day");
				panel.add(lblFromList, "cell 0 0");
			}
			{
				JLabel lblTasks = new JLabel("Tasks");
				panel.add(lblTasks, "cell 0 1,alignx center");
			}
			{
				comboBoxTasksFrom = new JComboBox<String>();
				panel.add(comboBoxTasksFrom, "cell 0 2,growx");
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.EAST);
			panel.setLayout(new MigLayout("", "[grow]", "[][][grow]"));
			{
				JLabel lblToList = new JLabel("To Day");
				panel.add(lblToList, "cell 0 0");
			}
			{
				JLabel lblTasks_1 = new JLabel("Tasks");
				panel.add(lblTasks_1, "cell 0 1");
			}
			{
				comboBoxTasksTo = new JComboBox<String>();
				panel.add(comboBoxTasksTo, "cell 0 2,growx");
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.WEST);
				panel_1.setLayout(new MigLayout("", "[grow]", "[]"));
				{
					comboBoxFrom = new JComboBox<Day>();
					comboBoxFrom.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							showTasks(tasks);
						}
					});
					panel_1.add(comboBoxFrom, "cell 0 0,growx");
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.EAST);
				panel_1.setLayout(new MigLayout("", "[grow]", "[]"));
				{
					comboBoxTo = new JComboBox<Day>();
					comboBoxTo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							showTasks(tasks);
						}
					});
					panel_1.add(comboBoxTo, "cell 0 0,growx");
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.SOUTH);
				{
					JButton btnTransfer = new JButton("Transfer");
					btnTransfer.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							for(int i = 0; i < tasks.size(); i++){
								if(tasks.get(i).getTaskName().equals(comboBoxTasksFrom.getSelectedItem())){
									tasks.get(i).setDay(Day.values()[comboBoxTo.getSelectedIndex()]);
									break;
								}
							}
							showTasks(tasks);
						}
					});
					btnTransfer.setPreferredSize(new Dimension(80, 23));
					btnTransfer.setHorizontalTextPosition(SwingConstants.CENTER);
					panel_1.add(btnTransfer);
				}
				{
					JButton btnDelete = new JButton("Delete");
					btnDelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							for(int i = 0; i < tasks.size(); i++){
								if(tasks.get(i).getTaskName().equals(comboBoxTasksFrom.getSelectedItem())){
									tasks.remove(i);
									break;
								}
							}
							showTasks(tasks);
						}
					});
					btnDelete.setPreferredSize(new Dimension(80, 23));
					panel_1.add(btnDelete);
				}
			}
		}
		
		for(int i = 0; i < 7; i++){
			comboBoxFrom.addItem(Day.values()[i]);
			comboBoxTo.addItem(Day.values()[i]);
		}
		
		comboBoxFrom.setSelectedItem(Day.values()[currentDay]);
		comboBoxTo.setSelectedItem(Day.values()[currentDay]);

		
		showTasks(tasks);
		
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	}

	private void showTasks(LinkedList<Task> tasks){
		comboBoxTasksTo.removeAllItems();
		comboBoxTasksFrom.removeAllItems();
		for(int i = 0; i < tasks.size(); i++){
			if(tasks.get(i).getDay() == comboBoxFrom.getSelectedItem()){
				comboBoxTasksFrom.addItem(tasks.get(i).getTaskName());
			}
			if(tasks.get(i).getDay() == comboBoxTo.getSelectedItem()){
				comboBoxTasksTo.addItem(tasks.get(i).getTaskName());
			}
		}
	}
}
