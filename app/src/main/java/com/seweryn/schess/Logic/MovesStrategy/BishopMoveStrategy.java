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

    /**
          * returns possible move for the piece that is at particular position
          * @param  Vector piecePosition current position of the piece
          * @return  returns array of possible piece moves
      */
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
