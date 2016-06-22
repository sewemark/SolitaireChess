package com.seweryn.schess.Logic.MovesStrategy;

import com.seweryn.schess.Models.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sew on 2016-01-24.
 */
public class PawnMoveStrategy extends  MoveStrategy implements IMoveStrategy {
    private Vector[] pawnVectors = {
            new Vector(1,1),
            new Vector(-1,1),
    };
    public PawnMoveStrategy(int _width, int _height) {
        super(_width, _height);
    }

    /**
      * returns possible move for the piece that is at particular position
      * @param  Vector piecePosition current position of the piece
      * @return  returns array of possible piece moves
     */
    @Override
    public Integer[] getMoveForPiece(Vector piecePosition) {
        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        for(int i =0; i < pawnVectors.length; i++){
            Vector vector = piecePosition.minus(pawnVectors[i]);
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,piecePosition.minus(pawnVectors[i])));
        }
        return toIntArray(listOfPossibleMoves);
    }
}
