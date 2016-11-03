package resources;

public class TimeCounter {
	private long startTime;
	private long totalTime;
	private boolean isOn;
	
	public TimeCounter(long totalTime){
		if(totalTime <= 0){
			this.totalTime = 0;
		}else{
			this.totalTime = totalTime;
		}
		this.totalTime = 0;
		this.isOn = false;
	}
	
	public void start(){
		this.startTime = System.currentTimeMillis();
		this.isOn = true;
	}
	
	public void pause(){
		if(isOn){
			this.totalTime += System.currentTimeMillis() - startTime;
			this.startTime = 0; //Double pause should be accounted for, this is not needed?
			this.isOn = false;
		}else{
			//
		}
	}
	
	public void reset(){
		this.startTime = 0;
		this.totalTime = 0;
	}
	
	public long getTotalTime(){
		if(isOn){
			return totalTime += System.currentTimeMillis() - startTime;
		}else{
			return totalTime;
		}
	}
	
	public long getTotalTimeSeconds(){
		return getTotalTime() / 1000;
	}
}
