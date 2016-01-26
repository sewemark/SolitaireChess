package com.seweryn.schess.Logic;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Logic.MovesStrategy.IMoveStrategy;
import com.seweryn.schess.Models.Vector;
import com.seweryn.schess.Logic.MovesStrategy.BishopMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.KingMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.KnightMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.PawnMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.QueenMoveStrategy;
import com.seweryn.schess.Logic.MovesStrategy.RockMoveStrategy;

/**
 * Created by sew on 2015-11-08.
 */

public class MoveRulesLogic implements IMoveRulesLogic {
    public MoveRulesLogic(){

    }
    public IMoveStrategy getMoveStrategy(int width, int height, PieceType pieceType){
        IMoveStrategy moveStrategy=null;
        if(pieceType==PieceType.KING) {
            moveStrategy =new KingMoveStrategy(width,height);
        }
        else if(pieceType == PieceType.TOWER){
            moveStrategy =new RockMoveStrategy(width,height);
        }
        else if (pieceType == PieceType.PAWN) {
            moveStrategy= new PawnMoveStrategy(width,height);
        }
        else if(pieceType == pieceType.BISHOP){
            moveStrategy= new BishopMoveStrategy(width,height);
        }
        else if(pieceType == pieceType.HORSE){
            moveStrategy= new KnightMoveStrategy(width,height);
        }
        else if(pieceType ==pieceType.QUEEN){
            moveStrategy= new QueenMoveStrategy(width,height);
        }
       return  moveStrategy;
    }

}
