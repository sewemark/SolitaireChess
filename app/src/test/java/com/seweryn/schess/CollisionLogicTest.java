package com.seweryn.schess;

import com.seweryn.schess.Logic.CollisionLogic;
import com.seweryn.schess.Models.Vector;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Created by sew on 30/01/2016.
 */
public class CollisionLogicTest {
    @Test
    public void testForPossibleCollisionForBoard4By4(){
        CollisionLogic collisionLogic  = new CollisionLogic();
        List<Vector> possibleCollisions = collisionLogic.checkIfCollision(new Vector(1, 2), new Vector(3, 2));
        //assertArrayEquals(possibleCollisions.toArray(),new Vector[]{new Vector(2,2)});
        assertEquals(possibleCollisions.get(0).getX(),2);
        assertEquals(possibleCollisions.get(0).getY(),2);
    }
}
