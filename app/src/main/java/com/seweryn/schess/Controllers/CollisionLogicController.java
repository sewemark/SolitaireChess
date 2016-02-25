package com.seweryn.schess.Controllers;

import com.seweryn.schess.Logic.CollisionLogic;
import com.seweryn.schess.Enums.Directions;
import com.seweryn.schess.Logic.ICollisionLogic;
import com.seweryn.schess.Models.Vector;

import java.util.List;

/**
 * Created by sew on 2016-01-11.
 */
public class CollisionLogicController implements  ICollisionLogicController<Vector> {
    private ICollisionLogic logic;
    /**
     * @param  _logic collision logic
     * */
    public CollisionLogicController(ICollisionLogic _logic){
     logic =  _logic;
    }

    /**
     * checks if for path from source to destination position any possible collision positions
     * may occur
     * @param  sourcePosition source position a piece
     * @param  destinationPosition destination position of a piece
     * @return  list of vector objects that contain possible collision positions
     * */
    @Override
    public List<Vector> checkIfCollision(Vector sourcePosition, Vector destinationPosition) {

        return logic.checkIfCollision(sourcePosition, destinationPosition);
    }

}
