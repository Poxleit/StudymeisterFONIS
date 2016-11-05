package core;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import resources.FileManager;

public class Statistics {
	public static long totalTime = 1;
	public static int totalTasks = 0;
	public static int totalPages = 0;
	public static double averagePageTime = 1;
	public static boolean dataExists = false;
	
	public static void averageTime(){
		if(dataExists){
			averagePageTime = (double) (totalTime / totalPages / 60000);
		}
	}
	
	public static void saveStatistics(){
		try(PrintWriter out = new PrintWriter(FileManager.getLocation() + "/statistics.txt")){
		    out.println(totalTime);
		    out.println(totalTasks);
		    out.println(totalPages);
		    out.println(averagePageTime);
		    out.println(dataExists);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void loadStatistics(){
		try(Scanner sc = new Scanner(new File(FileManager.getLocation() + "/statistics.txt"))){
		    totalTime = sc.nextLong();
		    totalTasks = sc.nextInt();
		    totalPages = sc.nextInt();
		    averagePageTime = sc.nextDouble();
		    dataExists = sc.nextBoolean();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
