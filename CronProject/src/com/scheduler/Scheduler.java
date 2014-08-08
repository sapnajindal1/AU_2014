package com.scheduler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.jobs.Jobs;
import com.Reader.ReadFiles;
public class Scheduler implements Runnable
{
	private ReadFiles rc;	
	
	@Override
	public void run() {
		rc=new ReadFiles();
		Jobs job[] = new Jobs[rc.getMap().size()];
		  String sTime = new SimpleDateFormat("HH:mm").format(new Date());
		  System.out.println(rc.getMap().get(sTime));
		if (rc.getMap().containsKey(sTime)) {
			   ExecutorService service = Executors.newFixedThreadPool(5);
			   ArrayList<String> remainingJobs = rc.getMap().get(sTime);
			   for (String s : remainingJobs) {
				    System.out.println("Job- " + s);
				    Future<String> future = service.submit(new Jobs(s,rc.getTime()));
				    try {
				     System.out.println("Result: " + future.get());
				     rc.updateMap(s, sTime);
				    } catch (InterruptedException e) {
				     e.printStackTrace();
				    } catch (ExecutionException e) {
				     e.printStackTrace();
				    }
				   }
		}
		
	}
	
	public void start()
	{
		Thread t=new Thread(this);
		t.start();
	}

}
