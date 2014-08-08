package com.prohibit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//this will provide lock on all the prohibited classes
public class ProhibitAccess extends IllegalStateException implements Runnable
{
	private String fileName;
	
	public  ProhibitAccess() {
		// TODO Auto-generated constructor stub
	}
	public ProhibitAccess(String fileName)
	{
		this.fileName=fileName;
	}
	@Override
	public void run() {
		FileInputStream in;
		try {
			in = new FileInputStream(fileName);
				java.nio.channels.FileLock lock = in.getChannel().lock();
			    while(true){
			    	Thread.sleep(10);
			    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void start()
	{
		Thread t=new Thread(this);
		t.start();
	}

}
