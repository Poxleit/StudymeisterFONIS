package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import core.Task;

public class StudymeisterFONIS extends JFrame {

	private static final long serialVersionUID = 5447418481059235006L;
	
	
	//Days
	private LinkedList<Task> tasks = new LinkedList<Task>();
	private boolean listWasLoaded = false;
	private int currentDay = new GregorianCalendar().get(Calendar.DAY_OF_WEEK) - 1;
	//
	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudymeisterFONIS frame = new StudymeisterFONIS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public StudymeisterFONIS() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	
	private void save(){
		if(listWasLoaded || !(new File("tasks.out").isFile())){ //Counts first load as well
			try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("tasks.out")))){
				for(int i = 0; i < tasks.size(); i++){
					out.writeObject(tasks.get(i));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			//Displays a message you need to load the list first
		}
	}
	
	private void load(){
		if(new File("tasks.out").isFile()){ //Check if there is something to load
			try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("tasks.out")))){
				while(true){
					Task task = (Task) ois.readObject();
					tasks.add(task);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			//Displays a message that no data about tasks was found.
		}
		listWasLoaded = true;
	}

}
