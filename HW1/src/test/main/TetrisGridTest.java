package test.main; 

import org.junit.Test; 
import main.TetrisGrid;
import java.util.Arrays;


/** 
* TetrisGrid Tester. 
* 
* @author <Authors name> 
* @since <pre>February 21, 2021</pre>
* @version 1.0 
*/ 
public class TetrisGridTest {
/** 
* 
* Method: clearRows() 
* 
*/ 
@Test
public void testClearRows() throws Exception {
    boolean[][] testGrid = new boolean[][]{
            {true, true, true, false, true, false, false},
            {true, true, true, true, false, false, false},
            {true, false, true, true, false, false, false}
    };
    TetrisGrid obj = new TetrisGrid(testGrid);
    obj.clearRows();
    boolean[][] res = new boolean[][]{
            {true, false, true, false, false, false, false},
            {true, true, false, false, false, false, false},
            {false, true, false, false, false, false, false}
    };
    assert (Arrays.deepEquals(res, obj.getGrid()));
}
} 
