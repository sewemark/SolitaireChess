package com.seweryn.schess.Controllers;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Models.Vector;

/**
 * Created by sew on 2016-01-10.
 */
public  interface  IBoardLogicController {

    void removePiece(int t1, int t2, int t3);
    void setPieceAtPosition(int t1, int t2);
    void setBoard(int[][] t1);
    void initializeBoard();
    boolean checkIfWinPosition();
    Vector[] getPiecePositions();
    int[][] performMove(int t1,Vector t2);
    boolean canMove(int t1, Vector t2, PieceType t3);
    int[][] getBoard();
    int getPieceAtPosition(int t1);

}
