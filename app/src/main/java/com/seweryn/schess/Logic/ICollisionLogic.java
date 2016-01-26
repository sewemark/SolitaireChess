package com.seweryn.schess.Logic;

import com.seweryn.schess.Models.Vector;

import java.util.List;

/**
 * Created by sew on 2016-01-24.
 */
public interface ICollisionLogic {
     List<Vector> checkIfCollision(Vector sourcePosition, Vector destinationPosition);
}
