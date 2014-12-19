package workPool;

import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WorkPool extends Thread{
	private Queue<Job> jobQueue = new LinkedList<Job>();
	public void run(){
		while(true){
			if(!jobQueue.isEmpty()){
				Job curr = jobQueue.poll();
			}else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
class Job{
	public String type;
	public HttpServletRequest request;
	public HttpServletResponse response;
	public Job(String type,HttpServletRequest request,HttpServletResponse response){
		this.request=request;
		this.response=response;
	}
}
