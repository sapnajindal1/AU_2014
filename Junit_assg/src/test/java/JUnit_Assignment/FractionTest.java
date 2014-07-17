package JUnit_Assignment;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class FractionTest {

	private Fraction fraction_obj;
	@Before
	public void setUpMethod()
	{
		fraction_obj= Fraction.getFraction(12,7);
	}

	@Test
public void testConstants() {
assertEquals(2, Fraction.ONE_HALF.getDenominator());
assertEquals(1, Fraction.ONE_THIRD.getNumerator());
assertEquals(3, Fraction.ONE_THIRD.getDenominator());
assertEquals(2, Fraction.TWO_THIRDS.getNumerator());
assertEquals(3, Fraction.TWO_THIRDS.getDenominator());
assertEquals(1, Fraction.ONE_QUARTER.getNumerator());
assertEquals(4, Fraction.ONE_QUARTER.getDenominator());
assertEquals(0, Fraction.ZERO.getNumerator());
assertEquals(1, Fraction.ZERO.getDenominator());
assertEquals(1, Fraction.ONE.getNumerator());
assertEquals(1, Fraction.ONE.getDenominator());
assertEquals(1, Fraction.ONE_HALF.getNumerator());
assertEquals(2, Fraction.TWO_QUARTERS.getNumerator());
assertEquals(4, Fraction.TWO_QUARTERS.getDenominator());
assertEquals(3, Fraction.THREE_QUARTERS.getNumerator());
assertEquals(4, Fraction.THREE_QUARTERS.getDenominator());
assertEquals(1, Fraction.ONE_FIFTH.getNumerator());
assertEquals(5, Fraction.ONE_FIFTH.getDenominator());
assertEquals(5, Fraction.THREE_FIFTHS.getDenominator());
assertEquals(4, Fraction.FOUR_FIFTHS.getNumerator());
assertEquals(5, Fraction.FOUR_FIFTHS.getDenominator());
assertEquals(2, Fraction.TWO_FIFTHS.getNumerator());
assertEquals(5, Fraction.TWO_FIFTHS.getDenominator());
assertEquals(3, Fraction.THREE_FIFTHS.getNumerator());
}
	@Test
	public void testGetFraction_GreaterValues()
	{
					try {
					Fraction f = Fraction.getFraction(Integer.MAX_VALUE+1, 1);
					} catch (ArithmeticException e) {
					}
					}
	
	@Test
	public void testGetFraction_Deno_Zero()
	{
		try {
			Fraction f = Fraction.getFraction(1, 0);
			fail("Expecting divide by 0");
			} catch (ArithmeticException e) {
			}
			}
	
	@Test
	public void testGetFraction_Deno_negative()
	{
					try {
					 Fraction f = Fraction.getFraction(Integer.MIN_VALUE, -1);
					fail("Expecting divide can't negate");
					} catch (ArithmeticException e) {
					}
	}

	
	@Test
	public void testGetReducedFraction()
	{
		try {
			 Fraction f = Fraction.getReducedFraction(1,0);
			fail("Expecting divide by 0");
			} catch (ArithmeticException e) {
			}
		}
	
	@Test
	public void testReducedFraction_String_Null()
	{
		try {
			 Fraction f = Fraction.getFraction(null);
			fail("Expecting illegal argument exception");
			} catch (IllegalArgumentException e) {
			}
	}
	 	
	@Test
	public void testReducedFraction_String_Incorrect()
	{
		try {
			 Fraction f = Fraction.getFraction("a b c d");
			fail("Expecting Number format exception");
			} catch (IllegalArgumentException e) {
			}
	}
	
	@Test
	public void testGetNumerator() {
		assertEquals("Testing if Numerator is correctly returned",12,fraction_obj.getNumerator());
		 }
	
	@Test
	public void testGetDenominator() {
		assertEquals("Testing if denominator is correctly returned",7,fraction_obj.getDenominator());
		 } 
	
	@Test
	public void testGetProperNumerator() {
		assertEquals("Testing if proper Numerator  is correctly returned",5,fraction_obj.getProperNumerator());
		 } 
	
	@Test
	public void testGetProperWhole() {
		assertEquals("Testing if proper whole number  is correctly returned",1,fraction_obj.getProperWhole());
		 }
	
	@Test
	public void testIntValue() {
		int i=1;
		assertEquals("Testing if int value  is correctly returned",i,fraction_obj.intValue());
		 }
	
	@Test
	public void testLongValue() {
		long l1=1;
		assertEquals("Testing if long value  is correctly returned",l1,fraction_obj.longValue());
		 }
	
	@Test
	public void testFloatValue() {
		Assert.assertNotNull(fraction_obj.floatValue());
		 }
	
	@Test
	public void testDoubleValue() {
		Assert.assertNotNull(fraction_obj.doubleValue());
		 }
	
	@Test
	public void testReduce() {
		Fraction testobj_actual=Fraction.getFraction(2, 4);
		Fraction testobj_expected=Fraction.getFraction(1, 2);
		assertEquals("Testing if reduce works correctly",testobj_expected,testobj_actual.reduce());
		 }
	
	@Test
	public void testInvert() {
		Fraction testobj_expected=Fraction.getFraction(7, 12);
		assertEquals("Testing if reduce works correctly",testobj_expected,fraction_obj.invert());
		 }
	
	@Test
	public void testNegate() {
		Fraction testobj_expected=Fraction.getFraction(-12, 7);
		assertEquals("Testing if negate works correctly",testobj_expected,fraction_obj.negate());
		 }
	
	@Test
	public void testAbs() {
		Fraction testobj_actual=Fraction.getFraction(-12, 7);
		assertEquals("Testing if abs works correctly",fraction_obj,testobj_actual.abs());
		 }
	
	@Test
	public void testPow() {
		Fraction testobj_expected=Fraction.getFraction(144,49);
		assertEquals("Testing if power works correctly",testobj_expected,fraction_obj.pow(2));
		 }
	
	@Test
	public void testAdd() {
		Fraction testobj_expected=Fraction.getFraction(13,7);
		Fraction testValue=Fraction.getFraction(1,7);
		assertEquals("Testing if add works correctly",testobj_expected,fraction_obj.add(testValue));
		 }
	
	@Test
	public void testSubtract() {
		Fraction testobj_expected=Fraction.getFraction(11,7);
		Fraction testValue=Fraction.getFraction(1,7);
		assertEquals("Testing if subtract works correctly",testobj_expected,fraction_obj.subtract(testValue));
		 }
	
	@Test
	public void testMultiplyBy() {
		Fraction testobj_expected=Fraction.getFraction(12,49);
		Fraction testValue=Fraction.getFraction(1,7);
		assertEquals("Testing if multiplyBy works correctly",testobj_expected,fraction_obj.multiplyBy(testValue));
		// IllegalArgumentException
		try {
		Fraction test1 = Fraction.getFraction(1, 2);
		Fraction test2 = null;
		Fraction output = test1.multiplyBy(test2);
		fail("expecting IllegalArgumentException");
		} catch (IllegalArgumentException e) {

		}
		// ArithmeticException
		try {
		Fraction f1 = Fraction.getFraction(Integer.MAX_VALUE, 1);
		Fraction f2 = Fraction.getFraction(2, 1);
		Fraction output = f1.multiplyBy(f2);
		fail("expecting arithmeticException");
		} catch (ArithmeticException e) {

		}
		 }
	
	@Test
	public void testDivideBy() {
		Fraction f1,f2,output;
		Fraction testobj_expected=Fraction.getFraction(12/1);
		Fraction testValue=Fraction.getFraction(1,7);
		assertEquals("Testing if diviideBy works correctly",testobj_expected,fraction_obj.divideBy(testValue));
		// checking for null
		try {
		f1 = Fraction.getFraction(2, 4);
		f2 = null;
		output = f1.divideBy(f2);
		fail("IllegalArgumentException expected");
		} catch (IllegalArgumentException e) {

		}
		// checking divide by 0
		try {
		f1 = Fraction.getFraction(2, 4);
		f2 = Fraction.getFraction(0, 1);
		output = f1.divideBy(f2);
		fail("Divide by 0 expected");
		} catch (ArithmeticException e) {

		}
		// checking >Integer.Max
		try {
		f1 = Fraction.getFraction(1, Integer.MAX_VALUE);
		f2 = Fraction.getFraction(Integer.MAX_VALUE, 1);
		output = f1.divideBy(f2); 
		fail("expecting ArithmeticException");
		} catch (ArithmeticException ex) {
		}	 
	}
	
	@Test
	public void testEquals() {
		Fraction testValue=Fraction.getFraction(12,7);
		assertEquals("Testing if equals works correctly",true,fraction_obj.equals(testValue));		
		 }
	
	@Test
	public void testCompareTo() {
		Fraction testobj1=Fraction.getFraction(1,2);
		Fraction testobj2=Fraction.getFraction(2,4);
		assertEquals("Testing if compare to works correctly",0,testobj2.compareTo(testobj1));		
		 }
	
	@Test
	public void testingHashCode() {
	Fraction f1, f2;

	f1 = Fraction.getFraction(3, 4);
	f2 = Fraction.getFraction(3, 4);
	assertTrue(f1.hashCode() == f2.hashCode());

	f1 = Fraction.getFraction(1, 4);
	f2 = Fraction.getFraction(3, 4);
	assertTrue(f1.hashCode() != f2.hashCode());
	}
	
	@Test
	public void testToString() {
		String testValue="12/7";
		assertEquals("Testing if equals works correctly",false,fraction_obj.equals(testValue));		
		 }
	
	@Test
	public void testToProperString() {
	Fraction f;

	f = Fraction.getFraction(3, 1, 4);
	assertEquals("3 1/4", f.toProperString());

	f = Fraction.getFraction(-3, 1, 4);
	assertEquals("-3 1/4", f.toProperString());

	f = Fraction.getFraction(-3, 0, 4);
	assertEquals("-3", f.toProperString());

	f = Fraction.getFraction(5, 5);
	assertEquals("1", f.toProperString());

	}
		

}
