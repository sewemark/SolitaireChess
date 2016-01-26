package com.seweryn.schess.Controllers;

/**
 * Created by sew on 2016-01-10.
 */
public  interface  IBoardLogicController {

    void removePiece(int t1, int t2, int t3);
    void setPieceAtPosition(int t1, int t2);
    void setBoard(int[][] t1);
    void initializeBoard();
    boolean checkIfWinPosition();
    int[][] getBoard();
    int getPieceAtPosition(int t1);

}
