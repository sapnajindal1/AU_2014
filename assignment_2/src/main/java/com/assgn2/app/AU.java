package com.assgn2.app;
/*
*This main file is supposed to manipulate the current date, it gives a date which is some specific number less than the current date
*
* @sapna jindal
* @dated 2014-07-08
*
*/

import java.text.*;
import java.util.*;


public class AU {

  // this static function returns a date which is num_days less than the current date
  public static String getDate(int num_days)
  {
 
  	  Calendar now = Calendar.getInstance();


      //SimpleDateFormat returns the calculated date in yyyy-MM-dd format.
      SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

      //Calender.add() subtracts the days from current date. minus sign indicates the subtraction operation.
      now.add(Calendar.DATE, -num_days);
      Date date=null;
      date=now.getTime();
      String date1 = format1.format(date);   
      return date1;
  }

  public static void main(String[] args) 
  {
     //num_days stores the number of days that are to be subtrated from current date
     int num_days;
     
     Scanner sc=new Scanner(System.in);

     num_days=sc.nextInt();
     System.out.println(getDate(num_days));
   
  }
}