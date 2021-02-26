package test.main;

import main.StringCode;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * StringCode Tester.
 *
 * @author <Yuqi Zhang>
 * @since <pre>February 18, 2021</pre>
 * @version 1.0
 */
public class StringCodeTest {
    private static StringCode obj = new StringCode();

    /**
     *
     * Method: blowup(String str)
     *
     */
    @Test
    public void testBlowup() throws Exception {
        assertEquals("attttxzzz", obj.blowup("a3tx2z"));
        assertEquals("2xxx", obj.blowup("12x"));
    }

    /**
     *
     * Method: maxRun(String str)
     *
     */
    @Test
    public void testMaxRun() throws Exception {
        assertEquals(3, obj.maxRun("xxyyyz"));
        assertEquals(1, obj.maxRun("xyz"));
    }

    /**
     *
     * Method: stringIntersect(String a, String b, int len)
     *
     */
    @Test
    public void testStringIntersect() throws Exception {
        assertEquals(true, obj.stringIntersect("xxyyyzz", "xyyz", 2));
        assertEquals(false, obj.stringIntersect("abc", "def", 2));
        assertEquals(true, obj.stringIntersect("abbc", "dbc", 1));
    }


} 
