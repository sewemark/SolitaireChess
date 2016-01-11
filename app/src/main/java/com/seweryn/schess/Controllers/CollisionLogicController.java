package com.seweryn.schess.Controllers;

import com.seweryn.schess.Logic.CollisionLogic;
import com.seweryn.schess.Enums.Directions;
import com.seweryn.schess.Models.Vector;

import java.util.List;

/**
 * Created by sew on 2016-01-11.
 */
public class CollisionLogicController implements  ICollisionLogicController<Vector> {
    private CollisionLogic logic;
    public CollisionLogicController(){
     logic =  new CollisionLogic();
    }

    @Override
    public List<Vector> checkIfCollision(Vector sourcePosition, Vector destinationPosition) {

        return logic.checkIfCollision(sourcePosition,destinationPosition);
    }

    @Override
    public Directions getDirection(Vector diffrence) {
        return  logic.getDirection(diffrence);
    }
}
