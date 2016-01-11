package com.seweryn.schess.Controllers;

/**
 * Created by sew on 2016-01-10.
 */
public  interface  IBoardLogicController {
    boolean checkIfWinPosition();
    boolean removePiece(int t1, int t2, int t3);
    int getPieceAtPosition(int t1);
    void setPieceAtPosition(int t1, int t2);
    int[][] getBoard();
}
