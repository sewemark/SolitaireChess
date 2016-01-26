package com.seweryn.schess.Controllers;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Logic.IMoveRulesLogic;
import com.seweryn.schess.Logic.MoveRulesLogic;
import com.seweryn.schess.Models.Vector;

/**
 * Created by sew on 2016-01-24.
 */
public class MoveRulesController implements IMoveRulesController {
    private IMoveRulesLogic moveLogic;
    public MoveRulesController(IMoveRulesLogic _moveRulesLogic){
        moveLogic  = _moveRulesLogic;
    }
    @Override
    public Integer[] PossibleMoves(int width, int height, Vector position, PieceType pieceType) {
     return  moveLogic.getMoveStrategy(width,  height, pieceType).getMoveForPiece(position);
    }
}
