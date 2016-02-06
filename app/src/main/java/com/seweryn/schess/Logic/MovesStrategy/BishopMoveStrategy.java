package com.seweryn.schess.Logic.MovesStrategy;

import com.seweryn.schess.Models.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sew on 2016-01-24.
 */
public  class BishopMoveStrategy extends MoveStrategy  implements IMoveStrategy{
    public BishopMoveStrategy(int _width, int _height) {
        super(_width, _height);
    }

    @Override
    public Integer[] getMoveForPiece(Vector piecePosition) {
        Vector leftCorner = getLastCoordinate(piecePosition,new Vector(-1,1));
        Vector rightCorner = getLastCoordinate(piecePosition,new Vector(1,1));
        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        while(checkRange(leftCorner)){
            if(!leftCorner.equals(piecePosition)) {
             System.out.println(leftCorner.getX() + " " + leftCorner.getY());
                listOfPossibleMoves.add(Vector.convertToScalar(width, height, leftCorner));
            }
            leftCorner =leftCorner.minus((new Vector(-1,1)));
        }
        while(checkRange(rightCorner)) {
            if (!rightCorner.equals(piecePosition)){
                System.out.println(rightCorner.getX() + " " + rightCorner.getY());
                listOfPossibleMoves.add(Vector.convertToScalar(width, height, rightCorner));
            }
            rightCorner = rightCorner.minus(new Vector(1,1));
        }
        return toIntArray(listOfPossibleMoves);
    }
}
