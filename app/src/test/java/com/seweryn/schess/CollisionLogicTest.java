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
    public void testCaseForPossibleCollisionForBoard4By4(){
        CollisionLogic collisionLogic  = new CollisionLogic();
        List<Vector> possibleCollisions = collisionLogic.checkIfCollision(new Vector(1, 2), new Vector(3, 2));
        assertEquals(possibleCollisions.size(),1);
        assertEquals(possibleCollisions.get(0).getX(),2);
        assertEquals(possibleCollisions.get(0).getY(),2);
    }
    @Test
    public void testCase2ForPossibleCollisionForBoard4By4(){
        CollisionLogic collisionLogic  = new CollisionLogic();
        List<Vector> possibleCollisions = collisionLogic.checkIfCollision(new Vector(3, 3), new Vector(3, 0));
        assertEquals(possibleCollisions.size(),2);
        assertEquals(possibleCollisions.get(1).getX(),3);
        assertEquals(possibleCollisions.get(1).getY(),1);
        assertEquals(possibleCollisions.get(0).getX(),3);
        assertEquals(possibleCollisions.get(0).getY(),2);
    }
    @Test
    public void testCase3ForPossibleCollisionForBoard4By4(){
        CollisionLogic collisionLogic  = new CollisionLogic();
        List<Vector> possibleCollisions = collisionLogic.checkIfCollision(new Vector(1, 1), new Vector(3, 1));
        assertEquals(possibleCollisions.size(),1);
        assertEquals(possibleCollisions.get(0).getX(),2);
        assertEquals(possibleCollisions.get(0).getY(),1);
    }
    @Test
    public void testCase4ForPossibleCollisionForBoard4By4(){
        CollisionLogic collisionLogic  = new CollisionLogic();
        List<Vector> possibleCollisions = collisionLogic.checkIfCollision(new Vector(0, 3), new Vector(3,0));
        assertEquals(possibleCollisions.size(),2);
        assertEquals(possibleCollisions.get(0).getX(),1);
        assertEquals(possibleCollisions.get(0).getY(),2);
        assertEquals(possibleCollisions.get(1).getX(),2);
        assertEquals(possibleCollisions.get(1).getY(),1);
    }
}
