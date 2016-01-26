package com.seweryn.schess.Logic;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Logic.MovesStrategy.IMoveStrategy;

/**
 * Created by sew on 2016-01-24.
 */
public interface IMoveRulesLogic {
    public IMoveStrategy getMoveStrategy(int width, int height, PieceType pieceType);
}
