package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class TaskAdder extends JDialog {

	
	private static final long serialVersionUID = 6719122565413781805L;
	private JTextField textField;
	private JTextField textField_1;

	public TaskAdder() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Done");
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
				textField = new JTextField();
				panel.add(textField, "cell 0 1,growx");
				textField.setColumns(10);
			}
			{
				JLabel lblTotalPages = new JLabel("Total Pages");
				panel.add(lblTotalPages, "cell 0 2");
			}
			{
				textField_1 = new JTextField();
				panel.add(textField_1, "cell 0 3,growx");
				textField_1.setColumns(10);
			}
			{
				JLabel lblSelectDay = new JLabel("Select Day");
				panel.add(lblSelectDay, "cell 0 4");
			}
			{
				JComboBox comboBox = new JComboBox();
				panel.add(comboBox, "cell 0 5,growx");
			}
			{
				JButton btnAddTask = new JButton("Add Task");
				panel.add(btnAddTask, "cell 0 8");
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				JLabel lblTaskName_1 = new JLabel("Task Name");
				scrollPane.setColumnHeaderView(lblTaskName_1);
			}
			{
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				scrollPane.setViewportView(textArea);
			}
		}
	}

}
