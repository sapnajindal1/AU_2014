package com.Reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.TreeMap;

public class ReadFiles extends TimerTask implements Runnable 
{
	static int Time;
    static Map<String, ArrayList<String>> map = new TreeMap<String, ArrayList<String>>();
	static String ProhibitedList[];
	public Map<String, ArrayList<String>> getMap()
	{
		return map;
		
	}
	public int getTime()
	{
		return Time;
		
	}
	public String[] getProhibitedList()
	{
		return ProhibitedList;
		
	}

	// this function will read the config file and save the content in the data structure
	public void readAndSaveConfigUtils() throws FileNotFoundException
	{
		String[] data;
		Scanner scanner2 = new Scanner(new File("textfiles/configUtils.txt"));
		String getLine = scanner2.nextLine();
		data = getLine.split(":");
		Time = Integer.parseInt(data[1]);
		getLine=scanner2.nextLine();
		getLine = getLine.replace("prohibittedfiles:","");
		ProhibitedList=getLine.split(",");
		scanner2.close();

	}
//this function will read the content of cron file and save in the treemap data structure
	public void readAndSaveCrone() throws IOException {
		Scanner scanner = new Scanner(new File("textfiles/Cron.txt"));
		String[] data;
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			data = line.split("	");
			ArrayList<String> al=new ArrayList<String>();
			al.add(data[1]);
			if(null!=map.get(data[0]))
			{
				ArrayList<String> al1=new ArrayList<String>();
				al1=map.get(data[0]);
				al1.add(data[1]);
				System.out.println(al1.size()+"size");
			}
			else
			{
				map.put(data[0], al);
			}
			
		}
		scanner.close();
		

	}
	public void updateMap(String jobName,String jobTime)
	{
		map.remove(jobTime, jobName);
		
	}
	@Override
	public void run() {
		try {
			System.out.println("sapna jindal");
			this.readAndSaveConfigUtils();
			this.readAndSaveCrone();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
