package core;

import java.io.Serializable;

import resources.Day;

public class Task implements Serializable {

	private static final long serialVersionUID = 1L;
	private String taskName;
	private String taskDescription;
	private int totalPages;
	private int pagesDone;
	private Day day;
	

	public Task(String taskName, String taskDescription, int totalPages, int taskDay) throws Exception {

		if (taskName.isEmpty() || taskName == null) {
			throw new Exception("Task name cannot be empty!");
		} else {
			this.taskName = taskName;
		}
		if (taskDescription.isEmpty() || taskDescription == null) {
			throw new Exception("Task description cannot be empty!");
		} else {
			this.taskDescription = taskDescription;
		}
		if (totalPages <= 0) {
			throw new Exception("Task description cannot be empty!");
		} else {
			this.totalPages = totalPages;
		}
		if(taskDay >= 0 && taskDay <=6){
			day = Day.values()[taskDay];
		}else{
			throw new Exception("Invalid day number!");
		}
	}

	public String getTaskName() {
		return taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public int getPagesDone() {
		return pagesDone;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void finishAPage() {
		if (pagesDone < totalPages) {
			pagesDone++;
		}
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setPagesDone(int pagesDone) {
		this.pagesDone = pagesDone;
	}
	
	
	

}
