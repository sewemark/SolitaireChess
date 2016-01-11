package com.seweryn.schess.Logic;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Models.Vector;

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
    Vector[] horseVectors = {new Vector(2,1),
                            new Vector(-2,1),
                            new Vector(2,-1),
                            new Vector(-2,-1),
                            new Vector(-1,2),
                            new Vector(-1,-2),
                            new Vector(1,2),
                            new Vector(1,-2)
    };
    Vector[] towerVectors = {new Vector(0,-1),
                             new Vector(-1,0)};
    Vector[] pawnVectors = {
            new Vector(1,1),
            new Vector(-1,1),
    };
    private int width;
    private int height;
    public Integer[] PossibleMoves(int width, int height, Vector position, PieceType pieceType){
        this.width=width;
        this.height =height;
        if(pieceType==PieceType.KING) {
            return getForKing(position);
        }
        else if(pieceType == PieceType.TOWER){
            return  getForTower(position);
        }
        else if (pieceType == PieceType.PAWN) {
            return getForPawn(position);
        }
        else if(pieceType == pieceType.BISHOP){
            return getForBishop(position);
        }
        else if(pieceType == pieceType.HORSE){
            return getForHorse(position);
        }
        else if(pieceType ==pieceType.QUEEN){
            return getForQueen(position);
        }
        else {
            return new Integer[]{};
        }
    }
    public Integer[] getForKing(Vector piecePosition){

        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        for(int i =0; i < kingVectors.length; i++){
            Vector vector = piecePosition.minus(kingVectors[i]);
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,piecePosition.minus(kingVectors[i])));
        }
        return toIntArray(listOfPossibleMoves);
      //  return new int[]{5,10,4};
    }
    public Integer[] getForTower(Vector piecePosition){
        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        Vector yLine = new Vector(piecePosition.getX(), 0);
        Vector xLine = new Vector(0, piecePosition.getY());
        for(int i =0 ;i < this.width; i++){
            Vector vector = xLine.plus(new Vector(i*1, 0));
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,vector));
        }
        for(int i =0 ;i < this.height; i++){
            Vector vector = yLine.plus(new Vector(0, i * 1));
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,vector));
        }
        return toIntArray(listOfPossibleMoves);
    }
    public Integer[] getForPawn(Vector piecePosition){
        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        for(int i =0; i < pawnVectors.length; i++){
            Vector vector = piecePosition.minus(pawnVectors[i]);
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,piecePosition.minus(pawnVectors[i])));
        }
        return toIntArray(listOfPossibleMoves);
    }
    public Integer[] getForBishop(Vector piecePosition){
        Vector leftCorner = getLastCoordinate(piecePosition,new Vector(1,-1));
        Vector rightCorner = getLastCoordinate(piecePosition,new Vector(1,1));
        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        while(checkRange(leftCorner)){
            listOfPossibleMoves.add(Vector.convertToScalar(width, height,leftCorner));
            leftCorner =leftCorner.minus((new Vector(1,-1)));
        }
        while(checkRange(rightCorner)){
            listOfPossibleMoves.add(Vector.convertToScalar(width, height,rightCorner));
            rightCorner = rightCorner.minus(new Vector(1,1));
        }
        return toIntArray(listOfPossibleMoves);
    }

    public Integer[] getForQueen(Vector piecePosition){
           Integer[] bishopPositions = getForBishop(piecePosition);
           Integer[] towerPositions = getForTower(piecePosition);
           Integer[] queenPositions = new Integer[bishopPositions.length + towerPositions.length];
            System.arraycopy(bishopPositions, 0,queenPositions, 0, bishopPositions.length);
            System.arraycopy(towerPositions, 0, queenPositions, bishopPositions.length, towerPositions.length);
            return  queenPositions;

    }
    public  Integer[] getForHorse(Vector piecePosition){
        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        for(int i =0; i < horseVectors.length; i++){
            Vector vector = piecePosition.minus(horseVectors[i]);
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,piecePosition.minus(horseVectors[i])));
        }
        return toIntArray(listOfPossibleMoves);

    }
    public Vector  getLastCoordinate(Vector position, Vector vector){
        Vector temp=position;
        while(checkRange(position.plus(vector))){
           position= position.plus(vector);
        }
        return position;
    }  public Integer[] toIntArray(List<Integer> list){
        Integer[] ret = new Integer[list.size()];
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
