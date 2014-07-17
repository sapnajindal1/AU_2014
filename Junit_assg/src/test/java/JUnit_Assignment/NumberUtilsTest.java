package JUnit_Assignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class NumberUtilsTest {
private NumberUtils numberUtils;

@Test
public void testConstants() {
assertEquals(0L, numberUtils.LONG_ZERO.longValue());
}

@Test
public void testToInt() {
assertEquals(10, numberUtils.toInt("10"));
assertEquals(0, NumberUtils.toInt(null));
}

@Test
public void testToIntDef() {
assertEquals(10, numberUtils.toInt(null, 10));
}

@Test
public void testToLong() {
assertEquals(10L, NumberUtils.toLong("10"));
}


@Test
public void testToFloat() {
assertTrue(NumberUtils.toFloat("2.3") == 2.3f);

}

@Test
public void testToFloatNull() {
assertTrue(NumberUtils.toFloat(null, 1.1f) == 1.1f);
}

@Test
public void testToDouble() {
assertTrue(NumberUtils.toDouble("1.3") == 1.3d);
assertTrue(NumberUtils.toDouble("-1.3") == -1.3d);

}

@Test
public void testToDoubleNull() {
assertTrue(NumberUtils.toDouble(null, 1.2) == 1.2d);
assertTrue(NumberUtils.toDouble(null, -1.2) == -1.2d);
}



@Test(expected = NumberFormatException.class)
public void testCreateNumException() {
assertEquals("", NumberUtils.createNumber(""));
assertEquals(Integer.valueOf("1453"),
NumberUtils.createNumber("1453"));
assertEquals(Double.valueOf("1.1E20"),
NumberUtils.createNumber("1.1E20"));
}


@Test(expected = IllegalArgumentException.class)
public void testMinLongArray() {
NumberUtils.min((long[]) null);
}

@Test(expected = IllegalArgumentException.class)
public void testMinLongEmpty() {
NumberUtils.min(new long[0]);
}


}