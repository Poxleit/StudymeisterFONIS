package gui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import core.Statistics;

public class OverallStatistics extends JDialog {

	private static final long serialVersionUID = 2402206126766189068L;
	private JTextArea textArea;

	public OverallStatistics() {
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
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				textArea = new JTextArea();
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setEditable(false);
				scrollPane.setViewportView(textArea);
			}
			{
				JLabel lblStatistics = new JLabel("Lifetime Statistics");
				scrollPane.setColumnHeaderView(lblStatistics);
			}
		}
		boolean divideByZero = false;
		if (Statistics.totalPages == 0) {
			Statistics.totalPages++;
		}
		textArea.setText("Total tasks done :	" + Statistics.totalTasks + " tasks\nTotal pages done :	"
				+ Statistics.totalPages + " pages\nTotal time spent studying:	" + Statistics.totalTime / 1000 / 60
				+ " minutes\nAverage time per page:	" + Statistics.totalTime / 1000 / 60 / Statistics.totalPages + " minutes per page");
		if (divideByZero) {
			Statistics.totalPages--;
		}
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

	}

}
