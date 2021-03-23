package test.main; 

import main.DBBinding;
import main.DBRecord;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** 
* DBRecord Tester. 
* 
* @author <Authors name> 
* @since <pre>March 23, 2021</pre>
* @version 1.0 
*/ 
public class DBRecordTest { 

@Test
public void testBasic() throws Exception {
    DBRecord record = new DBRecord("name:Alien, stars: Yaphet Kotto, stars:Sigourney Weaver, stars: Harry Dean Stanton");
    ArrayList<DBBinding> list = record.getRecords();
    assertEquals("name", list.get(0).getKey().toLowerCase());
    assertEquals("alien", list.get(0).getValue().toLowerCase());
    assertEquals("stars", list.get(1).getKey().toLowerCase());
    assertEquals("Sigourney Weaver".toLowerCase(), list.get(2).getValue().toLowerCase());
} 


} 
