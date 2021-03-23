package test.main; 

import main.DBBinding;
import org.junit.Test;
import static org.junit.Assert.*;

/** 
* DBBinding Tester. 
* 
* @author <Authors name> 
* @since <pre>March 23, 2021</pre>
* @version 1.0 
*/ 
public class DBBindingTest {

@Test
public void testBasic() throws Exception {
    DBBinding basic = new DBBinding("name:Midnight Run");
    assertEquals("name", basic.getKey().toLowerCase());
    assertEquals("midnight run", basic.getValue().toLowerCase());
}

@Test
public void testAdvance() throws Exception {
    DBBinding advance = new DBBinding(" year   : 1979 ");
    assertEquals("year", advance.getKey().toLowerCase());
    assertEquals("1979", advance.getValue().toLowerCase());
} 


} 
