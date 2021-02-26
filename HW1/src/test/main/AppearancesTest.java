package test.main; 

import main.Appearances;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

/** 
* Appearances Tester. 
* 
* @author <Authors name> 
* @since <pre>February 21, 2021</pre>
* @version 1.0 
*/ 
public class AppearancesTest { 

/** 
* 
* Method: sameCount(Collection<T> a, Collection<T> b) 
* 
*/ 
@Test
public void testSameCount() throws Exception { 
    assertEquals(2, Appearances.sameCount(Arrays.asList("a", "b", "a", "b", "c"), Arrays.asList("c", "a", "a", "d", "b", "b", "b")));
    assertEquals(2, Appearances.sameCount(Arrays.asList(1, 2, 3, 4, 1, 2, 3), Arrays.asList(2, 3, 4, 5, 2, 3, 4)));
    assertEquals(0, Appearances.sameCount(Arrays.asList(1, 2, 3, 4), Arrays.asList()));
    assertEquals(1, Appearances.sameCount(Arrays.asList('a', 'a', 'a'), Arrays.asList('a', 'b', 'a', 'a')));
} 

} 
