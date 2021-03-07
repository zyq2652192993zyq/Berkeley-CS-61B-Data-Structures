package test.main; 

import junit.framework.TestCase;
import main.Piece;
import main.TPoint;
import org.junit.Test;
import java.util.*;


/** 
* Piece Tester. 
* 
* @author <Authors name> 
* @since <pre>March 2, 2021</pre>
* @version 1.0 
*/ 
public class PieceTest extends TestCase {
    private Piece pyr1, pyr2, pyr3, pyr4;
    private Piece s, sRotated;
    private Piece l = new Piece(Piece.STICK_STR);
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        pyr1 = new Piece(Piece.PYRAMID_STR);
        pyr2 = pyr1.computeNextRotation();
        pyr3 = pyr2.computeNextRotation();
        pyr4 = pyr3.computeNextRotation();

        s = new Piece(Piece.S1_STR);
        sRotated = s.computeNextRotation();
    }
/** 
* 
* Method: getWidth() 
* 
*/ 
@Test
public void testGetWidth() throws Exception {
    assertEquals(3, pyr1.getWidth());
    assertEquals(2, pyr2.getWidth());
    assertEquals(1, l.getWidth());
}

/** 
* 
* Method: getHeight() 
* 
*/ 
@Test
public void testGetHeight() throws Exception {
    assertEquals(2, pyr1.getHeight());
    assertEquals(3, pyr2.getHeight());
    assertEquals(4, l.getHeight());
} 

/** 
* 
* Method: getBody() 
* 
*/ 
@Test
public void testGetBody() throws Exception {
    TPoint [] t1 = new TPoint[] {
        new TPoint(0, 0),
        new TPoint(1, 0),
        new TPoint(1, 1),
        new TPoint(2, 0)
    };
    assertTrue(Arrays.equals(t1, pyr1.getBody()));

    TPoint [] t2 = new TPoint[] {
            new TPoint(0, 1),
            new TPoint(1, 0),
            new TPoint(1, 1),
            new TPoint(1, 2)
    };
    assertTrue(Arrays.equals(t2, pyr2.getBody()));
} 

/** 
* 
* Method: getSkirt() 
* 
*/ 
@Test
public void testGetSkirt() throws Exception {
    assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
    assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
    assertTrue(Arrays.equals(new int[] {0, 0, 1}, s.getSkirt()));
    assertTrue(Arrays.equals(new int[] {1, 0}, sRotated.getSkirt()));
} 

/** 
* 
* Method: computeNextRotation() 
* 
*/ 
@Test
public void testComputeNextRotation() throws Exception {
    TPoint [] t = new TPoint[] {
            new TPoint(0, 1),
            new TPoint(1, 0),
            new TPoint(1, 1),
            new TPoint(2, 1)
    };
    assertTrue(Arrays.equals(t, pyr2.computeNextRotation().getBody()));
} 

/** 
* 
* Method: fastRotation() 
* 
*/ 
@Test
public void testFastRotation() throws Exception {
    Piece[] pieces = Piece.getPieces();
    Piece stick = pieces[Piece.STICK];
    Piece stick2 = stick.fastRotation();
    TPoint [] t = new TPoint[] {
            new TPoint(0, 0),
            new TPoint(1, 0),
            new TPoint(2, 0),
            new TPoint(3, 0)
    };
    assertTrue(Arrays.equals(t, stick2.getBody()));
} 

/** 
* 
* Method: equals(Object obj) 
* 
*/ 
@Test
public void testEquals() throws Exception { 
    Piece obj = new Piece(Piece.PYRAMID_STR);
    assertTrue(pyr1.equals(obj));
} 

/** 
* 
* Method: getPieces() 
* 
*/ 
@Test
public void testGetPieces() throws Exception { 
//TODO: Test goes here... 
}

} 
