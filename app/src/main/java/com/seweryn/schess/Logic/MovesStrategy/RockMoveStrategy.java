package com.seweryn.schess.Logic.MovesStrategy;

import com.seweryn.schess.Models.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sew on 2016-01-24.
 */
public class RockMoveStrategy extends MoveStrategy implements IMoveStrategy {

    public RockMoveStrategy(int _width, int _height) {
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
        Vector yLine = new Vector(piecePosition.getX(), 0);
        Vector xLine = new Vector(0, piecePosition.getY());
        for(int i =0 ;i < this.width; i++){
            Vector vector = xLine.plus(new Vector(i*1, 0));
            if(checkRange(vector))
                if(!vector.equals(piecePosition))
                 listOfPossibleMoves.add(Vector.convertToScalar(width, height,vector));
        }
        for(int i =0 ;i < this.height; i++){
            Vector vector = yLine.plus(new Vector(0, i));
            if(checkRange(vector))
                if(!vector.equals(piecePosition))
                    listOfPossibleMoves.add(Vector.convertToScalar(width, height,vector));
        }
        return toIntArray(listOfPossibleMoves);
    }
}
