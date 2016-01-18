package com.seweryn.schess.Models;

import android.view.View;

/**
 * Created by sew on 2016-01-02.
 */
public class Move {
    public Move(View _view,int _pieceType, int _betatedPieceType, int _sourcePostion, int _destinationPostion){
        this.beatedPieceType = _betatedPieceType;
        this.pieceType = _pieceType;
        this.destinatioPosition = _destinationPostion;
        this.sourPositon = _sourcePostion;
        this.view = _view;

    }
    public Move(int _pieceType, int _betatedPieceType, int _sourcePostion, int _destinationPostion){
        this.beatedPieceType = _betatedPieceType;
        this.pieceType = _pieceType;
        this.destinatioPosition = _destinationPostion;
        this.sourPositon = _sourcePostion;

    }
    public static Move extractMove(int[][]board, int[][]nextboard){
        Move m = new Move(-1,-1,-1,-1);
        for(int i=0;i< board[0].length; i++){
            for(int j=0;j< board.length;j++){
                   if(nextboard[j][i] != board[j][i])
                    if(nextboard[j][i]==0){
                        m.sourPositon= Vector.convertToScalar(board[0].length, board.length, new Vector(i, j));
                        m.pieceType = board[j][i];
                    }else {
                        m.destinatioPosition = Vector.convertToScalar(board[0].length,board.length,new Vector(i,j));
                        m.beatedPieceType = board[j][i];
                    }

            }
        }
        return  m;
    }
    public int pieceType;
    public View view;
    public int beatedPieceType;
    public int sourPositon;
    public int destinatioPosition;
}
