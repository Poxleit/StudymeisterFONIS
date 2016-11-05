package gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TaskConfirmation extends JDialog {
	private JTextField txtTaskHasBeen;

	public TaskConfirmation() {
		setTitle("Task added");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		txtTaskHasBeen = new JTextField();
		txtTaskHasBeen.setHorizontalAlignment(SwingConstants.CENTER);
		txtTaskHasBeen.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtTaskHasBeen.setEditable(false);
		txtTaskHasBeen.setText("TASK HAS BEEN ADDED!");
		getContentPane().add(txtTaskHasBeen, BorderLayout.CENTER);
		txtTaskHasBeen.setColumns(10);
		
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	}

}
