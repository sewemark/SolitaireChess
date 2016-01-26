package com.seweryn.schess.Logic.MovesStrategy;

import com.seweryn.schess.Models.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sew on 2016-01-24.
 */
public class KnightMoveStrategy extends  MoveStrategy implements IMoveStrategy {
    Vector[] horseVectors = {new Vector(2,1),
            new Vector(-2,1),
            new Vector(2,-1),
            new Vector(-2,-1),
            new Vector(-1,2),
            new Vector(-1,-2),
            new Vector(1,2),
            new Vector(1,-2)
    };
    public KnightMoveStrategy(int _width, int _height) {
        super(_width, _height);
    }

    @Override
    public Integer[] getMoveForPiece(Vector piecePosition) {
        List<Integer> listOfPossibleMoves = new LinkedList<Integer>();
        for(int i =0; i < horseVectors.length; i++){
            Vector vector = piecePosition.minus(horseVectors[i]);
            if(checkRange(vector))
                listOfPossibleMoves.add(Vector.convertToScalar(width, height,piecePosition.minus(horseVectors[i])));
        }
        return toIntArray(listOfPossibleMoves);
    }
}
