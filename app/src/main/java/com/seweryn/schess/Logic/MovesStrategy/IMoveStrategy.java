package com.seweryn.schess.Logic.MovesStrategy;

import com.seweryn.schess.Models.Vector;

/**
 * Created by sew on 2016-01-24.
 */
public interface IMoveStrategy {
     Integer[] getMoveForPiece(Vector piecePosition);
}
