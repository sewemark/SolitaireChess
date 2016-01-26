package com.seweryn.schess.Controllers;

import com.seweryn.schess.Enums.Directions;

import java.util.List;

/**
 * Created by sew on 2016-01-11.
 */
public interface  ICollisionLogicController<T> {
     List<T> checkIfCollision(T source, T destination);
}
