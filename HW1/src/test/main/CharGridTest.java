package test.main;

import main.CharGrid;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/** 
* CharGrid Tester. 
* 
* @author <Yuqi Zhang>
* @since <pre>February 18, 2021</pre>
* @version 1.0 
*/ 
public class CharGridTest { 
    private static CharGrid obj = new CharGrid();

/** 
* 
* Method: charArea(char ch) 
* 
*/ 
@Test
public void testCharArea() throws Exception { 
    assertEquals(0, obj.charArea('d'));
    assertEquals(6, obj.charArea('a'));
    assertEquals(3, obj.charArea('b'));
    assertEquals(1, obj.charArea('c'));
} 

/** 
* 
* Method: countPlus() 
* 
*/ 
@Test
public void testCountPlus() throws Exception { 
    assertEquals(2, obj.countPlus());
} 


} 
