package core;

public class Statistics {
	public static long totalTime = 1;
	public static int totalTasks = 0;
	public static int totalPages = 0;
	public static double averagePageTime = 1;
	boolean dataExists = false;
	
	public void averageTime(){
		if(dataExists){
			averagePageTime = (double) (totalTime / totalPages / 60000);
		}
	}
	
}
