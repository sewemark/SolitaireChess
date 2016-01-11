package com.seweryn.schess.Logic;

import com.seweryn.schess.Enums.Directions;
import com.seweryn.schess.Static.Lodash;
import com.seweryn.schess.Models.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sew on 2015-12-20.
 */
public class CollisionLogic {

    public CollisionLogic(){

    }
    public List<Vector> checkIfCollision(Vector sourcePosition, Vector destinationPosition){
          Vector diffrenceVector = destinationPosition.minus(sourcePosition);
          List<Vector> possibleCollisions = new ArrayList<>();
          Directions direction = getDirection(diffrenceVector);
          Vector vector= Lodash.getVectorForDirection(direction);
          sourcePosition= sourcePosition.plus(vector);
          while(!sourcePosition.equals(destinationPosition)){


              possibleCollisions.add(sourcePosition);
              sourcePosition= sourcePosition.plus(vector);
          }
        return possibleCollisions;
    }
    public  Directions getDirection(Vector diffrence){
            if(diffrence.getX() ==0 && diffrence.getY() >0){
                return Directions.S;
            }
        else if(diffrence.getX()==0 && diffrence.getY()<0){
                return  Directions.N;
            }
        else if(diffrence.getY()== 0 && diffrence.getX()>0){
                 return  Directions.E;
            }

            else if(diffrence.getY()== 0 && diffrence.getX()<0){
                return Directions.W;
            }
        else if(diffrence.getY()>0 && diffrence.getX() >0){
                return  Directions.SE;
            }

            else if(diffrence.getY()>0 && diffrence.getX() <0){
                return  Directions.SW;
            }

            else if(diffrence.getY()<0 && diffrence.getX() >0){
                return  Directions.NE;
            }
            else if(diffrence.getY()<0 && diffrence.getX() <0){
                return  Directions.NW;
            }
            return null;
    }
}
