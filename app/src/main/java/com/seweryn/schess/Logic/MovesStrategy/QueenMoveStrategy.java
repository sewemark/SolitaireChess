package com.seweryn.schess.Logic.MovesStrategy;

import com.seweryn.schess.Models.Vector;

/**
 * Created by sew on 2016-01-24.
 */
public class QueenMoveStrategy extends MoveStrategy implements IMoveStrategy {

    public QueenMoveStrategy(int _width, int _height) {
        super(_width, _height);
    }

    /**
     * returns possible move for the piece that is at particular position
     * @param  Vector piecePosition current position of the piece
     * @return  returns array of possible piece moves
     */
    @Override
    public Integer[] getMoveForPiece(Vector piecePosition) {
        Integer[] bishopPositions = new BishopMoveStrategy(this.width,this.height).getMoveForPiece(piecePosition);
        Integer[] rockPositions = new RockMoveStrategy(this.width,this.height).getMoveForPiece(piecePosition);
        Integer[] queenPositions = new Integer[bishopPositions.length + rockPositions.length];
        System.arraycopy(bishopPositions, 0,queenPositions, 0, bishopPositions.length);
        System.arraycopy(rockPositions, 0, queenPositions, bishopPositions.length, rockPositions.length);
        return  queenPositions;
    }
}
