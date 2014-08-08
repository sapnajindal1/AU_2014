package com.jobs;

import java.util.concurrent.Callable;
//a jobs class which is having definition for all jobs
public class Jobs implements Callable
{
	String threadName;
	int sleepTime;
	public Jobs()
	{
		
	}
	public Jobs(String threadName,int sleepTime)
	{
		this.threadName=threadName;
		this.sleepTime=sleepTime;
	}
	
	@Override
	public Object call() {
		final String jobName = Thread.currentThread().getName();
        Thread.currentThread().setName(jobName);
        System.out.println(Thread.currentThread().getName()+"-job running");
        try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobName+"success";
	}
	

}
