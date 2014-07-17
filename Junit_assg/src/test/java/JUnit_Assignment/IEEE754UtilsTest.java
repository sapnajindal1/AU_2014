package JUnit_Assignment;

import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class IEEE754UtilsTest {

@Test
public void testConstructor() {
	IEEE754Utils testObj = new IEEE754Utils();
}


@Test
public void testMinFloatArray() {
float FloatArr[] = { 1.2f, 4.6f , 5.7f, 1.6f};
assertTrue("testing if minimum fucntion returns good value",1.2f==IEEE754Utils.min(FloatArr));
//checking if array is empty
try {
float FloatArr2[] = new float[0];
IEEE754Utils.min(FloatArr2);
fail("Array must not be empty.");
} catch (Exception e) {

}
//null check for array
try {
IEEE754Utils.min((float[]) null);
fail("Array cannot be null");
} catch (Exception e) {

}
}

@Test
public void testMinDoubleArray() {
double[] doubleArr = { 3.4,5.4,6.5,9.8,0.1 };
double answer;
assertTrue("testing minimum for double array",0.1==IEEE754Utils.min(doubleArr));

// checking null array
try {
	double[] doubleArr1=null;
IEEE754Utils.min(doubleArr1);
fail("Expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {

}
// empty array check
try {
double doub_arr2[] = new double[0];
IEEE754Utils.min(doub_arr2);
fail("Expecting IllegalArgumentException");
} catch (IllegalArgumentException e) {

}
}


@Test
public void testMinDouble() {
double d1,d2,d3;
d1=0.8;
d2=8.9;
d3=6.7;
assertTrue(0.8==IEEE754Utils.min(d1, d2, d3));
d1 = 10.5;
d2 = -2.8;

assertTrue(IEEE754Utils.min(d1, d2) == -2.8);
assertEquals(IEEE754Utils.min(Double.NaN, Double.NaN), Double.NaN, 0.1);
assertEquals(IEEE754Utils.min(d2, Double.NaN), d2, 0.1);

}


@Test
public void testMaxFloatArray() {
float FloatArr[] = { 1.2f, 4.6f , 5.7f, 1.6f};
assertTrue("testing max float return function",5.7f == IEEE754Utils.max(FloatArr));
// array not null check
try {
IEEE754Utils.max((float[]) null);
fail("The Array can not be null");
} catch (Exception e) {

}
//array not empty
try {
float FloatArr2[] = new float[0];
IEEE754Utils.max(FloatArr2);
fail("Array must not be empty.");

} catch (Exception e) {

}
}

@Test
public void testMaxDouble() {
double d1=8.9, d2=3.4, d3=4.5;
assertTrue("checking max double return function",IEEE754Utils.max(d1, d2, d3) == 8.9);
assertEquals(IEEE754Utils.max(Double.NaN, Double.NaN, Double.NaN),
Double.NaN, 0.1);
d1 = 0.6;
d2 = -9.6;
assertTrue(0.6==IEEE754Utils.max(d1, d2));
assertEquals(IEEE754Utils.max(Double.NaN, Double.NaN), Double.NaN, 0.1);
assertEquals(IEEE754Utils.max(Double.NaN, d2), d2, 0.1);
}

@Test
public void testMinFloat() {
float f1 = 4.6f, f2 = -2.3f, f3 = 5.7f;

assertTrue(IEEE754Utils.min(f1, f2, f3) == -2.3f);
assertEquals(IEEE754Utils.min(Float.NaN, Float.NaN, Float.NaN),
Float.NaN, 0.01f);
assertTrue(IEEE754Utils.min(f1, f2) == -2.3f);
assertEquals(IEEE754Utils.min(Float.NaN, Float.NaN), Float.NaN, 0.01f);
assertEquals(IEEE754Utils.min(1.2f, Float.NaN), 1.2f, 0.01f);
}


@Test
public void testMaxFloat() {
float f1, f2, f3;
f1 = 2.1f;
f2 = 0.3f;
f3 = 4.5f;

assertTrue(4.5f==IEEE754Utils.max(f1, f2, f3));
assertEquals(IEEE754Utils.max(Float.NaN, Float.NaN, Float.NaN),
Float.NaN, 0.01f);
assertTrue(IEEE754Utils.max(f1, f2) == 2.1f);
assertEquals(IEEE754Utils.max(1.1f, Float.NaN), 1.1f, 0.01f);
assertEquals(IEEE754Utils.max(Float.NaN, Float.NaN), Float.NaN, 0.01f);
}
}