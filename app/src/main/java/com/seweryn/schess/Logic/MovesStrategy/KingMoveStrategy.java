package com.seweryn.schess.Logic.MovesStrategy;

import com.seweryn.schess.Models.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sew on 2016-01-24.
 */
public  class KingMoveStrategy extends  MoveStrategy implements IMoveStrategy {
    private Vector[] kingVectors = {new Vector(1, 1),
            new Vector(1, 0),
            new Vector(1, -1),
            new Vector(0, 1),
            new Vector(0, -1),
            new Vector(-1, 1),
            new Vector(-1, 0),
            new Vector(-1, -1)};
    public KingMoveStrategy(int _width, int _height) {
        super(_width, _height);
    }

    @Override
    public Integer[] getMoveForPiece(Vector piecePosition) {
        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        for(int i =0; i < kingVectors.length; i++){
            Vector vector = piecePosition.minus(kingVectors[i]);
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,piecePosition.minus(kingVectors[i])));
        }
        return toIntArray(listOfPossibleMoves);
    }
}
