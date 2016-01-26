package com.seweryn.schess.Controllers;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Models.Vector;

/**
 * Created by sew on 2016-01-24.
 */
public interface IMoveRulesController {
    Integer[] PossibleMoves(int width, int height, Vector position, PieceType pieceType);
}
