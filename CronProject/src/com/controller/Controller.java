package com.controller;
import java.util.Timer;

import com.scheduler.Scheduler;
import com.Reader.ReadFiles;
import com.prohibit.ProhibitAccess;
//this controller basically controls the flow of code and call the scheduler moethods
public class Controller {
	public static ReadFiles rf= new ReadFiles();
	public static void readFilesPeriodically()
	{
			rf.start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			//this timer is somehow not working
			//Timer time = new Timer();
			  //time.schedule(new ReadFiles(),0, 5*60*1000);
	}
	public static void main(String[] args)
	{	
		
		readFilesPeriodically();
		String[] proFiles=rf.getProhibitedList();
		for(int i=0;i<proFiles.length;i++)
		{
			ProhibitAccess pa= new ProhibitAccess(proFiles[i]);
			pa.start();
		}
		Scheduler sc= new Scheduler();
		sc.start();
	}

}
