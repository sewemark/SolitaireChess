package com.seweryn.schess.Logic;

import com.seweryn.schess.Models.ViewModels.Move;
import com.seweryn.schess.Models.Vector;

/**
 * Created by sew on 25/02/2016.
 */
public class MoveExtractor {
    /**
          * method that recover move that was performed based or should be performed based on board and nextboard
          * @param  int [][] board
          * @param  int [][] next board
          * @return  returns the Move object
      */
    public static Move extractMove(int[][]board, int[][]nextboard){
        Move m = new Move(-1,-1,-1,-1);
        for(int i=0;i< board[0].length; i++){
            for(int j=0;j< board.length;j++){
                if(nextboard[j][i] != board[j][i])
                    if(nextboard[j][i]==0){
                        m.orignialPiecePosition = Vector.convertToScalar(board[0].length, board.length, new Vector(i, j));
                        m.beatingPieceTypeValue = board[j][i];
                    }else {
                        m.destinationPiecePosition = Vector.convertToScalar(board[0].length,board.length,new Vector(i,j));
                        m.defeatedPieceTypeValue = board[j][i];
                    }

            }
        }
        return  m;
    }
}
