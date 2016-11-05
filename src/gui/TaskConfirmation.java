package gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaskConfirmation extends JDialog {
	
	private static final long serialVersionUID = -3538632613068412342L;
	private JTextField txtTaskHasBeen;

	public TaskConfirmation(boolean confirmation) {
		setTitle("Task added");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		txtTaskHasBeen = new JTextField();
		txtTaskHasBeen.setHorizontalAlignment(SwingConstants.CENTER);
		txtTaskHasBeen.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		txtTaskHasBeen.setEditable(false);
		if(confirmation) txtTaskHasBeen.setText("TASK HAS BEEN ADDED!");
		else txtTaskHasBeen.setText("TASK HAS NOT BEEN ADDED! CHECK FORMAT!");
		getContentPane().add(txtTaskHasBeen, BorderLayout.CENTER);
		txtTaskHasBeen.setColumns(10);
		
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
	}

}
