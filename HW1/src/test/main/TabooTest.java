package test.main; 

import main.Taboo;
import org.junit.Test;

import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** 
* Taboo Tester. 
* 
* @author <Authors name> 
* @since <pre>February 26, 2021</pre>
* @version 1.0 
*/ 
public class TabooTest {

/** 
* 
* Method: noFollow(T ele) 
* 
*/ 
@Test
public void testNoFollow() throws Exception {
    Taboo<String> obj = new Taboo<>(Arrays.asList("a", "c", "a", "b"));
    Set<String> res = new HashSet<>();
    res.add("c"); res.add("b");
    assertEquals(res, obj.noFollow("a"));

    res = new HashSet<>();
    res.add("a");
    assertEquals(res, obj.noFollow("c"));

    assertEquals(Collections.emptySet(), obj.noFollow("b"));

    obj = new Taboo<>(Arrays.asList("a", "b",null, "c", "d"));
    assertEquals(Collections.emptySet(), obj.noFollow("b"));
} 

/** 
* 
* Method: reduce(List<T> inputList) 
* 
*/ 
@Test
public void testReduce() throws Exception {
    Taboo<String> obj = new Taboo<String>(Arrays.asList("a", "c", "a", "b"));
    List<String> initialList = new ArrayList<String>(Arrays.asList("a", "c", "b", "x", "c", "a"));
    obj.reduce(initialList);
    List<String> resultList = new ArrayList<String>(Arrays.asList("a", "x", "c"));
    assertTrue(resultList.equals(initialList));
} 


} 
