package com.seweryn.schess;

import android.os.Build;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sew on 2015-11-08.
 */

public class MoveLogic {
    public  MoveLogic(){

    }
    Vector[] kingVectors = {new Vector(1, 1),
            new Vector(1, 0),
            new Vector(1, -1),
            new Vector(0, 1),
            new Vector(0, -1),
            new Vector(-1, 1),
            new Vector(-1, 0),
            new Vector(-1, -1)};
    Vector[] towerVectors = {new Vector(0,-1),
                             new Vector(-1,0)};
    private int width;
    private int height;
    public int[] PossibleMoves(int width, int height, Vector position, PieceType pieceType){
        this.width=width;
        this.height =height;
        if(pieceType==PieceType.KING) {
            return getForKing(position);
        }
        else if(pieceType == PieceType.TOWER){
            return  getForTower(position);
        }
        else{
            return new int[]{};
        }
    }
    public int[] getForKing(Vector piecePosition){

        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        for(int i =0; i < kingVectors.length; i++){
            Vector vector = piecePosition.minus(kingVectors[i]);
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,piecePosition.minus(kingVectors[i])));
        }
        return toIntArray(listOfPossibleMoves);
      //  return new int[]{5,10,4};
    }
    public int[] getForTower(Vector piecePosition){
        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        Vector xLine = new Vector(piecePosition.getX(),0);
        Vector yLine = new Vector(0, piecePosition.getY());
        for(int i =0 ;i < this.width; i++){
            Vector vector = xLine.plus(new Vector(i*1, 0));
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,vector));
        }
        for(int i =0 ;i < this.height; i++){
            Vector vector = yLine.plus(new Vector(0,i*1));
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,vector));
        }
        return toIntArray(listOfPossibleMoves);
    }
   public int[] toIntArray(List<Integer> list){
        int[] ret = new int[list.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = list.get(i);
        return ret;
    }

    public boolean checkRange(Vector position){
           if(position.getX()>= 0 && position.getX() < this.width){
               if(position.getY() >= 0&& position.getY() < this.height)
                    return  true;
           }
           return false;

    }


}
