package com.assgn2.app;
 
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.Date;
import org.apache.commons.lang3.StringUtils.*;
 
/**
 
 *this is a Unit test for the AU.java App which gives a date which is
 * a specific number of dates before the current date.
 
 */
public class AUTest
extends TestCase
{
/**
  * Create the test case
  *
  * @param testName name of the test case
  */
public AUTest( String testName )
{
super( testName );
}
 
/**
  * @return the suite of tests being tested
  */
public static Test suite()
{
return new TestSuite( AUTest.class );
}
 
/**
  * Rigourous Test :-)
  */
public void testApp()
{
assertTrue( true );
}
 
 
// A test case to see how packaging is done. this will fail for the testing purpose.
/*public void testGetDate()
  {
	int noOfDays = 1;
	
  assertTrue( false );
  }*/


 
 
public void testApp1()
{
assert(org.apache.commons.lang3.StringUtils.isNotBlank(new Date().toString()));
}
 
}